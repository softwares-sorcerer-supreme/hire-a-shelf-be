package com.example.shelve.dto.request;

import com.example.shelve.entities.Brand;
import com.example.shelve.entities.Registration;
import com.example.shelve.entities.Store;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationRequest {

    @NotBlank(message = "District can not be null")
    private String district;

    @NotBlank(message = "Address can not be null")
    private String address;

    @NotBlank(message = "City can not be null")
    private String city;

    @NotBlank(message = "Ward can not be null")
    private String ward;
    private boolean status;

}
