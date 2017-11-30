package celinederoland.controllers;

import celinederoland.errors.HttpError;
import celinederoland.models.Figure;
import celinederoland.repositories.FigureRepository;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FigureResource extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");

        Figure figure = this.getFigure(request);
        if(figure == null) {
            new HttpError(404, "NotFound", response);
            return;
        }
        Gson gson = new Gson();
        String json = gson.toJson(figure);
        response.getWriter().println(json);
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("application/json");
        try {

            Gson gson = new Gson();
            Figure newFigure = gson.fromJson(request.getReader(), Figure.class);

            if(newFigure.id() > 0) {
                new HttpError(403, "ResourceAlreadyExist - use POST method instead", response);
                return;
            }

            newFigure = (new FigureRepository()).save(newFigure);

            if(newFigure.id() == 0) {
                new HttpError(403, "ResourceAlreadyExist - use POST method instead", response);
                return;
            }

            response.getWriter().println(gson.toJson(newFigure));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("application/json");
        try {

            Gson gson = new Gson();
            Figure newFigure = gson.fromJson(request.getReader(), Figure.class);
            Figure figure = this.getFigure(request);
            if(figure == null) {
                new HttpError(404, "NotFound", response);
                return;
            }
            figure.name(newFigure.name());
            figure.uri(newFigure.uri());
            figure.description(newFigure.description());
            figure = (new FigureRepository()).save(figure);
            response.getWriter().println(gson.toJson(figure));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("application/json");
        try {

            Gson gson = new Gson();
            Figure figure = this.getFigure(request);
            if(figure == null) {
                new HttpError(404, "NotFound", response);
                return;
            }
            (new FigureRepository()).delete(figure);
            response.getWriter().println(gson.toJson(new int[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private Figure getFigure(HttpServletRequest request) {
        Long id = Long.parseLong(request.getPathInfo().substring(1));
        return (new FigureRepository()).findOne(id);
    }
}
