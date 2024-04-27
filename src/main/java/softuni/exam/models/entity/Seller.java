package softuni.exam.models.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "sellers")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false, unique = true)
    private String lastName;

    @Column(name = "personal_number", nullable = false, unique = true)
    private String personalNumber;


    public Seller() {
    }


    public Long getId() {
        return id;
    }

    public Seller setId(Long id) {
        this.id = id;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public Seller setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public Seller setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public Seller setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
        return this;
    }
}
