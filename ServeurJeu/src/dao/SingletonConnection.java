package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingletonConnection {
	private static Connection connection;
	// le bloc static est un bloc qui s'execute une seul fois au moment du
	// chargement de la classe en memoire
	
	static {
		
		System.out.println("Debut");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/multi_joueur", "root","10121991");
			System.out.println("connection");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Connection getConnection() {
		return connection;
	}

}
