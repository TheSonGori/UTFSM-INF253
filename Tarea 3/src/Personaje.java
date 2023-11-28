import javax.swing.ImageIcon;

import javax.swing.JOptionPane;

public class Personaje {

    private String nombre;
    private Integer dinero;
    private Integer hp_actual;
    private Integer hp_total;
    private Integer danio;
    private Integer defensa;

    // Contructor

    public Personaje(String nombre, Integer dinero, Integer hp_actual, Integer hp_total, Integer danio,
            Integer defensa) {
        this.nombre = nombre;
        this.dinero = dinero;
        this.hp_actual = hp_actual;
        this.hp_total = hp_total;
        this.danio = danio;
        this.defensa = defensa;
    }

    // Nombre
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    // Dinero
    public void setDinero(Integer dinero) {
        this.dinero = dinero;
    }

    public Integer getDinero() {
        return dinero;
    }

    // Hp_actual
    public void setHp_actual(Integer hp_actual) {
        this.hp_actual = hp_actual;
    }

    public Integer getHp_actual() {
        return hp_actual;
    }

    // Hp_total
    public void setHp_total(Integer hp_total) {
        this.hp_total = hp_total;
    }

    public Integer getHp_total() {
        return hp_total;
    }

    // Danio
    public void setDanio(Integer danio) {
        this.danio = danio;
    }

    public Integer getDanio() {
        return danio;
    }

    // Defensa
    public void setDefensa(Integer defensa) {
        this.defensa = defensa;
    }

    // Funciones
    public Integer getDefensa() {
        return defensa;
    }

    public void combate(Personaje enemigo, Jugador jugador, ImageIcon combate) {
        /*
         * ∗ Realiza el combate entre dos personajes
         * ∗
         * ∗ Personaje enemigo: enemigo a vencer.
         * * Jugador jugador: protagonista.
         * * ImageIcon combate: imagen del enemigo a vencer
         * ∗
         * ∗ return: al ser funcion Void no retorna nada!
         */

        boolean flag = true;
        String pelea = "";

        ImageIcon CombateIcon = new ImageIcon("PixelArts/IconCombate.png");

        JOptionPane.showMessageDialog(null, "En tu camino te encuentras una Unidad " + enemigo.getNombre(),
                null,
                JOptionPane.DEFAULT_OPTION, CombateIcon);

        pelea = enemigo.getNombre() + ":\n";
        pelea += "     ↳ Vida: " + enemigo.getHp_total() + " / " + enemigo.getHp_total() + "\n";
        pelea += "     ↳ Defensa: " + enemigo.getDefensa() + "\n";
        pelea += "     ↳ Danio: " + enemigo.getDanio();

        JOptionPane.showMessageDialog(null, pelea, null,
                JOptionPane.DEFAULT_OPTION, combate);

        pelea = jugador.getNombre() + ":\n";
        pelea += "     ↳ Vida: " + jugador.getHp_actual() + " / " + jugador.getHp_total() + "\n";
        pelea += "     ↳ Defensa: " + jugador.getDefensa() + "\n";
        pelea += "     ↳ Danio: " + jugador.getDanio();

        JOptionPane.showMessageDialog(null, pelea, null,
                JOptionPane.DEFAULT_OPTION, combate);

        while (flag == true) {
            // TURNO JUGADOR
            int VidaEnemigo = enemigo.getHp_actual() - (jugador.getDanio() - enemigo.getDefensa());
            
            if((jugador.getDanio() - enemigo.getDefensa())>=0){
                enemigo.setHp_actual(VidaEnemigo);
            }

            // MOSTRAR DANIO
            pelea = enemigo.getNombre() + ":\n";
            pelea += "     ↳ Vida: " + enemigo.getHp_actual() + " / " + enemigo.getHp_total() + "\n";
            pelea += "     ↳ Defensa: " + enemigo.getDefensa() + "\n";
            pelea += "     ↳ Danio: " + enemigo.getDanio();

            JOptionPane.showMessageDialog(null, pelea, null,
                    JOptionPane.DEFAULT_OPTION, combate);

            if (enemigo.getHp_actual() <= 0) {
                flag = false;
                JOptionPane.showMessageDialog(null, "Felicidades! Has derrotado al ominico " + enemigo.getNombre(),
                        null,
                        JOptionPane.DEFAULT_OPTION, CombateIcon);
                break;
            }

            // TURNO ENEMIGO
            int VidaJugador = jugador.getHp_actual() - (enemigo.getDanio() - jugador.getDefensa());
            
            if((enemigo.getDanio() - jugador.getDefensa())>=0){
                jugador.setHp_actual(VidaJugador);
            }

            // MOSTRAR DANIO
            pelea = jugador.getNombre() + ":\n";
            pelea += "     ↳ Vida: " + jugador.getHp_actual() + " / " + jugador.getHp_total() + "\n";
            pelea += "     ↳ Defensa: " + jugador.getDefensa() + "\n";
            pelea += "     ↳ Danio: " + jugador.getDanio();

            JOptionPane.showMessageDialog(null, pelea, null,
                    JOptionPane.DEFAULT_OPTION, combate);

            if (jugador.getHp_actual() <= 0) {
                ImageIcon derrota = new ImageIcon("PixelArts/Derrota.png");
                flag = false;
                JOptionPane.showMessageDialog(null, null, null,
                        JOptionPane.DEFAULT_OPTION, derrota);
                System.exit(0);
                break;
            }

        }
    }

}
