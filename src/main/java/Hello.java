import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

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
        response.setContentType("text/html");

        // Actual logic goes here.
        PrintWriter out = response.getWriter();
        out.println("<h1>" + this.message + "</h1>");
    }

    public void destroy() {
        // do nothing.
    }
}