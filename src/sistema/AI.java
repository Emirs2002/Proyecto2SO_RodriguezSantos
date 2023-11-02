/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistema;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistema.Character;
import utilidades.*;

/**
 *
 * @author alesc
 */
public class AI extends Thread {

    private String state; //decidiendo, esperando, ejecutando
    private float time; //tiempo que toma
    private int ZVicories;
    private int SFVictories;
    public int results; //hay ganador 1, empate 2, no hubo combate 3
    private double[] probs = {0.40, 0.27, 0.33}; //prob de ganar, prob empate, prob no combate
    private Semaphore mutex;
    private Cola Z1;
    private Cola ZRefuerzo;
    private Cola SF1;
    private Cola SFRefuerzo;
    private Lista winners;

    public AI(Semaphore mutex, Cola Z1, Cola ZRefuerzo, Cola SF1, Cola SFRefuerzo, Lista winners) {
        this.mutex = mutex;
        this.Z1 = Z1;
        this.ZRefuerzo = ZRefuerzo;
        this.SF1 = SF1;
        this.SFRefuerzo = SFRefuerzo;
        this.winners = winners;
    }

    @Override
    public void run() {

        while (true) {
            //COMBATIR
            if (Z1.esVacio() || SF1.esVacio()) {
                System.out.println("ALGUNA COLA VACIA");
            } else {
                System.out.println("EN COMBATE: ");
                combat();
            }
            try {
                sleep(2000);
            } catch (InterruptedException ex) {
                ex.printStackTrace(System.out);
            }
        }
    }

    //Esta funcion determina el resultado del combate
    public void combat() {
        try {
            mutex.acquire();
            double dice = 0;
            dice = Math.random();
            System.out.println(dice);
            if (dice <= 0.67) {
                dice = Math.random();
                if (dice >= 0.49) {
                    this.results = 1; //hubo un ganador
                    System.out.println("HAY GANADOR");
                    winner();
                } else {
                    this.results = 3; //hubo un empate  
                    System.out.println("EMPATE");
                    tie();
                }
            } else {
                this.results = 2;//no hubo combate
                System.out.println("NO HUBO COMBATE");
                noCombat();
            }
            System.out.println("FINALIZO EL COMBATE");
            mutex.release();
            
            System.out.println("cola zelda:");
            System.out.println("");
            Z1.mostrarCola();
            System.out.println("cola sf:");
            System.out.println("");
            SF1.mostrarCola();

        } catch (InterruptedException ex) {
            Logger.getLogger(AI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Esta funcion determina el ganador de un combate
    public void winner() {

        Character zelda = (Character) Z1.desencolar();
        Character SF = (Character) SF1.desencolar();
        int Zpoints = 0;
        int SFpoints = 0;
        double random = Math.random();

        if (zelda.getAbilities() > SF.getAbilities()) {
            Zpoints += 1;
        } else if (zelda.getAbilities() < SF.getAbilities()) {
            SFpoints += 1;
        }

        if (zelda.getAgility() > SF.getAgility()) {
            Zpoints += 1;
        } else if (zelda.getAgility() < SF.getAgility()) {
            SFpoints += 1;
        }

        if (zelda.getLifePoints() > SF.getLifePoints()) {
            Zpoints += 1;
        } else if (zelda.getLifePoints() < SF.getLifePoints()) {
            SFpoints += 1;
        }

        if (zelda.getStrenght() > SF.getStrenght()) {
            Zpoints += 1;
        } else if (zelda.getStrenght() < SF.getStrenght()) {
            SFpoints += 1;
        }

        if (Zpoints > SFpoints) {
            System.out.println("");
            System.out.println("ganador zelda: " + zelda);
            winners.addAtEnd(new Nodo(zelda));
            
            Character loser = SF;
        } else if (SFpoints > Zpoints) {
            System.out.println("");
            System.out.println("ganador sf: " + SF);
            winners.addAtEnd(new Nodo(SF));
            
            Character loser = zelda;
        } else if (SFpoints == Zpoints) {
            if (random <= 0.50) {
                System.out.println("");
                System.out.println("ganador zelda: " + zelda);
                winners.addAtEnd(new Nodo(zelda));
                Character loser = SF;
            } else {
                System.out.println("");
                System.out.println("ganador sf: " + SF);
                winners.addAtEnd(new Nodo(SF));
                Character loser = zelda;
            }
        }
        
    }

    public void tie() {
        Character zelda = (Character) Z1.desencolar();
        Character SF = (Character) SF1.desencolar();
        Z1.encolar(zelda);
        SF1.encolar(SF);
        
    }

    public void noCombat() {
        Character zelda = (Character) Z1.desencolar();
        Character SF = (Character) SF1.desencolar();
        
        ZRefuerzo.encolar(zelda);
        SFRefuerzo.encolar(SF);

    }
}
