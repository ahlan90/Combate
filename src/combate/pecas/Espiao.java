package combate.pecas;

import combate.model.Combate;
import combate.model.Peca;
import combate.model.Posicao;
import combate.model.Tabuleiro;
import java.awt.Color;
import java.util.ArrayList;
/**
 *
 * @author 
 */
public class Espiao extends Peca{

    public Espiao(String nome, Color cor) {
        super(nome, cor, 1);
    }
    
    public Espiao(String nome, Color cor, Posicao pos, int qtde) {
        super(nome, cor, pos, qtde);
    }
    
    public Espiao(Color cor, Posicao pos, boolean escondida) {
        super(1,"Espiao", cor, pos, escondida);
    }  

    public Espiao(Color cor, Posicao position) {
        super(1, "Espiao", cor, position);
    }
    
}
