package sistema;

import javax.swing.ImageIcon;

/**
 *
 * @author emirs
 */
public class Character {
    private final String id;
    private int prioridad; //1,2,3
    private int counter;//max 8
    private final String name;
    private String game;
    public ImageIcon imagen;
    private int abilities; //60%
    private int lifePoints;//70%
    private int strenght; //50%
    private int agility; //40%
    private static int zeldaCounter;
    private static int SFCounter;

    public Character(int prioridad,String name, ImageIcon imagen, int abilities, int lifePoints, int strenght, int agility, String game) {
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
        return "Character{" + "id=" + id + ", prioridad=" + prioridad + ", counter=" + counter + ", name=" + name + ", abilities=" + abilities + ", lifePoints=" + lifePoints + ", strenght=" + strenght + ", agility=" + agility + '}';
    }
    
    public void sumCounter(){
        this.counter++;
    }

    public String getId() {
        return id;
    }

    public int getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(int prioridad) {
        this.prioridad = prioridad;
    }

    public int getCounter() {
        return counter;
    }

    public void setCounter(int counter) {
        this.counter = counter;
    }

    public String getName() {
        return name;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public int getAbilities() {
        return abilities;
    }

    public void setAbilities(int abilities) {
        this.abilities = abilities;
    }

    public int getLifePoints() {
        return lifePoints;
    }

    public void setLifePoints(int lifePoints) {
        this.lifePoints = lifePoints;
    }

    public int getStrenght() {
        return strenght;
    }

    public void setStrenght(int strenght) {
        this.strenght = strenght;
    }

    public int getAgility() {
        return agility;
    }

    public void setAgility(int agility) {
        this.agility = agility;
    }

   

    
    
    
}

