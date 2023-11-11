package sistema;

import interfaz.GUIHandler;
import java.awt.Image;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import utilidades.*;

/**
 *
 * @author emirs
 */
public class Admin extends Thread {

    private String[] zeldaExcepArr = {"Link", "Zelda", "Ganondorf"};
    private String[] zeldaAvegArr = {"Urbosa", "Mipha", "Daruk", "Sidon"};
    private String[] zeldaLowArr = {"Kass", "Riju", "Yunobo"};

    private Cola cola1Zelda;
    private Cola cola2Zelda;
    private Cola cola3Zelda;
    private Cola colaRefuerzoZelda;
    private Cola[] zeldaColaArr;

    private String[] sfExcepArr = {"Ryu", "Zangief", "Chun-Li", "Ken"};
    private String[] sfAvegArr = {"Jurii", "Honda", "Blanka"};
    private String[] sfLowArr = {"Guile", "Dee Jay", "Cammy"};

    private Cola cola1SF;
    private Cola cola2SF;
    private Cola cola3SF;
    private Cola colaRefuerzoSF;
    private Cola[] sfColaArr;

    private Semaphore mutex;
    private Chooser chooser;
    private double[] abilityProbArr = {0.6, 0.7, 0.5, 0.4};
    private int cycleCounter;

    private Personaje sfFighter;
    private Personaje zFighter;
    private Personaje winner;
    private int result;
    private Lista winners;

    public GUIHandler guiHandler;
    
    Personaje zTemp = new Personaje("", 0, "", null, 0, 0, 0, 0, "z");
    Personaje sfTemp = new Personaje("", 0, "", null, 0, 0, 0, 0, "sf");

    public Admin(Semaphore mutex, Cola[] zeldaColaArr, Cola[] sfColaArr, Cola colaRefuerzoZelda, Cola colaRefuerzoSF, Lista winners, GUIHandler gui) {
        this.cola1Zelda = zeldaColaArr[0];
        this.cola2Zelda = zeldaColaArr[1];
        this.cola3Zelda = zeldaColaArr[2];
        this.colaRefuerzoZelda = colaRefuerzoZelda;
        this.zeldaColaArr = zeldaColaArr;

        this.cola1SF = sfColaArr[0];
        this.cola2SF = sfColaArr[1];
        this.cola3SF = sfColaArr[2];
        this.colaRefuerzoSF = colaRefuerzoSF;
        this.sfColaArr = sfColaArr;

        this.mutex = mutex;
        this.chooser = new Chooser();
        this.cycleCounter = 0;

        this.winners = winners;
        this.guiHandler = gui;
    }

