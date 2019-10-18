package ru.ifmo.se.asurkis.web.lab2.servlets;

import ru.ifmo.se.asurkis.web.lab2.parameters.RequestParameters;
import ru.ifmo.se.asurkis.web.lab2.results.Result;
import ru.ifmo.se.asurkis.web.lab2.results.ResultsBean;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AreaCheckServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        Object parametersObj = request.getAttribute("parameters");
        if (!(parametersObj instanceof RequestParameters)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        ResultsBean sessionResults = (ResultsBean) request.getSession().getAttribute("sessionResults");

        RequestParameters parameters = (RequestParameters) parametersObj;
        List<Result> results = sessionResults.getResults();

        Double[] xs = parameters.getX().getValues();
        Double[] ys = parameters.getY().getValues();
        Double[] rs = parameters.getR().getValues();

        for (double x: xs) {
            for (double y: ys) {
                for (double r: rs) {
                    results.add(new Result(x, y, r));
                }
            }
        }

        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/index.jsp");
        dispatcher.forward(request, response);
    }
}
