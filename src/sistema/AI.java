/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistema;

import java.util.concurrent.Semaphore;
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
            
            
            try {
                sleep(2000);
            } catch (InterruptedException ex) {
                ex.printStackTrace(System.out);
            }
        }
    }

    //Esta funcio determina el resultado del combate
    public int determineResult() {
        double dice = 0;
        dice = Math.random();
        if(dice >= 0.40)
        {
            this.results = 1; //hubo un ganador
        }else if(dice < 0.4 && dice >= 0.33)
        {
            this.results = 3; //hubo un empate            
        }else if(dice < 0.33 && dice >= 0.27)
        {
            this.results = 2;//no hubo combate
        }
        
        return this.results;
        
    }

    //Esta funcion determina el ganador de un combate
    public void determineWinner()
    {
        Character zelda = (Character)Z1.getFront().getData();
        Character SF = (Character)SF1.getFront().getData();        
        int Zpoints = 0;
        int SFpoints =  0;
        
        if(zelda.getAbilities() > SF.getAbilities()){
            Zpoints += 1;
        }else if(zelda.getAbilities() < SF.getAbilities()){
            SFpoints += 1;
        }

        if(zelda.getAgility() > SF.getAgility()){
            Zpoints += 1;
        }else if(zelda.getAgility() < SF.getAgility()){
            SFpoints += 1;
        }
        
        if(zelda.getLifePoints() > SF.getLifePoints()){
            Zpoints += 1;
        }else if(zelda.getLifePoints() < SF.getLifePoints()){
            SFpoints += 1;
        }
        
        if(zelda.getStrenght() > SF.getStrenght()){
            Zpoints += 1;
        }else if(zelda.getStrenght() < SF.getStrenght()){
            SFpoints += 1;
        }
        
        if(Zpoints > SFpoints){
            winners.addAtEnd(new Nodo(zelda));
            SF1.desencolar();
        }else{
            winners.addAtEnd(new Nodo(SF));
            Z1.desencolar();
        }
        
        
    }
    
}
