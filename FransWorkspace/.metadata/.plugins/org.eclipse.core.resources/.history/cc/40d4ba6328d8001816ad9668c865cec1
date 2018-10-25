package ej2;

import java.io.IOException;
import java.util.Scanner;

import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;
import org.xml.sax.helpers.XMLReaderFactory;

import comun.Entrada;

public class VerLineaLibro extends DefaultHandler {

	// public static void main(String[] args) {
	//
	// System.out.print("Introduzca título: ");
	// String titulo = Entrada.cadena();
	// System.out.print("Introduzca línea: ");
	// String linea = Entrada.cadena();
	//
	//
	//
	// }
	@Override
	public void startDocument() throws SAXException {
		System.out.println("\nPrincipio del documento...");
	}

	public void endDocument() throws SAXException {
		System.out.println("\nFin del documento...");
	}

	public void startElement(String uri, String localName, String name, Attributes attributes) throws SAXException {
		System.out.println("\nComienzo etiqueta:" + localName);

		// Recorremos los atributos
		System.out.println("\t" + attributes.getLength() + " atributos...");
		for (int i = 0; i < attributes.getLength(); i++) {
			System.out.println("\t\tNombre: " + attributes.getLocalName(i));
			System.out.println("\t\tValor: " + attributes.getValue(i));
		}

		// También podemos obtener los atributos por nombre
		String valorId = attributes.getValue("id");
		if (valorId != null) {
			System.out.println("\tId: " + valorId);
		}
	}

	public void characters(char[] ch, int start, int length) throws SAXException {

		System.out.println("\tTexto: " + String.valueOf(ch, start, length));
	}

	public void endElement(String uri, String localName, String name) throws SAXException {
		System.out.println("\nFin de etiqueta:" + localName);
	}

}
 class L_SAX_LeerXML {

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);
		System.out.print("Introduzca path fichero XML:");
		String path_xml = s.next();
		try {
			// Creamos la factoria analizadores por defecto
			XMLReader reader = XMLReaderFactory.createXMLReader();
			// Añadimos nuestro manejador al reader
			reader.setContentHandler(new VerLineaLibro());
			// Procesamos el xml de ejemplo
			reader.parse(new InputSource(path_xml));
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		s.close();
	}

}
