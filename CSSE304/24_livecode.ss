;; Day 24
(define apply-continuation (lambda (k v)
	(k v)))
	
(define fact-acc (lambda (n acc)
	(if (zero? n)
		acc
		(fact-acc (- n 1) (* acc n)))))

(define fact-cps
	(lambda (n k)
		(if (zero? n)
			(apply-continuation k 1)
			(fact-cps ( - n 1) (lambda (n-1-fact)
									(apply-continuation k (* n n-1-fact))))
			)))
			
(fact-cps 5 list)


(define memq-cps
	(lambda (sym ls k)
		(cond [(null? ls)
				(apply-continuation k #f)]
				[(eq? (car ls) sym) (apply-continuation k #t)]
				[else
					(memq-cps sym (cdr ls) k)]
			)))
			
(memq-cps 'a '(b c a d) list)
(memq-cps 'a '(b c d) not)

(define intersection
	(lambda (los1 los2)
		(cond
			[(null? los1) '()]
			[(memq (car los1) los2)
				(cons (car los1) (intersection (cdr los1) los2))]
			[else (intersection (cdr los1) los2)])))
			
(define intersection-cps
	(lambda (los1 los2 k)
		(if (null? los1)
			(apply-continuation k '())
			(intersection-cps (cdr los1) los2 
				(lambda (intersection-with-cdr)
					(memq-cps (car los1) los2 
						(lambda (is-car-in-list?)
							(apply-continuation k
								(if is-car-in-list?
									(cons (car los1) intersection-with-cdr)
									intersection-with-cdr)))))))))
									
(intersection-cps
	'(a d e g h) '(s f c h b r a) list)