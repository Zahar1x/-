package ru.mai.zaharix.normSolver.impl;

import ru.mai.zaharix.normSolver.NormSolverInterface;

public class ThirdNormInterface implements NormSolverInterface {
    @Override
    public double normSolver(double[] x, int l) {
        double sum = 0;
        for(int i = 0; i < x.length; i++) {
            sum += Math.pow(x[i], 2 * l);
        }
        sum = Math.pow(sum, 1 / (2 * l));
        return sum;
    }
}

