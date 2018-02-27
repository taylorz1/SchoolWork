;; Zachary Taylor Assignment-06a

;; Problem 1
;; This procedure takes in a procedure of two arguments and returns a curried
;; version of the procedure that takes the first argument and returns a procedure
;; that takes the second argument.

(define curry2
  (lambda (proc)
    (lambda (n)
      (lambda (m)
        (proc n m)))))


;; Problem 2
;; This procedure generates a curried version of the function compose.
;; Which is f(g(x)). This would be useful if you wanted to for instance apply
;; a function f on a whole list of functions g(x).
(define curried-compose
  (lambda (f)
    (lambda (g)
      (lambda (x)
        (f (g x))))))


;; Problem 3
;; This defines a version of compose that takes as arguments either
;; two or three procedures (of one argument) and composes them. Composition
;; is specified by (compose f g h) -> (compose (f (compose g h)))
(define compose
  (lambda list-of-functions
    (if (null? (cdr list-of-functions))
      (lambda (x) ((car list-of-functions) x))
      (lambda (x)
        ((car list-of-functions) ((apply compose (cdr list-of-functions)) x))))))

;; Problem 4
;; This procedure is a curried version of make-list.

(define make-list-c
  (lambda (n)
    (if (zero? n)
      (lambda (n)
      '())
      (lambda (string)
          (cons string ((make-list-c (sub1 n)) string))))))

;; Problem 5
;; This procedure takes a let expression and returns the equivalent expression
(define let->application
  (lambda (ls)
    (cons (lambda-list ls) (map cadr (cadr ls)))))


;; This is a helper function creates the first element of the list used to
;; convert let functions. It returns a list.
(define lambda-list
  (lambda (ls)
    (list 'lambda (map car (cadr ls)) (caddr ls))))



;; Problem 6
;; This procedure returns the equivalent nested let expression. This replaces
;; only the top-level let* by the equivalent nested let.

(define let*->let
  (lambda (ls)
    (let-help (cadr ls) (caddr ls))))

(define let-help
  (lambda (ls element)
    (if (null? ls)
      element
      (append (list 'let (list (car ls))) (list (let-help (cdr ls) element))))))
