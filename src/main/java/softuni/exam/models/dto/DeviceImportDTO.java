package softuni.exam.models.dto;

import softuni.exam.models.entity.enums.DeviceType;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class DeviceImportDTO {

    @XmlElement
    @Size(min = 2, max = 20)
    private String brand;

    @XmlElement(name = "device_type")
    private DeviceType deviceType;

    @XmlElement
    @Size(min = 1, max = 20)
    private String model;

    @XmlElement
    @Positive
    private double price;

    @XmlElement
    @Positive
    private int storage;

    @XmlElement(name = "sale_id")
    private long saleId;

    public DeviceImportDTO() {
    }

    public String getBrand() {
        return brand;
    }

    public DeviceImportDTO setBrand(String brand) {
        this.brand = brand;
        return this;
    }

    public DeviceType getDeviceType() {
        return deviceType;
    }

    public DeviceImportDTO setDeviceType(DeviceType deviceType) {
        this.deviceType = deviceType;
        return this;
    }

    public String getModel() {
        return model;
    }

    public DeviceImportDTO setModel(String model) {
        this.model = model;
        return this;
    }

    public double getPrice() {
        return price;
    }

    public DeviceImportDTO setPrice(double price) {
        this.price = price;
        return this;
    }

    public int getStorage() {
        return storage;
    }

    public DeviceImportDTO setStorage(int storage) {
        this.storage = storage;
        return this;
    }

    public long getSaleId() {
        return saleId;
    }

    public DeviceImportDTO setSaleId(long saleId) {
        this.saleId = saleId;
        return this;
    }
}
