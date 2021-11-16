package ru.mai.zaharix.solver.impl;

import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import ru.mai.zaharix.normSolver.NormSolverInterface;
import ru.mai.zaharix.solver.SolverInterface;

public class ConjugateGradientsMethod implements SolverInterface {

    @Override
    public RealMatrix methodRealisation(RealMatrix A, RealMatrix B, double eps, NormSolverInterface normSolverInterface) {

        //Вектор начальных/предыдущих значений х
        RealMatrix matrixXprev = MatrixUtils.createRealMatrix(4, 1);
        //init vector r - residuals vector
        RealMatrix matrixR = null;
        //init previous residuals vector
        RealMatrix matrixRprev =  A.multiply(matrixXprev);
        matrixRprev = matrixRprev.subtract(B.transpose());
        //init previous vector p - vector of direction
        RealMatrix matrixPprev = matrixRprev;
        //init vector X
        RealMatrix X = null;
        //init vector p - vector of direction
        RealMatrix matrixP = null;


        //init scalar step a
        double a;
        double b;

        int count = 0;
        while (criterium(matrixRprev, B) > eps) {

            a = scalarStep(matrixRprev, matrixPprev, A.multiply(matrixPprev));
            X = matrixXprev.add(matrixPprev.scalarMultiply(a));
            matrixR = matrixRprev.subtract(A.multiply(matrixPprev).scalarMultiply(a));
            b = scalarStepBeta(matrixR, matrixRprev);
            matrixP = matrixR.add(matrixPprev.scalarMultiply(b));


            matrixPprev = matrixP;
            matrixXprev = X;
            matrixRprev = matrixR;
            count++;

            System.out.println("Вектор Х на шаге " + count + " = "
                    + X.getColumn(0)[0] + " "
                    + X.getColumn(0)[1] + " "
                    + X.getColumn(0)[2] + " "
                    + X.getColumn(0)[3] + " ");
            System.out.println(count);
        }
        return null;
    }

    /**
     * Этот метод вычисляет скалярный шаг альфа
     * @param r вектор невязок
     * @param p вектор направлений
     * @param Ap произведение матрицы А на вектор направлений
     * @return коэффицент
     */
    private double scalarStep(RealMatrix r, RealMatrix p, RealMatrix Ap) {
        double s;
        double chis = 0;
        double znam = 0;


        for(int i = 0; i < r.getRowDimension(); i++) {
            chis += r.getEntry(i, 0) * r.getEntry(i, 0);
            znam += p.getEntry(i, 0) * Ap.getEntry(i, 0);
        }

        s = chis / znam;
        return s;
    }

    /**
     * Этот метод вычисляет шаг бета
     * @param r вектор невязок
     * @param rPrev вектор невязок на предыдущем шаге
     * @return коэффицент
     */
    private double scalarStepBeta(RealMatrix r, RealMatrix rPrev) {
        double chis = 0;
        double znam = 0;
        for(int i = 0; i < r.getRowDimension(); i++) {
            chis += r.getEntry(i, 0) * r.getEntry(i, 0);
            znam += rPrev.getEntry(i, 0) * rPrev.getEntry(i, 0);
        }
        return chis / znam;
    }

    private double criterium(RealMatrix r, RealMatrix b) {
        double chis = 0;
        double znam = 0;
        for(int i = 0; i < r.getRowDimension(); i++) {
            chis += Math.pow(r.getEntry(i, 0), 2);
            znam += Math.pow(b.getEntry(0, i), 2);
        }

        return chis/znam;

    }

}
