package by.service;

import by.database.repository.SupplierRepository;
import by.dto.supplier_dto.FromSupplierDtoToBase;
import by.dto.supplier_dto.SupplierDto;
import by.mapper.classes.suppliers.DtoToSupplier;
import by.mapper.classes.suppliers.SupplierToDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class SupplierService {
    private final SupplierRepository suppliersRepository;
    private final SupplierToDto supplierToDto;
    private final DtoToSupplier dtoToSupplier;

    public List<SupplierDto> findAll() {
        log.info("Attempt to extract SupplierDto collection in method findAll()");
        return suppliersRepository.findAll()
                .stream()
                .map(supplierToDto::mapFrom)
                .collect(Collectors.toList());
    }

    public Optional<SupplierDto> findById(Long id) {
        log.info("Attempt to extract SupplierDto object in method findById()");
        return suppliersRepository.findById(id).map(supplierToDto::mapFrom);
    }

    public void save(FromSupplierDtoToBase fromSupplierDtoToBase) {
        var supplier = dtoToSupplier.mapFrom(fromSupplierDtoToBase);
        suppliersRepository.save(supplier);
        log.info("Attempt to save fromSupplierDtoToBase object in method save()");

    }

    public void update(String email, Long id) {
        suppliersRepository.updateEmail(email, id);
        log.info("Attempt to update email by id for object Supplier in method update()");

    }

    public void delete(Long id) {
        suppliersRepository.deleteById(id);
        log.info("Attempt to delete Supplier object by your id, in method delete()");
    }

    public List<String> listPhoneNumbers() {
        log.info("Attempt to find  phone numbers collection, in method listPhoneNumbers()");
        return suppliersRepository.listPhoneNumbers();
    }

    public List<Object[]> listEmailAndPhoneNumber() {
        log.info("Attempt to find list of arrays emails and phone numbers in method listEmailAndPhoneNumber()");
        return suppliersRepository.listEmailAndPhoneNumber();
    }
}
