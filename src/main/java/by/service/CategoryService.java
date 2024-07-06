package by.service;

import by.database.repository.CategoryRepository;
import by.dto.category_dto.CategoryDto;
import by.dto.category_dto.FromDtoToCategory;
import by.mapper.classes.categories.CategoryToDto;
import by.mapper.classes.categories.DtoToCategory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final DtoToCategory dtoToCategory;
    private final CategoryToDto categoryToDto;

    public List<CategoryDto> findAll() {
        log.info("Attempt to extract categoryDto collection in method findAll()");
        return categoryRepository.findAllBy()
                .stream()
                .map(categoryToDto::mapFrom)
                .collect(Collectors.toList());


    }

    public Optional<CategoryDto> findById(Long id) {
        log.info("Attempt to extract categoryDto object in method findById()");

        return categoryRepository.findById(id).map(category -> CategoryDto.builder()
                .id(category.getId())
                .categoryName(category.getCategoryName())
                .build());
    }

    public CategoryDto save(FromDtoToCategory fromDtoToCategory) {
        var category = categoryRepository.save(dtoToCategory.mapFrom(fromDtoToCategory));
        log.info("Attempt to save fromCategoryDtoToCategory object in method save()");
        return categoryToDto.mapFrom(category);

        //       log.info("Attempt to save fromCategoryDtoToCategory object in method save()");
//        return Optional.ofNullable(fromDtoToCategory)
//                .map(dtoToCategory::mapFrom)
//                .map(categoryRepository::save)
//                .map(categoryToDto::mapFrom)
//                .orElseThrow();
    }

    public void update(String name, Long id) {
        categoryRepository.updateName(name, id);
        log.info("Attempt to update name by id for object Category in method updateName()");
    }

    public boolean delete(Long id) {
        log.info("Attempt to delete Category object by your id, in method delete()");
        return categoryRepository.findById(id)
                .map(category -> {
                    categoryRepository.deleteById(category.getId());
                    categoryRepository.flush();
                    return true;
                })
                .orElse(false);

    }
}
