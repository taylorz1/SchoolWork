;; Zachary Taylor Assignment-08b

;; Problem 4
;; Group-by-n
;; This function creates a list grouped by n's
(define group-by-n
  (lambda (ls n)
      (letrec ([create-list
        (lambda (ls n)
          (cond
            [(equal? n 0) '()]
            [else
              (cons (car ls) (create-list (cdr ls) (sub1 n)))]))])
      (if (> (length ls) n)
        (cons (create-list ls n) (group-by-n (list-tail ls n) n))
        (if (null? ls) '()
        (list ls))))))
;; Problem 5
;; This procedure takes the same arguments at new old but only substitutes
;; for the leftmost occurance of old.
(define subst-leftmost
  (lambda (new old slist proceq)
    (letrec ([ls slist]
      [finder
        (lambda(new old ls proceq)
          (cond
            [(null? ls) (list '() #f)]
            [(symbol? (car ls))
              (cond
                [(proceq (car ls) old)
                  (list (cons new (cdr ls)) #t) ]
                [else
                  (let ([newvalue (finder new old (cdr ls) proceq) ])
                    (list (cons (car ls) (car newvalue)) (cadr newvalue))) ])]
            [else
              (let ([newvalue (finder new old (car ls) proceq)])
                (cond
                  [(cadr newvalue)
                    (list (cons (car newvalue) (cdr ls)) (cadr newvalue))]
                  [else
                    (let ([newvalue (finder new old (cdr ls) proceq) ])
                      (list (cons (car ls) (car newvalue)) (cadr newvalue))) ]))]))])
        (car (finder new old ls proceq)))))
