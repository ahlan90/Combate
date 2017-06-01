package combate.visao;

import combate.model.Combate;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author 
 */
public class CombateGUI extends javax.swing.JFrame {
    
    
    // Modelo do jogo
    private Combate combate;
    
    // Tabuleiro da tela
    private TabuleiroGUI pnTabuleiro;
    
    
    // Pe�as mortas
    
    /** Creates new form JogoTetrisGUI */
    public CombateGUI() {
        
        this.combate = new Combate();
        initComponents();
        
        this.pnTabuleiro = new TabuleiroGUI(this.combate);
        
        
        getContentPane().add(this.pnTabuleiro, java.awt.BorderLayout.CENTER);
        setSize(350,450);
        
        //Inicia Jogo
        this.startGame();
    }
    
    public void startGame(){
        
        this.combate.startGame();
        
        this.pnTabuleiro = new TabuleiroGUI(this.combate);       
        
        this.repaint();
    }
    
    public void restartGame(){
        
        this.combate.restartGame();
        
        this.pnTabuleiro = new TabuleiroGUI(this.combate);       
        
        this.repaint();
    }
    
    public Combate getCombate() {
        return this.combate;
    }
    
    
    
    private void initComponents() {
        
        this.setResizable(false);

        jMenuBar1 = new javax.swing.JMenuBar();
        mnJogo = new javax.swing.JMenu();
        mnIniciar = new javax.swing.JMenuItem();
        mnReiniciar = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
       
        mnSair = new javax.swing.JMenuItem();
        
        
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
       
      
        
        mnJogo.setText("Jogo");
        mnIniciar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        mnIniciar.setText("Iniciar");
        mnIniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnIniciarActionPerformed(evt);
            }
        });
        
        mnReiniciar.setText("Reiniciar");
        mnReiniciar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnReiniciarActionPerformed(evt);
            }
        });
        
        mnJogo.add(mnReiniciar);
        mnJogo.add(mnIniciar);
        mnJogo.add(jSeparator1);
        mnJogo.add(mnSair);
        
        mnSair.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, 0));
        mnSair.setText("Sair");
        mnSair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mnSairActionPerformed(evt);
            }
        });
        
        jMenuBar1.add(mnJogo);
        
        setJMenuBar(jMenuBar1);
        
        // Mensagem de boas vindas
        String s = " Bem Vindo \n "+
                   "Peças Aleatórias? ";
        
        // Resposta da Tela
        int modo = javax.swing.JOptionPane.showConfirmDialog(this, s);
        
        
        
        // Tela inicial de Boas vindas
        if(modo == 0)
            this.combate.setJogoAleatorio(true);
        else{
            if(modo == 1){
                this.combate.setJogoAleatorio(false);
            }
            else{
                System.exit(0);
            }
        }
        
        pack();
    }
    

    
    
    private void mnIniciarActionPerformed(java.awt.event.ActionEvent evt) {
        this.startGame();
    }
    
    private void mnReiniciarActionPerformed(java.awt.event.ActionEvent evt) {
        this.restartGame();
    }
    
    private void mnSairActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CombateGUI().setVisible(true);
            }
        });
    }
    
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JMenuItem mnIniciar;
    private javax.swing.JMenuItem mnReiniciar;
    private javax.swing.JMenu mnJogo;
    private javax.swing.JMenuItem mnSair;
    
}
