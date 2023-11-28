#lang scheme (current-namespace (make-base-namespace))

#|---------------------------------------------------------------------------------------------------|#

(define (inversont lista n)
  ;; recibe una lista ordenada de mayor a menor y un numero n, retorna una lista con todos los numeros entre 0 y n que no esten en la lista. 
  ;;
  ;; lista : lista ordenada de mayor a menor
  ;; n : (- n 1) respecto a la original
  
  (if (= n (- 1))
      (list)
      (if (= n 0)
          (if (null? lista)
              (list 0)
              (if (= (car lista) n)
                  (inversont lista (- n 1))
                  (cons n (inversont lista (- n 1)))))
          (if (null? lista)
              (cons n (inversont lista (- n 1)))
              (if (= (car lista) n)
                  (inversont (cdr lista) (- n 1))
                  (cons n (inversont lista (- n 1))))))))


#|---------------------------------------------------------------------------------------------------|#

;I N V E R S O

(define (inverso lista n)
  (reverse (inversont (reverse lista) (- n 1))))

#|---------------------------------------------------------------------------------------------------|#


(define (index lista valor)
  ;; Obtiene la posicion de cierto valor en la lista, si este no es encontrado devuelve #f
  ;;
  ;; lista : lista a analizar
  ;; valor : valor del cual se busca su igual en la lista
  
  (let getIndex((pos 0) (shortlista lista))
    (if (null? shortlista)
        #f
        (if (= (car shortlista) valor)
            pos
            (getIndex (+ pos 1) (cdr shortlista))))))


#|---------------------------------------------------------------------------------------------------|#

;U M B R A L  S I M P L E

