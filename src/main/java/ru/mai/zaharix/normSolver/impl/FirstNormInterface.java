package ru.mai.zaharix.normSolver.impl;

import ru.mai.zaharix.normSolver.NormSolverInterface;

public class FirstNormInterface implements NormSolverInterface {
    @Override
    public double normSolver(double[] x, int l) {

        double max = 0;
        for (int i = 0; i < x.length; i++) {
            if(x[i] > max) max = x[i];

        }
        return max;
    }
}
