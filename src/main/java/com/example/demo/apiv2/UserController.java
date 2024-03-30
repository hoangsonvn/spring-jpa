package com.example.demo.apiv2;

import com.example.demo.entity.RoleEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.serviceimpl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    //    @PostMapping(value = "user")
//    public ResponseEntity<UserEntity> uUserEntity(@RequestBody UserEntity userEntity) {
//        userService.persist(userEntity);
//        return ResponseEntity.ok(userEntity);
//    }
//    @PutMapping(value = "user")
//    public ResponseEntity<UserEntity> uUsntity(@RequestBody UserEntity userEntity) {
//        userService.save(userEntity);
//        return ResponseEntity.ok(userEntity);
//    }
//    @DeleteMapping("user")
//    public ResponseEntity dUserEntity(@RequestParam("id")long id){
//      //  userService.remove(1l);
//        userService.remove( id);
//        return  null;
//    }
    @GetMapping("/role")
    public UserEntity find(@RequestParam Long id) {
        UserEntity user = userRepository.findByRoles_Id(id).get();
        CompletableFuture.runAsync(() -> {
            UserEntity user1 = userRepository.findByRoles_Id(5L).get();
            System.out.println(user1);
        });
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleName("admin2");
        user.addRole(roleEntity);
        userRepository.save(user);
        return user;
    }

    @DeleteMapping()
    public ResponseEntity delete(@RequestParam Long id) {
        Optional<UserEntity> userOpt = userRepository.findById(id);
        userRepository.delete(userOpt.get());
        return ResponseEntity.ok("done");
    }

    @GetMapping
    public UserEntity getUser(@RequestParam Long id) {
        Optional<UserEntity> userOpt = userRepository.findById(id);
        List<RoleEntity> roles = new ArrayList<>();
        RoleEntity roleEntity = new RoleEntity();
        //    roleEntity.setId(5L);
        roleEntity.setRoleName("qq1");
        RoleEntity roleEntity1 = new RoleEntity();
        roleEntity1.setRoleName("admin3");
        roles.add(roleEntity);
        //  roles.add(roleEntity1);
//        RoleEntity role = roleRepository.findById(11L).get();
//        roles.add(role);
        userOpt.get().setRoles(roles);
        userRepository.save(userOpt.get());
        //System.out.println( userOpt.get().getRoles());
        return userOpt.get();
    }

    @GetMapping("/dao")
    public UserEntity getUserNotRepo(@RequestParam Long id) {

        UserEntity user = userRepository.findById(id).orElseThrow(() -> new RuntimeException(("error")));
        user.getRoles().clear();

        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleName("admin2");
        RoleEntity roleEntity1 = new RoleEntity();
        roleEntity1.setRoleName("admin3");

        user.addRole(roleEntity);
        user.addRole(roleEntity1);
        userRepository.save(user);
        return user;
    }

    @GetMapping("/dao1")
    public UserEntity sua(@RequestParam Long id) {

        UserEntity user = userRepository.findById(id).orElseThrow(() -> new RuntimeException(("error")));
        user.getRoles().clear();

        RoleEntity roleEntity = roleRepository.findById(10L).orElseThrow(() -> new RuntimeException("exception"));
        //  roleEntity.setId(10L);
        roleEntity.setRoleName("1admin2qưe22");
        RoleEntity roleEntity1 = new RoleEntity();
        roleEntity1.setRoleName("admin3");

        user.addRole(roleEntity);
        //     user.addRole(roleEntity1);
        userRepository.save(user);
        return user;
    }


    @PostMapping()
    public ResponseEntity<?> updateUser(@RequestBody UserEntity user) {
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setRoleName("admin");
        List<RoleEntity> roles = new ArrayList<>();
        roles.add(roleEntity);
        user.setRoles(roles);
        UserEntity response = userRepository.save(user);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/user1")
    public ResponseEntity saves(@RequestParam long id) {
    //    UserEntity user = userRepository.findById(id).get();
        UserEntity user = new UserEntity();
        user.setUsername("123asdasdsa");

        RoleEntity roleEntity = new RoleEntity();
     //   roleEntity.setId(10L);
        roleEntity.setRoleName("7657    6547");
        RoleEntity roleEntity1 = new RoleEntity();
        roleEntity1.setRoleName("admin3");
        user.addRole(roleEntity);
        user.addRole(roleEntity1);
        userRepository.save(user);
        return null;
    }

    public int getRandom() {
        return (int) (Math.random() * 50 + 1);
    }

    @PostMapping("/user11")
    public ResponseEntity saveasds() {


        UserEntity user = new UserEntity();
        user.setUsername(String.valueOf(getRandom()));

        List<RoleEntity> roles = new ArrayList<>();
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setId(11L);
        roleEntity.setRoleName(String.valueOf(getRandom()));
        RoleEntity roleEntity1 = new RoleEntity();
        roleEntity1.setRoleName(String.valueOf(getRandom()));
        roles.add(roleEntity);
        roles.add(roleEntity1);
        user.setRoles(roles);
        userRepository.save(user);
        return null;
    }
}
