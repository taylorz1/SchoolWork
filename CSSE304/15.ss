;; Zachary Taylor Assignment-15

;; Problem 2 section 2, the written one. This is the written question.
#| The time savings are less dramatic due to  our function only caching
 the top level return of the function. For instance in fib we can see
that this function will only cache the value of fib(26).
It still has to evaluate fib(26) without caching which leads to the less efficient
memoization. For instance fib(26) = fib(25) + fib(24). Then fib(25) = fib(24) + fib(23)
So we can say that fib(26) = 2*fib(24) + fib(23), etc. In this version
we actually have to calculate fib(24) twice. In the class version we would have cached
fib(24) the first time, meaning that we don't need to calculate fib(24) again. Essentially
the in class version used knowledge of the recurrance relation that is not supplied
in the barebones abstract memoization function to increase efficiency. |#

;; Problem 1 a)

;; member?-cps, This is the member procedure written in cps form.

(define apply-continuation
	(lambda (k . args)
	  (apply k args)))
	  

(define member?-cps
	(lambda (item ls k)
		(cond
			[(null? ls) (apply-continuation k #f)]
			[(equal? item (car ls)) (apply-continuation k #t)]
			[else (member?-cps item (cdr ls) k)])))
			
;; Problem 1 b)

;; This is the solution to set?-cps

(define set?-cps
	(lambda (ls k)
	
		(cond
			[(null? ls) (apply-continuation k #t)]
			[(not (pair? ls)) (apply-continuation k #f)]
			[else
				(member?-cps (car ls) (cdr ls) (lambda (x)
					(if x
						(apply-continuation k #f)
						(set?-cps (cdr ls) k))))])))

;; Problem 1 between b and c
;; This is the solution to domain-cps

(define 1st-cps
	(lambda (ls k)
		(apply-continuation k (car ls))))

(define set-of-cps
	(lambda (ls k)
		(cond
			[(null? ls) (apply-continuation k '())]
			[else
				(member?-cps (car ls) (cdr ls) (lambda (x)
					(if x
						(set-of-cps (cdr ls) k)
						(set-of-cps (cdr ls) (lambda (x) 
							(apply-continuation k (cons (car ls) x)))))))])))

(define map-cps
	(lambda (proc-cps ls k)
		(cond
			[(null? ls) (apply-continuation k '())]
			[else
				(proc-cps (car ls) (lambda (x) (map-cps proc-cps (cdr ls) 
					(lambda (j) (apply-continuation k (cons x j))))))])))
					
(define domain-cps 
	(lambda (rel k)
		(map-cps 1st-cps rel (lambda(x) (set-of-cps x k)))))
		
;; Problem 1 c)
(define make-cps
	(lambda (proc)
		(lambda (x k)
			(apply-continuation k (proc x)))))
					
;; Problem 1 d)
(define andmap-cps
	(lambda (proc-cps ls k)
		(cond
			[(null? ls) (apply-continuation k #t)]
			[else
				(proc-cps (car ls) (lambda (x)
					(if x
						(andmap-cps proc-cps (cdr ls) k)
						(apply-continuation k #f))))])))
					

		

;; Problem 1 e)
(define cps-snlist-recur
	(lambda (base-value item-proc-cps list-proc-cps)
		(letrec
			([helper (lambda (ls k)
						(if (null? ls)
							(apply-continuation k base-value)
							(let ([c (car ls)])
								 (if (or (pair? c) (null? c))
									  (apply-continuation helper c (lambda (x)
										(apply-continuation helper (cdr ls) (lambda (y)
											(apply-continuation list-proc-cps x y k)))))
									  (apply-continuation helper (cdr ls) (lambda(x)
										(apply-continuation item-proc-cps c x k)))))))])
			helper)))

(define (sn-list-reverse-cps snlist k)
	((cps-snlist-recur '() (lambda (x y k) 
			(apply-continuation append-cps y (list x) k))
		(lambda (x y k) 
			(apply-continuation append-cps y (list x) k))) snlist k))
		
(define (sn-list-occur-cps ele snlist k)
	((cps-snlist-recur 0 (lambda (x y k) 
			(if (equal? ele x) 
				(apply-continuation add1-cps y k) 
				(apply-continuation k y)))
			+-cps) snlist k))
			
(define (sn-list-depth-cps snlist k)
	((cps-snlist-recur 1 (lambda (x y k)
								(apply-continuation k y))
						  (lambda (x y k)
								(apply-continuation max-cps (add1 x) y k))) snlist k))
								
(define +-cps
	(lambda (a b k)
		(apply-continuation k (+ a b))))
		
(define max-cps
	(lambda (a b k)
		(apply-continuation k (max a b))))

(define add1-cps
	(lambda (a k)
		(apply-continuation k (add1 a))))
		
(define append-cps
	(lambda (l1 l2 k)
		(if (null? l1)
			(apply-continuation k l2)
			(apply-continuation append-cps (cdr l1) l2 (lambda(x) 
				(apply-continuation k (cons (car l1) x)))))))

;; Problem 2
;; This function is a procedure that memoizes any function form

(define memoize
	(lambda (proc hash equiv?)
		(let ([sofar (make-hashtable hash equiv?)])
			(lambda val
				(if (hashtable-contains? sofar val)
					(hashtable-ref sofar val #f)
					(let ([insert (apply proc val)])
						(begin (hashtable-set! sofar val insert) 
						insert)))))))
			
;; Problem 3
;; This function is subst-leftmost using mutli-value returns	
(define subst-leftmost
	(lambda (new old slist proceq)
		(letrec ([ls slist]
				[finder
					(lambda(ls)
					  (cond
						[(null? ls) (values '() #f)]
						[(symbol? (car ls))
						  (cond
							[(proceq (car ls) old)
							  (values (cons new (cdr ls)) #t) ]
							[else
							  (call-with-values (lambda () (finder (cdr ls)))
								(lambda (x y) (values (cons (car ls) x) y)))])]
						[else
						  (call-with-values (lambda() (finder (car ls))) 
							(lambda (x y)
							(cond
							  [y
								(values (cons x (cdr ls)) y)]
							  [else
								(call-with-values (lambda () (finder (cdr ls)))
								(lambda (x y)
								  (values (cons (car ls) x) y)))])))]))])
			(call-with-values (lambda() (finder ls )) (lambda (x y) x)))))