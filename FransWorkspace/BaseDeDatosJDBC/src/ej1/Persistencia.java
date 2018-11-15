package ej1;

import java.util.ArrayList;

public interface Persistencia {

	void conectarDB(String IP, String usu, String pass, String bd);

	void desconectarDB();

	ArrayList<Persona> listadoPersonas(String tabla, String orderBy);

	void guardarPersona(String tabla, Persona p) throws Exception;// inserta o actualiza
	
	void borrarPersona(String tabla, Persona p) throws Exception;

	Persona consultarPersona(String tabla, String email) throws Exception;

}
