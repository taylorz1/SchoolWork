
; top-level-eval evaluates a form in the global environment
(define top-level-eval
  (lambda (form)
    ; later we may add things that are not expressions.
    (eval-exp form init-env)))

; eval-exp is the main component of the interpreter
; Fix eval-exp
(define eval-exp
 (let ([identity-proc (lambda (x) x)])
  (lambda (exp env)
    (cases expression exp
      [lit-exp (datum) datum]
      [var-exp (id)
				(apply-env env id
					identity-proc
						(lambda ()
							(error 'apply-env
										"variable ~s is not bound" id)))]
      [app-exp (rator rands)
        (let ([proc-value (eval-exp rator env)]
              [args (eval-rands rands env)])
          (apply-proc proc-value args))]
      [if-exp (id tr)
      	(if (eval-exp id env)
      		(eval-exp tr env))]
      [if-else-exp (id tr fal)
      	(if (eval-exp id env) (eval-exp tr env) (eval-exp fal env))]
      [quote-exp (id)
      	id]
      [lambda-exp (id body)
		(closure id body env)]
	  [nf-lambda-exp (id body)
	    (closure id body env)]
	  [let-exp (id bodies)
  			(let ([vars (map car id)]
  				[exps (map cadr id)])
		(eval-bodies bodies 
			(extend-env vars (eval-rands exps env) env)))]
      [else (eopl:error 'eval-exp "Bad abstract syntax: ~a" exp)]))))

(define syntax-expand
	(lambda (exp)
		(cases expression exp
			[let-exp (id bodies)
				(app-exp (lambda-exp (map car id) (map syntax-expand bodies))
						(map syntax-expand (map cadr id)))]
;(define (let->application letproc)
;	(cons
;		(list `lambda (map car (cadr letproc)) (caddr letproc))
;		 (map cadr (cadr letproc))))
			[app-exp (rator rand)
				(let ([outrand rand])
					(cases expression rator
						[var-exp (id)
							(cond [(eqv? id 'cond)
									(if (< (length rand) 2)
										(cases expression (car rand)
											[app-exp (rator rand)
												(cases expression rator
													[var-exp (id)
														(syntax-expand (car rand))]
													[else
														(if-exp (syntax-expand rator) (syntax-expand (car rand)))])]
											;[lit-exp (datum)
											;	(rand)]
											[else 
												(syntax-expand (car rand))])
										(cases expression (car rand)
											[app-exp (rator rand)
												(if-else-exp (syntax-expand rator) 
															(syntax-expand (car rand)) 
															(syntax-expand (app-exp (var-exp 'cond) (cdr outrand))))]
											[var-exp (id)
												(syntax-expand (car rand))]
											[else 
												(eopl:error 'eval-exp "Bad abstract syntax: ~a" 'cond)]))]
								[(eqv? id 'begin)
									(app-exp (lambda-exp '() (map syntax-expand rand)) '())]
								[(eqv? id 'or)
									(cond [(null? rand) (lit-exp #f)]
										[(< (length rand) 2)
											(syntax-expand (car rand))]
										[else
											(if-else-exp (syntax-expand (car rand)) 
														(syntax-expand (car rand))
														(syntax-expand (app-exp (var-exp 'or) (cdr outrand))))])]
								[(eqv? id 'case)
									(cond [(< (length rand) 3)
											(cases expression (cadr rand)
												[app-exp (rator rand)
													(car rand)]
												[else (syntax-expand rand)])]
										[else 
											(if-else-exp (app-exp (var-exp 'member) 
											(list (car rand)
												(cases expression (cadr rand)
													[app-exp (rator rand)
														(cases expression (rator)
															[app-exp (rator rand)
																(cons rator rand)]
															[else (syntax-expand rand)])]
													[else (syntax-expand rand)])))
											(cases expression (cadr rand)
												[app-exp (rator rand)
													(car rand)]
												[else (syntax-expand rand)])
											(syntax-expand (app-exp (var-exp 'case) (cons (car rand) (cddr rand)))))])]
								[else exp])]
						[else exp]))]
			[if-exp (id tr)
				(if-exp (syntax-expand id) (syntax-expand tr))]
			[else
				exp])))

;; Useful for lambda, let, let*, letrec, begin
(define eval-bodies
	(lambda (bodies env)
		(if (null? (cdr bodies))
			(eval-exp (car bodies) env)
			(begin
				(eval-exp (car bodies) env)
				(eval-bodies (cdr bodies) env)))))

(define eval-rands
  (lambda (rands env)
    (map (lambda (x) 
    	(eval-exp x env)) rands)))

(define apply-proc
  (lambda (proc-value args)
    (cases proc-val proc-value
      [prim-proc (op) (apply-prim-proc op args)]
	  [closure (ids bodies env)
		(eval-bodies bodies 
			(extend-env ids args env))]
			; You will add other cases
      [else (error 'apply-proc
                   "Attempt to apply bad procedure: ~s" 
                    proc-value)])))

;  Apply a procedure to its arguments.
;  At this point, we only have primitive procedures.  
;  User-defined procedures will be added later.
(define *prim-proc-names* '(+ - * / add1 sub1 cons = zero? not < > >= <= list null?
	assq eq? equal? atom? length list? list->vector pair? procedure? vector->list vector
	vector make-vector vector-ref vector? number? symbol? set-car! set-cdr!
	vector-set! display newline car cdr caar cadr cdar cddr caaar caadr cadar 
	cdaar caddr cddar cdadr cdddr apply map member))

; Usually an interpreter must define each 
; built-in procedure individually.  We are "cheating" a little bit.

(define apply-prim-proc
  (lambda (prim-proc args)
    (case prim-proc
      [(+) (apply + args)]
      [(-) (apply - args)]
      [(*) (apply * args)]
	  [(/) (apply / args)]
      [(add1) (+ (1st args) 1)]
      [(sub1) (- (1st args) 1)]
      [(cons) (cons (1st args) (2nd args))]
      [(=) (apply = args)]
	  [(zero?) (zero? (1st args))]
	  [(not) (not (1st args))]
	  [(<) (< (1st args) (2nd args))]
	  [(>) (> (1st args) (2nd args))]
	  [(>=) (>= (1st args) (2nd args))]
	  [(<=) (<= (1st args) (2nd args))]
	  [(list) args]
	  [(null?) (null? (1st args))]
	  [(assq) (assq (1st args) (2nd args))]
	  [(eq?) (eq? (1st args) (2nd args))]
	  [(equal?) (equal? (1st args) (2nd args))]
	  [(atom?) (atom? (1st args))]
	  [(length) (length (1st args))]
	  [(list?) (list? (1st args))]
	  [(list->vector) (list->vector (1st args))]
	  [(pair?) (pair? (1st args))]
	  [(procedure?) (proc-val? (1st args))]
	  [(vector->list) (vector->list (1st args))]
	  [(vector) (apply vector args)]
	  [(make-vector) (make-vector (1st args) (2nd args))]
	  [(vector-ref) (vector-ref (1st args) (2nd args))]
	  [(vector?) (vector? (1st args))]
	  [(number?) (number? (1st args))]
	  [(symbol?) (symbol? (1st args))]
	  [(set-car!) (set-car! (1st args) (2nd args))]
	  [(set-cdr!) (set-cdr! (1st args) (2nd args))]
	  [(vector-set!) (vector-set! (1st args) (2nd args) (3rd args))]
	  [(display) (display (1st args))]
	  [(newline) (newline)]
	  [(car) (car (1st args))]
	  [(cdr) (cdr (1st args))]
	  [(caar) (caar (1st args))]
	  [(cadr) (cadr (1st args))]
	  [(cdar) (cdar (1st args))]
	  [(cddr) (cddr (1st args))]
	  [(caaar) (caaar (1st args))]
	  [(caadr) (caadr (1st args))]
	  [(cadar) (cadar (1st args))]
	  [(cdaar) (cdaar (1st args))]
	  [(caddr) (caddr (1st args))]
	  [(cddar) (cddar (1st args))]
	  [(cdadr) (cdadr (1st args))]
	  [(cdddr) (cdddr (1st args))]
	  [(apply) (apply (lambda x (apply-proc (1st args) x)) 
	  				(cadr args))]
	  [(map) (map (lambda x (apply-proc (1st args) x)) 
	  				(cadr args))]
	  [(member) (member (1st args) (2nd args))]
      [else (error 'apply-prim-proc 
            "Bad primitive procedure name: ~s" 
            prim-op)])))

(define init-env         ; for now, our initial global environment only contains 
  (extend-env            ; procedure names.  Recall that an environment associates
     *prim-proc-names*   ;  a value (not an expression) with an identifier.
     (map prim-proc      
          *prim-proc-names*)
     (empty-env)))

; evaluate the list of operands, putting results into a list

(define rep      ; "read-eval-print" loop.
  (lambda ()
    (display "--> ")
    ;; notice that we don't save changes to the environment...
    (let ([answer (top-level-eval (parse-exp (read)))])
      ;; TODO: are there answers that should display differently?
      (eopl:pretty-print answer) (newline)
      (rep))))  ; tail-recursive, so stack doesn't grow.

(define eval-one-exp
  (lambda (x) (top-level-eval (syntax-expand (parse-exp x)))))

				
