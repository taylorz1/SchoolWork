;; Zachary Taylor Assignment-01
;; Problem 1
;; This procedure takes a value in Fahrenheit and returns it in Celsius.
(define Fahrenheit->Celsius
 (lambda (temperature)
      (* (- temperature 32) 5/9)))


;; Problem 2
;; This procedure takes in an interval and a number and returns a boolean
;; if that number is within the interval.
(define interval-contains? (lambda (interval number)
    (and (<= number (cadr interval)) (>= number (car interval)))))

;; Problem 3
;; This procedure takes in two intervals and returns if the intervals
;; have non-empty intersection. Returns #t if the intersections contains a
;; single number
(define interval-intersects?
      (lambda (i1 i2)
        (or (interval-contains? i1 (cadr i2))
          (interval-contains? i1 (car i2))
          (interval-contains? i2 (cadr i1))
          (interval-contains? i2 (car i1)))))
;; Problem 4
;; This procedure takes two intervals and returns their union
(define interval-union
    (lambda (i1 i2)
      (if (interval-intersects? i1 i2) (list (list (min (car i1) (car i2))
          (max (cadr i1) (cadr i2)))) (list i1 i2))))
;; Problem 5
;; This procedure returns if a number is divisible by 7.
(define divisible-by-7?
    (lambda (num)
      (= (modulo num 7) 0)))

;; Problem 6
;; This procedure determines if the decimal representation of num ends with 7
(define ends-with-7?
  (lambda (num)
    (= (modulo num 10) 7)))

;; Problem 7
;; 1st
;; This procedure gets the first element of a list
(define 1st
  (lambda (list)
    (car list)))

;; 2nd
;; This procedure gets the second element of a list
(define 2nd
  (lambda (list)
    (cadr list)))

;; 3rd
;; This procedure gets the third element of a list
(define 3rd
  (lambda (list)
      (caddr list)))