(define (umbral_simple lista umbral tipo)
  (let mini_umbral((u umbral) (type tipo) (shortlist lista))
    (if (null? shortlist)
        (list)
        (if (eq? type #\M)
            (if (> (car shortlist) umbral)
                (cons (index lista (car shortlist))(mini_umbral u type (cdr shortlist)))
                (mini_umbral u type (cdr shortlist)))
            (if (< (car shortlist) umbral)
                (cons (index lista (car shortlist))(mini_umbral u type (cdr shortlist)))
                (mini_umbral u type (cdr shortlist)))))))


#|---------------------------------------------------------------------------------------------------|#

(define (encontrar lista valor)
  ;; Busca el valor en la lista, si no lo encuentra retorna #f
  ;;
  ;; lista : lista a analizar
  ;; valor : numero a encontrar en la lista
  
  (if (null? lista)
      #f
      (if(= (car lista) valor)
         (car lista)
         (encontrar (cdr lista) valor))))

(define (mayor lista)
  ;; Encuentra el valor mayor dentro de la lista.
  ;;
  ;; lista : lista de numeros a analizar
  
  (cond
    [(empty? lista) empty]
    [(empty? (rest lista))(first lista)]
    [(>(car lista)(car (cdr lista)))(mayor (cons (car lista)(cddr lista)))]
    [else (mayor (cdr lista))]))


#|---------------------------------------------------------------------------------------------------|#

;U M B R A L  C O L A

(define (umbral_cola lista umbral tipo)
  (let miniumbral((pos 0) (newlista (list)) (n (mayor lista)))
    (if (null? lista)
        (newlista)
        (if (<= pos n)
            (if (equal? #f (encontrar lista pos))
                (miniumbral (+ pos 1) newlista n)
                (if (eq? tipo #\M)
                    (if (> pos umbral) ; #\M
                        (miniumbral (+ pos 1) (cons (index lista pos) newlista) n)
                        (miniumbral (+ pos 1) newlista n)
                        ) 
                    (if (< pos umbral) ; #\m
                        (miniumbral (+ pos 1) (cons (index lista pos) newlista) n)
                        (miniumbral (+ pos 1) newlista n))))
            newlista))))

#|---------------------------------------------------------------------------------------------------|#

(define (obtener_valor lista pos)
  ;; Obtiene en valor en la lista segun la posicion solicitada
  ;;
  ;; lista : lista
  ;; pos : posicion de la cual se quiere obtener el valor de la lista
  
  (let comparar((x 0) (shortlist lista))
    (if (null? shortlist)
        -1 ; se salio del rango de length de lista
        (if (= x pos)
            (car shortlist)
            (comparar (+ x 1) (cdr shortlist))))))


#|---------------------------------------------------------------------------------------------------|#

;M O D S E L  S I M P L E

(define (modsel_simple lista seleccion f)
  (let miniumbral((pos 0) (newlista lista) (newseleccion (sort seleccion <)))
    (if (<= pos (length lista))
        (if (not (null? newseleccion))
            (if (= (car newseleccion) pos)
                (cons (f (car newlista)) (miniumbral (+ pos 1) (cdr newlista) (cdr newseleccion)))
                (cons (car newlista) (miniumbral (+ pos 1) (cdr newlista) newseleccion)))
            
            (if (null? newlista)
                empty
                (cons (car newlista) (miniumbral (+ pos 1) (cdr newlista) newseleccion))
                )
            )
        empty)))


#|---------------------------------------------------------------------------------------------------|#

;M O D S E L  C O L A

(define (modsel_cola lista seleccion f)
  (let mini_modsel ((pos_lista 0) (pos_seleccion 0) (maxpos_lista (length lista)) (maxpos_seleccion (length seleccion)) (newlista '()))
    (if (<= pos_lista maxpos_lista)
        (if (<= pos_seleccion maxpos_seleccion)
            (if (= (obtener_valor seleccion pos_seleccion) pos_lista)
                (mini_modsel (+ pos_lista 1) 0 maxpos_lista maxpos_seleccion (cons (f (obtener_valor lista pos_lista)) newlista))
                (mini_modsel pos_lista (+ pos_seleccion 1) maxpos_lista maxpos_seleccion newlista)
                )
            (if (< pos_lista maxpos_lista)
                (mini_modsel (+ pos_lista 1) 0 maxpos_lista maxpos_seleccion (cons (obtener_valor lista pos_lista) newlista))
                (reverse newlista)))
        (reverse newlista))))

#|---------------------------------------------------------------------------------------------------|#

;E S T A B L E S

(define (estables lista umbral fM fm)
  (let ((mayores (umbral_cola lista umbral #\M)) (menores (umbral_cola lista umbral #\m)))
    (let ((modselmayor (modsel_cola lista mayores fM)) (modselmenor (modsel_cola lista menores fm)))
      (let iterar((pos 0) (menor 0) (mayor 0))
        (if (< pos (length lista))
            (cond
              [(< umbral (obtener_valor modselmayor pos)) (iterar (+ pos 1) menor (+ mayor 1))]
              [(> umbral (obtener_valor modselmenor pos)) (iterar (+ pos 1) (+ menor 1) mayor)]
              [else (iterar (+ pos 1) menor mayor)])
            (list mayor menor))))))

#|---------------------------------------------------------------------------------------------------|#

(define (obtenerPos lista pos x)
  ;; retorna el valor en la lista segun la posicion solicitada
  ;;
  ;; lista : lista de la cual se quiere obtener el valoir
  ;; pos : posicion de la cual se quiere obtener el valor en la lista 
  ;; x : va iterando constantemente hasta que sea igual a pos
  
  (if (null? lista)
      -1
      (if (= x pos)
          (car lista)
          (obtenerPos (cdr lista) pos (+ x 1)))))


#|---------------------------------------------------------------------------------------------------|#

;Q U E R Y

(define (query lista pos op params)
  (cond
    [(= op 1)(umbral_cola (obtenerPos lista pos 0) (obtenerPos params 0 0) (obtenerPos params 1 0))];Umbral
    [(= op 2)(modsel_cola (obtenerPos lista pos 0) (obtenerPos params 0 0) (eval (obtenerPos params 1 0)))];Modsel
    [(= op 3)(estables (obtenerPos lista pos 0) (obtenerPos params 0 0) (eval (obtenerPos params 1 0)) (eval (obtenerPos params 2 0)))];Estable
    ))

#|---------------------------------------------------------------------------------------------------|#