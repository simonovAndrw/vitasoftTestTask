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

import static test.vitasoft.vitasoft_test.util.Utility.isSingleRole;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/getAll")
    public String getAllUsers(@RequestParam(name = "userId") Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("No such user"));
        if (isSingleRole(user.getRoles(), Role.ADMIN)) {
            return StreamSupport.stream(userRepository.findAll().spliterator(), false)
                    .collect(Collectors.toList()).toString();
        } else {
            return "You have not got such privileges";
        }
    }

    @GetMapping("/grantRole")
    public String grantRole(@RequestParam(name = "userId") Long userId,
                          @RequestParam(name = "secondUserId") Long secondUserId,
                          @RequestParam(name = "roleName") String roleName) {
        User user = userRepository.findById(userId).orElseThrow(() -> new UsernameNotFoundException("No such user"));
        if (isSingleRole(user.getRoles(), Role.ADMIN)) {
            User secondUser = userRepository.findById(secondUserId).orElseThrow(() -> new UsernameNotFoundException("No such user"));
            secondUser.getRoles().add(Role.getRoleByName(roleName));

            userRepository.save(secondUser);
            return "Role granted";
        }

        return "You dont have such permission";
    }
}
