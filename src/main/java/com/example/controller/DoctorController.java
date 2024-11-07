package com.example.controller;

import com.example.payload.DoctorDto;
import com.example.service.DoctorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/doctor")
public class DoctorController {
    private DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }
//http://localhost:8080/api/v1/doctor
    @PostMapping
    public ResponseEntity<?> addDoctor(@Valid @RequestBody DoctorDto doctorDto, BindingResult result) {
        if (result.hasErrors()) {
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.OK);
        }
        DoctorDto doctorDto1=doctorService.createDoctor(doctorDto);
        return new ResponseEntity<>(doctorDto1, HttpStatus.CREATED);
    }
    //http://localhost:8080/api/v1/doctor?id=
    @DeleteMapping
    public ResponseEntity<String> removeDoctor(@RequestParam long id){
        doctorService.deleteDoctor(id);
        return new ResponseEntity<>("Doctor deleted successfully", HttpStatus.OK);
    }
    //http://localhost:8080/api/v1/doctor?id=
    @PutMapping
    public ResponseEntity<DoctorDto> updateDoctor(@RequestBody DoctorDto doctorDto, @RequestParam long id){
        DoctorDto doctorDto1=doctorService.updateDoctor(doctorDto, id);
        return new ResponseEntity<>(doctorDto1, HttpStatus.OK);
    }
    //http://localhost:8080/api/v1/doctor?pageNo=0&pageSize=3&sortBy=name&sortDir=esc
    //http://localhost:8080/api/v1/doctor?pageNo=0&pageSize=3&sortBy=name&sortDir=desc
    @GetMapping
    public ResponseEntity<List<DoctorDto>> getDoctorList(
            @RequestParam(name="pageNo",defaultValue = "0",required = false) int pageNo,
            @RequestParam(name="pageSize",defaultValue = "3",required = false) int pageSize,
            @RequestParam(name="sortBy",defaultValue = "name",required = false) String sortBy,
            @RequestParam(name="sortDir",defaultValue = "name",required = false) String sortDir

    ){
        List<DoctorDto> doctorDtos=doctorService.getDoctorList(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(doctorDtos,HttpStatus.OK);
    }
    //http://localhost:8080/api/v1/doctor/ById?id=
    @GetMapping("/ById")
    public ResponseEntity<DoctorDto> getDoctorById(@RequestParam long id){
        DoctorDto dto=doctorService.getDoctorById(id);
        return new ResponseEntity<>(dto,HttpStatus.OK);
    }
}
