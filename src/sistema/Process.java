package sistema;

import javax.swing.ImageIcon;

/**
 *
 * @author emirs
 */
public class Process {
    private int id;
    private int prioridad; //1,2,3
    private int counter;//max 8
    public ImageIcon imagen;
    private int abilities; //60%
    private int lifePoints;//70%
    private int strenght; //50%
    private int agility; //40%
    private static int processCounter;

    public Process(int prioridad, ImageIcon imagen, int abilities, int lifePoints, int strenght, int agility) {
        this.id = ++Process.processCounter;
        this.prioridad = prioridad;
        this.imagen = imagen;
        this.abilities = abilities;
        this.lifePoints = lifePoints;
        this.strenght = strenght;
        this.agility = agility;
    }

    @Override
    public String toString() {
        return "Process{" + "id=" + id + ", prioridad=" + prioridad + ", counter=" + counter + ", abilities=" + abilities + ", lifePoints=" + lifePoints + ", strenght=" + strenght + ", agility=" + agility + '}';
    }
    
    
    
}

