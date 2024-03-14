package by.http;

import by.database.entity.UserStatus;
import by.dto.user_dto.FromUserDtoToBase;
import by.service.UserService;
import by.utils.JspHelper;
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
@WebServlet("/registration")
@RequiredArgsConstructor
@Component
public class RegistrationServlet  extends HttpServlet {
    private final UserService userService ;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("status", UserStatus.values());
        req.getRequestDispatcher(JspHelper.getPath("registration")).forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        var userDto = FromUserDtoToBase.builder()
                .name(req.getParameter("name"))
                .birthday(req.getParameter("birthday"))
                .password(req.getParameter("pwd"))
                .status(req.getParameter("status"))
                .build();

        try {
            userService.saveUser(userDto);
            log.info("The User with name {} and status {} was registered", userDto.getName(), userDto.getStatus());
            resp.sendRedirect("/login");
        } catch (Exception e) {
            req.setAttribute("errors", e.getMessage());
            doGet(req,resp);
        }
    }
}
