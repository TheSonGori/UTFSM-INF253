#MODULES
import pprint
import re
from tokenize import Token
from PIL import Image
import numpy as np

#FIXED COLORS
Rojo = (255,0,0)
Verde = (0,255,0)
Azul = (0,0,255)
Negro = (0,0,0)
Blanco = (255,255,255)

#MATRIX -> IMAGE
def MatrizAImagen(matriz, filename='pixelart.png', factor=10):
    '''
    Convierte una matriz de valores RGB en una imagen y la guarda como un archivo png.
    Las imagenes son escaladas por un factor ya que con los ejemplos se producirian imagenes muy pequeñas.
        
        Parametros:
                matriz (lista de lista de tuplas de enteros): Matriz que representa la imagen en rgb.
                filename (str): Nombre del archivo en que se guardara la imagen.
                factor (int): Factor por el cual se escala el tamaño de las imagenes.
    '''
    matriz = np.array(matriz, dtype=np.uint8)
    np.swapaxes(matriz, 0, -1)

    N = np.shape(matriz)[0]

    img = Image.fromarray(matriz, 'RGB')
    img = img.resize((N*10, N*10), Image.Resampling.BOX)
    img.save(filename)

#ERRORES.TXT
def CreationOfTXTFile(Archive):
    '''
    Crea el archivo errores.txt Que dependiendo del valor de Archive es que se escribe.
    Si el parametro Archive es = a "" escribe "No hay errores!", pero si Archive es != a "" 
    muestra los erroes de sintaxis.

        Parametros:
            Archive (string): string que puede ser vacio o con multiples cadenas.

    '''
    file = open("errrores.txt", "w")

    if Archive == "":
        file.write("No hay errores!")
        file.close()

    else:
        file.write(Archive)
        file.close()

#REGULAR EXPRESSIONS FUNCTION
def RegularExpressions(Line):
    '''
    Funcion que tiene almacenada las expresiones regulares con las cuales se analizara el archivo .txt

        Parametros:
            Line (string): Linea que se quiere analizar del archivo .txt
        Retorno:
            yield (tuple): parecido al return, solo que yield pausa la ejecucion de la funcion y guarda el estado hasta usarla de nuevo.
    '''
    token_specifications = [
        ('Tabulador', r'\t[^\n]+'),
        ('RepetirLinea', r'Repetir \d+ veces [{ [a-zA-Z0-9]+ }'),
        ('RepetirInicio', r'Repetir \d+ veces {'),
        ('Rotacion', r'Derecha|Izquierda'),
        ('Avanzar', r'Avanzar ?[0-9]*'),
        ('PintarRGB', r'Pintar RGB[(][0-9]+[,][0-9]+[,][0-9]+[)]'),
        ('PintarColor', r'Pintar (?:Rojo|Verde|Azul|Negro|Blanco)'),
        ('RepetirFinal', r'\}'),
        ('SaltoDeLinea', r'\n'),
        ('ErrorSintaxis', r'\w+')
    ]

    Regex = '|'.join('(?P<%s>%s)' % pair for pair in token_specifications)

    for x in re.finditer(Regex,Line):
        EvalueRegex = x.lastgroup
        ResultValue = x.group()
        yield (EvalueRegex, ResultValue)

#FUNCTIONS
def MatrixColorCreation(N):
    '''
    Crea la Matriz Color, que es de la forma NxN y dentro de ella contiene una tuplaRGB de '(0,0,0)'

        Parametros:
                N (int): es el valor de la fila y la columna de la matriz (NxN)
        
        Retorno:
                MatrizColor (list): Lista de Listas ej: N=2 -> [[('0,0,0'),('0,0,0')],[('0,0,0'),('0,0,0')]]

    '''
    MatrizColor = []

    for row in range(N):
        MatrizColor.append([])
        for column in range(N):
            MatrizColor[row].append('(0,0,0)')
    return MatrizColor

def MatrixPositionCreation(N):
    '''
    Crea la Matriz Arrow, que es de la forma NxN y dentro de ella contiene una string de la forma
    '-' y para la posicion MatrizArrow[0][0] es '→'.
        
        Parametros:
                N (int): es el valor de la fila y la columna de la matriz (NxN)
        
        Retorno:
                MatrizArrow (list): Lista de Listas ej: N=2 -> [['→','-'],['-','-']]
    '''
    MatrizArrow = []
    for row in range(N):
        MatrizArrow.append([])
        for column in range(N):
            if (row == 0) and (column == 0):
                MatrizArrow[row].append('→')
            else:
                MatrizArrow[row].append('-')
    return MatrizArrow

