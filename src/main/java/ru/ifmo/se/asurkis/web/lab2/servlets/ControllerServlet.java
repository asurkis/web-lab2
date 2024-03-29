package ru.ifmo.se.asurkis.web.lab2.servlets;

import ru.ifmo.se.asurkis.web.lab2.Utils;
import ru.ifmo.se.asurkis.web.lab2.parameters.RequestParameters;
import ru.ifmo.se.asurkis.web.lab2.results.Result;
import ru.ifmo.se.asurkis.web.lab2.results.ResultsBean;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        Object sessionResults = request.getSession().getAttribute("sessionResults");
        if (!(sessionResults instanceof ResultsBean)) {
            sessionResults = new ResultsBean();
            request.getSession().setAttribute("sessionResults", sessionResults);
        }

        RequestParameters parameters = Utils.parseRequest(request);
        request.setAttribute("parameters", parameters);

        List<Result> results = ((ResultsBean) sessionResults).getResults();
        if (parameters.getToDelete().isValid()) {
            Integer[] toDelete = parameters.getToDelete().getValues();
            Arrays.sort(toDelete);
            for (int i = toDelete.length - 1; i >= 0; i--) {
                results.remove(toDelete[i].intValue());
            }
        }

        boolean shouldCheck = parameters.getX().isValid()
                && parameters.getY().isValid()
                && parameters.getR().isValid();

        getServletContext()
                .getRequestDispatcher(
                        shouldCheck
                                ? "/area-check"
                                : parameters.isPictureOnly()
                                ? "/picture"
                                : "/index.jsp")
                .forward(request, response);
    }
}
