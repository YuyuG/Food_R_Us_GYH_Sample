package model;


import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.sun.org.apache.xml.internal.security.utils.Base64;

public class CateDAO
{
	private DataSource dataSource;

	public CateDAO() throws Exception
	{
		this.dataSource = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
	}

	public CateDAO(DataSource dataSource) throws Exception{
		super();
		this.dataSource = (DataSource) (new InitialContext()).lookup("java:/comp/env/jdbc/EECS");
	}
	
	public List<CateBean> retrieveCategory() throws SQLException, IOException
	{
		final String query = "select id, name, description, picture from CATEGORY";
		System.out.println(query);
		return executeQueryAndExtractBean(query);
	}

	private List<CateBean> executeQueryAndExtractBean(String query)
			throws SQLException, IOException
	{
		List<CateBean> cgb = new ArrayList<CateBean>();
		int id = -1;
		String name = null;
		String description = null;
		String picture = null; 

		try (Connection connection = this.dataSource.getConnection();
				Statement statement = connection.createStatement();)
		{
			final String schema = "set schema roumani";
			statement.executeUpdate(schema);
			ResultSet rs = statement.executeQuery(query);
			while (rs.next())
			{
				id = rs.getInt("id");
				name = rs.getString("name");
				description = rs.getString("description");
				Blob input = rs.getBlob("picture");
				picture = Base64.encode(input.getBytes((long)1, (int) input.length()));
				cgb.add((new CateBean(id, name, description, picture)));
			}
			return cgb;
		}
	}
}
