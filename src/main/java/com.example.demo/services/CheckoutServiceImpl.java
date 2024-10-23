package com.example.demo.services;

import com.example.demo.dao.CartItemRepository;
import com.example.demo.dao.CartRepository;
import com.example.demo.dao.CustomerRepository;
import com.example.demo.dao.ExcursionRepository;
import com.example.demo.entities.Cart;
import com.example.demo.entities.CartItem;
import com.example.demo.entities.Customer;
import com.example.demo.entities.StatusType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.UUID;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private CustomerRepository customerRepository;
    private CartRepository cartRepository;
    private ExcursionRepository excursionRepository;
    private CartItemRepository cartItemRepository;

    public CheckoutServiceImpl(CustomerRepository customerRepository, CartRepository cartRepository,
                               ExcursionRepository excursionRepository, CartItemRepository cartItemRepository) {
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;
        this.excursionRepository = excursionRepository;
        this.cartItemRepository = cartItemRepository;
    }
    @Override
    @Transactional
    public PurchaseResponse placeOrder(Purchase purchase) {

        try {
            // Retrieve the cart information
            Cart cart = purchase.getCart();

            // Generate tracking number
            String orderTrackingNumber = generateOrderTrackingNumber();
            cart.setOrderTrackingNumber(orderTrackingNumber);

            // add items to cart
            Set<CartItem> cartItems = purchase.getCartItems();
            cartItems.forEach(item -> item.setCart(cart));

            // Set the cart items and customers to the cart
            cartItems.forEach(item -> cart.add(item));

            Customer customer = purchase.getCustomer();
            cart.setCustomer(customer);

            // Populate customer with cart
            //customer.add(cart);

            // Save to database
            customerRepository.save(customer);
            cartRepository.save(cart);

            // Set the status type to ordered
            cart.setStatus(StatusType.ordered);

            // Handle null customer or empty cart items
            if (customer == null || cartItems.isEmpty()) {
                throw new IllegalArgumentException("Customer cannot be null and cart items cannot be empty.");
            }

            // Return a response
            return new PurchaseResponse(orderTrackingNumber);

        } catch (Exception e) {
            // Handle any exceptions and provide an error response
            return new PurchaseResponse(e.getMessage());
        }
    }

    // Private function to create a random order tracking number
    private String generateOrderTrackingNumber() {
        return UUID.randomUUID().toString();
    }
}