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
public class Bomba extends Peca {

    public Bomba(String nome, Color cor) {
        super(nome, cor, 0);
    }
    
    public Bomba(String nome, Color cor, Posicao pos, int qtde) {
        super(nome, cor, pos, qtde);
    }

     public Bomba(Color cor, Posicao pos, boolean escondida) {
        super(12,"Bomba", cor, pos, escondida);
    }
    
    public Bomba(Color cor, Posicao position) {
        super(12, "Bomba", cor, position);
    }

}
