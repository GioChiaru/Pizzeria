package Pizzeria;

public class Cameriere implements Runnable {
    private final Buffer_Ordini bufferOrdine;
    private final Buffer_Ordini2 bufferOrdine2;
    private final Buffer_Consegna bufferConsegna;

    public Cameriere(Buffer_Ordini bufferOrdine, Buffer_Ordini2 bufferOrdine2, Buffer_Consegna bufferConsegna) {
        this.bufferOrdine = bufferOrdine;
        this.bufferOrdine2 = bufferOrdine2;
        this.bufferConsegna = bufferConsegna;
    }

    @Override
    public void run() {
        while (true) {
            int[] ordine = bufferOrdine.prelevaOrdine();
            System.out.println(Thread.currentThread().getName() + " ha prelevato l'ordine di " + ordine[0] + " pizze.");
            bufferOrdine2.inserisciOrdine(ordine);
            try {
                int pizzeDaConsegnare = ordine[0];
                for (int i = 0; i < pizzeDaConsegnare; i++) {
                    String pizza = bufferConsegna.prelevaPizza();
                    System.out.println(Thread.currentThread().getName() + " ha consegnato una pizza: " + pizza);
                    // Simula la consegna della pizza a un tavolo casuale
                    int tavoloCasuale = (int)(Math.random() * 20);
                    System.out.println(Thread.currentThread().getName() + " sta consegnando una pizza al Tavolo " + tavoloCasuale);
                    // Invoca il metodo riceviPizze sul tavolo casuale
                    ((Tavolo)Thread.getAllStackTraces().keySet().toArray()[tavoloCasuale]).riceviPizze(pizzeDaConsegnare);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
