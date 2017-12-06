package com.qisen.mts.common.util;

import java.io.InputStream;
import java.util.Properties;

import org.springframework.stereotype.Component;

@Component
public class ConfigUtil
{
    /**
     * param:pname 要读取的参数名称,sp.property文件在classes根目录下
     **/
    public String getPara(String pname)
    {
        Properties prop = new Properties();
        try {
            InputStream is = getClass().getResourceAsStream("/sp.properties");
            prop.load(is);
            if (is != null)
                is.close();
        } catch (Exception e) {
            System.out.println(e + " sp.property not found!");
        }
        return prop.getProperty(pname);
    }
}
