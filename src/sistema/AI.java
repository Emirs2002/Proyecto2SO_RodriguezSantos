/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistema;

import interfaz.GUIHandler;
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
    private int time = 10; //tiempo que toma
    private int ZVicories;
    private int SFVictories;
    private double[] probs = {0.40, 0.27, 0.33}; //prob de ganar, prob empate, prob no combate
    private Semaphore mutex;
    private String status;

    private GUIHandler gui;

    public AI(Semaphore mutex, GUIHandler gui) {
        this.mutex = mutex;
        this.gui = gui;
    }

    @Override
    public void run() {

        gui.getTime().setValue(time);
        while (true) {
            //COMBATIR
            try {
                mutex.acquire(1);
                System.out.println("");
                System.out.println("----------------ENTRA PROCESADOR---------------");
                System.out.println("");

                System.out.println("EN COMBATE: ");
                time = (Integer)gui.getTime().getValue();
                if (Global.getzFighter() != null && Global.getSfFighter() != null) {
                    gui.setInfo();
                    status = "En combate";
                    gui.statusAI(status);
                    double dice = deciding();
                    combat(dice);
                    gui.statusAI(status);
                } else {
                    System.out.println("No hay luchadores suficientes");
                }

                System.out.println("Resultados: ");
                System.out.println("Zelda: " + Global.getzFighter());
                System.out.println("SF: " + Global.getSfFighter());
                System.out.println("Ganador: " + Global.getWinner());
                mutex.release();
                sleep(1000);
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
                status = "Hay ganador!!";
                Global.setResult(1); //hubo un ganador
                System.out.println("HAY GANADOR");
                winner();
            } else {
                status = "Empate";
                Global.setResult(2); //hubo un empate 
                System.out.println("EMPATE");
            }
        } else {
            status = "No hay combate";
            Global.setResult(3); //no hubo combate
            System.out.println("NO HUBO COMBATE");
        }
        System.out.println("FINALIZO EL COMBATE");

    }

    //Esta funcion determina el ganador de un combate
    public void winner() {

        Personaje zelda = Global.getzFighter();
        Personaje SF = Global.getSfFighter();

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
            Global.setWinner(zelda);

        } else if (SFpoints > Zpoints) {
            System.out.println("");
            System.out.println("ganador sf: " + SF);
            Global.setWinner(SF);

        } else if (SFpoints == Zpoints) {
            if (random <= 0.50) {
                System.out.println("");
                System.out.println("ganador zelda: " + zelda);
                Global.setWinner(zelda);

            } else {
                System.out.println("");
                System.out.println("ganador sf: " + SF);
                Global.setWinner(SF);
            }
        }
        gui.loser();
    }

}
