//package com.imanuel.ronaldo.simplepos.response;
//
//import com.imanuel.ronaldo.simplepos.exception.GlobalExceptionHandler;
//import com.imanuel.ronaldo.simplepos.exception.ResourceNotFoundException;
//import com.imanuel.ronaldo.simplepos.product.dto.ErrorResponse;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//
//@ExtendWith(MockitoExtension.class)
//class GlobalExceptionHandlerTest {
//    private MockMvc mockMvc;
//
//    @InjectMocks
//    private GlobalExceptionHandler globalExceptionHandler;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//        mockMvc = MockMvcBuilders.standaloneSetup(globalExceptionHandler).build();
//    }
//
//    @Test
//    @DisplayName("Test handleResourceNotFoundException()")
//    void testHandleResourceNotFoundException() throws Exception {
//        // Prepare test data
//        String errorMessage = "Resource not found";
//        ResourceNotFoundException ex = new ResourceNotFoundException(errorMessage);
//
//        //Mocking ResourceNotFoundException
//        when(globalExceptionHandler.handleResourceNotFoundException(ex))
//                .thenReturn(ResponseEntity.status(HttpStatus.NOT_FOUND)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .body(ErrorResponse.builder()
//                                .httpStatusCode(HttpStatus.NOT_FOUND.value())
//                                .errorCode(1001)
//                                .errorMessage(errorMessage)
//                                .build()));
//
//        // Perform the request and assert the response
//        mockMvc.perform(get("/test")).andExpect(status().isNotFound());
//    }
//}
