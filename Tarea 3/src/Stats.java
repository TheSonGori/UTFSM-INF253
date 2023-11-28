import java.util.concurrent.ThreadLocalRandom;

public class Stats {
    private int min;
    private int max;

    // Constructor
    public Stats(int min, int max) {
        this.min = min;
        this.max = max;
    }

    // Min
    public void setMin(int min) {
        this.min = min;
    }

    public int getMin() {
        return min;
    }

    // Max
    public void setMax(int max) {
        this.max = max;
    }

    public int getMax() {
        return max;
    }

    // Funciones
    public int rangoValores() {
        /*
         * ∗ Retorna un numero aleatorio del rango establecido
         * ∗
         * * sin parametros:
         * ∗
         * ∗ return randomNum: numero aleatorio del rango
         */

        ThreadLocalRandom tlr = ThreadLocalRandom.current();
        int randomNum = tlr.nextInt(min, max + 1);
        return randomNum;
    }
}