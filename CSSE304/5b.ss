;; Zachary Taylor Assignment-05b

;; This is a helper method from 3.ss
;; Determines if something contains the given element.
(define contains?
              (lambda (element list)
                (if (equal? list '()) #f
                (if (equal? element (car list)) #t
                (contains? element (cdr list))))))


;; Problem 6
;; This procedure takes in a nonnegative integer and returns
;; the maximum number of edges than an undirected graph of n verticies
;; could have.
(define max-edges
  (lambda (n)
    (/ (* n (- n 1)) 2)))


;; Problem 7
;; This predicate takes a graph and determines whether it is complete.
(define complete?
  (lambda (G)
    (if (null? G)
      #t
      (complete-helper? (cdr G) (append (list (caar G)) (cadar G))))))

;; This helper method checks if the first solo element is contained in the list
;; of known elements.
(define complete-helper?
  (lambda (G ls)
    (if (null? G) #t
      (and (contains? (caar G) ls) (complete-helper? (cdr G)
        (append (list (caar G)) (cadar G)))))))

;; Problem 8
;; This problem takes a list of symbols and returns a complete graph.
(define complete
  (lambda (ls)
    (if (null? ls)
    '()
    (cons (list (car ls) (cdr ls))
      (complete-helper (cdr ls) ls)))))

;; A method to build the all the sublists.
(define complete-helper
  (lambda (ls lsO)
    (if (null? ls) '()
    (cons (list (car ls) (delete (car ls) lsO)) (complete-helper (cdr ls) lsO)))))

;; A helper method to delete an item from a list so we can make the right sublist.
(define delete
  (lambda (ele ls)
    (if (null? ls)
      ls
      (if (equal? ele (car ls))
        (cdr ls)
        (cons (car ls) (delete ele (cdr ls)))))))

;; Problem 9
;; This procedure takes two numbers to replace in a list of value.
(define replace
  (lambda (old new ls)
    (replace-help old new ls '())))


;; This helper method builds a list.
(define replace-help
  (lambda (old new ls l2)
    (if (null? ls)
      l2
      (if (equal? (car ls) old)
        (replace-help old new (cdr ls) (append l2 (list new)))
        (replace-help old new (cdr ls) (append l2 (list (car ls))))))))

;; Problem 10
;; This method removes the first instance of an element from a list.
(define remove-first
  (lambda (element ls)
    (delete element ls)))

;; Problem 11
;; This method removes the last instance of the element.
(define remove-last
  (lambda (element ls)
    (if (null? ls)
      '()
      (if (equal? (car ls) element)
        (if (contains? element (cdr ls))
            (cons (car ls) (remove-last element (cdr ls)))
            (remove-first element ls))
        (cons (car ls) (remove-last element (cdr ls)))))))
