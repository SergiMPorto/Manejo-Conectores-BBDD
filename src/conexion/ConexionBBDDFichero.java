package conexion;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConexionBBDDFichero {

	private Properties properties;

	public ConexionBBDDFichero() {
		properties = new Properties();
		try (FileInputStream fis = new FileInputStream("database.properties")) {
			properties.load(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getUrl() {
		return properties.getProperty("db.url");
	}

	public String getUser() {
		return properties.getProperty("db.user");
	}

	public String getPassword() {
		return properties.getProperty("db.password");
	}

}
