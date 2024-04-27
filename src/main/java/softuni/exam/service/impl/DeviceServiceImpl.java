package softuni.exam.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.DeviceImportDTO;
import softuni.exam.models.dto.DeviceImportRootDTO;
import softuni.exam.models.entity.Device;
import softuni.exam.models.entity.Sale;
import softuni.exam.models.entity.enums.DeviceType;
import softuni.exam.repository.DeviceRepository;
import softuni.exam.repository.SaleRepository;
import softuni.exam.service.DeviceService;
import softuni.exam.util.ValidationUtil;
import softuni.exam.util.XmlParser;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DeviceServiceImpl implements DeviceService {

    private static final String DEVICES_FILE_PATH = "src/main/resources/files/xml/devices.xml";

    private final DeviceRepository deviceRepository;
    
    private final SaleRepository saleRepository;

    private final XmlParser xmlParser;

    private final ValidationUtil validationUtil;

    private final ModelMapper modelMapper;

    public DeviceServiceImpl(DeviceRepository deviceRepository, SaleRepository saleRepository, XmlParser xmlParser, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.deviceRepository = deviceRepository;
        this.saleRepository = saleRepository;
        this.xmlParser = xmlParser;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return this.deviceRepository.count() > 0;
    }

    @Override
    public String readDevicesFromFile() throws IOException {
        return Files.readString(Path.of(DEVICES_FILE_PATH));
    }

    @Override
    public String importDevices() throws IOException, JAXBException {
        DeviceImportRootDTO deviceImportRootDTO = this.xmlParser.fromFile(DEVICES_FILE_PATH, DeviceImportRootDTO.class);

        List<DeviceImportDTO> deviceImportDTOs = deviceImportRootDTO.getDevices();

        List<String> result = new ArrayList<>();

        for (DeviceImportDTO deviceImportDTO : deviceImportDTOs) {
           if (this.validationUtil.isValid(deviceImportDTO)){
               Optional<Device> optDevice = this.deviceRepository.findByBrandAndModel(deviceImportDTO.getBrand(), deviceImportDTO.getModel());
               if (optDevice.isEmpty()) {
                   Optional<Sale> optSale = this.saleRepository.findById(deviceImportDTO.getSaleId());

                    if (optSale.isPresent()){
                        Device mappedDevice = this.modelMapper.map(deviceImportDTO, Device.class);
//                        mappedDevice.setSale(optSale.get());
                        this.deviceRepository.save(mappedDevice);
                        result.add(String.format("Successfully imported device of type %s with brand %s", deviceImportDTO.getDeviceType(), deviceImportDTO.getBrand()));
                    } else {
                        result.add("Invalid device");
                    }
               } else {
                   result.add("Invalid device");
               }
           } else {
               result.add("Invalid device");
           }
        }

        return String.join("\n", result).trim();
    }

    @Override
    public String exportDevices() {

        List<Device> devices = this.deviceRepository.findSmartPhonesByPriceAndStorageOrderedByBrand(DeviceType.SMART_PHONE, 1000, 128);


        StringBuilder sb = new StringBuilder();
        for (Device device : devices) {

            sb.append("Device brand: ").append(device.getBrand()).append("\n");
            sb.append("   *Model: ").append(device.getModel()).append("\n");
            sb.append("   **Storage: ").append(device.getStorage()).append("\n");
            sb.append("   ***Price: ").append(String.format("%.2f", device.getPrice())).append("\n");
        }

        return sb.toString();
    }


}
