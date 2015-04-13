package de.synyx.selfservice.security;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * Created by klem on 13.04.15.
 */
@RestController
public class UserController {

    @RequestMapping("/user")
    public Principal getUser(Principal user){
        return user;
    }
}