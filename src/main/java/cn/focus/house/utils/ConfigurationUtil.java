package cn.focus.house.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigurationUtil {

    private Properties properties = new Properties();

    private String configFile;

    public ConfigurationUtil() {
    }

    public ConfigurationUtil(String filename) {
        this.configFile = filename;
        reset(this.configFile);
    }

    public void init() {
        reset(this.configFile);
    }

    protected void reset(String filename) {
        InputStream in = null;
        try {
            in = ConfigurationUtil.class.getResourceAsStream(filename);
            properties.load(in);
        } catch (Exception e) {
        } finally {
            try {
                in.close();
            } catch (IOException ioe) {
            }
        }
    }

    public String get(String property) {
        return properties.getProperty(property);
    }

    public String get(String property, String defaultValue) {
        String result = properties.getProperty(property);
        if (result == null) {
            result = defaultValue;
        }
        return result;
    }

    public int getInt(String property) {
        try {
            return Integer.valueOf(get(property)).intValue();
        } catch (Exception e) {
            return Integer.MAX_VALUE;
        }
    }

    public int getInt(String property, int defaultValue) {
        try {
            return Integer.valueOf(get(property)).intValue();
        } catch (Exception e) {
            return defaultValue;
        }
    }

    public boolean getBoolean(String property) {
        try {
            return Boolean.valueOf(get(property)).booleanValue();
        } catch (Exception e) {
            return false;
        }
    }

    public String getConfigFile() {
        return configFile;
    }

    public void setConfigFile(String configFile) {
        this.configFile = configFile;
    }
}
