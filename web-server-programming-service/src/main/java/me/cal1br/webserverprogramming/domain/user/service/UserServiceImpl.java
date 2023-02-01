package me.cal1br.webserverprogramming.domain.user.service;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import me.cal1br.webserverprogramming.api.user.filter.UserFilter;
import me.cal1br.webserverprogramming.api.user.model.UserDTO;
import me.cal1br.webserverprogramming.auth.jwt.JWTService;
import me.cal1br.webserverprogramming.base.service.BaseServiceImpl;
import me.cal1br.webserverprogramming.domain.user.model.UserEntity;
import me.cal1br.webserverprogramming.domain.user.repository.UserRepository;
import me.cal1br.webserverprogramming.exception.UserAlreadyExists;
import me.cal1br.webserverprogramming.exception.UserNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Optional;

@Service
public class UserServiceImpl extends BaseServiceImpl<UserDTO,
        UserEntity,
        UserFilter,
        UserRepository
        > implements UserService {

    private final JWTService jwtService;
    private final int expirationHours;

    private final LoadingCache<Long, Optional<UserDTO>> userCache;

    @Autowired
    public UserServiceImpl(final UserRepository repository,
                           final ModelMapper modelMapper,
                           final JWTService jwtService,
                           @Value("${auth.user.token.expiration.time_hours:48}") final int expirationHours
    ) {
        super(repository, UserDTO.class, UserEntity.class);
        this.jwtService = jwtService;
        this.expirationHours = expirationHours;
        userCache = CacheBuilder.newBuilder()
                .expireAfterAccess(Duration.ofMinutes(5))
                .maximumSize(100)
                .build(new CacheLoader<Long, Optional<UserDTO>>() {
                    @Override
                    public Optional<UserDTO> load(final Long key) throws Exception {
                        return UserServiceImpl.super.findById(key);
                    }
                });
    }

    @Override
    public Optional<UserDTO> findById(final long id) {
        return userCache.getUnchecked(id);
    }

    @Override
    public String register(final UserDTO dto) {
        Optional<UserEntity> optionalUser = this.getRepository().findByUsername(dto.getUserName());
        if (optionalUser.isPresent()) {
            throw new UserAlreadyExists();
        }
        String hashedPassword = BCrypt.hashpw(dto.getPassword(), BCrypt.gensalt(10));
        dto.setPassword(hashedPassword);
        final UserEntity saved = getRepository().save(this.toEntity(dto));

        return jwtService.sign(saved.getId(), expirationHours);
    }

    @Override
    public String login(final String username, final String password) {
        Optional<UserEntity> optionalUser = this.getRepository().findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new UserNotFoundException("No such user with name: " + username);
        }
        if (BCrypt.checkpw(password, optionalUser.get().getPassword())) {
            return jwtService.sign(optionalUser.get().getId(), expirationHours);
        } else {
            throw new RuntimeException("Incorrect password");
        }
    }
}
