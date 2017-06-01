    package combate.visao;

import combate.model.Combate;
import combate.model.Peca;
import combate.model.Posicao;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

public class TabuleiroGUI extends javax.swing.JPanel implements MouseListener  {
    
    
    public static final int TAMANHO_LINHA = 250;
    public static final int TAMANHO_COLUNA = 250;
    
    public static final int ALTURA_CELULA = TAMANHO_LINHA/5;
    public static final int LARGURA_CELULA = TAMANHO_COLUNA/5;
       
    public static final String CLARAS = File.separator + "fundo_claro" + File.separator;
    
    private String path;
    
    private Posicao origemJogada = null;
    private Posicao destinoJogada = null;
    
    private Color c1 = Color.white;
    private Color c2 = Color.green;
    
    private Posicao selected = null;
    
    private Combate combate;
    
    public boolean vezPc = false;
    
    public TabuleiroGUI(Combate combate) {
        
        this.combate = combate;
        
        
        File f = new File(".");
        this.origemJogada = null;
        this.destinoJogada = null;
        
        this.setPath(f.getAbsolutePath() + File.separator +"img");
        
        addMouseListener(this);
        
        initComponents();
        
    }
    
    // Pega Selcao 
    public Posicao getSelected() {
        return selected;
    }
    
    // Seta selecao
    public void setSelected(Posicao selected) {
        this.selected = selected;
    }
    
    // Pinta o tabuleiro
    public void paint(Graphics g){
        
       
        super.paint(g);

        // Desenha o quadrado principal
        g.fillRect(0,0, TAMANHO_COLUNA, TAMANHO_LINHA);

        // Definir tamanho dos quadrados
        int ladoLinha = TAMANHO_LINHA / 5;
        int ladoColuna = TAMANHO_COLUNA / 5;

        // Variavel que contem a cor
        String auxColor = this.CLARAS;

        // Variavel que conter� o endere�o da imagem
        String image;



        System.out.println("");
        System.out.println("As pecas do computador sao representadas por . e o jogador por x");
        System.out.println("Tabuleiro:");
        // Faz o desenho do tabuleiro
        for (int lin=0;lin<5;lin++){
            System.out.println("");
            for (int col=0;col<5;col++){

                Peca piece = this.combate.getBoard().getPecaAtPosicao(new Posicao(lin,col));

                if (piece == null){
                    System.out.print("  *  ");
                    image = "fundo.jpg";
                }
                else{
                    if (piece.getColor() == Color.black){
                        System.out.print(" " + piece.getName().substring(0,2)+ ". ");
                        image = "marron" + File.separator +piece.getName()+".jpg";
                    }else{
                        if (piece.getColor() == Color.blue){
                            System.out.print(" " + piece.getName().substring(0,2)+ "x ");
                            if(piece.isEscondida())
                                image = "azul" + File.separator + "escondida.jpg";
                            else{
                                image = "azul" + File.separator +piece.getName()+".jpg";
                            }
                        }
                        else{
                            System.out.print(" La ");
                            image = "lago.jpg";
                        }
                    }
                }
                // Instrucao que faz desenho
                g.drawImage(this.getImage(path+auxColor+image),1+(col*ladoColuna),1+(lin*ladoLinha),ladoColuna-1,ladoLinha-1,null);
            }

        }

        // Destaca a peca selecionada
        g.setColor(Color.BLACK);
        if (this.getSelected() != null){
            g.drawRect((this.getSelected().getY()*ladoLinha),(this.getSelected().getX()*ladoColuna), ladoLinha, ladoColuna);
            g.drawRect(1+(this.getSelected().getY()*ladoLinha), 1+(this.getSelected().getX()*ladoColuna),  ladoLinha-2 ,ladoColuna-2);
        }


        // Pega pecas iniciais
        ArrayList <Peca> ls = new ArrayList<>();

        for(int i = 0; i < 6 ; i++){
            ls.add(this.combate.getBoard().getPecaInicial(i));
        }

        // Escreve o t�tulo
        g.setColor(Color.BLUE);
        g.setFont(new Font("Times", Font.BOLD+Font.PLAIN, 20));
        g.drawString("Insira suas peças: ",5,275);


        // Desenha as qtde pecas inicias na tela
        g.setColor(Color.BLACK);
        g.setFont(new Font("Times", Font.BOLD+Font.PLAIN, 15));
        int qX = 15;
        for (Peca p: ls){
            g.drawString(p.getQuantidade()+"x",qX,295);

            qX+=50;
        }

        // Inicializa as posicoes
        int cX=0;
        int cY=300;

        // Desenha as pecas inicias na tela
        for (Peca p: ls){
            p.getName();
            image = path + File.separator + "fundo_claro" + File.separator + "marron" + File.separator + p.getName()+".jpg";

            g.drawImage(this.getImage(image),cX,cY,50,50,null);

            cX+=50;
        }

        g.setColor(Color.BLACK);
        g.setFont(new Font("Times", Font.BOLD+Font.PLAIN, 15));
        g.drawString("Numero de Dicas: " + this.combate.getQtdeDica(),5,380);
        
    }
    
