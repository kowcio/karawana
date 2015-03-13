/*
 * 
 */
package rlhd.hd.base;

import org.apache.log4j.Logger;
import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestFalsePositiveReRunner implements ITestListener, IRetryAnalyzer {
    public final static int MAX_RETRY_COUNT = AbstractBaseTest.MAX_RETRY_COUNT;
    static Logger           log             = Logger.getLogger("log4j.logger.TestLogger");
    
    @Override
    public void onTestFailure(ITestResult result) { // test results change logic
    
        if (result.getMethod().getCurrentInvocationCount() < MAX_RETRY_COUNT && !result.isSuccess()) {
            result.setStatus(ITestResult.SKIP);
        } else if (result.getMethod().getCurrentInvocationCount() == MAX_RETRY_COUNT && !result.isSuccess()) {
            
        }
        
        log.info(" The end line result = " + result.getStatus() + " hashcode = " + result.hashCode());
        
    }
    
    /**
     * Zachodzi po metodach onFailure/Skip/Success
     */
    @Override
    public boolean retry(ITestResult result) {
        
        if (result.getMethod().getCurrentInvocationCount() >= MAX_RETRY_COUNT || result.isSuccess()) {
            log.info("NOT RETRYING zero= " + result.getMethod().getCurrentInvocationCount());
            return false;
        } else {
            log.info("RETRYING No. = " + result.getMethod().getCurrentInvocationCount());
            return true;
        }
        
    }
    
    @Override
    public void onTestStart(ITestResult result) {
    }
    
    @Override
    public void onTestSkipped(ITestResult result) {
    }
    
    @Override
    public void onTestSuccess(ITestResult result) {
    }
    
    @Override
    public void onStart(ITestContext context) {
    }
    
    @Override
    public void onFinish(ITestContext context) {
        
    }
    
    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        
    }
    
}
