package by.mapper.map_interfaces.product;

import by.database.entity.Product;
import by.dto.product_dto.FromProductDtoToBase;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-20T17:22:59+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.jar, environment: Java 17.0.8 (Oracle Corporation)"
)
public class DtoToProductMapperImpl implements DtoToProductMapper {

    @Override
    public Product mapFrom(FromProductDtoToBase fromProductDtoToBase) {
        if ( fromProductDtoToBase == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.supplier( fromProductDtoToBase.getSupplier() );
        product.name( fromProductDtoToBase.getName() );
        product.count( fromProductDtoToBase.getCount() );
        product.priceForOne( fromProductDtoToBase.getPriceForOne() );
        product.category( fromProductDtoToBase.getCategory() );

        return product.build();
    }
}
