package ru.mai.zaharix.scanner;

public interface ScannerInterface {
    /**
     * Этот метод считывает полную матрицу из выбранного файла
     * @param fileName имя файла, из которого читать данные
     * @return двумерный массив
     */
    double[][] scanFile(String fileName);

    /**
     * Этот метод считывает целое число из консоли для выбора чего-либо
     * @param txtToWrite условие для выбора
     * @return целое число
     */
    int scanNumFromConsole(String txtToWrite);
}
