
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
							(apply-env global-env
								id
								identity-proc
								(lambda ()
									(error 'apply-env
										"variable ~s is not bound" id)))))]
      [app-exp (rator rands)
        (let ([proc-value (eval-exp rator env)]
              [args (eval-rands rands env)])
        	(if ((list-of primitive?) (list args))
        		(apply-proc proc-value (list args))
        		(apply-proc proc-value args)))]
      [if-exp (id tr)
      	(if (eval-exp id env)
      		(eval-exp tr env))]
      [if-else-exp (id tr fal)
      	(if (eval-exp id env) (eval-exp tr env) (eval-exp fal env))]
      [quote-exp (id)
      	id]
      [while-exp (test-exp bodies)
      	(if (eval-exp test-exp env) 
      		(eval-bodies (append bodies (list exp)) env))]
			
	  ;; I don't know why it doesn't update what i refers to.
	  [for-exp (init test update bodies)
		(lambda ()
			(eval-bodies init env)
			(if (eval-exp test env)
				(eval-bodies (append bodies update (list
					;; we've already done init
					(for-exp '() test update bodies))) env)
				(eval-bodies bodies env)))
				]
      [lambda-exp (id bodies)
		(closure id bodies env)]
	  [nf-lambda-exp (id bodies)
	    (nf-closure id bodies env)]
	  [improper-lambda-exp (id bodies)
	  	(improper-closure id bodies env)]
	  [let-exp (id bodies)
  		(let ([vars (map car id)]
  				[exps (map cadr id)])
		(eval-bodies bodies 
			(extend-env vars (eval-rands exps env) env)))]
	  ;; Change made here.
	  [letrec-exp (procnames id bodies letrec-bodies)
		(eval-bodies letrec-bodies (extend-env-recursively procnames id bodies env))]
      [else (eopl:error 'eval-exp "Bad abstract syntax: ~a" exp)]))))

(define syntax-expand
  (lambda (exp)
    (cases expression exp
	   [let-exp (bind bodies)
		    (expand-let bind bodies)]
	   [let*-exp (bind bodies)
		     (syntax-expand (let*->let bind bodies))]

	   [cond-exp (test vals)
		     (expand-cond test vals)]
	   [begin-exp (bodies)
		      (expand-begin bodies)]
	   [or-exp (bodies)
		   (expand-or bodies)]
	   [and-exp (bodies)
		    (expand-and bodies)]
	   [case-exp (test range vals)
		     (expand-case test range vals)]
	   [named-let-exp (name bind bodies)
			  (named->letrec name bind bodies)]
	   [if-else-exp (test then else)
		   (if-else-exp (syntax-expand test)
			   (syntax-expand then)
			   (syntax-expand else))]
	   [if-exp (test then)
			 (if-exp (syntax-expand test)
				       (syntax-expand then))]
	   [letrec-exp (proc-names idss bodiess letrec-bodies)
	   		(letrec-exp proc-names idss 
	   			(map (lambda (x) (map syntax-expand x)) bodiess) 
	   			(map syntax-expand letrec-bodies))]
	   [for-exp (init test update bodies)
			(for-exp (map syntax-expand init) (syntax-expand test)
				(map syntax-expand update) (map syntax-expand bodies))]
	   [else exp]
	   )))

(define named->letrec
  (lambda (name bind bodies)
    (let ([proc-names (list name)]
	  [idss (list (map car bind))]
	  [bodiess (list (map syntax-expand bodies))]
	  [letrec-bodies (map syntax-expand (list (app-exp (var-exp name) (map cadr bind))))])
      (letrec-exp proc-names idss bodiess letrec-bodies))))


(define expand-case
  (lambda (test range vals)
    (cond [(eqv? 'else (cadar range)) (syntax-expand (car vals))]
	  [(eqv? 'else (cadadr range)) (if-else-exp (app-exp (var-exp 'member) (list (syntax-expand test) (car range)))
					       (syntax-expand (car vals))
					       (syntax-expand (cadr vals)))]
	  [else (if-else-exp  (app-exp (var-exp 'member) (list (syntax-expand test) (car range)))
			 	(syntax-expand (car vals))
			 	(expand-case test (cdr range) (cdr vals)))])))

(define expand-or
  (lambda (bodies)
    (if (null? bodies)
		(parse-exp '#f)
		(if-else-exp (syntax-expand (car bodies))
			(syntax-expand (car bodies))
			(expand-or (cdr bodies))))))

(define expand-and
  (lambda (bodies)
    (if (null? bodies)
	(parse-exp '#t)
	(if-else-exp (syntax-expand (car bodies))
		(syntax-expand (car bodies))
		(expand-and (cdr bodies))))))

(define expand-begin
  (lambda (bodies)
    (app-exp (lambda-exp (list)
			 (map syntax-expand bodies)) '())))

(define expand-let
  (lambda (bind bodies)
    (let ([syms (map car bind)]
	  [vals (map cadr bind)])
      (app-exp (lambda-exp syms (map syntax-expand bodies))(map syntax-expand vals)))))

(define let*->let
  (lambda(bind bodies)
    (if (null? (cdr bind))
	(let-exp  (list (list (caar bind) (syntax-expand (cadar bind)))) (map syntax-expand bodies))
        (let-exp (list (list (caar bind) (syntax-expand (cadar bind)))) (list (let*->let (cdr bind) bodies))))))

(define expand-cond
  (lambda(test vals)
    (cond
     [(eqv? 'else (cadar test)) (syntax-expand (car vals))]
     [(null? (cdr test)) (if-exp (syntax-expand (car test))
				      	 		(syntax-expand (car vals)))]
	  [(eqv? 'else (cadadr test)) (if-else-exp (syntax-expand (car test))
					      					(syntax-expand (car vals))
					      					(syntax-expand (cadr vals)))]
	  [else (if-else-exp (syntax-expand (car test))
						(syntax-expand (car vals))
						(expand-cond (cdr test) (cdr vals)))])))

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

(define (proper-args ls args)
   	(if (primitive? ls)
		(list args)
		(cons (car args) (proper-args (cdr ls) (cdr args)))))

(define proper-id
  (lambda(ls)
    (if (primitive? ls)
		(list ls)
		(cons (car ls) (proper-id (cdr ls))))))

(define apply-proc
  (lambda (proc-value args)
    (cases proc-val proc-value
      	[prim-proc (op) (apply-prim-proc op args)]
	  	[closure (ids bodies env)
			(eval-bodies bodies 
				(extend-env ids args env))]
				; You will add other cases
	  	[improper-closure (ids bodies env)
	  		(eval-bodies bodies
	  			(extend-env (proper-id ids) (proper-args ids args) env))]
	  	[nf-closure (ids bodies env)
	  		(eval-bodies bodies
	  			(extend-env (list ids) (list args) env))]
      	[else (error 'apply-proc
                "Attempt to apply bad procedure: ~s" 
                proc-value)])))

;  Apply a procedure to its arguments.
;  At this point, we only have primitive procedures.  
;  User-defined procedures will be added later.
(define *prim-proc-names* '(+ - * / add1 sub1 cons = zero? not < > >= <= list null?
	assq eq? eqv? equal? atom? length list? list->vector pair? procedure? vector->list vector
	vector make-vector vector-ref vector? number? symbol? set-car! set-cdr!
	vector-set! display newline car cdr caar cadr cdar cddr caaar caadr cadar 
	cdaar caddr cddar cdadr cdddr apply map member quotient append list-tail void))

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
	  [(eqv?) (eqv? (1st args) (2nd args))]
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
	  [(quotient) (apply quotient args)]
	  [(append) (apply append args)]
	  [(list-tail) (list-tail (1st args) (2nd args))]
	  [(void) (void)]
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

				
