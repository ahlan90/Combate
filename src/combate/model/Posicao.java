package combate.model;

public class Posicao {
    
    /*
     Class Atributes 
     */
    int x;
    int y;
    
     public Posicao() {
        this(0,0);
    }
             
    /**
     * Creates a new instance of Position
     */
    public Posicao(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    
    public boolean equals (Posicao pos) {
        return (this.getX() == pos.getX()) && (this.getY() == pos.getY());
    }
    
    public String toString() {
       return this.getX()+" - "+ this.getY();
    }
}
