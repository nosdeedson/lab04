package br.edson.si.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name = "carro")
public class Carro implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long idCarro;
	private String placaCarro;
	private String modeloCarro;
	
	public Carro() {
		super();
	}

	public Carro( String modeloCarro, String placaCarro ) {
		super();
		this.placaCarro = placaCarro;
		this.modeloCarro = modeloCarro;
	}
	
	@Id
	@GeneratedValue
	@Column (name = "id")
	public Long getIdCarro() {
		return idCarro;
	}

	public void setIdCarro(Long idCarro) {
		this.idCarro = idCarro;
	}

	public String getPlacaCarro() {
		return placaCarro;
	}

	public void setPlacaCarro(String placaCarro) {
		this.placaCarro = placaCarro;
	}

	public String getModeloCarro() {
		return modeloCarro;
	}

	public void setModeloCarro(String modeloCarro) {
		this.modeloCarro = modeloCarro;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCarro == null) ? 0 : idCarro.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carro other = (Carro) obj;
		if (idCarro == null) {
			if (other.idCarro != null)
				return false;
		} else if (!idCarro.equals(other.idCarro))
			return false;
		return true;
	}
	
	
}
