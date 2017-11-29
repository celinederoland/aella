package celinederoland.controllers;

import celinederoland.models.Dragoon;

import java.io.*;
import java.util.List;
import javax.servlet.*;
import javax.servlet.http.*;

import celinederoland.repositories.DragoonRepository;
import com.google.gson.Gson;

// Extend HttpServlet class
public class DragoonListResource extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");

        List<Dragoon> dragoons = (new DragoonRepository()).findAll();
        Gson gson = new Gson();
        String json = gson.toJson(dragoons);

        response.getWriter().println(json);
    }
}