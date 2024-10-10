package Pizzeria;

import java.util.LinkedList;
import java.util.Queue;

public class Buffer_Ordini2 {
    private final Queue<int[]> ordini = new LinkedList<>();

    public synchronized void inserisciOrdine(int[] ordine) {
        ordini.add(ordine);
        notifyAll();
    }

    public synchronized int[] prelevaOrdine() {
        while (ordini.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        return ordini.poll();
    }
}
