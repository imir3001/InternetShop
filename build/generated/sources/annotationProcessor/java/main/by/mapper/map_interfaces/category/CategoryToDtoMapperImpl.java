package by.mapper.map_interfaces.category;

import by.database.entity.Category;
import by.dto.category_dto.CategoryDto;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-03-18T23:17:55+0300",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.jar, environment: Java 17.0.8 (Oracle Corporation)"
)
public class CategoryToDtoMapperImpl implements CategoryToDtoMapper {

    @Override
    public CategoryDto fromCategory(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDto.CategoryDtoBuilder categoryDto = CategoryDto.builder();

        categoryDto.id( category.getId() );
        categoryDto.categoryName( category.getCategoryName() );

        return categoryDto.build();
    }
}
