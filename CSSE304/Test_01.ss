;; Zachary Taylor Test01

;; Problem 1
;; This problem finds the max difference of numbers in a list
(define (max-diff lon)
  (let ([sorted (list-sort < lon)])
    (- (car (list-tail sorted (- (length sorted) 1))) (car sorted) )))

;; Problem 2
;; Returns if the number of tail elements are present.
(define (member-n-times? obj ls n)
  (letrec ([memberls (member obj ls)] [callme
          (lambda(ls n obj)
            (cond
              [(equal? n 0) #t]
              [(null? ls) #f]
              [(list? memberls) (member-n-times? obj (cdr memberls) (- n 1))]
              [else #f]))])
    (callme ls n obj)))


;; Problem 3
;; This problem takes a list of numbers and returns the one with the most freq
(define (most-frequent lon)
  (letrec ([sorted (list-sort < lon)] [callme
            (lambda (ele n ls maxele nmax)
              (cond
                [(null? ls) maxele]
                [(equal? ele (car ls))
                  (cond
                    [(> (add1 n) nmax) (callme ele (add1 n) (cdr ls) ele (add1 n))]
                    [else
                      (callme ele (add1 n) (cdr ls) maxele nmax)])]
                [else
                  (callme (car ls) 1 (cdr ls) maxele nmax)]))])
      (if (null? sorted) #f
      (callme (car sorted) 1 (cdr sorted) (car sorted) 1))))


;; Problem 4
;; This problem takes two s-lists and a symbol comparator. Returns #t if they
;; are equal when applied to the same trees.
;; This makes the argument that if a tree contains the same count of parens
;; and the same depth of another tree, they must have the same structure.
;; I couldn't think of a counter example off the top of my head.
(define (slist-same? s1 s2 sym-comparator)
  (let ([sp1 (slist-paren-count s1)] [sp2 (slist-paren-count s2)]
        [d1 (slist-depth s1)] [d2 (slist-depth s2)])
    (if (and (equal? sp1 sp2) (equal? d1 d2))
        (andmap sym-comparator (flatten s1) (flatten s2))
        #f)))
;; Borrowed from livecoding
(define (flatten slist)
  (let flatten ([slist slist])
    (cond [(null? slist) '()]
          [(symbol? (car slist))
              (cons (car slist) (flatten (cdr slist)))]
          [else (append (flatten (car slist)) (flatten (cdr slist)))])))
;; Borrowed from my 8a
;; This procedure counts the number of parens required to produce an slist
(define slist-paren-count
  (lambda (slist)
    (letrec ([counter (lambda(slist)
      (cond
            [(null? slist) 2]

            [(symbol? (car slist)) (counter (cdr slist))]

            [else (+ (counter (car slist)) (counter (cdr slist)))]))])

    (counter slist))))
;; This procedure finds the maximum nesting level of parens in the printed slist
(define slist-depth
  (lambda (slist)
    (letrec ([depth (lambda(slist maximum)
      (cond
          [(null? slist) maximum]

          [(symbol? (car slist)) (depth (cdr slist) maximum)]

          [else (max (depth (car slist) (add1 maximum)) (depth (cdr slist)
              maximum))]))])
      (depth slist 1))))

;; Problem 5
;; This procedure creates a mock-list class called lyst that is mutable.
(define make-lyst
  (lambda ()
    (let ([lst '()])
      (lambda (msg . args)
        (case msg
          [(first)
            (if (null? lst) '()
            (car lst)) ]
          [(last)
            (if (null? lst) '()
            (car (list-tail lst (sub1 (length lst))))) ]
          [(add-first)
            (set! lst (cons (car args) lst)) ]
          [(add-last)
            (set! lst (append lst args)) ]
          [(len)
            (length lst)]
          [(get)
            (if (or (< (car args) 0) (> (car args) (sub1 (length lst)))) #f
            (list-ref lst (car args))) ]
          [else (errorf 'lyst "illegal message")])))))
