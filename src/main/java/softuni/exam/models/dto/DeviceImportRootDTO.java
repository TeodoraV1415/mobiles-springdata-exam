package softuni.exam.models.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "devices")
@XmlAccessorType(XmlAccessType.FIELD)
public class DeviceImportRootDTO {

    @XmlElement(name = "device")
    private List<DeviceImportDTO> devices;

    public DeviceImportRootDTO() {
    }

    public List<DeviceImportDTO> getDevices() {
        return devices;
    }

    public DeviceImportRootDTO setDevices(List<DeviceImportDTO> devices) {
        this.devices = devices;
        return this;
    }
}
