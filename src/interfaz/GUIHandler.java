package interfaz;

import javax.swing.*;
import sistema.Personaje;
import utilidades.Cola;
import utilidades.Global;
import utilidades.Nodo;

/**
 *
 * @author emirs
 */
public class GUIHandler {

    private JTextArea colaZelda1;
    private JTextArea colaZelda2;
    private JTextArea colaZelda3;
    private JTextArea colaZeldaRefuerzo;
    private JTextArea[] colasZelda;

    JLabel zeldaId;
    JLabel zeldaName;
    JLabel zeldaPriority;
    JLabel zeldaLife;
    JLabel zeldaAbility;
    JLabel zeldaStrength;
    JLabel zeldaAgility;

    JLabel zeldaImage;

    private JTextArea colaSf1;
    private JTextArea colaSf2;
    private JTextArea colaSf3;
    private JTextArea colaSfRefuerzo;
    private JTextArea[] colasSf;

    private JTextField sfVictory;
    private JTextField zeldaVictory;

    JLabel sfId;
    JLabel sfName;
    JLabel sfPriority;
    JLabel sfLife;
    JLabel sfAbility;
    JLabel sfStrength;
    JLabel sfAgility;

    JLabel sfImage;
    
    JSlider time;
    JTextField timeVisual;

    private JLabel[] zCharacterInfo;

    private JLabel[] sfCharacterInfo;

    public GUIHandler(JTextArea[] colasZelda, JTextArea[] colasSf, JTextField zVictory, JTextField sfVictory) {
        this.colaZelda1 = colasZelda[0];
        this.colaZelda2 = colasZelda[1];
        this.colaZelda3 = colasZelda[2];
        this.colasZelda = colasZelda;
        this.colaZeldaRefuerzo = colasZelda[3];

        this.colaSf1 = colasSf[0];
        this.colaSf2 = colasSf[1];
        this.colaSf3 = colasSf[2];
        this.colasSf = colasSf;
        this.colaSfRefuerzo = colasSf[3];

        this.zeldaVictory = zVictory;
        this.sfVictory = sfVictory;

    }

    public GUIHandler(JLabel[] zCharacterInfo, JLabel[] sfCharacterInfo, JSlider time, JTextField timeVisual) {
        this.zeldaId = zCharacterInfo[0];
        this.zeldaName = zCharacterInfo[1];
        this.zeldaPriority = zCharacterInfo[2];
        this.zeldaLife = zCharacterInfo[3];
        this.zeldaAbility = zCharacterInfo[4];
        this.zeldaStrength = zCharacterInfo[5];
        this.zeldaAgility = zCharacterInfo[6];
        this.zeldaImage = zCharacterInfo[7];

        this.sfId = sfCharacterInfo[0];
        this.sfName = sfCharacterInfo[1];
        this.sfPriority = sfCharacterInfo[2];
        this.sfLife = sfCharacterInfo[3];
        this.sfAbility = sfCharacterInfo[4];
        this.sfStrength = sfCharacterInfo[5];
        this.sfAgility = sfCharacterInfo[6];
        this.sfImage = sfCharacterInfo[7];
        
        this.time = time;
        this.timeVisual = timeVisual;

    }

    public void formatQueues(Cola[] colaArr, String game) {

        JTextArea[] colaLabelArr = (game.equals("z")) ? this.colasZelda : this.colasSf;

        for (int i = 0; i < colaArr.length; i++) {
            String id;
            String outPut = "";
            Cola cola = colaArr[i];

            if (!cola.esVacio()) {

                JTextArea colaLabel = colaLabelArr[i];
                Nodo temp = cola.getFront();

                while (temp != null) {

                    Personaje chara = (Personaje) temp.getData();
                    id = chara.getId();

                    outPut += " " + id + " ";

                    temp = temp.getPnext();

                }

                colaLabel.setText(outPut);
            }
        }

    }

    public void formatQueue(Cola cola, String game, int index) {

        JTextArea[] colaLabelArr = (game.equals("z")) ? this.colasZelda : this.colasSf;

        String id;
        String outPut = "";

        if (!cola.esVacio()) {

            JTextArea colaLabel = colaLabelArr[index];
            Nodo temp = cola.getFront();

            while (temp != null) {

                Personaje chara = (Personaje) temp.getData();
                id = chara.getId();

                outPut += " " + id + " ";

                temp = temp.getPnext();

            }

            colaLabel.setText(outPut);
        }

    }

    public JTextField getSfVictory() {
        return sfVictory;
    }

    public JTextField getZeldaVictory() {
        return zeldaVictory;
    }

    public void setInfo() {
        this.zeldaId.setText(Global.getzFighter().getId());
        this.zeldaName.setText(Global.getzFighter().getName());
        this.zeldaPriority.setText(Integer.toString(Global.getzFighter().getPrioridad()));
        this.zeldaLife.setText(Integer.toString(Global.getzFighter().getLifePoints()));
        this.zeldaAbility.setText(Integer.toString(Global.getzFighter().getAbilities()));
        this.zeldaAgility.setText(Integer.toString(Global.getzFighter().getAgility()));
        this.zeldaStrength.setText(Integer.toString(Global.getzFighter().getStrenght()));
        this.zeldaImage.setIcon(Global.getzFighter().getImagen());

        this.sfId.setText(Global.getSfFighter().getId());
        this.sfName.setText(Global.getSfFighter().getName());
        this.sfPriority.setText(Integer.toString(Global.getSfFighter().getPrioridad()));
        this.sfLife.setText(Integer.toString(Global.getSfFighter().getLifePoints()));
        this.sfAbility.setText(Integer.toString(Global.getSfFighter().getAbilities()));
        this.sfAgility.setText(Integer.toString(Global.getSfFighter().getAgility()));
        this.sfStrength.setText(Integer.toString(Global.getSfFighter().getStrenght()));
        this.sfImage.setIcon(Global.getSfFighter().getImagen());
    }

    public JSlider getTime() {
        return time;
    }

    public JTextField getTimeVisual() {
        return timeVisual;
    }
    
    
}
