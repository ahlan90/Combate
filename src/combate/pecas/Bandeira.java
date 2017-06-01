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
public class Bandeira extends Peca {

    public Bandeira(String nome, Color cor) {
        super(nome, cor, 0);
    }
    
    public Bandeira(String nome, Color cor, Posicao pos, int qtde) {
        super(nome, cor, pos, qtde);
    }
    
    public Bandeira(Color cor, Posicao pos, boolean escondida) {
        super(0, "Bandeira", cor, pos, escondida);
    }
    
    public Bandeira(Color cor, Posicao position) {
        super(0, "Bandeira", cor, position);
    }

}
