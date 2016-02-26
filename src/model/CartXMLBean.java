package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="order")
public class CartXMLBean
{	
	@XmlAttribute
	private int id;
	@XmlAttribute (name = "submitted")
	private Date submitted;
	@XmlElement(name = "customer")
	private ClientBean customer;
	@XmlElementWrapper(name = "items")
	@XmlElement(name = "item")
	private List<ItemXMLBean> items;
	@XmlElement(name = "total")
	private double total;
	@XmlElement(name = "shipping")
	private double shippingCost;
	@XmlElement(name ="HST")
	private double hst;
	@XmlElement
	private double grandTotal;
	public CartXMLBean(int id, Date sumitDate, ClientBean client,
			CartBean cartBean)
	{
		super();
		this.items = new ArrayList<ItemXMLBean>();
		this.id = id;
		this.submitted = sumitDate;
		this.customer = client;
		for(int i = 0; i< cartBean.getItems().size();i++)
		{
			ItemPurchaseBean iipb = cartBean.getItems().get(i);
			ItemBean ib = iipb.getIb();
			this.items.add(new ItemXMLBean(ib.getNumber(),ib.getName(), ib.getPrice(),iipb.getUnits(), iipb.getExtendedPrice()));
		}
		this.total = cartBean.getTotal();
		this.shippingCost = cartBean.getShippingCost();
		this.hst = cartBean.getHst();
		this.grandTotal = cartBean.getGrandTotal();
	}

	public CartXMLBean()
	{
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
	