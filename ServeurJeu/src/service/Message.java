package service;

import java.io.Serializable;

public class Message implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int type;
	private Object donne;

	public Message(int type, Object donne) {
		this.type = type;
		this.donne = donne;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Object getDonne() {
		return donne;
	}

	public void setDonne(Object donne) {
		this.donne = donne;
	}

}