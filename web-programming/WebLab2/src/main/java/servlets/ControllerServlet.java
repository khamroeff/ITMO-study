package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("x")!=null && req.getParameter("y")!=null && req.getParameter("r")!=null){
            getServletContext().getRequestDispatcher("/check").forward(req,resp);
        } else if (req.getParameter("reload")!= null) {
            getServletContext().getRequestDispatcher("/reload").forward(req, resp);
        } else{
            getServletContext().getRequestDispatcher("/index.jsp").forward(req,resp);
        }
    }
}