def BackgroundColor(N,Color,MatrizColor):
    '''
    Cambia el valor de las tupla ingresada en la funcion MatrixColorCreation al color que solicita el usario.
        
        Parametros:
                N (int): es el valor de la fila y la columna de la matriz (NxN)
                Color (tuple): es una tupla de la forma RGB -> (R,G,B)
                MatrizColor (list): Lista de Listas ej: N=2 -> [[('0,0,0'),('0,0,0')],[('0,0,0'),('0,0,0')]] 
    '''
    for row in range(N):
        for column in range(N):
            MatrizColor[row][column] = Color

def CurrentPosition(N, MatrizArrow):
    '''
    Encuentra la Posicion en la que se encuentra la flecha en la lista, para eso tiene que encontrar
    cualquiera de las siguientas flechas: [ '→' , '↑' , '←' , '↓'] 

        Parametros:
            N (int): es el valor de la fila y la columna de la matriz (NxN)
            MatrizArrow (list): Lista de Listas ej: N=2 -> [['→','-'],['-','-']]
        
        Retorno:
            (row,column)(tuple): Tupla que entrega la posicion donde se encuentra la flecha
    '''
    for row in range(N):
        for column in range(N):
            if MatrizArrow[row][column] != '-':
                return (row,column)

def Left(N,MatrizArrow):
    '''
    Gira a la izquierda la orientacion de la flecha que se encuentra guardada en MatrizArrow

        Parametros:
            N (int): Tamaño de la matriz
            MatrizArrow (list): Matriz que almacena la posicion en la que se encuentra la flecha de movimiento
    '''
    for row in range(N):
        for column in range(N):
            if MatrizArrow[row][column] != '-':
                if MatrizArrow[row][column] == '→': MatrizArrow[row][column] = '↑'
                elif MatrizArrow[row][column] == '↑': MatrizArrow[row][column] = '←'
                elif MatrizArrow[row][column] == '←': MatrizArrow[row][column] = '↓'
                elif MatrizArrow[row][column] == '↓': MatrizArrow[row][column] = '→'

def Right(N,MatrizArrow):
    '''
    Gira a la derecha la orientacion de la flecha que se encuentra guardada en MatrizArrow

        Parametros:
            N (int): Tamaño de la matriz
            MatrizArrow (list): Matriz que almacena la posicion en la que se encuentra la flecha de movimiento
    '''
    for row in range(N):
        for column in range(N):
            if MatrizArrow[row][column] != '-':
                if MatrizArrow[row][column] == '→': MatrizArrow[row][column] = '↓'
                elif MatrizArrow[row][column] == '↑': MatrizArrow[row][column] = '→'
                elif MatrizArrow[row][column] == '←': MatrizArrow[row][column] = '↑'
                elif MatrizArrow[row][column] == '↓': MatrizArrow[row][column] = '←'

def AdvancePosition(N,MatrizArrow, Advance):
    '''
    Descripcion de la funcion

        Parametros:
            N (int): Tamaño de la matriz
            MatrizArrow (int): Matriz que almacena la posicion en la que se encuentra la flecha de movimiento
            Advance (int): Cantidad de casillas que se quiere mover la flecha
        Retorno:
            -1 (int): Si el movimiento que se quiere efectuar sale de la matriz, retorna -1
            0 (int): Si el movimiento es correcto y se mantiene dentro de la matriz, retorna 0
    '''
    for row in range(N):
        for column in range(N):
            if MatrizArrow[row][column] != '-':
                #Movimiento [→]
                if MatrizArrow[row][column] == '→':
                    if column != (N-1) and column < (N-1):
                        NewPos = column + Advance
                        if NewPos >= 0 and NewPos <= (N-1):
                            MatrizArrow[row][column] = '-'
                            MatrizArrow[row][NewPos] = '→'
                            return 0
                        else:
                            return -1
                    else:
                        return -1

                #Movimiento [↑]
                elif MatrizArrow[row][column] == '↑':
                    if row != 0:
                        NewPos = row - Advance
                        if NewPos >= 0 and NewPos <= (N-1):
                            MatrizArrow[row][column] = '-'
                            MatrizArrow[NewPos][column] = '↑'
                            return 0
                        else:
                            return -1
                    else:
                        return -1

                #Movimiento [←]
                elif MatrizArrow[row][column] == '←':
                    if column != 0 and column <= (N-1):
                        NewPos = column - Advance
                        if NewPos >= 0 and NewPos <= (N-1):
                            MatrizArrow[row][column] = '-'
                            MatrizArrow[row][NewPos] = '←'
                            return 0
                        else:
                            return -1
                    else:
                        return -1
                        
                #Movimiento [↓]
                elif MatrizArrow[row][column] == '↓':
                    if row != (N-1):
                        NewPos = row + Advance
                        if NewPos >= 0 and NewPos <= (N-1):
                            MatrizArrow[row][column] = '-'
                            MatrizArrow[NewPos][column] = '↓'
                            return 0
                        else:
                            return -1
                    else:
                        return -1 

