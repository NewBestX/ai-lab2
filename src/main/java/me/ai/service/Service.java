package me.ai.service;

import me.ai.logic.TSPGreedy;
import me.ai.repository.Repository;

import java.io.IOException;

public class Service {
    private Repository repo;

    public Service(Repository repo) {
        this.repo = repo;
    }

    public void generateSolution(String fileName) throws IOException {
        repo.loadFile(fileName);

        TSPGreedy.Solution solutionFullPath = TSPGreedy.generatePath(repo.getMatrix(), repo.getN(), 1, 1);
        TSPGreedy.Solution solutionOptimalPath = TSPGreedy.generatePath(repo.getMatrix(), repo.getN(), repo.getStart(), repo.getEnd());

        repo.saveSolution(solutionFullPath.getPath(), solutionFullPath.getValue(), solutionOptimalPath.getPath(), solutionOptimalPath.getValue());
    }
}
