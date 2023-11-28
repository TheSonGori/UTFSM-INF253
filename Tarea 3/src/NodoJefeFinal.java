import javax.swing.ImageIcon;

public class NodoJefeFinal {
    private Personaje jefe;

    // Contructor
    public NodoJefeFinal(Personaje jefe) {
        this.jefe = jefe;
    }

    // Jefe
    public void setJefe(Personaje jefe) {
        this.jefe = jefe;
    }

    public Personaje getJefe() {
        return jefe;
    }

    // Funciones
    public void interactuar(Jugador jugador, Personaje JefeFinal) {
        /*
         * ∗ Realiza el combate inform´andole al usuario los resultados de cada ataque
         * realizado y determina al ganador.
         * ∗
         * * Jugador jugador: protagonista.
         * * Personaje JefeFinal: antagonista.
         * ∗
         * ∗ return: al ser funcion Void no retorna nada!
         */

        ImageIcon antagonistaImage = new ImageIcon("PixelArts/JefeFinal.gif");
        jugador.combate(JefeFinal, jugador, antagonistaImage);
    }
}
