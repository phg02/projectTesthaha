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

public class statecity implements Handler {
    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/citystate.html";

    // Name of the HTML file in the resources folder
    private static final String TEMPLATE = ("citystate.html");

    @Override
    public void handle(Context context) throws Exception {
        // Create a simple HTML webpage in a String
        Map<String, Object> model = new HashMap<String, Object>();

        JDBCConnection jdbc = new JDBCConnection();
        ArrayList<Integer> yearselection = new ArrayList<Integer>();
        ArrayList<info> infos = jdbc.getallYear();

        for (info time : infos) {
            yearselection.add(time.year);
        }
        model.put("yearselection", yearselection);


        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        //COUNTRY SELECTION
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
