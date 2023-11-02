package utilidades;


public class Cola {
     
    private int size;
    private Nodo front;
    private Nodo back;

    //CONSTRUCTOR COLA
    public Cola() {
        this.size = 0;
        this.front = null;
        this.back = null;
    }
    
 
    /**
    *@return size
    * 
    */
    public int getSize() {
        return size;
    }
    
    /**
    *@return front 
    */
    public Nodo getFront() {
        return front;
    }

    /**
    *@param front asigna front
    */
    public void setFront(Nodo front) {
        this.front = front;
    }

    /**
    *@return back 
    */
    public Nodo getBack() {
        return back;
    }

    /**
    *@param back asigna back 
    */
    public void setBack(Nodo back) {
        this.back = back;
    }

    /**
    *@return si esta vacia la cola
    */
    public boolean esVacio() {
        return this.front == null;
    }

    //Verificar si existen
    public boolean existe(Object info) {
        Nodo aux = front;
        boolean existe;
        if (!esVacio()) {
            desencolar();
            if (aux.getData() == info) {
                existe = true;
            } else {
                existe = existe(info);
            }
            encolar(aux.getData());
        } else {
            existe = false;
        }
        return existe;
    }

    //Agregar a la cola
    public void encolar(Object info) {
        Nodo nuevo = new Nodo(info);
        if (esVacio()) {
            this.front = nuevo;
            this.back = nuevo;
            size++;
        } else {
            back.pnext = nuevo;
            back = nuevo;
            size++;
        }
    }

    //Eliminar de la cola
    //Devolver index del vertice
    public Object desencolar() {
        Nodo aux = front;    
        Object index = aux.getData();
        if (!esVacio()) {
            front = front.pnext;
            size--;
        }
    return index;
    }
    

    //Invertir cola
    public void invertCola() {
        Object aux;
        if (!esVacio()) {
            aux = front.data;
            desencolar();
            invertCola();
            encolar(aux);
        }
    }

    //Copiar Cola
    public Cola copy(Cola copia) {
        Object aux;
        if (!esVacio()) {
            aux = front.getData();
            desencolar();
            copia.encolar(aux);
            copy(copia);
            encolar(aux);
        }
        return copia;
    }

    //Buscar elemento en cola
    public Object searchElement(int index) {
        Nodo aux = front;
        Object elemento = null;
        if (index > size) {
            System.out.println("No existe en la cola");
        } else if (index > 1) {
            desencolar();
            index--;
            elemento = searchElement(index);
            encolar(aux.getData());
        } else {
            elemento = front.getData();
        }
        return elemento;
    }

    //Mostrar cola
    public void mostrarCola() {
        Nodo temp = front;
        
        while (temp != null) {
            System.out.println(temp.getData());
            temp = temp.pnext;
        }
        
    }
    
   
    
}
