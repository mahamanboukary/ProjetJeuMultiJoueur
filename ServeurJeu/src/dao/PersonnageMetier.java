package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.mysql.jdbc.Statement;

import entities.Personnage;

public class PersonnageMetier {

	private final int NOMBREVIE = 20;
	private final int SCORE = 0;
	private final boolean ETAT = true;
	private Connection con = null;
	private PreparedStatement st = null;
	private ResultSet rs = null;

	// Methode de creation d'une capacite
	public int creerCapacite() {
		int id = 0;

		try {
			st = con.prepareStatement("insert into capaciter (nombreVie,scrore,etat) values(?,?,?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, NOMBREVIE);
			st.setInt(2, SCORE);
			st.setBoolean(3, ETAT);
			st.executeUpdate();
			rs = st.getGeneratedKeys();

			if (rs.next()) {
				id = rs.getInt(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("numero" + id);
		return id;
	}

	// Methode d'authentification d'un personnage
	public boolean authentification(String pseudo) {

		try {
			st = con.prepareStatement("select pseudo from personnage WHERE pseudo= ?");
			st.setString(1, pseudo);
			rs = st.executeQuery();
			if (rs.first()) {

				return true;
			}

		} catch (Exception ex) {
			System.out.println("!!!!" + ex.getMessage());
		}
		return false;
	}

	// Methode de creation d'un personnage
	public void creerPersonnage(Personnage personnage) {
		int numero = creerCapacite();

		try {
			st = con.prepareStatement("insert into personnage (pseudo,profil,capacite) values(?,?,?)");
			st.setString(1, personnage.getPseudo());
			st.setInt(2, personnage.getProfil().getProfil());
			// st.setInt(2,);
			// System.out.println("salu"+ creerCapacite() +"test");
			st.setInt(3, numero);
			st.executeUpdate();

		} catch (Exception ex) {
			System.out.println("!!!!" + ex.getMessage());
		}

	}

}
