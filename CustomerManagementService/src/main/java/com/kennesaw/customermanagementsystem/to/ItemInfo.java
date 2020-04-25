package com.kennesaw.customermanagementsystem.to;

public class ItemInfo {

	private int itemId;
	private String itemName;
	private Float itemCost;

	
	public ItemInfo(){}

	public ItemInfo(int itemId, String itemName, Float itemCost) {
		super();
		this.itemId = itemId;
		this.itemName = itemName;
		this.itemCost = itemCost;
	}
	
	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Float getItemCost() {
		return itemCost;
	}

	public void setItemCost(Float itemCost) {
		this.itemCost = itemCost;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemInfo other = (ItemInfo) obj;
		if (itemId == 0) {
			if (other.itemId != 0)
				return false;
		} else if (!itemName.equals(other.itemName))
			return false;
		if (itemCost == null) {
			if (other.itemCost != null)
				return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ItemInfo [itemId=" + itemId + ", itemName=" + itemName + ", itemCost=" + itemCost +  "]";
	}

	
}
