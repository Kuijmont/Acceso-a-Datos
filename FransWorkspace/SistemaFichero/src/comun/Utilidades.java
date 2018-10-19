package comun;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Utilidades {
	public static ArrayList<File> buscarArchivosPorTamanio(File carpetaDondeBuscar,long tamEnBytes, char criterio, boolean incluirOcultos, boolean incluirSubcarpetas) {
		if (carpetaDondeBuscar==null)
			throw new IllegalArgumentException("El primer argumento debe ser un objeto de la clase File");
		if (!carpetaDondeBuscar.isDirectory())
			throw new IllegalArgumentException("El objeto File no es una carpeta");
			
		boolean mayores = true;
		switch (criterio) {
			case '+':
				mayores = true;
				break;
			case '-':
				mayores = false;
				break;
			default:
				throw new IllegalArgumentException("El argumento criterio debe ser un caracter '+' o '-'");
		}
		
		ArrayList<File> al = new ArrayList<File>();
		
		File[] lista = carpetaDondeBuscar.listFiles();
		if (lista==null) //Error al listar carpeta: falta de permisos, etc
			return al;
		
		for (int i = 0; i < lista.length; i++) {
			boolean incluir= !lista[i].isHidden() || lista[i].isHidden()&&incluirOcultos;//Ignoramos carpetas y ficheros ocultos
			if (incluir){
				if (lista[i].isFile()){
					if (mayores && lista[i].length()>=tamEnBytes || !mayores && lista[i].length()<=tamEnBytes)
						al.add(lista[i]);
				}
				else{//Subcarpeta
					if (incluirSubcarpetas) {
						//Añadimos los ficheros que se encuentren en subcarpetas mediante recursividad
						ArrayList<File> al2=buscarArchivosPorTamanio(lista[i],tamEnBytes,criterio,incluirOcultos,incluirSubcarpetas);
						if (al2!=null)
							al.addAll(al2);
					}
				}
			}
		}
		return al;
	}

	public static String mostrarInfoFileTam(File f) {
		return f.getAbsolutePath()+" ("+f.length()+" bytes)";
	}
	public static String mostrarInfoFileLastMod (File f) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");
		Date d = new Date (f.lastModified());
		
		return sdf.format(d);
	}
	
	public static void notificaError(JFrame padre, String titulo,	Exception e, String mensaje) {
		String contenido="";
		if (e!=null)
			contenido+=e.getClass().getName()+"\n"+e.getMessage(); //Nombre de la excepcion y mensaje de la excep.
		if (mensaje!=null)
			contenido+=mensaje; 
		JOptionPane.showMessageDialog(padre,contenido,titulo, JOptionPane.ERROR_MESSAGE);		
	}
	
	private boolean preguntaUsuario(JFrame padre, String titulo, String mensaje){
		///Mensaje de confirmacion. Devuelve true si el usuario pulsa SI
		return JOptionPane.showConfirmDialog(padre, mensaje,titulo,JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION;
	}
	public static ArrayList <File> buscarArchivosPorFecha (File carpetaDondeBuscar, Date d, char criterio, boolean incluirOcultos, boolean incluirSubcarpetas) {
		if (carpetaDondeBuscar==null)
			throw new IllegalArgumentException("El primer argumento debe ser un objeto de la clase File");
		if (!carpetaDondeBuscar.isDirectory())
			throw new IllegalArgumentException("El objeto File no es una carpeta");
		boolean antes = true;
		switch (criterio) {
			case '+':
				antes = true;
				break;
			case '-':
				antes = false;
				break;
			default:
				throw new IllegalArgumentException("El argumento criterio debe ser un caracter '+' o '-'");
		}
		ArrayList<File> al = new ArrayList<File>();
		
			File[] lista = carpetaDondeBuscar.listFiles();
			if (lista==null) //Error al listar carpeta: falta de permisos, etc
				return null;
			for (int i = 0; i < lista.length; i++) {
				boolean incluir= !lista[i].isHidden() || lista[i].isHidden()&&incluirOcultos;//Ignoramos carpetas y ficheros ocultos
				if (incluir){
					if (lista[i].isFile()){
						if (antes && lista[i].lastModified()>=d.getTime() || !antes && lista[i].lastModified()<=d.getTime())
							al.add(lista[i]);
					}
					else{//Subcarpeta
						if (incluirSubcarpetas) {
							//Añadimos los ficheros que se encuentren en subcarpetas mediante recursividad
							ArrayList<File> al2=buscarArchivosPorFecha(lista[i],d,criterio,incluirOcultos,incluirSubcarpetas);
							if (al2!=null)
								al.addAll(al2);
						}
					}
				}
			}
		return al;
	}
}

