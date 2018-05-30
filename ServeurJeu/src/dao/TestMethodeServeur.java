package dao;

import entities.Personnage;
import entities.Profil;

public class TestMethodeServeur {

	public static void main(String[] args) {
		
		
		
		Personnage personnage = new Personnage();
		Profil profil = new Profil();
		profil.setProfil(2);
		
		personnage.setPseudo("kaka222");
		personnage.setProfil(profil);
		String pseudo = personnage.getPseudo();
		
		
		PersonnageMetier personnageMetier = new PersonnageMetier();
		
		personnageMetier.creerPersonnage(personnage);
		System.out.println(personnageMetier.authentification("wwwwwww"));
		
		
		

	}

}
