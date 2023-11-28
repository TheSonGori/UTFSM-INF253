#MAKEFILE JAVA

all: default run clean

JFLAGS = -g
JC = javac
JVM = java
MAIN = JavaQuest
FILE = 

.SUFFIXES: .java .class

.java.class: 
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	Stats.java \
	GraphGenerator.java \
	Edge.java \
	Item.java \
	Nodo.java \
	NodoCombate.java \
	NodoEvento.java \
	NodoInicial.java \
	NodoJefeFinal.java \
	NodoTienda.java \
	Personaje.java \
	Jugador.java \
	Mapa.java \
	JavaQuest.java 


default: classes

classes: $(CLASSES:.java=.class)

run: $(MAIN).class
	$(JVM) $(MAIN) 

clean: 
	$(RM) *.class
