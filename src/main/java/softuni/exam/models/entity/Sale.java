package softuni.exam.models.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = true)
    private boolean discounted;

    @Column(nullable = false, unique = true)
    private String number;

    @Column(nullable = false)
    private LocalDateTime saleDate;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = true)
    private Seller seller;

    public Sale() {
    }


    public Long getId() {
        return id;
    }

    public Sale setId(Long id) {
        this.id = id;
        return this;
    }

    public boolean isDiscounted() {
        return discounted;
    }

    public Sale setDiscounted(boolean discounted) {
        this.discounted = discounted;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public Sale setNumber(String number) {
        this.number = number;
        return this;
    }

    public LocalDateTime getSaleDate() {
        return saleDate;
    }

    public Sale setSaleDate(LocalDateTime saleDate) {
        this.saleDate = saleDate;
        return this;
    }

    public Seller getSeller() {
        return seller;
    }

    public Sale setSeller(Seller seller) {
        this.seller = seller;
        return this;
    }
}