def Paint(N,Color,MatrizColor,MatrizArrow):
    '''
    Cambia el valor de la tupla que se encuentra guardada en MatrizColor respecto a la posicion de MatrizArrow

        Parametros:
            N (int): Tamaño de la matriz
            Color (tuple): de la forma (R,G,B)
            MatrizColor (list): Matriz que almacena los colores de cada pixel en forma de tupla [(R,G,B)]
            MatrizArrow (list): Matriz que almacena la posicion en la que se encuentra la flecha de movimiento
    '''
    TuplePos = CurrentPosition(N,MatrizArrow) #Tupla de la forma: (row,column)
    row, column = TuplePos
    MatrizColor[row][column] = Color

def TupleRGBCreation(Convert):
    '''
    Para los datos RGB(R,G,B) que sean un string los convierte en una tupla

        Parametros:
            Convert (string): de la forma '(R,G,B)'

        Retorno:
            RGB (tuple): regresa una tupla de la forma (R,G,B)
    '''
    Convert = Convert.split(",")
    NewPosZero = Convert[0]
    NewPosTwo = Convert[2]

    NewPosZero = NewPosZero[1:]
    NewPosTwo = NewPosTwo[:-1]

    Convert[0] = NewPosZero
    Convert[2] = NewPosTwo

    RGB = (int(Convert[0]),int(Convert[1]),int(Convert[2]))

    return RGB

def RepeatInOneLine(N,MatrizArrow,MatrizColor,DataTuple,Archive,RepeatList,Line):
    '''
    Esta funcion es para casos como -> Repetir 8 veces { Pintar Negro Avanzar } En otras palabras, Repetir de una sola linea.
    Obtiene la cantidad de veces que se tiene que repetir y ejecuta lo que este estre llaves {} esa cantidad de veces.

        Parametros:
            N (int): Tamaño de la matriz
            MatrizArrow (list): Matriz que almacena la posicion en la que se encuentra la flecha de movimiento
            MatrizColor (list): Matriz que almacena los colores de cada pixel en forma de tupla [(R,G,B)]
            DataTuple (tuple): Tupla que contiene el nombre de la expresion regular (declarados en la funcion RegularExpressions) y el Resultado encontrado
            Archive (string): Archivo que si no hay error encontrado todavia es vacio
            RepeatList (list): Contiene todo el texto que conforma un Repeat Dividido
            Line (string): Linea del Texto .txt a analizar

        Retorno:
            -1 (int): Si el movimiento que se quiere efectuar sale de la matriz, retorna -1
            0 (int): Si el movimiento es correcto y se mantiene dentro de la matriz, retorna 0
    '''
    Ignore, NumberString = DataTuple
    NumberString = NumberString[8:]
    Number = ""

    for Quantity in NumberString:
        if Quantity != " ":
            Number += Quantity
        else:
            break

    Number = int(Number)

    for x in range(len(NumberString)):
        if NumberString[x] == "{":
            Line = NumberString[x+1:]

    for x in range(len(Line)):
        if Line[x] == "}":
            Line = Line[:x]

    for Repeat in range(Number):
        for Analysis in RegularExpressions(Line):
            DataTuple = Analysis
            Valor = Commands(N,MatrizArrow,MatrizColor,DataTuple,Archive,RepeatList,Line)
            if Valor == -1:
                return -1
            
    return 0

