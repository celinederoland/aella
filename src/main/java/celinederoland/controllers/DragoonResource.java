package celinederoland.controllers;

import celinederoland.models.Dragoon;
import celinederoland.repositories.DragoonRepository;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.http.HTTPException;
import java.io.IOException;

public class DragoonResource extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");

        Dragoon dragoon = getDragoon(request);
        Gson gson = new Gson();
        String json = gson.toJson(dragoon);
        response.getWriter().println(json);
    }

    public void doPut(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("application/json");
        try {

            Gson gson = new Gson();
            Dragoon newDragoon = gson.fromJson(request.getReader(), Dragoon.class);

            if(newDragoon.id() > 0) {
                throw new HTTPException(403);
            }
            newDragoon = (new DragoonRepository()).save(newDragoon);
            response.getWriter().println(gson.toJson(newDragoon));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("application/json");
        try {

            Gson gson = new Gson();
            Dragoon newDragoon = gson.fromJson(request.getReader(), Dragoon.class);
            Dragoon dragoon = this.getDragoon(request);
            dragoon.name(newDragoon.name());
            dragoon = (new DragoonRepository()).save(dragoon);
            response.getWriter().println(gson.toJson(dragoon));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doDelete(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("application/json");
        try {

            Gson gson = new Gson();
            Dragoon dragoon = this.getDragoon(request);
            (new DragoonRepository()).delete(dragoon);
            response.getWriter().println(gson.toJson(new int[0]));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private Dragoon getDragoon(HttpServletRequest request) {
        int id = Integer.parseInt(request.getPathInfo().substring(1));
        return (new DragoonRepository()).findOne(id);
    }
}
