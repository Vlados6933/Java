package com.univ.order.storage;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Electronics extends Product {
    private int warrantyMonths;

    @Builder
    public Electronics(String name, double price, int warrantyMonths) {
        super(name, price);
        this.warrantyMonths = warrantyMonths;
    }

    @Override
    public String toString() {
        return "Електроніка: " + getName() + " | Ціна: " + getPrice() + " | Гарантія: " + warrantyMonths + " міс.";
    }
}