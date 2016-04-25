package app.config;

/**
 * Created by Doublefinger on 11/16/15.
 */
public class PPBTConfig extends BaseConfig {

    public static String getSystemEnv() {
        return getString("PPBT_ENV", "development");
    }

    public static boolean isDebugMode() {
        return getBool("DEBUG_MODE", false);
    }

    public static String getApplicationName() {
        return getString("APPLICATION_NAME", "");
    }
}
