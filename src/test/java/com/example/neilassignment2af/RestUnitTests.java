package com.example.neilassignment2af;

import com.example.neilassignment2af.entities.Property;
import com.example.neilassignment2af.entities.Tenant;
import com.example.neilassignment2af.repositories.PropertyRepository;
import com.example.neilassignment2af.repositories.TenantRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;

import static org.assertj.core.api.BDDAssumptions.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class RestUnitTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PropertyRepository propertyRepository;

    @MockBean
    TenantRepository tenantRepository;

    @Test
    void givenAnyUser_whenGetById_thenOk() throws Exception{
        Property testProperty = new Property("New Address", "Eircode", 4, 3000);
        when(propertyRepository.findById(1)).thenReturn(Optional.of(testProperty));
        mockMvc.perform(MockMvcRequestBuilders.get("/properties/1"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.propertyAddress").value("New Address"));
    }

    @Test
    void givenAnyUser_whenGetById_thenNotFound() throws Exception{
        when(propertyRepository.existsById(1)).thenReturn(true);
        mockMvc.perform(MockMvcRequestBuilders.get("/properties/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser(username = "manager@gmail,com", roles = {"MANAGER"})
    void givenUserIsManager_whenDeleteTenant_thenOk() throws Exception{
        when(tenantRepository.existsById(1)).thenReturn(true);
        mockMvc.perform( MockMvcRequestBuilders
                .delete("/tenants/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    @WithMockUser(username = "staff@gmail,com", roles = {"OFFICE_STAFF"})
    void givenUserIsOfficeStaff_whenDeleteProperty_thenIsForbidden() throws Exception{
        when(propertyRepository.existsById(1)).thenReturn(true);
        mockMvc.perform( MockMvcRequestBuilders
                        .delete("/properties/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());
    }

    @Test
    void givenAnonymous_whenDeleteTenant_thenIsUnauthorised() throws Exception{
        when(tenantRepository.existsById(1)).thenReturn(true);
        mockMvc.perform( MockMvcRequestBuilders
                        .delete("/tenants/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }
}
