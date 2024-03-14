package by.http;

import by.database.repository.Greid;
import by.dto.employees_dto.FromDtoToEmployee;
import by.service.EmployeeService;
import by.service.RankService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping("/employees")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final RankService rankService;

    @GetMapping
    public String findAll(Model model) {
        var result = employeeService.findAll();
        model.addAttribute("employees", result);
        return "employee/employees";
    }

    @GetMapping("/{id}")
    public String findById(@PathVariable("id")Long id, Model model){
        return employeeService.findById(id)
                .map(employee -> {
                    model.addAttribute("employee",employee);
                    model.addAttribute("greids",Greid.values());
                    return "employee/employee";
                })
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    @PostMapping
    public String create(@ModelAttribute FromDtoToEmployee fromDtoToEmployee){
        System.out.println(fromDtoToEmployee);
        var result = employeeService.save(fromDtoToEmployee);
        System.out.println(result + " before save()");
        return "redirect:/employees/" + result.getId();

    }

    @PostMapping("/{id}/update")
    public String update(@PathVariable("id")Long id, @ModelAttribute FromDtoToEmployee fromDtoToEmployee){//@ModelAttribute("employee")
        System.out.println(fromDtoToEmployee + " from employee Update");
        employeeService.updateAddress(fromDtoToEmployee.getAddress(),id);
        return "redirect:/employees/{id}";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") Long id){
        if(!employeeService.delete(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/employees";
    }

    @GetMapping("/registration")
    public String registration(Model model,FromDtoToEmployee fromDtoToEmployee){
        System.out.println(fromDtoToEmployee + " fromDto");
        model.addAttribute("employee", fromDtoToEmployee);
        model.addAttribute("ranks", rankService.findAll());
        System.out.println(model.getAttribute("ranks") + " model");

        return "employee/employee_save";
    }


}
