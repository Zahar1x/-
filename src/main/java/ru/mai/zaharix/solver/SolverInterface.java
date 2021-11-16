package ru.mai.zaharix.solver;


import org.apache.commons.math3.linear.RealMatrix;
import org.apache.commons.math3.linear.RealVector;
import ru.mai.zaharix.normSolver.NormSolverInterface;

public interface SolverInterface {
    public RealMatrix methodRealisation(RealMatrix A, RealMatrix B, double eps, NormSolverInterface normSolver);
}
