package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.util.Map;
import java.util.HashMap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Example Index HTML class using Javalin
 * <p>
 * Generate a static HTML page using Javalin
 * by writing the raw HTML into a Java String object
 *
 * @author Timothy Wiley, 2023. email: timothy.wiley@rmit.edu.au
 * @author Santha Sumanasekara, 2021. email: santha.sumanasekara@rmit.edu.au
 */
public class PageST2B implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/country.html";
    public static final String TEMPLATE = "lv2Country.html";

    @Override
    public void handle(Context context) throws Exception {

        Map<String, Object> model = new HashMap<String, Object>();
        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Integer> yearselection = new ArrayList<Integer>();
        ArrayList<info> infos = jdbc.getallYear();

        for (info time : infos) {
            yearselection.add(time.year);
        }
        model.put("yearselection", yearselection);

        JDBCConnection jdbc1 = new JDBCConnection();
        ArrayList<String> countryselection = new ArrayList<String>();
        ArrayList<info> infos1 = jdbc1.getallCountry();

        for (info time1 : infos1) {
            countryselection.add(time1.country);
        }
        model.put("countryselection", countryselection);

        context.render(TEMPLATE, model);

    }
}
