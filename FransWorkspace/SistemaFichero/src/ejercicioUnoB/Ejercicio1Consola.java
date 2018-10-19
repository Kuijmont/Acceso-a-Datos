package ejercicioUnoB;

import java.io.File;
import java.util.ArrayList;

import javax.xml.stream.util.EventReaderDelegate;

import comun.Entrada;
import comun.Utilidades;
import ejercicioUno.Ejercicio1;

public class Ejercicio1Consola {

	public static void main(String[] args) {

		File f;
		do {
			System.out.print("Ruta: ");
			String ruta = Entrada.cadena();
			f = new File(ruta);
			if(!f.exists())
				System.out.println("Ruta inválida");
		}while (!f.exists()); 
		
		System.out.println("La ruta es correcta.");
		int tamanio = -1;
		do {
			tamanio = Entrada.enteroVal("Introduzca tamaño en Bytes:", "El valor introducido no es numérico", 0, 999999999, false);
		}while(tamanio<0);
		char criterio='+';
		do {
			System.out.print("Introduzca '+' ó '-' para mayor o menor:");
			criterio = Entrada.caracter(); 
		}while (criterio!='+' && criterio!='-');
		boolean oculto = false;
		String ficheroOculto;
		do {
			System.out.print("Quieres ver archivos ocultos? (si ó no): ");
			ficheroOculto = Entrada.cadena();
		}while (!ficheroOculto.equals("no") && !ficheroOculto.equals("si"));
		if(ficheroOculto.equals("si"))
			oculto = true;
		boolean subcarpetas = false;
		String subcarpeta;
		do {
			System.out.print("Quieres ver subcarpetas? (si ó no): ");
			subcarpeta = Entrada.cadena();
		}while (!subcarpeta.equals("no") && !subcarpeta.equals("si"));
		if(subcarpeta.equals("si"))
			subcarpetas = true;
		ArrayList<File> res = Utilidades.buscarArchivosPorTamanio(f, tamanio, criterio, oculto, subcarpetas);
		for (int i = 0; i < res.size(); i++) {
			System.out.println(Utilidades.mostrarInfoFileTam(res.get(i)));
		}
		System.out.println("Archivos encontrados: "+res.size());
	}

}
