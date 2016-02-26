package model;

import java.io.File;
import java.io.FileWriter;
import java.io.StringWriter;
import java.util.Date;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.transform.stream.StreamResult;

public class PO
{

	public PO()
	{
		super();
	}

	public String genFileName(ClientBean customer, String folder){
		StringBuffer fileName = new StringBuffer();
		
		String account = customer.getAccount();
		fileName.append("po");
		fileName.append(account);
		fileName.append("_0");
		fileName.append(this.getPONumber(account, folder)+1);
		fileName.append(".xml");
		System.out.println("GR" + fileName.toString());
		return fileName.toString();
		
	}
	

	private int getPONumber(String number, String folder)
	{
		File directory = new File(folder);
		
		String[] fileList = directory.list();
	
		int total = 0;
		for (String fileName : fileList)
		{
			// new Integer(number).
			fileName.matches("po" + number + "_0.[.]xml");
			// fileName.contains(number+"");
			total++;
		}
		System.out.println("GGGGEEEETTTT HHHHEREERRRRR" + total);
		return total;
	}

	public Boolean exportPO(CartBean cart, ClientBean client,
			String filename, String filePath) throws Exception
	{
		Boolean success = false;
		Date date = new Date();
		//int id = Integer.parseInt(idString);
		
		try{
			

			CartXMLBean cw = new CartXMLBean(1, date, client, cart);

			JAXBContext jc = JAXBContext.newInstance(cw.getClass());
			Marshaller marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
					Boolean.TRUE);
			marshaller.setProperty(Marshaller.JAXB_FRAGMENT, Boolean.TRUE);
			// marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION,
			// "SIS.xsd");
			StringWriter sw = new StringWriter();
			sw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
			sw.write("<?xml-stylesheet type=\"text/xsl\" href=\"PO.xsd\"?>\n");
			
			marshaller.marshal(cw, new StreamResult(sw));
			
			System.out.println("ggeeettttt hhhherrrreeee");
			
			System.out.println(sw.toString()); // for debugging
			FileWriter fw = new FileWriter(filename);
			System.out.println("workspace/foodRUs6/WebContent/" + filePath);
			FileWriter fw2 = new FileWriter("workspace/foodRUs6/WebContent/" + filePath);
			FileWriter fw3 = new FileWriter("workspace/foodRUs6/WebContent/WEB-INF/" + filePath);
			fw.write(sw.toString());
			fw2.write(sw.toString());
			fw3.write(sw.toString());
			fw3.close();
			fw2.close();
			fw.close();
			success = true;
		}catch (Exception e){
			e.printStackTrace();
			success = false;
		}
		
		return success;
	}
	private int getID() throws Exception 
	{
		File dir = new File("workspace/foodRUs6/WebContent/WEB-INF/PO");
		if(dir.isDirectory())
		{
			return dir.list().length + 1;
		}
		else
		{
			throw new Exception("No directory!");
		}
		
	}
}
