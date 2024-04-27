package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.SellerImportDTO;
import softuni.exam.models.entity.Seller;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.SellerService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SellerServiceImpl implements SellerService {

    private static final String SELLERS_FILE_PATH = "src/main/resources/files/json/sellers.json";

    private final SellerRepository sellerRepository;

    private final Gson gson;

    private final ValidationUtil validationUtil;

    private final ModelMapper modelMapper;

    public SellerServiceImpl(SellerRepository sellerRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.sellerRepository = sellerRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return this.sellerRepository.count() > 0;
    }

    @Override
    public String readSellersFromFile() throws IOException {
        return Files.readString(Path.of(SELLERS_FILE_PATH));
    }

    @Override
    public String importSellers() throws IOException {
        String json = this.readSellersFromFile();

        SellerImportDTO[] sellerImportDTOs = this.gson.fromJson(json, SellerImportDTO[].class);

        List<String> result = new ArrayList<>();

        for (SellerImportDTO sellerImportDTO : sellerImportDTOs) {
            if (this.validationUtil.isValid(sellerImportDTO)){
                Optional<Seller> optSeller = this.sellerRepository.findByLastName(sellerImportDTO.getLastName());
                if (optSeller.isEmpty()){
                    Seller mappedSeller = this.modelMapper.map(sellerImportDTO, Seller.class);
                    this.sellerRepository.save(mappedSeller);
                    result.add(String.format("Successfully imported seller %s %s", sellerImportDTO.getFirstName(), sellerImportDTO.getLastName()));
                } else {
                    result.add("Invalid seller");
                }
            } else {
                result.add("Invalid seller");
            }
        }
        return String.join("\n", result);
    }
}
