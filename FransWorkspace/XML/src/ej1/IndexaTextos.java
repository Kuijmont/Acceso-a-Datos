package ej1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

public class IndexaTextos {

	public static void main(String[] args) throws DOMException, IOException {
		try {
			String ruta = "/home/alumno/biblioteca";
			
			ArrayList <String> al = sacarRutas(ruta);
			ArrayList <String> nombre = new ArrayList<String>();
			String file,rut = null;
			for (int i = 0; i < al.size(); i++) {
				rut = al.get(i).toString();
				file =rut.substring(rut.lastIndexOf('/') + 1);
				nombre.add(file);
			}
				
			
			
			//Creamos arbol DOM
	        DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
	        DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
			Document doc = docBuilder.newDocument();
			
			//crea elemento raiz
            Element raiz = doc.createElement("biblioteca");
            doc.appendChild(raiz);
            
	         for (int i = 0; i < al.size(); i++) {	
	        	BufferedReader bfr = new BufferedReader(new FileReader(new File(al.get(i).toString())));
	            //crea elemento hijo, aniade atributo, y lo cuelga de raiz
	            Element padre = doc.createElement("libro");
	            padre.setAttribute("titulo", nombre.get(i));
	            raiz.appendChild(padre);   
	            
	            Element hijo = doc.createElement("linea");
	            String linea;
	            int cnt=0;
	            while((linea=bfr.readLine())!=null) {
	            	
	            	cnt++;
	            }
	            bfr.close();
	            for (int j = 0; j < cnt+1; j++) {
	            	hijo.setAttribute("num",""+j);
	            	
				}
	            hijo.setTextContent(linea);
	            padre.appendChild(hijo);
	            cnt=0;
	         }
	         
			// Escribe arbol DOM a fichero XML
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "3");
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("nuevo.xml"));
			transformer.transform(source, result);
			
			
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}

	private static ArrayList <String> sacarRutas(String ruta) {
		File fil = new File(ruta);
		ArrayList <String>al = new ArrayList<String>();
		File listFile[] = fil.listFiles();
        if (listFile != null) {
            for (int i = 0; i < listFile.length; i++) {
                    al.add(listFile[i].getPath());
            }
        }
        
		return al;
	}

}
