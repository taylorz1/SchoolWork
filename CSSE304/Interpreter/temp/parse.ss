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
     [(pair? datum)
      (cond
       [(eqv? (car datum) 'lambda)
			(cond
				[(>= (length datum) 3)
					(cond
						[(not (or 	(symbol? (cadr datum)) 
									((list-of symbol?) (cadr datum))
									((list-of symbol?) (make-list (cadr datum)))))
							(eopl:error 'parse-exp "Error in parse-exp: lambda argument list: formals must be symbols: ~s" (cadr datum))]
						[(pair? (cadr datum))
							(nf-lambda-exp (cadr datum) (map parse-exp (cddr datum)))]
						[(symbol? (cadr datum))
							(nf-lambda-exp (cadr datum) (map parse-exp (cddr datum)))]
						[else
							(lambda-exp (cadr datum)
								(map parse-exp (cddr datum)))])]
				[else (eopl:error 'parse-exp "Error in parse-exp: lambda expression: incorrect length: ~s" datum)])]
	   [(eqv? (car datum) 'if)
			(cond
				[(equal? (length datum) 4) (if-else-exp 
										(parse-exp (cadr datum)) 
										(parse-exp (caddr datum))
										(parse-exp (cadddr datum)))]
				[(equal? (length datum) 3)
					(if-exp (parse-exp (cadr datum)) (parse-exp (caddr datum)))]
				[(> (length datum) 4)
					(eopl:error 'parse-exp "Error in parse-exp: if expression: should have (only) test, then, and else clauses: ~s" datum)]
				[else
					(eopl:error 'parse-exp "Error in parse-exp: if: missing then and else parts: ~s" datum)])]
	   [(eqv? (car datum) 'set!)
			(cond
				[(equal? (length datum) 3)
					(set!-exp (cadr datum) (parse-exp (caddr datum)))]
				[(> (length datum) 3)
					(eopl:error 'parse-exp "Error in parse-exp: set!: Too many parts: ~s" datum)]
				[else (eopl:error 'parse-exp "Error in parse-exp: set!: missing expression: ~s" datum)])]
	   [(eqv? (car datum) 'let)
			(cond 
				[(> (length datum) 2)
					(cond
						[(symbol? (cadr datum)) 
							(cond
								[(list? (caddr datum))
									(cond
										[(andmap list? (caddr datum))
											(cond 
												[(andmap (lambda (e) (equal? (length e) 2)) (caddr datum))
													(named-let-exp (map parse-exp (caddr datum)) (map parse-exp (cdddr datum)))]
												[else
													(eopl:error 'parse-exp "Error in parse-exp: decls: not all length 2: ~s" (caddr datum))])]
										[else
											(eopl:error 'parse-exp "Error in parse-exp: decls: not all proper lists: ~s" (caddr datum))])]
								[else
									(eopl:error 'parse-exp "Error in parse-exp decls: not a proper list: ~s" (caddr datum))])]
						[else
							(cond
								[(not ((list-of list?) (2nd datum)))
									(eopl:error 'parse-exp "Error in parse-exp: decls: not all proper lists: ~s" (cadr datum))]
								[(not (andmap (lambda (x) (symbol? (car x))) (cadr datum)))
									(eopl:error 'parse-exp "Error in parse-exp: let argument list: formals must be symbols: ~s" (cadr datum))]
								[(not (andmap (lambda (x) (eqv? 2 (length x))) (cadr datum)))
									(eopl:error 'parse-exp "Error in parse-exp: decls: not all length 2: ~s" (cadr datum))]
								[else (let-exp (map (lambda (x)
														(list (car x) (parse-exp (cadr x))))
													(cadr datum))
										(map parse-exp (cddr datum)))])])]
				[else
					(eopl:error 'parse-exp "Error in parse-expression: let expression: incorrect length: ~s" datum)])]
	   [(eqv? (car datum) 'let*)
				(cond
					[(> (length datum) 2)
						(cond
							[(list? (cadr datum))
								(cond
									[(andmap list? (cadr datum))
										(cond 
											[(andmap (lambda (e) (equal? (length e) 2)) (cadr datum))
												(let*-exp (car (map list (map car (cadr datum)) (map parse-exp (map cadr (cadr datum))))) (map parse-exp (cddr datum)))]
											[else
												(eopl:error 'parse-exp "Error in parse-exp: decls: not all length 2: ~s" (cadr datum))])]
									[else
										(eopl:error 'parse-exp "Error in parse-exp: decls: not all proper lists: ~s" (cadr datum))])]
							[else
								(eopl:error 'parse-exp "Error in parse-exp: let* declarations not a list")])]
					[else
						(eopl:error 'parse-exp "Error in parse-expression: let* expression: incorrect length: ~s" datum)])]
	   [(eqv? (car datum) 'letrec)
				(cond
					[(> (length datum) 2)
						(cond
							[(list? (cadr datum))
								(cond
									[(andmap list? (cadr datum))
										(cond 
											[(andmap (lambda (e) (equal? (length e) 2)) (cadr datum))
												(letrec-exp (map parse-exp (cadr datum)) (map parse-exp (cddr datum)))]
											[else
												(eopl:error 'parse-exp "Error in parse-exp: decls: not all length 2: ~s" (cadr datum))])]
									[else
										(eopl:error 'parse-exp "Error in parse-exp: decls: not all proper lists: ~s" (cadr datum))])]
							[else
								(eopl:error 'parse-exp "Error in parse-exp: letrec declarations not a list")])]
					[else
						(eopl:error 'parse-exp "Error in parse-expression: letrec expression: incorrect length: ~s" datum)])]
       [(eqv? (car datum) 'quote)
       		(quote-exp (2nd datum))]
       [else
			(app-exp (parse-exp (1st datum))
				(map parse-exp (cdr datum)))]
			)]
	 [else (eopl:error 'parse-exp "bad expression: ~s" datum)])))
	 









