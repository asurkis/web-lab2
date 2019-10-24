package ru.ifmo.se.asurkis.web.lab2;

import ru.ifmo.se.asurkis.web.lab2.parameters.MultipleParameter;
import ru.ifmo.se.asurkis.web.lab2.parameters.MultipleParameter.State;
import ru.ifmo.se.asurkis.web.lab2.parameters.RequestParameters;
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
        args.setPictureOnly(request.getParameter("p") != null);

        return args;
    }

    public static int[] colorByMatches(int matches, double[] radiuses) {
        if (radiuses != null && radiuses.length != 0) {
            double a = (double) matches / radiuses.length;
            double r = 255 * Math.sqrt(1 - a);
            double g = 255 * Math.sqrt(a);
            double b = 0;
            return new int[] {
                    (int) r,
                    (int) g,
                    (int) b,
            };
        } else {
            return new int[] { 128, 128, 128 };
        }
    }

    public static String styleByMatches(int matches, double[] radiuses) {
        int[] color = colorByMatches(matches, radiuses);
        return String.format("fill: #%02x%02x%02x; stroke: #000", color[0], color[1], color[2]);
    }
}
