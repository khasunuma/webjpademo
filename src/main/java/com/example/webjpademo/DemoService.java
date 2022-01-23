package com.example.webjpademo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@Service
public class DemoService {

    private static final Logger log = LoggerFactory.getLogger(DemoService.class);

    private final CustomerRepository repository;

    private final RemarksRepository remarksRepository;

    public DemoService(CustomerRepository repository, RemarksRepository remarksRepository) {
        this.repository = repository;
        this.remarksRepository = remarksRepository;
    }

    @Bean
    public CommandLineRunner getCustomer() {
        return (args) -> {
            Customer customer1 = new Customer("Jack", "Bauer");
            Customer customer2 = new Customer("Chloe", "O'Brian");
            Customer customer3 = new Customer("Kim", "Bauer");
            Customer customer4 = new Customer("David", "Palmer");
            Customer customer5 = new Customer("Michelle", "Dessler");

            repository.save(customer1);
            repository.save(customer2);
            repository.save(customer3);
            repository.save(customer4);
            repository.save(customer5);
            repository.flush();

            Remarks remarks1 = new Remarks("*1");
            Remarks remarks2 = new Remarks("*2");
            Remarks remarks3 = new Remarks("*3");
            Remarks remarks4 = new Remarks("*4");
            Remarks remarks5 = new Remarks("*5");

            remarksRepository.save(remarks1);
            remarksRepository.save(remarks2);
            remarksRepository.save(remarks3);
            remarksRepository.save(remarks4);
            remarksRepository.save(remarks5);

            log.info(remarks1.toString());
            log.info(remarks2.toString());
            log.info(remarks3.toString());
            log.info(remarks4.toString());
            log.info(remarks5.toString());

            customer1.setRemarks(Arrays.asList(remarks1, remarks5));
            customer2.setRemarks(Arrays.asList(remarks2, remarks3));
            customer3.setRemarks(Arrays.asList(remarks3, remarks2));
            customer4.setRemarks(Arrays.asList(remarks4, remarks1));
            customer5.setRemarks(Arrays.asList(remarks5, remarks4));

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Customer customer : repository.findAll()) {
                log.info(customer.toString());
                log.info(customer.getRemarks().toString());
            }
            log.info("");

            customer1.setFirstName("J. Jack");
            customer4.setFirstName("Dave");

//            repository.saveAndFlush(customer1);
//            repository.saveAndFlush(customer2);
//            repository.saveAndFlush(customer3);
//            repository.saveAndFlush(customer4);
//            repository.saveAndFlush(customer5);
            repository.save(customer1);
            repository.save(customer2);
            repository.save(customer3);
            repository.save(customer4);
            repository.save(customer5);
//            repository.flush();

            // fetch all customers after modified
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Customer customer : repository.findAll()) {
                log.info(customer.toString());
                log.info(customer.getRemarks().toString());
            }
            log.info("");

            // fetch an individual customer by ID
//            Customer customer = repository.findById(1L).get();
//            log.info("Customer found with findById(1L).get():");
//            log.info("--------------------------------");
//            log.info(customer.toString());
//            log.info("");

        };
    }

}
