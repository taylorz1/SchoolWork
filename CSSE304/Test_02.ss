;; Something

(define succ
	(lambda (m)
		(cond
			[(null? m) '()]
			[(= (car m) 9)
				(if (null? (cdr m))
					(list 0 1)
					(cons 0 (succ (cdr m))))]
			[else 
				(cons (add1 (car m)) (cdr m))])))
				
