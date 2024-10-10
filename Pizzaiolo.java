package Pizzeria;

public class Pizzaiolo implements Runnable {
    private final Buffer_Ordini2 bufferOrdine2;
    private final Buffer_Pizza bufferPizza;
    private final String[] pizze;

    public Pizzaiolo(Buffer_Ordini2 bufferOrdine2, Buffer_Pizza bufferPizza, String[] pizze) {
        this.bufferOrdine2 = bufferOrdine2;
        this.bufferPizza = bufferPizza;
        this.pizze = pizze;
    }

    @Override
    public void run() {
        while (true) {
            int[] ordine = bufferOrdine2.prelevaOrdine();
            int numeroPizze = ordine[0];
            for (int i = 0; i < numeroPizze; i++) {
                String pizza = pizze[i % pizze.length];
                bufferPizza.inserisciPizza(pizza);
                System.out.println("Pizzaiolo ha sfornato una pizza: " + pizza);
            }
        }
    }
}
