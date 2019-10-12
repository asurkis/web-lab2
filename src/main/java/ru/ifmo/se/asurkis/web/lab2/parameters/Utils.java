package ru.ifmo.se.asurkis.web.lab2.parameters;

import ru.ifmo.se.asurkis.web.lab2.parameters.MultipleParameter.State;
import ru.ifmo.se.asurkis.web.lab2.results.ResultsBean;

import javax.servlet.http.HttpServletRequest;

public class Utils {
    private static Double[] parseDoubleParameter(String name, HttpServletRequest request) {
        String[] strings = request.getParameterValues(name);
        if (strings == null) {
            return null;
        }
        Double[] values = new Double[strings.length];
        for (int i = 0; i < values.length; i++) {
            values[i] = Double.parseDouble(strings[i]);
        }
        return values;
    }

    private static MultipleParameter<Double> validateDoubleParameter(String name, HttpServletRequest request, double min, double max) {
        MultipleParameter<Double> result = new MultipleParameter<>();
        try {
            Double[] values = parseDoubleParameter(name, request);
            if (values != null) {
                boolean isValid = true;
                for (int i = 0; isValid && i < values.length; i++) {
                    isValid = min <= values[i] && values[i] <= max;
                }
                if (isValid) {
                    result.setValues(values);
                } else {
                    result.setState(State.INVALID);
                }
            }
        } catch(NumberFormatException e) {
            result.setState(State.INVALID);
        }
        return result;
    }

    private static Integer[] parseIntegerParameter(String name, HttpServletRequest request) {
        String[] strings = request.getParameterValues(name);
        if (strings == null) {
            return null;
        }
        Integer[] values = new Integer[strings.length];
        for (int i = 0; i < values.length; i++) {
            values[i] = Integer.parseInt(strings[i]);
        }
        return values;
    }

    private static MultipleParameter<Integer> validateIntegerParameter(String name, HttpServletRequest request, int min, int max) {
        MultipleParameter<Integer> result = new MultipleParameter<>();
        try {
            Integer[] values = parseIntegerParameter(name, request);
            if (values != null) {
                boolean isValid = true;
                for (int i = 0; isValid && i < values.length; i++) {
                    isValid = min <= values[i] && values[i] <= max;
                }
                if (isValid) {
                    result.setValues(values);
                } else {
                    result.setState(State.INVALID);
                }
            }
        } catch(NumberFormatException e) {
            result.setState(State.INVALID);
        }
        return result;
    }

    public static RequestParameters parseRequest(HttpServletRequest request) {
        RequestParameters args = new RequestParameters();
        ResultsBean resultsBean = (ResultsBean) request.getSession().getAttribute("sessionResults");

        args.setX(validateDoubleParameter("x", request, -4, 4));
        args.setY(validateDoubleParameter("y", request, -3, 5));
        args.setR(validateDoubleParameter("r", request, 1, 5));
        args.setToDelete(validateIntegerParameter("d", request, 0, resultsBean.getResults().size() - 1));

        return args;
    }
}
