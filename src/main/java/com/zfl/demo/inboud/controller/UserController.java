package com.zfl.demo.inboud.controller;

import com.zfl.demo.domain.user.User;
import com.zfl.demo.domain.user.UserService;
import io.swagger.annotations.Api;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(tags = "用户接口")
@RestController
@RequestMapping("/users")
@CacheConfig(cacheNames = "controller.user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    @Cacheable(key = "'ALL_USERS'")
    public List<User> list() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @Cacheable(key = "#id")
    public User get(@PathVariable Long id) {
        return userService.getUser(id);
    }

    @PostMapping
    @Caching(evict = {
            @CacheEvict(key = "#user.id"),
            @CacheEvict(key = "'ALL_USERS'")
    })
    public User create(@Valid User user) {
        return userService.createUser(user);
    }

    @DeleteMapping("/{id}")
    @Caching(evict = {
            @CacheEvict(key = "#id"),
            @CacheEvict(key = "'ALL_USERS'"),
    })
    public void delete(@PathVariable Long id) {
        userService.deleteUser(id);
    }
}
