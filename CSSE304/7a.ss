;; Zachary Taylor Assignment-07a


;; Determines if something contains the given element.
(define contains?
    (lambda (element list)
      (if (equal? list '()) #f
      (if (equal? element (car list)) #t
      (contains? element (cdr list))))))

;; Problem 1
;; This procedure returns a new vector with the elements of lst attached to the
;; end of the vector. This is done without using vector->list, list->vector or append.
(define vector-append-list
  (lambda (vec ls)
    (let ([new-vector (make-vector (+ (vector-length vec) (length ls)))])
      (copy-from-vector new-vector vec 0)
      (copy-from-list new-vector ls (vector-length vec))
      new-vector)))

;; this helper functions copies the elements from the vector to a new vector.
(define copy-from-vector
  (lambda (new-vector vec n)
    (if (equal? (vector-length vec) 0) new-vector
    (let ([new-vector new-vector] [vec vec] [n n])
      (vector-set! new-vector n (vector-ref vec n))
      (if (< (add1 n) (vector-length vec))
      (copy-from-vector new-vector vec (add1 n))
      new-vector)))))

;; This helper function copies the elements from the list to the new vector.
(define copy-from-list
  (lambda (new-vector ls n)
    (if (null? ls) new-vector
    (let ([new-vector new-vector] [ls ls] [n n])
      (vector-set! new-vector n (car ls))
        (copy-from-list new-vector (cdr ls) (add1 n))
        new-vector))))

;; Problem 2
;; This procedure is quicksort on a list of elements, with no sorting in place
;; Using only lists.
(define (qsort pred ls)
    (if (null? ls) '()
      (let ([pred pred] [ls ls])
;; Should I use let rec here?
        (let ([l2 (qpartition pred (cdr ls) (car ls))] [pivot (car ls)])
          (let ([new (car l2)] [index (cadr l2)])
            (let ([upper (cdr (list-tail new index))] [lower (reverse (cdr (list-tail (reverse new) (- (sub1 (length new)) index))))])
                  (append (qsort pred lower) (list pivot) (qsort pred upper))))))))

;; Partition function that returns a concatenated list of type lesser:pivot:greater
(define (qpartition pred ls pivot)
  (let ([pred pred] [ls ls] [pivot pivot])
    (cond [(null? ls) (list (list pivot) 0)]
      [(pred pivot (car ls))
        (let ([part (qpartition pred (cdr ls) pivot)])
          (list (append (car part) (list (car ls))) (cadr part)))]
      [else
        (let ([part (qpartition pred (cdr ls) pivot)])
          (list (append (list (car ls)) (car part)) (add1 (cadr part))))])))


;; Problem 3
;; This procedure checks if each vertex of a graph can be reached using
;; any sequence of edges.
(define (connected? g)
  (if (null? g) #t
  (let ([g g] [visited (list (caar g))] [neighbors-to-visit (cadar g)])
    (connected-help? g visited neighbors-to-visit))))

;; This function should compares the final result of the helper function.
;; Node this function always starts comparing the graph from the first element
;; of the graph.
(define (connected-help? g visited neighbors-to-visit)
      (let ([to-visit (visit-helper neighbors-to-visit visited g)])
        (list-compare (construct-vert g) (visitme to-visit visited g))))

;; This function visits nodes recursively.
(define (visitme to-visit visited g)
  (let ([visited visited] [to-visit to-visit] [g g]
      [toneighbor (visit-helper (node-fetch-list to-visit g) visited g)]
      [nvisited (if (equal? (length to-visit) 1)
                    (if (contains? (car to-visit) visited)
                      to-visit
                      (cons (car to-visit) visited))
                    (append visited (visit-helper (node-fetch-list to-visit g) visited g)))])
    (if (null? toneighbor)
        nvisited
        (visitme toneighbor nvisited g))))

;; Returns a list of unvisited neighbors.
(define visit-helper
  (lambda (neighbor-to-visit visited g)
    (if (null? neighbor-to-visit)
        neighbor-to-visit
        (if (contains? (car neighbor-to-visit) visited)
              (visit-helper (cdr neighbor-to-visit) visited g)
              (cons (car neighbor-to-visit)
                (visit-helper (cdr neighbor-to-visit) visited g))))))

;; node-fetch with a list
(define node-fetch-list
  (lambda (ls g)
    (if (null? ls)
      '()
      (append (node-fetch (car ls) g) (node-fetch-list (cdr ls) g)))))

;; Need a helper method to fetch the neighbors of a given node so you can call on it.
;; Fetches the neighbors of a node.
(define node-fetch
  (lambda (node g)
    (if (equal? node (caar g))
      (cadar g)
      (node-fetch node (cdr g)))))



;; This compares two unordered lists of the same length to
;; see if they have the same elements.
(define list-compare
  (lambda (ls l2)
    (if (null? ls) #t
      (if (contains? (car ls) l2)
        (and #t (list-compare (cdr ls) l2))
        #f))))

;; Just constructs a list containing all the vert of a graph
(define construct-vert
  (lambda (ls)
    (if (null? ls) '()
      (cons (caar ls) (construct-vert (cdr ls))))))


;; Problem 4
;; this problem reverses a list in O(n) time.
(define reverse-it
  (lambda (lst)
    (reverse-it-helper lst '())))

(define reverse-it-helper
  (lambda (lst toadd)
    (if (null? lst)
      toadd
      (reverse-it-helper (cdr lst) (cons (car lst) toadd)))))


;; Problem 5
;; This problem has several functions that create a binary tree.
(define empty-BST
  (lambda ()
    '()))

(define empty-BST?
  (lambda (BST)
    (null? BST)))

;;(Represented as (1 (left tree) (right tree))) if 1 is the root.
(define BST-insert
  (lambda (num bst)
    (cond
      ;;The root case.
      [(null? bst)
          (list num '() '())]
      [(> num (car bst))
          (list (BST-element bst) (BST-left bst) (BST-insert num (caddr bst)))]
      [(< num (car bst))
          (list (BST-element bst) (BST-insert num (cadr bst)) (BST-right bst))]
      [else
          bst])))

(define BST-inorder
  (lambda (BST)
    (if (null? BST) '()
      (append (BST-inorder (cadr BST)) (list (car BST))
        (BST-inorder (caddr BST))))))

(define BST?
  (lambda (BST)
    (if (null? BST) #t
      (if (and (list? BST) (equal? (length BST) 3) (number? (car BST)) (list? (cadr BST)) (list? (caddr BST)))
        (let ([bst BST] [left (BST-left BST)] [root (BST-element BST)]
              [right (BST-right BST)])
              (and (or (null? left) (> root (BST-element left)))
              (or (null? right) (< root (BST-element right))) (BST? left) (BST? right)
              (equal? (BST-inorder bst) (sort < (BST-inorder bst)))))
        #f))))

(define BST-insert-nodes
  (lambda (bst nums)
    (if (null? nums) bst
      (BST-insert-nodes (BST-insert (car nums) bst) (cdr nums)))))

(define BST-element
  (lambda (BST)
    (car BST)))

(define BST-left
  (lambda (BST)
    (cadr BST)))

(define BST-right
  (lambda (BST)
    (caddr BST)))

(define BST-contains?
  (lambda (bst num)
    (if (null? bst) #f
    (let ([root (BST-element bst)])
      (cond [(equal? num root) #t]
            [(< num root) (BST-contains? (BST-left bst) num)]
            [(> num root) (BST-contains? (BST-right bst) num)])))))
