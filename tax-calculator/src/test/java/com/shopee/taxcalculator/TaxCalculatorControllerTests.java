package com.shopee.taxcalculator;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopee.taxcalculator.controller.TaxCalculatorController;
import com.shopee.taxcalculator.model.Detail;
import com.shopee.taxcalculator.model.Product;
import com.shopee.taxcalculator.model.TaxCalculator;
import com.shopee.taxcalculator.service.TaxCalculatorService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@WebMvcTest(TaxCalculatorController.class)
public class TaxCalculatorControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TaxCalculatorService taxCalculatorService;

    private JacksonTester<TaxCalculator> jsonTaxCalculator;

    @Before
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void getTaxCalculatorTest() throws Exception {
        Product product = new Product(1l, "Game", 3, 100d);
        Detail detail = new Detail(product);
        TaxCalculator taxCalculator = new TaxCalculator(Arrays.asList(detail));

        Mockito.when(taxCalculatorService.getTaxCalculator()).thenReturn(taxCalculator);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/tax_calculators")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = jsonTaxCalculator.write(taxCalculator).getJson();
        String outputInJson = result.getResponse().getContentAsString();

        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(outputInJson).isEqualTo(expectedJson);
    }

}
