package test;

/**
 *
 * @author emirs
 */
import java.util.concurrent.Semaphore;
import sistema.*;
import utilidades.Chooser;
import utilidades.*;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
       Cola zelda1 = new Cola();
       Cola zelda2 = new Cola();
       Cola zelda3 = new Cola();
       Cola zeldaRefuerzo = new Cola();

       Cola SF1 = new Cola();
       Cola SF2 = new Cola();
       Cola SF3 = new Cola();
       Cola SFRefuerzo = new Cola();
       
       Lista winners = new Lista();
       
       Semaphore mutex = new Semaphore(1);
       
       Admin so = new Admin(zelda1, zelda2, zelda3, zeldaRefuerzo, SF1, SF2, SF3,SFRefuerzo, mutex);
       AI procesador = new AI(mutex,zelda1, zeldaRefuerzo, SF1, SFRefuerzo, winners);
        
       so.start();
       procesador.start();

    }

}
