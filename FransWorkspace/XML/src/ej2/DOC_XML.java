package ej2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import comun.Entrada;

public class DOC_XML{
	public static void main(String[] args){
		try {
			boolean tituloExiste = false;
			String titulo,ruta;
			do {
				System.out.print("Introduzca título: ");
				titulo = Entrada.cadena();
				
				ruta = "/home/alumno/biblioteca";
	
				ArrayList<String> al = sacarRutas(ruta);
				ArrayList<String> nombre = new ArrayList<String>();
				String file, rut = null;
				for (int i = 0; i < al.size(); i++) {
					rut = al.get(i).toString();
					file = rut.substring(rut.lastIndexOf('/') + 1);
					nombre.add(file);
				}
				for (int i = 0; i < nombre.size(); i++) {
					if(nombre.get(i).equals(titulo))
						tituloExiste = true;
				}
			}while(!tituloExiste);
			
			
			System.out.print("Introduzca línea: ");
			int linea = Entrada.entero();
			
			//RandomAccessFile fichero = new RandomAccessFile(ruta+"/"+titulo, "rw");
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse("indice.xml"); //Crea erbol DOM a partir de archivo XML	

			Element des = (Element) (XPathFactory.newInstance().newXPath().evaluate(
					"//libro[@titulo=\'" + titulo + "\']/linea[@num=\'" 
					+ linea + "\']", doc, XPathConstants.NODE));
			if(des!=null) {
				File f = new File("/home/alumno/biblioteca/" + titulo);
				RandomAccessFile raf = new RandomAccessFile(f, "r");
				String resul;
				raf.seek(Long.parseLong(des.getTextContent()));
				System.out.println(raf.readLine());
			}	
            
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
		
		
	}
	private static ArrayList<String> sacarRutas(String ruta) {
		File fil = new File(ruta);
		ArrayList<String> al = new ArrayList<String>();
		File listFile[] = fil.listFiles();
		if (listFile != null) {
			for (int i = 0; i < listFile.length; i++) {
				al.add(listFile[i].getPath());
			}
		}

		return al;
	}
}