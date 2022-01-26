package track.webjpademo;

import javax.persistence.*;

@Entity
public class Remarks {

    @Id
    @GeneratedValue //(strategy = GenerationType.AUTO)
    private Long num;

    private String text;

    /*
     * Remarks エンティティを紐付ける先の Customer エンティティへの参照を保持します。
     * 有効な Customer エンティティを設定して persist することで Remarks エンティティの内容がデータベースに保存されます。
     *
     * @ManyToOne アノテーションは必須です。これがない場合はリレーションが設定されません。
     * @JoinColumn アノテーションは任意で、name 属性は外部キーを表すものの名前となります。
     * @JoinColumn を省略した場合は、この場合は name 属性に customer_id が指定されたものと見なされます。
     */
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
