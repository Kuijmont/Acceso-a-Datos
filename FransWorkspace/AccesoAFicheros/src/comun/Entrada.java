package comun;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

public class Entrada {
	static String inicializar() {
		String buzon = "";
		InputStreamReader flujo = new InputStreamReader(System.in);
		BufferedReader teclado = new BufferedReader(flujo);
		try {
			buzon = teclado.readLine();
		} catch (Exception e) {
			System.out.append("Entrada incorrecta)");
		}
		return buzon;
	}

	public static Integer entero() {
		int valor = Integer.parseInt(inicializar());
		return valor;
	}

	public static double real() {
		double valor = Double.parseDouble(inicializar());
		return valor;
	}
	
	public static double realVal(String mensajeSolicitud,String mensajeError,double min,double max,boolean excepcionPorEnter) {
		String dato;
		double valor=0;
		
		boolean bien;
		do {
			bien=true;
			if (mensajeSolicitud!=null)
				System.out.print(mensajeSolicitud);
			dato=cadena();//Leemos String
			if (excepcionPorEnter && dato.length()==0)
				throw new RuntimeException("Se ha pulsado ENTER");
			try {
				valor=Double.valueOf(dato); //Hacemos la conversion manejando la posible excepcion
				if (valor<min || valor>max) //Error por numero fuera de rango
					bien=false;
			} catch (NumberFormatException e) { 
				bien=false; //Error por excepcion al convertir
			}
			if (!bien && mensajeError!=null)
				System.out.println(mensajeError);			
		} while (!bien);
		//Devolvemos el entero validado
		return valor;
	}
	

	public static String cadena() {
		String valor = inicializar();
		return valor;
	}

	public static String cadenaVal(String mensajeSolicitud,String mensajeError,String plantilla,boolean excepcionPorEnter) {
			String dato;
			boolean bien;
			do {
				bien=true;
				if (mensajeSolicitud!=null)
					System.out.print(mensajeSolicitud);
				dato=cadena();//Leemos String
				if (excepcionPorEnter && dato.length()==0)
					throw new RuntimeException("Se ha pulsado ENTER");
				if (plantilla!=null)
					bien=dato.matches(plantilla);
				if (!bien && mensajeError!=null)
					System.out.println(mensajeError);		
			} while (!bien);
			return dato;
	}
	
	public static char caracter() {
		String valor = inicializar();
		return valor.charAt(0);
	}
	
	
	public static Date fecha(String plantilla) throws ParseException {
		String t=cadena();
		SimpleDateFormat sdf=new SimpleDateFormat(plantilla);
		sdf.setLenient(false);
		Date d=sdf.parse(t);
		return d;
	}
	
	public static Date fechaVal(String mensajeSolicitud,String mensajeError,String plantilla,boolean excepcionPorEnter){
		String dato;
		Date d=null;
		boolean bien;
		do {
			bien=true;
			if (mensajeSolicitud!=null)
				System.out.print(mensajeSolicitud);
			dato=cadena();//Leemos String
			if (excepcionPorEnter && dato.length()==0)
				throw new RuntimeException("Se ha pulsado ENTER");
			SimpleDateFormat sdf=new SimpleDateFormat(plantilla);
			sdf.setLenient(false);
			try {
				d = sdf.parse(dato);//Hacemos la conversion de String a Date manejando la posible excepcion
			} catch (ParseException e) {
				bien=false; //Error por excepcion al convertir(parse)
			}
			if (!bien && mensajeError!=null)
				System.out.println(mensajeError);				
		} while (!bien);
	
		return d;
	}
	

	public static int enteroVal(String mensajeSolicitud,String mensajeError,int min,int max,boolean excepcionPorEnter) {
		String dato;
		int valor=0;
		
		boolean bien;
		do {
			bien=true;
			if (mensajeSolicitud!=null)
				System.out.print(mensajeSolicitud);
			dato=cadena();//Leemos String
			if (excepcionPorEnter && dato.length()==0)
				throw new RuntimeException("Se ha pulsado ENTER");
			try {
				valor=Integer.valueOf(dato); //Hacemos la conversion manejando la posible excepcion
				if (valor<min || valor>max) //Error por numero fuera de rango
					bien=false;
			} catch (NumberFormatException e) { 
				bien=false; //Error por excepcion al convertir
			}
			if (!bien && mensajeError!=null)
				System.out.println(mensajeError);			
		} while (!bien);
		//Devolvemos el entero validado
		return valor;
	}
}