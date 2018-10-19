package ej6;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;

public class PrincipalA {

	public static void main(String[] args) throws IOException {
		Persona p1 = new Persona(20, "Julio Perez", 'M');
		p1.añadirTrabajo(new Trabajo(1, "Albanil"));
		p1.añadirTrabajo(new Trabajo(1, "Encofrador"));
		Persona p2 = new Persona(21, "Ana Gutierrez", 'F');
		p2.añadirTrabajo(new Trabajo(2, "Ingeniero"));
		Persona p3 = new Persona(84, "Emilia Rivilla", 'F');
		p3.añadirTrabajo(new Trabajo(2, "Profesora"));
		p3.añadirTrabajo(new Trabajo(3, "Locutora de radio"));

		File ruta = new File("/media/Datos/2DAM/AccesoADatos/PersonasB.txt");
		if(!ruta.exists()) {
			PrintWriter pw=new PrintWriter(new FileWriter (ruta));
			pw.print(p1+"\n");
			pw.print(p2+"\n");
			pw.print(p3+"\n");
			pw.close();
		}
		ruta = new File("/media/Datos/2DAM/AccesoADatos/PersonasB.bin");
		if(!ruta.exists()) {
			DataOutputStream dos=new DataOutputStream(new FileOutputStream(ruta));
			dos.writeInt(p1.edad);
			dos.writeUTF(p1.nombre);
			dos.writeUTF(p1.trabs.toString());
			dos.writeChars(p1.sexo+"\n");
			dos.writeInt(p2.edad);
			dos.writeUTF(p2.nombre);
			dos.writeUTF(p2.trabs.toString());
			dos.writeChars(p2.sexo+"\n");
			dos.writeInt(p3.edad);
			dos.writeUTF(p3.nombre);
			dos.writeUTF(p3.trabs.toString());
			dos.writeChar(p3.sexo);
			dos.close();

		}
		
		ruta =new File("/media/Datos/2DAM/AccesoADatos/PersonasB.obj");
		if(!ruta.exists()) {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta));
			oos.writeObject(p1+"\n");
			oos.writeObject(p2+"\n");
			oos.writeObject(p3+"\n");
			oos.close();
		}
			
	}

}
