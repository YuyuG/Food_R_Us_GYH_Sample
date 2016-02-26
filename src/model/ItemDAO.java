package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ItemDAO
{
	private DataSource dataSource;
	
	public ItemDAO() throws Exception{
		this.dataSource = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
	}

	public ItemDAO(DataSource dataSource) throws Exception
	{
		super();
		this.dataSource = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
	}
	
	
	public List<ItemBean> getItemName() throws Exception
	{
		try{
		Connection con = this.dataSource.getConnection();
		Statement stm = con.createStatement();
		ResultSet rs;
		stm.executeUpdate("set schema roumani");
		String query = "select "
				+ "unit,costprice,supid,catid,reorder,onorder,qty,price,name,number "
				+ "from ITEM";
		
		rs = stm.executeQuery(query);
		List<ItemBean> list = new ArrayList<ItemBean>();
		
		
		while(rs.next()){
			list.add(new ItemBean(rs.getString("NAME"), rs.getString("NUMBER"), rs.getString("UNIT"), 
					rs.getDouble("COSTPRICE"), rs.getInt("SUPID"), rs.getInt("CATID"), rs.getInt("REORDER"),
					rs.getInt("ONORDER"), rs.getInt("QTY"), rs.getDouble("PRICE"))); 
		
		}
		
		rs.close();
		stm.close();
		con.close();
		
		
		return list;
		}
		catch (Exception e){
			e.printStackTrace();
			System.out.println("ITemDAO Get error");
			throw e;
			
		}
	}
	
	
	public List<ItemBean> retrieveItemByCategory(int catid) throws SQLException
	{
		try{
			Connection con = this.dataSource.getConnection();
			Statement stm = con.createStatement();
			ResultSet rs;
			stm.executeUpdate("set schema roumani");
			String query = "select "
					+ "unit,costprice,supid,catid,reorder,onorder,qty,price,name,number "
					+ "from ITEM where catid=" +
					catid;
			
			rs = stm.executeQuery(query);
			List<ItemBean> list = new ArrayList<ItemBean>();
			
			while(rs.next()){
				list.add(new ItemBean(rs.getString("NAME"), rs.getString("NUMBER"), rs.getString("UNIT"), 
						rs.getDouble("COSTPRICE"), rs.getInt("SUPID"), rs.getInt("CATID"), rs.getInt("REORDER"),
						rs.getInt("ONORDER"), rs.getInt("QTY"), rs.getDouble("PRICE"))); 
			
			}
			
			rs.close();
			stm.close();
			con.close();
			
			
			return list;
			}
			catch (Exception e){
				e.printStackTrace();
				System.out.println("ITemDAO Get error");
				throw e;
				
			}
	}
	
	public List<ItemBean> getItemByName(String name) throws Exception{
		
		try{
			Connection con = this.dataSource.getConnection();
			Statement stm = con.createStatement();
			ResultSet rs;
			stm.executeUpdate("set schema roumani");
			String query = "select "
					+ "unit,costprice,supid,catid,reorder,onorder,qty,price,name,number "
					+ "from ITEM " + "where lower(name) like " + "lower('%" +name+"%')";
			
			rs = stm.executeQuery(query);
			List<ItemBean> list = new ArrayList<ItemBean>();
			
			
			while(rs.next()){
				list.add(new ItemBean(rs.getString("NAME"), rs.getString("NUMBER"), rs.getString("UNIT"), 
						rs.getDouble("COSTPRICE"), rs.getInt("SUPID"), rs.getInt("CATID"), rs.getInt("REORDER"),
						rs.getInt("ONORDER"), rs.getInt("QTY"), rs.getDouble("PRICE"))); 
			
			}
			
			rs.close();
			stm.close();
			con.close();
			
			
			return list;
			}
			catch (Exception e){
				e.printStackTrace();
				System.out.println("ITemDAO Get error");
				throw e;
				
			}
		
	}

	public ItemBean getItemByID(String id) throws Exception{
		
		try{
			Connection con = this.dataSource.getConnection();
			Statement stm = con.createStatement();
			ResultSet rs;
			stm.executeUpdate("set schema roumani");
			String query = "select "
					+ "unit,costprice,supid,catid,reorder,onorder,qty,price,name,number "
					+ "from ITEM" + " where number like '" + id + "'";
			
			rs = stm.executeQuery(query);
			ItemBean found = new ItemBean();
			
			
			while(rs.next()){
				found = new ItemBean(rs.getString("NAME"), rs.getString("NUMBER"), rs.getString("UNIT"), 
						rs.getDouble("COSTPRICE"), rs.getInt("SUPID"), rs.getInt("CATID"), rs.getInt("REORDER"),
						rs.getInt("ONORDER"), rs.getInt("QTY"), rs.getDouble("PRICE")); 
			
			}
			
			rs.close();
			stm.close();
			con.close();
			
			
			return found;
			}
			catch (Exception e){
				e.printStackTrace();
				System.out.println("No such item id");
				throw e;
				
			}
		
	}
	
	
	
}
