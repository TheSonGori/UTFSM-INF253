import javax.swing.ImageIcon;

import javax.swing.JOptionPane;

public class NodoEvento extends Nodo {
        private String descripcion = "";
        private String alternativa1 = "";
        private String alternativa2 = "";
        private Item resultado1 = null;
        private Item resultado2 = null;

        // Contructor
        public NodoEvento(Integer id) {
                super(id);

        }

        // Descripcion
        public void setDescripcion(String descripcion) {
                this.descripcion = descripcion;
        }

        public String getDescripcion() {
                return descripcion;
        }

        // Alternativa1
        public void setAlternativa1(String alternativa1) {
                this.alternativa1 = alternativa1;
        }

        public String getAlternativa1() {
                return alternativa1;
        }

        // Alternativa2
        public void setAlternativa2(String alternativa2) {
                this.alternativa2 = alternativa2;
        }

        public String getAlternativa2() {
                return alternativa2;
        }

        // Resultado1
        public void setResultado1(Item resultado1) {
                this.resultado1 = resultado1;
        }

        public Item getResultado1() {
                return resultado1;
        }

        // Resultado2
        public void setResultado2(Item resultado2) {
                this.resultado2 = resultado2;
        }

        public Item getResultado2() {
                return resultado2;
        }

        // Funciones
        public void interactuar(Jugador jugador) {
                /*
                 * ∗ Le muestra al usuario la descripci´on del evento y las alternativas que
                 * puede
                 * seleccionar
                 * ∗
                 * * Jugador jugador: protagonista.
                 * ∗
                 * ∗ return: al ser funcion Void no retorna nada!
                 */

                Stats key = new Stats(1, 4);
                int opcion;

                ImageIcon EventoIcon = new ImageIcon("PixelArts/IconEvento.png");
                switch (key.rangoValores()) {
                        case 1:
                                alternativa1 = "Zoro";
                                alternativa2 = "Sanji";

                                String[] menuOpciones = { alternativa1, alternativa2 };

                                ImageIcon Evento1 = new ImageIcon("PixelArts/Evento1.png");

                                descripcion = "Vas caminando tranquilamente hacia la siguiente ciudad, cuando derrepente...";

                                JOptionPane.showMessageDialog(null, descripcion,
                                                null, JOptionPane.DEFAULT_OPTION, EventoIcon);

                                descripcion = "Zoro: ¡Por tu culpa cocinero pervertido estamos perdidos!\n" +
                                                "Sanji: ¿POR MI CULPA? ¡TU TE PERDISTE MARIMO Y YO TE VINE A BUSCAR!";

                                JOptionPane.showMessageDialog(null, descripcion, null,
                                                JOptionPane.DEFAULT_OPTION, EventoIcon);

                                descripcion = jugador.getNombre()
                                                + ": Hey Zoro y Sanji! ¿Como están? Escuche que están perdidos... ¿Les ayudo?";

                                JOptionPane.showMessageDialog(null, descripcion, null,
                                                JOptionPane.DEFAULT_OPTION, Evento1);

                                descripcion = "Luego de darles algunas indicaciones para que puedan llegar a la base, ambos me dan las gracias.\n"
                                                + "Antes de retirarse me ofrecen una recompensa por mi ayuda brindada...";

                                JOptionPane.showMessageDialog(null, descripcion, null,
                                                JOptionPane.DEFAULT_OPTION, Evento1);

                                opcion = JOptionPane.showOptionDialog(null, Evento1, null, 0,
                                                JOptionPane.QUESTION_MESSAGE, null,
                                                menuOpciones, null);

                                if (menuOpciones[opcion] == alternativa1) {
                                        Stats danio = new Stats(10, 15);
                                        resultado1 = new Item("Wado Ichimonji", 0, 0, 0, danio.rangoValores(), 0);
                                        resultado1.aplicarEventos(jugador, resultado1);

                                } else {
                                        Stats defensa = new Stats(5, 10), hp = new Stats(10, 15);
                                        resultado2 = new Item("Shokugeki no Sanji", 0, 0, hp.rangoValores(), 0,
                                                        defensa.rangoValores());
                                        resultado2.aplicarEventos(jugador, resultado2);

                                }
                                break;

                        case 2:
                                alternativa1 = "Killua";
                                alternativa2 = "Gon";

                                String[] menuOpciones2 = { alternativa1, alternativa2 };

                                ImageIcon Evento2 = new ImageIcon("PixelArts/Evento2.png");

                                descripcion = "Mientras vas caminado logras escuchar a lo lejos una batalla.\n"
                                                + "Sin dudarlo un segundo corres hacia el lugar...";

                                JOptionPane.showMessageDialog(null, descripcion,
                                                null, JOptionPane.DEFAULT_OPTION, EventoIcon);

                                descripcion = "Cuando llegas al lugar, te das cuenta de que son dos compañeros tuyos peleando con algunos ómnicos.\n"
                                                + " Te unes a ellos sin miedo para derrotar con más facilidad a los ómnicos.";

                                JOptionPane.showMessageDialog(null, descripcion, null,
                                                JOptionPane.DEFAULT_OPTION, EventoIcon);

                                descripcion = "Gon: Gracias por tu ayuda " + jugador.getNombre()
                                                + ", con tu ayuda fue mucho más rápido derrotarlos.\n"
                                                + "Killua: Si, aunque no era necesario...";

                                JOptionPane.showMessageDialog(null, descripcion, null,
                                                JOptionPane.DEFAULT_OPTION, Evento2);

                                descripcion = "Luego de charlar un poco con ellos, decido retomar mi camino.\n"
                                                + "Antes de eso, me ofrecen un pequeño item en agradecimiento por mi ayuda.";

                                JOptionPane.showMessageDialog(null, descripcion, null,
                                                JOptionPane.DEFAULT_OPTION, Evento2);

                                opcion = JOptionPane.showOptionDialog(null, Evento2, null, 0,
                                                JOptionPane.QUESTION_MESSAGE, null,
                                                menuOpciones2, null);

                                if (menuOpciones2[opcion] == alternativa1) {
                                        Stats danio = new Stats(10, 15), recuperar = new Stats(3, 5);
                                        resultado1 = new Item("Denkō Sekka", 0, recuperar.rangoValores(), 0,
                                                        danio.rangoValores(), 0);
                                        resultado1.aplicarEventos(jugador, resultado1);

                                } else {
                                        Stats danio = new Stats(5, 20);
                                        resultado2 = new Item("Jajanken", 0, 0, 0, danio.rangoValores(), 0);
                                        resultado2.aplicarEventos(jugador, resultado2);

                                }

                                break;

                        case 3:
                                alternativa1 = "Vegeta";
                                alternativa2 = "Goku";

                                String[] menuOpciones3 = { alternativa1, alternativa2 };

                                ImageIcon Evento3 = new ImageIcon("PixelArts/Evento3.png");

                                descripcion = "Mientras vas caminado logras escuchar a lo lejos una batalla.\n"
                                                + "Sin dudarlo un segundo corres hacia el lugar...";

                                JOptionPane.showMessageDialog(null, descripcion,
                                                null, JOptionPane.DEFAULT_OPTION, EventoIcon);

                                descripcion = "Cuando llegas al lugar, te das cuenta de que son dos compañeros tuyos peleando con algunos ómnicos.\n"
                                                + " Te unes a ellos sin miedo para derrotar con más facilidad a los ómnicos.";

                                JOptionPane.showMessageDialog(null, descripcion, null,
                                                JOptionPane.DEFAULT_OPTION, EventoIcon);

                                descripcion = "Vegeta: ¡Maldito insecto! No necesitaba tu ayuda.\n"
                                                + "Goku: ¡No seas malo Vegeta, muchas gracias! Te debemos una";

                                JOptionPane.showMessageDialog(null, descripcion, null,
                                                JOptionPane.DEFAULT_OPTION, Evento3);

                                descripcion = "Luego de charlar un poco con ellos, decido retomar mi camino.\n"
                                                + "Antes de eso, Goku me ofrece una capsula en agradecimiento por mi ayuda.";

                                JOptionPane.showMessageDialog(null, descripcion, null,
                                                JOptionPane.DEFAULT_OPTION, Evento3);

                                opcion = JOptionPane.showOptionDialog(null, Evento3, null, 0,
                                                JOptionPane.QUESTION_MESSAGE, null,
                                                menuOpciones3, null);

                                if (menuOpciones3[opcion] == alternativa1) {
                                        Stats defensa = new Stats(3, 5);
                                        resultado1 = new Item("Super Saiyan Segundo Grado", 0, 0, 0, 0,
                                                        defensa.rangoValores());
                                        resultado1.aplicarEventos(jugador, resultado1);

                                } else {
                                        Stats danio = new Stats(7, 15);
                                        resultado2 = new Item("Kamehameha", 0, 0, 0, danio.rangoValores(), 0);
                                        resultado2.aplicarEventos(jugador, resultado2);

                                }

                                break;

                        case 4:
                                alternativa1 = "Dar Pudin";
                                alternativa2 = "Rechazar";

                                String[] menuOpciones4 = { alternativa1, alternativa2 };

                                ImageIcon Evento4 = new ImageIcon("PixelArts/Evento4.png");

                                descripcion = "x: Hey tu!";

                                JOptionPane.showMessageDialog(null, descripcion,
                                                null, JOptionPane.DEFAULT_OPTION, EventoIcon);

                                descripcion = "Me doy media vuelta para saber quien me llama...";

                                JOptionPane.showMessageDialog(null, descripcion, null,
                                                JOptionPane.DEFAULT_OPTION, EventoIcon);

                                descripcion = "¿Es un gato? ¿Un humano? ¡O tal vez un nuevo tipo de ómnico!";

                                JOptionPane.showMessageDialog(null, descripcion, null,
                                                JOptionPane.DEFAULT_OPTION, Evento4);

                                descripcion = "Beerus: ¿Tienes un pudin contigo? Dámelo.";

                                JOptionPane.showMessageDialog(null, descripcion, null,
                                                JOptionPane.DEFAULT_OPTION, Evento4);

                                opcion = JOptionPane.showOptionDialog(null, Evento4, null, 0,
                                                JOptionPane.QUESTION_MESSAGE, null,
                                                menuOpciones4, null);

                                if (menuOpciones4[opcion] == alternativa1) {

                                        descripcion = "Beerus: ¡Toma, en recompensa por el pudin!";

                                        JOptionPane.showMessageDialog(null, descripcion, null,
                                                        JOptionPane.DEFAULT_OPTION, Evento4);

                                        Stats danio = new Stats(20, 30);
                                        resultado1 = new Item("Ultra Ego", 0, 0, 0, danio.rangoValores(), 0);
                                        resultado1.aplicarEventos(jugador, resultado1);

                                } else {
                                        descripcion = "Beerus: ¡Como te atreves a no compartir tu pudin! ¡Ahora siente la ira del dios de la destrucción!";

                                        JOptionPane.showMessageDialog(null, descripcion, null,
                                                        JOptionPane.DEFAULT_OPTION, Evento4);

                                        Stats hp = new Stats(5, 10);
                                        int hp_negativo = -(hp.rangoValores());

                                        resultado2 = new Item("Hakaishin no Ikari", 0, hp_negativo, 0, 0, 0);
                                        resultado2.aplicarEventos(jugador, resultado2);
                                }

                                break;
                }
        }
}
