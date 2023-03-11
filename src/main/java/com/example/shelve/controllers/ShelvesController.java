package com.example.shelve.controllers;

import com.example.shelve.dto.request.ShelvesRequest;
import com.example.shelve.dto.request.ShelvesTypeRequest;
import com.example.shelve.dto.response.APIResponse;
import com.example.shelve.dto.response.ShelvesResponse;
import com.example.shelve.dto.response.ShelvesTypeResponse;
import com.example.shelve.entities.ShelvesType;
import com.example.shelve.services.ShelvesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/shelve")
public class ShelvesController {

    @Autowired
    private ShelvesService shelvesService;

    @GetMapping
    public APIResponse<List<ShelvesResponse>> getListShelvesWithFilter(@RequestParam(required = false, defaultValue = "") String keyword,
                                                                         @RequestParam(required = false, defaultValue = "0") long storeId,
                                                                         @RequestParam(value = "page", required = false, defaultValue = "0") int page,
                                                                         @RequestParam(required = false, defaultValue = "none") String status) {
        return shelvesService.getListShelvesWithFilter(storeId, keyword, page, status);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShelvesResponse> getShelve(@PathVariable Long id) {
        return new ResponseEntity<>(shelvesService.getShelve(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ShelvesResponse> createShelve(@RequestBody ShelvesRequest shelvesRequest) {
        return new ResponseEntity<>(shelvesService.createShelve(shelvesRequest), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShelvesResponse> updateShelve(@PathVariable Long id, @Valid @RequestBody ShelvesRequest shelvesRequest) {
        return new ResponseEntity<>(shelvesService.updateShelve(id, shelvesRequest), HttpStatus.OK);
    }

    @GetMapping("/types")
    public List<ShelvesTypeResponse> getListShelvesTypes(@RequestParam(required = false, defaultValue = "none") String status) {
        return shelvesService.getListShelvesTypes(status);
    }

    @PostMapping("/type")
    public ResponseEntity<ShelvesTypeResponse> crateShelveType(@RequestBody ShelvesTypeRequest shelvesTypeRequest) {
        return new ResponseEntity<>(shelvesService.createShelveType(shelvesTypeRequest), HttpStatus.OK);
    }

}
