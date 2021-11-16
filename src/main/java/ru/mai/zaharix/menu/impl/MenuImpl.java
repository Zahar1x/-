package ru.mai.zaharix.menu.impl;

import org.apache.commons.math3.linear.RealMatrix;
import ru.mai.zaharix.menu.MenuInterface;
import ru.mai.zaharix.normSolver.NormSolverInterface;
import ru.mai.zaharix.scanner.impl.ScannerImpl;
import ru.mai.zaharix.solver.SolverInterface;

import java.util.List;

/**
 *
 */
public class MenuImpl implements MenuInterface {

    ScannerImpl scanner = new ScannerImpl();

    private List<SolverInterface> list;
    private List<NormSolverInterface> listOfNorms;

    private SolverInterface solverMethod;
    private NormSolverInterface norm;
    private double eps;
    private String nameOfEquit;

    MenuImpl(List<SolverInterface> list, List<NormSolverInterface> listOfNorms) {
        this.list = list;
        this.listOfNorms = listOfNorms;
        this.solverMethod = chooseMethod();
        this.norm = chooseNorm();
        this.eps = chooseEpsilon();
        this.nameOfEquit = chooseEquitToSolve();
    }

    @Override
    public double chooseEpsilon() {
        double[] eps = {1e-2, 1e-3, 1e-4, 1e-5, 1e-10, 1e-12, 1e-15};
        int numOfEps = scanner.scanNumFromConsole("Please enter the epsilon: " +
                "\n1 - 0.01" +
                "\n2 - 0.001" +
                "\n3 - 0.0001" +
                "\n4 - 0.00001" +
                "\n5 - 10^-10" +
                "\n6 - 10^-12" +
                "\n7 - 10^-15");

        return eps[numOfEps - 1];

    }

    @Override
    public SolverInterface chooseMethod() {
        int numOfMethod = scanner.scanNumFromConsole("Please enter the number of method, you want to use: " +
                "1 - fastest descents method" +
                "2 - minimal residuals method" +
                "3 - conjugate gradients method");
        return list.get(numOfMethod - 1);
    }

    @Override
    public NormSolverInterface chooseNorm() {
        int numOfNorm = scanner.scanNumFromConsole("Please enter the number of norm, you want to use: " +
                "\n1 - ||X||∞ " +
                "\n2 - ||X||1 " +
                "\n3 - ||X||2l ");
        return listOfNorms.get(numOfNorm - 1);
    }
    /**
     * Этот метод на основе выбора пользователя возвращает файл с матрицей нужного уравнения
     * @return Имя файла
     */
    public String chooseEquitToSolve() {
        int numOfEquit = scanner.scanNumFromConsole("Please enter the number of equitation, you want to solve: " +
                "\n1" +
                "\n2" +
                "\n3" +
                "\n4");
        String[] files = {"слау1.txt", "слау2.txt", "слау3.txt", "слау4.txt"};
        return files[numOfEquit - 1];
    }

}
