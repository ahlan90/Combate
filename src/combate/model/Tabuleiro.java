package combate.model;

import java.util.ArrayList;
import java.util.List;



public class Tabuleiro {
    
    /* Matriz de pecas do tabuleiro */
    Peca tabuleiro[][];
    
    List<Peca> iniciais;
    /* 
     Contrutor da classe tabuleiro
     */
    public Tabuleiro() {
        this.tabuleiro = new Peca[5][5];
        this.iniciais = new ArrayList<>();
        this.limparTabuleiro();
    }
    
    /* 
     Inicializa board
     */
    public void limparTabuleiro () {
        
        for (int i=0; i<5; i++) 
            for (int j=0; j<5; j++)
                this.tabuleiro[i][j] = null;
    }
    
    
    public void pecasInicias(){
        
    }
    
    /* 
       Coloca a pe�a em uma posicao do board
     */
    public void setPosicao(Peca peca, Posicao pos){
        this.tabuleiro[pos.getX()][pos.getY()] = peca;
    }
    
    /* 
       Remove pe�a em uma posicao do board
     */
    public void setNullPosicao(Posicao pos){
        this.tabuleiro[pos.getX()][pos.getY()] = null;
    }
    
    
    /* 
       Verifica se posi��o � vazia
     */
    public boolean isNullPosicao(Posicao pos){
        return this.tabuleiro[pos.getX()][pos.getY()] == null;
    }
    
    /*
        Move a peca localizada em origem para o destino
        - sobreescreve a peca localizada em destino (se existir)
        - limpa a posicao origem
     */
    public void moverPeca (Posicao origem, Posicao destino) {
        
        //Pega a pe�a da posi��o
        Peca peca = this.getPecaAtPosicao(origem);
        
        // Move a pe�a de origem para o destino
        this.setPosicao(peca, destino);     
        
        // Atualiza posi��o da pe�a movida
        peca.setPosition(destino);
        
        // Seta origem como vazia
        this.setNullPosicao(origem);
    }
    
    /* 
       Retorna a pe�a em uma determinada posicao
     */
    public Peca getPecaAtPosicao(Posicao pos){
        return this.tabuleiro[pos.getX()][pos.getY()];
    }
    
    public Peca getPecaInicial(int x){
        return this.iniciais.get(x);
    }
}
