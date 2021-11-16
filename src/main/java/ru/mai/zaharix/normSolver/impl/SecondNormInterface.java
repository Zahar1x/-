package ru.mai.zaharix.normSolver.impl;

import ru.mai.zaharix.normSolver.NormSolverInterface;

public class SecondNormInterface implements NormSolverInterface {
    @Override
    public double normSolver(double[] x, int l) {
        double sum = 0;
        for(int i = 0; i < x.length; i++) {
            sum += Math.abs(x[i]);
        }
        return sum;
    }
}
