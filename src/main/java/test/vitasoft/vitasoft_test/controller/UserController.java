package test.vitasoft.vitasoft_test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import test.vitasoft.vitasoft_test.entity.Role;
import test.vitasoft.vitasoft_test.entity.User;
import test.vitasoft.vitasoft_test.repository.UserRepository;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/getAll")
    public String getAllUsers(@RequestParam(name = "isAdmin") boolean isAdmin) {
        if (isAdmin) {
            return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                    .collect(Collectors.toList()).toString();
        } else {
            return "You have not got such privileges";
        }
    }

    @GetMapping("/grantRole")
    public void grantRole(@RequestParam(name = "isAdmin") boolean isAdmin,
                          @RequestParam(name = "userId") Long userId,
                          @RequestParam(name = "roleName") String roleName) {
        if (isAdmin) {
            User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("No such user"));
            user.getRoles().add(Role.getRoleByName(roleName));

            userRepository.save(user);
        }
    }
}
