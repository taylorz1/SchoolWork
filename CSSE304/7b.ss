;; Zachary Taylor Assignment-07b

;; Problem 6
;; This procedure returns a list which is the results of applying each function
;; from input to the corresponding value of arg-list.
(define map-by-position
  (lambda (fn-list arg-list)
    (map (lambda(func arg) (func arg)) fn-list arg-list)))

;; Problem 7
;; This procedure is a collection of various bintree procedures.
;; Form (n 1 2) or (n (n 1 2) 2)

;; bt-leaf-sum
;; This procedure sums all the leaves of a tree.
(define bt-leaf-sum
  (lambda (T)
    (if (integer? T) T
      (+ (bt-leaf-sum (cadr T)) (bt-leaf-sum (caddr T))))))


;; bt-inorder-list
;; This procedure creates a list of symbols from the interior nodes of T
(define bt-inorder-list
  (lambda (T)
    (let helperfunc ([T T] [ls '()])
      (if (number? T) ls
        (helperfunc (cadr T) (cons (car T) (helperfunc (caddr T) ls)))))))


;; bt-max
;; returns the largest integer of the tree
(define bt-max
  (lambda (T)
    (if (number? T) T
      (max (bt-max (cadr T)) (bt-max (caddr T))))))

;; bt-max-interior T
;; This function takes in a binary tree and returns the symbol associated
;; with the internal node whose subtree has a maximal leaf sum.
;; returns symbol maxsum treesum
(define (bt-max-interior T)
  (letrec ([max-interiorhelper (lambda(T)
       (cond
        [(and (number? (cadr T)) (number? (caddr T))) ; Both
          (list (car T) (+ (cadr T) (caddr T)) (+ (cadr T) (caddr T)))]

        [(and (list? (cadr T)) (number? (caddr T))) ; Right only
          (let* ([ls (max-interiorhelper (cadr T))] [newsum (+ (caddr ls) (caddr T))])
              (if (>= (cadr ls) newsum)
                (list (car ls) (cadr ls) newsum)
                (list (car T) newsum newsum)))]

        [(and (number? (cadr T)) (list? (caddr T))) ; Left only
          (let* ([ls (max-interiorhelper (caddr T))] [newsum (+ (cadr T) (caddr ls))])
            (if (> (cadr ls) newsum)
              (list (car ls) (cadr ls) newsum)
              (list (car T) newsum newsum)))]

        [else
          (let* ([left (max-interiorhelper (cadr T))] [right (max-interiorhelper (caddr T))]
            [treesum (+ (caddr left) (caddr right))])
              (if (>= (cadr left) (cadr right))
                (if (>= (cadr left) treesum)
                  (list (car left) (cadr left) treesum)
                  (list (car T) treesum treesum))
                (if (>= (cadr right) treesum)
                  (list (car right) (cadr right) treesum)
                  (list (car T) treesum treesum))))]))])
        (car (max-interiorhelper T))))
