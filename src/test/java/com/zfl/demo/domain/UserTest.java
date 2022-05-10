package com.zfl.demo.domain;

import com.zfl.demo.DBRollbackBase;
import com.zfl.demo.DemoApplication;
import com.zfl.demo.domain.user.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = DemoApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserTest extends DBRollbackBase {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test() {
        userRepository.findAll().forEach(System.out::println);
    }
}
