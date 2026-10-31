package com.univ.order.concurrency;

import com.github.javafaker.Faker;
import com.univ.order.processing.OrderProcessor;
import com.univ.order.storage.Clothing;
import com.univ.order.storage.Electronics;
import com.univ.order.storage.Product;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Faker faker = new Faker();
        List<Product> catalog = new ArrayList<>();

        // 1. Наповнення об'єктів за допомогою JavaFaker та патерну Builder
        for (int i = 0; i < 3; i++) {
            catalog.add(Electronics.builder()
                    .name(faker.commerce().productName())
                    .price(Double.parseDouble(faker.commerce().price().replace(",", ".")))
                    .warrantyMonths(faker.number().numberBetween(6, 24))
                    .build());

            catalog.add(Clothing.builder()
                    .name(faker.commerce().productName())
                    .price(Double.parseDouble(faker.commerce().price().replace(",", ".")))
                    .size(faker.options().option("S", "M", "L", "XL"))
                    .build());
        }

        // 2. Фільтрація замовлень за типом за допомогою лямбда-виразів
        List<Product> electronicsOrders = catalog.stream()
                .filter(p -> p instanceof Electronics)
                .toList();

        List<Product> clothingOrders = catalog.stream()
                .filter(p -> p instanceof Clothing)
                .toList();

        // 3. Ініціалізація узагальнених обробників (Generics)
        OrderProcessor<Product> electronicsProcessor = new OrderProcessor<>();
        OrderProcessor<Product> clothingProcessor = new OrderProcessor<>();

        // 4. Використання лямбда-виразів для створення потоків
        Runnable processElectronicsTask = () -> {
            System.out.println("--- Початок обробки електроніки в потоці " + Thread.currentThread().getName() + " ---");
            // Використання посилання на методи (Method Reference) для виклику методів обробки
            electronicsOrders.forEach(electronicsProcessor::processOrder);
        };

        Runnable processClothingTask = () -> {
            System.out.println("--- Початок обробки одягу в потоці " + Thread.currentThread().getName() + " ---");
            // Використання посилання на методи (Method Reference)
            clothingOrders.forEach(clothingProcessor::processOrder);
        };

        // Запуск багатопотокової системи
        Thread t1 = new Thread(processElectronicsTask, "Thread-Electronics");
        Thread t2 = new Thread(processClothingTask, "Thread-Clothing");

        t1.start();
        t2.start();

        // Синхронізація (чекаємо завершення потоків)
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("\n[СИСТЕМА] Усі замовлення успішно оброблено!");
    }
}