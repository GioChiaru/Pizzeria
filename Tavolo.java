package Pizzeria;

import java.util.Random;

public class Tavolo implements Runnable {
    private final Buffer_Ordini bufferOrdine;
    private final String[] pizze;
    private static final Random random = new Random();
    private final int numeroTavolo;
    private final Buffer_Consegna bufferConsegna;

    public Tavolo(Buffer_Ordini bufferOrdine, String[] pizze, int numeroTavolo, Buffer_Consegna bufferConsegna) {
        this.bufferOrdine = bufferOrdine;
        this.pizze = pizze;
        this.numeroTavolo = numeroTavolo;
        this.bufferConsegna = bufferConsegna;
    }

    @Override
    public void run() {
        while (true) {
            int numeroPizze = random.nextInt(8) + 1;
            bufferOrdine.inserisciOrdine(new int[] {numeroPizze});
            System.out.println("Tavolo " + numeroTavolo + " ha ordinato " + numeroPizze + " pizze.");
            try {
                Thread.sleep(random.nextInt(5000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    public void riceviPizze(int numeroPizze) {
        System.out.println("Tavolo " + numeroTavolo + " ha ricevuto tutte le " + numeroPizze + " pizze.");
    }
}