    // Le uma imagem do disco e transforma em BufferedImage 
    public BufferedImage getImage(String path){
        
        File inputFile = new File(path);
        try {
            BufferedImage imagem = ImageIO.read(inputFile);
            
            return imagem;
            
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    
    public void positionClicked(Posicao pos){
        
        // Identifica se e jogada de Origem ou de Destino
        if (this.getOrigemJogada() == null) {
            System.out.println("");
            System.out.println("Entrou 2");
            
            //Controle da Captura das pecas que nao estao posicionadas
            if(!this.combate.isPecasPosicionadas()){
                this.setOrigemJogada(pos);
                this.setSelected(pos);
                this.repaint();
            }
            else{
                
                //Verifica se pegou uma peca
                if (!this.combate.getBoard().isNullPosicao(pos)){
                    
                    Peca pecaO = this.combate.getBoard().getPecaAtPosicao(pos);
                    //Verifica se esta peca e a peca do jogador
                    if (pecaO.getColor() == Color.black){
                        //Verifica se nao e bandeira ou Bomba
                        if(!(pecaO.getName().equals("Bomba") || pecaO.getName().equals("Bandeira"))){
                            this.setOrigemJogada(pos);
                            this.setSelected(pos);
                        }
                    }
                    else{
                        
                        if(this.combate.getQtdeDica() > 0){
                            String s = "Deseja usar sua dica?";
                            // Resposta da Tela
                            int modo = javax.swing.JOptionPane.showConfirmDialog(this, s);
                            //Implementar a dica aqui
                            if(modo == 0){
                                if(pecaO.getName().equals("Bomba")){
                                    pecaO.setEscondida(false);
                                }
                                this.combate.setQtdeDica(this.combate.getQtdeDica()-1);
                            }
                        }
                    }
                    
                    this.repaint();
                }
            }
        } 
        else{
            
            if (this.getDestinoJogada() == null){
                
                //Controle do movimento das ainda naoPosicionadas
                if(!this.combate.isPecasPosicionadas()){

                    if(pos.getX() > 2 && pos.getY() < 5){
                        
                        this.setDestinoJogada(pos);

                        this.combate.play(this.getOrigemJogada(), this.getDestinoJogada());
                        
                        this.setOrigemJogada(null);

                        this.setDestinoJogada(null);

                        this.setSelected(null);

                        this.repaint();
                    }
                    else{
                        this.setOrigemJogada(null);

                        this.setDestinoJogada(null);

                        this.setSelected(null);

                        this.repaint();
                    }
                }
                else{
                    
                    if(this.combate.getBoard().getPecaAtPosicao(pos) == null ||
                            this.combate.getBoard().getPecaAtPosicao(pos).getColor() == Color.blue){
                        
                        this.setDestinoJogada(pos);
                    
                        boolean jogou = this.combate.play(this.getOrigemJogada(),getDestinoJogada());
                        
                        if(jogou){
                            
                            vezPc = true;
                            
                            
                            if(this.combate.isGameover()){
                                
                                String ganhador = "";
                                
                                if(this.combate.getBoard().getPecaAtPosicao(this.origemJogada).getColor().equals(Color.black))
                                    ganhador = "Voce";
                                else
                                    ganhador = "Computador";
                                
                                String s = ganhador + " ganhou! \n" + "Deseja Reinicar?";
                                // Resposta da Tela
                                int modo = javax.swing.JOptionPane.showConfirmDialog(this, s);

                                if(modo == 0){
                                    this.combate.startGame();
                                }
                                else{
                                    System.exit(0);
                                }
                                
                            }

                            this.setOrigemJogada(null);

                            this.setDestinoJogada(null);

                            this.setSelected(null);

                            this.repaint();

                            if(vezPc){

                                this.combate.movimentaComputador();

                                this.setOrigemJogada(null);

                                this.setDestinoJogada(null);

                                this.setSelected(null);

                                this.repaint();

                                vezPc = false;
                            }
                        }
                        
                        
                        
                    }
                    
                    // Deseleciona a peca
                    if(this.origemJogada != null && this.getOrigemJogada().equals(pos)){
                        
                        this.setOrigemJogada(null);
                        
                        this.setDestinoJogada(null);

                        this.setSelected(null);

                        this.repaint();
                    }

                }
            
            }
        }
    }
    
    public Posicao calcPosition(int x, int y){
        Posicao pos;
        pos = new Posicao((int)(x/ALTURA_CELULA),(int)(y/LARGURA_CELULA));
        
        return pos;
    }
    
    public String getPath() {
        return this.path;
    }
    
    public void setPath(String path) {
        this.path = path;
    }
    
    public Posicao getOrigemJogada() {
        return origemJogada;
    }
    
    public Posicao getDestinoJogada() {
        return destinoJogada;
    }
    
    public void setOrigemJogada(Posicao origemJogada) {
        this.origemJogada = origemJogada;
    }
    
    public void setDestinoJogada(Posicao destinoJogada) {
        this.destinoJogada = destinoJogada;
    }
    
    // Metodos da interface MouseListener, redefinidos para a classe TabuleiroGUI:
    
    public void mouseClicked(MouseEvent e) {
        //this.positionClicked(this.calcPosition(e.getX(),e.getY()));
    }
    
    public void mousePressed(MouseEvent e) {
        
        //Jogadas normais dentro do tabuleiro
        if (( e.getX()<=this.TAMANHO_COLUNA) && (e.getY()<=this.TAMANHO_LINHA) ){
            this.positionClicked(this.calcPosition(e.getY(),e.getX()));
        }
        
        //Pecas Iniciais, So acessa o menu das pecas quando o jogo nao e Aleatorio e as pecas nao estao posicionadas
        if(!this.combate.isJogoAleatorio() && !this.combate.isPecasPosicionadas()){
            if(((e.getY() >= 300) && (e.getY() < 350)) && (e.getX() >= 0 && e.getX() <= 300)){

                this.positionClicked(this.calcPosition(e.getY(),e.getX()));
            }
        }
        
    }
    
    public void mouseEntered  (MouseEvent e) {}
    public void mouseExited   (MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">
    private void initComponents() {
        
        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(0, 400, Short.MAX_VALUE)
                );
        layout.setVerticalGroup(
                layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                .add(0, 300, Short.MAX_VALUE)
                );
    }// </editor-fold>
    
    
    // Variables declaration - do not modify
    // End of variables declaration
    
}

