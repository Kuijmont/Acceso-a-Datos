package ejercicios;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.swing.JOptionPane;

import comun.Entrada;

public class CopiaFicheros {

	public static void main(String[] args) {
		//home/alumno/Pruebas/hola.txt
		//home/alumno/Pruebas/prueba/prueba2.txt
		File fichero,ficheroCop;
		do {
			System.out.print("Fichero origen: ");
			String origen = Entrada.cadena();
			fichero = new File(origen);
		}while(!fichero.isFile());
			
		
			System.out.print("Fichero destino: ");
			String destino = Entrada.cadena();
			ficheroCop = new File(destino);
	
		
		if(ficheroCop.exists()) {
			//Sobrescribir?
			int opcion=JOptionPane.showConfirmDialog(null, "Quiere sobreescribir?","Fichero ya existe!!",JOptionPane.OK_CANCEL_OPTION);
			switch(opcion){
				case JOptionPane.OK_OPTION:
					//Sobrescribir = true
					try {
						InputStream in = new FileInputStream(fichero);
	                    OutputStream out = new FileOutputStream(ficheroCop);
	
	                    byte[] buf = new byte[1024];
	                    int len;
	
	                    while ((len = in.read(buf)) > 0) {
	                            out.write(buf, 0, len);
	                            
	                    }
	
	                    in.close();
	                    out.close();
					}catch (IOException ioe){
	                    ioe.printStackTrace();
	                }
					break;
				case JOptionPane.CANCEL_OPTION:
					System.exit(0);
					break;
				case JOptionPane.CLOSED_OPTION:
					System.exit(0);
					break;
			}
		}else {
			try {
				InputStream in = new FileInputStream(fichero);
                OutputStream out = new FileOutputStream(ficheroCop);

                byte[] buf = new byte[1024];
                int len;

                while ((len = in.read(buf)) > 0) {
                        out.write(buf, 0, len);
                }

                in.close();
                out.close();
			}catch (IOException ioe){
                ioe.printStackTrace();
            }
		}
			
		
	}

}
