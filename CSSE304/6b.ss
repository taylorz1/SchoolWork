;; Zachary Taylor Assignment 6b


;; Problem 7
;; This procedure returns a list in the order of which the pred is true.
(define filter-in
  (lambda (pred ls)
    (if (null? ls)
      '()
      (if (pred (car ls))
        (cons (car ls) (filter-in pred (cdr ls)))
        (filter-in pred (cdr ls))))))


;; Problem 8
;; This procedure returns a list in which the pred is false.
(define filter-out
  (lambda (pred ls)
    (if (null? ls)
      '()
      (if (pred (car ls))
        (filter-out pred (cdr ls))
        (cons (car ls) (filter-out pred (cdr ls)))))))


;; Problem 9
;; This procedure takes a list of symbols and returns a list of symbols sorted
;; as if they were strings.
(define sort-list-of-symbols
  (lambda (ls)
    (map string->symbol (sort string<? (map symbol->string ls)))))


;; Problem 10
;; This procedure reverses the cadr and car of each sublist in a list of lists.
(define invert
  (lambda (ls)
    (if (null? ls)
      '()
      (cons (list (cadar ls) (caar ls)) (invert (cdr ls))))))

;; Problem 11
;; This procedure is list list-index but its second argument is a vector not a list.
(define vector-index
  (lambda (pred vec)
    (vector-index-help pred vec 0)))

;; This helper function simply has a recursive counter it uses.
(define vector-index-help
  (lambda (pred vec n)
    (if (not (< n (vector-length vec)))
      #f
      (if (pred (vector-ref vec n))
        n
        (vector-index-help pred vec (add1 n))))))

;; Problem 12
;; This procedure returns the value in vector v that is associated with
;; the symbol s (or returns fail-value) if there is no such associated value.
(define ribassoc
  (lambda (s los v fail-value)
    (if (list-index (lambda(n) (equal? s n)) los)
      (vector-ref v (list-index (lambda(n) (equal? s n)) los))
      fail-value)))


;; List-index from previous work.
(define list-index
  (lambda (pred ls)
    (list-index-counter pred ls 0)))

;; Helper that has a counter.
(define list-index-counter
  (lambda (pred ls n)
    (if (null? ls) #f
    (if (pred (car ls)) n
      (list-index-counter pred (cdr ls) (+ n 1))))))
