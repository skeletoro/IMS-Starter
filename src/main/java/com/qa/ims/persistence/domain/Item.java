package com.qa.ims.persistence.domain;

public class Item {

	private long item_Id;
	private String name;
	private double value;

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	public Item(String name, double value) {
		this.setName(name);
		this.value= value;
	}

	public Item(Long item_Id, String name, double value) {
		this.setItem_Id(item_Id);
		this.setName(name);
		this.value= value;

	}

	public long getItem_Id() {
		return item_Id;
	}

	public void setItem_Id(long item_Id) {
		this.item_Id = item_Id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Item id: " + item_Id + " Item name: " + name + " Item Value: $" + value;
	}



}
