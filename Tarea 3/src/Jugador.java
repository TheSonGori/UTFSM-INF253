import java.util.*;

public class Jugador extends Personaje {
    private List<Item> items_aplicados = new ArrayList<>();
    private String stats = "";
    private String items_string = "";

    // Contructor
    public Jugador(List<Item> items_aplicados, String nombre, Integer dinero, Integer hp_actual, Integer hp_total,
            Integer danio, Integer defensa) {
        super(nombre, dinero, hp_actual, hp_total, danio, defensa);
    }

    // Items_aplicados
    public void setItems_aplicados(List<Item> items_aplicados) {
        this.items_aplicados = items_aplicados;
    }

    public List<Item> getItems_aplicados() {
        return items_aplicados;
    }

    // Stats
    public void setStats(String stats) {
        this.stats = stats;
    }

    public String getStats() {
        return stats;
    }

    // Items
    public void setItems_string(String items_string) {
        this.items_string = items_string;
    }

    public String getItems_string() {
        return items_string;
    }

    // Funciones
    public void agregarItems(Integer pos, Jugador jugador, List<Item> compras) {
        /*
         * ∗ Agrega el item a la lista de items aplicados del jugador.
         * ∗
         * ∗ Integer pos: entrega la posicion del item comprado para sacar la
         * informacion del ArrayList.
         * ∗ Jugador jugador: extiende la clase personaje, tiene toda la informacion del
         * protagonista.
         * List<Item> compras: ArrayList de los items de la tienda.
         * ∗
         * ∗ return: al ser funcion Void no retorna nada!
         */

        items_aplicados.add(compras.get(pos));
    }

    public void agregarItemsSueltos(Jugador jugador, Item item) {
        /*
         * ∗ Agrega el item a la lista de items aplicados del jugador, pero para Items
         * unicos.
         * ∗
         * ∗ Jugador jugador: extiende la clase personaje, tiene toda la informacion del
         * protagonista.
         * Item item: Tiene toda la informacion del item del evento.
         * ∗
         * ∗ return: al ser funcion Void no retorna nada!
         */

        items_aplicados.add(item);
    }

    public void verEstado() {
        /*
         * ∗ Muestra al usuario los atributos actuales del jugador (nombre, dinero, hp
         * actual,hp total, danio y defensa)
         * ∗
         * ∗ sin parametros:
         * ∗
         * ∗ return: al ser funcion Void no retorna nada!
         */

        String name = super.getNombre();
        int cash = super.getDinero();
        int hp_now = super.getHp_actual();
        int hp_max = super.getHp_total();
        int atack = super.getDanio();
        int defense = super.getDefensa();

        stats = "ESTADISTICAS:\n";
        stats += "     ↳ Nombre: " + name + "\n";
        stats += "     ↳ Dinero: " + cash + "\n";
        stats += "     ↳ Vida: " + hp_now + " / " + hp_max + "\n";
        stats += "     ↳ Ataque: " + atack + "\n";
        stats += "     ↳ Defensa: " + defense + "\n";
    }

    public static boolean ListaVacia(Collection<?> collection) {
        /*
         * ∗ Revisa si la lista items_aplicados esta vacia
         * ∗
         * ∗ Collection<?> collection: es la lista a analizar
         * ∗
         * ∗ return collection: si la lista es vacia retorna Verdadero, si la lista no
         * esta vacia retorna Falso
         */
        return collection == null || collection.isEmpty();
    }

    public void verItems() {
        /*
         * ∗ Muestra al usuario los items que a adquirido.
         * ∗
         * ∗ sin parametros:
         * ∗
         * ∗ return: al ser funcion Void no retorna nada!
         */

        int cont = 1;
        items_string = "ITEMS:\n";

        boolean listnull = ListaVacia(items_aplicados);

        if (listnull) {
            items_string += "     Todavia no obtienes ningun item!\n";
        } else {
            for (Item item_aplicado : items_aplicados) {

                String name_item = item_aplicado.getNombre_item();
                int precio_item = item_aplicado.getPrecio();
                int recuperar_hp_item = item_aplicado.getRecuperar_hp();
                int aumentar_hp_total_item = item_aplicado.getAumentar_hp_total();
                int aumentar_danio_item = item_aplicado.getAumentar_danio();
                int aumentar_defensa_item = item_aplicado.getAumentar_defensa();

                items_string += "     ↳ Item (" + cont + "): " + name_item + "\n";
                items_string += "          Precio: " + precio_item + "\n";
                items_string += "          Recuperar Hp: " + recuperar_hp_item + "\n";
                items_string += "          Aumentar Hp: " + aumentar_hp_total_item + "\n";
                items_string += "          Aumentar Ataque: " + aumentar_danio_item + "\n";
                items_string += "          Aumentar Defensa: " + aumentar_defensa_item + "\n\n";

                cont += 1;
            }

        }
    }

}
