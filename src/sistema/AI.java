/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistema;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import sistema.*;
import test.Main;
import utilidades.*;

/**
 *
 * @author alesc
 */
public class AI extends Thread {

    private String state; //decidiendo, esperando, ejecutando
    private float time = 10; //tiempo que toma
    private int ZVicories;
    private int SFVictories;
    private double[] probs = {0.40, 0.27, 0.33}; //prob de ganar, prob empate, prob no combate
    private Semaphore mutex;
    private Personaje sfFighter;
    private Personaje zFighter;
    private Personaje winner;
    public int result; //hay ganador 1, empate 2, no hubo combate 3

    public AI(Semaphore mutex, Personaje sfFighter, Personaje zFighter, Personaje winner, int result) {
        this.mutex = mutex;
        this.zFighter = zFighter;
        this.sfFighter = sfFighter;
        this.winner = winner;
        this.result = result;
    }

    @Override
    public void run() {

        while (true) {
            //COMBATIR
            try {
                mutex.acquire();
                System.out.println("");
                System.out.println("----------------ENTRA PROCESADOR---------------");
                System.out.println("");

                System.out.println("EN COMBATE: ");
                if(zFighter != null  && sfFighter != null)
                {
                double dice = deciding();
                combat(dice);
                }
                System.out.println("Resultados: ");
                System.out.println("Zelda: " + zFighter);
                System.out.println("SF: " + sfFighter);
                System.out.println("Ganador: " + winner);
                mutex.release();

                sleep(2000);
            } catch (InterruptedException ex) {
                ex.printStackTrace(System.out);
            }
        }
    }


    public double deciding() {

        double dice = 0;
        System.out.println("DECIDIENDO");

        for (int i = 0; i < time - 1; i++) {
            dice = Math.random();
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(AI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return dice;
    }

    //Esta funcion determina el resultado del combate
    public void combat(double dice) {

        if (dice <= 0.67) {
            dice = Math.random();
            if (dice >= 0.49) {
                this.result = 1; //hubo un ganador
                System.out.println("HAY GANADOR");
                winner();
            } else {
                this.result = 2; //hubo un empate  
                System.out.println("EMPATE");
            }
        } else {
            this.result = 3;//no hubo combate
            System.out.println("NO HUBO COMBATE");
        }
        System.out.println("FINALIZO EL COMBATE");

    }

    //Esta funcion determina el ganador de un combate
    public void winner() {

        Personaje zelda = zFighter;
        Personaje SF = sfFighter;
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
            winner = zFighter;
            
        } else if (SFpoints > Zpoints) {
            System.out.println("");
            System.out.println("ganador sf: " + SF);
            winner = sfFighter;

        } else if (SFpoints == Zpoints) {
            if (random <= 0.50) {
                System.out.println("");
                System.out.println("ganador zelda: " + zelda);
                winner = zFighter;
                
            } else {
                System.out.println("");
                System.out.println("ganador sf: " + SF);
                winner = sfFighter;
            }
        }

    }

}
