package ejercicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import comun.Entrada;

public class LecturaFicheroCSV {

	public static void main(String[] args) throws IOException {
		System.out.print("Ruta del fichero?");
		String rut = Entrada.cadena();
		File ruta = new File("/media/Datos/2DAM/AccesoADatos/"+rut);
		BufferedReader bfr = new BufferedReader(new FileReader(ruta));
		String linea,datos;
		
		while((linea=bfr.readLine())!=null) {
			datos = linea.replaceAll(";", " ");
			System.out.println(datos);
		}
	}

}
