package testpublica.data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Marcelo Augusto Mertz
 */
public class ControleRecordes {

    private int jg, placar;
    private int recordMinimo = 0;
    private int recordMaximo = 0;
    private int minimo = 999;
    private int maximo = 0;
    public int lenght = 0;
    private List Tabela = new ArrayList();
    public Jogos jogos = new Jogos();

    /**
     * <b>Class Constructor</b><br>
     * Controla os recordes, valores minimo e maximo dos dados fornecidos.<Br>
     * O usuário apenas precisa informar o índice(jogo) e a quantidade de pontos
     * marcados.<br>
     * Os demais valores são calculados e armazenados na Class Lista -
     * Tabela.<br>
     * Cada linha ja com os valores calculados será armazenada na Class
     * TableLine.<br>
     *
     * @see testpublica.TableLine
     */
    public ControleRecordes() {
    }

    /**
     * <b>Class Constructor</b><br>
     * Controla os recordes, valores minimo e maximo dos dados fornecidos.<Br>
     * O usuário apenas precisa informar o índice(jogo) e a quantidade de pontos
     * marcados.<br>
     * Os demais valores são calculados e armazenados na Class Lista -
     * Tabela.<br>
     * Cada linha ja com os valores calculados será armazenada na Class
     * TableLine.<br>
     *
     * @param jgo Índice, valor único na tabela que identifica o jogo.<br>
     * Entretanto, não há controle na classe para que o valor seja único.
     * @param pl Quantidade de pontos marcados no jogo.
     * @see TableLine
     */
    public ControleRecordes(int jgo, int pl) {
        this.jg = jgo;
        placar = pl;
        if (pl < minimo) {
            minimo = pl;
            recordMinimo++;
        }
        if (pl > maximo) {
            maximo = pl;
            recordMaximo++;
        }
        Tabela.add(new TableLine(jg, placar, minimo, maximo, recordMinimo, recordMaximo));
        lenght++;
    }

    /**
     * Adiciona uma linha à tabela
     *
     * @param jgo Índice, valor único na tabela que identifica o jogo
     * @param pl Quantidade de pontos marcados no jogo
     */
    public void addTableLine(int jgo, int pl) {
        //restringe o método aceitar apenas o valor único e subsequente ao anterior
        if (jgo == lenght + 1) {
            jogos.add(jgo, pl);
            this.jg = jgo;
            placar = pl;
            if (pl < minimo) {
                minimo = pl;
                if (lenght > 0){
                    recordMinimo++;
                }
            }
            if (pl > maximo) {
                maximo = pl;
                if (lenght > 0){
                    recordMaximo++;
                }
            }
            Tabela.add(new TableLine(jg, placar, minimo, maximo, recordMinimo, recordMaximo));
            lenght++;
        }
    }
    /**
     * Recalcula os valores de recorde mínimo, recorde maximo, e quebras de recordes.<br>
     * Função utilizada em caso de edição do conteúdo da tabela.
     */
    public void Recalculate() {
        Tabela.clear();
        lenght = 0;

        recordMinimo = 0;
        recordMaximo = 0;
        minimo = 999;
        maximo = 0;
        Jogo jogo;
        for (int cont = 0; cont < jogos.lenght; cont++) {
            jogo = (Jogo) jogos.jogos.get(cont);
            this.jg = jogo.jogo;
            placar = jogo.placar;
            if (jogo.placar < minimo) {
                minimo = jogo.placar;
                if (lenght > 0){
                    recordMinimo++;
                }
            }
            if (jogo.placar > maximo) {
                maximo = jogo.placar;
                if (lenght > 0){
                    recordMaximo++;
                }
            }
            Tabela.add(new TableLine(jg, placar, minimo, maximo, recordMinimo, recordMaximo));
            lenght++;
        }
    }

    /**
     * Edita o conteúdo de uma linha da tabela
     *
     * @param pos Índice da tabela que sofrerá alteração
     * @param tl Dados da linha
     */
    public void editContent(int jg, int pl) {
        jogos.editJogo(jg, pl);
        Recalculate();

    }

    /**
     * @return Tabela - o conteúdo completo armazenado na classe no formato de
     * Lista
     */
    public List getTabela() {
        return Tabela;
    }

    /**
     * @param id Índice da linha desejada
     * @return TableLine contendo os dados da linha desejada
     */
    public TableLine getTableLine(int id) {
        return (TableLine) Tabela.get(id);
    }

    /**
     * Converte o formato da classe de armazenamento TableLine para um Vetor de
     * Strings necessário para acrescentar linhas à tabela visual MyTable
     *
     * @param id Índice da linha desejada
     * @return um vetor de String com os valores da linha desejada
     */
    public String[] getTableLineAsString(int id) {
        TableLine tl = (TableLine) Tabela.get(id);
        String jogo = "" + tl.getJogo();
        String placar = "" + tl.getPlacar();
        String minimo = "" + tl.getMinimo();
        String maximo = "" + tl.getMaximo();
        String rN = "" + tl.getRecorMinimo();
        String rX = "" + tl.getRecordMaximo();
        String[] str = {jogo, placar, minimo, maximo, rN, rX};
        return str;
    }
}
