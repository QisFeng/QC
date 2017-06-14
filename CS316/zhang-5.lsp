;solution to A
(defun my-index (N L)
	(index (- N 1) (cdr L)))
;solution to B
(defun my-min-first (L)
	(let ((X (min-first (cdr L))))
	(if (> (car L) (car X)) 
		(cons (car X) (cons (car L) (cdr X)))
		L)))	
;solution to C
(defun my-ssort (L)
	(let* ((L1 (MIN-FIRST l))
		   (X  (ssort (cdr L1))))
		   (cons (car L1) X)))
;solution to 1
(defun index (N L)
	(if (endp L) 
		'ERROR
		(if (= N 1) 
			(car L)
			(index (- N 1) (cdr L)))))			
;solution to 2
(defun min-first (L)
	(if (endp L) 
		nil
		(if (= 1 (length L)) 
			L
			(let ((X (min-first (cdr L))))
			(if (> (car L) (car X)) 
				(cons (car X)(cons (car L) (cdr X)))
				L)))))			
;solution to 3
(defun ssort (L)
	(if (endp L) 
		nil
		(let((L1 (min-first L)))
			 (cons (car L1) (ssort (cdr L1))))))
;solution to 4
(defun qsort (L)
	(cond ((endp L) nil)
		  (T (let ((pl (partition L (car L))))
				(cond ((endp (car pl)) (cons (car L) (qsort (cdr L)))
					(T (append (qsort (car pl)) (qsort (cadr pl))))))))))					
;solution to 5
(defun merge-lists (L1 L2)
	(if (endp L1) 
		L2
		(if (endp L2)
			L1
			(if (< (car L1) (car L2)) 
				(cons (car L1) (merge-lists (cdr L1) L2))
				(cons (car L2) (merge-lists L1 (cdr L2)))))))
;solution to 6
(defun msort (L)
	(if (endp L)
		nil
		(if (= (length L) 1);((null? (cdr l)) l)
			L
			(let ((X (split-list L)))
				  (merge-lists (msort (car X)) (msort (cadr X)))))))			  
;solution to 7
(defun remove-adj-dupl (L)
	(if (or (endp L) (endp (cdr L))) 
		L
		(let ((X (remove-adj-dupl (cdr L))))
			(if (equal (car L) (car X))
				X
				(cons (car L) X)))))
;corrected for 7 in scm
(define (remove-adj-dupl l)
  (cond ((or (null? l) (null? (cdr l))) l)
        ((equal? (car l) (cadr l))
         (cons (car l) (cdr (remove-adj-dupl (cdr l)))))
        (else (cons (car l) (remove-adj-dupl (cdr l))))))

;solution to 8
(defun unrepeated-elts (L)
	(cond ((endp L) nil)
		  ((or (endp (cdr L)) (not (equal (car L)(cadr L)))) (cons (car L)(unrepeated-elts(cdr L))))
		  ((or (endp (cddr L)) (not (equal (car L)(caddr L)))) (unrepeated-elts(cddr L)))
		  (T  (unrepeated-elts (cdr L)))))
;solution to 9
(defun repeated-elts (L)
	(cond ((endp L) nil)
		  ((or (endp (cdr L)) (not (equal (car L)(cadr L)))) (repeated-elts (cdr L)))
		  ((or (endp (cddr L)) (not (equal (car L)(caddr L)))) (cons (car L)(repeated-elts(cddr L))))
		  (T  (repeated-elts (cdr L)))))
;solution to 10
(defun count-repetitions (L)
	(if (endp L) 
		nil
		(let ((X (count-repetitions (cdr L))))
			(if (or (endp (cdr L) (not (equal (car L) (cadr L)))))
				(cons (cons 1 (list (car L))) (cdr X))))))
;corrected 10
(define (count-repetitions l)
  (cond ((null? l) ())
        ((or (null? (cdr l))
             (not (equal? (car l) (cadr l))))
         (cons (list 1 (car l))
               (count-repetitions (cdr l))))
        (else
         (let ((count-rep-cdr (count-repetitions (cdr l))))
           (cons (list (+ 1 (caar count-rep-cdr))
                       (cadar count-rep-cdr))
                 (cdr count-rep-cdr))))))

;solution to 11
(defun subset (f L)
	(if (endp L) 
		nil
		(if (funcall f (car L))
			(cons (car L) (subset f (cdr L)))
			(subset f (cdr L)))))		
;solution to 12
(defun our-some (f L)
	(if (endp L) 
		nil
		(if (funcall f (car L))
			L
			(our-some (f (cdr L))))))

(defun our-every (f L)
	(if (endp L)
		T
		(and (funcall f (car L)) (our-every f (cdr L)))))		
;solution to 13
(defun newpartition (f L P)
	(if (endp L) 
		(list nil nil)
		(let ((X (newpartition f (cdr L) P)))
			(if (funcall f (car L) P) 
				(list (cons (car L) (car X)) (cadr X))
				(list (car X) (cons (car L) (cadr X)))))))
				
(defun qsort1 (f L)
	(if (endp L) nil
		(let ((X (newpartition f L (car L))))
			(if (endp (car X))
				(cons (car L) (qsort1 f (cdr L)))
				(append (qsort1 (car X)) (qsort1 (cadr X)))))))
;solution to 14
(defun foo (f L)
	(if (endp L) 
		nil
		(list (cons (f (car L)) (cdr L))(foo f (cdr L)))))
;14corrected
(define (foo f l)
  (if (null? l)
    ()
    (cons (cons (f (car l)) (cdr l))
          (map (lambda (lst) (cons (car l) lst))
               (foo f (cdr l))))))

;solution to 15
;(a)
(defun tr-add (L e)
	(if (endp L) 
		e
		(tr-add (cdr L) (+ (car L) e))))
(defun tr-mul (L e)
	(if (endp L) 
		e
		(tr-mul (cdr L) (* (car L) e))))
(defun tr-fac (X Y)
	(if (= X Y)
		Y
		(tr-fac (- X 1) (* X Y))))
;(b)
(defun slow-primep (X)
	(if (not (integerp X)) 
		nil
		(if (< X 1)
			nil
			(= (- X 1) (mod (tr-fac(- X 1) 1) X)))))
;solution to 16
;(a) 
(defun transpose1 (M)
	(if (endp M)
		nil
		(if (endp (cdr M))
			(mapcar #'list (car M))
			(mapcar #'cons (car M) (transpose1 (cdr M))))))
;(b)
(defun transpose2 (M)
	(if (endp M)
		nil
		(if (endp (cdar M))
			(list (mapcar #'car M))
			(cons (mapcar #'car M) (transpose2(mapcar #'cdr M))))))
;(c)
(defun transpose3 (M)
	(apply #'mapcar #'list M))



































