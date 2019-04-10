

package utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


/**
* <h1>PropertyFileReader!</h1>
* The Purpose of this Class is to read and write property files.
* <p>
* <b>Note:</b> Giving proper comments in your program makes it more
* user friendly and it is assumed as a high quality code.
* @author  Matthew
* @version 1.0
* @since   2019-01-29 
*/

public class PropertyFile {
		 
	/**
	 * Read Configuration Property file
	 * @return value of the key
	*/
	 public String readPropFile(String key) {	 
		Properties prop = new Properties();
		InputStream input = null;
		String getProp = null;
		try {	 						
			input = new FileInputStream("./Excel/configuration.properties");
			// load a properties file
			prop.load(input);
			// get the property value and print it out
			getProp = prop.getProperty(key);	
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return getProp;	 
	  }
	
	
	 /**
	  * Read App/Project's Property file
	  * @return value of the key
	 */
	  public String readPara(String key, String propFile) {	 
			Properties prop = new Properties();
			InputStream input = null;
			String getProp = null;
			try {	 						
				
				input = new FileInputStream("Excel/"+propFile+".properties");
				// load a properties file
				prop.load(input);
				// get the property value and print it out
				getProp = prop.getProperty(key);	
				
			} catch (IOException ex) {
				ex.printStackTrace();
			} finally {
				if (input != null) {
					try {
						input.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			return getProp;	 
		  }
		
	  	
	  	
	  
	  	/**
		  * Write App/Project's Property file
		  *
		 */
		  public void writePara(String key, String val, String propFile) {	 
				Properties prop = new Properties();		
				InputStream input = null;
				FileOutputStream out = null;
			
				try{
					input = new FileInputStream("./Excel/"+propFile+".properties");
					// load a properties file
					prop.load(input);											        			        		       
					out = new FileOutputStream("./Excel/"+propFile+".properties");
					prop.setProperty(key, val);	
					//Store to Store Parameter file
					prop.store(out, "This is an optional header comment string");
				}catch(Exception e){
					e.printStackTrace();
				} finally {
					if (input != null) {
						try {
							input.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}											
		 } 
		  
	 /**
	  * Clear App/Project's Property file
	  * 
	 */
	  public void clearPropFile(String key, String val, String propFile) throws Exception {	 
			Properties prop = new Properties();		
			FileOutputStream out = null;
			//String getProp = null;
			try{		
				out = new FileOutputStream("./Excel/"+propFile+".properties");
				prop.setProperty(key, val);	
				//Store to Store Parameter file
				prop.store(out, "This is an optional header comment string");
			}catch(Exception e){									        			        		       
				e.printStackTrace();
			}
	  }
		
	
}
