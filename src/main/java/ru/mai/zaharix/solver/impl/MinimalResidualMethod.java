package ru.mai.zaharix.solver.impl;


import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import ru.mai.zaharix.normSolver.NormSolverInterface;
import ru.mai.zaharix.solver.SolverInterface;

public class MinimalResidualMethod implements SolverInterface {

    @Override
    public RealMatrix methodRealisation(RealMatrix A, RealMatrix B, double eps, NormSolverInterface normSolver) {

        //init vector y
        RealMatrix matrixY;

        //init scalar coefficient
        double r;

        //init previous vector x
        RealMatrix matrixXprev = MatrixUtils.createRealMatrix(4, 1);

        //Вычисление вектора невязок
        matrixY = A.multiply(matrixXprev);
        matrixY = matrixY.subtract(B.transpose());

        //init vector x
        RealMatrix matrixX;

        int count = 0;
        do {
            matrixY = A.multiply(matrixXprev).subtract(B.transpose());
            r = scalarMult(matrixY, A.multiply(matrixY));
            matrixX = matrixXprev.subtract(matrixY.scalarMultiply(r));

            matrixXprev = matrixX;

            count++;
            System.out.println("Вектор Х на шаге " + count + " = "
                    + matrixX.getColumn(0)[0] + " "
                    + matrixX.getColumn(0)[1] + " "
                    + matrixX.getColumn(0)[2] + " "
                    + matrixX.getColumn(0)[3] + " ");
            System.out.println(count);
        } while (criterium(A.multiply(matrixX).subtract(B.transpose()), B) > eps);


        return null;
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

    private double scalarMult(RealMatrix y, RealMatrix Ay) {
        double chis = 0;
        double znam = 0;
        for(int i = 0; i < y.getRowDimension(); i++) {
            chis += y.getEntry(i, 0) * Ay.getEntry(i, 0);
            znam += Math.pow(Ay.getEntry(i, 0), 2);
        }
        return chis / znam;
    }
}
