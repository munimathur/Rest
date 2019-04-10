package e2e_framework;


import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;



public abstract class ParallelClassesBase extends Base {
    
    final String fileName = getOutputFolder() + getClass().getPackage().getName() + ".ParallelClasses.html";
    
    @BeforeSuite
    public void setup() {
        ExtentManager.createInstance(fileName);
        ExtentTestManager.setReporter(ExtentManager.getInstance());
    }
    
    @AfterSuite
    public void tearDown() {
        ExtentManager.getInstance().flush();
    }
    
}
