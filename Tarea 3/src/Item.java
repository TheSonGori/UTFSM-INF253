import java.util.*;

public class Item {
    private String nombre_item;
    private Integer precio;
    private Integer recuperar_hp;
    private Integer aumentar_hp_total;
    private Integer aumentar_danio;
    private Integer aumentar_defensa;

    // Constructor
    public Item(String nombre_item, Integer precio, Integer recuperar_hp, Integer aumentar_hp_total,
            Integer aumentar_danio,
            Integer aumentar_defensa) {
        this.nombre_item = nombre_item;
        this.precio = precio;
        this.recuperar_hp = recuperar_hp;
        this.aumentar_hp_total = aumentar_hp_total;
        this.aumentar_danio = aumentar_danio;
        this.aumentar_defensa = aumentar_defensa;
    }

    // Nombre_item
    public void setNombre_item(String nombre_item) {
        this.nombre_item = nombre_item;
    }

    public String getNombre_item() {
        return nombre_item;
    }

    // Precio
    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Integer getPrecio() {
        return precio;
    }

    // Recuperar_hp
    public void setRecuperar_hp(Integer recuperar_hp) {
        this.recuperar_hp = recuperar_hp;
    }

    public Integer getRecuperar_hp() {
        return recuperar_hp;
    }

    // Aumentar_hp_total
    public void setAumentar_hp_total(Integer aumentar_hp_total) {
        this.aumentar_hp_total = aumentar_hp_total;
    }

    public Integer getAumentar_hp_total() {
        return aumentar_hp_total;
    }

    // Aumentar_danio
    public void setAumentar_danio(Integer aumentar_danio) {
        this.aumentar_danio = aumentar_danio;
    }

    public Integer getAumentar_danio() {
        return aumentar_danio;
    }

    // Aumentar_defensa
    public void setAumentar_defensa(Integer aumentar_defensa) {
        this.aumentar_defensa = aumentar_defensa;
    }

    public Integer getAumentar_defensa() {
        return aumentar_defensa;
    }

    // Funciones
    public void aplicar(Integer pos, Jugador jugador, List<Item> compras) {
        /*
         * ∗ Ingresa las nuevas stats al protagonista
         * ∗
         * ∗ Integer pos: entrega la posicion del item comprado para sacar la
         * informacion del ArrayList.
         * ∗ Jugador jugador: extiende la clase personaje, tiene toda la informacion del
         * protagonista.
         * List<Item> compras: ArrayList de los items de la tienda.
         * ∗
         * ∗ return: al ser funcion Void no retorna nada!
         */

        jugador.agregarItems(pos, jugador, compras);

        // STATS ITEM
        int recuperar_hp = compras.get(pos).getRecuperar_hp();
        int aumentar_hp_total = compras.get(pos).getAumentar_hp_total();
        int aumentar_danio = compras.get(pos).getAumentar_danio();
        int aumentar_defensa = compras.get(pos).getAumentar_defensa();

        // STATS JUGADOR
        int hp_actual = jugador.getHp_actual();
        int hp_total = jugador.getHp_total();
        int danio = jugador.getDanio();
        int defensa = jugador.getDefensa();

        // NUEVAS STATS JUGADOR
        jugador.setHp_total(hp_total + aumentar_hp_total);

        if (hp_actual + recuperar_hp > jugador.getHp_total()) {
            jugador.setHp_actual(jugador.getHp_total());
        } else {
            jugador.setHp_actual(hp_actual + recuperar_hp);
        }

        jugador.setDanio(danio + aumentar_danio);
        jugador.setDefensa(defensa + aumentar_defensa);

    }

    public void aplicarEventos(Jugador jugador, Item item) {
        /*
         * ∗ Ingresa las nuevas stats al protagonista, la unica diferencia con la
         * funcion anterior es que esta funcion es para eventos.
         * ∗
         * ∗ Jugador jugador: extiende la clase personaje, tiene toda la informacion del
         * protagonista.
         * Item item: Tiene toda la informacion del item del evento.
         * ∗
         * ∗ return: al ser funcion Void no retorna nada!
         */

        jugador.agregarItemsSueltos(jugador, item);

        // STATS ITEM
        int recuperar_hp = item.getRecuperar_hp();
        int aumentar_hp_total = item.getAumentar_hp_total();
        int aumentar_danio = item.getAumentar_danio();
        int aumentar_defensa = item.getAumentar_defensa();

        // STATS JUGADOR
        int hp_actual = jugador.getHp_actual();
        int hp_total = jugador.getHp_total();
        int danio = jugador.getDanio();
        int defensa = jugador.getDefensa();

        // NUEVAS STATS JUGADOR
        jugador.setHp_total(hp_total + aumentar_hp_total);

        if (hp_actual + recuperar_hp > jugador.getHp_total()) {
            jugador.setHp_actual(jugador.getHp_total());
        } else {
            jugador.setHp_actual(hp_actual + recuperar_hp);
        }

        jugador.setDanio(danio + aumentar_danio);
        jugador.setDefensa(defensa + aumentar_defensa);

    }
}
