package ru.izergin.hometask.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.izergin.hometask.dao.AuthorDao;
import ru.izergin.hometask.dao.BookDao;
import ru.izergin.hometask.service.BookServiceImpl;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
class BookControllerTest {

    @MockBean
    BookServiceImpl bookService;
    @MockBean
    AuthorDao authorDao;
    @MockBean
    BookDao bookDao;

    @Autowired
    MockMvc mockMvc;

    @WithMockUser(
            username = "fake",
            authorities = {"fake"}
    )
    @Test
    void formMainAcceessFreeTest() throws Exception {
        mockMvc.perform(get("/main"))
                .andExpect(status().isOk());
    }

    @WithMockUser(
            username = "fake",
            authorities = {"fake"}
    )
    @Test
    void formCreateAcceessFakeTest() throws Exception {
        mockMvc.perform(get("/create"))
                .andExpect(status().isForbidden());
    }

    @WithMockUser(value = "adm", authorities = "ROLE_ADMIN")
    @Test
    void formCreateAcceessTrueTest() throws Exception {
        //данный тест не проходит, ошибка 403, forbidden, Что я не так делаю?
        // Уже пытался дебагнуться, в userDetails юзер верный, с паролем и ролью которой доступен адрес
        SecurityContext securityContext = SecurityContextHolder.getContext();
        Authentication authentication = securityContext.getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        mockMvc.perform(get("/create"))
                .andExpect(status().isOk());
    }

}