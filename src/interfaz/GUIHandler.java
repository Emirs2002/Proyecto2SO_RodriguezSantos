package interfaz;

import java.awt.Image;
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

    JSpinner time;

    JTextField status;
    JLabel statusIcon;

    private JLabel[] zCharacterInfo;
    private JLabel[] sfCharacterInfo;
    
    public JLabel saleRefZ;
    public JLabel saleRefSF;
            

    public GUIHandler(JTextArea[] colasZelda, JTextArea[] colasSf, JTextField zVictory, JTextField sfVictory, JTextField status, JLabel statusIcon, JLabel saleRefZ,JLabel saleRefSF) {
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
        
        this.status = status;
        this.statusIcon = statusIcon;
        
        this.saleRefZ = saleRefZ;
        this.saleRefSF = saleRefSF;

    }

    public GUIHandler(JLabel[] zCharacterInfo, JLabel[] sfCharacterInfo, JSpinner time, JTextField status, JLabel statusIcon) {
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
       

        this.status = status;
        this.statusIcon = statusIcon;

    }

    public void formatQueues(Cola[] colaArr, String game) {

        JTextArea[] colaLabelArr = (game.equals("z")) ? this.colasZelda : this.colasSf;

        for (int i = 0; i < colaArr.length; i++) {
            String id;
            String outPut = "";
            Cola cola = colaArr[i];
            JTextArea colaLabel = colaLabelArr[i];

            if (!cola.esVacio()) {

                Nodo temp = cola.getFront();

                while (temp != null) {

                    Personaje chara = (Personaje) temp.getData();
                    id = chara.getId();

                    outPut += " " + id + " ";

                    temp = temp.getPnext();

                }

                colaLabel.setText(outPut);
            }else{
                colaLabel.setText(" ");
            }
        }

    }

    public void formatQueue(Cola cola, String game, int index) {

        JTextArea[] colaLabelArr = (game.equals("z")) ? this.colasZelda : this.colasSf;

        String id;
        String outPut = "";
        JTextArea colaLabel = colaLabelArr[index];

        if (!cola.esVacio()) {

            Nodo temp = cola.getFront();

            while (temp != null) {

                Personaje chara = (Personaje) temp.getData();
                id = chara.getId();

                outPut += " " + id + " ";

                temp = temp.getPnext();

            }

            colaLabel.setText(outPut);
        }else{
            colaLabel.setText(" ");
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

    public JSpinner getTime() {
        return time;
    }


    public ImageIcon makeIcon(String dir, int width, int height) {
        ImageIcon icon = new ImageIcon(new ImageIcon(getClass().getResource(dir)).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        return icon;
    }

    public void loser() {
        ImageIcon loser = makeIcon("/imagenes/gif_ko.gif", 140, 180);
        if ((Global.getWinner().getGame()).equals("z")) {
            ImageIcon winner = makeIcon("/imagenes/gif 4.gif", 140, 180);
            this.sfImage.setIcon(loser);
            this.zeldaImage.setIcon(winner);
        } else {
            ImageIcon winner = makeIcon("/imagenes/youwin.png", 140, 180);
            this.zeldaImage.setIcon(loser);
            this.sfImage.setIcon(winner);
        }

    }

    public void statusAI(String estado) {
        this.status.setText(estado);
        if (estado == "En combate") {
            ImageIcon icon = makeIcon("/imagenes/peleando.gif", 220, 180);
            this.statusIcon.setIcon(icon);
        } else if (estado == "Hay ganador!!") {
            ImageIcon icon = makeIcon("/imagenes/winner.gif", 140, 180);
            this.statusIcon.setIcon(icon);
        } else if (estado == "Empate") {
            ImageIcon icon = makeIcon("/imagenes/tie.gif", 220, 180);
            this.statusIcon.setIcon(icon);
        } else if (estado == "No hay combate") {
            ImageIcon icon = makeIcon("/imagenes/gif perder 2.gif", 200, 180);
            this.statusIcon.setIcon(icon);
        } else if (estado == "En espera") {
            ImageIcon icon = makeIcon("/imagenes/gif 6.gif", 140, 180);
        }

    }

}
