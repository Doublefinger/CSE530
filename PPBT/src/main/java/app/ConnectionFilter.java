package app;

import app.models.SiteError;
import app.services.SpringContextHolder;
import org.javalite.activejdbc.DB;
import org.javalite.activeweb.controller_filters.ControllerFilterAdapter;

import javax.sql.DataSource;
import java.sql.SQLException;

public class ConnectionFilter extends ControllerFilterAdapter {

    @Override
    public void before() {
        DB db = new DB("default");
        try {
            DataSource ppbtDataSource = SpringContextHolder.getBean("ppbtDataSource");
            db.attach(ppbtDataSource.getConnection());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void after() {
        DB db = new DB("default");
        db.close();
    }

    @Override
    public void onException(Exception e) {

        try {
            SiteError.saveException(e);
        } catch (Exception ex) {
        }

        DB db = new DB("default");
        db.close();
    }
}
