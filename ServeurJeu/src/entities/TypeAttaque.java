package entities;
import java.io.Serializable;

public class TypeAttaque implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int typeAttaque;
	private String description;
	
	public int getTypeAttaque() {
		return typeAttaque;
	}
	public void setTypeAttaque(int typeAttaque) {
		this.typeAttaque = typeAttaque;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
