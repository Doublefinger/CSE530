package app.models;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.Table;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by baby on 15/6/5.
 */
@Table("site_errors")
public class SiteError extends Model {

    public static void saveException(Exception e) {
        saveException(e, null);
    }

    public static void saveException(Exception e, String extra) {
        SiteError error = new SiteError();
        error.set("error", getStackTraceString(e, extra));
        error.save();
    }

    public static String getStackTraceString(Throwable e, String extra) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        pw.flush();
        return (extra == null ? "" : extra + System.getProperty("line.separator")) + sw.toString();
    }
}
