;; Zachary Taylor Assignment-09


;; Problem 1
;; This procedure is snlist-recur


(define (snlist-recur base notproc listproc)
  (letrec ([traversal
    (lambda (sn-list)
      (cond
        [(null? sn-list) base]
        [(list? (car sn-list)) (listproc (traversal (car sn-list))
                                (traversal (cdr sn-list))) ]
        [else
          (notproc (car sn-list) (traversal (cdr sn-list)))]))])
    traversal))


;; Problem 1a
;; This sums an sn list's symbols
(define sn-list-sum
  (lambda (snlist)
    ((snlist-recur 0 + +) snlist)))

;; Problem 1b
;; THis applies a procedure across the an entire slist.
(define sn-list-map
  (lambda (proc snlist)
    (let ([mapper (lambda (proc)
          (snlist-recur '() (lambda (x y) (cons (proc x) y))
            (lambda (x y) (cons x y))))])
     ((mapper proc) snlist))))

;; Problem 1c
;; This counts the number of parens to replicate an snlist
(define sn-list-paren-count
  (lambda (snlist)
    ((snlist-recur 2 (lambda (x y) y) (lambda (x y) (+ x y))) snlist)))

;; Problem 1d
;; This reverses all the elements of an snlist.
(define sn-list-reverse
  (lambda (snlist)
    ((snlist-recur '() (lambda (x y) (append y (list x)))
      (lambda (x y) (append y (list x)))) snlist)))


;; Problem 1e
;; This counts how many times an element occurs in snlist.
(define sn-list-occur
  (lambda (ele snlist)
    ((snlist-recur 0 (lambda (x y)
                        (if (equal? ele x) (add1 y) y))
                      (lambda (x y) (+ x y))) snlist)))

;; Problem 1f
;; This finds the maximum nesting-level of parentheses in the printed
;; representation of snlist.
(define (sn-list-depth snlist)
  ((snlist-recur 1 (lambda (x y)
                       y)
                    (lambda (x y)
                      (max(add1 x) y))) snlist))


;; Problem 2
;; This is bt-recur
(define (bt-recur base sproc sbbproc)
    (letrec ([trav (lambda(bt)
      (cond
        [(null? bt) base]
        [(number? bt) (sproc bt)]
        [(symbol? (car bt)) (sbbproc (car bt) (trav (cadr bt)) (trav (caddr bt)))]))])
    trav))

;; Problem 2a
;; This is bt-sum, where it sums all the leaves.
(define (bt-sum bt)
  ((bt-recur #f (lambda (x) x) (lambda (x y z) (+ y z))) bt))

;; Problem 2b
;; This is an inorder iterator of a bintree
(define (bt-inorder bt)
  ((bt-recur '() (lambda (x) '()) (lambda (x y z) (append y (list x) z))) bt))


;; Problem 3
;; This procedures returns the procedure equivalent to c...r
(define (make-c...r str)
  (let ([ls (string->list str)])
    (letrec ([helper (lambda (ls)
      (cond
        [(null? ls) '()]
        [(equal? (car ls) #\a) (cons car (helper (cdr ls)))]
        [(equal? (car ls) #\d) (cons cdr (helper (cdr ls)))]))])
     (apply compose (helper ls)))))


(define compose
  (case-lambda
   [() (lambda (x) x)]
   [(first . rest)
    (let ([composed-rest (apply compose rest)])
      (lambda (x) (first (composed-rest x))))]))
