package competicion;

import java.sql.SQLException;

public interface Persistencia {

	void conectBD();

	void desconectBD();
	
	void toRegister(String table) throws SQLException;

	void toModify(String table) throws SQLException;

	void toCancel(String table) throws SQLException;

}
