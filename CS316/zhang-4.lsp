;solution to A 
(defun my-sum (L)
	(let ((X (sum (cdr L))))
	     (+ (car L) X)))

;solution to B
(defun my-neg-nums (L)
	(let ((x (neg-nums (cdr L))))
		(if (minusp (car L)) 
		    (cons (car L) X)
		     X)))

;solution to C
(defun my-inc-list-2 (L N)
	(let ((X (inc-list-2 (cdr L) N)))
		 (cons (+ (car L) N) X)))

;solutions to D
(defun my-insrt (N L)
	(if (<= N (car L)) 
		(cons N L)
	    (cons (car L) (insert N (cdr L)))))

;solution to E
(defun my-isort (L)
	(let ((X (isort (cdr L))))
		 (insert (car L) X)))

;solution to F
(defun my-split-list (L)
	(let ((X (split-list (cdr L))))
		(list (cons (car L) (cadr X)) (car X))))

;solution to G
(defun my-partition (L P)
	(let ((X (partition (cdr L) P)))
		(if (< (car L) P) 
			(list (cons (car L) (car X)) (cadr X))
			(list (car X) (cons (car L) (cadr X))))))

;solution to 1
(defun SUM (L)
	(if (endp L) 
		0
		(+ (sum (cdr L)) (car L))))
		
;solution to 2
(defun NEG-NUMS (L)
	(if (endp L) 
		nil
	    (if (minusp (car L)) 
			(cons (car L) (neg-nums (cdr L)))
			(neg-nums (cdr L)))))

;solution to 3
(defun inc-list-2 (L N)
	(if (endp L) 
		nil
	    (cons (+ (car L) N) (inc-list-2 (cdr L) N))))

;solution to 4
(defun insert (N L)
	(if (endp L) 
		(list N)
		(if (<= N (car L)) 
			(cons N L)
			(cons (car L) (insert N (cdr L))))))

;solution to 5
(defun isort (L)
	(if (endp L) 
		nil
		(insert (car L) (isort (cdr L)))))

;solution to 6 corrected
(defun split-list (L)
	(if (endp L) 
		(list nil nil)
		(let ((X (split-list (cdr L))))
			(list (cons (car L) (cadr X)) 
				  (car X)))))

;solution to 7
(defun partition (L P)
	(if (endp L) 
		(list nil nil)
		(let ((X (partition (cdr L) P)))
			(if (< (car L) P) 
				(list (cons (car L) (car X)) (cadr X))
				(list (car X) (cons (car L) (cadr X)))))))

;solution to 8
(defun pos (E L)
	(cond ((endp L) 0)
	      ((equal E (car L)) 1 )
	      (T (let ((X (pos E (cdr L))))
			(if (= X 0) 
			0
			(+ X 1))))))


;solution to 9
(defun split-nums (N)
	(if (zerop N) 
		(list '(0) nil) ;or can be write as (list (list 0) nil)
		(let ((X (split-nums (- N 1))))
			(if (evenp N) 
			(list (cons N (car X)) (cadr X))
			(list (car X) (cons  N (cadr X)))))))

;solution to 10
(defun set-union (S1 S2)
    (cond ((endp S1) S2)
		  ((member (car S1) S2) (set-union (cdr S1) S2))
		  (T (cons (car S1) (set-union (cdr S1) S2)))))

;solution to 11
(defun set-remove (X S)
        (if (endp S) 
			nil
            (if (equal X (car S)) (cdr S)
                (cons (car S) (set-remove X (cdr S))))))
				
;solution to 12
(defun set-excl-union (S1 S2)
    (if (endp S1) 
		S2
        (if (member (car S1) S2) 
			(set-remove (car S1) (set-excl-union (cdr S1) S2))
			(cons (car S1) (set-excl-union (cdr S1) S2)))))
		
;solution t0 13
(defun singletons (E)
    (if (endp E) 
		nil
        (if (member (car E) (cdr E)) 
			(set-remove (car E) (singletons (cdr E)))
            (cons (car E) (singletons (cdr E))))))

	     