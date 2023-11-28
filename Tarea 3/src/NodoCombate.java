import javax.swing.ImageIcon;

public class NodoCombate extends Nodo {
    private Personaje enemigo;

    // Contructor
    public NodoCombate(Integer id) {
        super(id);
    }

    // Enemigo
    public void setEnemigo(Personaje enemigo) {
        this.enemigo = enemigo;
    }

    public Personaje getEnemigo() {
        return enemigo;
    }

    // Funciones
    public void interactuar(Jugador jugador) {
        /*
         * ∗ Realiza el combate inform´andole al usuario los resultados de cada ataque
         * realizado y determina al ganador.
         * ∗
         * * Jugador jugador: protagonista.
         * ∗
         * ∗ return: al ser funcion Void no retorna nada!
         */

        Stats key = new Stats(1, 3);
        Personaje enemigo = null;
        int nuevoDinero;

        switch (key.rangoValores()) {
            case 1: // COMBATE FACIL
                ImageIcon Combate1 = new ImageIcon("PixelArts/Combate1.png");
                enemigo = new Personaje("Bastion", 0, 10, 10, 2, 0);
                jugador.combate(enemigo, jugador, Combate1);
                nuevoDinero = jugador.getDinero() + 300;
                jugador.setDinero(nuevoDinero);
                break;

            case 2: // COMBATE MEDIO
                ImageIcon Combate2 = new ImageIcon("PixelArts/Combate2.png");
                enemigo = new Personaje("RK-800", 0, 20, 20, 5, 2);
                jugador.combate(enemigo, jugador, Combate2);
                nuevoDinero = jugador.getDinero() + 500;
                jugador.setDinero(nuevoDinero);
                break;

            case 3: // COMBATE DIFICIL
                ImageIcon Combate3 = new ImageIcon("PixelArts/Combate3.png");
                enemigo = new Personaje("Eva01", 0, 35, 35, 10, 5);
                jugador.combate(enemigo, jugador, Combate3);
                nuevoDinero = jugador.getDinero() + 700;
                jugador.setDinero(nuevoDinero);
                break;
        }
    }
}
