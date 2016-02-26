package model;

import java.io.PrintWriter;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamResult;

import com.google.gson.Gson;

public class Item
{
	private ItemDAO dao;
	private static final int MONTHS_IN_YEAR=12;
	private static final String NO_ITEM = "No item";
	
	
	public Item() throws Exception {
		dao = new ItemDAO();
	}
	
	public List<ItemBean> getItemName() throws Exception{
		
		List<ItemBean> itemNameList = dao.getItemName();
		if(itemNameList == null){
			itemNameList.add(new ItemBean(Item.NO_ITEM, null, null, 0.0, 0, 0,0, 0, 0, 0.0));
		}
		return itemNameList;
		
	}
	public List<ItemBean> getItemByCategory(int catID) throws Exception{
		
		List<ItemBean> itemNameList = dao.retrieveItemByCategory(catID);
		if(itemNameList == null){
			itemNameList.add(new ItemBean(Item.NO_ITEM, null, null, 0.0, 0, 0,0, 0, 0, 0.0));
		}
		return itemNameList;
		
	}
	public List<ItemBean> getItemByName(String name) throws Exception{
		
		List<ItemBean> itemNameList = dao.getItemByName(name);
		if(itemNameList == null){
			itemNameList.add(new ItemBean(Item.NO_ITEM, null, null, 0.0, 0, 0,0, 0, 0, 0.0));
		}
		return itemNameList;
		
	}
	public ItemBean getItemByID(String id) throws Exception{
		
		ItemBean item = dao.getItemByID(id);
		if(item == null){
			item = new ItemBean(Item.NO_ITEM, null, null, 0.0, 0, 0,0, 0, 0, 0.0);
		}
		return item;
		
	}

	
	
}
