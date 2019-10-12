package ru.ifmo.se.asurkis.web.lab2.results;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ResultsBean implements Serializable {
    private List<Result> results = new CopyOnWriteArrayList<>();

    public ResultsBean() {}

    public List<Result> getResults() {
        return results;
    }

    public void addResult(Result result) {
        results.add(result);
    }

    public void removeResult(int id) {
        results.remove(id);
    }
}
