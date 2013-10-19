package org.ganjp.jpw.core.model;

public class UserAgent {  
    private String browserType;  
    private String browserVersion; 
    private String platformType;  
    private String platformSeries; 
    private String platformVersion;  
      
    public UserAgent(){}  
      
    public UserAgent(String browserType, String browserVersion,  
            String platformType, String platformSeries, String platformVersion){  
        this.browserType = browserType;  
        this.browserVersion = browserVersion;  
        this.platformType = platformType;  
        this.platformSeries = platformSeries;  
        this.platformVersion = platformVersion;  
    }  
      
    public String getBrowserType() {  
        return browserType;  
    }  
    public void setBrowserType(String browserType) {  
        this.browserType = browserType;  
    }  
    public String getBrowserVersion() {  
        return browserVersion;  
    }  
    public void setBrowserVersion(String browserVersion) {  
        this.browserVersion = browserVersion;  
    }  
    public String getPlatformType() {  
        return platformType;  
    }  
    public void setPlatformType(String platformType) {  
        this.platformType = platformType;  
    }  
    public String getPlatformSeries() {  
        return platformSeries;  
    }  
    public void setPlatformSeries(String platformSeries) {  
        this.platformSeries = platformSeries;  
    }  
    public String getPlatformVersion() {  
        return platformVersion;  
    }  
    public void setPlatformVersion(String platformVersion) {  
        this.platformVersion = platformVersion;  
    }  
      
}  