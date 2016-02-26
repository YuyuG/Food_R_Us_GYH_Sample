package model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class ItemXMLBean
{
	@XmlAttribute
	private String number;
	@XmlElement
	private String name;
	@XmlElement
	private double price;
	@XmlElement
	private int quantity;
	@XmlElement
	private double extended;
	public ItemXMLBean(String number, String name, double price, int quantity, double extended)
	{
		super();
		this.number = number;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.extended = extended;
	}
	
}
