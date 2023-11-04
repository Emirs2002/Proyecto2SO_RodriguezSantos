package sistema;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import test.Main;
import utilidades.*;

/**
 *
 * @author emirs
 */
public class Admin extends Thread {

    private String[] zeldaExcepArr = {"Link", "Zelda", "Ganondorf"};
    private String[] zeldaAvegArr = {"Impa", "Mipha", "Daruk", "Sidon"};
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

    public Admin(Semaphore mutex, Cola[] zeldaColaArr, Cola[] sfColaArr, Cola colaRefuerzoZelda, Cola colaRefuerzoSF, Personaje sfFighter, Personaje zFighter, Personaje winner, int result, Lista winners) {
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

        this.zFighter = zFighter;
        this.sfFighter = sfFighter;
        this.winner = winner;
        this.result = result;
        this.winners = winners;
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

        try {
            mutex.acquire();
            System.out.println("");
            System.out.println("----------------ENTRA SISTEMA OPERATIVO---------------");
            System.out.println("");

            organizeQueues();

            if (this.cycleCounter < 2) {

                //revisar el estado del sistema
                System.out.println("");
                System.out.println("revisa estado sistema");

                this.cycleCounter++;

            } else {

                //calcular probabilidad de que se cree un nuevo psj
                int create = chooser.dice(1, 0.8);
                System.out.println("crear psj: " + create);

                if (create == 1) {

                    createCharacters();

                } else {
                    System.out.println("");
                    System.out.println("no se crea na");
                }

                this.cycleCounter = 0;
            }
            mutex.release();
        } catch (InterruptedException ex) {
            ex.printStackTrace(System.out);
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
                    icon = new ImageIcon(getClass().getResource("/imagenes/SF/guile.gif"));
                    psj = new Personaje(3, "Guile", icon, getRandomNum(15, 25), getRandomNum(30, 45), getRandomNum(10, 20), getRandomNum(20, 28), "sf");
                    break;
                case "Dee Jay":
                    //System.out.println("Dee Jay");
                    icon = new ImageIcon(getClass().getResource("/imagenes/SF/deejay1.gif"));
                    psj = new Personaje(3, "Dee Jay", icon, getRandomNum(20, 25), getRandomNum(17, 25), getRandomNum(21, 35), getRandomNum(22, 37), "sf");
                    break;
                case "Cammy":
                    //System.out.println("Cammy");
                    icon = new ImageIcon(getClass().getResource("/imagenes/SF/cammy.gif"));
                    psj = new Personaje(3, "Cammy", icon, getRandomNum(19, 30), getRandomNum(25, 35), getRandomNum(18, 24), getRandomNum(20, 26), "sf");
                    break;
            }
            this.cola3SF.encolar(psj);

        } else if (result >= 1 && result <= 3) { //promedio
            int num = getRandomNum(0, (sfAvegArr.length) - 1);
            switch (sfAvegArr[num]) {
                case "Jurii":
                    //System.out.println("Jurii");
                    icon = new ImageIcon(getClass().getResource("/imagenes/SF/juri.gif"));
                    psj = new Personaje(2, "Jurii", icon, getRandomNum(55, 68), getRandomNum(50, 64), getRandomNum(50, 60), getRandomNum(40, 60), "sf");
                    break;
                case "Honda":
                    //System.out.println("Honda");
                    icon = new ImageIcon(getClass().getResource("/imagenes/SF/e-honda.gif"));
                    psj = new Personaje(2, "Honda", icon, getRandomNum(50, 65), getRandomNum(47, 68), getRandomNum(42, 65), getRandomNum(34, 50), "sf");
                    break;
                case "Blanka":
                    //System.out.println("Blanka");
                    icon = new ImageIcon(getClass().getResource("/imagenes/SF/blanka.gif"));
                    psj = new Personaje(2, "Blanka", icon, getRandomNum(45, 50), getRandomNum(70, 75), getRandomNum(65, 85), getRandomNum(50, 60), "sf");
                    break;
            }
            this.cola2SF.encolar(psj);

        } else if (result >= 4) { //excepcionales
            int num = getRandomNum(0, (sfExcepArr.length) - 1);

            switch (sfExcepArr[num]) {
                case "Ryu":
                    //System.out.println("Ryu");
                    icon = new ImageIcon(getClass().getResource("/imagenes/SF/RYU.gif"));
                    psj = new Personaje(1, "Ryu", icon, getRandomNum(80, 100), getRandomNum(76, 100), getRandomNum(85, 100), getRandomNum(70, 100), "sf");
                    break;
                case "Zangief":
                    //System.out.println("Zangief");
                    icon = new ImageIcon(getClass().getResource("/imagenes/SF/zangief.gif"));
                    psj = new Personaje(1, "Zangief", icon, getRandomNum(67, 100), getRandomNum(80, 100), getRandomNum(89, 100), getRandomNum(63, 100), "sf");
                    break;
                case "Chun-Li":
                    //System.out.println("Chun-Li");
                    icon = new ImageIcon(getClass().getResource("/imagenes/SF/chunli-street-fighter.gif"));
                    psj = new Personaje(1, "Chun-Li", icon, getRandomNum(75, 100), getRandomNum(78, 100), getRandomNum(75, 100), getRandomNum(90, 100), "sf");
                    break;
                case "Ken":
                    //System.out.println("Ken");
                    icon = new ImageIcon(getClass().getResource("/imagenes/SF/ken.gif"));
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
                    icon = new ImageIcon(getClass().getResource("/imagenes/ZELDA/kass.gif"));
                    psj = new Personaje(3, "Kass", icon, getRandomNum(15, 30), getRandomNum(30, 45), getRandomNum(10, 20), getRandomNum(20, 30), "z");
                    break;
                case "Riju":
                    //  System.out.println("riju");
                    icon = new ImageIcon(getClass().getResource("/imagenes/ZELDA/riju.gif"));
                    psj = new Personaje(3, "Riju", icon, getRandomNum(20, 25), getRandomNum(25, 50), getRandomNum(12, 35), getRandomNum(22, 37), "z");
                    break;
                case "Yunobo":
                    // System.out.println("yunobo");
                    icon = new ImageIcon(getClass().getResource("/imagenes/ZELDA/yunobo.gif"));
                    psj = new Personaje(3, "Yunobo", icon, getRandomNum(14, 25), getRandomNum(10, 27), getRandomNum(25, 48), getRandomNum(16, 20), "z");
                    break;
            }
            this.cola3Zelda.encolar(psj);

        } else if (result >= 1 && result <= 3) { //promedio
            int num = getRandomNum(0, (zeldaAvegArr.length) - 1);
            switch (zeldaAvegArr[num]) {
                case "Impa":
                    //System.out.println("Impa");
                    icon = new ImageIcon(getClass().getResource("/imagenes/ZELDA/impa.gif"));
                    psj = new Personaje(2, "Impa", icon, getRandomNum(55, 70), getRandomNum(47, 64), getRandomNum(56, 70), getRandomNum(45, 57), "z");
                    break;
                case "Mipha":
                    //System.out.println("Mipha");
                    icon = new ImageIcon(getClass().getResource("/imagenes/ZELDA/mipha.gif"));
                    psj = new Personaje(2, "Mipha", icon, getRandomNum(66, 80), getRandomNum(43, 55), getRandomNum(42, 54), getRandomNum(48, 65), "z");
                    break;
                case "Daruk":
                    //System.out.println("Daruk");
                    icon = new ImageIcon(getClass().getResource("/imagenes/ZELDA/daruk.png"));
                    psj = new Personaje(2, "Daruk", icon, getRandomNum(45, 50), getRandomNum(70, 80), getRandomNum(85, 95), getRandomNum(35, 45), "z");
                    break;
                case "Sidon":
                    //System.out.println("Sidon");
                    icon = new ImageIcon(getClass().getResource("/imagenes/ZELDA/sidon.png"));
                    psj = new Personaje(2, "Sidon", icon, getRandomNum(40, 56), getRandomNum(45, 56), getRandomNum(30, 45), getRandomNum(58, 70), "z");
                    break;
            }
            this.cola2Zelda.encolar(psj);

        } else if (result >= 4) { //excepcionales
            int num = getRandomNum(0, (zeldaExcepArr.length) - 1);

            switch (zeldaExcepArr[num]) {
                case "Link":
                    //System.out.println("link");
                    icon = new ImageIcon(getClass().getResource("/imagenes/ZELDA/link.gif"));
                    psj = new Personaje(1, "Link", icon, getRandomNum(69, 100), getRandomNum(80, 100), getRandomNum(85, 100), getRandomNum(90, 100), "z");
                    break;
                case "Zelda":
                    //System.out.println("zelda");
                    icon = new ImageIcon(getClass().getResource("/imagenes/ZELDA/zelda.gif"));
                    psj = new Personaje(1, "Zelda", icon, getRandomNum(85, 100), getRandomNum(80, 100), getRandomNum(73, 100), getRandomNum(75, 100), "z");
                    break;
                case "Ganondorf":
                    //System.out.println("ganon");
                    icon = new ImageIcon(getClass().getResource("/imagenes/ZELDA/ganondorf.gif"));
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
        

        checkCounter(this.zeldaColaArr);
        checkCounter(this.sfColaArr);

        System.out.println("Despues checkCounter");
        System.out.println("");
        System.out.println("COLA 1 ZELDA");
        this.cola1Zelda.mostrarCola();

        System.out.println("");
        System.out.println("COLA 2 ZELDA");
        this.cola2Zelda.mostrarCola();

        System.out.println("");
        System.out.println("COLA 3 ZELDA");
        this.cola3Zelda.mostrarCola();

        updateCounter(this.zeldaColaArr);
        updateCounter(this.sfColaArr);

        //Gestionar la cola de refuerzo
        checkBackUpQueue();
        
        pickFighters();
        System.out.println("Pick fighters");
        System.out.println("Zelda" + zFighter);
        System.out.println("SF" +sfFighter);
        
        //Gestionar resultados
        dealWithResults();
        
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
            Nodo aux;

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
        int resultz = chooser.dice(1, 0.4);

        int resultsf = chooser.dice(1, 0.4);

        if (resultz == 1) {
            System.out.println("SALE DE REFUERZO ZELDA");

            if (!this.colaRefuerzoZelda.esVacio()) {
                Personaje zelda = (Personaje) this.colaRefuerzoZelda.desencolar();
                System.out.println("zelda refuerzo = " + zelda);
                this.cola1Zelda.encolar(zelda);
            }

        } else {
            System.out.println("no sale psj zelda");
        }

        if (resultsf == 1) {
            System.out.println("SALE DE REFUERZO SF");

            if (!this.colaRefuerzoSF.esVacio()) {
                Personaje sf = (Personaje) this.colaRefuerzoSF.desencolar();
                System.out.println("sf refuerzo = " + sf);
                this.cola1SF.encolar(sf);

            }

        } else {
            System.out.println("no sale psj sf");
        }
    }

    public void pickFighters() {

        for (int i = 0; i < zeldaColaArr.length; i++) {
            boolean isEmpty = zeldaColaArr[i].esVacio();
            if (!isEmpty) {
                zFighter = (Personaje) zeldaColaArr[i].desencolar();
                break;
            }
        }

        for (int i = 0; i < sfColaArr.length; i++) {
            boolean isEmpty = sfColaArr[i].esVacio();
            if (!isEmpty) {
                sfFighter = (Personaje) sfColaArr[i].desencolar();
                break;
            }
        }
    }
    
    public void dealWithResults(){
        if(result!= 0)
        {
            if(result == 1){
                Nodo nodito = new Nodo(winner);
                winners.addAtEnd(nodito);
                winners.imprimirLista();
            }else if(result == 2){
                cola1Zelda.encolar(zFighter);
                cola1SF.encolar(sfFighter);
            }else if(result == 3){
                colaRefuerzoZelda.encolar(zFighter);
                colaRefuerzoSF.encolar(sfFighter);
            }
        }
    }
}
