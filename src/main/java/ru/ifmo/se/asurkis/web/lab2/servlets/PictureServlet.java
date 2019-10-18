package ru.ifmo.se.asurkis.web.lab2.servlets;

import ru.ifmo.se.asurkis.web.lab2.parameters.MultipleParameter;
import ru.ifmo.se.asurkis.web.lab2.parameters.RequestParameters;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class PictureServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        Object parametersObj = request.getAttribute("parameters");
        if (!(parametersObj instanceof RequestParameters)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        RequestParameters parameters = (RequestParameters) parametersObj;
        double[] radiuses = null;
        if (parameters.getR().isValid()) {
            Double[] rs = parameters.getR().getValues();
            radiuses = new double[rs.length];
            for (int i = 0; i < rs.length; i++) {
                radiuses[i] = rs[i];
            }
            Arrays.sort(radiuses);
            for (int i = 0; i + i < radiuses.length; i++) {
                double t1 = radiuses[i];
                double t2 = radiuses[radiuses.length - i - 1];
                radiuses[i] = t2;
                radiuses[radiuses.length - i - 1] = t1;
            }
        }

        request.setAttribute("radiuses", radiuses);
        getServletContext().getRequestDispatcher("/svg-inner.jsp").include(request, response);
    }
}
