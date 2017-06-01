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
public class Armeiro extends Peca{
    
    public Armeiro(String nome, Color cor) {
        super(nome, cor, 3);
    }

    public Armeiro(String nome, Color cor, Posicao pos, int qtde) {
        super(nome, cor, pos, qtde);
    }
    
    public Armeiro(Color cor, Posicao pos, boolean escondida) {
        super(3, "Armeiro", cor, pos, escondida);
    }
    
    public Armeiro(Color cor, Posicao position) {
        super(3, "Armeiro", cor, position);
    }
    
}
