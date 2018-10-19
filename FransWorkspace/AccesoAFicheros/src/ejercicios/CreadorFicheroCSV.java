package ejercicios;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import comun.Entrada;

public class CreadorFicheroCSV {

	public static void main(String[] args) throws IOException {

		System.out.print("Ruta del fichero donde se grabarán los registros: ");
		String rut = Entrada.cadena();
		File ruta = new File("/media/Datos/2DAM/AccesoADatos/"+rut);
		if(!ruta.exists()) {
			PrintWriter pw=new PrintWriter(new FileWriter (ruta));
			pw.print("Matricula;Marca;Precio\n");
			String ap,mat,marca,precio;
			do {
				System.out.print("Matrícula (Intro para salir): "); 
				mat = Entrada.cadena();
				if(!mat.equals("")) {
					System.out.print("Marca: "); 
					marca = Entrada.cadena();
					System.out.print("Precio: "); 
					precio = Entrada.cadena();
					pw.print(mat+";"+marca+";"+precio+"\n");
				}
			}while(!mat.equals(""));
			pw.close();
		}else {
			
		}
	
	}

}
