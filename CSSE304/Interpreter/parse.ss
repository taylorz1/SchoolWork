; This is a parser for simple Scheme expressions, such as those in EOPL, 3.1 thru 3.3.

; Procedures to make the parser a little bit saner.
(define 1st car)
(define 2nd cadr)
(define 3rd caddr)

(define primitive?
	(lambda (id)
		(or 
			(number? id)
			(string? id)
			(symbol? id)
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

(define parse-exp         
  (lambda (datum)
    (cond
    [(symbol? datum) (var-exp datum)]
    [(primitive? datum) (lit-exp datum)]
    [(not (list? datum)) (eopl:error 'parse-exp "This is not a proper list: ~s" datum)]
    [(pair? datum)
      (cond
       	[(eqv? (car datum) 'lambda)
        	(cond [(improper? (cadr datum)) (parse-improper-lambda datum)]
	      		[(symbol? (cadr datum)) (parse-nf-lambda datum)]
	      		[else (parse-lambda datum)])]
       			[(eqv? (car datum) 'if)
					(if (= 4 (length datum))
	   					(parse-if-else datum)
						(parse-if datum))]
       			[(eqv? (car datum) 'let)
					(if (symbol? (cadr datum))
	    				(parse-named-let datum)
	    				(parse-let datum))]
       			[(eqv? (car datum) 'let*)
					(parse-let* datum)]
       			[(eqv? (car datum) 'letrec)
					(parse-letrec datum)]
       			[(eqv? (car datum) 'cond)
					(parse-cond datum)]
       			[(eqv? (car datum) 'begin)
					(parse-begin datum)]
       			[(eqv? (car datum) 'while)
					(parse-while datum)]
       			[(eqv? (car datum) 'set!)
					(parse-set! datum)]
       			[(eqv? (car datum) 'quote)
					(parse-quote datum)]
       			[(eqv? (car datum) 'or)
					(parse-or datum)]
       			[(eqv? (car datum) 'and)
					(parse-and datum)]
       			[(eqv? (car datum) 'case)
					(parse-case datum)]
				[(eqv? (car datum) 'for)
					(parse-for datum)]
       			[else (app-exp (parse-exp (1st datum))
		      					(map parse-exp (cdr datum)))])]
    [else (eopl:error 'parse-exp "bad expression: ~s" datum)])))

	
(define parse-for
	(lambda (exp)
			(for-exp 
				(map parse-exp (caadr exp))
				(parse-exp (car (cddadr exp)))
				(if (null? (cddr (cddadr exp)))
					'()
					 (map parse-exp (cddr (cddadr exp))))
				(map parse-exp (cddr exp)))))
	
(define parse-while
  (lambda (exp)
    (while-exp (parse-exp (cadr exp))
			   (map parse-exp (cddr exp)))))

(define parse-case
  (lambda (exp)
    (cond [else (case-exp (parse-exp (cadr exp))
			  (map (lambda(x) (if (eqv? (car x) 'else)
					      (parse-exp (car x))
					      (parse-exp (cons 'quote (list (car x))))))
			       (cddr exp))
			  (map (lambda(x) (parse-exp (cadr x))) (cddr exp)))])))

(define parse-or
  (lambda (exp)
    (cond [else (or-exp (map parse-exp (cdr exp)))])))

(define parse-and
  (lambda (exp)
    (cond [else (and-exp (map parse-exp (cdr exp)))])))

(define parse-begin
  (lambda (exp)
    (cond [else (begin-exp (map parse-exp (cdr exp)))])))

(define parse-cond
  (lambda(exp)
    (cond [(< (length exp) 2) (eopl:error 'parse-exp "mssing arguments: ~s" exp)]
	  [(not (andmap list? (cdr exp))) (eopl:error 'parse-exp "bindings should be all list:~s" (cdr exp))]
	  [(andmap (lambda (x) (not (= 2 (length x)))) (cdr exp))
	   (eopl:error 'parse-exp "all bindings should have length of 2:~s" (cdr exp))]
	  [else (cond-exp (map (lambda (x) (parse-exp (car x))) (cdr exp))
			  (map (lambda (x) (parse-exp (cadr x))) (cdr exp)))])))

(define parse-quote
  (lambda(exp)
    (quote-exp (cadr exp))))

(define parse-lambda
  (lambda(exp)
    (cond [(< (length exp) 3) (eopl:error 'parse-exp "need more arguments: ~s" exp)]
	  [(not (or (symbol? (cadr exp))
		    ((list-of symbol?) (cadr exp))))
	   (eopl:error 'parse-exp "lambda arguments need to be symbols:~s" exp)]
	  [else (lambda-exp (cadr exp)
			    (map parse-exp (cddr exp)))])))

(define parse-improper-lambda
  (lambda(exp)
    (cond [(< (length exp) 3) (eopl:error 'parse-exp "need more arguments: ~s" exp)]
	  [else (improper-lambda-exp (cadr exp)
			    (map parse-exp (cddr exp)))])))

(define parse-nf-lambda
  (lambda(exp)
    (cond [(< (length exp) 3) (eopl:error 'parse-exp "need more arguments: ~s" exp)]
	  [else (nf-lambda-exp (cadr exp)
			    (map parse-exp (cddr exp)))])))


(define parse-if-else
  (lambda(exp)
    (cond [(< (length exp) 3) (eopl:error 'parse-exp "need more arguments: ~s" exp)]
	  [(> (length exp) 4) (eopl:error 'parse-exp "too many arguments: ~s" exp)]
	  [else (if-else-exp (parse-exp (cadr exp))
			(parse-exp (caddr exp))
			(parse-exp (cadddr exp)))])))

(define parse-if
  (lambda(exp)
    (cond [(< (length exp) 2) (eopl:error 'parse-exp "need more arguments: ~s" exp)]
	  [(> (length exp) 3) (eopl:error 'parse-exp "too many arguments: ~s" exp)]
	  [else (if-exp (parse-exp (cadr exp))
			      (parse-exp (caddr exp)))])))
					
(define parse-named-let
  (lambda (exp)
    (cond [else (named-let-exp (cadr exp)
			       (map (lambda (x)
				      (list (car x)
					    (parse-exp (cadr x))))
				    (caddr exp))
			       (map parse-exp (cdddr exp)))])))

(define parse-let
  (lambda(exp)
    (cond [(< (length exp) 3) (eopl:error 'parse-exp "need more arguments: ~s" exp)]
	  [(not ((list-of list?) (cadr exp))) (eopl:error 'parse-exp "binding should be list of list: ~s" exp)]
	  [(not (andmap (lambda(n)
			  (symbol? (car n))) (cadr exp)))
	   (eopl:error 'parse-exp "wrong binding, needs to be symbols: ~s" exp)]
	  [(not (andmap (lambda(x)
			  (= 2 (length x))) (cadr exp)))
	   (eopl:error 'parse-exp "wrong length in binding: ~s" exp)]

	  [else (let-exp (map (lambda (x)
				(list (car x)
				      (parse-exp (cadr x))))
			      (cadr exp))
			 (map parse-exp (cddr exp)))])))

(define parse-let*
  (lambda(exp)
    (cond [(< (length exp) 3) (eopl:error 'parse-exp "need more arguments: ~s" exp)]
	  [(not ((list-of list?) (cadr exp))) (eopl:error 'parse-exp "binding should be list of list: ~s" exp)]
	  [(not (andmap (lambda(n)
			  (symbol? (car n))) (cadr exp)))
	   (eopl:error 'parse-exp "wrong binding, needs to be symbols: ~s" exp)]
	  [(not (andmap (lambda(x)
			  (= 2 (length x))) (cadr exp)))
	   (eopl:error 'parse-exp "wrong length in binding: ~s" exp)]

	  [else (let*-exp (map (lambda (x)
				(list (car x)
				      (parse-exp (cadr x))))
			      (cadr exp))
			 (map parse-exp (cddr exp)))])))

(define parse-letrec
  (lambda(exp)
    (cond [(< (length exp) 3) (eopl:error 'parse-exp "need more arguments: ~s" exp)]
	  [(not ((list-of list?) (cadr exp))) (eopl:error 'parse-exp "binding should be list of list: ~s" exp)]
	  [(not (andmap (lambda(n)
			  (symbol? (car n))) (cadr exp)))
	   (eopl:error 'parse-exp "wrong binding, needs to be symbols: ~s" exp)]
	  [(not (andmap (lambda(x)
			  (= 2 (length x))) (cadr exp)))
	   (eopl:error 'parse-exp "wrong length in binding: ~s" exp)]

	  [else (let ([proc-names (map car (cadr exp))]
		      [idss 
		       (map (lambda (x)
				  (cadr (cadr x)))
			    (cadr exp))]
		      [bodiess  (map (lambda(x) (map parse-exp (cddr (cadr x)))) (cadr exp))]
		      [letrec-bodies (map parse-exp (cddr exp))])
		 
		  (letrec-exp proc-names idss bodiess letrec-bodies))])))

(define parse-set!
  (lambda(exp)
    (cond [(< (length exp) 3) (eopl:error 'parse-exp "need more arguments: ~s" exp)]
	  [(> (length exp) 3) (eopl:error 'parse-exp "too many arguments: ~s" exp)]
	  [else
	   (set!-exp (cadr exp) (parse-exp (cddr exp)))])))
