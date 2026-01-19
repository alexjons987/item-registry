package se.alexjons987.item_registry.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import se.alexjons987.item_registry.dto.UserAccountResponseDTO;
import se.alexjons987.item_registry.entity.UserAccount;
import se.alexjons987.item_registry.mapper.UserAccountMapper;
import se.alexjons987.item_registry.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserAccountMapper userAccountMapper;

    public UserAccountResponseDTO getCurrentUser(Authentication authentication) {
        UserAccount user = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return userAccountMapper.toResponseDTO(user);
    }
}
