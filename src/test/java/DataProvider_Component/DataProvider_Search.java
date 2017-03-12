package DataProvider_Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.testng.annotations.DataProvider;

import Generic_Component.ExcelReadWrite;

public class DataProvider_Search {
	
	@DataProvider(name="dp_InvalidSearch")
	public static Iterator<Object[]> getInvalidSearchdata() throws IOException
	{
		List<Object[]> Obj = flagRowCount("InvalidSearch");
		return Obj.iterator();
		
	}
	
	@DataProvider(name="dp_ValidSearch")
	public static Iterator<Object[]> getValidSearchdata() throws IOException
	{
		
		List<Object[]> Obj = flagRowCount("ValidSearch");
		return Obj.iterator();
	}
	
	
	public static List<Object[]> flagRowCount(String Scriptname) throws IOException
	{
		ExcelReadWrite xl= new ExcelReadWrite("F:\\Appium_Workspace\\BB_Poject\\TestData\\TestData.xls");
		HSSFSheet Scenario_Search = xl.Setsheet("Scenario_Search");
		
		int RowCount = xl.getrowcount(Scenario_Search);
		int ColCount = xl.getcolcount(Scenario_Search, 0);
		
		//Create the List of Object array
		List<Object[]> list_Search= new ArrayList<Object[]>();
		
		for(int irow=1;irow<=RowCount;irow++)
		{
			String Execute_Flag = xl.Readvalue(Scenario_Search, irow, "Execute_Flag");
			String Script_name = xl.Readvalue(Scenario_Search, irow, "Script_name");
			
			if((Execute_Flag.equals("Y")) && (Script_name.equals(Scriptname)))
			{
				//Create the Map
				Map<String,String> dsMap= new HashMap<String,String>();
				
				for(int jCol=0;jCol<=ColCount;jCol++)
				{
					
					String Key = xl.Readvalue(Scenario_Search, 0, jCol);
					String Value = xl.Readvalue(Scenario_Search, irow, jCol);
					
					dsMap.put(Key, Value);			
					
					
				}//end of for loop for column
				
				Object[] x= new Object[1];
				x[0]=dsMap;
				
				list_Search.add(x);			
				
				
			}		
			
			
		}//end of for loop for row
		
		return list_Search;
		
	}
	

}
