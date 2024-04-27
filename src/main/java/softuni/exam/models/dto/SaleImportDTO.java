package softuni.exam.models.dto;

import javax.validation.constraints.Size;

public class SaleImportDTO {

    private boolean discounted;

    @Size(min = 7, max = 7)
    private String number;

    private String saleDate;

    private Long seller;


    public SaleImportDTO() {
    }

    public boolean isDiscounted() {
        return discounted;
    }

    public SaleImportDTO setDiscounted(boolean discounted) {
        this.discounted = discounted;
        return this;
    }

    public String getNumber() {
        return number;
    }

    public SaleImportDTO setNumber(String number) {
        this.number = number;
        return this;
    }

    public String getSaleDate() {
        return saleDate;
    }

    public SaleImportDTO setSaleDate(String saleDate) {
        this.saleDate = saleDate;
        return this;
    }

    public Long getSeller() {
        return seller;
    }

    public SaleImportDTO setSeller(Long seller) {
        this.seller = seller;
        return this;
    }
}
