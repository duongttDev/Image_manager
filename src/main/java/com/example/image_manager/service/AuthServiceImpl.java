package com.example.image_manager.service;

import com.example.image_manager.dto.UserDto;
import com.example.image_manager.entity.User;
import com.example.image_manager.exception.BusinessException;
import com.example.image_manager.repository.UserRepository;
import com.example.image_manager.request.LoginRequest;
import com.example.image_manager.response.EntityCustomResponse;
import com.example.image_manager.security.jwt.JwtTokenProvider;
import com.example.image_manager.security.jwt.UserDetailsImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    UserRepository userRepository;

    @Value("${app-jwt-expiration-milliseconds}")
    private long jwtExpirationDate;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Override
    public EntityCustomResponse login(LoginRequest loginRequest) {
        UserDto userDto = new UserDto();
        try {
            User user = userRepository.findByUsername(loginRequest.getUsername()).orElseThrow(() -> new Exception("Không tìm thấy username"));
            if (passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {//kiểm tra mk xem đã được mã hóa đúng DB chưa
                Authentication authentication = authenticationManager
                        .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                                loginRequest.getPassword()));//tạo ra Authentication

                //đặt thông tin xác thực của người dùng vào (giống session)
                SecurityContextHolder.getContext().setAuthentication(authentication);

                //tạo jwt để sử dụng
                String jwt = jwtTokenProvider.generateToken(authentication);

                //lấy thử thông tin người dùng từ biến xác thực
                UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

                List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority())
                        .collect(Collectors.toList());
                Date currentDate = new Date();
                Date expireDate = new Date(currentDate.getTime() + jwtExpirationDate);
                userDto = modelMapper.map(user, UserDto.class);
                userDto.setToken(jwt);
                userDto.setExpireDateToken(expireDate);

            } else {
                throw new BusinessException(500, "Mật khẩu không đúng");
            }

        } catch (
                BusinessException businessException) {
            return new EntityCustomResponse(0, businessException.getMessage(), businessException.getStatusCode(), List.of());
        } catch (Exception exception) {
            return new EntityCustomResponse(0, "Error system ", 500, exception.getMessage());
        }
        return new EntityCustomResponse(1, "Đăng nhập thành công", 200, userDto);
    }
}
