package com.example.service;

import com.example.payload.DoctorDto;

import java.util.List;

public interface DoctorService {
    public DoctorDto createDoctor(DoctorDto doctorDto);

    void deleteDoctor(long id);

    DoctorDto updateDoctor(DoctorDto doctorDto, long id);

    List<DoctorDto> getDoctorList(int pageNo, int pageSize, String sortBy, String sortDir);

    DoctorDto getDoctorById(long id);
}
