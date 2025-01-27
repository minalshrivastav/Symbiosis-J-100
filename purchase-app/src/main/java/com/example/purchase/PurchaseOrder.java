package com.example.purchase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity /* It is atomatically mapped to a table in the database */
@Table(name = "purchase_orders")
@Data // @Data (from Lombok): Generates getters, setters, toString(), equals(), and hashCode() methods automatically.
@NoArgsConstructor //@NoArgsConstructor (from Lombok): Generates a no-argument constructor.
@AllArgsConstructor //  @AllArgsConstructor (from Lombok): Generates a constructor with arguments for all fields
public class PurchaseOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // Automatically generate value for the primary key field
    private Long id;

    private String item;
    private int quantity;
    private String supplier;
    private String batchNo;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getSupplier() {
		return supplier;
	}
	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
    
}


