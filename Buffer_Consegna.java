package Pizzeria;

import java.util.LinkedList;
import java.util.Queue;

public class Buffer_Consegna {
    private final Queue<String> pizze = new LinkedList<>();

    public synchronized void inserisciPizza(String pizza) {
        pizze.add(pizza);
        notifyAll();
    }

    public synchronized String prelevaPizza() throws InterruptedException {
        while (pizze.isEmpty()) {
            wait();
        }
        return pizze.poll();
    }
}
