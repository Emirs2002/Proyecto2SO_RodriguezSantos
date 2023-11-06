/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;

import sistema.Personaje;

/**
 *
 * @author emirs
 */
public class Global {

    private static Personaje sfFighter = null;
    private static Personaje zFighter = null;
    private static Personaje winner = null;
    private static int result = 0; // ganador 1, empate 2, no combate 3
    private static int victoriesZelda = 0; 
    private static int victoriesSF = 0; 
    
    /**
     * @return the sfFighter
     */
    public static Personaje getSfFighter() {
        return Global.sfFighter;
    }

    /**
     * @param sfFighter the sfFighter to set
     */
    public static void setSfFighter(Personaje sfFighter) {
        Global.sfFighter = sfFighter;
    }

    /**
     * @return the zFighter
     */
    public static Personaje getzFighter() {
        return Global.zFighter;
    }

    /**
     * @param zFighter the zFighter to set
     */
    public static void setzFighter(Personaje zFighter) {
        Global.zFighter = zFighter;
    }

    /**
     * @return the winner
     */
    public static Personaje getWinner() {
        return Global.winner;
    }

    /**
     * @param winner the winner to set
     */
    public static void setWinner(Personaje winner) {
        Global.winner = winner;
    }

    /**
     * @return the result
     */
    public static int getResult() {
        return Global.result;
    }

    /**
     * @param result the result to set
     */
    public static void setResult(int result) {
        Global.result = result;
    }

    public static int getVictoriesZelda() {
        return victoriesZelda;
    }

    public static void sumVictoriesZelda() {
        Global.victoriesZelda++;
    }

    public static int getVictoriesSF() {
        return victoriesSF;
    }

    public static void sumVictoriesSF() {
        Global.victoriesSF++;
    }
    
    
}
