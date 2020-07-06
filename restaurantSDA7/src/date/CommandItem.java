package date;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comand_items")
public class CommandItem {
	
		
	@Id
	@Column(name = "id_item")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer idItem;
	
	@Column(name = "id_comanda")
	public Integer idComanda;
	
	  
	public Integer getIdItem() {
		return idItem;
	}

	public void setIdItem(Integer idItem) {
		this.idItem = idItem;
	}

	public void setIdComanda(Integer idComanda) {
		this.idComanda = idComanda;
	}

	@Column(name = "data_creare_item")
	public Date dataCreareItem;
	  
	@Column(name = "id_produs")
	public Integer idProdus;
	 
	@Column(name = "nr_produse")
	public Integer nrProduse;
	  
	@Column(name = "pret_total")
	public Double pretTotal;
	  
	@Column(name = "data_livrare_item")
	public Date dataLivrareItem;

	
	
	
	public Date getDataCreareItem() {
		return dataCreareItem;
	}

	public void setDataCreareItem(Date dataCreareItem) {
		this.dataCreareItem = dataCreareItem;
	}

	public Integer getIdProdus() {
		return idProdus;
	}

	public void setIdProdus(Integer idProdus) {
		this.idProdus = idProdus;
	}

	public Integer getNrProduse() {
		return nrProduse;
	}

	public void setNrProduse(Integer nrProduse) {
		this.nrProduse = nrProduse;
	}

	public Double getPretTotal() {
		return pretTotal;
	}

	public void setPretTotal(Double pretTotal) {
		this.pretTotal = pretTotal;
	}

	public Date getDataLivrareItem() {
		return dataLivrareItem;
	}

	public void setDataLivrareItem(Date dataLivrareItem) {
		this.dataLivrareItem = dataLivrareItem;
	}

	public Integer getIdComanda() {
		return idComanda;
	}

	public CommandItem(Integer idItem, Integer idComanda, Date dataCreareItem, Integer idProdus, Integer nrProduse,
			Double pretTotal, Date dataLivrareItem) {
		super();
		this.idItem = idItem;
		this.idComanda = idComanda;
		this.dataCreareItem = dataCreareItem;
		this.idProdus = idProdus;
		this.nrProduse = nrProduse;
		this.pretTotal = pretTotal;
		this.dataLivrareItem = dataLivrareItem;
	}

	public CommandItem() {
		super();
	}
	
}
