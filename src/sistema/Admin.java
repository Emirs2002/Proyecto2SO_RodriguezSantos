package sistema;

import java.util.concurrent.Semaphore;
import javax.swing.ImageIcon;
import utilidades.Chooser;

/**
 *
 * @author emirs
 */
public class Admin extends Thread{
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
    private Chooser dice;
    private double[] abilityProbArr = {0.6,0.7,0.5,0.4};
    
    public Admin(){
        dice = new Chooser();
    }
    
    
    @Override
    public void run(){
        while(true){
            
            //lanzar dado
            int result = dice.dice(2, abilityProbArr);
            Process character = chooseArray(result);
            
            //aqui se encola en la determina cola con un if
        }
    }
    
    public Process chooseArray(int result){
        Process psj = null;
        ImageIcon icon;
        
        if(result == 0){ //deficientes
            int num = getRandomNum(0,(zeldaLowArr.length) - 1);
            
            switch(zeldaLowArr[num]){
                case "Kass":
                    System.out.println("kass");
                    break;
                case "Riju":
                    System.out.println("riju");
                    break;
                case "Yunobo":
                    System.out.println("yunobo");
                    break;
            }
        }else if(result == 1 || result == 2){ //promedio
            int num = getRandomNum(0,(zeldaAvegArr.length) - 1);
            
        }else if(result >= 3){ //excepcionales
            int num = getRandomNum(0,(zeldaExcepArr.length) - 1);
            
            switch(zeldaExcepArr[num]){
                case "Link":
                    icon = new ImageIcon(getClass().getResource("/imagenes/ZELDA/link.gif"));
                    psj = new Process(1,icon,getRandomNum(60,100), getRandomNum(80,100), getRandomNum(75,100),getRandomNum(90,100));
                    
                    break;
                case "Zelda":
                    System.out.println("zelda");
                    break;
                case "Ganondorf":
                    System.out.println("ganon");
                    break;
            }
        }
        
        return psj;
    }
    
    
    public int getRandomNum(int min, int max){
        int num = min + (int)(Math.random() * ((max - min) + 1));
        return num;
    }
    
}
