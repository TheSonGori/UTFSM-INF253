import java.util.*;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

public class NodoTienda extends Nodo {
    private List<Item> compras;

    // Constructor
    public NodoTienda(Integer id) {
        super(id);
        compras = new ArrayList<>();
    }

    // Compras
    public void setCompras(List<Item> compras) {
        this.compras = compras;
    }

    public List<Item> getCompras() {
        return compras;
    }

    // Funciones

    public void agregarItems(Item item) {
        /*
         * ∗ Agrega los items a la tienda
         * ∗
         * * Item item: item.
         * ∗
         * ∗ return: al ser funcion Void no retorna nada!
         */

        compras.add(item);
    }

    public void interactuar(Jugador jugador) {
        /*
         * ∗ Le muestra los items en el inventario de la tienda al usuario y cuanto
         * dinero
         * tiene a su disposicion. Le permite comprar tantos items como quiera.
         * ∗
         * * Jugador jugador: protagonista.
         * ∗
         * ∗ return: al ser funcion Void no retorna nada!
         */

        ImageIcon tiendaIcon = new ImageIcon("PixelArts/IconTienda.png");

        ImageIcon tiendaIMG = new ImageIcon("PixelArts/Tienda.png");
        Scanner input = new Scanner(System.in);

        char valor = JOptionPane.showInputDialog("Desea Ingresar a la Tienda? (Y/N): ").charAt(0);

        int item_compra;
        if (valor == 'Y') {
            String info = "";
            boolean flag = true;
            String respuesta;

            while (flag == true) {
                int dinero = jugador.getDinero();

                info = "STATS\n";
                info += "     ↳ Dinero disponible: $" + dinero + "\n";
                info += "     ↳ Vida: " + jugador.getHp_actual() + " / " + jugador.getHp_total() + "\n";
                info += "     ↳ Ataque: " + jugador.getDanio() + "\n";
                info += "     ↳ Defensa: " + jugador.getDefensa() + "\n";

                JOptionPane.showMessageDialog(null, info,
                        "Tienda", JOptionPane.DEFAULT_OPTION, tiendaIcon);
                respuesta = JOptionPane.showInputDialog(null, tiendaIMG);

                if (respuesta == null) {
                    flag = false;
                    break;

                } else {
                    item_compra = Integer.parseInt(respuesta);
                    comprar(jugador, item_compra, dinero);
                }
            }
        }

        input.close();
    }

    public void comprar(Jugador jugador, Integer valor, Integer dinero) {
        /*
         * ∗ Revisa si el al protagonista le alcanza el dinero para el item y aplica las
         * stats del item
         * ∗
         * * Jugador jugador: protagonista.
         * * Integer valor: seleccion de item a comprar.
         * * Integer dinero: dinero del protagonsita.
         * ∗
         * ∗ return: al ser funcion Void no retorna nada!
         */

        ImageIcon tiendaIcon = new ImageIcon("PixelArts/IconTienda.png");
        int valorItem, nuevoDinero;

        switch (valor) {
            case 1: // SHURIKEN
                valorItem = compras.get(0).getPrecio();
                nuevoDinero = dinero - valorItem;
                if (nuevoDinero >= 0) {
                    jugador.setDinero(nuevoDinero);
                    compras.get(0).aplicar(0, jugador, compras);

                } else {
                    JOptionPane.showMessageDialog(null, "No tiene del dinero suficiente para comprar el Item!",
                            "Tienda", JOptionPane.DEFAULT_OPTION, tiendaIcon);
                }
                break;
            case 2: // REVOLVER
                valorItem = compras.get(1).getPrecio();
                nuevoDinero = dinero - valorItem;
                if (nuevoDinero >= 0) {
                    jugador.setDinero(nuevoDinero);
                    compras.get(1).aplicar(1, jugador, compras);
                } else {
                    JOptionPane.showMessageDialog(null, "No tiene del dinero suficiente para comprar el Item!",
                            "Tienda", JOptionPane.DEFAULT_OPTION, tiendaIcon);
                }
                break;
            case 3: // SHISUI
                valorItem = compras.get(2).getPrecio();
                nuevoDinero = dinero - valorItem;
                if (nuevoDinero >= 0) {
                    jugador.setDinero(nuevoDinero);
                    compras.get(2).aplicar(2, jugador, compras);
                } else {
                    JOptionPane.showMessageDialog(null, "No tiene del dinero suficiente para comprar el Item!",
                            "Tienda", JOptionPane.DEFAULT_OPTION, tiendaIcon);
                }
                break;
            case 4: // WICK
                valorItem = compras.get(3).getPrecio();
                nuevoDinero = dinero - valorItem;
                if (nuevoDinero >= 0) {
                    jugador.setDinero(nuevoDinero);
                    compras.get(3).aplicar(3, jugador, compras);
                } else {
                    JOptionPane.showMessageDialog(null, "No tiene del dinero suficiente para comprar el Item!",
                            "Tienda", JOptionPane.DEFAULT_OPTION, tiendaIcon);
                }
                break;
            case 5: // ESCUDO
                valorItem = compras.get(4).getPrecio();
                nuevoDinero = dinero - valorItem;
                if (nuevoDinero >= 0) {
                    jugador.setDinero(nuevoDinero);
                    compras.get(4).aplicar(4, jugador, compras);
                } else {
                    JOptionPane.showMessageDialog(null, "No tiene del dinero suficiente para comprar el Item!",
                            "Tienda", JOptionPane.DEFAULT_OPTION, tiendaIcon);
                }
                break;
            case 6: // CASCO
                valorItem = compras.get(5).getPrecio();
                nuevoDinero = dinero - valorItem;
                if (nuevoDinero >= 0) {
                    jugador.setDinero(nuevoDinero);
                    compras.get(5).aplicar(5, jugador, compras);
                } else {
                    JOptionPane.showMessageDialog(null, "No tiene del dinero suficiente para comprar el Item!",
                            "Tienda", JOptionPane.DEFAULT_OPTION, tiendaIcon);
                }
                break;
            case 7: // POCION
                valorItem = compras.get(6).getPrecio();
                nuevoDinero = dinero - valorItem;
                if (nuevoDinero >= 0) {
                    jugador.setDinero(nuevoDinero);
                    compras.get(6).aplicar(6, jugador, compras);
                } else {
                    JOptionPane.showMessageDialog(null, "No tiene del dinero suficiente para comprar el Item!",
                            "Tienda", JOptionPane.DEFAULT_OPTION, tiendaIcon);
                }
                break;
            case 8: // BOTIQUIN
                valorItem = compras.get(7).getPrecio();
                nuevoDinero = dinero - valorItem;
                if (nuevoDinero >= 0) {
                    jugador.setDinero(nuevoDinero);
                    compras.get(7).aplicar(7, jugador, compras);
                } else {
                    JOptionPane.showMessageDialog(null, "No tiene del dinero suficiente para comprar el Item!",
                            "Tienda", JOptionPane.DEFAULT_OPTION, tiendaIcon);
                }
                break;
            default:
                JOptionPane.showMessageDialog(null, "Ingrese un numero de Item valido (1-8)!",
                        "Tienda", JOptionPane.DEFAULT_OPTION, tiendaIcon);
                break;
        }

    }

}