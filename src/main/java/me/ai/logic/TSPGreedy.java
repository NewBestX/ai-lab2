package me.ai.logic;

import java.util.ArrayList;
import java.util.List;

public class TSPGreedy {
    /**
     * Gaseste drumul de cost minim dintre doua noduri ale grafului. Daca se doreste parcurgerea tuturor nodurilor, parametrii start si end trebuie sa fie
     * egali.
     *
     * @param mat   graful dat ca si matrice de adiacenta
     * @param n     numarul de noduri din graf
     * @param start nodul de inceput al drumului
     * @param end   ultimul nod al drumului
     * @return drumul de cost minim determinat si costul total al acestuia
     * @see Solution
     */
    public static Solution generatePath(double[][] mat, int n, int start, int end) {
        int[] viz = new int[n + 1]; // Retine nodurile care au fost vizitate

        List<Integer> path = new ArrayList<>(); // Retine nodurile in ordinea in care au fost parcurse
        double value = 0; // Retine valoarea totala a drumului

        int current = start;
        do {
            path.add(current);
            viz[current] = 1;

            int next = 0;
            double min = Double.MAX_VALUE;

            for (int j = 1; j <= n; j++) {
                if (mat[current][j] < min && viz[j] == 0) { // Se cauta muchia cu valoare minima care duce spre un nod nevizitat
                    min = mat[current][j];
                    next = j;
                }
            }

            if (next == 0) { // S-au parcurs toate nodurile din graf fara sa se ajunga la end, inseamna ca start == end
                value += mat[current][end];
                break;
            }
            value += min;
            current = next;
        } while (current != end);

        if (start != end)
            path.add(end);

        return new Solution(path, value);
    }

    /**
     * Obiect care salveaza un drum de cost minim si costul total al acestuia
     */
    public static class Solution {
        private List<Integer> path;
        private double value;

        public Solution(List<Integer> path, double value) {
            this.path = path;
            this.value = value;
        }

        public List<Integer> getPath() {
            return path;
        }

        public double getValue() {
            return value;
        }
    }
}


