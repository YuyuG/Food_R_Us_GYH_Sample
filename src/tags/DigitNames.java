package tags;
import javax.servlet.jsp.tagext.*;
import javax.servlet.jsp.*;

import java.io.*;


public class DigitNames extends SimpleTagSupport
{
	  private String max;
	  
	  private static final String[] tensNames = {
	        "zero",
	        "one",
	        "two",
	        "three",
	        "four",
	        "five",
	        "six",
	        "seven",
	        "eight",
	        "nine"
	    };

      public void doTag() throws JspException, IOException
      {
              JspWriter os = this.getJspContext().getOut();
              
              StringWriter sw = new StringWriter();
              getJspBody().invoke(sw);
              Double maxN = Double.parseDouble(this.max);
              String input = sw.toString();
              System.out.println(input);
              Double inputN = Double.parseDouble(input);
              System.out.println("double: " + inputN);
              String converted = sw.toString();
              if(inputN <= maxN && inputN >= 1){
            	 
            	  converted = this.convert(inputN.toString());
            	  System.out.println(inputN + " IS SMALLER THAN " + maxN);
                  os.write(" [" + converted + "].");
              }
      }
      
      public void setmax(String max)
      {
              this.max = max;
              System.out.println(this.max);
      }
      
      public String convert(String od)
      {	
    	 
    	 String regex = "\\.";
    	 String[] tokens = od.split(regex);
    	 
    	 System.out.println("Method takes that");
    	
    	 String convertedDigits = "";
    	  String[] splitted = tokens[0].split("");
    	 
    	  int length = splitted.length;
    	  String[] converted = new String[length];
    	  
    	  int index;
    	  
    	 for(int i = 0; i < splitted.length; i++){
    		index = (int) Double.parseDouble(splitted[i]);
    		converted[i] = tensNames[index];
    	 }
    	 
    	 for(int i = 0; i < converted.length; i ++){
    		 if(i < (converted.length - 1 )){
    		 convertedDigits = convertedDigits + converted[i] + "-";
    		 }else if(i == (converted.length - 1) ){
    		 convertedDigits = convertedDigits + converted[i];
    		 }
    	 }
    	 
    	  return convertedDigits;
    	  
      }
}
