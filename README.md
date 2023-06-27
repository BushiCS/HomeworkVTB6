# HomeworkVTB6
##### 1. Необходимо написать два метода, которые делают следующее:
1) Создают одномерный длинный массив, например:
```java
static final int SIZE = 10 000 000;
static final int HALF = size / 2;
float[] arr = new float[size];
```

3) Заполняют этот массив единицами.
4) Засекают время выполнения:
    long a = System.currentTimeMillis();
   
6) Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
```java   
arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) *
Math.cos(0.4f + i / 2));
```
8) Проверяется время окончания метода System.currentTimeMillis();
   
10) В консоль выводится время работы:
```java
System.out.println(System.currentTimeMillis() - a);
```
Отличие первого метода от второго:
● Первый просто бежит по массиву и вычисляет значения.
● Второй разбивает массив на два массива, в двух потоках высчитывает новые значения и
потом склеивает эти массивы обратно в один.
Пример деления одного массива на два:
```java
System.arraycopy(arr, 0, a1, 0, h);
System.arraycopy(arr, h, a2, 0, h).
```
