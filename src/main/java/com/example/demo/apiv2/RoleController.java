package com.example.demo.apiv2;

import com.example.demo.entity.RoleEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.serviceimpl.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/insert")
    public ResponseEntity<RoleEntity> pInsert(@RequestBody RoleEntity roleEntity) throws InterruptedException {
        roleService.insert(roleEntity);
        return ResponseEntity.ok(roleEntity);
    }
    @PostMapping(value = "/insert1")
    public ResponseEntity<RoleEntity> pInsert1(@RequestBody RoleEntity roleEntity) throws InterruptedException {
        roleService.insert1(roleEntity);
        return ResponseEntity.ok(roleEntity);
    }



    @PostMapping(value = "/role")
    public ResponseEntity<RoleEntity> pRole(@RequestBody RoleEntity roleEntity) {
        roleService.persist(roleEntity);
        return ResponseEntity.ok(roleEntity);
    }

    @PostMapping(value = "/role1")
    public ResponseEntity pRol1e(@RequestBody UserEntity user, @RequestParam long id) {
        RoleEntity role = roleRepository.findById(id).orElse(new RoleEntity());
        List<UserEntity> userEntities = new ArrayList<>();

        userEntities.add(user);
        role.setUsers(userEntities);
        roleRepository.save(role);
        return ResponseEntity.ok("done");
    }

    @DeleteMapping(value = "/role")
    public ResponseEntity pRol1ed(@RequestParam long id) {
        RoleEntity role = roleRepository.findById(id).orElse(new RoleEntity());
        role.getUsers().forEach(s -> s.setPassword("ádad1y23"));

        UserEntity user = new UserEntity();
        user.setUsername("son");
        user.setPassword("123");
//        List<UserEntity> users = new ArrayList<>();
//        users.add(user);
//        role.setUsers(users);

//        role.setRoleName("ádasdsad");
        roleRepository.save(role);
//        roleRepository.delete(role);
        return ResponseEntity.ok("done");
    }

    @PutMapping(value = "/role")
    public ResponseEntity<RoleEntity> p1Role(@RequestBody RoleEntity roleEntity) {
        roleService.save(roleEntity);
        return ResponseEntity.ok(roleEntity);
    }

    @DeleteMapping("/value")
    public ResponseEntity<RoleEntity> dRole(@RequestParam("id") Long id) {
        RoleEntity role = roleRepository.getById(id);
        roleRepository.delete(role);
        return null;
    }

    @GetMapping
    public RoleEntity getRole(@RequestParam Long id) {
        return roleRepository.findById(id).orElse(new RoleEntity());
    }

    // static int temp;

    @DeleteMapping("/c")
    public void deleteRoleCasade(@RequestParam Long id, @RequestParam Long roleId) {
//        UserEntity user1 = new UserEntity();
//        user1.setId(1L);
//        IntStream.rangeClosed(1, 5).forEach(i -> {
//            user1.setUsername("son");
//            user1 = new UserEntity();
//            System.out.println("value of temp is = " + user1);
//        });

        //   Integer temp = 0;
        IntStream.rangeClosed(1, 5).forEach(i -> {
            int temp = 0;
            temp++;
            System.out.println("value of temp is = " + temp);
        });


        UserEntity user = userRepository.getById(id);
        RoleEntity role = roleRepository.getById(roleId);
        user.removeRole(role);

        userRepository.save(user);
        System.out.println("-------------");
        roleRepository.delete(role);
    }

    @DeleteMapping("/cvsrole")
    public void deleteRoleCasadea(@RequestParam Long roleId) {
        RoleEntity role = roleRepository.getById(roleId);
        role.getUsers().forEach(role::removeUser);
        System.out.println("-------------");
        roleRepository.save(role);
    }

    @PostMapping("/rol")  // xét lại vào ngày thứe 2 trong bảng trung gian có ghi đè "nên nhớ check trước khi lưu"
    public void sa(@RequestParam Long id, @RequestParam Long roleId) {
        UserEntity user = userRepository.getById(id);
        RoleEntity role = roleRepository.getById(roleId);
        user.addRole(role);
        System.out.println("-------------");
        userRepository.save(user);


    }
}
