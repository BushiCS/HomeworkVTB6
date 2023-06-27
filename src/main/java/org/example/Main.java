package org.example;

import java.util.Arrays;

public class Main {

    static final int SIZE = 10;
    static final int HALF_SIZE = SIZE / 2;

    public static void main(String[] args) {
        createArr();
        createHardArr();
    }

    static void createArr() {
        long mathArr1;
        long mathArr2;
        float[] arr = new float[SIZE];
        Arrays.fill(arr, 1);
        mathArr1 = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5f) * Math.cos(0.2f + i / 5f) *
                    Math.cos(0.4f + i / 2f));
        }
        mathArr2 = System.currentTimeMillis();
        System.out.println("Время подсчёта простого массива: " + (mathArr2 - mathArr1));
    }

    static void createHardArr() {
        float[] arr = new float[SIZE];
        float[] a1 = new float[HALF_SIZE];
        float[] a2 = new float[HALF_SIZE];
        Arrays.fill(arr, 1);

        long split1 = System.currentTimeMillis();
        System.arraycopy(arr, 0, a1, 0, HALF_SIZE);
        System.arraycopy(arr, HALF_SIZE, a2, 0, HALF_SIZE);
        long split2 = System.currentTimeMillis();
        System.out.println("Время подсчета разбивки массива на 2 части: " + (split2 - split1));
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < a1.length; i++) {
                a1[i] = (float) (a1[i] * Math.sin(0.2f + i / 5f) * Math.cos(0.2f + i / 5f) * Math.cos(0.4f + i / 2f));

            }
            long mathFirstThread = System.currentTimeMillis();
            System.out.println("Время подсчёта первого массива в 1 потоке: " + (mathFirstThread - split2));
            System.out.println(Arrays.toString(a1));
        });
        Thread t2 = new Thread(() -> {
            // сделали смещение, ибо 2 массив должен будет лечь во 2 часть списка и подсчет ведется с учетом смещения
            for (int i = 0, j = HALF_SIZE; i < a2.length; i++, j++) {
                a2[i] = (float) (a2[i] * Math.sin(0.2f + j / 5f) * Math.cos(0.2f + j / 5f) * Math.cos(0.4f + j / 2f));
            }
            long mathSecondThread = System.currentTimeMillis();
            System.out.println("время подсчёта второго массива во 2 потоке: " + (mathSecondThread - split2));
            System.out.println(Arrays.toString(a2));
        });
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long mergeArrays1 = System.currentTimeMillis();
        System.arraycopy(a1, 0, arr, 0, HALF_SIZE);
        System.arraycopy(a2, 0, arr, HALF_SIZE, HALF_SIZE);
        long mergeArrays2 = System.currentTimeMillis();
        System.out.println("Время склейки: " + (mergeArrays2 - mergeArrays1));
        System.out.println("Общее затраченное время: " + (mergeArrays1 - split1)); // время работы 2-х параллельных потоков
        System.out.println(Arrays.toString(arr));

    }
}