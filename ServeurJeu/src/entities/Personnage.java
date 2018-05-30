package entities;

public class Personnage {

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