    @Override
    public void run() {

        //cargar 10 procesos de cada empresa en el sistema
        int count = 0;
        while (count < 10) {
            createCharacters();
            count++;
        }

        this.guiHandler.formatQueues(this.zeldaColaArr, "z");
        this.guiHandler.formatQueues(this.sfColaArr, "sf");

        while (true) {
            
                checkCycle();
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void checkCycle() {
        try {

            mutex.acquire(1);
            System.out.println("");
            System.out.println("----------------ENTRA SISTEMA OPERATIVO---------------");
            System.out.println("");

            organizeQueues();

            if (this.cycleCounter < 2) {

                this.cycleCounter++;

            } else {

                //calcular probabilidad de que se cree un nuevo psj
                int create = chooser.dice(1, 0.8);
                System.out.println("crear psj: " + create);

                if (create == 1) {

                    createCharacters();
                    this.guiHandler.formatQueues(this.zeldaColaArr, "z");
                    this.guiHandler.formatQueues(this.sfColaArr, "sf");

                } else {
                    System.out.println("");
                    System.out.println("no se crea na");
                }

                this.cycleCounter = 0;
            }

            mutex.release();
            sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void createCharacters() {

        //crear zelda
        int resultZ = chooser.dice(1, abilityProbArr);
        Personaje characterZelda = chooseZeldaArray(resultZ);
        System.out.println("");
        System.out.println("characterZelda = " + characterZelda);

        //crear SF
        int resultSF = chooser.dice(1, abilityProbArr);
        Personaje characterSF = chooseSFArray(resultSF);
        System.out.println("");
        System.out.println("characterSF = " + characterSF);

    }

    public Personaje chooseSFArray(int result) {
        Personaje psj = null;
        ImageIcon icon;

        if (result == 0) { //deficientes
            int num = getRandomNum(0, (sfLowArr.length) - 1);
            switch (sfLowArr[num]) {
                case "Guile":
                    //System.out.println("Guile");
                    icon = makeIcon("/imagenes/SF/guile.gif", 140, 180);
                    psj = new Personaje(3, "Guile", icon, getRandomNum(15, 25), getRandomNum(30, 45), getRandomNum(10, 20), getRandomNum(20, 28), "sf");
                    break;
                case "Dee Jay":
                    //System.out.println("Dee Jay");
                    icon = makeIcon("/imagenes/SF/deejay1.gif", 140, 180);
                    psj = new Personaje(3, "Dee Jay", icon, getRandomNum(20, 25), getRandomNum(17, 25), getRandomNum(21, 35), getRandomNum(22, 37), "sf");
                    break;
                case "Cammy":
                    //System.out.println("Cammy");
                    icon = makeIcon("/imagenes/SF/cammy.gif", 140, 180);
                    psj = new Personaje(3, "Cammy", icon, getRandomNum(19, 30), getRandomNum(25, 35), getRandomNum(18, 24), getRandomNum(20, 26), "sf");
                    break;
            }
            this.cola3SF.encolar(psj);

        } else if (result >= 1 && result <= 3) { //promedio
            int num = getRandomNum(0, (sfAvegArr.length) - 1);
            switch (sfAvegArr[num]) {
                case "Jurii":
                    //System.out.println("Jurii");
                    icon = makeIcon("/imagenes/SF/juri.gif", 140, 180);
                    psj = new Personaje(2, "Jurii", icon, getRandomNum(55, 68), getRandomNum(50, 64), getRandomNum(50, 60), getRandomNum(40, 60), "sf");
                    break;
                case "Honda":
                    //System.out.println("Honda");
                    icon = makeIcon("/imagenes/SF/honda2.png", 140, 180);
                    psj = new Personaje(2, "Honda", icon, getRandomNum(50, 65), getRandomNum(47, 68), getRandomNum(42, 65), getRandomNum(34, 50), "sf");
                    break;
                case "Blanka":
                    //System.out.println("Blanka");
                    icon = makeIcon("/imagenes/SF/blanka.gif", 140, 180);
                    psj = new Personaje(2, "Blanka", icon, getRandomNum(45, 50), getRandomNum(70, 75), getRandomNum(65, 85), getRandomNum(50, 60), "sf");
                    break;
            }
            this.cola2SF.encolar(psj);

        } else if (result >= 4) { //excepcionales
            int num = getRandomNum(0, (sfExcepArr.length) - 1);

            switch (sfExcepArr[num]) {
                case "Ryu":
                    //System.out.println("Ryu");
                    icon = makeIcon("/imagenes/SF/RYU.gif", 140, 180);
                    psj = new Personaje(1, "Ryu", icon, getRandomNum(80, 100), getRandomNum(76, 100), getRandomNum(85, 100), getRandomNum(70, 100), "sf");
                    break;
                case "Zangief":
                    //System.out.println("Zangief");
                    icon = makeIcon("/imagenes/SF/zangief.gif", 140, 180);
                    psj = new Personaje(1, "Zangief", icon, getRandomNum(67, 100), getRandomNum(80, 100), getRandomNum(89, 100), getRandomNum(63, 100), "sf");
                    break;
                case "Chun-Li":
                    //System.out.println("Chun-Li");
                    icon = makeIcon("/imagenes/SF/chunli-street-fighter.gif", 140, 180);
                    psj = new Personaje(1, "Chun-Li", icon, getRandomNum(75, 100), getRandomNum(78, 100), getRandomNum(75, 100), getRandomNum(90, 100), "sf");
                    break;
                case "Ken":
                    //System.out.println("Ken");
                    icon = makeIcon("/imagenes/SF/ken.gif", 140, 180);
                    psj = new Personaje(1, "Ken", icon, getRandomNum(84, 100), getRandomNum(76, 100), getRandomNum(88, 100), getRandomNum(78, 100), "sf");
                    break;
            }
            this.cola1SF.encolar(psj);
        }

        return psj;
    }

    public Personaje chooseZeldaArray(int result) {
        Personaje psj = null;
        ImageIcon icon;

        if (result == 0) { //deficientes
            int num = getRandomNum(0, (zeldaLowArr.length) - 1);
            switch (zeldaLowArr[num]) {
                case "Kass":
                    // System.out.println("kass");
                    icon = makeIcon("/imagenes/ZELDA/kass.png", 140, 180);
                    psj = new Personaje(3, "Kass", icon, getRandomNum(15, 30), getRandomNum(30, 45), getRandomNum(10, 20), getRandomNum(20, 30), "z");
                    break;
                case "Riju":
                    //  System.out.println("riju");
                    icon = makeIcon("/imagenes/ZELDA/riju.png", 140, 180);
                    psj = new Personaje(3, "Riju", icon, getRandomNum(20, 25), getRandomNum(25, 50), getRandomNum(12, 35), getRandomNum(22, 37), "z");
                    break;
                case "Yunobo":
                    // System.out.println("yunobo");
                    icon = makeIcon("/imagenes/ZELDA/yunobo.gif", 200, 200);
                    psj = new Personaje(3, "Yunobo", icon, getRandomNum(14, 25), getRandomNum(10, 27), getRandomNum(25, 48), getRandomNum(16, 20), "z");
                    break;
            }
            this.cola3Zelda.encolar(psj);

        } else if (result >= 1 && result <= 3) { //promedio
            int num = getRandomNum(0, (zeldaAvegArr.length) - 1);
            switch (zeldaAvegArr[num]) {
                case "Urbosa":
                    //System.out.println("Urbosa");
                    icon = makeIcon("/imagenes/ZELDA/urbosa.png", 120, 180);
                    psj = new Personaje(2, "Urbosa", icon, getRandomNum(55, 70), getRandomNum(47, 64), getRandomNum(56, 70), getRandomNum(45, 57), "z");
                    break;
                case "Mipha":
                    //System.out.println("Mipha");
                    icon = makeIcon("/imagenes/ZELDA/mipha.png", 140, 180);
                    psj = new Personaje(2, "Mipha", icon, getRandomNum(66, 80), getRandomNum(43, 55), getRandomNum(42, 54), getRandomNum(48, 65), "z");
                    break;
                case "Daruk": //width 150
                    //System.out.println("Daruk");
                    icon = makeIcon("/imagenes/ZELDA/daruk.png", 150, 180);
                    psj = new Personaje(2, "Daruk", icon, getRandomNum(45, 50), getRandomNum(70, 80), getRandomNum(85, 95), getRandomNum(35, 45), "z");
                    break;
                case "Sidon":
                    //System.out.println("Sidon");
                    icon = makeIcon("/imagenes/ZELDA/sidon.png", 140, 180);
                    psj = new Personaje(2, "Sidon", icon, getRandomNum(40, 56), getRandomNum(45, 56), getRandomNum(30, 45), getRandomNum(58, 70), "z");
                    break;
            }
            this.cola2Zelda.encolar(psj);

        } else if (result >= 4) { //excepcionales
            int num = getRandomNum(0, (zeldaExcepArr.length) - 1);

            switch (zeldaExcepArr[num]) {
                case "Link":
                    //System.out.println("link");
                    icon = makeIcon("/imagenes/ZELDA/link.gif", 140, 180);
                    psj = new Personaje(1, "Link", icon, getRandomNum(69, 100), getRandomNum(80, 100), getRandomNum(85, 100), getRandomNum(90, 100), "z");
                    break;
                case "Zelda":
                    //System.out.println("zelda");
                    icon = makeIcon("/imagenes/ZELDA/zelda.gif", 140, 180);
                    psj = new Personaje(1, "Zelda", icon, getRandomNum(85, 100), getRandomNum(80, 100), getRandomNum(73, 100), getRandomNum(75, 100), "z");
                    break;
                case "Ganondorf":
                    //System.out.println("ganon");
                    icon = makeIcon("/imagenes/ZELDA/ganondorf.gif", 140, 180);
                    psj = new Personaje(1, "Ganondorf", icon, getRandomNum(90, 100), getRandomNum(85, 100), getRandomNum(88, 100), getRandomNum(89, 100), "z");
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

    public void organizeQueues() {

        //Gestionar resultados
        dealWithResults();

        //Gestionar el contador de los procesos
        checkCounter(this.zeldaColaArr);
        checkCounter(this.sfColaArr);
        
        this.guiHandler.formatQueues(this.zeldaColaArr, "z");
        this.guiHandler.formatQueues(this.sfColaArr, "sf");

        updateCounter(this.zeldaColaArr);
        updateCounter(this.sfColaArr);


        //Gestionar la cola de refuerzo
        checkBackUpQueue();

        //Escoger a los combatientes
        pickFighters();
        this.guiHandler.formatQueues(this.zeldaColaArr, "z");
        this.guiHandler.formatQueues(this.sfColaArr, "sf");

        System.out.println("");
        System.out.println("Pick fighters");
        System.out.println("Zelda" + Global.getzFighter());
        System.out.println("SF" + Global.getSfFighter());

    }

    //sumarle 1 al contador de los personajes de prioridad 2 y 3
    public void updateCounter(Cola[] colaArr) {
        for (int i = 1; i < colaArr.length; i++) {
            Cola cola = colaArr[i];
            Nodo temp = cola.getFront();

            while (temp != null) {

                Personaje chara = (Personaje) temp.getData();
                chara.sumCounter();

                temp = temp.getPnext();
            }
        }

    }

    //ver el valor del contador y asignarlo al siguiente nivel de prioridad
    public void checkCounter(Cola[] colaArr) {
        for (int i = 1; i < colaArr.length; i++) {
            Cola cola = colaArr[i];
            Nodo temp = cola.getFront();

            while (temp != null) {

                Personaje chara = (Personaje) temp.getData();

                //si el contador llega a su max, se encola en la cola superior
                if (chara.getCounter() == 8) {

                    temp = temp.getPnext();
                    //desencolar nodo
                    cola.desencolar();

                    if (chara.getPrioridad() == 2) {
                        chara.setCounter(0);
                        chara.setPrioridad(1);
                        colaArr[0].encolar(chara);
                    } else if (chara.getPrioridad() == 3) {
                        chara.setCounter(0);
                        chara.setPrioridad(2);
                        colaArr[1].encolar(chara);

                    }
                } else {
                    break;
                }

            }
        }

    }

    public void checkBackUpQueue() {
        //tirar dados para ver si un personaje sale de las colas

        if (!this.colaRefuerzoZelda.esVacio()) {

            int resultz = chooser.dice(1, 0.4);

            if (resultz == 1) {
                System.out.println("SALE DE REFUERZO ZELDA");

                Personaje zelda = (Personaje) this.colaRefuerzoZelda.desencolar();
                System.out.println("zelda refuerzo = " + zelda);

                this.cola1Zelda.encolar(zelda);
                
                this.guiHandler.formatQueue(this.colaRefuerzoZelda, "z", 3);
                
                this.guiHandler.saleRefZ.setText("Salio");

                this.guiHandler.formatQueue(this.cola1Zelda, "z", 0);

            } else {
                System.out.println("no sale psj zelda");
                this.guiHandler.saleRefZ.setText("No salio");
            }
        }

        if (!this.colaRefuerzoSF.esVacio()) {

            int resultsf = chooser.dice(1, 0.4);

            if (resultsf == 1) {
                System.out.println("SALE DE REFUERZO SF");
                Personaje sf = (Personaje) this.colaRefuerzoSF.desencolar();
                System.out.println("sf refuerzo = " + sf);
                this.cola1SF.encolar(sf);

                this.guiHandler.formatQueue(this.colaRefuerzoSF, "sf", 3);
                this.guiHandler.saleRefSF.setText("Salio");
                this.guiHandler.formatQueue(this.cola1SF, "sf", 0);

            } else {
                System.out.println("no sale psj sf");
                this.guiHandler.saleRefSF.setText("No salio");
            }
        }
    }

    public void pickFighters() {

        for (int i = 0; i < zeldaColaArr.length; i++) {
            boolean isEmpty = zeldaColaArr[i].esVacio();
            if (!isEmpty) {
                Global.setzFighter((Personaje) zeldaColaArr[i].desencolar());
                break;
            }
        }

        for (int i = 0; i < sfColaArr.length; i++) {
            boolean isEmpty = sfColaArr[i].esVacio();
            if (!isEmpty) {
                Global.setSfFighter((Personaje) sfColaArr[i].desencolar());
                break;
            }
        }
    }

    public void dealWithResults() {
        if (Global.getResult() != 0) {
            switch (Global.getResult()) {
                case 1:
                    Nodo nodito = new Nodo(Global.getWinner());
                    winners.addAtEnd(nodito);
                    String game = Global.getWinner().getGame();

                    //actualizar los valores de los contadores de victorias
                    if (game.equals("z")) {
                        Global.sumVictoriesZelda();
                        int victoria = Global.getVictoriesZelda();

                        this.guiHandler.getZeldaVictory().setText(Integer.toString(victoria));
                    } else{
                        Global.sumVictoriesSF();
                        int victoria = Global.getVictoriesSF();

                        this.guiHandler.getSfVictory().setText(Integer.toString(victoria));
                    }

                    Global.setWinner(null);
                    Global.setResult(0);
                    break;
                case 2:
                    cola1Zelda.encolar(Global.getzFighter());
                    this.guiHandler.formatQueue(this.cola1Zelda, "z", 0);

                    cola1SF.encolar(Global.getSfFighter());
                    this.guiHandler.formatQueue(this.cola1SF, "sf", 0);

                    Global.setResult(0);

                    break;
                case 3:
                    colaRefuerzoZelda.encolar(Global.getzFighter());
                    this.guiHandler.formatQueue(this.colaRefuerzoZelda, "z", 3);

                    colaRefuerzoSF.encolar(Global.getSfFighter());
                    this.guiHandler.formatQueue(this.colaRefuerzoSF, "sf", 3);
                    Global.setResult(0);
                    break;
                default:
                    System.out.println("resultado no valido");
                    break;
            }
        }
    }

    public ImageIcon makeIcon(String dir, int width, int height) {
        ImageIcon icon = new ImageIcon(new ImageIcon(getClass().getResource(dir)).getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        return icon;
    }
}
