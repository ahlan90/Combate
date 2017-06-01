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
public class Soldado extends Peca{

    public Soldado(String nome, Color cor) {
        super(nome, cor, 2);
    }

    public Soldado(String nome, Color cor, Posicao pos, int qtde) {
        super(nome, cor, pos, qtde);
    }

    public Soldado(Color cor, Posicao pos, boolean escondida) {
        super(2,"Soldado", cor, pos, escondida);
    }
    
    public Soldado(Color cor, Posicao position) {
        super(2, "Soldado", cor, position);
    }
    
}
