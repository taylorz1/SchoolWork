;; Zachary Taylor Assignment-02
;; Problem 1a
;; This problem writes a procedure that returns the factorial of the input
(define fact
  (lambda (n)
    (if (zero? n)
        1
        (* n (fact (- n 1))))))
;; Problem 1b
;; This procedure allows you to execute the choose notation
;; Hereby: n!/(n-k)!k!
(define choose
    (lambda (n k)
      (* (/ (fact n) (fact (- n k)) (fact k)))))

;; Problem 2
;; This procedure returns the list of integers starting at integer m to n
;; noninclusive.
(define range
  (lambda (m n)
      (if (< m n) (cons m (range (+ m 1) n)) (list))))

;; Problem 3
;; This helper determines if an element is present twice in the list
(define helper3?
  (lambda (element list)
    (if (equal? list '()) #f
    (if (equal? element (car list)) #t
    (helper3? element (cdr list))))))
;; This procedure determines if a list is a set.
(define set?
  (lambda (list)
    (if (equal? list '()) #t (if (helper3? (car list) (cdr list)) #f
    (set? (cdr list))))))


;; Problem 4
;; This method takes a list of numbers and returns the sum of their squares
(define sum-of-squares
  (lambda (lon)
    (if (equal? lon '()) 0 (helper4 0 lon))))

;;This is the helper method for problem 4, it takes a total and a list.
(define helper4
  (lambda (total lon)
    (if (equal? lon '()) 0
    (+ (+ total (* (car lon) (car lon))) (helper4 total (cdr lon))))))

;; Problem 5
;; This procedure will determine if vector between two points
(define make-vec-from-points
  (lambda (p1 p2)
    (list (- (car p2) (car p1)) (- (cadr p2) (cadr p1))
    (- (caddr p2) (caddr p1)))))

;; Problem 6
;; This procedure computes the dot product of two vectors.
(define dot-product
  (lambda (v1 v2)
    (+ (+ (* (car v1) (car v2)) (* (cadr v1) (cadr v2)))
    (* (caddr v1) (caddr v2)))))

;; Problem 7
;; This procedure finds the madnitude of the vector V
(define vec-length
  (lambda (v)
    (sqrt (+ (+ (expt (car v) 2) (expt (cadr v) 2)) (expt (caddr v) 2)))))

;; Problem 8
;; This procedure finds the distance between two points
(define distance
  (lambda (p1 p2)
    (vec-length (make-vec-from-points p1 p2))))

;; Problem 9
;; This procedure computes the cross product of two vectors
(define cross-product
  (lambda (v1 v2)
    (list (- (* (cadr v1) (caddr v2)) (* (caddr v1) (cadr v2)))
    (- (* (caddr v1) (car v2)) (* (car v1) (caddr v2)))
    (- (* (car v1) (cadr v2)) (* (cadr v1) (car v2))))))

;; Problem 10
;; This procedure checks if two vectors are parallel
(define parallel?
  (lambda (v1 v2)
    (equal? (vec-length (cross-product v1 v2)) 0)))

;; Problem 11
;; This procedure checks if point p1, p2, and p3 all reside on the same line
(define collinear?
  (lambda (p1 p2 p3)
    (helper11 (make-vec-from-points p1 p2) (make-vec-from-points p1 p3)
    (make-vec-from-points p2 p3))))

;;helper for problem 11
;;It simply does stuff with vectors instead of points for neatness.
(define helper11
  (lambda (v1 v2 v3)
    (and (parallel? v1 v2) (parallel? v2 v3) (parallel? v1 v3))))
