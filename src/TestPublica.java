
import testpublica.containers.MyTable;
import java.awt.Color;
import java.awt.TextField;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import testpublica.data.ControleRecordes;
import testpublica.xml.ATDJ;
import testpublica.containers.MyJPanel;
import testpublica.util.trace;

/**
 *
 * @author Marcelo
 */
public class TestPublica extends JFrame implements MouseListener {

    MyJPanel fundo;
    MyTable myTable;
    ATDJ fileSave;

    /**
     * <b>Class Constructor</b><br>
     * Teste de Desafio Publica. Objetiva criação de um aplicativo para controle
     * de dados e "facilitar o acompanhamento de resultados de Maria".
     *
     * O termo <b>facilitar</b> e os únicos valores relevantes que o usuário
     * deve fornecer são Jogo(índice) e placar(dado processável), este
     * aplicativo restringe o usuário quanto a alimentação de dados, forçando-o
     * a alimentar indice acompanhado de placar em ordem, os demais dados são
     * processados a partir dos dois iniciais. Ainda, como o objetivo é
     * <b>Facilitar</b> e Maria deseja utilziar este aplicativo durante um longo
     * período, foi implementado um sistema de salvamento de dados em um arquivo
     * xml.
     *
     * Maria pode errar ao informar os dados, pois foi implementada edição ou 
     * exclusão de dados!
     * 
     * Porém Maria tem que se esforçar para visualizar a tabela, pois não tem 
     * gráfico exibindo sua progressão.
     *
     */
    public TestPublica() {
        /**
         * *********************************************
         * Cria o layout básico da janela principal *
         * *********************************************
         */
        this.setLayout(null);
        this.setTitle("Controle de placar");
        this.setSize(640, 380);
        this.setResizable(false);
        //this.setUndecorated(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        /**
         * *********************************************
         * Cria o fundo da janela principal *
         * *********************************************
         */
        fundo = new MyJPanel();
        fundo.setBackground(new Color(255, 175, 175, 15));
        fundo.setSize(this.getWidth(), this.getHeight());
        fundo.setLayout(null);

        //cRec = new ControleRecordes();
        //Prepare Table to show Data
        String[] columnNames = {"Jogo", "Placar", "mín. da Temp",
            "Máx. da Temp", "Qbr Rec. Min.",
            "Qbr. Rec. Máx."};
        myTable = new MyTable(columnNames);
        myTable.setName("myTableBasquetballGames");
        myTable.setLocation(5, 65);
        myTable.setSize(600, 250);
        fundo.add(myTable);

        //Create hud to insert data
        String[] rotulo = {"Jogo", "Placar"};
        int posX, posY, espH;
        posX = 5;
        posY = 5;
        espH = 5;
        JLabel lbl;
        JTextField txt;
        for (int cont = 0; cont < rotulo.length; cont++) {
            lbl = new JLabel(rotulo[cont]);
            lbl.setName("lbl" + rotulo[cont]);
            lbl.setSize(45, 25);
            lbl.setLocation(posX, posY);
            txt = new JTextField();
            txt.setName("txt" + rotulo[cont]);
            txt.setSize(45, 25);
            txt.setLocation(posX, posY + lbl.getHeight() + espH);
            fundo.add(lbl);
            fundo.add(txt);
            posX += 45 + espH;
        } //txt.setText("1");
        JButton btn = new JButton("Confirmar");
        btn.setName("btnAdcDados");
        btn.setSize(100, 25 * 2 + espH);
        btn.setLocation(posX, posY);
        btn.addMouseListener(this);
        fundo.add(btn);
        posX += btn.getWidth() + espH;
        btn = new JButton("Salvar");
        btn.setName("btnSaveData");
        btn.setSize(100, 25 * 2 + espH);
        btn.setLocation(posX, posY);
        btn.addMouseListener(this);
        fundo.add(btn);

        // try to locate any previous saved data into xml file
        fileSave = null;
        try {
            fileSave = new ATDJ("src/TabelaDeJogos.xml");
            for (int c = 0; c < fileSave.id.length; c++) {
                myTable.addJogo(fileSave.id[c], fileSave.placar[c]);
            }
        } catch (Exception err) {
            new trace("Filesave not found!\nCreating a new one.");
            try {
                fileSave = new ATDJ("duhh");
                new trace("implementar");
            } catch (Exception errr) {
                new trace("Deu ruim!\n" + errr);
            }
        }

        this.add(fundo);

    }

    public static void main(String[] args) {
        TestPublica x = new TestPublica();
        x.setVisible(true);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        String trigger = "";
        //identifica o tipo de elemento que acionou o evento
        if (e.getComponent() instanceof JButton) {
            JButton btnTrigger = (JButton) e.getComponent();
            trigger = btnTrigger.getName();
        }
        //com base no nome, verifica qual elemento específico acionou o evento 
        //e realiza as devidas operações
        if (trigger.equals("btnAdcDados")) {
            int jogo = 0, placar = 0;
            boolean bJo = false, bPl = false, editing = false;
            //localiza componente por nome para facilitar a captura do dado
            JTextField txt1 = (JTextField) fundo.getComponentByName("txtJogo");
            try {
                //certifica que o dado é um numero inteiro válido
                jogo = Integer.parseInt(txt1.getText());
                if (jogo > 0) {
                    if (jogo == (myTable.lenght + 1)) {
                        bJo = true;
                    } else if (jogo > (myTable.lenght + 1)) {

                        JOptionPane.showMessageDialog(this, "O ultimo jogo cadastrado foi o de número " + (myTable.lenght) + "\nFavor cadastre os jogos anteriores à este");
                    } else {
                        String str = "Deseja editar o placar do jogo " + jogo + "?";
                        int n = JOptionPane.showConfirmDialog(myTable, str, "Atenção", JOptionPane.YES_NO_OPTION);
                        if (n == JOptionPane.YES_OPTION) {
                            bJo = true;
                            editing = true;
                        }
                    }
                } else {
                    bJo = false;
                    txt1.setText("");
                    JOptionPane.showMessageDialog(this, "O número do Jogo deve ser positivo");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Campo Jogo deve conter um número inteiro válido");
                txt1.setText("");
            }
            JTextField txt2 = (JTextField) fundo.getComponentByName("txtPlacar");
            try {
                placar = Integer.parseInt(txt2.getText());
                if ((placar < 0) || (placar > 999)) {
                    JOptionPane.showMessageDialog(this, "Valor númérico inválido");
                    txt2.setText("");
                } else {
                    bPl = true;
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Campo placar deve conter um número inteiro válido");
            }

            if (bJo && bPl) {
                if (!editing) {
                    myTable.addJogo(jogo, placar);
                    txt1.setText((jogo + 1) + "");
                    txt2.setText("");
                } else {
                    myTable.editTableLine(jogo, placar);
                    txt1.setText("");
                    txt2.setText("");
                }
            }

        } else if (trigger.equals("btnSaveData")) {
            try {
                fileSave.saveFile("src/TabelaDeJogos.xml", myTable.getJogos());
            } catch (Exception ex) {
            };
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }
}
