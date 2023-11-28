import javax.swing.ImageIcon;

import javax.swing.JOptionPane;

public class NodoInicial extends Nodo {
    // Constructor
    public NodoInicial(Integer id) {
        super(id);

    }

    // Funciones
    public void interactuar(Jugador jugador) {
        /*
         * ∗ Le muestra al usuario una introduccion al juego
         * ∗
         * * Jugador jugador: protagonista.
         * ∗
         * ∗ return: al ser funcion Void no retorna nada!
         */

        // INTRODUCCION IMAGENES
        ImageIcon Intro1 = new ImageIcon("PixelArts/Historia1.png");
        ImageIcon Intro2 = new ImageIcon("PixelArts/Historia2.png");

        JOptionPane.showMessageDialog(null, null, "En un futuro no tan lejano… Las maquinas se rebelan",
                JOptionPane.DEFAULT_OPTION, Intro1);
        String Nickname = JOptionPane.showInputDialog(null, Intro2);
        jugador.setNombre(Nickname);
    }
}
