package by.http;

import by.dto.supplier_dto.FromSupplierDtoToBase;
import by.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;


@RequiredArgsConstructor
@Controller
@RequestMapping("/suppliers")
public class SupplierController {
    private final SupplierService suppliersService;

    //TODO
    @GetMapping
    public String findAll(Model model) {
        var result = suppliersService.findAll();
        model.addAttribute("suppliers", result);
        return "supplier/suppliers";
    }

    //TODO
    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {

        return suppliersService.findById(id)
                .map(supplier -> {
                    model.addAttribute("supplier", supplier);
                    return "supplier/one_supplier";
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/registration")
    public String registrSupplr(FromSupplierDtoToBase fromSupplierDtoToBase, Model model){
        model.addAttribute("supplier", fromSupplierDtoToBase);
        return "supplier/supplier_save";
    }

    //TODO
    @PostMapping
    public String create(FromSupplierDtoToBase fromSupplierDtoToBase) {
        var result = suppliersService.save(fromSupplierDtoToBase);
        return "redirect:/suppliers/"+ result.getId();
    }

    //TODO
    //Update должен происходить на странице единичного продукта, с запуском изменений через кнопку
    @PostMapping("/{id}/update")
    public String update(@PathVariable("id")Long id, FromSupplierDtoToBase fromSupplierDtoToBase) {
        return suppliersService.update(id,fromSupplierDtoToBase)
                .map(supplier->"redirect:/suppliers/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    //TODO
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id) {
        if (!suppliersService.delete(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/suppliers";
    }
}
