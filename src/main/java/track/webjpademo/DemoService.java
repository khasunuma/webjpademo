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
            Customer customer1 = repository.save(new Customer("Jack", "Bauer"));
            Customer customer2 = repository.save(new Customer("Chloe", "O'Brian"));
            Customer customer3 = repository.save(new Customer("Kim", "Bauer"));
            Customer customer4 = repository.save(new Customer("David", "Palmer"));
            Customer customer5 = repository.save(new Customer("Michelle", "Dessler"));

            Remarks remarks1 = remarksRepository.save(new Remarks("Remarks #1", customer1));
            Remarks remarks2 = remarksRepository.save(new Remarks("Remarks #2", customer2));
            Remarks remarks3 = remarksRepository.save(new Remarks("Remarks #3", customer3));
            Remarks remarks4 = remarksRepository.save(new Remarks("Remarks #4", customer4));
            Remarks remarks5 = remarksRepository.save(new Remarks("Remarks #5", customer5));
            Remarks remarks6 = remarksRepository.save(new Remarks("Remarks #6", customer1));
            Remarks remarks7 = remarksRepository.save(new Remarks("Remarks #7", customer1));
            Remarks remarks8 = remarksRepository.save(new Remarks("Remarks #8", customer2));

            // fetch all customers
            log.info("Customers found with findAll():");
            log.info("-------------------------------");
            for (Customer customer : repository.findAll()) {
                log.info(String.format("Customer %s", customer.toString()));
                log.info(String.format("Remarks %s", customer.getRemarks().toString()));
            }
        };
    }

}
