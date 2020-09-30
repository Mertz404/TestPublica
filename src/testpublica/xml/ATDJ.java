package testpublica.xml;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import testpublica.data.Jogo;
import testpublica.data.Jogos;
import testpublica.util.trace;

/**
 *
 * @author Marcelo
 */
public class ATDJ {

    public int[] id, placar;
    private DocumentBuilderFactory fac;
    private DocumentBuilder builder;
    private Document document;
    private String path;

    /**
     * <b>Class Constructor</b><br>
     * ATDJ - Arquivo Tabela De Jogos<BR>
     * Responásavel pelo manuseio do arquivo xml onde os dados serão armazenados
     * No arquivo, apenas os dados de índice e de placar serão armazenados Abre
     * um arquivo pré definido
     *
     * @param path caminho de destino até o arquivo préexistente
     */
    public ATDJ(String path) throws ParserConfigurationException, SAXException, IOException {

        fac = DocumentBuilderFactory.newInstance();
        builder = fac.newDocumentBuilder();
        document = builder.parse(path);

        loadData();

    }

    /**
     * <b>Class Constructor</b><br>
     * Responásavel pelo manuseio do arquivo xml onde os dados serão armazenados
     * No arquivo, apenas os dados de índice e de placar serão armazenados Cria
     * um novo arquivo
     *
     * @param fileName Nome do arquivo a ser criado.
     */
    public ATDJ() throws ParserConfigurationException, SAXException, IOException {
        System.out.println("ao menos passei por aqui");
        fac = DocumentBuilderFactory.newInstance();
        builder = fac.newDocumentBuilder();
        document = builder.newDocument();
    }

    /**
     * Função getSaveFileContent()<br>
     * Reorna um objeto do tipo Jogos criado a partir dos dados armazenados no
     * arquivo aberto.<br>
     *
     * @return Um objeto do tipo Jogos
     * @see testpublica.data.Jogos
     */
    public Jogos getSaveFileContent() {
        Jogos jogos = new Jogos();

        NodeList nl = document.getElementsByTagName("jogo");
        id = new int[nl.getLength()];
        placar = new int[nl.getLength()];
        for (int cont = 0; cont < nl.getLength(); cont++) {
            Element fdp = (Element) nl.item(cont);
            String nome = fdp.getTextContent();
            id[cont] = Integer.parseInt(fdp.getElementsByTagName("id").item(0).getTextContent());
            placar[cont] = Integer.parseInt(fdp.getElementsByTagName("placar").item(0).getTextContent());
            jogos.add(id[cont], placar[cont]);
        }
        return jogos;
    }

    /**
     * Método saveFile(String path, Jogos jogos)<br>
     * Salva no arquivo informado o conteúdo do objeto Jogos.<br>
     *
     * @param path caminho para o arquivo.
     * @param jogos variavel do tipo Jogos com a informação a ser salva.
     * @throws TransformerConfigurationException
     * @throws TransformerException
     * @throws ParserConfigurationException
     * @throws SAXException
     * @throws IOException
     */
    public void saveFile(String path, Jogos jogos) throws TransformerConfigurationException, TransformerException, ParserConfigurationException, SAXException, IOException {

        try {
            //coleta o nodelist raiz
            NodeList nl = document.getElementsByTagName("TabelaDeJogos");
            //isola o elemento raiz
            Element fdp = (Element) nl.item(0);
            //remove o elemento raiz do arquivo
            document.removeChild(fdp);
        } catch (Exception ers) {
            new trace("ATDJ - erro ao tentar carregar o elemento raiz do arquivo\n"+ers);
            new trace(">>Provável causa do erro - criando um arquivo novo<<");

        }

        // cria o novo elemento raiz
        Element root = document.createElement("TabelaDeJogos");
        document.appendChild(root);

        // elemento jogos 
        Element jogosz = document.createElement("Jogos");

        root.appendChild(jogosz);

        for (int cont = 0; cont < jogos.lenght; cont++) {
            Jogo m;
            m = (Jogo) jogos.jogos.get(cont);
            Element j = document.createElement("jogo");
            Element id = document.createElement("id");
            id.appendChild(document.createTextNode("" + m.jogo));
            Element p = document.createElement("placar");
            p.appendChild(document.createTextNode("" + m.placar));
            j.appendChild(id);
            j.appendChild(p);
            jogosz.appendChild(j);
        }

        // transforma o objeto DOM em um arquivo XML
        // cria o arquivo XML
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource domSource = new DOMSource(document);
        StreamResult streamResult = new StreamResult(new File(path));

        transformer.transform(domSource, streamResult);
    }

    /**
     * Carrega os dados nos vetores públicos com o conteúdo do arquivo.<br>
     * Os valores poderão ser acessados por meio dos vetores id[] e
     * placar[].<br>
     */
    public void loadData() {

        NodeList jogos = document.getElementsByTagName("jogo");

        id = new int[jogos.getLength()];
        placar = new int[jogos.getLength()];
        for (int cont = 0; cont < jogos.getLength(); cont++) {
            Element fdp = (Element) jogos.item(cont);
            String nome = fdp.getTextContent();
            id[cont] = Integer.parseInt(fdp.getElementsByTagName("id").item(0).getTextContent());
            placar[cont] = Integer.parseInt(fdp.getElementsByTagName("placar").item(0).getTextContent());
        }
    }
}
