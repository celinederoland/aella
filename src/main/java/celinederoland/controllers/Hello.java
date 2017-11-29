package celinederoland.controllers;

import celinederoland.models.Dragoon;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import com.google.gson.Gson;

// Extend HttpServlet class
public class Hello extends HttpServlet {

    private String message;

    public void init() throws ServletException {
        // Do required initialization
        this.message = "Hello it's Aella project";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Set response content type
        response.setContentType("application/json");

        Dragoon aella = new Dragoon();
        Gson gson = new Gson();
        String json = gson.toJson(aella);

        // Actual logic goes here.
        PrintWriter out = response.getWriter();
        out.println(json);
    }

    public void destroy() {
        // do nothing.
    }
}