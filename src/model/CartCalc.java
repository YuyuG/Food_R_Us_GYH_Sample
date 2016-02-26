package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CartCalc
{
	
	
	public CartCalc(){
		
	}
	
	public List<ItemPurchaseBean> convertList(List<ItemBean> ibl, Map<String, Integer> cart){
		
		List<ItemPurchaseBean> result = new ArrayList<ItemPurchaseBean>();
		
	
		for(int i=0; i < ibl.size(); i++){
			String productID = ibl.get(i).getNumber();
			int qty = cart.get(ibl.get(i).getNumber());
			result.add(new ItemPurchaseBean(ibl.get(i), qty, ibl.get(i).getPrice()));
		}
		
		
		return result;
		
	}
	
	public double calculateExtendedPrice(ItemPurchaseBean ipb)
	{
		double ep = ipb.getIb().getPrice() * ipb.getUnits();
		ipb.setExtendedPrice(ep);
		return ep;
	}
	
	public boolean validateQty(String qty) throws Exception{
		int quantity = 0;
		try{
		 quantity = Integer.parseInt(qty);
		}
		catch(Exception e){
			throw new Exception("Cannot Update: Quantity should be numeric.");
		}
		if(quantity < 0){
			throw new Exception("Cannot Update: Quantity should be positive.");
		}
		return true;
	}
	
	
	public double calculateSub(List<ItemPurchaseBean> ipbl){
		
		double subtotal = 0;
		for(int i=0; i < ipbl.size(); i++){
			
			subtotal = ipbl.get(i).getExtendedPrice() + subtotal;
		}
		
		return subtotal;
		
	}
	
	public double calculateHST(double subtotal){
		
		double hst = 0;
		
		hst = subtotal * 0.13;
		return hst;
		
	}
	
	public double calcutlateGrandTotal(double subtotal, double hst){
		
		
		double grandtotal = subtotal + hst;
		return grandtotal;
	}
	
	
	
	
	
	
	
	
}
