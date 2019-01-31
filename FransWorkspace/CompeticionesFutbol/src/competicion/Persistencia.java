package competicion;

import java.util.ArrayList;

public interface Persistencia {

	void conectBD(String IP, String user, String pass, String bd);

	void desconectBD();

	/*ArrayList<> list(String tabla, String orderBy);

	void save(String tabla) throws Exception;
	
	void delete(String tabla) throws Exception;
	
	void modify(String tabla) throws Exception;

	void query(String tabla) throws Exception;
	 */
}
