package softuni.exam.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.exam.models.dto.SaleImportDTO;
import softuni.exam.models.entity.Sale;
import softuni.exam.models.entity.Seller;
import softuni.exam.repository.SaleRepository;
import softuni.exam.repository.SellerRepository;
import softuni.exam.service.SaleService;
import softuni.exam.util.ValidationUtil;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SaleServiceImpl implements SaleService {

    private static final String SALES_FILE_PATH = "src/main/resources/files/json/sales.json";

    private final SaleRepository saleRepository;

    private final SellerRepository sellerRepository;

    private final Gson gson;

    private final ValidationUtil validationUtil;

    private final ModelMapper modelMapper;

    public SaleServiceImpl(SaleRepository saleRepository, SellerRepository sellerRepository, Gson gson, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.saleRepository = saleRepository;
        this.sellerRepository = sellerRepository;
        this.gson = gson;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public boolean areImported() {
        return this.saleRepository.count() > 0;
    }

    @Override
    public String readSalesFileContent() throws IOException {
        return Files.readString(Path.of(SALES_FILE_PATH));
    }

    @Override
    public String importSales() throws IOException {
        String json = this.readSalesFileContent();

        SaleImportDTO[] saleImportDTOs = this.gson.fromJson(json, SaleImportDTO[].class);

        List<String> result = new ArrayList<>();

        for (SaleImportDTO saleImportDTO : saleImportDTOs) {
            if (this.validationUtil.isValid(saleImportDTO)){
                Optional<Sale> optSale = this.saleRepository.findByNumber(saleImportDTO.getNumber());
                if (optSale.isEmpty()){

                    Optional<Seller> sellerById = this.sellerRepository.findById(saleImportDTO.getSeller());

                    Sale mappedSale = this.modelMapper.map(saleImportDTO, Sale.class);
                    mappedSale.setSeller(sellerById.get());
                    this.saleRepository.save(mappedSale);
                    result.add(String.format("Successfully imported sale with number %s", saleImportDTO.getNumber()));
                } else {
                    result.add("Invalid sale");
                }
            } else {
                result.add("Invalid sale");
            }
        }
        return String.join("\n", result);
    }
}
