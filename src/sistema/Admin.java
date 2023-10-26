package sistema;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import utilidades.Chooser;

/**
 *
 * @author emirs
 */
public class Admin extends Thread {

    private String[] zeldaExcepArr = {"Link", "Zelda", "Ganondorf"};
    private String[] zeldaAvegArr = {"Impa", "Mipha", "Daruk", "Sidon"};
    private String[] zeldaLowArr = {"Kass", "Riju", "Yunobo"};

    //private cola1 Zelda
    //private cola2 Zelda
    //private cola3 Zelda
    //private colaRefuerzo Zelda
    //private cola1 SF
    //private cola2 SF
    //private cola3 SF
    //private colaRefuerzo SF
    private Semaphore mutex;
    private Chooser chooser;
    private double[] abilityProbArr = {0.6, 0.7, 0.5, 0.4};
    private int cycleCounter;
    

    public Admin() {
        this.chooser = new Chooser();
        this.cycleCounter = 0;
    }

    @Override
    public void run() {
        while (true) {
            
            checkCycle();
            
            try {
                sleep(2000);
            } catch (InterruptedException ex) {
                ex.printStackTrace(System.out);
            }
        }
    }
    
    public void checkCycle(){
        
        //evaluar si han pasado dos ciclos de revision
        if(this.cycleCounter < 2){
            
                //revisa
                System.out.println("");
                System.out.println("revisa estado sistema");
                this.cycleCounter++;
                
            }else{
                
            //calcular probabilidad de que se cree un nuevo psj
                int createCharacter = chooser.dice(1,0.8);
                System.out.println("crear psj: " + createCharacter);
                        
                if(createCharacter == 1){
                  
                  //CREAR PERSONAJES
                  
                    //crear zelda
                    int resultZ = chooser.dice(1, abilityProbArr);
                    System.out.println("resultado dado zelda:" + resultZ);
                    Process characterZelda = chooseZeldaArray(resultZ);
                    
                    System.out.println("Personaje creado!!");
                    System.out.println(characterZelda);
                    
                    //crear SF6
                    
                    
                    //encolar ambos
                    queuingCharacters(characterZelda,characterZelda);
                } 
                
                this.cycleCounter = 0;
            }
    }
            

    public Process chooseZeldaArray(int result) {
        Process psj = null;
        ImageIcon icon;

        if (result == 0) { //deficientes
            int num = getRandomNum(0, (zeldaLowArr.length) - 1);
            switch (zeldaLowArr[num]) {
                case "Kass":
                    System.out.println("kass");
                    icon = new ImageIcon(getClass().getResource("/imagenes/ZELDA/kass.gif"));
                    psj = new Process(3, "Kass", icon, getRandomNum(15, 30), getRandomNum(30, 45), getRandomNum(10, 20), getRandomNum(20, 30));
                    break;
                case "Riju":
                    System.out.println("riju");
                    icon = new ImageIcon(getClass().getResource("/imagenes/ZELDA/riju.gif"));
                    psj = new Process(3, "Riju", icon, getRandomNum(20, 25), getRandomNum(25, 50), getRandomNum(12, 35), getRandomNum(22, 37));
                    break;
                case "Yunobo":
                    System.out.println("yunobo");
                    icon = new ImageIcon(getClass().getResource("/imagenes/ZELDA/yunobo.gif"));
                    psj = new Process(3, "Yunobo", icon, getRandomNum(14, 25), getRandomNum(10, 27), getRandomNum(25, 48), getRandomNum(16, 20));
                    break;
            }

        } else if (result >= 1 && result <= 3) { //promedio
            int num = getRandomNum(0, (zeldaAvegArr.length) - 1);
            switch (zeldaAvegArr[num]) {
                case "Impa":
                    System.out.println("Impa");
                    icon = new ImageIcon(getClass().getResource("/imagenes/ZELDA/impa.gif"));
                    psj = new Process(2, "Impa", icon, getRandomNum(55, 70), getRandomNum(47, 64), getRandomNum(56, 70), getRandomNum(45, 57));
                    break;
                case "Mipha":
                    System.out.println("Mipha");
                    icon = new ImageIcon(getClass().getResource("/imagenes/ZELDA/mipha.gif"));
                    psj = new Process(2, "Mipha", icon, getRandomNum(66, 80), getRandomNum(43, 55), getRandomNum(42, 54), getRandomNum(48, 65));
                    break;
                case "Daruk":
                    System.out.println("Daruk");
                    icon = new ImageIcon(getClass().getResource("/imagenes/ZELDA/daruk.png"));
                    psj = new Process(2, "Daruk", icon, getRandomNum(45, 50), getRandomNum(70, 80), getRandomNum(85, 95), getRandomNum(35, 45));
                    break;
                case "Sidon":
                    System.out.println("Sidon");
                    icon = new ImageIcon(getClass().getResource("/imagenes/ZELDA/sidon.png"));
                    psj = new Process(2, "Sidon", icon, getRandomNum(40, 56), getRandomNum(45, 56), getRandomNum(30, 45), getRandomNum(58, 70));
                    break;
            }

        } else if (result >= 4) { //excepcionales
            int num = getRandomNum(0, (zeldaExcepArr.length) - 1);

            switch (zeldaExcepArr[num]) {
                case "Link":
                    System.out.println("link");
                    icon = new ImageIcon(getClass().getResource("/imagenes/ZELDA/link.gif"));
                    psj = new Process(1, "Link", icon, getRandomNum(69, 100), getRandomNum(80, 100), getRandomNum(85, 100), getRandomNum(90, 100));
                    break;
                case "Zelda":
                    System.out.println("zelda");
                    icon = new ImageIcon(getClass().getResource("/imagenes/ZELDA/zelda.gif"));
                    psj = new Process(1, "Zelda", icon, getRandomNum(85, 100), getRandomNum(80, 100), getRandomNum(73, 100), getRandomNum(75, 100));
                    break;
                case "Ganondorf":
                    System.out.println("ganon");
                    icon = new ImageIcon(getClass().getResource("/imagenes/ZELDA/ganondorf.gif"));
                    psj = new Process(1, "Ganondorf", icon, getRandomNum(90, 100), getRandomNum(85, 100), getRandomNum(88, 100), getRandomNum(89, 100));
                    break;
            }
        }

        return psj;
    }

    public Process chooseSFArray(int result){
        //hacer codigo como el de arriba
        return null;
    }
    
    public void queuingCharacters(Process psjZ, Process psjSF){
        System.out.println("personajes se encolan");
        //encolar los personajes
    }
    
    public int getRandomNum(int min, int max) {
        int num = min + (int) (Math.random() * ((max - min) + 1));
        return num;
    }

}
