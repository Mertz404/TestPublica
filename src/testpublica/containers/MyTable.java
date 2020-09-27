package testpublica.containers;
 
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import javax.swing.JScrollBar;
import javax.swing.table.DefaultTableModel;
import testpublica.data.ControleRecordes;
import testpublica.data.Jogo;
import testpublica.data.Jogos;
import testpublica.util.trace;
 /**
 *
 * @author Marcelo Augusto Mertz
 */
public class MyTable extends JPanel {
    
    private JTable table;
    private DefaultTableModel model;
    private JScrollPane scrollPane;
    public int lenght;
    //private Jogos jogos = new Jogos();
    public ControleRecordes cRec = new ControleRecordes();
    
    /**
     * <b>Class Constructor</b><br>
     * Cria uma nova classe de MyTable para exibição de dados.<br>
     * A classe será criada com 0(zero) linhas, apenas com os identificadores das colunas.<br>
     * As linhas deverão ser adicionadas uma a uma pelo método <b>addRow</b>.
     * 
     * @see addRow
     * @param Header Vetor com os identificadores das colunas da tabela.
     */
    public MyTable(String[] Header) {
        model = new DefaultTableModel();
        table = new JTable(model);
        
        //cria as colunas para a tabela
        for (int cont = 0; cont < Header.length; cont++){
            model.addColumn(Header[cont]);
        }
        
        //configura o tamanho padrão para a tabela
        table.setPreferredScrollableViewportSize(new Dimension(600,100));
        table.setFillsViewportHeight(true);
        
        //adiciona a tabela em um painel com barra de rolagem
        scrollPane = new JScrollPane(table);        
        add(scrollPane);
    }
    /**
     * Adiciona uma linha ao final da tabela
     * @param str um vetor do tipo String contendo todos os dados para 
     *            preenchimento de uma linha.<br>
     *            Excesso ou falta de dados serão desprezados (apenas 
     *            uma mensagem no console será exibida).<br>
     */
    public void addRow(String[] str){
        if (str.length != model.getColumnCount()){
            System.out.println("Linha inserida com quantidade de dados diverso do número de colunas\n"
                    + "verifique o comprimento da linha inserida");
        }
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        Object[] linha = new Object[str.length];
        for (int cont = 0; cont <str.length; cont++){
            linha[cont] = str[cont];
        }
        model.addRow(linha);
        
        JScrollBar vertical = scrollPane.getVerticalScrollBar();
        vertical.setValue( vertical.getMaximum() );
    }
    public void editTableLine(int jg, int pl) {
        cRec.editContent(jg, pl);
        clearTable();
        for (int cont = 0; cont < cRec.lenght;cont++){
            addRow(cRec.getTableLineAsString(cont));
        }
    }
    
    public void clearTable(){
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowCount = model.getRowCount();
        //Remove rows one by one from the end of the table
        for (int i = rowCount - 1; i >= 0; i--) {
            model.removeRow(i);
        }        
    }
    
    public void addJogo(int jogo, int placar){
        cRec.addTableLine(jogo, placar);
        addRow(cRec.getTableLineAsString(jogo-1));
        lenght++;
        
    }
    public Jogos getJogos(){
        return cRec.jogos;
    }
}

