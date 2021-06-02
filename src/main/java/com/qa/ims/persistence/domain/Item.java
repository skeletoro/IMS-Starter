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
		return "Item id: " + item_Id + " Item name: " + name + " Item Value: $" + value + "\n";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (item_Id ^ (item_Id >>> 32));
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(value);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Item other = (Item) obj;
		if (item_Id != other.item_Id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(value) != Double.doubleToLongBits(other.value))
			return false;
		return true;
	}



}
