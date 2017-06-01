/*
 * Combate.java
 *
 * Created on October 12, 2006, 1:22 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package combate.model;

import combate.pecas.Marechal;
import combate.pecas.Armeiro;
import combate.pecas.Bandeira;
import combate.pecas.Bomba;
import combate.pecas.Espiao;
import combate.pecas.Lago;
import combate.pecas.Soldado;
import combate.visao.TabuleiroGUI;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;
import javax.rmi.CORBA.Util;
import javax.swing.JOptionPane;

/**
 *
 * @author 
 */
public class Combate {

    /* Tabuleiro do Jogo */
    Tabuleiro tabuleiro;

    Tabuleiro inicialTabuleiro;
    //Variavel para controle caso o jogador selecione ele posicionar as pecas
    boolean pecasPosicionadas;
    
//    /* Players */
//    Player jogador;
//
//    /* Players */
//    Player computador;

    /* Player */
    boolean firstPlayersTime;

    /* Game is over*/
    boolean gameover;

    
    boolean jogoAleatorio;
    
    
    private int qtdeDica = 2;
    
    
    public Combate() {

    }

//    public Player getPlayer1() {
//        return jogador;
//    }
//
//    public Player getPlayer2() {
//        return computador;
//    }

    public boolean isGameover() {
        return gameover;
    }

    public boolean isFirstPlayersTime() {
        return firstPlayersTime;
    }

    public void setGameover(boolean gameover) {
        this.gameover = gameover;
    }

//    public void setJogador(Player jogador) {
//        this.jogador = jogador;
//    }
//
//    public void setComputador(Player computador) {
//        this.computador = computador;
//    }

    public boolean isPecasPosicionadas() {
        return pecasPosicionadas;
    }

    public void setPecasPosicionadas(boolean pecasPosicionadas) {
        this.pecasPosicionadas = pecasPosicionadas;
    }
    
    public void setFirstPlayersTime(boolean firstPlayersTime) {
        this.firstPlayersTime = firstPlayersTime;
    }

    public void setTabuleiro(Tabuleiro board) {
        this.tabuleiro = board;
    }

    public boolean isJogoAleatorio() {
        return jogoAleatorio;
    }

    public void setJogoAleatorio(boolean jogoAleatorio) {
        this.jogoAleatorio = jogoAleatorio;
    }

    public int getQtdeDica() {
        return qtdeDica;
    }

    public void setQtdeDica(int qtdeDica) {
        this.qtdeDica = qtdeDica;
    }
    
    

    /**
     * Inicia um novo Jogo
     */
    public void startGame() {
        
        System.out.println("CAIU 1");
        // Cria tabuleiro
        this.tabuleiro = new Tabuleiro();
        
        this.inicialTabuleiro = new Tabuleiro();

        // Cria jogadores
        //this.createPlayers();

        // seta a vez do jogador 1 (pecas brancas)
        //this.setFirstPlayersTime(true);

        
        this.setGameover(false);

        // Cria as pecas do Jogador
        createPecasJogador();

        // Limpa todas as posicoes do tabuleiro
        this.tabuleiro.limparTabuleiro();

        // Inicializa as pe�as
        this.prepareBoard();
        
        this.inicialTabuleiro = copiaTabuleiro(this.tabuleiro);
    }
    
    public void restartGame() {

        System.out.println("CAIU 2");
        // Cria tabuleiro
        

        
        this.setGameover(false);

        // Cria as pecas do Jogador
        //createPecasJogador();

        // Limpa todas as posicoes do tabuleiro
        this.tabuleiro.limparTabuleiro();

        this.tabuleiro = copiaTabuleiro(this.inicialTabuleiro);
        
        
        // Inicializa as pe�as
        //this.prepareBoard();
    }

