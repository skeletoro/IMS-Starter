package com.qa.ims.persistence.domain;

public class Order {

	private Long order_Id;
	private Long id;
	private Long item_Id;
	private Long quantity;
	private Long oi_Id;
	private String first_Name;

//	public Order (Long oi_Id,Long order_Id) {
//		this.setOi_Id(oi_Id);
//		this.setOrder_Id(order_Id);
//	}
	public Order(Long id) {
		this.setId(id);
	}

	public Order(Long order_Id, Long id) {
		this.setOrder_Id(order_Id);
		this.setId(id);
	}

	public Order(Long order_Id, Long item_Id, Long quantity) {
		this.setOrder_Id(order_Id);
		this.setItem_Id(item_Id);
		this.setQuantity(quantity);
	}

	public Order(Long order_Id, Long id, Long item_Id, Long quantity) {
		this.setOrder_Id(order_Id);
		this.setId(id);
		this.setItem_Id(item_Id);
		this.setQuantity(quantity);
	}

	public Order(Long order_Id, String first_Name, Long id, Long item_Id, Long quantity) {
		this.setOrder_Id(order_Id);
		this.setFirst_Name(first_Name);
		this.setId(id);
		this.setItem_Id(item_Id);
		this.setQuantity(quantity);
	}

	public String getFirst_Name() {
		return first_Name;
	}

	public void setFirst_Name(String first_Name) {
		this.first_Name = first_Name;
	}

	public long getOrder_Id() {
		return order_Id;
	}

	public void setOrder_Id(long order_Id) {
		this.order_Id = order_Id;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getItem_Id() {
		return item_Id;
	}

	public void setItem_Id(long item_Id) {
		this.item_Id = item_Id;
	}

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	public Long getOi_Id() {
		return oi_Id;
	}

	public void setOi_Id(Long oi_Id) {
		this.oi_Id = oi_Id;
	}

	@Override
	public String toString() {
		return "order Id= " + order_Id + " first name= " + first_Name + ", Customer ID= " + id + ", items Id= "
				+ item_Id + ", quantity= " + quantity + "\n";
	}
}
