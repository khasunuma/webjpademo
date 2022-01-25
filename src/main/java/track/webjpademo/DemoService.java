package track.webjpademo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DemoService {

    private static final Logger log = LoggerFactory.getLogger(DemoService.class);

    private final CustomerRepository repository;

    public DemoService(CustomerRepository repository) {
        this.repository = repository;
    }

    @Bean
    public CommandLineRunner getCustomer() {
        return (args) -> {
            Customer customer1 = new Customer("Jack", "Bauer");
            Customer customer2 = new Customer("Chloe", "O'Brian");
            Customer customer3 = new Customer("Kim", "Bauer");
            Customer customer4 = new Customer("David", "Palmer");
            Customer customer5 = new Customer("Michelle", "Dessler");

            Remarks remarks1 = new Remarks("*1");
            Remarks remarks2 = new Remarks("*2");
            Remarks remarks3 = new Remarks("*3");
            Remarks remarks4 = new Remarks("*4");
            Remarks remarks5 = new Remarks("*5");
            Remarks remarks6 = new Remarks("*6");
            Remarks remarks7 = new Remarks("*7");
            Remarks remarks8 = new Remarks("*8");
            Remarks remarks9 = new Remarks("*9");
            Remarks remarks0 = new Remarks("*0");

            customer1.getRemarks().add(remarks1);
            customer2.getRemarks().add(remarks2);
            customer3.getRemarks().add(remarks3);
            customer4.getRemarks().add(remarks4);
            customer5.getRemarks().add(remarks5);
            customer1.getRemarks().add(remarks6);
            customer2.getRemarks().add(remarks7);
            customer3.getRemarks().add(remarks8);
            customer4.getRemarks().add(remarks9);
            customer5.getRemarks().add(remarks0);

            repository.saveAndFlush(customer1);
            repository.saveAndFlush(customer2);
            repository.saveAndFlush(customer3);
            repository.saveAndFlush(customer4);
            repository.saveAndFlush(customer5);

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Customer customer : repository.findAll()) {
                log.info(customer.toString());
                log.info(customer.getRemarks().toString());
            }
        };
    }

}
