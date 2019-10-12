package ru.ifmo.se.asurkis.web.lab2.servlets;

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

public class NoInputServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        ResultsBean sessionResults = (ResultsBean) request.getSession().getAttribute("sessionResults");
        ResultsBean requestResults = (ResultsBean) request.getAttribute("requestResults");

        List<Result> results = requestResults.getResults();
        results.clear();
        results.addAll(sessionResults.getResults());

        ServletContext context = getServletContext();
        RequestDispatcher dispatcher = context.getRequestDispatcher("/no-input.jsp");
        dispatcher.forward(request, response);
    }
}
