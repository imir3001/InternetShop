package by.service;

import by.database.repository.ProductRepository;
import by.dto.product_dto.FromProductDtoToBase;
import by.dto.product_dto.ProductDto;
import by.mapper.classes.products.DtoToProduct;
import by.mapper.classes.products.ProductToDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productsRepository;
    private final ProductToDto productToDto;
    private final DtoToProduct dtoToProduct;

    public List<ProductDto> findAll() {
        log.info("Attempt to extract productDto collection in method findAll()");
        return productsRepository.findAll()
                .stream()
                .map(productToDto::mapFrom)
                .collect(Collectors.toList());
    }

    public Optional<ProductDto> findById(Long id) {
        log.info("Attempt to extract productDto object in method findById()");
        return productsRepository.findById(id).map(productToDto::mapFrom);
    }

    public void save(FromProductDtoToBase fromProductDtoToBase) {
        log.info("Attempt to save fromProductDtoToBase object in method save()");

        var product = dtoToProduct.mapFrom(fromProductDtoToBase);
        productsRepository.save(product);
    }

    public void updateProductPriceForOneById(Long priceForOne, Long id) {
        productsRepository.updateProductPriceForOneById(priceForOne, id);
        log.info("Attempt to update price for one product " +
                 "by id for object Product in method updateProductPriceForOneById()");

    }

    public void delete(Long id) {
        productsRepository.deleteById(id);
        log.info("Attempt to delete Product object by your id, in method delete()");
    }
}
