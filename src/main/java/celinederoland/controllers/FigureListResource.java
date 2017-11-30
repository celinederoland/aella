package celinederoland.controllers;

import celinederoland.models.Figure;
import celinederoland.repositories.FigureRepository;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

// Extend HttpServlet class
public class FigureListResource extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");

        List<Figure> figures = (new FigureRepository()).findAll();
        Gson gson = new Gson();
        String json = gson.toJson(figures);

        response.getWriter().println(json);
    }
}