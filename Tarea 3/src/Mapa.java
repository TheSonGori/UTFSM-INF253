import java.util.SortedSet;

import javax.swing.JOptionPane;

import java.io.*;

public class Mapa {

    private Integer profundidad;
    private NodoInicial nodo_inicial;
    private Nodo nodo_actual;
    private String map = "";
    private Integer nodo_mayor;
    private SortedSet<Edge> edges;
    private Edge e;

    // Contructor
    public Mapa(Integer profundidad) {
        this.profundidad = profundidad;
    }

    // Profundidad
    public void setProfundidad(Integer profundidad) {
        this.profundidad = profundidad;
    }

    public Integer getProfundidad() {
        return profundidad;
    }

    // Nodo Inicial
    public void setNodo_inicial(NodoInicial nodo_inicial) {
        this.nodo_inicial = nodo_inicial;
    }

    public NodoInicial getNodo_inicial() {
        return nodo_inicial;
    }

    // Nodo Actual
    public void setNodo_actual(Nodo nodo_actual) {
        this.nodo_actual = nodo_actual;
    }

    public Nodo getNodo_actual() {
        return nodo_actual;
    }

    // Mayor
    public void setNodo_mayor(Integer nodo_mayor) {
        this.nodo_mayor = nodo_mayor;
    }

    public Integer getNodo_mayor() {
        return nodo_mayor;
    }

    // Edges;
    public void setEdges(SortedSet<Edge> edges) {
        this.edges = edges;
    }

    public SortedSet<Edge> getEdges() {
        return edges;
    }

    // e
    public void setE(Edge e) {
        this.e = e;
    }

    public Edge getE() {
        return e;
    }

    // String map
    public void setMap(String map) {
        this.map = map;
    }

    public String getMap() {
        return map;
    }

    // Funciones
    public void creacionMapa() {
        /*
         * ∗ Crea el Mapa del juego
         * ∗
         * ∗ sin parametros:
         * ∗
         * ∗ return: al ser funcion Void no retorna nada!
         */

        SortedSet<Edge> edges = GraphGenerator.Generar(profundidad);

        this.edges = edges;

        NodoInicial NodoInicial = new NodoInicial(0);
        this.nodo_inicial = NodoInicial;
        this.nodo_actual = NodoInicial;

        int mayor = 0;
        int cont = 0;

        for (Edge e : edges) {
            if (cont == 0) {
                this.e = e;
            }
            if (e.y > mayor) {
                mayor = e.y;
            }
            cont++;
        }

        this.nodo_mayor = mayor;
    }

    public void nodosConexion(int ConexionAdyacencia[][]) {
        /*
         * ∗ Guarda la informacion de la conexion de los nodos
         * ∗
         * ∗ int ConexionAdyacencia[][]: matriz que si hay conexion entre dos nodos
         * tendra un 1
         * ∗
         * ∗ return: al ser funcion Void no retorna nada!
         */
        for (Edge e : edges) {
            ConexionAdyacencia[e.x][e.y] = 1;
        }
    }

    public void valorNodos(String ValorNodos[][]) {
        /*
         * ∗ Guarda la informacion del valor de los nodos
         * ∗
         * ∗ String ValorNodos[][]: matriz que contiene el valor de los nodos
         * (tienda/evento/combate)
         * ∗
         * ∗ return: al ser funcion Void no retorna nada!
         */

        String nombreFichero = "PixelArts/NombresCiudades.txt";
        BufferedReader br = null;

        try {
            br = new BufferedReader(new FileReader(nombreFichero));
            String texto = br.readLine();
            for (int i = 0; i <= nodo_mayor; i++) {
                for (int j = 0; j < 2; j++) {
                    if (j == 0) {
                        ValorNodos[i][j] = texto;
                    } else {
                        if (i == nodo_mayor) {
                            ValorNodos[i][j] = "Jefe Final";
                        } else if (i == 0) {
                            ValorNodos[i][j] = "Inicio";
                        } else {
                            int numero = (int) (Math.random() * 10 + 1);
                            if (numero == 1 || numero == 2 || numero == 3) {
                                ValorNodos[i][j] = "Evento";
                            } else if (numero == 4) {
                                ValorNodos[i][j] = "Tienda";
                            } else {
                                ValorNodos[i][j] = "Combate";
                            }

                        }

                    }
                    texto = br.readLine();
                }
            }

        } catch (FileNotFoundException ex) {
            System.out.println("Error: Fichero no encontrado");
            ex.printStackTrace();
        } catch (Exception ex) {
            System.out.println("Error de lectura del fichero");
            ex.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (Exception ex) {
                System.out.println("Error al cerrar el fichero");
                ex.printStackTrace();
            }
        }

    }

    public void verMapa(int ConexionAdyacencia[][], String ValorNodos[][]) {
        /*
         * ∗ Permite al usuario ver el mapa completo
         * ∗
         * ∗ int ConexionAdyacencia[][]: matriz que si hay conexion entre dos nodos
         * tendra un 1
         * ∗ String ValorNodos[][]: matriz que contiene el valor de los nodos
         * (tienda/evento/combate)
         * ∗
         * ∗ return: al ser funcion Void no retorna nada!
         */

        for (int i = 0; i <= nodo_mayor; i++) {
            for (int j = 0; j <= nodo_mayor; j++) {
                if (j == 0) {
                    String NameNodo = ValorNodos[i][0];
                    String TipoNodo = ValorNodos[i][1];
                    if (i == 0) {
                        map += "NODO (" + i + ") " + NameNodo + " - " + TipoNodo + "\n";
                    } else {
                        map += "\nNODO (" + i + ") " + NameNodo + " - " + TipoNodo + "\n";
                    }
                }
                if (ConexionAdyacencia[i][j] != 0) {
                    map += "     ↳ Conexion con: Nodo (" + j + ")\n";
                }

            }
            map += "\n";
        }

    }

    public void avanzar(int ConexionAdyacencia[][], String ValorNodos[][]) {
        /*
         * ∗ Le muestra al usuario los nodos a los que puede avanzar, le pide
         * seleccionar uno
         * y hace al jugador interactuar con ese nodo.
         * ∗
         * ∗ int ConexionAdyacencia[][]: matriz que si hay conexion entre dos nodos
         * tendra un 1
         * ∗ String ValorNodos[][]: matriz que contiene el valor de los nodos
         * (tienda/evento/combate)
         * ∗
         * ∗ return: al ser funcion Void no retorna nada!
         */

        String MovPosibles = "MOVIMIENTOS POSIBLES: \n";
        int ID = nodo_actual.getId();

        for (int i = 0; i <= nodo_mayor; i++) {
            for (int j = 0; j <= nodo_mayor; j++) {
                if (i == ID) {
                    if (ConexionAdyacencia[i][j] != 0) {
                        MovPosibles += "     ↳ Nodo (" + j + ") - " + ValorNodos[j][0] + " - " + ValorNodos[j][1]
                                + "\n";
                    }
                }

            }
        }
        int Movimiento = Integer
                .parseInt(JOptionPane.showInputDialog(null, MovPosibles));

        nodo_actual.setId(Movimiento);
    }

    public int valorNodo_Actual() {
        /*
         * ∗ Retorna el nodo actual
         * ∗
         * sin parametros:
         * ∗
         * ∗ return ID: retorna el nodo actual.
         */

        int ID = nodo_actual.getId();
        return ID;
    }
}