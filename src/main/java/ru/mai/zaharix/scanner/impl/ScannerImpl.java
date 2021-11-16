package ru.mai.zaharix.scanner.impl;

import ru.mai.zaharix.scanner.ScannerInterface;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ScannerImpl implements ScannerInterface {
    /**
     * Этот метод считывает полную матрицу из выбранного файла
     * @param fileName имя файла, из которого читать данные
     * @return двумерный массив
     */
    public double[][] scanFile(String fileName) {
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

    @Override
    public int scanNumFromConsole(String txtToWrite) {
        int num = 0;
        Scanner scanner = new Scanner(System.in);
        System.out.println(txtToWrite);
        try {
            num = scanner.nextInt();
        } catch (Exception e) {
            System.out.println("Missclick! Try again to write integer!");
            scanNumFromConsole(txtToWrite);
        }
        return num;
    }

}
