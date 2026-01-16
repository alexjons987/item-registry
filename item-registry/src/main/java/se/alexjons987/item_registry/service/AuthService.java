package se.alexjons987.item_registry.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.alexjons987.item_registry.dto.UserAccountRequestDTO;
import se.alexjons987.item_registry.dto.UserAccountResponseDTO;
import se.alexjons987.item_registry.entity.UserAccount;
import se.alexjons987.item_registry.entity.UserRole;
import se.alexjons987.item_registry.exception.UsernameAlreadyTakenException;
import se.alexjons987.item_registry.mapper.UserAccountMapper;
import se.alexjons987.item_registry.repository.RoleRepository;
import se.alexjons987.item_registry.repository.UserRepository;

import java.util.Optional;
import java.util.Set;

@Service
public class AuthService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserAccountMapper userAccountMapper;

    public UserAccountResponseDTO registerNewUser(UserAccountRequestDTO userAccReqDTO) {
        Optional<UserAccount> existingUser = userRepository.findByUsername(userAccReqDTO.getUsername());

        if (existingUser.isPresent()) {
            throw new UsernameAlreadyTakenException("Username is already in use");
        }

        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(userAccReqDTO.getUsername());
        userAccount.setPassword(passwordEncoder.encode(userAccReqDTO.getPassword()));
        userAccount.setValue(0L);

        UserRole userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new IllegalStateException("Role 'USER' was not found"));

        userAccount.setRoles(Set.of(userRole));

        return userAccountMapper.toResponseDTO(userRepository.save(userAccount));
    }
}
