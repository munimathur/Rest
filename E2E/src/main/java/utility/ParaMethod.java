package utility;


import java.util.ArrayList;

import org.testng.annotations.DataProvider;




public class ParaMethod {
	@DataProvider(name = "Testcase1")
	public static Object[][] Testcase1() throws Exception {
		ExcelReaderTestData readExl = new ExcelReaderTestData();
		String[][] tabArray = null;		
		ArrayList<ArrayList<String>> getList = readExl.getCellData("TestCase_1");
		tabArray=new String[getList.size()][getList.get(0).size()];
		for(int ix=0; ix<getList.size(); ix++){
			for(int ixx=0;ixx<getList.get(ix).size();ixx++){						
				tabArray[ix][ixx] = getList.get(ix).get(ixx);								
			}
		}
		return tabArray;
	}
	
	@DataProvider(name = "Testcase2")
	public static Object[][] Testcase2() throws Exception {
		ExcelReaderTestData readExl = new ExcelReaderTestData();
		String[][] tabArray = null;		
		ArrayList<ArrayList<String>> getList = readExl.getCellData("TestCase_2");
		tabArray=new String[getList.size()][getList.get(0).size()];
		for(int ix=0; ix<getList.size(); ix++){
			for(int ixx=0;ixx<getList.get(ix).size();ixx++){						
				tabArray[ix][ixx] = getList.get(ix).get(ixx);								
			}
		}
		return tabArray;
	}
	
	@DataProvider(name = "Testcase3")
	public static Object[][] Testcase3() throws Exception {
		ExcelReaderTestData readExl = new ExcelReaderTestData();
		String[][] tabArray = null;		
		ArrayList<ArrayList<String>> getList = readExl.getCellData("TestCase_3");
		tabArray=new String[getList.size()][getList.get(0).size()];
		for(int ix=0; ix<getList.size(); ix++){
			for(int ixx=0;ixx<getList.get(ix).size();ixx++){						
				tabArray[ix][ixx] = getList.get(ix).get(ixx);								
			}
		}
		return tabArray;
	
	}
	
}
