package com.example.customermobile.service.impl;

import com.example.customermobile.entity.Vehicle;
import com.example.customermobile.exception.ResourceNotFoundException;
import com.example.customermobile.payload.VehicleDto;
import com.example.customermobile.payload.VehicleResponse;
import com.example.customermobile.repository.VehicleRepository;
import com.example.customermobile.service.VehicleService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {
    @Resource
    private VehicleRepository vehicleRepository;

    @Resource
    private ModelMapper modelMapper;

    public VehicleServiceImpl(VehicleRepository vehicleRepository, ModelMapper modelMapper) {
        this.vehicleRepository = vehicleRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public VehicleDto createVe(VehicleDto veDto) {
        Vehicle vehicle = mapToEntity(veDto);
        Vehicle newVehicle = vehicleRepository.save(vehicle);

        return mapToDto(newVehicle);
    }

    @Override
    public VehicleResponse getAllVes(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();

        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);

        Page<Vehicle> vehicles = vehicleRepository.findAll(pageable);
        List<Vehicle> vehicleList = vehicles.getContent();

        List<VehicleDto> content = vehicleList.stream().map(this::mapToDto).collect(Collectors.toList());

        VehicleResponse vehicleResponse = new VehicleResponse();
        vehicleResponse.setContent(content);
        vehicleResponse.setPageNo(vehicles.getNumber());
        vehicleResponse.setPageSize(vehicles.getSize());
        vehicleResponse.setTotalElements(vehicles.getTotalElements());
        vehicleResponse.setTotalPages(vehicles.getTotalPages());
        vehicleResponse.setLast(vehicleResponse.isLast());

        return vehicleResponse;
    }

    @Override
    public VehicleDto getVeById(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vehicle", "id", id));
        return mapToDto(vehicle);
    }

    @Override
    public VehicleDto updateVe(VehicleDto veDto, Long id) {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vehicle", "id", id));

        vehicle.setType(veDto.getType());
        vehicle.setCode(veDto.getCode());
        vehicle.setName(veDto.getName());
        vehicle.setPrice(veDto.getPrice());
        vehicle.setSpecial(false);
        vehicle.setParentCode(veDto.getParentCode());
        vehicle.setNameSearch(veDto.getNameSearch());
        vehicle.setPviCodeLx(veDto.getPviCodeLx());

        Vehicle updateVe = vehicleRepository.save(vehicle);

        return mapToDto(updateVe);
    }

    @Override
    public void deleteVe(Long id) {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Vehicle", "id", id));
        vehicleRepository.delete(vehicle);
    }

    private VehicleDto mapToDto(Vehicle newVehicle) {
        return modelMapper.map(newVehicle, VehicleDto.class);
    }

    private Vehicle mapToEntity(VehicleDto vehicleDto) {
        return modelMapper.map(vehicleDto, Vehicle.class);
    }
}
