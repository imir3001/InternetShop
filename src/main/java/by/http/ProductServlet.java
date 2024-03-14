package by.http;


import by.utils.JspHelper;
import by.service.ProductService;
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
@WebServlet("/product")
public class ProductServlet  extends HttpServlet {
    private final ProductService productService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Long productId = Long.valueOf(req.getParameter("productId"));
        var product = productService.findById(productId);
        req.setAttribute("product", product);
        req.getRequestDispatcher(JspHelper.getPath("products")).forward(req, resp);
        log.info("The request was directed in 'products' page");
    }
}
