
package testpublica.data;

import java.util.List;
import testpublica.util.trace;

/**
 *
 * @author Marcelo
 */
public class Jogo {
    public int jogo, placar;
    /**
    * <b>Class Constructor</b><br>
    * Objeto resposável pelo armazenamento do número do jogo e seu respectivo placar
    * 
    * @param jogo - Representa o número do jogo (inteiro maior que zero).
    * @param placar - Armazena o placar do jogo (inteiro maior ou igual a zero e inferior a 1000).
    */
    public Jogo (int jogo, int placar){
        if ((jogo> 0)&&((placar >=0)&&(placar < 1000))){
            this.jogo = jogo;
            this.placar = placar;
        }        
    }
    
}
