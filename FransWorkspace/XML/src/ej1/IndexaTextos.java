package ej1;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Comment;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class IndexaTextos {

	public static void main(String[] args) {
		try {
			//Creamos arbol DOM
	        DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
	        DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			
			//crea elemento raiz
            Element raiz = doc.createElement("raiz");
            doc.appendChild(raiz);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
        
	}

}
