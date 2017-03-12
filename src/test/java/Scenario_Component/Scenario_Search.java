package Scenario_Component;

import java.io.IOException;
import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import Generic_Component.Base_Class;
import PageObject_Component.PageObject_Search;

public class Scenario_Search extends Base_Class {
	
	public static Logger log= Logger.getLogger(Scenario_Search.class);
	SoftAssert sAssert= new SoftAssert();
	
	@Test(dataProvider="dp_InvalidSearch", dataProviderClass=DataProvider_Component.DataProvider_Search.class,groups={"smoke"})
	public void testInvalidSearch(Map Search) throws IOException, InterruptedException
	{
		String TC_ID = Search.get("TC_ID").toString();
		String Order = Search.get("Order").toString();
		String Search_item = Search.get("Search_item").toString();
		String Exp_Result = Search.get("Exp_Result").toString();
		
		Start_Server();
		log.info("Executing the Testcase " +TC_ID +" Order is  "+Order);
		
		Initialize_app();	
		
		PageObject_Search BS_pob=new PageObject_Search(driver);
		
		Explicitwait(BS_pob.Search_btn, 25);
		BS_pob.Click_Search();
		
		Explicitwait(BS_pob.Search_txtbox, 25);
		BS_pob.Enter_Searchtxt(Search_item);
		
		Explicitwait(BS_pob.Invalid_msg, 25);
		String Actual_Result = BS_pob.getInvalidmsg();
		
		if(Actual_Result.equals(Exp_Result))
		{
			log.info("Passed as Actual Result is " +Actual_Result+ "  Expected Result is "+Exp_Result);
		}
		else
		{
			log.info("Failed as Actual Result is " +Actual_Result+ "  Expected Result is "+Exp_Result);
			sAssert.fail("Failed as Actual Result is " +Actual_Result+ "  Expected Result is "+Exp_Result);
		}
		
		Stop_Server();
		sAssert.assertAll();
		
	}

	
	
	
	@Test(dataProvider="dp_ValidSearch", dataProviderClass=DataProvider_Component.DataProvider_Search.class,groups={"regression"})
	public void testValidSearch(Map Search) throws IOException, InterruptedException
	{
		String TC_ID = Search.get("TC_ID").toString();
		String Order = Search.get("Order").toString();
		String Search_item = Search.get("Search_item").toString();
		String Exp_Result = Search.get("Exp_Result").toString().replace(".0", "");
		
		Start_Server();
		log.info("Executing the Testcase "+TC_ID +" Order is "+Order);
		
		Initialize_app();
		
		PageObject_Search BS_Pob=new PageObject_Search(driver);
		
		Explicitwait(BS_Pob.Search_btn, 25);
		BS_Pob.Click_Search();
		
		Explicitwait(BS_Pob.Search_txtbox, 25);
		BS_Pob.Enter_Searchtxt(Search_item);
		
		Explicitwait(BS_Pob.Valid_msg, 25);
		String Output = BS_Pob.getValidmsg();
		
		String Actual_Result = Output.replace(" products", "");
		
		if(Actual_Result.equals(Exp_Result))
		{
			log.info("Passed as Actual Result is " +Actual_Result + " Expected Result  is "+Exp_Result);
			Snapshot1(TC_ID, Order);
		}
		else
		{
			log.info("Failed as Actual Result is " +Actual_Result + " Expected Result  is "+Exp_Result);
			Snapshot1(TC_ID, Order);
			sAssert.fail("Failed as Actual Result is " +Actual_Result + " Expected Result  is "+Exp_Result);
		}
		
		
		Stop_Server();
		sAssert.assertAll();
		
		
	}
}