    /**
     * Prepara o tabuleiro com a posicao inicial das pecas
     */
    private void prepareBoard() {

        Random gerador = new Random();
        
        // Instancia um lago em uma posicao randomica da linha 3 do tabuleiro
        this.tabuleiro.setPosicao(new Lago(Color.lightGray, new Posicao(2, gerador.nextInt(5))), new Posicao(2, gerador.nextInt(5)));

        //Cria as pecas do Computador de forma randomica
        createPecasComputador();
        
        //Verifica se a distribuicao sera feito automatica ou nao
        if(jogoAleatorio){
            //Cria as pecas de forma aleatoria
            createPecaAleatoria();
            pecasPosicionadas = true;
        }
        else{
            //Cria as pecas em uma lista abaixo do tabuleiro
            // que permite o usuario posicionar.
            createPecasJogador();
        }

    }

    /**
     * Valida e realiza a jogada
     */
    public boolean play(Posicao origem, Posicao destino) {
        
        Peca peca1;
        
   
        //Verifica se as pecas do Jogador Não estao posicionadas 
        if(!pecasPosicionadas){
            //Entao permite o movimento de pecas fora do tabuleiro
            peca1 = this.tabuleiro.getPecaInicial(origem.getY());
            
            //Controla a quantidade de pecas 
            if(peca1.getQuantidade() > 0){
                this.tabuleiro.setPosicao(peca1, destino);
                peca1.setQuantidade(peca1.getQuantidade()-1);
            }
        }
        else{
            
            
            peca1 = this.tabuleiro.getPecaAtPosicao(origem);
            
                
            if(validaMovimento(origem, destino)){


                if(this.getBoard().getPecaAtPosicao(destino) == null){
                    
                    this.tabuleiro.moverPeca(origem, destino);
                    // seta a peca na posicao de destino
                    this.tabuleiro.setPosicao(peca1, destino);
                    //Seta a posicao antiga para nulo
                    this.tabuleiro.setNullPosicao(origem);
                }
                else{
                    
                    Peca pecaComp = this.getBoard().getPecaAtPosicao(destino);
                    
                    Color corAtacada = pecaComp.getColor();
                    
                    if(pecaComp.getColor().equals(peca1.getColor()))
                        return false;
                    
                    //Achando a Bandeira
                    if(pecaComp.getName().equals("Bandeira")){
                        this.setGameover(true);
                    }
                    else{
                        
                        //Desarmando a bomba
                        if(peca1.getName().equals("Armeiro") && pecaComp.getName().equals("Bomba")){
                            this.tabuleiro.setPosicao(peca1, destino);
                            this.tabuleiro.setNullPosicao(origem);
                        }else{
                            System.out.println("CASO 1");
                            if(peca1.getName().equals("Espiao") && pecaComp.getName().equals("Marechal")){
                                System.out.println("CASO 2");
                                this.tabuleiro.setPosicao(peca1, destino);
                                
                                this.tabuleiro.setNullPosicao(origem);
                            }
                            else{
                                if(peca1.getForca() > pecaComp.getForca()){
                                    System.out.println("CASO 3");
                                    this.tabuleiro.setPosicao(peca1, destino);
                                    //Seta a posicao antiga para nulo
                                    this.tabuleiro.setNullPosicao(origem);
                                }
                                else{
                                    if(peca1.getForca() == pecaComp.getForca()){
                                        System.out.println("CASO 4");
                                        //Seta a posicao antiga para nulo
                                        this.tabuleiro.setNullPosicao(origem);
                                         //Seta a posicao antiga para nulo
                                        this.tabuleiro.setNullPosicao(destino);
                                    }
                                    else{
                                        System.out.println("CASO 5");
                                        //Seta a posicao antiga para nulo
                                        this.tabuleiro.setNullPosicao(origem);

                                        pecaComp.setEscondida(false);
                                    }
                                }
                            }

                        }

                    }
                    
                    
                    if(verificaSemJogada(corAtacada)){
                        setGameover(gameover);
                    }
                }
            }
           // }
            
        }
        
        
        
        //Controle das pecas do jogo nao aleatorio
        int contadorPecas = 0;
        for(int i = 0; i < 6; i++){
            contadorPecas = contadorPecas + this.tabuleiro.getPecaInicial(i).getQuantidade();
        }
        
        if(contadorPecas == 0){
            pecasPosicionadas = true;
        }
        

        
        
        this.setFirstPlayersTime(!this.isFirstPlayersTime());
        return true;
    }

