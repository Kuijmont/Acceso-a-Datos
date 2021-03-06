package ej5;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;

public class PrincipalB {

	public static void main(String[] args) throws IOException, ClassNotFoundException {

		File ruta = new File("/media/Datos/2DAM/AccesoADatos/Personas.txt");
		BufferedReader bfr = new BufferedReader(new FileReader(ruta));
		String linea;
		while((linea=bfr.readLine())!=null) {
			System.out.println(linea);
		}
		System.out.println("===================================================");
		bfr.close();
		
		String nombre = null;
		int edad = 0;
		char sexo = 0;
		ruta = new File("/media/Datos/2DAM/AccesoADatos/Personas.bin");
		DataInputStream dis = new DataInputStream(new FileInputStream(ruta));
		try {
			do{
				edad = dis.readInt();
				nombre = dis.readUTF();
				sexo = dis.readChar();
				System.out.println("Persona [edad="+edad+", nombre="+nombre+", sexo="+sexo);
			}while((linea=dis.readLine())!=null);
			
		}catch (EOFException e) {}
		System.out.println("===================================================");
		dis.close();
		
		ruta = new File("/media/Datos/2DAM/AccesoADatos/Personas.obj");
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta));
		try {
			while((linea=(String) ois.readObject())!=null) {
				System.out.println(linea);
			}
		}catch (EOFException e) {}
		ois.close();
	}

}
