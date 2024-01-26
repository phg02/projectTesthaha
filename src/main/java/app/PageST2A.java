package app;

import java.util.ArrayList;

import io.javalin.http.Context;
import io.javalin.http.Handler;
import java.util.HashMap;
import java.util.Map;
import java.lang.reflect.Array;
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
public class PageST2A implements Handler {

    // URL of this page relative to http://localhost:7001/
    public static final String URL = "/world.html";

    // Name of the HTML file in the resources folder
    private static final String TEMPLATE = "level2.html";

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

        String startYear = context.formParam("startyear");
        String endYear = context.formParam("endyear");

        /*
         * if (Integer.valueOf(startYear) < Integer.valueOf(endYear)) {
         * model.put("answer", "The start year must be less than the end year");
         * } else if (startYear == null || endYear == null) {
         * model.put("answer", "Please select a year");
         * } else {
         */
        JDBCConnection jdbc1 = new JDBCConnection();
        ArrayList<info> result = jdbc1.lvl2query(startYear, endYear);
        model.put("answer", result);

        // }
        JDBCConnection jdbc2 = new JDBCConnection();
        ArrayList<info> result2 = jdbc2.lv2queryYearReader(startYear);

        model.put("answer2", result2);

        JDBCConnection jdbc3 = new JDBCConnection();
        ArrayList<info> result3 = jdbc3.lv2queryYearReader(endYear);

        model.put("answer3", result3);
        // DO NOT MODIFY THIS
        // Makes Javalin render the webpage
        context.render(TEMPLATE, model);
    }

}
