package Pizzeria;

public class Main {
    public static void main(String[] args) {
        String[] pizze = {"Margherita", "Marinara", "Patatine e Wurstel", "Napoli"};
        
        // Buffers condivisi
        Buffer_Ordini bufferOrdine = new Buffer_Ordini();
        Buffer_Ordini2 bufferOrdine2 = new Buffer_Ordini2();
        Buffer_Pizza bufferPizza = new Buffer_Pizza();
        Buffer_Consegna bufferConsegna = new Buffer_Consegna();

        // Istanze
        Tavolo[] tavoli = new Tavolo[20];
        for (int i = 0; i < 20; i++) {
            tavoli[i] = new Tavolo(bufferOrdine, pizze, i + 1, bufferConsegna);
            new Thread(tavoli[i]).start();
        }

        Cameriere[] camerieri = new Cameriere[3];
        for (int i = 0; i < 3; i++) {
            camerieri[i] = new Cameriere(bufferOrdine, bufferOrdine2, bufferConsegna);
            new Thread(camerieri[i], "Cameriere-" + (i + 1)).start();
        }

        Pizzaiolo pizzaiolo = new Pizzaiolo(bufferOrdine2, bufferPizza, pizze);
        new Thread(pizzaiolo).start();
    }
}
