package com.example.service;

import com.example.entity.Doctor;
import com.example.exception.ResourceNotFound;
import com.example.payload.DoctorDto;
import com.example.repository.DoctorRepository;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService{

    private DoctorRepository doctorRepository;

    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public DoctorDto createDoctor(DoctorDto doctorDto) {
        Doctor doctor = mapToEntity(doctorDto);
        Doctor savedEntity=doctorRepository.save(doctor);
        DoctorDto dto=mapToDto(savedEntity);
        dto.setMessage("Doctor Details Registered");
        return dto;
    }

    @Override
    public void deleteDoctor(long id) {
        doctorRepository.deleteById(id);
    }

    @Override
    public DoctorDto updateDoctor(DoctorDto doctorDto, long id) {
        Optional<Doctor> byId = doctorRepository.findById(id);
        Doctor doctor=byId.get();
        doctor.setName(doctorDto.getName());
        doctor.setDepartment(doctorDto.getDepartment());
        doctor.setEmail(doctorDto.getEmail());
        doctor.setPhone(doctorDto.getPhone());
        Doctor savedEntity=doctorRepository.save(doctor);
        DoctorDto dto=mapToDto(doctor);
        dto.setMessage("Doctor Details Updated");
        return dto;
    }

    @Override
    public List<DoctorDto> getDoctorList(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort=sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(Sort.Direction.ASC, sortBy) : Sort.by(Sort.Direction.DESC, sortBy);
        Pageable pageable= PageRequest.of(pageNo, pageSize, sort);
        Page<Doctor> all = doctorRepository.findAll(pageable);
        List<Doctor> doctors = all.getContent();
        List<DoctorDto> doctorDtos=doctors.stream().map(Doctor->mapToDto(Doctor)).collect(Collectors.toList());
        return doctorDtos;
    }

    @Override
    public DoctorDto getDoctorById(long id) {
        Doctor doctor = doctorRepository.findById(id).orElseThrow(()-> new ResourceNotFound("Result not found for id: " + id));
        DoctorDto dto=mapToDto(doctor);
        return dto;
    }

    Doctor mapToEntity(DoctorDto dto){
        Doctor entity=new Doctor();
        entity.setName(dto.getName());
        entity.setDepartment(dto.getDepartment());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        return entity;
    }
    DoctorDto mapToDto(Doctor doctor){
        DoctorDto dto=new DoctorDto();
        dto.setId(doctor.getId());
        dto.setName(doctor.getName());
        dto.setDepartment(doctor.getDepartment());
        dto.setEmail(doctor.getEmail());
        dto.setPhone(doctor.getPhone());
        return dto;
    }
}
