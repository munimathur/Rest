package utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
* <h1>ExcelReaderTestData!</h1>
* The Purpose of this Class is to read test data from xls files.
* <p>
* <b>Note:</b> Giving proper comments in your program makes it more
* user friendly and it is assumed as a high quality code.
* @author  Matthew
* @version 1.0
* @since   2019-01-29 
*/
	public class ExcelReaderTestData {
    	int rowIndex = 0, columnIndex = 0;
        private static HSSFSheet ExcelWSheet;
   		private static HSSFWorkbook ExcelWBook;
   		private static HSSFCell Cell;
   		private static HSSFRow Row;

		//This method is to set the File path and to open the Excel file, Pass Excel Path and Sheetname as Arguments to this method 
		public void setExcelFile() throws Exception {
			try {
					PropertyFile prop = new PropertyFile();
			   		// Open the Excel file       				
					String fileName = prop.readPropFile("testDataPath"); 
					//File file = new File("../TestSet/Excel", fileName);
					File file;
					file = new File("./Excel", fileName);
					FileInputStream ExcelFile = new FileInputStream(file); 
					// Access the required test data sheet 
					
					ExcelWBook = new HSSFWorkbook(ExcelFile); 
					ExcelWSheet = ExcelWBook.getSheet("Sheet1"); 
				} catch (Exception e){ 
					throw (e); 
				} 
		}
	 
		public ArrayList<ArrayList<String>> getCellData(String getData) throws Exception{
			try{       				
	   				setExcelFile();  
	   				int tcColID = 0;
	   				//Cool
	   				ArrayList<ArrayList<String>> array = new ArrayList<ArrayList<String>>();	       				
	   				//Get TestcasesID:
	   				for (columnIndex = 0; columnIndex < ExcelWSheet.getRow(0).getPhysicalNumberOfCells(); columnIndex++) {       						
					Cell = ExcelWSheet.getRow(0).getCell(columnIndex);
					String cellData = Cell.getStringCellValue();
					if(cellData.contentEquals("TestCasesID")){       							
						//System.out.println("TestCasesID we got");
	       							tcColID = columnIndex;
	       							break;
	       						}       					
	       					}
	  
	       					int tcRowID = 0;
	       					boolean tcflag=false;
	       					boolean flagloop=false;
	       					for (rowIndex = 0; rowIndex < ExcelWSheet.getPhysicalNumberOfRows(); rowIndex++) {       					
			       				Cell = ExcelWSheet.getRow(rowIndex).getCell(tcColID);
		   						String getTCName = Cell.getStringCellValue();	   	   						
		   						if(getTCName.contains(getData) || tcflag==true){	   						
				       				if(getTCName.contains(getData) || getTCName.isEmpty()){
				       					if(getTCName.contains(getData)){
				       						tcRowID = rowIndex;
				       					}
				       					Cell = ExcelWSheet.getRow(rowIndex+1).getCell(tcColID);
				   						String getrowcnt = Cell.getStringCellValue();		       					
				       					if(getrowcnt.isEmpty()){
				       						tcflag=true;
											for (columnIndex = 0; columnIndex < ExcelWSheet.getRow(rowIndex).getPhysicalNumberOfCells(); columnIndex++) {
												Cell = ExcelWSheet.getRow(0).getCell(columnIndex);
												String getData1 = Cell.getStringCellValue();
												if (getData1.trim().contentEquals("Data1")) {
													Cell = ExcelWSheet.getRow(rowIndex + 1).getCell(columnIndex);
													// String DataExist =
													// Cell.getStringCellValue();
													String DataExist = null;
													try {
														DataExist = Cell.getStringCellValue();
													} catch (Exception e) {
														int getnum = (int) Cell.getNumericCellValue();
														DataExist = Integer.toString(getnum);
													}
													if (DataExist.isEmpty()) {
														flagloop = true;
														break;
													}
												}
											}
	       						if(flagloop==true){
	       							break;
	       						}			       						
	       					}else{			       						
	       						break;
	       					}			       					
			       			ArrayList<String> getObj = new ArrayList<String>();
	       					boolean dataflag = false;
	       					for(columnIndex = 0; columnIndex < ExcelWSheet.getRow(rowIndex).getPhysicalNumberOfCells(); columnIndex++){
	       						Cell = ExcelWSheet.getRow(rowIndex).getCell(columnIndex);
		   						String cellData2 = Cell.getStringCellValue();	
		   						if(!cellData2.isEmpty()){
		   							if(dataflag == true){
		   								//Cell = ExcelWSheet.getRow(tcRowID).getCell(columnIndex);
		   								//String getHeader = Cell.getStringCellValue();
		   								Cell = ExcelWSheet.getRow(rowIndex+1).getCell(columnIndex);
		   								String getValue=null; 
		   								try{
		   									getValue = Cell.getStringCellValue();
		   									//System.out.println(getValue);
		   								}catch(Exception e){
		   								    int getnum = (int) Cell.getNumericCellValue();
		   								    getValue = Integer.toString(getnum);
		   								}
		   								
		   								//getMap.put(getHeader, getValue);
		   								getObj.add(getValue);
		   								
		   							}				   							
		   							Cell = ExcelWSheet.getRow(0).getCell(columnIndex);
			   						String getData1 = Cell.getStringCellValue();		
		   							if(getData1.trim().contentEquals("Data1")){
		   								//Cell = ExcelWSheet.getRow(tcRowID).getCell(columnIndex);
		   								//String getHeader = Cell.getStringCellValue();
		   								Cell = ExcelWSheet.getRow(rowIndex+1).getCell(columnIndex);
		   								String getValue = null;
		   								try{
		   									getValue = Cell.getStringCellValue();
		   									System.out.println(getValue);
		   								}catch(Exception e){
		   								    int getnum = (int) Cell.getNumericCellValue();
		   								    getValue = Integer.toString(getnum);
		   								}	
		   								//getMap.put(getHeader, getValue);
		   								
		   								getObj.add(getValue);			   											   												   						
		   								dataflag = true;
		   							}				
		   						}	
	       					}	
	       					System.out.println("Cool Params :  "+getObj);
	       					array.add(getObj);	   
	       					System.out.println("aaaa "+ array);
	       				}
					}
					
				}	
				return array; 
			}catch (Exception e){
				return null;
			}
		}
	}