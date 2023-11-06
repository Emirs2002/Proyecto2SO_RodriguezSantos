package interfaz;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import sistema.Personaje;
import utilidades.Cola;
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
    
//    JLabel zeldaId;
//    JLabel zeldaName;
//    JLabel zeldaPriority;
//    JLabel zeldaLife;
//    JLabel zeldaAbility;
//    JLabel zeldaStrenght;
//    JLabel zeldaAgility;
    
    private JTextArea colaSf1;
    private JTextArea colaSf2;
    private JTextArea colaSf3;
    private JTextArea colaSfRefuerzo;
    private JTextArea[] colasSf;
    
    private JTextField sfVictory;
    private JTextField zeldaVictory;
    
//    JLabel sfId;
//    JLabel sfName;
//    JLabel sfPriority;
//    JLabel sfLife;
//    JLabel sfAbility;
//    JLabel sfStrenght;
//    JLabel sfAgility;
    
//    JLabel zeldaImage;
//    JLabel sfImage;
    
    public GUIHandler(JTextArea[] colasZelda,JTextArea[] colasSf, JTextField zVictory,JTextField sfVictory){
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
    
    public void formatQueues(Cola[] colaArr, String game){
        
        JTextArea[] colaLabelArr = (game.equals("z")) ? this.colasZelda : this.colasSf;
                
        for (int i = 0; i < colaArr.length; i++) {
            String id;
            String outPut = "";
            Cola cola = colaArr[i];
            
            if(!cola.esVacio()){
                
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
    
    public void formatQueue(Cola cola, String game, int index){
        
        JTextArea[] colaLabelArr = (game.equals("z")) ? this.colasZelda : this.colasSf;
        
        String id;
        String outPut = "";
        
        if(!cola.esVacio()){
            
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
    
    
}
