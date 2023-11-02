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
                
                
//        Admin so = new Admin();
//        
//        so.start();
        
        
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
       
       Semaphore mutex = new Semaphore(1);
       
       Admin so = new Admin(mutex, zeldaArrCola, sfArrCola, zeldaRefuerzo,SFRefuerzo);
       AI procesador = new AI(mutex,zeldaArrCola, sfArrCola, zeldaRefuerzo, SFRefuerzo, winners);
        
       so.start();
       procesador.start();

    }

}
