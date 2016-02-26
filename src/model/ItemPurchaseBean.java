package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class ItemPurchaseBean
{
	@XmlElement(name = "item")
	private ItemBean ib;
	@XmlElement(name = "quantity")
	private int units;
	@XmlElement(name = "extendedPrice")
	private double extendedPrice;

	public ItemPurchaseBean(ItemBean ib, int units,
			double extendedPrice)
	{
		super();
		this.ib = ib;
		this.units = units;
		this.extendedPrice = extendedPrice;
	}

	public ItemBean getIb()
	{
		return ib;
	}

	public void setIb(ItemBean ib)
	{
		this.ib = ib;
	}

	public int getUnits()
	{
		return units;
	}

	public void setUnits(int units)
	{
		this.units = units;
	}

	public double getExtendedPrice()
	{
		return extendedPrice;
	}

	public void setExtendedPrice(double extendedPrice)
	{
		this.extendedPrice = extendedPrice;
	}
}
