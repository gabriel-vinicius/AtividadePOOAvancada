package br.edu.atitus.pooavancado.CadUsuario.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "produto")
public class Produto extends GenericEntity {	
	public long valorUnitario;

	public long getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(long valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
}
