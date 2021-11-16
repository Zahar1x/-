package ru.mai.zaharix.normSolver.impl;

import ru.mai.zaharix.normSolver.NormSolverInterface;

public class FirstNormImpl implements NormSolverInterface {
    @Override
    public double normSolver(double[] x, int l) {

        double max = 0;
        for (int i = 0; i < x.length; i++) {
            if(Math.abs(x[i]) > max) max = Math.abs(x[i]);
        }
        return max;
    }
}
