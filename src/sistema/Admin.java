package sistema;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import utilidades.Chooser;
import utilidades.Cola;

/**
 *
 * @author emirs
 */
public class Admin extends Thread {

    private String[] zeldaExcepArr = {"Link", "Zelda", "Ganondorf"};
    private String[] zeldaAvegArr = {"Impa", "Mipha", "Daruk", "Sidon"};
    private String[] zeldaLowArr = {"Kass", "Riju", "Yunobo"};
    private Cola cola1Zelda = new Cola();
    private Cola cola2Zelda = new Cola();
    private Cola cola3Zelda = new Cola();
    private Cola colaRefuerzoZelda = new Cola();

    private String[] sfExcepArr = {"Ryu", "Zangief", "Chun-Li", "Ken"};
    private String[] sfAvegArr = {"Jurii", "Honda", "Blanka"};
    private String[] sfLowArr = {"Guile", "Dee Jay", "Cammy"};

    private Cola cola1SF = new Cola();
    private Cola cola2SF = new Cola();
    private Cola cola3SF = new Cola();
    private Cola colaRefuerzoSF = new Cola();
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
        
        //cargar 10 procesos de cada empresa en el sistema
        int count = 0;
        while (count < 10) {
            createCharacters();
            count++;
        }
        
        while (true) {

            checkCycle();

            try {
                sleep(2000);
            } catch (InterruptedException ex) {
                ex.printStackTrace(System.out);
            }
        }
    }

    public void checkCycle() {

        //evaluar si han pasado dos ciclos de revision
        if (this.cycleCounter < 2) {

            //revisa
            System.out.println("");
            System.out.println("revisa estado sistema");
            this.cycleCounter++;

        } else {

            //calcular probabilidad de que se cree un nuevo psj
            int create = chooser.dice(1, 0.8);
            System.out.println("crear psj: " + create);
            
                if (create == 1) {
                    
                    createCharacters();                    
                   
                }else{
                    System.out.println("");
                    System.out.println("no se crea na");
                }
                
            this.cycleCounter = 0;
        }
    }

    public void createCharacters() {

        //crear zelda
        int resultZ = chooser.dice(1, abilityProbArr);
        //System.out.println("resultado dado zelda:" + resultZ);
        Character characterZelda = chooseZeldaArray(resultZ);
        //System.out.println(characterZelda);
        //System.out.println("COLA ZELDA PRIORIDAD 1:");
        //this.cola3Zelda.mostrarCola();

        //crear SF
        int resultSF = chooser.dice(1, abilityProbArr);
        //System.out.println("resultado dado SF:" + resultSF);
        Character characterSF = chooseSFArray(resultSF);
        //System.out.println(characterSF);
        //System.out.println("COLA SF PRIORIDAD 3: ");
        //this.cola2SF.mostrarCola();

    }

    public Character chooseSFArray(int result) {
        Character psj = null;
        ImageIcon icon;

        if (result == 0) { //deficientes
            int num = getRandomNum(0, (sfLowArr.length) - 1);
            switch (sfLowArr[num]) {
                case "Guile":
                    //System.out.println("Guile");
                    icon = new ImageIcon(getClass().getResource("/imagenes/SF/guile.gif"));
                    psj = new Character(3, "Guile", icon, getRandomNum(15, 25), getRandomNum(30, 45), getRandomNum(10, 20), getRandomNum(20, 28), "sf");
                    break;
                case "Dee Jay":
                    //System.out.println("Dee Jay");
                    icon = new ImageIcon(getClass().getResource("/imagenes/SF/deejay1.gif"));
                    psj = new Character(3, "Dee Jay", icon, getRandomNum(20, 25), getRandomNum(17, 25), getRandomNum(21, 35), getRandomNum(22, 37), "sf");
                    break;
                case "Cammy":
                    //System.out.println("Cammy");
                    icon = new ImageIcon(getClass().getResource("/imagenes/SF/cammy.gif"));
                    psj = new Character(3, "Cammy", icon, getRandomNum(19, 30), getRandomNum(25, 35), getRandomNum(18, 24), getRandomNum(20, 26), "sf");
                    break;
            }
            this.cola3SF.encolar(psj);
            
        } else if (result >= 1 && result <= 3) { //promedio
            int num = getRandomNum(0, (sfAvegArr.length) - 1);
            switch (sfAvegArr[num]) {
                case "Jurii":
                    //System.out.println("Jurii");
                    icon = new ImageIcon(getClass().getResource("/imagenes/SF/juri.gif"));
                    psj = new Character(2, "Jurii", icon, getRandomNum(55, 68), getRandomNum(50, 64), getRandomNum(50, 60), getRandomNum(40, 60), "sf");
                    break;
                case "Honda":
                    //System.out.println("Honda");
                    icon = new ImageIcon(getClass().getResource("/imagenes/SF/e-honda.gif"));
                    psj = new Character(2, "Honda", icon, getRandomNum(50, 65), getRandomNum(47, 68), getRandomNum(42, 65), getRandomNum(34, 50), "sf");
                    break;
                case "Blanka":
                    //System.out.println("Blanka");
                    icon = new ImageIcon(getClass().getResource("/imagenes/SF/blanka.gif"));
                    psj = new Character(2, "Blanka", icon, getRandomNum(45, 50), getRandomNum(70, 75), getRandomNum(65, 85), getRandomNum(50, 60), "sf");
                    break;
            }
            this.cola2SF.encolar(psj);

        } else if (result >= 4) { //excepcionales
            int num = getRandomNum(0, (sfExcepArr.length) - 1);

            switch (sfExcepArr[num]) {
                case "Ryu":
                    //System.out.println("Ryu");
                    icon = new ImageIcon(getClass().getResource("/imagenes/SF/RYU.gif"));
                    psj = new Character(1, "Ryu", icon, getRandomNum(80, 100), getRandomNum(76, 100), getRandomNum(85, 100), getRandomNum(70, 100), "sf");
                    break;
                case "Zangief":
                    //System.out.println("Zangief");
                    icon = new ImageIcon(getClass().getResource("/imagenes/SF/zangief.gif"));
                    psj = new Character(1, "Zangief", icon, getRandomNum(67, 100), getRandomNum(80, 100), getRandomNum(89, 100), getRandomNum(63, 100), "sf");
                    break;
                case "Chun-Li":
                    //System.out.println("Chun-Li");
                    icon = new ImageIcon(getClass().getResource("/imagenes/SF/chunli-street-fighter.gif"));
                    psj = new Character(1, "Chun-Li", icon, getRandomNum(75, 100), getRandomNum(78, 100), getRandomNum(75, 100), getRandomNum(90, 100), "sf");
                    break;
                case "Ken":
                    //System.out.println("Ken");
                    icon = new ImageIcon(getClass().getResource("/imagenes/SF/ken.gif"));
                    psj = new Character(1, "Ken", icon, getRandomNum(84, 100), getRandomNum(76, 100), getRandomNum(88, 100), getRandomNum(78, 100), "sf");
                    break;
            }
            this.cola1SF.encolar(psj);
        }

        return psj;
    }

    public Character chooseZeldaArray(int result) {
        Character psj = null;
        ImageIcon icon;

        if (result == 0) { //deficientes
            int num = getRandomNum(0, (zeldaLowArr.length) - 1);
            switch (zeldaLowArr[num]) {
                case "Kass":
                   // System.out.println("kass");
                    icon = new ImageIcon(getClass().getResource("/imagenes/ZELDA/kass.gif"));
                    psj = new Character(3, "Kass", icon, getRandomNum(15, 30), getRandomNum(30, 45), getRandomNum(10, 20), getRandomNum(20, 30), "z");
                    break;
                case "Riju":
                  //  System.out.println("riju");
                    icon = new ImageIcon(getClass().getResource("/imagenes/ZELDA/riju.gif"));
                    psj = new Character(3, "Riju", icon, getRandomNum(20, 25), getRandomNum(25, 50), getRandomNum(12, 35), getRandomNum(22, 37), "z");
                    break;
                case "Yunobo":
                   // System.out.println("yunobo");
                    icon = new ImageIcon(getClass().getResource("/imagenes/ZELDA/yunobo.gif"));
                    psj = new Character(3, "Yunobo", icon, getRandomNum(14, 25), getRandomNum(10, 27), getRandomNum(25, 48), getRandomNum(16, 20), "z");
                    break;
            }
            this.cola3Zelda.encolar(psj);

        } else if (result >= 1 && result <= 3) { //promedio
            int num = getRandomNum(0, (zeldaAvegArr.length) - 1);
            switch (zeldaAvegArr[num]) {
                case "Impa":
                    //System.out.println("Impa");
                    icon = new ImageIcon(getClass().getResource("/imagenes/ZELDA/impa.gif"));
                    psj = new Character(2, "Impa", icon, getRandomNum(55, 70), getRandomNum(47, 64), getRandomNum(56, 70), getRandomNum(45, 57), "z");
                    break;
                case "Mipha":
                    //System.out.println("Mipha");
                    icon = new ImageIcon(getClass().getResource("/imagenes/ZELDA/mipha.gif"));
                    psj = new Character(2, "Mipha", icon, getRandomNum(66, 80), getRandomNum(43, 55), getRandomNum(42, 54), getRandomNum(48, 65), "z");
                    break;
                case "Daruk":
                    //System.out.println("Daruk");
                    icon = new ImageIcon(getClass().getResource("/imagenes/ZELDA/daruk.png"));
                    psj = new Character(2, "Daruk", icon, getRandomNum(45, 50), getRandomNum(70, 80), getRandomNum(85, 95), getRandomNum(35, 45), "z");
                    break;
                case "Sidon":
                    //System.out.println("Sidon");
                    icon = new ImageIcon(getClass().getResource("/imagenes/ZELDA/sidon.png"));
                    psj = new Character(2, "Sidon", icon, getRandomNum(40, 56), getRandomNum(45, 56), getRandomNum(30, 45), getRandomNum(58, 70), "z");
                    break;
            }
              this.cola2Zelda.encolar(psj);

        } else if (result >= 4) { //excepcionales
            int num = getRandomNum(0, (zeldaExcepArr.length) - 1);

            switch (zeldaExcepArr[num]) {
                case "Link":
                    //System.out.println("link");
                    icon = new ImageIcon(getClass().getResource("/imagenes/ZELDA/link.gif"));
                    psj = new Character(1, "Link", icon, getRandomNum(69, 100), getRandomNum(80, 100), getRandomNum(85, 100), getRandomNum(90, 100), "z");
                    break;
                case "Zelda":
                    //System.out.println("zelda");
                    icon = new ImageIcon(getClass().getResource("/imagenes/ZELDA/zelda.gif"));
                    psj = new Character(1, "Zelda", icon, getRandomNum(85, 100), getRandomNum(80, 100), getRandomNum(73, 100), getRandomNum(75, 100), "z");
                    break;
                case "Ganondorf":
                    //System.out.println("ganon");
                    icon = new ImageIcon(getClass().getResource("/imagenes/ZELDA/ganondorf.gif"));
                    psj = new Character(1, "Ganondorf", icon, getRandomNum(90, 100), getRandomNum(85, 100), getRandomNum(88, 100), getRandomNum(89, 100), "z");
                    break;
            }
            this.cola1Zelda.encolar(psj);
        }

        return psj;
    }



    public int getRandomNum(int min, int max) {
        int num = min + (int) (Math.random() * ((max - min) + 1));
        return num;
    }

}
