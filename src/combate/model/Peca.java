
package combate.model;

import java.awt.Color;
import java.util.ArrayList;


public class Peca {
    
    
    String name;
    Color color;
    Posicao position;
    int quantidade;
    boolean escondida = false;
    public int forca;
    
    
    public Peca(Peca outraPeca) {
        this.name = outraPeca.name;
        this.color = outraPeca.color;
        this.quantidade = outraPeca.quantidade;
        this.position = outraPeca.position;
        this.forca = outraPeca.forca;
        this.escondida = outraPeca.escondida;
    }
    
    public Peca(String nome, Color cor, int forca) {
       this(nome, cor, new Posicao(), forca);            
    }
    
    public Peca(String nome, Color cor, Posicao pos, int qtde) {
        this.name = nome;
        this.color = cor;
        this.quantidade = qtde;
        this.position = pos; 
    }
    public Peca(String nome, Color cor, Posicao pos) {
        this.name = nome;
        this.color = cor;
        this.position = pos;
    }
    
    
    public Peca(int forca, String nome, Color cor, Posicao pos) {
        this.name = nome;
        this.color = cor;
        this.position = pos;
        this.forca = forca;
    }
    
    public Peca(int forca, String nome, Color cor, Posicao pos, boolean escondida) {
        this.name = nome;
        this.color = cor;
        this.position = pos;
        this.escondida = escondida;
        this.forca = forca;
    }
    
   
    public void setPosition(Posicao pos) {
        this.position = pos;
    }
  
    public Posicao getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public boolean isEscondida() {
        return escondida;
    }

    public void setEscondida(boolean escondida) {
        this.escondida = escondida;
    }

    public int getForca() {
        return forca;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }
    
    
    
}
