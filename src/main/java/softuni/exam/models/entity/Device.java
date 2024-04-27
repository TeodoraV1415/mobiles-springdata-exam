package softuni.exam.models.entity;

import softuni.exam.models.entity.enums.DeviceType;

import javax.persistence.*;

@Entity
@Table(name = "devices")
public class Device {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String brand;

    @Enumerated(EnumType.STRING)
    @Column(nullable = true)
    private DeviceType deviceType;

    @Column(nullable = false, unique = true)
    private String model;

    @Column(nullable = true)
    private double price;

    @Column(nullable = true)
    private int storage;

    @ManyToOne
    @JoinColumn(name = "sale_id", nullable = true)
    private Sale sale;

    public Device() {
    }

    public Long getId() {
        return id;
    }

    public Device setId(Long id) {
        this.id = id;
        return this;
    }

    public String getBrand() {
        return brand;
    }

    public Device setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public Device setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
        return this;
    }

    public String getModel() {
        return model;
    }

    public Device setModel(String model) {
        this.model = model;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public Device setPrice(double price) {
        this.price = price;
        return this;
    }

    public int getStorage() {
        return storage;
    }

    public Device setStorage(int storage) {
        this.storage = storage;
        return this;
    }

    public Sale getSale() {
        return sale;
    }

    public Device setSale(Sale sale) {
        this.sale = sale;
        return this;
    }
}
