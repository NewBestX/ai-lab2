package me.ai.repository;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class Repository {
    private double[][] matrix;
    private int n;
    private int start;
    private int end;
    private String fileName;

    public double[][] getMatrix() {
        return matrix;
    }

    public int getN() {
        return n;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }

    public void loadFile(String newFileName) throws IOException {
        this.fileName = newFileName;
        readMatrix();
    }

    public void saveSolution(List<Integer> fullPath, double fullPathValue, List<Integer> optimalPath, double optimalPathValue) {
        List<String> rez = new ArrayList<>();
        rez.add(n + "");

        StringBuilder sb1 = new StringBuilder();
        Iterator<Integer> it1 = fullPath.iterator();
        sb1.append(it1.next());
        while(it1.hasNext())
            sb1.append(",").append(it1.next());
        rez.add(sb1.toString());

        rez.add(fullPathValue + "");
        rez.add(optimalPath.size() + "");

        StringBuilder sb2 = new StringBuilder(optimalPath.get(0));
        Iterator<Integer> it2 = optimalPath.iterator();
        sb2.append(it2.next());
        while(it2.hasNext())
            sb2.append(",").append(it2.next());
        rez.add(sb2.toString());

        rez.add(optimalPathValue + "");

        writeFile(rez);
    }

    private void readMatrix() throws IOException {
        List<String> lines = readFile();

        if (lines == null)
            throw new IOException("Eroare la citirea fisierului");

        Iterator<String> it = lines.iterator();
        n = Integer.parseInt(it.next());
        matrix = new double[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            String[] parts = it.next().split(",");
            for (int j = 1; j <= n; j++)
                matrix[i][j] = Double.parseDouble(parts[j - 1]);
        }

        start = Integer.parseInt(it.next());
        end = Integer.parseInt(it.next());
    }

    private List<String> readFile() {
        Path p = Paths.get("./src/data/" + fileName + ".txt");
        if (!Files.exists(p))
            return null;

        List<String> lines = null;

        try {
            lines = Files.lines(p).collect(Collectors.toList());
        } catch (IOException ignored) {
        }

        return lines;
    }

    private void writeFile(List<String> lines) {
        Path p = Paths.get("./src/data/" + fileName + "_solution.txt");

        try (BufferedWriter bufferedWriter = Files
                .newBufferedWriter(p, StandardOpenOption.CREATE, StandardOpenOption.WRITE, StandardOpenOption.TRUNCATE_EXISTING)) {
            for (String line : lines) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