    public Tabuleiro getBoard() {
        return this.tabuleiro;
    }

    private void createPlayers() {
        
//        this.jogador = new Player("Player 1", Color.WHITE);
//        this.computador = new Player("Computador", Color.BLACK);

    }

    //Cria as pecas iniciais
    private void createPecasJogador() {
        
        //Seta a quantidade de pecas inicias dependendo do jogo
        if(jogoAleatorio){
            this.tabuleiro.iniciais.add(new Bandeira("Bandeira", Color.black, new Posicao(6, 1), 0));
            this.tabuleiro.iniciais.add(new Bomba("Bomba", Color.black, new Posicao(6, 2), 0));
            this.tabuleiro.iniciais.add(new Espiao("Espiao", Color.black, new Posicao(6, 3), 0));
            this.tabuleiro.iniciais.add(new Soldado("Soldado", Color.black, new Posicao(6, 4), 0));
            this.tabuleiro.iniciais.add(new Armeiro("Armeiro", Color.black, new Posicao(6, 5), 0));
            this.tabuleiro.iniciais.add(new Marechal("Marechal", Color.black, new Posicao(6, 6), 0));
        }
        else{
            this.tabuleiro.iniciais.add(new Bandeira("Bandeira", Color.black, new Posicao(6, 1), 1));
            this.tabuleiro.iniciais.add(new Bomba("Bomba", Color.black, new Posicao(6, 2), 2));
            this.tabuleiro.iniciais.add(new Espiao("Espiao", Color.black, new Posicao(6, 3), 1));
            this.tabuleiro.iniciais.add(new Soldado("Soldado", Color.black, new Posicao(6, 4), 3));
            this.tabuleiro.iniciais.add(new Armeiro("Armeiro", Color.black, new Posicao(6, 5), 2));
            this.tabuleiro.iniciais.add(new Marechal("Marechal", Color.black, new Posicao(6, 6), 1));
        }

    }
    
