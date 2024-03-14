package by.http;

import by.utils.JspHelper;
import by.service.SupplierService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Slf4j
@RequiredArgsConstructor
@Component
@WebServlet("/suppliers")
public class SupplierServlet  extends HttpServlet {
    private final SupplierService suppliersService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        var supplierList = suppliersService.findAll();
        req.setAttribute("supplierList", supplierList);
        req.getRequestDispatcher(JspHelper.getPath("suppliers")).forward(req, resp);
        log.info("The request was directed in 'suppliers' page");
    }
}
