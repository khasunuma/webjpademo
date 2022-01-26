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
            /*
                以下、Customer のエンティティと Remarks のエンティティをデータベースに登録してゆきます。
                エンティティの関係は、Customer 1 (One) : Remarks N (Many) です。

                まず、Customer のエンティティを Repository に save し、エンティティは customer テーブルに登録されます。
                この時、紐付く Remarks エンティティを同時に設定しても、Customer#remarks リストは
                Hibernate の内部情報によって上書きされるため、Remarks エンティティもデータベースに登録される (*1) が、
                remarks テーブルの外部キーは必ず NULL になり、Customer エンティティに紐付きません。

                (*1) Customer#remarks に付加される @OneToMany アノテーションの fetch 属性および cascade 属性に依存します。
                fetch が FetchType.EAGER かつ、cascade に CascadeType.PERSIST が含まれる場合には、
                Remarks エンティティが remarks テーブルに登録されます。
                fetch が FetchType.LAZY の場合、cascade に CascadeType.PERSIST が含まれていない場合には、登録されません。
                なお、CascadeType.ALL を設定している場合は、CascadeType.PERSIST も同時に指定されているものとみなされます。

                Customer エンティティに Remarks エンティティを紐付けるためには、
                Remarks#customer に save 済みの Customer エンティティを設定した後、Remarks を Repository に save します。
                この操作によって Hibernate の内部状態が変化し、以下の処理が実行されます:
                1. Remarks エンティティがデータベースの remarks テーブルに登録されます
                2. Customer エンティティに対して Remarks エンティティが紐付きます (*2)
                3. Customer#remarks リストには Remarks エンティティが追加されます (*2)
                4. remarks テーブルの外部キーに customer テーブルの主キーが設定されます。

                (*2) Hibernate の内部状態が変化し、この関連付けが行われます。
                Hibernate の内部状態を変化させるトリガーとなるのは、Remarks#customer です。
                Customer#remarks は Hibernate の管轄下にあるため、操作できません。
                仮に Customer#remarks を操作した場合でも、次に当該 Customer が Repository に save されたタイミングで、
                Customer#remarks が Hibernate の内部状態によって上書きされ、操作内容はすべて消えてしまいます。

                [覚え方]
                1. Customer (顧客) がいないのに、その Remarks (備考) が存在するわけがないので、まずは Customer を先に登録する。
                2. Customer が存在するならば、Remarks は存在が許されるので、後から Remarks を登録する。
                   この時、対応する Customer の設定を忘れないように (どの Customer にも紐付かない Remarks が発生してしまう)
             */

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
