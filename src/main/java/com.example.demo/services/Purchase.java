package com.example.demo.services;

import com.example.demo.entities.Cart;
import com.example.demo.entities.CartItem;
import com.example.demo.entities.Customer;
import com.example.demo.entities.Excursion;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class Purchase {
    private Customer customer;
    private Set<CartItem> cartItems = new HashSet<>();
    private Cart cart;
    private Set<Excursion> excursions = new HashSet<>();
}