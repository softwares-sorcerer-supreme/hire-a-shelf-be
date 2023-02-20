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
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationRequest {
    private String district;
    private String address;
    private String city;
    private String ward;
    private Brand brand;
    private boolean status;

}
