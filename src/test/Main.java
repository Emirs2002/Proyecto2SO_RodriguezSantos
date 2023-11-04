package test;

/**
 *
 * @author emirs
 */
import interfaz.Screen;
import java.util.concurrent.Semaphore;
import sistema.*;
import utilidades.*;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Screen screen = new Screen();
        screen.setVisible(true);
        screen.setLocationRelativeTo(null);

        Cola zelda1 = new Cola();
        Cola zelda2 = new Cola();
        Cola zelda3 = new Cola();
        Cola zeldaRefuerzo = new Cola();
        Cola[] zeldaArrCola = {zelda1, zelda2, zelda3};

        Cola SF1 = new Cola();
        Cola SF2 = new Cola();
        Cola SF3 = new Cola();
        Cola SFRefuerzo = new Cola();
        Cola[] sfArrCola = {SF1, SF2, SF3};

        Lista winners = new Lista();
        Personaje sfFighter = null;
        Personaje zFighter = null;
        Personaje winner = null;
        int result = 0; // ganador 1, empate 2, no combate 3

        Semaphore mutex = new Semaphore(1);

        Admin so = new Admin(mutex, zeldaArrCola, sfArrCola, zeldaRefuerzo, SFRefuerzo, sfFighter, zFighter, winner, result, winners);
        AI procesador = new AI(mutex, sfFighter, zFighter, winner, result);

        so.start();
        procesador.start();

    }

}
