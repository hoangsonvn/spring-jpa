package com.example.demo.controller;

import com.example.demo.entity.RoleEntity;
import com.example.demo.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RequiredArgsConstructor
@RestController
@RequestMapping("/cole")
public class cRoleController {
    private final RoleRepository roleRepository;
    @DeleteMapping("/")
    public ResponseEntity<?> deleteRole(@RequestParam long id){
        RoleEntity role =roleRepository.findById(id).get();
        roleRepository.delete(role);
        return ResponseEntity.ok("done");
    }
}
