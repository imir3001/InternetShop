package by.mapper.map_interfaces.product;

import by.database.entity.Product;
import by.dto.product_dto.FromProductDtoToBase;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DtoToProductMapper {

    DtoToProductMapper INSTANCE = Mappers.getMapper(DtoToProductMapper.class);
    Product mapFrom(FromProductDtoToBase fromProductDtoToBase);
}
