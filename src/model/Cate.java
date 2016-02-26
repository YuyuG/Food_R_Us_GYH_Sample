package model;

import java.sql.SQLException;
import java.util.List;

public class Cate
{
	private final CateDAO dao;

	public Cate() throws Exception
	{
		this.dao = new CateDAO();
	}

	public List<CateBean> retrieveCatrgory() throws Exception
	{
		try
		{
			return this.dao.retrieveCategory();
		} catch (SQLException e)
		{
			System.out.println(e);
			throw new Exception(
					"sql query or database connection has a few problems!", e);
		}
	}
}
