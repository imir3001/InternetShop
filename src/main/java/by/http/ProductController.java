package by.http;


import by.dto.product_dto.FromProductDtoToBase;
import by.service.CategoryService;
import by.service.ProductService;
import by.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RequiredArgsConstructor
@Controller
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;
    private final CategoryService categoryService;
    private final SupplierService supplierService;

    @GetMapping
    public String findAll(Model model) {
        var result = productService.findAll();
        model.addAttribute("products", result);
        //result.forEach(e-> System.out.println(e.getSupplier()));
        return "product/products";
    }

    //TODO
    @GetMapping("/{id}")
    public String findById(@PathVariable("id") Long id, Model model) {
//        var result = productService.findById(id).get();
//        model.addAttribute("product", result);
//        return "product/one_product";
        return productService.findById(id)
                .map(product->{
                    model.addAttribute("product", product);
                    return "product/one_product";
                })
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/registration")
    public String registrProduct(Model model, FromProductDtoToBase fromProductDtoToBase){
        model.addAttribute("product",fromProductDtoToBase);
        //model.addAttribute("categories", categoryService.findAll());
        //model.addAttribute("suppliers",supplierService.findAll());

        return "product/product_save";
    }

    //TODO
    @PostMapping
    public String create(FromProductDtoToBase productDtoToBase) {
        var result = productService.save(productDtoToBase);
        System.out.println(result.getId() + " from create Method");
        return "redirect:/products/" + result.getId();
    }

    //TODO
    @PostMapping("/{id}/update")
    public String update(@PathVariable("id") Long id, FromProductDtoToBase fromProductDtoToBase) {
        return productService.update(id, fromProductDtoToBase)
                .map(path -> "redirect:/products/{id}")
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    //TODO
    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id")Long id) {
        if(!productService.delete(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/products";
    }

}
