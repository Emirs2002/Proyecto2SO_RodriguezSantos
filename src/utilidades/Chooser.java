/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilidades;

/**
 *
 * @author alesc
 */
public class Chooser {

    public int dice(int tries, double[] probs) {
        int result = 0;
        double random = 0;
        for (int i = 0; i < tries; i++) {
            random = Math.random();
            for (int j = 0; j < probs.length; j++) {
                if (probs[j] > random) {
                    result += 1;
                }
            }
        }

        return result;
    }

    public int dice(int tries, double prob) {
        int result = 0;
        double random = 0;
        for (int i = 0; i < tries; i++) {
            random = Math.random();

            if (prob > random) {
                result += 1;
            }

        }

        return result;
    }

}
