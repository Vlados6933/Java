package com.univ.order.storage;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class Product {
    private String name;
    private double price;
}