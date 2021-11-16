package ru.mai.zaharix;


import org.apache.commons.math3.linear.MatrixUtils;
import org.apache.commons.math3.linear.RealMatrix;
import ru.mai.zaharix.normSolver.impl.FirstNormInterface;
import ru.mai.zaharix.normSolver.impl.SecondNormInterface;
import ru.mai.zaharix.normSolver.impl.ThirdNormInterface;
import ru.mai.zaharix.solver.impl.ConjugateGradientsMethod;
import ru.mai.zaharix.solver.impl.FastestDescentMethod;
import ru.mai.zaharix.solver.impl.MinimalResidualMethod;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        RealMatrix A = null;
        RealMatrix B = null;

        FirstNormInterface firstNormInterface = new FirstNormInterface();
        SecondNormInterface secondNormInterface = new SecondNormInterface();
        ThirdNormInterface thirdNormInterface = new ThirdNormInterface();

        String fileName = chooseEquitToSolve();
        double[][] fullMatrix = scanFile(fileName);

//        System.out.println(fileName);
//        printFullMatrix(fullMatrix);

        A = matrixAExtractor(fullMatrix);
        B = vectorBExtractor(fullMatrix);

        FastestDescentMethod fastestDescentMethod = new FastestDescentMethod();
        fastestDescentMethod.methodRealisation(A, B, 0.0001, firstNormInterface);

        ConjugateGradientsMethod conjugateGradientsMethod = new ConjugateGradientsMethod();
        conjugateGradientsMethod.methodRealisation(A, B, 0.0001, secondNormInterface);

        MinimalResidualMethod minimalResidualMethod = new MinimalResidualMethod();
        minimalResidualMethod.methodRealisation(A, B, 0.0001, thirdNormInterface);
    }

    /**
     * Этот метод выделяет из общей матрицы матрицу А
     * @param fullMatrix общая матрица
     * @return матрица А
     */
    private static RealMatrix matrixAExtractor(double[][] fullMatrix) {
        RealMatrix matrixA = MatrixUtils.createRealMatrix(4, 4);
        for (int i = 0; i < fullMatrix.length; i++) {
            for (int j = 0; j < fullMatrix[i].length - 1; j++) {
                matrixA.setEntry(i, j, fullMatrix[i][j]);
            }
        }
        return matrixA;
    }


    /**
     * этот метод выделает из общей матрицы вектор B
     * @param fullMatrix общая матрица
     * @return вектор В
     */
    private static RealMatrix vectorBExtractor(double[][] fullMatrix) {
        RealMatrix matrixB = null;
        double[] vector = new double[4];

        for (int i = 0; i < fullMatrix.length; i++) {
             int j = 4;
             vector[i] = fullMatrix[i][j];
        }

        matrixB = MatrixUtils.createRowRealMatrix(vector);

        return matrixB;
    }


    /**
     * Вспомогательный метод для проверки правильности сканирования
     * @param fullMatrix двумерный массив полной матрицы
     */
    private static void printFullMatrix(double[][] fullMatrix) {
        for (int i = 0; i < fullMatrix.length; i++) {
            for (int j = 0; j < fullMatrix[i].length; j++) {
                System.out.print("full matrix[" + i + "][" + j + "] = " + fullMatrix[i][j] + " ");
            }
            System.out.println("\n");
        }
    }

    /**
     * Этот метод считывает полную матрицу из выбранного файла
     * @param fileName имя файла, из которого читать данные
     * @return двумерный массив
     */
    private static double[][] scanFile(String fileName) {
        double[][] fullMatrix = new double[4][5];

        try {
            FileInputStream inputStream = new FileInputStream(fileName);
            Scanner scanner = new Scanner(inputStream);
            int i = 0;
            while (scanner.hasNextLine() && i < 4) {

                String line = scanner.nextLine();
                String[] nums = line.split(" ");

                for(int j = 0; j < nums.length; j++) {
                    int numInt;
                    double numDouble;
                    try {
                        numInt = Integer.parseInt(nums[j]);
                        fullMatrix[i][j] = numInt;
                    } catch (Exception e) {
                        numDouble = Double.parseDouble(nums[j]);
                        fullMatrix[i][j] = numDouble;
                    }
                }
            i++;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (Exception E) {
            E.printStackTrace();
        }

        return fullMatrix;
    }

    /**
     * Этот метод считывает какое уравнение выбрал пользователь для решения
     * @return целое число от 0 до 4
     */
    private static int scanNumOfEquit() {
        int num = 0;
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please, enter number of equitation to solve: 1, 2, 3, 4");
        if (scanner.hasNextInt()) {
            num = scanner.nextInt();
        } else {
            System.out.println("You entered wrong number! Try again!!");
        }
        return num;
    }

    /**
     * Этот метод на основе выбора пользователя возвращает файл с матрицей нужного уравнения
     * @return Имя файла
     */
    private static String chooseEquitToSolve() {
        int numOfEquit;
        numOfEquit = scanNumOfEquit();

        switch (numOfEquit) {
            case 0:
                chooseEquitToSolve();
                break;
            case 1:
                return "слау1.txt";
            case 2:
                return "слау2.txt";
            case 3:
                return "слау3.txt";
            case 4:
                return "слау4.txt";
        }
        return "";
    }


}
