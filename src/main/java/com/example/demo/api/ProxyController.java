package com.example.demo.api;

import com.example.demo.entity.Proxy;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.IProxyRepository;
import com.example.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/proxy")
public class ProxyController {
    private final IProxyRepository iProxyRepository;
    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<?> deleteProxy(@RequestParam Long id) {
        UserEntity user = userRepository.findById(5L).get();
        user.setProxy(null);
        userRepository.save(user);
        iProxyRepository.findById(id).ifPresent(s -> iProxyRepository.delete(s));
//        UserEntity user = userRepository.findById(5L).get();
        Proxy proxy = user.getProxy();
        System.out.println(proxy);
        iProxyRepository.findById(id).ifPresent((s) -> {
            s.setIp(String.valueOf(new Date().getTime()));
            s.setUser(null);
            iProxyRepository.save(s);
        });
        return null;
    }
}
