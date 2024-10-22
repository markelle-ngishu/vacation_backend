package com.example.demo.bootstrap;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.dao.DivisionRepository;
import com.example.demo.entities.Customer;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@AllArgsConstructor
@Component
public class BootStrapData {

    private CustomerRepository customerRepository;
    private DivisionRepository divisionRepository;

    @PostConstruct
    public void loadInitialData() {

        if(customerRepository.count() == 1) {


            Customer customer1 = new Customer();
            customer1.setFirstName("Peppa");
            customer1.setLastName("Pig");
            customer1.setAddress("111 Street");
            customer1.setPostal_code("1234");
            customer1.setPhone("(555)111-1111");
            customer1.setDivision(divisionRepository.findAll().get(5));
            customer1.setCreate_date(new Date());
            customer1.setLast_update(new Date());

            Customer customer2 = new Customer();
            customer2.setFirstName("SpongeBob");
            customer2.setLastName("SquarePants");
            customer2.setAddress("222 Drive");
            customer2.setPostal_code("12345");
            customer2.setPhone("(555)222-2222");
            customer2.setDivision(divisionRepository.findAll().get(7));
            customer2.setCreate_date(new Date());
            customer2.setLast_update(new Date());

            Customer customer3 = new Customer();
            customer3.setFirstName("Dora");
            customer3.setLastName("Marquez");
            customer3.setAddress("333 Court");
            customer3.setPostal_code("12345");
            customer3.setPhone("(555)333-3333");
            customer3.setDivision(divisionRepository.findAll().get(5));
            customer3.setCreate_date(new Date());
            customer3.setLast_update(new Date());

            Customer customer4 = new Customer();
            customer4.setFirstName("Bluey");
            customer4.setLastName("Heeler");
            customer4.setAddress("444 Lane");
            customer4.setPostal_code("12345");
            customer4.setPhone("(555)444-4444");
            customer4.setDivision(divisionRepository.findAll().get(2));
            customer4.setCreate_date(new Date());
            customer4.setLast_update(new Date());

            Customer customer5 = new Customer();
            customer5.setFirstName("Mickey");
            customer5.setLastName("Mouse");
            customer5.setAddress("555 Road");
            customer5.setPostal_code("12345");
            customer5.setPhone("(555)555-5555");
            customer5.setDivision(divisionRepository.findAll().get(4));
            customer5.setCreate_date(new Date());
            customer5.setLast_update(new Date());

            customerRepository.save(customer1);
            customerRepository.save(customer2);
            customerRepository.save(customer3);
            customerRepository.save(customer4);
            customerRepository.save(customer5);

            System.out.println("Sample customers added");
        } else {
            System.out.println("Sample customers already present");
        }
        for (Customer customer : customerRepository.findAll()) {
            System.out.println(customer.getFirstName() + " " + customer.getLastName());
        }
    }
}