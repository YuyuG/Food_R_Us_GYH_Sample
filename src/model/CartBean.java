package model;

import java.util.List;

public class CartBean
{
	private List<ItemPurchaseBean> items;
	private double total;
	private double shippingCost;
	private double hst;
	private double grandTotal;
	
	public CartBean(List<ItemPurchaseBean> items, double total,
			double shippingCost, double hst, double grandTotal)
	{
		super();
		this.items = items;
		this.total = total;
		this.shippingCost = shippingCost;
		this.hst = hst;
		this.grandTotal = grandTotal;
	}

	public List<ItemPurchaseBean> getItems()
	{
		return items;
	}

	public void setItems(List<ItemPurchaseBean> items)
	{
		this.items = items;
	}

	public double getTotal()
	{
		return total;
	}

	public void setTotal(double total)
	{
		this.total = total;
	}

	public double getShippingCost()
	{
		return shippingCost;
	}

	public void setShippingCost(int shippingCost)
	{
		this.shippingCost = shippingCost;
	}

	public double getHst()
	{
		return hst;
	}

	public void setHst(double hst)
	{
		this.hst = hst;
	}

	public double getGrandTotal()
	{
		return grandTotal;
	}

	public void setGrandTotal(double grandTotal)
	{
		this.grandTotal = grandTotal;
	}
}
