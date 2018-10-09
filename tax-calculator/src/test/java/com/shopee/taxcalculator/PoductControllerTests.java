package com.shopee.taxcalculator;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.shopee.taxcalculator.controller.ProductController;
import com.shopee.taxcalculator.model.Product;
import com.shopee.taxcalculator.service.ProductService;
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

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@WebMvcTest(ProductController.class)
public class PoductControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private JacksonTester<Product> jsonProduct;
    private JacksonTester<List<Product>> jsonListProduct;

    @Before
    public void setup() {
        JacksonTester.initFields(this, new ObjectMapper());
    }

    @Test
    public void getAllProductsTest() throws Exception {
        Product product = new Product(1l, "Game", 3, 100d);
        List<Product> listProduct = new ArrayList<>();
        listProduct.add(product);

        Mockito.when(productService.getAllProducts()).thenReturn(listProduct);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/products")
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = jsonListProduct.write(listProduct).getJson();
        String outputInJson = result.getResponse().getContentAsString();

        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(outputInJson).isEqualTo(expectedJson);
    }

    @Test
    public void createProductTest() throws Exception{
        Product product = new Product(1l, "Game", 3, 100d);
        String inputInJson = jsonProduct.write(product).getJson();

        Mockito.when(productService.createProduct(product)).thenReturn(product);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/products")
                .accept(MediaType.APPLICATION_JSON)
                .content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    public void getProductByIdTest() throws Exception {
        Product product = new Product(1l, "Game", 3, 100d);

        Mockito.when(productService.getProductById(Mockito.anyLong())).thenReturn(product);
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/products/" + product.getId())
                .accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = jsonProduct.write(product).getJson();
        String outputInJson = result.getResponse().getContentAsString();

        assertThat(result.getResponse().getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(outputInJson).isEqualTo(expectedJson);
    }
}
