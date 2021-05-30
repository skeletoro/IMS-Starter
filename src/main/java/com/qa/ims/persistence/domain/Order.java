package com.qa.ims.persistence.domain;

public class Order {

	private Long order_Id;
	private Long id;
	private Long item_Id;

	
	public Order(Long id, Long item_Id) {
		this.setId(id);
		this.setItems_Id(item_Id);
	}
	public Order(Long order_Id, Long id, Long item_Id) {
		this.setId(id);
		this.setId(id);
		this.setItems_Id(item_Id);
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

	public void setItems_Id(long item_Id) {
		this.item_Id = item_Id;
	}

	@Override
	public String toString() {
		return "Order [order_Id=" + order_Id  + ", id=" + id + ", items_Id=" + item_Id + "]";
	}
}
