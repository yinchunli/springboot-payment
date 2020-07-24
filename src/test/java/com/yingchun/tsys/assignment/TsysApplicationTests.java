package com.yingchun.tsys.assignment;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.yingchun.tsys.assignment.model.Payment;
import com.yingchun.tsys.assignment.repo.PaymentRepository;
import com.yingchun.tsys.assignment.service.PaymentService;

import org.apache.logging.log4j.core.util.Assert;
import org.aspectj.lang.annotation.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class TsysApplicationTests {

    private String paymentJson = "{" + 
    		"\"id\": 1," + 
    		"\"customerId\": 101," + 
    		"\"year\": 2020," + 
    		"\"month\": 1,	" + 
    		"\"amount\": 200" + 
    	"}";
    		
    private String newPaymentJson = "{" + 
    		"\"customerId\": 101," + 
    		"\"year\": 2020," + 
    		"\"month\": 1," + 
    		"\"amount\": 200" + 
    	"}"; 
    		
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private TestRestTemplate restTemplate;
    
    @Autowired
//    @MockBean
    private PaymentService paymentService;

    @Autowired
    private PaymentRepository paymentRepository;
    
    public TsysApplicationTests() {};

    @Before(value = "")
    public void setUp() {
        this.mockMvc = webAppContextSetup(webApplicationContext).build();

        for(int i=0;i<5; i++) {
	        Payment payment = createTestPayments(paymentJson);        
	        paymentService.addPayment(payment);
    	}
    }

    private Payment createTestPayments(String jsonString) {
    	Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Payment payment = null;

        try {
            payment = new ObjectMapper().readValue(jsonString, Payment.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return payment;
	}
	    
    @Test //getPaymentById
    public void testGetPayments()
      throws Exception {
     
        Payment payment = createTestPayments(paymentJson);
        paymentService.addPayment(payment);
        
        ResponseEntity<Payment> responseEntity = restTemplate.getForEntity("/payments/{id}",Payment.class,"1");
        assertThat(responseEntity.getBody().getAmount().equals(Integer.valueOf(200)));
    }

    @Test
    public void generalApiTest()
      throws Exception {
    	HttpHeaders headers = this.restTemplate.getForEntity("/payments", String.class).getHeaders();

        mockMvc.perform(get("/payments")
          .contentType(MediaType.APPLICATION_JSON))
          .andExpect(status().isOk());
    }

	@Test //post, save new payment
	public void savePaymentsTest() throws Exception {
         mockMvc.perform(
                post("/payments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(newPaymentJson)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists());
    }

	@Test //delete delete payments
	public void deletePaymentByIdTest() throws Exception {
        Payment newPayment = createTestPayments(paymentJson);
        newPayment = paymentService.addPayment(newPayment);

        mockMvc.perform(
            delete("/payments/"+newPayment.getPaymentId())
        )
        .andExpect(status().isOk());

        Assert.isEmpty(paymentService.getPaymentById(newPayment.getPaymentId()));
    }

}
