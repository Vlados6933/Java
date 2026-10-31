package com.univ.order.storage;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Clothing extends Product {
    private String size;

    @Builder
    public Clothing(String name, double price, String size) {
        super(name, price);
        this.size = size;
    }

    @Override
    public String toString() {
        return "Одяг: " + getName() + " | Ціна: " + getPrice() + " | Розмір: " + size;
    }
}