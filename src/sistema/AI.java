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
    private Cola[] zeldaColaArr;
    private Cola[] sfColaArr;
    private Lista winners;
    private Cola zRefuerzo;
    private Cola sfRefuerzo;
    

    public AI(Semaphore mutex, Cola[] zeldaColaArr, Cola[] sfColaArr, Cola zRefuerzo, Cola sfRefuerzo, Lista winners) {
        this.mutex = mutex;
        this.sfColaArr = sfColaArr;
        this.zeldaColaArr = zeldaColaArr;
        this.winners = winners;
        this.zRefuerzo = zRefuerzo;
        this.sfRefuerzo = sfRefuerzo;
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
            
                Cola zeldaCola = checkQueue(zeldaColaArr);
                Cola sfCola = checkQueue(sfColaArr);
                
                if(zeldaCola != null && sfCola != null){
                    
                    System.out.println("EN COMBATE: ");
                    combat(zeldaCola, sfCola);
                }else{
                    System.out.println("No hay suficientes luchadores creados");
                }
                
                mutex.release();
                
                sleep(2000);
            } catch (InterruptedException ex) {
                ex.printStackTrace(System.out);
            }
        }
    }

    //chequear todas las colas hasta que haya una no vacia
    public Cola checkQueue(Cola[] colaArr){
        Cola cola = null;
        
        for (int i = 0; i < colaArr.length; i++) {
            boolean isEmpty = colaArr[i].esVacio();
            if(!isEmpty){
                cola = colaArr[i];
                break;
            }
        }
        
        return cola;
    }
    
    //Esta funcion determina el resultado del combate
    public void combat(Cola zelda, Cola SF) {
        
            
        double dice = 0;
        dice = Math.random();
        System.out.println(dice);
        if (dice <= 0.67) {
            dice = Math.random();
            if (dice >= 0.49) {
                this.results = 1; //hubo un ganador
                System.out.println("HAY GANADOR");
                winner(zelda,  SF);
            } else {
                this.results = 3; //hubo un empate  
                System.out.println("EMPATE");
                tie(zelda, SF);
            }
        } else {
            this.results = 2;//no hubo combate
            System.out.println("NO HUBO COMBATE");
            noCombat(zelda, SF);
        }
        System.out.println("FINALIZO EL COMBATE");


        System.out.println("cola zelda:");
        System.out.println("");
        zelda.mostrarCola();
        System.out.println("cola sf:");
        System.out.println("");
        SF.mostrarCola();

        
    }
    
    //Esta funcion determina el ganador de un combate
    public void winner(Cola zeldaCola, Cola SFCola) {

        Character zelda = (Character) zeldaCola.desencolar();
        Character SF = (Character) SFCola.desencolar();
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

    public void tie(Cola zeldaCola, Cola SFCola) {
        Character zelda = (Character) zeldaCola.desencolar();
        Character SF = (Character) SFCola.desencolar();
        
        zeldaCola.encolar(zelda);
        SFCola.encolar(SF);
        
    }

    public void noCombat(Cola zeldaCola, Cola SFCola) {
        Character zelda = (Character) zeldaCola.desencolar();
        Character SF = (Character) SFCola.desencolar();
        
        this.zRefuerzo.encolar(zelda);
        this.sfRefuerzo.encolar(SF);

    }
}
