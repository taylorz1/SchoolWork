(define scheme-value?
  (lambda (x) #t))

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

(define proper-id
  (lambda(ls)
    (if (primitive? ls)
	(list ls)
	(cons (car ls) (proper-id (cdr ls))))))

(define improper?
  (lambda(ls)
    (if (pair? ls)
	    (if (symbol? (cdr ls))
	      #t
	      (improper? (cdr ls)))
	    #f)))

(define (check-improper? id)
	(ormap (lambda (x) (or ((list-of symbol?) x) ((list-of improper?) x))) id))

(define-datatype environment environment?
  	[empty-env-record]
  	[extended-env-record
   		(syms (list-of symbol?))
   		(vals (list-of scheme-value?))
   		(env environment?)]
   	[recursively-extended-env-record
		(procnames (list-of symbol?))
		(id check-improper?)
		(bodies (list-of (list-of expression?)))
		(env environment?)])

; datatype for procedures.  At first there is only one
; kind of procedure, but more kinds will be added later.
(define-datatype proc-val proc-val?
  	[prim-proc
   		(name symbol?)]
   	[closure
		(ids (lambda (ids) (or (symbol? ids)
							(pair? ids)
							(null? ids))))
		(bodies (list-of expression?))
		(env environment?)]
	[improper-closure 
		(ids improper?)
		(bodies (list-of expression?))
		(env environment?)]
	[nf-closure 
		(ids symbol?)
		(bodies (list-of expression?))
		(env environment?)])

;; Problem 4
;; Parses input	and unparses input.	
 (define-datatype expression expression?
 	[var-exp (id symbol?)]
 	[lit-exp
			(id primitive?)]
	[lambda-exp 
			(id (list-of symbol?))
            (bodies (list-of expression?))]
    [nf-lambda-exp
    		(id symbol?)
    		(bodies (list-of expression?))]
    [improper-lambda-exp
    		(id improper?)
    		(bodies (list-of expression?))]
    [quote-exp
    	(id (lambda (x) #t))]
	[app-exp (rator expression?)
            (rand (list-of expression?))]
    [while-exp 
    	(test-exp expression?)
    	(bodies (list-of expression?))]
	[set!-exp
            (id symbol?)
            (bodies expression?)]
	[if-else-exp
			(id expression?)
			(tr expression?)
			(fal expression?)]
	[if-exp
			(id expression?)
			(tr expression?)]
	[letrec-exp
		(procnames (list-of symbol?))
		(id check-improper?)
		(bodies (list-of (list-of expression?)))
		(letrec-bodies (list-of expression?))]
	[let-exp
		(id (lambda (id)
				(andmap (lambda (x) (and (symbol? (car x)) (expression? (cadr x)))) id)))
		(bodies (list-of expression?))]
	[named-let-exp
		(name symbol?)
		(id (lambda (id)
			(andmap (lambda (x) (and (symbol? (car x)) (expression? (cadr x)))) id)))
		(bodies (list-of expression?))]
	[let*-exp
		(id (lambda (id)
			(andmap (lambda (x) (and (symbol? (car x)) (expression? (cadr x)))) id)))
		(bodies (list-of expression?))]
	[cond-exp
   		(test-exp (list-of (lambda (x) 
   								(or (eqv? x 'else)
								(expression? x)))))
   		(bodies (list-of expression?))]
  	[or-exp
   		(bodies (list-of expression?))]
  	[and-exp
   		(bodies (list-of expression?))]
  	[begin-exp
   		(bodies (list-of expression?))]
  	[case-exp
   		(test expression?)
   		(ls (list-of (lambda (x)
   						(or (eqv? x 'else)
			 			(list? x)))))
   		(bodies (list-of expression?))]
	[for-exp
		(init (lambda(x) (or (null? x) (list-of expression? x) (expression? x))))
		(test expression?)
		(update (lambda(x) (or (null? x) (list-of expression? x))))
		(bodies (list-of expression?))]
   )

	 
	
;; environment type definitions
