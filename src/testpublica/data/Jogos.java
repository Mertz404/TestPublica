package testpublica.data;

import java.util.ArrayList;
import java.util.List;
import testpublica.util.trace;

/**
 *
 * @author Marcelo
 */
public class Jogos {
    public int lenght;
    public List jogos = new ArrayList();
    public Jogos(){
        
    }
    public Jogos (Jogo jogo){
        jogos.add(jogo);
        lenght++;
    }
    public void add(Jogo jogo){
        jogos.add(jogo);
        lenght++;
    }
    public void add(int jogo, int placar){
        jogos.add(new Jogo(jogo, placar));
        lenght++;
    }
    public void editJogo(int jogo, int placar ){
        Jogo tmp = new Jogo(jogo,placar);
        jogos.set(jogo-1, tmp);
    }
}
