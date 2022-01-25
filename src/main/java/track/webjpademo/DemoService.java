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

            Remarks remarks1 = new Remarks("*1");
            Remarks remarks2 = new Remarks("*2");
            Remarks remarks3 = new Remarks("*3");
            Remarks remarks4 = new Remarks("*4");
            Remarks remarks5 = new Remarks("*5");

            customer1.setRemarks(Arrays.asList(remarks1));
            customer2.setRemarks(Arrays.asList(remarks2));
            customer3.setRemarks(Arrays.asList(remarks3));
            customer4.setRemarks(Arrays.asList(remarks4));
            customer5.setRemarks(Arrays.asList(remarks5));

            repository.save(customer1);
            repository.save(customer2);
            repository.save(customer3);
            repository.save(customer4);
            repository.save(customer5);

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
