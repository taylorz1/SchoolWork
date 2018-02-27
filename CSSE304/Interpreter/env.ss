; Environment definitions for CSSE 304 Scheme interpreter.  
; Based on EoPL sections 2.2 and  2.3

(define empty-env
  (lambda ()
    (empty-env-record)))

(define extend-env
  (lambda (syms vals env)
    (extended-env-record syms vals env)))
	
;; This is the no mutation approach
(define extend-env-recursively
	(lambda (proc-names idss bodiess old-env)
		(recursively-extended-env-record proc-names idss bodiess old-env)))

(define list-find-position
  (lambda (sym los)
    (list-index (lambda (xsym) (eqv? sym xsym)) los)))

(define list-index
  (lambda (pred ls)
    (cond
     ((null? ls) #f)
     ((pred (car ls)) 0)
     (else (let ((list-index-r (list-index pred (cdr ls))))
	     (if (number? list-index-r)
		 (+ 1 list-index-r)
		 #f))))))

(define make-list (lambda (id)
          (cond
            [(list? id) id]
            [(pair? id) (cons (car id) (make-list (cdr id)))]
            [else
              (cons id '())])))

(define apply-env
  (lambda (env sym succeed fail) ; succeed and fail are "callback procedures, 
    (cases environment env       ;  succeed is appluied if sym is found, otherwise 
      [empty-env-record ()       ;  fail is applied.
        (fail)]
      [extended-env-record (syms vals env)
        (let ([pos (list-find-position sym syms)])
          (if (number? pos)
              (succeed (list-ref vals pos))
              (apply-env env sym succeed fail)))]
	  ; Change made here
    [recursively-extended-env-record
      (procnames id bodies old-env)
      (let ([pos (list-find-position sym procnames)])
        (if (number? pos)
          (if ((list-of symbol?) (list-ref id pos))
              (closure  (list-ref id pos)
                        (list-ref bodies pos)
                        env)
              (improper-closure (list-ref id pos)
                                (list-ref bodies pos)
                                env))
          (apply-env old-env sym succeed fail)))])))

