import javax.swing.ImageIcon;

import javax.swing.JOptionPane;

public class JavaQuest {
    public static void main(String[] args) throws Exception {
        int profundidad = Integer
                .parseInt(JOptionPane.showInputDialog("Que profundidad desea para el Mapa?"));

        // STATS INCIALES PROTAGONISTA
        Jugador protagonista = new Jugador(null, "", 500, 20, 20, 5, 1);
        NodoInicial inicio = new NodoInicial(0);
        inicio.interactuar(protagonista);

        // MAPA
        Mapa mapa = new Mapa(profundidad);
        mapa.creacionMapa();

        // MATRIZ ADYACENCIA
        int x = mapa.getNodo_mayor();

        int ConexionAdyacencia[][] = new int[x + 1][x + 1];

        String ValorNodos[][] = new String[x + 1][2];

        for (int i = 0; i <= x; i++) {
            for (int j = 0; j <= x; j++) {
                ConexionAdyacencia[i][j] = 0;
            }
        }

        for (int i = 0; i <= x; i++) {
            for (int j = 0; j < 2; j++) {
                ValorNodos[i][j] = "";
            }

        }

        mapa.nodosConexion(ConexionAdyacencia);
        mapa.valorNodos(ValorNodos);
        mapa.verMapa(ConexionAdyacencia, ValorNodos);

        // ITEMS Y JEFE FINAL
        Personaje antagonista = null;
        Item shuriken = null, revolver = null, shusui = null, wick = null;
        Item escudo = null, casco = null, pocion = null, botiquin = null;

        if (mapa.getProfundidad() >= 3 && mapa.getProfundidad() <= 5) {
            // MAPA CHICO

            // JEFE FINAL
            Stats hp = new Stats(30, 50), danio = new Stats(10, 15), defensa = new Stats(5, 10);
            int hp_total = hp.rangoValores();

            antagonista = new Personaje("KB-09", 0, hp_total, hp_total, danio.rangoValores(), defensa.rangoValores());

            // ITEMS ATAQUE
            Stats tres_cinco = new Stats(3, 5), siete_quince = new Stats(7, 15), veinte_treinta = new Stats(20, 30),
                    treinta_cincuenta = new Stats(30, 50);

            /* 1 */ shuriken = new Item("Shuriken", 300, 0, 0, tres_cinco.rangoValores(), 0);
            /* 2 */ revolver = new Item("Revolver", 700, 0, tres_cinco.rangoValores(), siete_quince.rangoValores(), 0);
            /* 3 */ shusui = new Item("Espada Shusui", 1050, 0, siete_quince.rangoValores(),
                    veinte_treinta.rangoValores(), 0);
            /* 4 */ wick = new Item("Modo John Wick", 2000, treinta_cincuenta.rangoValores(),
                    treinta_cincuenta.rangoValores(), treinta_cincuenta.rangoValores(), 0);

            // ITEMS DEFENSA
            /* 5 */ escudo = new Item("Escudo", 200, 0, 0, 0, tres_cinco.rangoValores());
            /* 6 */ casco = new Item("Casco", 500, 0, 0, 0, 8);

            // ITEMS SANACION
            /* 7 */ pocion = new Item("Pocion", 100, tres_cinco.rangoValores(), tres_cinco.rangoValores(), 0, 0);
            /* 8 */ botiquin = new Item("Botiquin", 600, veinte_treinta.rangoValores(), siete_quince.rangoValores(), 0,
                    0);

        } else if (mapa.getProfundidad() >= 6 && mapa.getProfundidad() <= 8) {
            // MAPA MEDIO

            // JEFE FINAL
            Stats hp = new Stats(90, 110), danio = new Stats(25, 30), defensa = new Stats(20, 30);
            int hp_total = hp.rangoValores();

            antagonista = new Personaje("KB-09", 0, hp_total, hp_total, danio.rangoValores(), defensa.rangoValores());

            // ITEMS ATAQUE
            Stats seis_ocho = new Stats(6, 8), quince_veinticinco = new Stats(15, 25),
                    cuarenta_cincuenta = new Stats(40, 50),
                    cincuenta_setenta = new Stats(50, 70);

            /* 1 */ shuriken = new Item("Shuriken", 300, 0, 0, seis_ocho.rangoValores(), 0);
            /* 2 */ revolver = new Item("Revolver", 700, 0, seis_ocho.rangoValores(), quince_veinticinco.rangoValores(),
                    0);
            /* 3 */ shusui = new Item("Espada Shusui", 1050, 0, quince_veinticinco.rangoValores(),
                    cuarenta_cincuenta.rangoValores(), 0);
            /* 4 */ wick = new Item("Modo John Wick", 2000, cincuenta_setenta.rangoValores(),
                    cincuenta_setenta.rangoValores(), cincuenta_setenta.rangoValores(), 0);

            // ITEMS DEFENSA
            /* 5 */ escudo = new Item("Escudo", 200, 0, 0, 0, seis_ocho.rangoValores());
            /* 6 */ casco = new Item("Casco", 500, 0, 0, 0, 8);

            // ITEMS SANACION
            /* 7 */ pocion = new Item("Pocion", 100, seis_ocho.rangoValores(), seis_ocho.rangoValores(), 0, 0);
            /* 8 */ botiquin = new Item("Botiquin", 600, cuarenta_cincuenta.rangoValores(),
                    quince_veinticinco.rangoValores(), 0,
                    0);
        } else {
            // MAPA GRANDE

            // JEFE FINAL
            Stats hp = new Stats(160, 180), danio = new Stats(40, 45), defensa = new Stats(40, 50);
            int hp_total = hp.rangoValores();

            antagonista = new Personaje("KB-09", 0, hp_total, hp_total, danio.rangoValores(), defensa.rangoValores());

            // ITEMS ATAQUE
            Stats nueve_doce = new Stats(9, 12), quince_veinticinco = new Stats(30, 40),
                    cuarenta_cincuenta = new Stats(40, 50),
                    cincuenta_setenta = new Stats(50, 70);

            /* 1 */ shuriken = new Item("Shuriken", 300, 0, 0, nueve_doce.rangoValores(), 0);
            /* 2 */ revolver = new Item("Revolver", 700, 0, nueve_doce.rangoValores(),
                    quince_veinticinco.rangoValores(),
                    0);
            /* 3 */ shusui = new Item("Espada Shusui", 1050, 0, quince_veinticinco.rangoValores(),
                    cuarenta_cincuenta.rangoValores(), 0);
            /* 4 */ wick = new Item("Modo John Wick", 2000, cincuenta_setenta.rangoValores(),
                    cincuenta_setenta.rangoValores(), cincuenta_setenta.rangoValores(), 0);

            // ITEMS DEFENSA
            /* 5 */ escudo = new Item("Escudo", 200, 0, 0, 0, nueve_doce.rangoValores());
            /* 6 */ casco = new Item("Casco", 500, 0, 0, 0, 8);

            // ITEMS SANACION
            /* 7 */ pocion = new Item("Pocion", 100, nueve_doce.rangoValores(), nueve_doce.rangoValores(), 0, 0);
            /* 8 */ botiquin = new Item("Botiquin", 600, cuarenta_cincuenta.rangoValores(),
                    quince_veinticinco.rangoValores(), 0,
                    0);

        }

        // MENU IMAGEN
        ImageIcon menu = new ImageIcon("PixelArts/Menu.png");

        boolean flag = true;
        String[] menuOpciones = { "(1) Mapa", "(2) Estadisticas", "(3) Items", "(4) Avanzar" };

        while (flag == true) {
            if (antagonista.getHp_actual() <= 0) {
                flag = false;
                ImageIcon victoria = new ImageIcon("PixelArts/Victoria.png");
                JOptionPane.showMessageDialog(null, null, "FELICIDADES LOGRASTE LIBERAR A JAPON DE LOS OMNICOS!",
                        JOptionPane.DEFAULT_OPTION, victoria);
                System.exit(0);
                break;
            }
            
            if(protagonista.getHp_actual() <= 0){
                flag = false;
                ImageIcon derrota = new ImageIcon("PixelArts/Derrota.png");
                JOptionPane.showMessageDialog(null, null, null,
                        JOptionPane.DEFAULT_OPTION, derrota);
                System.exit(0);
                break;
            }

            int opcion = JOptionPane.showOptionDialog(null, menu, null, 0, JOptionPane.QUESTION_MESSAGE, null,
                    menuOpciones, null);

            if (menuOpciones[opcion] == "(1) Mapa") {
                ImageIcon mapaIcon = new ImageIcon("PixelArts/IconMapa.png");
                String map = mapa.getMap();
                JOptionPane.showMessageDialog(null, map, "(1) Mapa", JOptionPane.DEFAULT_OPTION, mapaIcon);

            } else if (menuOpciones[opcion] == "(2) Estadisticas") {
                protagonista.verEstado();
                String estadoprota = protagonista.getStats();
                ImageIcon estadoIcon = new ImageIcon("PixelArts/IconStats.png");
                JOptionPane.showMessageDialog(null, estadoprota,
                        "(2) Estadisticas", JOptionPane.DEFAULT_OPTION, estadoIcon);

            } else if (menuOpciones[opcion] == "(3) Items") {
                protagonista.verItems();
                String itemsprota = protagonista.getItems_string();
                ImageIcon itemsIcon = new ImageIcon("PixelArts/IconItem.png");
                JOptionPane.showMessageDialog(null, itemsprota,
                        "(3) Items", JOptionPane.DEFAULT_OPTION, itemsIcon);

            } else if (menuOpciones[opcion] == "(4) Avanzar") {
                mapa.avanzar(ConexionAdyacencia, ValorNodos);

                int NodoActual = mapa.valorNodo_Actual();

                if (ValorNodos[NodoActual][1] == "Evento") {
                    NodoEvento evento = new NodoEvento(NodoActual);
                    evento.interactuar(protagonista);

                } else if (ValorNodos[NodoActual][1] == "Tienda") {
                    NodoTienda tienda = new NodoTienda(NodoActual);

                    /* 1 */ tienda.agregarItems(shuriken);
                    /* 2 */ tienda.agregarItems(revolver);
                    /* 3 */ tienda.agregarItems(shusui);
                    /* 4 */ tienda.agregarItems(wick);
                    /* 5 */ tienda.agregarItems(escudo);
                    /* 6 */ tienda.agregarItems(casco);
                    /* 7 */ tienda.agregarItems(pocion);
                    /* 8 */ tienda.agregarItems(botiquin);

                    tienda.interactuar(protagonista);

                } else if (ValorNodos[NodoActual][1] == "Combate") {
                    NodoCombate combate = new NodoCombate(NodoActual);
                    combate.interactuar(protagonista);

                } else {
                    NodoJefeFinal JefeFinal = new NodoJefeFinal(antagonista);
                    JefeFinal.interactuar(protagonista, antagonista);
                }
            }
        }
    }
}
