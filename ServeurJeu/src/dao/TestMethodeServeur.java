package dao;

import entities.Personnage;
import entities.Profil;

public class TestMethodeServeur {

	public static void main(String[] args) {
		
		
		
		Personnage personnage = new Personnage();
		Profil profil = new Profil();
		profil.setProfil(2);
		
		personnage.setPseudo("LABO");
		personnage.setProfil(profil);
		System.out.println("la description "+personnage.getProfil().getProfil());
		String pseudo = personnage.getPseudo();
		
		
		PersonnageMetier personnageMetier = new PersonnageMetier();
		
		//personnageMetier.creerPersonnage(personnage);
		System.out.println(personnageMetier.authentification("LABO"));
		
		
		

	}

}
