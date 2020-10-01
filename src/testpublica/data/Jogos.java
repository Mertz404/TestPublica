package testpublica.data;

import java.util.ArrayList;
import java.util.List;
import testpublica.util.trace;

/**
 *
 * @author Marcelo
 */
public class Jogos {
    public int lenght = 0;
    public List jogos = new ArrayList();
    /**
    *  <b>Class Constructor</b><br>
    * Cria uma lista de objetos do tipo Jogo.<br>
    * A lista não possuirá 0(zero) elementos.<br>
    * Poderá adicionar outros elementos com o método .add<br>
    * @see testpublica.data.Jogo
    */
    public Jogos(){
    }
    /**
     *  <b>Class Constructor</b><br>
     * Cria uma lista de objetos do tipo Jogo<br>
     * Outros elementos poderão ser adicionados com o método .add.<br>
     * @param jogo Cria a lista com 1(um) elemento incluso<br>
     * @see testpublica.data.Jogo
     */
    public Jogos (Jogo jogo){
        add(jogo);        
    }
    /**
     *  <b>Class Constructor</b><br>
     * Cria uma lista de objetos do tipo Jogo<br>
     * Outros elementos poderão ser adicionados com o método .add.<br>
     * Cria o primeiro elemento Jogo da lista
     * @param jogo - Representa o número do jogo
     * @param placar - Armazena o placar do jogo
     * @see testpublica.data.Jogo
     */
    public Jogos (int jogo, int placar){
        add(jogo, placar);        
    }
    /**
     * Método .add(Jogo jogo)
     * Adiciona um elemento do tipo jogo à lista de elementos Jogos
     * @param jogo Adiciona um elemento do tipo jogo ao final da lista
     */
    public void add(Jogo jogo){
        jogos.add(jogo);
        lenght++;
    }
    /**
     * Método .add(int jogo, int placar)
     * Cria um elemento do tipo jogo e o adiciona ao final da lista de elementos Jogos
     * @param jogo - Representa o número do jogo
     * @param placar - Armazena o placar do jogo
     */    
    public void add(int jogo, int placar){
        jogos.add(new Jogo(jogo, placar));
        lenght++;
    }
    /**
     * Método editJogo(int jogo, int placar)
     * Altera o placar de um jogo previamente cadastrado
     * @param jogo
     * @param placar 
     */
    public void editJogo(int jogo, int placar ){
        if (jogo < lenght){
            Jogo tmp = new Jogo(jogo,placar);
            jogos.set(jogo-1, tmp);
        }        
    }
    public Jogo getJogo(int index){
        Jogo jg = (Jogo)jogos.get(index);
        return jg;
    }
}
