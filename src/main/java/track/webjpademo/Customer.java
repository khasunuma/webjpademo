package track.webjpademo;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;

    private String lastName;

    /*
     * Customer エンティティに紐付く Remarks エンティティのコレクション (リスト) です。
     * ここでは、Customer 1 に対して Remarks N のリレーションを設定しています。
     *
     * @OneToMany アノテーションは必須で、これがない場合はリレーションが設定されません。
     * mappedBy 属性は、リレーションを設定する向こう側のエンティティ名で、この場合は customer となります。
     * fetch 属性は FetchType.LAZY または FetchType.EAGER のいずれかを取ります。
     *   FetchType.EAGER は Customer エンティティ取得時に関連する Remarks をすべて取得します。
     *   FetchType.LAZY は Customer エンティティ取得後、Remarks が参照された時に初めてそれを取得します。
     *   クエリーによる参照時には必ずしも Remarks の情報が必要とは限らないため、FetchType.LAZY が多用されます。
     * cascade 属性は persist / merge / remove 時に紐付くリレーションを追従させるかどうかを指定するもので、
     *   CascadeType.ALL はすべて追従するよう設定します。
     *   cascade 属性はリストを取るため、追従が必要な操作だけ指定することもできます。
     *
     * このリストは、Hibernate の管轄下にあり、その値は Hibernate の内部状態によって自動的に決定されます。
     * そのため、このリストに対するあらゆる操作は、エンティティを persist / merge した時点で無効となり、
     * Hibernate が内部で持っているリストによって上書きされます。
     */
    @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Remarks> remarks;

    protected Customer() { }

    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return String.format("{'id': %d, 'firstName': '%s', 'lastName': '%s'}", id, firstName, lastName);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<Remarks> getRemarks() {
        return remarks;
    }

    public void setRemarks(List<Remarks> remarks) {
        this.remarks = remarks;
    }

}
