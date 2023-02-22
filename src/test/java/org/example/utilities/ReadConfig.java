package org.example.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ReadConfig {
    Properties pro;

    public ReadConfig()

    {
        File src= new File("./configuration/config.properties");

        try {
            FileInputStream fis= new FileInputStream(src);
            pro= new Properties();
            pro.load(fis);
        }catch (Exception e) {
            // TODO: handle exception
            System.out.println("exception is"+e.getMessage());
        }
    }

    public String getApplicationURL()
    {
        String url= pro.getProperty("BaseUrl");
        return url;
    }
    public String getUsername()
    {
        String username= pro.getProperty("username");
        return username;
    }
    public String getPswd()
    {
        String pswd= pro.getProperty("pswd");
        return pswd;
    }
    public String getBrowser()
    {
        String browser= pro.getProperty("browser");
        return browser;
    }
}
