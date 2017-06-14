6. (defun sqr (side)
      (list (* 4 side) (* side side)))

7. (defun quadratic (a b c)
       (list (/ (+ (- b)
                   (sqrt (- (* b b) (* 4 a c))))
                (* 2 a))
             (/ (- (- b)
                   (sqrt (- (* b b) (* 4 a c))))
                (* 2 a))))

8. (defun area-of-circle (radius)
       (* pi radius radius))

9. (defun ftoc (fahrenheit)
       (* (- fahrenheit 32.0) 5/9))

10. (defun rotate-left (lst)
       (append (cdr lst) (list (car lst))))

11. (defun square (x) (* x x))

    (defun euclidean-distance (p1 p2)
       (sqrt (+ (square (- (car p1) (car p2)))
                (square (- (cadr p1) (cadr p2))))))

12. (defun head (lst) (car lst))
    (defun tail (lst) (cdr lst))
:
13. (defun switch (2-list)
       (list (cadr 2-list) (car 2-list)))