def RepeatDivided(N,MatrizArrow,MatrizColor,DataTuple,Archive,RepeatList,Line):
    '''
    Esta funcion es para casos como -> Repetir 4 veces {
                                            Repetir 8 veces { Pintar Negro Avanzar }
                                            Derecha Derecha Avanzar Derecha
                                        }
    En otras palabras para repetir donde la llaves no estan en una linea juntos.
    Obtiene la cantidad de veces que se tiene que repetir y ejecuta lo que este estre llaves {} esa cantidad de veces.

        Parametros:
            N (int): Tamaño de la matriz
            MatrizArrow (list): Matriz que almacena la posicion en la que se encuentra la flecha de movimiento
            MatrizColor (list): Matriz que almacena los colores de cada pixel en forma de tupla [(R,G,B)]
            DataTuple (tuple): Tupla que contiene el nombre de la expresion regular (declarados en la funcion RegularExpressions) y el Resultado encontrado
            Archive (string): Archivo que si no hay error encontrado todavia es vacio
            RepeatList (list): Contiene todo el texto que conforma un Repeat Dividido
            Line (string): Linea del Texto .txt a analizar

        Retorno:
            -1 (int): Si el movimiento que se quiere efectuar sale de la matriz, retorna -1
            0 (int): Si el movimiento es correcto y se mantiene dentro de la matriz, retorna 0
    '''
    Number = ""
 
    for x in range(len(RepeatList)):
        if x == 0:
            Cut = RepeatList[x][1]
            Cut = Cut[8:]

            for Quantity in Cut:
                if Quantity != " ":
                    Number += Quantity
                else:
                    break
            Number = int(Number)
    RepeatList = RepeatList[1:-1]
    for Repeat in range(Number):
        for x in range(len(RepeatList)):
            Line = RepeatList[x][1]
            Line = Line.replace("\t","")
            for Analysis in RegularExpressions(Line):
                DataTuple = Analysis
                Valor = Commands(N,MatrizArrow,MatrizColor,DataTuple,Archive,RepeatList,Line)
                if Valor == -1:
                    return -1
    RepeatList = []
    return 0

#COMMAND FUNCTION
def Commands(N,MatrizArrow,MatrizColor,DataTuple,Archive,RepeatList,Line):
    '''
    Funcion que contiene todos los llamados a funciones que sean comandos (ej: Right((...))). 

        Parametros:
            N (int): Tamaño de la matriz
            MatrizArrow (list): Matriz que almacena la posicion en la que se encuentra la flecha de movimiento
            MatrizColor (list): Matriz que almacena los colores de cada pixel en forma de tupla [(R,G,B)]
            DataTuple (tuple): Tupla que contiene el nombre de la expresion regular (declarados en la funcion RegularExpressions) y el Resultado encontrado
            Archive (string): Archivo que si no hay error encontrado todavia es vacio
            RepeatList (list): Contiene todo el texto que conforma un Repeat Dividido
            Line (string): Linea del Texto .txt a analizar

        Retorno:
            -1 (int): Si el movimiento que se quiere efectuar sale de la matriz, retorna -1
            0 (int): Si el movimiento es correcto y se mantiene dentro de la matriz, retorna 0
    '''
    Name, Result = DataTuple

    if Name == "Avanzar":
        Advance = Result[7:]
        if (0 == len(Advance)) or (1 == len(Advance)):
            Advance = 1
            AnalysisFunction = AdvancePosition(N,MatrizArrow,Advance)
            if AnalysisFunction == -1:
                Archive += Line
                SyntaxError(Archive)
            else:
                return 0
        else:
            Advance = int(Advance) 
            AnalysisFunction = AdvancePosition(N,MatrizArrow,Advance)
            if AnalysisFunction == -1:
                Archive += Line
                SyntaxError(Archive)
            else:
                return 0

    elif Name == "Rotacion" :
        if Result == "Derecha": 
            Right(N,MatrizArrow)
            return 0
        elif Result == "Izquierda": 
            Left(N,MatrizArrow)
            return 0

    elif (Name == "PintarRGB") or (Name == "PintarColor"):
        if Name == "PintarColor":
            Color = Result[7:]
            if Color == "Rojo": Paint(N,Rojo,MatrizColor,MatrizArrow)
            elif Color == "Verde": Paint(N,Verde,MatrizColor,MatrizArrow)
            elif Color == "Azul": Paint(N,Azul,MatrizColor,MatrizArrow)
            elif Color == "Negro": Paint(N,Negro,MatrizColor,MatrizArrow)
            elif Color == "Blanco": Paint(N,Blanco,MatrizColor,MatrizArrow)
        elif Name == "PintarRGB":
            Convert = Result[10:]
            Color = TupleRGBCreation(Convert)
            Paint(N,Color,MatrizColor,MatrizArrow)
        return 0

    elif Name == "RepetirLinea":
        DataTuple = (Name,Result)
        Valor = RepeatInOneLine(N,MatrizArrow,MatrizColor,DataTuple,Archive,RepeatList,Line)
        if Valor == -1:
            Archive += Line
            SyntaxError(Archive)
        else:
            return 0

    elif Name == "ErrorSintaxis":
        Archive += Line
        SyntaxError(Archive)

    elif (Name == "RepetirInicio") or (Name == "Tabulador"):
        DataTuple = (Name,Result)
        RepeatList.append(DataTuple)
        return 0

    elif Name == "RepetirFinal":
        DataTuple = (Name,Result)
        RepeatList.append(DataTuple)
        Valor = RepeatDivided(N,MatrizArrow,MatrizColor,DataTuple,Archive,RepeatList,Line)
        if Valor == -1:
            Archive += Line
            SyntaxError(Archive)
            

    return 0

