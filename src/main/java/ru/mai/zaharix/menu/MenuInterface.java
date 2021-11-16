package ru.mai.zaharix.menu;

import ru.mai.zaharix.normSolver.NormSolverInterface;
import ru.mai.zaharix.solver.SolverInterface;


/**
 * Этот интерфейс описывает методы класса меню
 */
public interface MenuInterface {

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

    /**
     * Этот метод на основе выбора пользователя возвращает файл с матрицей нужного уравнения
     * @return Имя файла
     */
    String chooseEquitToSolve();
}
