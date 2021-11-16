package ru.mai.zaharix.menu;

import org.apache.commons.math3.linear.RealMatrix;
import ru.mai.zaharix.normSolver.NormSolverInterface;
import ru.mai.zaharix.solver.SolverInterface;

/**
 * Этот интерфейс описывает методы класса меню
 */
public interface MenuInterface {

    /**
     *
     * @param A
     * @param B
     */
    void menu(RealMatrix A, RealMatrix B);

    /**
     *
     * @return
     */
    double chooseEpsilon();

    /**
     *
     * @return
     */
    SolverInterface chooseMethod();

    /**
     *
     * @return
     */
    NormSolverInterface chooseNorm();

}
