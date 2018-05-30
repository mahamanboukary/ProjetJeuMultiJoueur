package entities;

public class Profil {

	private int profil;
	private int resistance;
	private TypeAttaque typeAttaque;
	private String description;
	private String image;
	
	

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public int getProfil() {
		return profil;
	}

	public void setProfil(int profil) {
		this.profil = profil;
	}

	public int getResistance() {
		return resistance;
	}

	public void setResistance(int resistance) {
		this.resistance = resistance;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public TypeAttaque getTypeAttaque() {
		return typeAttaque;
	}

	public void setTypeAttaque(TypeAttaque typeAttaque) {
		this.typeAttaque = typeAttaque;
	}

}
