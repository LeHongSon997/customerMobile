package com.example.customermobile.controller;

import com.example.customermobile.payload.VehicleDto;
import com.example.customermobile.payload.VehicleResponse;
import com.example.customermobile.service.VehicleService;
import com.example.customermobile.untils.AppConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class VehicleController {
    @Resource
    private VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/vehicle")
    public ResponseEntity<VehicleDto> createVehicle(@Valid @RequestBody VehicleDto vehicleDto) {
        return new ResponseEntity<>(vehicleService.createVe(vehicleDto), HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/vehicles")
    public VehicleResponse getAllVehicles(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return vehicleService.getAllVes(pageNo, pageSize, sortBy, sortDir);
    }

    @GetMapping("/vehicles/{id}")
    public ResponseEntity<VehicleDto> getVehicleById(@PathVariable(name = "id") Long id) {
        return new ResponseEntity<>(vehicleService.getVeById(id), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/vehicles/{id}")
    public ResponseEntity<VehicleDto> updateVehicle(@Valid @RequestBody VehicleDto vehicleDto, @PathVariable(name = "id") Long id) {
        VehicleDto responseVehicle = vehicleService.updateVe(vehicleDto, id);

        return new ResponseEntity<>(responseVehicle, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/vehicles/{id}")
    public ResponseEntity<String> deleteVehicle(@PathVariable(name = "id") Long id) {
        vehicleService.deleteVe(id);

        return new ResponseEntity<>("Delete successfully", HttpStatus.OK);
    }
}
