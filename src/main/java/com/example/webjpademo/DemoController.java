package com.example.webjpademo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DemoController {

    private final CustomerRepository repository;

    private final RemarksRepository remarksRepository;

    public DemoController(CustomerRepository repository, RemarksRepository remarksRepository) {
        this.repository = repository;
        this.remarksRepository = remarksRepository;
    }

    @GetMapping("/customer/{id}")
    public Customer getCustomer(@PathVariable("id") long id) {
        return repository.findById(id).get();
    }

    @GetMapping("/customers")
    public List<Customer> getCustomers() {
        List<Customer> list = new ArrayList<>();
        for (Customer customer : repository.findAll()) {
            list.add(customer);
        }
        return list;
    }

    @PostMapping("/customers")
    public Customer createCustomer(@RequestBody Person person) {
        return repository.save(new Customer(person.getFirstName(), person.getLastName()));
    }

}
