package ej2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import comun.Entrada;

class Manejador extends DefaultHandler { 
	private String titulo;
	private int linea;
	private String resul;
	private boolean tit = false,lin = false;
	
	public String getResul() {
		return resul;
	}

	public void setResul(String resul) {
		this.resul = resul;
	}

	public Manejador(String titulo, int linea) {
		super();
		this.titulo = titulo;
		this.linea = linea;
	}

	@Override
	public void startDocument() throws SAXException {
		
	}

	public void endDocument() throws SAXException {
		
	}
	
	public void startElement(String uri, String localName, String name,
			Attributes attributes) throws SAXException {
		if(name.equals("libro"))
			if (attributes.getValue("titulo").equals(titulo))
				tit = true;
		if(tit && name.equals("linea"))
			if(attributes.getValue("num").equals(lin))
				lin = true;
	}

	public void characters(char[] ch, int start, int length)
			throws SAXException {
		if(lin && tit) {
			try {
				RandomAccessFile raf = new RandomAccessFile(new File("/home/alumno/biblioteca/" + titulo), "r");
				
				System.out.println(raf.readLine());
				lin = false;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public int getLinea() {
		return linea;
	}

	public void setLinea(int linea) {
		this.linea = linea;
	}

	public boolean isTit() {
		return tit;
	}

	public void setTit(boolean tit) {
		this.tit = tit;
	}

	public boolean isLin() {
		return lin;
	}

	public void setLin(boolean lin) {
		this.lin = lin;
	}
	
}
public class SAX_XML{

	public static void main(String[] args) {
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
			// Creamos la factoria analizadores por defecto
			XMLReader reader = XMLReaderFactory.createXMLReader();
			// Añadimos nuestro manejador al reader
			reader.setContentHandler(new Manejador(titulo,linea));
			// Procesamos el xml de ejemplo
			reader.parse(new InputSource("/home/alumno/biblioteca/indice.xml"));
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
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