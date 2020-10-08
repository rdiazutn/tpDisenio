package utn.dds.tpAnual.db.entity.proveedor;


import utn.dds.tpAnual.db.entity.ubicacion.DireccionPostal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author Tomas
 * @version 1.0
 * @created 10-abr.-2020 18:19:19
 */

@Entity
public class ProveedorJuridico extends Proveedor {

	@Column(name = "CUIT")
	private String CUIT;

	@Column(name = "RAZON_SOCIAL", length = 100)
	private String razonSocial;

	public ProveedorJuridico() {

	}

	public ProveedorJuridico(DireccionPostal direccionPostal, String CUIT, String razonSocial) {
		super(direccionPostal);
		this.CUIT = CUIT;
		this.razonSocial = razonSocial;
	}

	public String getCUIT() {
		return CUIT;
	}

	public void setCUIT(String CUIT) {
		this.CUIT = CUIT;
	}

	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}


}