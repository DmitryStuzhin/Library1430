package ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.SecurityRep.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.ModelsSecurity.User;
import ru.testRestApi.project.RESTApiLIbraryProject.SecurityService.DTO.RegistrationUserDto;
import ru.testRestApi.project.RESTApiLIbraryProject.models.Book;

@Service
@RequiredArgsConstructor
public class ServiceUser implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;

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
}
