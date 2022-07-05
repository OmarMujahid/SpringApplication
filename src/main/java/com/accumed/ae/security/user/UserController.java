package com.accumed.ae.security.user;

import com.accumed.ae.security.role.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;


@Data
class RoleToUserForm{
    private String username;
    private String roleName;
}
//@Data
//class UsernamePassword{
//    private String username;
//    private String password;
//}
//Map<String,String> usernamePassword;
@RestController
@RequestMapping("/security")
public class UserController {

    private final UserServices userServices;
    @Autowired
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }
    @GetMapping("/users")
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok().body(userServices.getUsers());
    }

    @PostMapping("/user/save")
    public ResponseEntity<User> saveUser(@RequestBody User user){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/security/user/save").toUriString());
        return ResponseEntity.created(uri).body(userServices.saveUser(user));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role){
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/security/role/save").toUriString());
        return ResponseEntity.created(uri).body(userServices.saveRole(role));
    }

    @PostMapping("role/addRoleToUser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserForm form){
        userServices.addRoleToUser(form.getUsername(),form.getRoleName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/user")
    public ResponseEntity<User> getUser(@RequestParam String username){
        return ResponseEntity.ok().body(userServices.getUser(username));
    }


}
