DATOS:

	NOMBRE: Javiera Gutierrez Abarca
	ROL: 202173626-3


IMPORTANTE:

	En el codigo asumo que el Ancho N y el Color de Fondo 'Color' o cualquier tupla de RGB(R,G,B) son 
	lineas o palabras que nunca tendran problema de sintaxis.
	(tupla de RGB(R,G,B) en cualquier parte del codigo respetara su sintaxis)

	Cuando existe un error de sintaxis en cualquier linea, errores.txt contendra desde la linea de error hasta el final del codigo, porque si esta mala una parte
	del archivo.txt, esta malo todo lo que sigue a esa linea.

	La sintaxis en las expresiones regulares se encuentra en la funcion RegularExpressions

	Ej:
		Archivo.txt a analizar:
				Ancho 3
				Color de fondo Negro

				Repetir 2 {
					Pintar RGB(255,0,0) Avanzar Derecha 1 Avanzar
				}
				Pintar RGB(255,0,0)
		
		errores.txt resultado:
				Repetir 2 {
							Pintar RGB(255,0,0) Avanzar Derecha 1 Avanzar
						}
						Pintar RGB(255,0,0)









