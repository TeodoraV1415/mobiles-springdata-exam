package softuni.exam.models.dto;

import javax.validation.constraints.Size;

public class SellerImportDTO {

    @Size(min = 2, max = 30)
    private String firstName;

    @Size(min = 2, max = 30)
    private String lastName;

    @Size(min = 3, max = 6)
    private String personalNumber;

    public SellerImportDTO() {
    }

    public String getFirstName() {
        return firstName;
    }

    public SellerImportDTO setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public SellerImportDTO setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPersonalNumber() {
        return personalNumber;
    }

    public SellerImportDTO setPersonalNumber(String personalNumber) {
        this.personalNumber = personalNumber;
        return this;
    }
}
