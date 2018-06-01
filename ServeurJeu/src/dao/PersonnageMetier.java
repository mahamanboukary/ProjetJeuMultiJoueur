package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import com.mysql.jdbc.Statement;

import entities.Capaciter;
import entities.Personnage;
import entities.Profil;
import entities.TypeAttaque;

public class PersonnageMetier {

	private final int NOMBREVIE = 20;
	private final int SCORE = 0;
	private final boolean ETAT = true;
	private Connection con = SingletonConnection.getConnection() ;
	private PreparedStatement st = null;
	private ResultSet rs = null;
	private static List<String> personneConnecter  = new ArrayList<>();
	
	static HashMap<String, Calendar> hmap = new HashMap<String, Calendar>();
	
	public static void initiliseMap() {
		Calendar cal = Calendar.getInstance();
		hmap.put("LIK", cal);
		hmap.put("MA", cal);
	}
	
	public static HashMap<String, Calendar> getMap() {
		return hmap;
	}
	
	public  void decoonecter(String pseudo) {
		hmap.remove(pseudo);
		personneConnecter.remove(pseudo);
	}
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
			st = con.prepareStatement("select  * from personnage WHERE pseudo= ?");
			st.setString(1, pseudo);
			rs = st.executeQuery();
			if (rs.first()) {
				personneConnecter.add(pseudo);
				Calendar cal = Calendar.getInstance();
				hmap.put(pseudo, cal);
				System.out.println(pseudo + "connceter" );
				
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
	
	//mise à jour de la capacité
		public void UpdateCapaciter(String pseudo, Boolean estAttaque) {
			
			//System.out.println(hmap.get(pseudo).getTimeInMillis());
			Personnage pers = new Personnage();
			pers.setPseudo(pseudo);
			Personnage personnage = getPersonnage(pers);
			System.out.println(personnage.getPseudo()+ " "+personnage.getCapacite().getNombreVie());
			try {
				st = con.prepareStatement("update capaciter set nombreVie = ? where capacite = ?");
				if(estAttaque) {
					if(personnage.getCapacite().getNombreVie() > 0 ) {
						st.setInt(1, personnage.getCapacite().getNombreVie() - 1);
					}else {
						st.setInt(1, personnage.getCapacite().getNombreVie());
					}	
				}else {
					if(personnage.getCapacite().getNombreVie() < 50 ) {
						st.setInt(1, personnage.getCapacite().getNombreVie() + 1);
					}else {
						st.setInt(1, personnage.getCapacite().getNombreVie());
					}
				}
				
				st.setInt(2, personnage.getCapacite().getCapacite());
				st.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		// lister les profils
		public java.util.List<Profil> listerProfils() {
			List<Profil> profils = new ArrayList<>();
			
			try {
				   st = con.prepareStatement("select * from profil");
		           rs=st.executeQuery();
		           while(rs.next())
		           {
			        	Profil profil = new Profil();
			        	TypeAttaque typeAttaque = new TypeAttaque();
			        	profil.setProfil(rs.getInt("profil"));
			        	profil.setResistance(rs.getInt("resistance"));
			        	profil.setDescription(rs.getString("description"));
			        	typeAttaque.setTypeAttaque(rs.getInt("typeAttaque"));
			        	profil.setTypeAttaque(typeAttaque);
			        	profils.add(profil);
		            }
				
			} catch (SQLException ex) {
				// TODO Auto-generated catch block
				System.out.println("!!!!"+ex.getMessage());
			}
			return profils;
			
		}
		
		//Information d'un personnage (pseudo, profil, capacité)
		public Personnage getPersonnage(Personnage personnage) {
			
			try {
				st =this.con.prepareStatement("select * from personnage,capaciter,profil, typeattaque where personnage.capacite = capaciter.	\n" + 
						"capacite and personnage.profil = profil.profil and typeattaque.typeAttaque = profil.typeAttaque  and personnage.pseudo = ?");
				st.setString(1,personnage.getPseudo());
		        rs=st.executeQuery();
		        while(rs.next())
		        {
			     	//System.out.println(rs.getString("pseudo"));
		        	personnage.setPseudo(rs.getString("pseudo"));
		        	
		        	Capaciter capaciter = new Capaciter();
		        	capaciter.setCapacite(rs.getInt("capacite"));
		        	capaciter.setEtat(rs.getBoolean("etat"));
		        	capaciter.setNombreVie(rs.getInt("nombreVie"));
		        	capaciter.setScrore(rs.getInt("scrore"));
		        	
		        	Profil profil = new Profil();
		        	profil.setProfil(rs.getInt("profil"));
		        	profil.setResistance(rs.getInt("resistance"));
		        	profil.setDescription(rs.getString("profil.description"));
		        	
		        	TypeAttaque typeAttaque = new TypeAttaque();
		        	typeAttaque.setDescription(rs.getString("typeattaque.description"));
		        	
		        	profil.setTypeAttaque(typeAttaque);
		        	personnage.setCapacite(capaciter);
		        	personnage.setProfil(profil);
		        	
		        	
		         }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   
	       
		   
		   //System.out.println(personnage.getProfil().getDescription() +"  "+ personnage.getPseudo()+" "+ personnage.getCapacite().getNombreVie()+" "+personnage.getProfil().getTypeAttaque().getDescription());
		   return personnage;
			
		}
		
		public List<Personnage> getPersonnageConnecter(){
			//personneConnecter.clear();
			//personneConnecter.add("sidi");
			//personneConnecter.add("maman sani");
			List<Personnage> personnageConnecter = new ArrayList<>();
			for (String pseudo : personneConnecter) {
				Personnage personnage = new Personnage();
				personnage.setPseudo(pseudo);
				//System.out.println("Repeter");
				personnageConnecter.add(getPersonnage(personnage));
				
			}
			return personnageConnecter;
			
		}
		

}
