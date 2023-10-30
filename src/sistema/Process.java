package sistema;

import javax.swing.ImageIcon;

/**
 *
 * @author emirs
 */
public class Process {
    private String id;
    private int prioridad; //1,2,3
    private int counter;//max 8
    private String name;
    private String game;
    public ImageIcon imagen;
    private int abilities; //60%
    private int lifePoints;//70%
    private int strenght; //50%
    private int agility; //40%
    private static int zeldaCounter;
    private static int SFCounter;

    public Process(int prioridad,String name, ImageIcon imagen, int abilities, int lifePoints, int strenght, int agility, String game) {
        this.id = (game.equals("z")) ? "z" + ++zeldaCounter : "sf"+ ++SFCounter;
        this.name = name;
        this.prioridad = prioridad;
        this.imagen = imagen;
        this.abilities = abilities;
        this.lifePoints = lifePoints;
        this.strenght = strenght;
        this.agility = agility;
    }
    
    
    @Override
    public String toString() {
        return "Process{" + "id=" + id + ", prioridad=" + prioridad + ", name=" + name + ", abilities=" + abilities + ", lifePoints=" + lifePoints + ", strenght=" + strenght + ", agility=" + agility + '}';
    }

    
    
    
}

