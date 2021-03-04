package com.pgk.delivery;

import com.pgk.delivery.Login.Controller.LoginController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DeliveryApplicationTests {
    @Autowired
    LoginController loginController;

    @Test
    void contextLoads() {

    }

}
