package track.webjpademo;

import javax.persistence.*;

@Entity
public class Remarks {

    @Id
    @GeneratedValue //(strategy = GenerationType.AUTO)
    private Long num;

    private String text;

    @ManyToOne
    @JoinColumn(name = "id")
    private Customer customer;

    protected Remarks() { }

    public Remarks(String text) {
        this.text = text;
    }

    public Remarks(String text, Customer customer) {
        this.text = text;
        this.customer = customer;
    }

    @Override
    public String toString() {
        return String.format("{%d: '%s'}", num, text);
    }

    public Long getNum() {
        return num;
    }

    public String getText() {
        return text;
    }

    public void setNum(Long num) {
        this.num = num;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
