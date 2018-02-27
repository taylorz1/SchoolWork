;; Zachary Taylor Assignment-08a

;; Problem 1
;; This is a bunch of procedures related to S-lists

;; slist-map proc
;; This procedure takes in an slist and a procedure and maps it to each element
;; of an slist.
(define slist-map
  (lambda (prec slist)
    (letrec ([procmap (lambda(prec slist)
      (cond
          [(null? slist) '()]

          [(symbol? (car slist)) (cons (prec (car slist)) (procmap prec (cdr slist)))]

          [else (cons (procmap prec (car slist)) (procmap prec (cdr slist)))]))])
      (procmap prec slist))))



;; This procedure reverses an slist
(define slist-reverse
  (lambda (slist)
    (letrec ([rev (lambda(slist)
      (cond
          [(null? slist) '()]

          [(symbol? (car slist)) (cons (car slist) (rev (cdr slist)))]

          [else (cons (rev (reverse (car slist))) (rev (cdr slist)))]))])
     (rev (reverse slist)))))

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

;; This procedure finds the symbols at a specified depth in an slist
(define slist-symbols-at-depth
  (lambda (slist d)
    (letrec ([sad (lambda(slist curr need)
        (cond
            [(null? slist) '()]

            [(symbol? (car slist))
              (cond
                [(equal? curr need) (cons (car slist) (sad (cdr slist) curr need))]

                [else (sad (cdr slist) curr need)])]

            [else
              (cond
                [(equal? curr need) (sad (cdr slist) curr need)]

                [else
                  (append (sad (car slist) (add1 curr) need)
                      (sad (cdr slist) curr need))])]))])
        (sad slist 1 d))))

;; Problem 2
;; Procedure takes a list ls and returns a list of lists, the elements of ls
;; in groups of two. If ls has an odd number is returns a sublist with 1.
(define group-by-two
  (lambda (ls)
    (letrec ([grouper (lambda(ls)
      (cond
        [(null? ls) '()]

        [(> (length ls) 1) (cons (list (car ls) (cadr ls)) (grouper (cddr ls)))]

        [else (list ls)]))])
    (grouper ls))))


;; Problem 3
;; This procedures returns an iterator for an slist.
(define make-slist-leaf-iterator
  (lambda(slist)
    (let loop ([superlist slist])
      (lambda (msg)
        (case msg
          [(next)
            (let find-next ([sl superlist] [rest '()])
              (cond
                [(null? sl) #f]
                [(symbol? (car sl))
                  (let ([next (car sl)])
                    (set! superlist (cons (cdr sl) rest))
                    next)]
                [else
                  (let find-next-helper
                    ([nexter (find-next (car sl) (cons (cdr sl) rest))])
                      (if nexter
                        nexter
                        (find-next (cdr sl) rest)))]))]
          [else (errorf 'iterator "illegal message")])))))
