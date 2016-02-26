package model;

public class ItemBean
{
	
	private String unit;
	private double costprice;
	private int supID;
	private int catID;
	private int reorder;
	private int onorder;
	private int qty;
	private double price;
	private String name;
	private String number;
	public ItemBean()
	{
		super();
		
	}


	public ItemBean( String name,
			String number, String unit, double costprice, int supID, int catID,
			int reorder, int onorder, int qty, double price)
	{
		super();
		this.unit = unit;
		this.costprice = costprice;
		this.supID = supID;
		this.catID = catID;
		this.reorder = reorder;
		this.onorder = onorder;
		this.qty = qty;
		this.price = price;
		this.name = name;
		this.number = number;
	}
	

	public String getUnit()
	{
		return unit;
	}
	public void setUnit(String unit)
	{
		this.unit = unit;
	}
	public double getCostprice()
	{
		return costprice;
	}
	public void setCostprice(double costprice)
	{
		this.costprice = costprice;
	}
	public int getSupID()
	{
		return supID;
	}
	public void setSupID(int supID)
	{
		this.supID = supID;
	}
	public int getCatID()
	{
		return catID;
	}
	public void setCatID(int catID)
	{
		this.catID = catID;
	}
	public int getReorder()
	{
		return reorder;
	}
	public void setReorder(int reorder)
	{
		this.reorder = reorder;
	}
	public int getOnorder()
	{
		return onorder;
	}
	public void setOnorder(int onorder)
	{
		this.onorder = onorder;
	}
	public int getQty()
	{
		return qty;
	}
	public void setQty(int qty)
	{
		this.qty = qty;
	}
	public double getPrice()
	{
		return price;
	}
	public void setPrice(double price)
	{
		this.price = price;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getNumber()
	{
		return number;
	}
	public void setNumber(String number)
	{
		this.number = number;
	}
	

	@Override
	public String toString()
	{
		return "ItemBean [toString()=" + super.toString() + "]";
	}
	
	
}
