;; Zachary Taylor Assignment-04

;; Problem 1
;; This problem determines if the given object is a multi-set
;; This is a helper method from 3.ss
(define contains?
              (lambda (element list)
                (if (equal? list '()) #f
                (if (equal? element (car list)) #t
                (contains? element (cdr list))))))

;; Starts the whole thing rolling
(define multi-set?
  (lambda (obj)
    (if (null? obj)
      #t
      (if (list? obj)
        (type-helper? obj)
        #f))))


;; This is a helper method that determines that each of the input sets are of the
;; correct type.
(define type-helper?
  (lambda (set)
    (if (null? set)
      #t
      (if (list? (car set))
        (if (contains? (car set) (cdr set))
          #f
          (if (list-check? set '())
            (if (not (number? (car (car set))))
              (if (and (number? (cadr (car set))) (> (cadr (car set)) 0))
                (if (null? (cddr (car set)))
                  (and #t (type-helper? (cdr set)))
                  #f)
                #f)
              #f)
            #f)) #f))))

;; Checks if the first element of each list is present in the first of any other
;; list
(define list-check?
  (lambda (set ls)
    (if (null? set)
      #t
      (if (list? (car set))
      (if (contains? (car (car set)) ls)
        #f
        (list-check? (cdr set) (append (list (car (car set))) ls))) #f))))


;; Problem 2
;; Determines the total number of elements in the multi-set ms.
(define ms-size
  (lambda (ms)
    (apply + (map cadr ms))))


;; Problem 3
;; Returns the value in row , col of an input matrix.
(define matrix-ref
  (lambda (m row col)
    (list-ref (list-ref m row) col)))

;; Problem 4
;; Determines if an input obj is a valid matrix.
(define matrix?
  (lambda (m)
    (if (or (not (list? m)) (null? m))
      #f
      (if (or (not (list? (car m))) (null? (car m)))
        #f
        (length? (cdr m) (length (car m)))))))
;; This helper function determines if each of the sublists of a given list
;; are the same length.
(define length?
  (lambda (m l)
    (if (null? m)
      #t
      (and (equal? (length (car m)) l) (length? (cdr m) l)))))

;; Problem 5
;; Returns the transpose of the input matrix.
(define matrix-transpose
  (lambda (m)
    (apply map list m)))


;; Problem 6
;; Returns the last element of the list ls.
(define last
  (lambda (ls)
    (list-ref ls (- (length ls) 1))))


;; Problem 7
;; Returns a list that excludes the last element of the input list.
(define all-but-last
  (lambda (L)
    (if (equal? (length L) 1)
      '()
       (append (list (car L)) (all-but-last (cdr L))))))
