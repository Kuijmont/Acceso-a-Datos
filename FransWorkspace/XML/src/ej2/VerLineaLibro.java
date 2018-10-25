package ej2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import comun.Entrada;

public class VerLineaLibro{
	public static void main(String[] args){
		try {
			boolean tituloExiste = false;
			do {
				System.out.print("Introduzca título: ");
				String titulo = Entrada.cadena();
				
				String ruta = "/home/alumno/biblioteca";
	
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
			
			

			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.parse(new InputSource(new FileInputStream("indice.xml"))); //Crea erbol DOM a partir de archivo XML	

		    Element elementRaiz = doc.getDocumentElement();  
	
		    
	    
	
		
		
		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
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