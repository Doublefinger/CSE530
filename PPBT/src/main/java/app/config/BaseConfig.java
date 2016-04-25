package app.config;

import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Doublefinger on 11/16/15.
 */
public class BaseConfig {

    //check flag
    protected static final boolean IS_CHECK_NOT_CONFIG_KEY = false;

    private static PropertiesConfiguration settingsConfiguration = null;

    private static final String SETTINGS_FILE_PATH = System.getenv("PPBT_ENVIRONMENTS_FILE") == null ? System.getProperty("PPBT_ENVIRONMENTS_FILE") : System.getenv("PPBT_ENVIRONMENTS_FILE");

    static {
        settingsConfiguration = loadConfigFile(settingsConfiguration, SETTINGS_FILE_PATH);
    }

    public static PropertiesConfiguration loadConfigFile(PropertiesConfiguration configuration, String filePth) {
        try {
            if (!StringUtils.isBlank(filePth)) {
                configuration = new PropertiesConfiguration(filePth);
                configuration.setReloadingStrategy(new FileChangedReloadingStrategy());
            }
        } catch (Exception e) {
            // do nothing make client check!!!
            return null;
        }
        return configuration;
    }

    public static Map<String, String> getAllSettings() {
        Map<String, String> mapValues = new ListOrderedMap();
        Iterator<String> keys = settingsConfiguration.getKeys();
        while (keys.hasNext()) {
            String key = keys.next();
            if (key == null) {
                continue;
            }

            String value = getString(key, "");
            mapValues.put(key, value);
        }
        return mapValues;
    }

    private static void checkNullValue(String key, String value, boolean env) {
        if (IS_CHECK_NOT_CONFIG_KEY && value == null) {
            throw new RuntimeException((env ? "环境" : "业务") + "配置文件中找不到Key：'" + key + "'，请检查相应的配置文件！");
        }
    }

    protected static String getString(String key, String defaultValue) {
        return getString(settingsConfiguration, key, defaultValue);
    }

    protected static Boolean getBool(String key, Boolean defaultValue) {
        return getBool(settingsConfiguration, key, defaultValue);
    }

    protected static int getInt(String key, int defaultValue) {
        return getInt(settingsConfiguration, key, defaultValue);
    }

    protected static BigDecimal getDecimal(String key, BigDecimal defaultValue) {
        return getDecimal(settingsConfiguration, key, defaultValue);
    }

    protected static String getString(PropertiesConfiguration configuration, String key, String defaultValue) {
        String value = configuration.getString(key);
        checkNullValue(key, value, false);
        //no value
        if (value == null) {
            return defaultValue;
        }
        try {
            value = new String(value.getBytes("ISO8859-1"), "UTF-8");
        } catch (Exception e) {
            return defaultValue;
        }
        return value;
    }

    protected static Boolean getBool(PropertiesConfiguration configuration, String key, Boolean defaultValue) {
        String value = configuration.getString(key);
        checkNullValue(key, value, false);
        return value == null ? defaultValue : Boolean.parseBoolean(value);
    }

    protected static int getInt(PropertiesConfiguration configuration, String key, int defaultValue) {
        String value = configuration.getString(key);
        checkNullValue(key, value, false);
        return value == null ? defaultValue : Integer.parseInt(value);
    }

    protected static BigDecimal getDecimal(PropertiesConfiguration configuration, String key, BigDecimal defaultValue) {
        String value = configuration.getString(key);
        checkNullValue(key, value, false);
        return value == null ? defaultValue : new BigDecimal(value);
    }
}
