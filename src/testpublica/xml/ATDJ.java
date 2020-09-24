/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testpublica.xml;

import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
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
     *<b>Class Constructor</b><br>
     * ATDJ - Arquivo Tabela De Jogos<BR>
     * Responásavel pelo manuseio do arquivo xml onde os dados serão armazenados
     * No arquivo, apenas os dados de índice e de placar serão armazenados
     * Abre um arquivo pré definido
     */
    public ATDJ (String path) throws ParserConfigurationException, SAXException, IOException{

        fac = DocumentBuilderFactory.newInstance();
        builder = fac.newDocumentBuilder();
        document = builder.parse(path);
        
        NodeList nl = document.getChildNodes();
        //System.out.println(nl);
        int length = nl.getLength();
        for (int i = 0; i < length; i++) {
            if (nl.item(i).getNodeType() == Node.ELEMENT_NODE) {
                Element root = (Element) nl.item(i);
                new trace(root.getTagName());
                new trace(i);
                NodeList childs = root.getChildNodes();
                
                new trace(root.getChildNodes());
            }
        }

        NodeList jogos = document.getElementsByTagName("jogo");
        id = new int[jogos.getLength()];
        placar = new int[jogos.getLength()];
        for (int cont = 0; cont < jogos.getLength(); cont++){
            Element fdp= (Element) jogos.item(cont);
            String nome = fdp.getTextContent();
            id[cont] = Integer.parseInt(fdp.getElementsByTagName("id").item(0).getTextContent());
            placar[cont] = Integer.parseInt(fdp.getElementsByTagName("placar").item(0).getTextContent());
        }
        
    }
    /**
     *<b>Class Constructor</b><br>
     * Responásavel pelo manuseio do arquivo xml onde os dados serão armazenados
     * No arquivo, apenas os dados de índice e de placar serão armazenados
     * Cria um novo arquivo
     * @param fileName Nome do arquivo a ser criado.
     */
    public ATDJ () throws ParserConfigurationException, SAXException, IOException{
        System.out.println("ao menos passei por aqui");
    }
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
    public void saveFile(String path, Jogos jogos){
        
    }
    
        public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        ATDJ x = new ATDJ();
    }


}
