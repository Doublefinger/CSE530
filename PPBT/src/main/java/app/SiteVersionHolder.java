package app;

/**
 * Created by xiezhongsheng on 14-6-17.
 */
public class SiteVersionHolder {

    private String siteEnv;

    private volatile Long version = System.currentTimeMillis();

    public String getVersion() {
        return version + "";
    }

    public String getSiteEnv() {
        return siteEnv;
    }

    public boolean isProductionEnv() {
        return siteEnv != null && siteEnv.equals("production");
    }

    public void setSiteEnv(String siteEnv) {
        this.siteEnv = siteEnv;
    }
}
