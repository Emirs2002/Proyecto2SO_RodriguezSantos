/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sistema;

import utilidades.Chooser;

/**
 *
 * @author alesc
 */
public class AI extends Thread {

    private String state; //decidiendo, esperando, ejecutando
    private Character Zelda;
    private Character SF;
    private float time; //tiempo que toma
    private int ZVicories;
    private int SFVictories;
    public int results; //hay ganador 1, empate 2, no hubo combate 3
    private double[] probs = {0.40, 0.27, 0.33}; //prob de ganar, prob empate, prob no combate

    @Override
    public void run() {

        //cargar 10 procesos de cada empresa en el sistema
        while (true) {
            try {
                sleep(2000);
            } catch (InterruptedException ex) {
                ex.printStackTrace(System.out);
            }
        }
    }

    //funcion determinar result
    public int determineResult() {
        double dice = 0;
        dice = Math.random();
        if(dice >= 0.40)
        {
            this.results = 1;
        }else if(dice < 0.4 && dice >= 0.33)
        {
            this.results = 3;            
        }else if(dice < 0.33 && dice >= 0.27)
        {
            this.results = 2;
        }
        
        return this.results;
    }

    //funcion determinar ganador
    //
}
