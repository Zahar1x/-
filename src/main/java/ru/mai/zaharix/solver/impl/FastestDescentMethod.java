package ru.mai.zaharix.solver.impl;

import com.sun.security.jgss.GSSUtil;
import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import ru.mai.zaharix.normSolver.NormSolverInterface;
import ru.mai.zaharix.solver.SolverInterface;

public class FastestDescentMethod implements SolverInterface {

    @Override
    public RealMatrix methodRealisation(RealMatrix A, RealMatrix B, double eps, NormSolverInterface normSolver) {

        //Вектор начальных/предыдущих значений х
        RealMatrix matrixXprev = MatrixUtils.createRealMatrix(4, 1);
        //Вычисление начального значения х
        for(int i = 0; i < B.getColumnDimension(); i++) {
            double bi = B.getEntry(0, i);
            double aii = A.getEntry(i, i);
            matrixXprev.setEntry(i, 0,  -bi / aii);
        }
        //Вычисление вектора невязок
        RealMatrix matrixR = A.multiply(matrixXprev);
        matrixR = matrixR.subtract(B.transpose());
        //Вычисление произведения транспонированной матрицы А на вектор невязок
        RealMatrix matrixATransponcedR = A.transpose().multiply(matrixR);
        //Вычисление произведения матрицы А на предыдущее произведение
        RealMatrix matrixAMatrixAtransponcedR = A.multiply(matrixATransponcedR);

        //Вычисление коэффицента мю
        double M = findTheMu(matrixR, matrixAMatrixAtransponcedR);

        //Вычисление вектора х для первого шага
        RealMatrix matrixX = matrixXprev.subtract(matrixATransponcedR.scalarMultiply(M));

        int count = 1;

        while (getLengthOfVector(matrixR.getColumnVector(0)) > eps) {

            matrixXprev = matrixX;

            //Вычисление вектора невязок
            matrixR = A.multiply(matrixXprev);
            matrixR = matrixR.subtract(B.transpose());
            //Вычисление произведения транспонированной матрицы А на вектор невязок
            matrixATransponcedR = A.transpose().multiply(matrixR);
            //Вычисление произведения матрицы А на предыдущее произведение
            matrixAMatrixAtransponcedR = A.multiply(matrixATransponcedR);

            //Вычисление коэффицента мю
            M = findTheMu(matrixR, matrixAMatrixAtransponcedR);

            //Вычисление вектора х для первого шага
            matrixX = matrixXprev.subtract(matrixATransponcedR.scalarMultiply(M));
            count++;


            System.out.println("Вектор Х на шаге " + count + " = "
                    + matrixX.getColumn(0)[0] + " "
                    + matrixX.getColumn(0)[1] + " "
                    + matrixX.getColumn(0)[2] + " "
                    + matrixX.getColumn(0)[3] + " ");
            System.out.println(count);
        }


        return null;

    }

    private double getLengthOfVector(RealVector vector) {
        double length;

        double sum = 0;

        for(int i = 0; i < vector.getDimension(); i++) {
            sum += Math.pow(vector.getEntry(i), 2);
        }
        length = Math.sqrt(sum);

        return length;
    }


    private double findTheMu(RealMatrix r, RealMatrix AAtrR) {
        double mu = 0;
        double chis = 0;
        double znam = 1;
        AAtrR = AAtrR.transpose();
        for (int i = 0; i < r.getColumnDimension(); i++) {
            chis += r.getEntry(0, i) * AAtrR.getEntry(0, i);
            znam += Math.pow(AAtrR.getEntry(0, i), 2);
        }
        mu = chis / znam;
        return mu;
    }
}
