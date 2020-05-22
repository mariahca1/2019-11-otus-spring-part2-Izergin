package ru.izergin.hometask.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.izergin.hometask.service.BookService;
import ru.izergin.hometask.service.BookServiceImpl;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

//@WebMvcTest(controllers = TestController.class)
//@WebMvcTest(controllers = BookController.class)
//@SpringBootTest
//@AutoConfigureMockMvc
//@Import({BookServiceImpl.class})
class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    BookServiceImpl bookService;

//    @Autowired
//    BookController bookController;

    //
//    @MockBean
//    private RegisterUseCase registerUseCase;
//    @Test
    void getAllBookTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/test")).andDo(print())
//                .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}