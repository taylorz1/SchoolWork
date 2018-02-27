;; Zachary Taylor Assignment-05

;; Below are functions from previous assignments.
;; From 1.ss
;; This procedure takes two intervals and returns their union
(define interval-union
    (lambda (i1 i2)
      (if (interval-intersects? i1 i2) (list (list (min (car i1) (car i2))
          (max (cadr i1) (cadr i2)))) (list i1 i2))))

(define interval-intersects?
      (lambda (i1 i2)
        (or (interval-contains? i1 (cadr i2))
          (interval-contains? i1 (car i2))
          (interval-contains? i2 (cadr i1))
          (interval-contains? i2 (car i1)))))

(define interval-contains? (lambda (interval number)
    (and (<= number (cadr interval)) (>= number (car interval)))))


;; Fact from given code.
(define fact
  (lambda (n)
    (if (zero? n)
        1
        (* n (fact (- n 1))))))

;; Problem 1
;; This procedure minimizes an interval from a list of intervals.

(define minimize-interval-list
  (lambda (ls)
    (list-builder-helper (car (sort-first ls)) (cdr (sort-first ls)))))

;; This procedure loops over the elements of an input list and builds an output
;; list of the minimized intervals

 (define sort-first
  (lambda (ls)
    (list-sort
        (lambda (x y)
            (< (car x) (car y))) ls)))

(define list-builder-helper
  (lambda (pair ls)
    (if (null? ls)
      (list pair)
      (if (<= (caar ls) (cadr pair))
        (list-builder-helper (car (interval-union pair (car ls))) (cdr ls))
        (cons pair (list-builder-helper (car ls) (cdr ls)))))))



;; Problem 2
;; This procedure returns true if the given procedure returns true for any element.
(define exists?
  (lambda (pred ls)
    (if (null? ls) #f
    (or (pred (car ls)) (exists? pred (cdr ls))))))


;; Problem 3
;; This procedure returns the 0-based position of the first element of ls that
;; satisfies the predicate pred.
(define list-index
  (lambda (pred ls)
    (list-index-counter pred ls 0)))

;; Helper that has a counter.
(define list-index-counter
  (lambda (pred ls n)
    (if (null? ls) #f
    (if (pred (car ls)) n
      (list-index-counter pred (cdr ls) (+ n 1))))))


;; Problem 4
;; This procedure takes an integer n, and returns a "list of lists"
;; representation of the first n+1 rows of Pascal's triangle.
(define pascal-triangle
  (lambda (n)
    (if (< n 0) '()
      (cons (pascal-triangle-print n n) (pascal-triangle (- n 1))))))

;; This creates lists line by line.
(define pascal-triangle-print
  (lambda (n k)
    (if (equal? k 0) (list (nCk n k))
    (cons (nCk n k) (pascal-triangle-print n (- k 1))))))

;; This determines n choose k
(define nCk
  (lambda (n k)
    (/ (fact n) (* (fact k) (fact (- n k))))))

;; Problem 5
;; this is a procedure that returns the cartesian product of two input sets
(define product
  (lambda (s1 s2)
    (if (or (null? s1) (null? s2))
      '()
      (append (map (lambda (n) (list (car s1) n)) s2) (product (cdr s1) s2)))))
