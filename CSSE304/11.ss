;; Zachary Taylor Assignmnent-11a



;; Problem 1
;; This is a my-let function

(define-syntax my-let
  (syntax-rules ()
    [(_ ((x v) ...) e1 e2 ...)
      ((lambda(x ...) e1 e2 ...) v ...)]
    [(_ name ((x v) ...) e1 e2 ...)
      (letrec [(name (lambda(x ...) e1 e2 ...))]
        (name v ...))]))

(define-syntax my-or
  (syntax-rules ()
    [(_) #f]
    [(_ e1) e1]
    [(_ e1 e2 ...)
      (let ([val e1])
        (if val
            val
            (my-or e2 ...)))]))

(define-syntax +=
  (syntax-rules ()
    [(_ x y)
      (begin (set! x (+ x y)) x)]))

(define-syntax return-first
  (syntax-rules ()
    [(_ e1 e2 ...)
      (let ([val e1])
        (begin e2 ... val))]))
		
		
;; Problem 2
;; This is a bintree to list procedure.
(define-datatype bintree bintree?
	(leaf-node
		(num integer?))
	(interior-node
		(key symbol?)
		(left-tree bintree?)
		(right-tree bintree?)))
		
		
;; bintree-to-list
(define (bintree-to-list tree)
	(cases bintree tree
		[leaf-node (datum) (list 'leaf-node datum)]
		[interior-node (key left right) (list 'interior-node key 
				(bintree-to-list left) (bintree-to-list right))]))


;; Problem 3
;; max-interior node of a bintree
;; ORDER IS MAX_ELEMENT, SUM, RUNNING SUM
(define (max-interior tree)
	(letrec ([max-interiorhelper (lambda (tree)
		(cases bintree tree
			[leaf-node (datum) (list '() datum datum)]
			[interior-node (key left right)
				(let ([Lls (max-interiorhelper left)] [Rls (max-interiorhelper right)])
					(cond
						[(and (null? (car Lls)) (null? (car Rls)));; Both are data
								(list key (+ (cadr Lls) (cadr Rls)) (+ (cadr Lls) (cadr Rls)))]
						[(null? (car Lls)) ;; Right tree
							(let ([sum (+ (caddr Rls) (cadr Lls))])
								(cond
									[(>= sum (cadr Rls))
										(list key sum sum)]
									[else
										(list (car Rls) (cadr Rls) sum)]))]
						[(null? (car Rls)) ;; Left tree
							(let ([sum (+ (caddr Lls) (cadr Rls))])
								(cond
									[(> sum (cadr Lls))
										(list key sum sum)]
									[else
										(list (car Lls) (cadr Lls) sum)]))]
						[else 
							(let* ([treesum (+ (caddr Lls) (caddr Rls))])
								(if (>= (cadr Lls) (cadr Rls))
									(if (>= (cadr Lls) treesum)
										(list (car Lls) (cadr Lls) treesum)
										(list key treesum treesum))
									(if (>= (cadr Rls) treesum)
										(list (car Rls) (cadr Rls) treesum)
										(list key treesum treesum))))]))]))])
			(car (max-interiorhelper tree))))
		
(define multiple-one?
	(lambda (data)
		(or (list-of expression? data)
			(expression? data))))
;; Problem 4
;; Parses input	and unparses input.	
 (define-datatype expression expression?
	[lambda-exp (id 
					(lambda (id)
						(cond
							[(symbol? id)]
							[(list? id) 
								(andmap symbol? id)]
							[else
								(letrec ([make-list (lambda (id)
									(cond
										[(list? id) id]
										[(pair? id) (cons (car id) (make-list (cdr id)))]
										[else
											(cons id '())]))])
									(andmap symbol? (make-list id)))])))
            (body multiple-one?)]
	[app-exp (rator expression?)
            (rand multiple-one?)]
	[set!-exp
            (id symbol?)
            (exp expression?)]
	[lit-exp
			(id (lambda (id) (or (list? id) 
								 (primitive? id))))]
	[if-else-exp
			(id expression?)
			(tr expression?)
			(fal expression?)]
	[if-exp
			(id expression?)
			(tr expression?)]
	[letrec-exp
			(def multiple-one?)
			(body multiple-one?)]
	[let-exp 
			(def multiple-one?)
			(body multiple-one?)]
	[named-let-exp
			(name symbol?)
			(def multiple-one?)
			(body multiple-one?)]
	[let*-exp
			(def multiple-one?)
			(body multiple-one?)]
	[var-exp (id symbol?)])
	
; Procedures to make the parser a little bit saner.
(define var-exp?
 (lambda (x)
   (cases expression x
     [var-exp (id) #t]
     [else #f])))


(define primitive?
	(lambda (id)
	(or 
		(number? id)
		(string? id)
		(boolean? id)
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
						[(symbol? (cadr datum))
							(lambda-exp (cadr datum) (map parse-exp (cddr datum)))]
						[(andmap symbol? (make-list (cadr datum))) 
							(lambda-exp (cadr datum) (map parse-exp (cddr datum)))]
						[else
							(eopl:error 'parse-exp "Error in parse-exp: lambda argument list: formals must be symbols: ~s" (cadr datum))])]
				[else
					(cond
						[(list? (cadr datum)) (eopl:error 'parse-exp "Error in parse-exp: lambda expression: incorrect length: ~s" datum)]
						[else
							(eopl:error 'parse-exp "Error in parse-exp: lambda expression missing body")])])]
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
								[(list? (cadr datum))
									(cond
										[(andmap list? (cadr datum))
											(cond 
												[(andmap (lambda (e) (equal? (length e) 2)) (cadr datum))
													(let-exp (map parse-exp (cadr datum)) (map parse-exp (cddr datum)))]
												[else
													(eopl:error 'parse-exp "Error in parse-exp: decls: not all length 2: ~s" (cadr datum))])]
										[else
											(eopl:error 'parse-exp "Error in parse-exp: decls: not all proper lists: ~s" (cadr datum))])]
								[else
									(eopl:error 'parse-exp "Error in parse-exp decls: not a proper list: ~s" (cadr datum))])])]
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
												(let*-exp (map parse-exp (cadr datum)) (map parse-exp (cddr datum)))]
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
       [(symbol? (car datum))
			(cond
				[(list? (cdr datum)) (app-exp (parse-exp (car datum)) (map parse-exp (cdr datum)))]
				[else
					(eopl:error 'parse-exp "application ~s is not a proper list" datum)])]
	   [else (eopl:error 'parse-exp "Error in parse-exp: decls: first members must be symbols: ~s" datum)])]
	   
     [else (eopl:error 'parse-exp "bad expression: ~s" datum)])))
	 
(define unparse-exp
	(lambda (e)
		(cases expression e
			[var-exp (id) id]
			[lit-exp (id) id]
			[lambda-exp (id body) (append (list 'lambda id) (map unparse-exp body))]
			[set!-exp (id exp) (list 'set! id (unparse-exp e))]
			[if-else-exp (id tr fal) (list 'if (unparse-exp id) (unparse-exp tr) (unparse-exp fal))]
			[if-exp (id tr) (list 'if (unparse-exp id) (unparse-exp tr))]
			[let-exp (def body) (append (list 'let (map unparse-exp def)) (map unparse-exp body))]
			[named-let-exp (name def body) (append (list 'let name (map unparse-exp def)) (map unparse-exp body))]
			[let*-exp (def body) (append (list 'let* (map unparse-exp def)) (map unparse-exp body))]
			[letrec-exp (def body) (append (list 'letrec (map unparse-exp def)) (map unparse-exp body))]
			[app-exp (rator rand) (cons (unparse-exp rator) (map unparse-exp rand))])))