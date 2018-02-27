;;  Zachary Taylor Assignment10
(define union
  (lambda (l1 l2)
   (if (null? l2) l1
    (if (member (car l2) l1) (union l1 (cdr l2))
    (union (append l1 (list (car l2))) (cdr l2))))))

;; Problem 1
;; These procedures return a list of all bound vars or all free vars.
;; Lambda exp: identifier || lambda (identifier) LcExp) || LcEXP LcEXP)
(define (free-vars e)
  (let ([search (lambda (x)
                              (if (member x (cadr e))
                                '()
                                (list x)))])
    (cond
      [(symbol? e) (list e)]
      [(equal? (length e) 3) (apply append (map search (free-vars (caddr e))))]
      [else
        (union (free-vars (car e)) (free-vars (cadr e)))])))


(define (bound-vars e)
  (let ([search (lambda (x)
                    (if (member x (cadr e))
                      (list x)
                      '()))])
    (cond
      [(symbol? e) '()]
      [(equal? (length e) 3) (union (apply append (map search (free-vars (caddr e))))
                              (bound-vars (caddr e)))]
      [else
        (union (bound-vars (car e)) (bound-vars (cadr e)))])))

;; Problem 2
;; This expands occurs fre ena doccurs-bound to incoprorate language features.
(define occurs-free?
  (lambda (var exp)
    (cond
      [(symbol? exp) (eqv? var exp)]
      [(eqv? (car exp) 'lambda)
       (and (not (member var (cadr exp)))
            (occurs-free? var (caddr exp)))]
      [(eqv? (car exp) 'let)
        (cond
          [(member var (map car (cadr exp))) (and (ormap (lambda(x)
                                        (occurs-free? var x)) (map cadr (cadr exp)))
                                        (occurs-free? var (caddr exp)))]
          [else
            (ormap (lambda(x) (occurs-free? var x)) (map cadr (cadr exp)))])]
      [(eqv? (car exp) 'let*)
          (cond
            [(member var (map cadr (cadr exp))) (not (member var (map car (cadr exp))))]
            [else (and (not (member var (map car (cadr exp))))
                          (occurs-free? var (caddr exp)))])]
      [(eqv? (car exp) 'set!) (and (not (eqv? var (cadr exp))) (eqv? var (caddr exp)))]
      [(eqv? (car exp) 'if) (ormap (lambda(x) (occurs-free? var x)) (cdr exp))]
      [else (ormap (lambda (x) (occurs-free? var x)) exp)])))


(define occurs-bound?
  (lambda (var exp)
    (cond
      [(symbol? exp) #f]
      [(eqv? (car exp) 'lambda) (or (occurs-bound? var (caddr exp))
                  (and (member var (cadr exp)) (occurs-free? var (caddr exp))))]
      [(eqv? (car exp) 'let)
        (if (member var (map car (cadr exp))) (occurs-free? var (caddr exp))
          (occurs-bound? var (caddr exp)))]
      [(eqv? (car exp) 'let*)
        (if (and (member var (map car (cadr exp))) (member var (map cadr (cadr exp))))
          #t
          #f)]
      [(eqv? (car exp) 'set!)
        (and (equal? var (car exp)) (not (equal? var 'set!)))]
      [(eqv? (car exp) 'if)
        (occurs-bound? var (cdr exp))]
      [else (ormap (lambda(x) (occurs-bound? var x)) exp)])))

(define lexical-address
  (lambda (exp)
    (lexical-helper exp '() 0)))

(define lexical-helper
  (lambda (exp sym depth)
    (letrec ([helper (lambda(exp sym depth)
      (cond
        [(symbol? exp) (address exp sym depth)]
        [(eqv? (car exp) 'lambda) (append (list 'lambda (cadr exp))
                            (helper (cddr exp)
                              (cons (argument-helper (cadr exp)
                                (add1 depth) 0) sym) (add1 depth))) ]
        [(eqv? (car exp) 'let) (append (list 'let (map
            (lambda(x)
                (list (car x) (helper (cadr x) sym depth)))
           (cadr exp)))
          (helper (cddr exp)
            (cons (argument-helper (map car (cadr exp)) (add1 depth) 0) sym) (add1 depth)))]
        [(eqv? (car exp) 'if) (cons 'if (helper (cdr exp) sym depth))]
        [(eqv? (car exp) 'set!) (append (list 'set! (cadr exp)) (helper (cddr exp) sym depth))]
        [(list? exp) (map (lambda(x) (helper x sym depth)) exp)]))])
    (helper exp sym depth))))

(define argument-helper
  (lambda (ls depth index)
    (cond
      [(null? ls) '()]
      [else
        (cons (list (car ls) depth index)
          (argument-helper (cdr ls) depth (add1 index)))])))

(define address
  (lambda (sym ls depth)
    (let ([ret (address-helper sym ls)])
      (if ret (list ': (- depth (cadr ret)) (caddr ret))
                (list ': 'free sym)))))


(define address-helper
  (lambda (sym ls)
   (cond
    [(null? ls) #f]
    [(list? (car ls)) (or (address-helper sym (car ls)) (address-helper sym (cdr ls)))]
    [else
      (if (equal? sym (car ls)) ls (address-helper sym (cdr ls)))])))

(define un-lexical-address
  (lambda (exp)
    (letrec ([helper (lambda(exp stack depth)
      (cond
        [(null? exp) '()]
        [(eqv? (car exp) ':) (lookup exp stack depth)]
        [(eqv? (car exp) 'lambda) (cons 'lambda (cons (cadr exp) (helper (cddr exp)
              (cons (argument-helper (cadr exp) (add1 depth) 0) stack) (add1 depth))))]
        [(eqv? (car exp) 'let) (cons 'let (cons (map
            (lambda(x)
                (list (car x) (helper (cadr x) stack depth)))
           (cadr exp))
          (helper (cddr exp)
            (cons (argument-helper (map car (cadr exp)) (add1 depth) 0) stack) (add1 depth))))]
        [(eqv? (car exp) 'set!) (cons 'set! (cons (cadr exp) (helper (cddr exp) stack depth))) ]
        [(eqv? (car exp) 'if) (cons 'if (helper (cdr exp) stack depth))]
        [(list? (car exp)) (cons (helper (car exp) stack depth) (helper (cdr exp) stack depth))]
        [else
          (cons (car exp) (helper (cdr exp) stack depth))]))])
    (helper exp '() 0))))

; i.e. is of type : n m
(define lookup
  (lambda (exp stack depth)
    (cond
      [(equal? (cadr exp) 'free) (caddr exp)]
      [(null? stack) #f]
      [(list? (car stack)) (or (lookup exp (car stack) depth) (lookup exp (cdr stack) depth))]
      [(equal? (- depth (cadr stack)) (cadr exp))
        (cond
          [(equal? (caddr exp) (caddr stack)) (car stack)]
          [else
            (lookup exp (cdr stack) depth)])]
      [else #f])))