    //Cria as Pecas do Computador de forma randomica
    private void createPecasComputador(){
        
        createBombaBandeiraComp();
        
        ArrayList<Posicao> posAleatorias = new ArrayList<>();
                
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 5; j++) {
                
                Posicao pos = new Posicao(i, j);
                
                Peca peca = this.tabuleiro.getPecaAtPosicao(pos);
                
                if(peca == null){
                    posAleatorias.add(pos);
                }
            }
        }
        
        //Randomiza a lista de posicoes vazias
        Collections.shuffle(posAleatorias, new Random());
        
        this.tabuleiro.setPosicao(new Espiao(Color.blue, posAleatorias.get(0), true), posAleatorias.get(0));
        this.tabuleiro.setPosicao(new Soldado(Color.blue, posAleatorias.get(1), true), posAleatorias.get(1));
        this.tabuleiro.setPosicao(new Soldado(Color.blue, posAleatorias.get(2), true), posAleatorias.get(2));
        this.tabuleiro.setPosicao(new Soldado(Color.blue, posAleatorias.get(3), true), posAleatorias.get(3));
        this.tabuleiro.setPosicao(new Armeiro(Color.blue, posAleatorias.get(4), true), posAleatorias.get(4));
        this.tabuleiro.setPosicao(new Armeiro(Color.blue, posAleatorias.get(5), true), posAleatorias.get(5));
        this.tabuleiro.setPosicao(new Marechal(Color.blue, posAleatorias.get(6), true), posAleatorias.get(6));


    }
    
    //Cria as pecas do Jogador de forma randomica
    private void createPecaAleatoria(){
        
        createBombaBandeiraJog();
                 
        ArrayList<Posicao> posAleatorias = new ArrayList<>();
                
        for (int i = 3; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                
                Posicao pos = new Posicao(i, j);
                
                Peca peca = this.tabuleiro.getPecaAtPosicao(pos);
                
                if(peca == null){
                    posAleatorias.add(pos);
                }
            }
        }
        
        //Randomiza a lista de posicoes vazias
        Collections.shuffle(posAleatorias, new Random());
        
        this.tabuleiro.setPosicao(new Espiao(Color.black, posAleatorias.get(0)), posAleatorias.get(0));
        this.tabuleiro.setPosicao(new Soldado(Color.black, posAleatorias.get(1)), posAleatorias.get(1));
        this.tabuleiro.setPosicao(new Soldado(Color.black, posAleatorias.get(2)), posAleatorias.get(2));
        this.tabuleiro.setPosicao(new Soldado(Color.black, posAleatorias.get(3)), posAleatorias.get(3));
        this.tabuleiro.setPosicao(new Armeiro(Color.black, posAleatorias.get(4)), posAleatorias.get(4));
        this.tabuleiro.setPosicao(new Armeiro(Color.black, posAleatorias.get(5)), posAleatorias.get(5));
        this.tabuleiro.setPosicao(new Marechal(Color.black, posAleatorias.get(6)), posAleatorias.get(6));
        
        
        
    }
    
    //Cria Bomba e a Bandeira do Computador de forma randomica
    private void createBombaBandeiraComp(){
        
         Random gerador = new Random();
        
        int numero = gerador.nextInt(4);
        
        int numeroBd = 0;
        
        if(numero == 4){
            numeroBd = 3;
        }
        else{
            if(numero == 0)
                numeroBd = 1;
            else{
                numeroBd = numero + 1;
            }
        }
            
        this.tabuleiro.setPosicao(new Bandeira(Color.blue, new Posicao(0, numero), true), new Posicao(0, numero));
        this.tabuleiro.setPosicao(new Bomba(Color.blue, new Posicao(0+1, numero), true), new Posicao(0+1, numero));
        this.tabuleiro.setPosicao(new Bomba(Color.blue, new Posicao(0, numeroBd), true), new Posicao(0, numeroBd));
    }
    
    //Cria Bomba e a Bandeira do Jogador de forma randomica
    private void createBombaBandeiraJog(){
        
         Random gerador = new Random();
        
        int numero = gerador.nextInt(4);
        
        int numeroBd = 0;
        
        if(numero == 4){
            numeroBd = 3;
        }
        else{
            if(numero == 0)
                numeroBd = 1;
            else{
                numeroBd = numero + 1;
            }
        }
            
        this.tabuleiro.setPosicao(new Bandeira(Color.black, new Posicao(4, numero), false), new Posicao(4, numero));
        this.tabuleiro.setPosicao(new Bomba(Color.black, new Posicao(4-1, numero), false), new Posicao(4-1, numero));
        this.tabuleiro.setPosicao(new Bomba(Color.black, new Posicao(4, numeroBd), false), new Posicao(4, numeroBd));
    }
    
    //Valida se as pecas podem realizar o movimento
    private boolean validaMovimento(Posicao origem, Posicao destino){

       int valor = 0;

       Peca pecaSold = this.tabuleiro.getPecaAtPosicao(origem);

       if(origem.getX() == destino.getX()){
           valor = Math.abs(origem.getY() - destino.getY());
           if(valor < 2){
               return true;
           }
           else{
               //Logica para excessao do movimento do soldado
               if(verifcaCaminhoLivre(origem, destino) && pecaSold.getName().equals("Soldado"))
                   return true;

               return false;
           }
       }
       else{
           if(origem.getY() == destino.getY()){
               valor = Math.abs(origem.getX() - destino.getX());
               if(valor < 2){
                   return true;
               }
               else{

                   if(verifcaCaminhoLivre(origem, destino) && pecaSold.getName().equals("Soldado"))
                       return true;

                   return false;
               }
           }
           else{
               return false;
           }
       }
    }
    
    //Verifica o caminho livre para o Peao jogar
    private boolean verifcaCaminhoLivre(Posicao origem, Posicao destino){
        
        boolean contem = false;
        if(origem.getX() == destino.getX()){
            if(origem.getY() < destino.getY()){
                for(int i = origem.getY() + 1; i < destino.getY(); i++ ){
                    if(this.tabuleiro.getPecaAtPosicao(new Posicao(origem.getX(), i)) != null){
                        contem = true;
                    }
                }
            }
            else{
                for(int i = destino.getY() + 1; i < origem.getY(); i++ ){
                    if(this.tabuleiro.getPecaAtPosicao(new Posicao(origem.getX(), i)) != null){
                        contem = true;
                    }
                }
            }
            if(contem)
                    return false;
        }
        else{
            if(origem.getY() == destino.getY()){
                if(origem.getX() < destino.getX()){
                    for(int i = origem.getX() + 1; i < destino.getX(); i++ ){
                        if(this.tabuleiro.getPecaAtPosicao(new Posicao(i, origem.getY())) != null)
                            contem = true;
                    }
                }else
                {
                    for(int i = destino.getX() + 1; i < origem.getX(); i++){
                        if(this.tabuleiro.getPecaAtPosicao(new Posicao(i, origem.getY())) != null)
                            contem = true;
                    }
                }
                if(contem)
                    return false;
            }
        }
        
        return true;
    }

    public void movimentaComputador() {
        
        ArrayList<Posicao> posicoesUsadas = new ArrayList<Posicao>();
        
        
        
        //Varrer o tabuleiro para pegar a peca do computador
        for(int i = 4; i >= 0; i--){
            for(int j = 4; j>= 0; j--){
                Posicao origem = new Posicao(i, j);
                if(this.tabuleiro.getPecaAtPosicao(origem) != null && 
                        !this.tabuleiro.getPecaAtPosicao(origem).getName().equals("Bomba") &&
                        !this.tabuleiro.getPecaAtPosicao(origem).getName().equals("Bandeira") &&
                        !this.tabuleiro.getPecaAtPosicao(origem).getName().equals("Lago") &&
                        this.tabuleiro.getPecaAtPosicao(origem).getColor() != Color.black){

                    posicoesUsadas.add(origem);
                }
            }
        }
        
        Random gerador = new Random();
        
        Collections.shuffle(posicoesUsadas, gerador);
        
        boolean realizouJogada = false;
        
        int i = 0;
        while(!realizouJogada){
            
            Posicao origemRobo = posicoesUsadas.get(i);
            if(origemRobo.getX()+1 < 5 && origemRobo.getY() < 5 && origemRobo.getX()+1 >=0  && origemRobo.getY() >=0 ){
                
                Posicao destinoRobo = new Posicao(origemRobo.getX()+1, origemRobo.getY());
                
                if(this.play(origemRobo, destinoRobo)){
                    System.out.println("Origem: " + origemRobo.toString());
                    System.out.println("Destino: " + origemRobo.toString());
                    realizouJogada = true;
                }else{
                    destinoRobo = new Posicao(origemRobo.getX()+1, origemRobo.getY());
                    
                    if(this.play(origemRobo, destinoRobo)){
                        realizouJogada = true;
                    }
                }
            }
            i++;
            
        }
    }
    
    private Tabuleiro copiaTabuleiro(Tabuleiro tabuleiroVelho){
        
        Tabuleiro tabuleiroNovo = new Tabuleiro();
        
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if(tabuleiroVelho.tabuleiro[i][j] != null)
                    tabuleiroNovo.tabuleiro[i][j] = new Peca(tabuleiroVelho.tabuleiro[i][j]);
            }
        }
        
        tabuleiroNovo.iniciais.addAll(tabuleiroVelho.iniciais);
        
        return tabuleiroNovo;
    }
    
    
    private boolean verificaSemJogada(Color cor){
        
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if(this.tabuleiro.getPecaAtPosicao(new Posicao(i, j)) != null &&
                        this.tabuleiro.getPecaAtPosicao(new Posicao(i, j)).getColor() == cor &&
                        !this.tabuleiro.getPecaAtPosicao(new Posicao(i, j)).getName().equals("Bomba") &&
                        !this.tabuleiro.getPecaAtPosicao(new Posicao(i, j)).getName().equals("Bandeira")){
                    return true;
                }
            }
        }
        
        return false;
    }
    
}
