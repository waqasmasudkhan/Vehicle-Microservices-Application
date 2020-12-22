package com.udacity.pricing.web;

import com.udacity.pricing.api.PricingController;
import com.udacity.pricing.service.PricingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PricingController.class)
public class PricingControllerUnitTest {
    @Autowired
    private MockMvc  mockMvc;

    @MockBean
    PricingService pricingService;

    @Test
    public void getPrices() throws Exception{
        mockMvc.perform(get("/services/price").param("vehicleId","3").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON)).andExpect(content().contentType("application/hal+json;charset=UTF-8"));
        verify(pricingService, times(1)).getPrice(Long.getLong("3"));
    }

    @Test
    public void getPriceNotFound() throws Exception{
        mockMvc.perform(get("/services/price").param("vehicleId","300")).andExpect(status().is(404));
        verify(pricingService, times(1)).getPrice(Long.getLong("300"));
    }

}
