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
public class Marechal extends Peca {

    public Marechal(String nome, Color cor) {
        super(nome, cor, 10);
    }
    
    public Marechal(String nome, Color cor, Posicao pos, int qtde) {
        super(nome, cor, pos, qtde);
    }
    
    public Marechal(Color cor, Posicao pos, boolean escondida) {
        super(10, "Marechal", cor, pos, escondida);
    }  

    public Marechal(Color cor, Posicao position) {
        super(10, "Marechal", cor, position);
    }
    
}
