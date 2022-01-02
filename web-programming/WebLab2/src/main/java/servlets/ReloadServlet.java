package servlets;

import entities.Point;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Model;

public class ReloadServlet extends HttpServlet {

  @Override
  protected void doGet(HttpServletRequest req, HttpServletResponse resp)
      throws ServletException, IOException {
    PrintWriter output = resp.getWriter();
    ServletContext context = this.getServletContext();
    if (context.getAttribute("points") == null) {
      context.setAttribute("points", new ArrayList<>());
    }
    Model model = Model.getInstance();
    model.setPoints((List<Point>) context.getAttribute("points"));
    if (model.getPoints().isEmpty()) {
      output.write("[]");
    } else {
      output.write(model.getPoints().get(0).toJsonArray());
    }
  }
}
