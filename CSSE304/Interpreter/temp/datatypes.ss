(define scheme-value?
  (lambda (x) #t))

(define make-list (lambda (id)
					(cond
						[(list? id) id]
						[(pair? id) (cons (car id) (make-list (cdr id)))]
						[else
							(cons id '())])))

(define-datatype environment environment?
  [empty-env-record]
  [extended-env-record
   (syms (lambda (x) 
   			(or (symbol? x) 
   				((list-of symbol?) x)
   				((list-of symbol?) (make-list x)))))
   (vals (list-of scheme-value?))
   (env environment?)])

(define make-list (lambda (id)
					(cond
						[(list? id) id]
						[(pair? id) (cons (car id) (make-list (cdr id)))]
						[else
							(cons id '())])))

; datatype for procedures.  At first there is only one
; kind of procedure, but more kinds will be added later.
(define-datatype proc-val proc-val?
  [prim-proc
   (name symbol?)]
   [closure
	(ids (lambda (ids) (or (symbol? ids)
							((list-of symbol?) ids)
							((list-of symbol?) (make-list ids)))))
	(bodies (list-of expression?))
	(env environment?)])
	
(define primitive?
	(lambda (id)
		(or 
			(number? id)
			(string? id)
			(boolean? id)
			(char? id)
			(null? id)
			(vector? id))))


(define make-list (lambda (id)
					(cond
						[(list? id) id]
						[(pair? id) (cons (car id) (make-list (cdr id)))]
						[else
							(cons id '())])))

;; Problem 4
;; Parses input	and unparses input.	
 (define-datatype expression expression?
 	[var-exp (id symbol?)]
 	[lit-exp
			(id (lambda (id)  (primitive? id)))]
	[lambda-exp 
			(id (lambda (id)
						(or (symbol? id)
							((list-of symbol?) id))))
            (body (list-of expression?))]
    [nf-lambda-exp
    		(id (lambda (id) 
    				((list-of symbol?) (make-list id))))
    		(body (list-of expression?))]
    [quote-exp
    	(id (lambda (x) #t))]
	[app-exp (rator expression?)
            (rand (list-of expression?))]
	[set!-exp
            (id symbol?)
            (exp expression?)]
	[if-else-exp
			(id expression?)
			(tr expression?)
			(fal expression?)]
	[if-exp
			(id expression?)
			(tr expression?)]
	[letrec-exp
			(def (list-of expression?))
			(body (list-of expression?))]
	[let-exp
		(id (lambda (id)
				(andmap (lambda (x) (and (symbol? (car x)) (expression? (cadr x)))) id)))
		(bodies (list-of expression?))]
	[named-let-exp
			(name symbol?)
			(def (list-of expression?))
			(body (list-of expression?))]
	[let*-exp
			(def (list-of expression?))
			(body (list-of expression?))]
	)

	 
	
;; environment type definitions
