package com.univ.order.processing;

import com.univ.order.storage.Product;

public class OrderProcessor<T extends Product> {

    public void processOrder(T product) {
        // Обробка виключень специфічних для узагальнень
        if (product == null) {
            throw new InvalidOrderException("Помилка: Спроба обробити порожнє замовлення (null)!");
        }

        if (product.getPrice() <= 0) {
            throw new InvalidOrderException("Помилка: Некоректна ціна для товару " + product.getName());
        }

        // Імітація обробки
        System.out.println("[" + Thread.currentThread().getName() + "] Обробляється замовлення: " + product.toString());
        try {
            Thread.sleep(500); // Імітація затримки (з лекції про потоки)
        } catch (InterruptedException e) {
            System.out.println("Потік перервано");
        }
    }
}