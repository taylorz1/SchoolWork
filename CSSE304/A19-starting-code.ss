(load "chez-init.ss")

(define k)
(define v)
(define L1)
(define L2)
(define ls)


(define-datatype kontinuation kontinuation?
  [init-k]
  [flatten-cdr-k (ls list?) (k kontinuation?)]
  [flatten-car-k  (flattened-cdr list?)
		  (k kontinuation?)]
  [append-k (car-L1 symbol?) (k kontinuation?)]
)

(define apply-k ; k v
  (lambda ()
	 (cases kontinuation k
	    [init-k ()
	       v]
	    [flatten-cdr-k (cdr-ls k-ls)
	       (if (list? (car cdr-ls))
		   (begin (set! k (flatten-car-k v k-ls)) (set! ls (car cdr-ls))
		   (flatten-cps))
		   (begin (set! k k-ls) (set! v (cons (car cdr-ls) v))
		   (apply-k)))]
	    [flatten-car-k (flattened-cdr car-k)
			(set! L1 v)
			(set! L2 flattened-cdr)
			(set! k car-k)
	       (append-cps)]
	    [append-k (car-L1 app-k)
		(set! k app-k)
		(set! v (cons car-L1 v))
		(apply-k)])))


(define append-cps ; L1 L2 k
  (lambda ()
    (if (null? L1)
	(begin (set! v L2)
	(apply-k))
	(begin (set! k (append-k (car L1) k)) (set! L1 (cdr L1))
	(append-cps)))))




(define read-flatten-print
  (lambda ()
    (display "enter slist to flatten: ")
    (let ([slist (read)])
      (unless (eq? slist 'exit)
	  (begin (set! ls slist) (set! k (init-k))
	   (flatten-cps))))))

(define flatten-cps ; ls k
  (lambda ()
    (if (null? ls)
	(begin (set! v ls)
	(apply-k))
	(begin (set! k (flatten-cdr-k ls k)) (set! ls (cdr ls))
	(flatten-cps)))))

'(trace append-cps flatten-cps apply-k)
