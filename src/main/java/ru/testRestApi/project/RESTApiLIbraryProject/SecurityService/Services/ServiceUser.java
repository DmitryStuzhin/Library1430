package ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.Services;

import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.testRestApi.project.RESTApiLIbraryProject.DTO.GetUserInfo;
import ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.DTO.RegistrationUserDto;
import ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.ModelsSecurity.User;
import ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.SecurityRep.UserRepository;
import ru.testRestApi.project.RESTApiLIbraryProject.models.Book;
import ru.testRestApi.project.RESTApiLIbraryProject.models.Order;
import ru.testRestApi.project.RESTApiLIbraryProject.services.OrderService;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
//@RequiredArgsConstructor
public class ServiceUser implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final OrderService orderService;

    public ServiceUser(UserRepository userRepository, RoleService roleService, PasswordEncoder passwordEncoder, @Lazy OrderService orderService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.passwordEncoder = passwordEncoder;
        this.orderService = orderService;
    }

    public Optional<User> findUserByUsername(String username){
        return userRepository.findUserByUsername(username);
    }
    public Book findBookByUser_Id(int id) { return userRepository.findBookByUser_Id(id); }
    public Optional<User> findUserById(int id) { return  userRepository.findById(id); }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                user.getRoles().stream()
                        .map(role -> new SimpleGrantedAuthority(role.getName()))
                        .collect(Collectors.toList())
        );
    }

    public User createNameUser(RegistrationUserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setClass_number(userDto.getClass_number());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setRoles(List.of(roleService.getRole()));
        return userRepository.save(user);
    }

    public ResponseEntity<?> getUserInfo(){
        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null){
            User user = findUserByUsername(authentication.getName()).orElseThrow(
                    () -> new UsernameNotFoundException("User not found")
            );
            Book userBook = user
                    .getBooksFromOrder()
                    .stream()
                    .findFirst()
                    .orElse(null);
            Order order = orderService.findOrderByUser_Id(user.getId()).orElse(null);
            if (order == null) {
                return ResponseEntity.ok(new GetUserInfo(
                        user.getId(),
                        user.getUsername(),
                        user.getPassword(),
                        user.getClass_number(),
                        userBook
                ));
            }else {
                return ResponseEntity.ok(new GetUserInfo(
                        user.getId(),
                        user.getUsername(),
                        user.getPassword(),
                        user.getClass_number(),
                        userBook,
                        order.getOrders_number()
                ));
            }
        }
        else return ResponseEntity.badRequest().body("User not found");
    }
}
