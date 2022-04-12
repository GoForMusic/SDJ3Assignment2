package via.sdj3.sdj3assignment2.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;

@Entity
@Table(name ="Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "description")
    private String description;
    @Column(name = "amount")
    private float amount;
    @Column(name="delivered")
    private boolean delivered;

    public Order(){}

    public float getAmount() {
        return amount;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public boolean isDelivered() {
        return delivered;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public void setDelivered(boolean delivered) {
        this.delivered = delivered;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(int id) {
        this.id = id;
    }
}
