package entities;

import java.io.Serializable;

public class Personnage implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String pseudo;
	private Profil profil;
	private Capaciter capacite;

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public Profil getProfil() {
		return profil;
	}

	public void setProfil(Profil profil) {
		this.profil = profil;
	}

	public Capaciter getCapacite() {
		return capacite;
	}

	public void setCapacite(Capaciter capacite) {
		this.capacite = capacite;
	}

}
