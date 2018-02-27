;; Zachary Taylor Assignment-03

;; methods from Assignment-02. Specification is in 2.ss
(define make-vec-from-points
  (lambda (p1 p2)
    (list (- (car p2) (car p1)) (- (cadr p2) (cadr p1))
    (- (caddr p2) (caddr p1)))))

(define vec-length
      (lambda (v)
        (sqrt (+ (+ (expt (car v) 2) (expt (cadr v) 2)) (expt (caddr v) 2)))))

(define distance
          (lambda (p1 p2)
            (vec-length (make-vec-from-points p1 p2))))

;; This function is actually called helper3 in 2.ss
(define contains?
              (lambda (element list)
                (if (equal? list '()) #f
                (if (equal? element (car list)) #t
                (contains? element (cdr list))))))

;; Problem 1
;; This procedure returns the nearest point from a list of points closest to P

(define nearest-point
  (lambda (p list-of-points)
    (helper1 p (car list-of-points) (distance p (car list-of-points))
    (cdr list-of-points))))

;; This helper method for problem 1 recursively determines the minimum distance
;; and keeps track of the following point.
(define helper1
  (lambda (p near dist list-of-points)
   (if (null? list-of-points) near
    (if (< (distance p (car list-of-points)) dist)
    (helper1 p (car list-of-points) (distance p (car list-of-points))
    (cdr list-of-points)) (helper1 p near dist (cdr list-of-points))))))


;; Problem 2
;; Creates a proper union  between two sets
(define union
  (lambda (l1 l2)
   (if (null? l2) l1
    (if (contains? (car l2) l1) (union l1 (cdr l2))
    (union (append l1 (list (car l2))) (cdr l2))))))


;; Problem 3
;; Creates a proper intersection between two sets.
(define intersection
    (lambda (s1 s2)
      (if (null? s1) (list)
        (if (contains? (car s1) s2) (append (list (car s1)) (intersection (cdr s1) s2))
        (intersection (cdr s1) s2)))))


;; Problem 4
;; Determines if X is a subset of Y
(define subset?
  (lambda (s1 s2)
    (if (null? s1) #t
      (if (contains? (car s1) s2) (subset? (cdr s1) s2) #f))))

;; Problem 5
;; This helper method determines if a list is a set.
(define set?
  (lambda (list)
    (if (list? list)
    (if (equal? list '()) #t (if (contains? (car list) (cdr list)) #f
    (set? (cdr list)))) #f)))

;; Determines if exists a set of ordered pairs relation in a given object.
(define relation?
  (lambda (obj)
    (if (set? obj)
    (if (null? obj) #t
    (if (list? (car obj))
    (if (equal? (length (car obj)) 2) (and #t (relation? (cdr obj))) #f) #f)) #f)))

;; Problem 6
;; This procedure determines the domain of the input set.
(define domain
  (lambda (r)
    (helper6 r '())))


;; This helper appends the elements of the thing to a list. Then returns
;; that list.
(define helper6
  (lambda (r in)
    (if (null? r) in (union (list (car (car r))) (helper6 (cdr r) in)))))


;; Problem 7
;; This procedure determines if a relation is reflexive
(define reflexive?
  (lambda (r)
  (reflexive-help? r r)))

;; This helper method takes in the modified list and the original list.
(define reflexive-help?
  (lambda (r or)
  (if (null? r) #t
  (if (relation? r)
    (if (and (contains? (list (car (car r)) (car (car r))) or)
            (contains? (list (cadr (car r)) (cadr (car r))) or))
      (reflexive-help? (cdr r) or)
      #f)
    #f))))

;; Problem 8
;; This counts how many times you must step along the hail-stone conjecture
;; to reach zero
(define hailstone-step-count
  (lambda (n)
    (hailstone-help n 0)))

;; This helper method does the hailstone count while also actually counting.
(define hailstone-help
  (lambda (n count)
    (if (equal? n 1)
    count
    (if (equal? (modulo n 2) 0) (hailstone-help (/ n 2) (+ count 1))
    (hailstone-help (+ (* 3 n) 1) (+ count 1))))))
