package ru.ifmo.se.asurkis.web.lab2.servlets;

import ru.ifmo.se.asurkis.web.lab2.parameters.MultipleParameter;
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
    private boolean checkPoint(double x, double y, double r) {
        if (x > 0) {
            if (y >= 0) {
                return x * x + y * y <= r * r;
            } else {
                return 2 * x - y <= r;
            }
        } else if (x < 0) {
            return -r <= x && -r <= y && y <= 0;
        } else {
            return -r <= y && y <= r;
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ResultsBean sessionResults = (ResultsBean) request.getSession().getAttribute("sessionResults");
        ResultsBean requestResults = (ResultsBean) request.getAttribute("requestResults");

        Object parametersObj = request.getAttribute("parameters");
        if (!(parametersObj instanceof RequestParameters)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        RequestParameters parameters = (RequestParameters) parametersObj;
        List<Result> results = requestResults.getResults();
        results.clear();

        Double[] xs = parameters.getX().getValues();
        Double[] ys = parameters.getY().getValues();
        Double[] rs = parameters.getR().getValues();

        for (double x: xs) {
            for (double y: ys) {
                for (double r: rs) {
                    results.add(new Result(x, y, r, checkPoint(x, y, r)));
                }
            }
        }

        sessionResults.getResults().addAll(results);

        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/area-check.jsp");
        dispatcher.forward(request, response);
    }
}