#REGULAR EXPRESIONS
RGB_ER = '[(][0-9]+[,][0-9]+[,][0-9]+[)]' #RGB(R,G,B)
Pattern = '[A-Za-z]+'
Number = '[0-9]+'

#RENAME FILE
NameArchivoTXT = 'a.txt'

#SYNTAXERROR
def SyntaxError(Archive):
    '''
    Esta funcion solo se ejecutara cuando exista un error de syntaxis o bien si algun Avanzar N se sale de la MatrizArrow. Si se ejecuta 
    creara un archivo .txt de nombre errores, que contiene la linea de donde comienza el error hasta el final del archivo.txt a analizar.

        Parametros:
            Archive (string): Contiene la linea de donde existe el primer error
    '''
    Compare = Archive.replace("\n", "")
    with open(NameArchivoTXT, 'r', encoding="utf-8") as Text:
        CycleCont = 0
        cont = 0
        for Line in Text:
            Line = Line.replace("\n", "")
            if Line == Compare:
                CycleCont = cont
            elif (CycleCont != 0) and (CycleCont<cont):
                Archive += Line + "\n"
            cont += 1
    CreationOfTXTFile(Archive)
    exit()

#MAIN FUNCTION
try:
    with open(NameArchivoTXT, 'r', encoding="utf-8") as Text:
        Archive = ""
        RepeatList = []
        cont = 0

        for Line in Text:
            if cont<2:
                if cont == 0:
                    ListLine = re.findall(Number,Line)
                    N = int(ListLine[0])
                    N = int(N) # Obtiene el tamaño de la matriz
                    MatrizArrow = MatrixPositionCreation(N)
                    MatrizColor = MatrixColorCreation(N)

                elif cont == 1:
                    TupleRGB = re.findall(RGB_ER,Line) # Vale [] si no hay tupla
                    ColorName = re.findall(Pattern,Line) # Vale 0 si no esta el nombre del color

                    if TupleRGB != []:
                        Convert = TupleRGB[0]
                        RGB = TupleRGBCreation(Convert)
                        BackgroundColor(N,RGB,MatrizColor)
        
                    elif ColorName[-1] != 0:
                        Color = ColorName[-1]
                        if Color == "Rojo": BackgroundColor(N,Rojo,MatrizColor)
                        elif Color == "Verde": BackgroundColor(N,Verde,MatrizColor)
                        elif Color == "Azul": BackgroundColor(N,Azul,MatrizColor)
                        elif Color == "Negro": BackgroundColor(N,Negro,MatrizColor)
                        elif Color == "Blanco": BackgroundColor(N,Blanco,MatrizColor)
            else:
                if cont>2: #Se salta la linea en blanco!
                    for Analysis in RegularExpressions(Line):
                        DataTuple = Analysis
                        ReturnValue = Commands(N,MatrizArrow,MatrizColor,DataTuple,Archive,RepeatList,Line)
                        if ReturnValue == -1:
                            Archive += Line
                            break
                        if Archive != "":
                            break
            cont +=1

except FileNotFoundError:
    print("Error con la lectura del Archivo.txt!")


#RESULT
if Archive == "":
    MatrizAImagen(MatrizColor)
    CreationOfTXTFile(Archive)