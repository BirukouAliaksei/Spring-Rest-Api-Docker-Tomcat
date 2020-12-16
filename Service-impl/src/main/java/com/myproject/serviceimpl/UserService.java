package com.myproject.serviceimpl;

import com.myproject.daoapi.HistoryDao;
import com.myproject.daoapi.ScooterDao;
import com.myproject.daoapi.UserDao;
import com.myproject.domain.entity.History;
import com.myproject.domain.entity.Scooter;
import com.myproject.domain.entity.User;
import com.myproject.domain.enums.OfferType;
import com.myproject.domain.enums.Role;
import com.myproject.dto.dto.HistoryDto;
import com.myproject.dto.dto.UserDto;
import com.myproject.dto.mapper.HistoryMapper;
import com.myproject.dto.mapper.UserMapper;
import com.myproject.serviceapi.UserServiceApi;
import com.myproject.serviceimpl.exceptions.ServiceValidationException;
import com.myproject.serviceimpl.exceptions.UserServiceException;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Log4j
@Service
@NoArgsConstructor
public class UserService implements UserServiceApi {

    @Autowired
    private UserDao userDao;

    @Autowired
    private HistoryDao historyDao;

    @Autowired
    private ScooterDao scooterDao;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private HistoryMapper historyMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private Double offerCost = 1.0;
    private Double tripDiscount = 0.9;

    @Transactional
    @Override
    public ArrayList<UserDto> findAll() {
        ArrayList<UserDto> userDtos = new ArrayList<>();
        if (userDao.findAllUsers() != null) {
            for (User user : userDao.findAllUsers()) {
                userDtos.add(userMapper.toDto(user));
            }
            return userDtos;
        } else throw new UserServiceException("User service throws null");
    }

    @Transactional
    @Override
    public UserDto update(UserDto entity, int id) {
        User user = findById(id);
        if (entity.getEmail() == null || entity.getPassword() == null
                || entity.getUsername() == null || entity.getRole() == null) {
            throw new UserServiceException("User service throws null");
        } else {
            entity.setId(user.getId());
            entity.setPassword(passwordEncoder.encode(entity.getPassword()));
            User newUser = userMapper.toEntity(entity);
            return userMapper.toDto(userDao.updateUser(newUser));
        }
    }

    @Override
    public User findById(int id) {
        User user = userDao.findUserById(id);
        if (user != null) {
            return user;
        } else {
            throw new UserServiceException("Error");
        }
    }

    @Override
    public ArrayList<UserDto> findByIdWithHistory(int id) {
        User user = userDao.findUserById(id);
        if (user != null) {
            ArrayList<UserDto> userDtos = new ArrayList<>();
            userDtos.add(userMapper.toDto(user));
            return userDtos;
        } else {
            throw new UserServiceException("Error");
        }
    }

    @Transactional
    @Override
    public HistoryDto startTrip(int id, HistoryDto historyDto) {
        if (id == 0 || historyDto.getOfferType() == null
                || historyDto.getScooterId() == 0) {
            throw new ServiceValidationException();
        } else {
            History history = historyMapper.toEntity(historyDto);
            Scooter scooter = scooterDao.findScooterById(historyDto.getScooterId());

            if (historyDto.getDiscount() != null && historyDto.getDiscount().equals(History.discount)){
                if (historyDto.getOfferType().equals(OfferType.ONCE_TIME.toString())) {
                    offerCost = scooter.getCost() * tripDiscount;
                }
                if (history.getOfferType().equals(OfferType.SUBSCRIPTION.toString())){
                    offerCost = scooter.getCost() * 10 * tripDiscount;
                }
            }else {
                if (historyDto.getOfferType().equals(OfferType.ONCE_TIME.toString())) {
                    offerCost = scooter.getCost();
                }
                if (history.getOfferType().equals(OfferType.SUBSCRIPTION.toString())){
                    offerCost = scooter.getCost() * 10;
                }
            }

            history.setUserId(id);
            history.setStartLocationId(scooter.getRentalPointId());
            history.setScooterId(history.getScooterId());
            history.setStartTime(LocalDateTime.now());
            history.setFinishTime(LocalDateTime.now());
            return historyMapper.toDto(historyDao.saveHistory(history));
        }
    }

    @Transactional
    @Override
    public HistoryDto finishTrip(int id, HistoryDto historyDto, int historyId) {
        if (id == 0 || historyDto.getFinishLocationId() == 0
                || historyDto.getMileage() == null || historyId == 0) {
            throw new ServiceValidationException();
        } else {
            History history = historyDao.findHistoryById(historyId);
            history.setOfferCost(offerCost);
            history.setMileage(historyDto.getMileage());
            history.setFinishTime(LocalDateTime.now());
            history.setFinishLocationId(historyDto.getFinishLocationId());

            Scooter scooter = scooterDao.findScooterById(history.getScooterId());
            scooter.setRentalPointId(historyDto.getFinishLocationId());
            scooterDao.updateScooter(scooter);
            return historyMapper.toDto(historyDao.updateHistory(history));
        }
    }

    @Transactional
    public User findByLogin(String login) {
        if (userDao.findByLogin(login) == null) {
            throw new ServiceValidationException();
        }
        return userDao.findByLogin(login);
    }

    @Override
    @Transactional
    public User findByLoginAndPassword(String login, String password) {
        User user;
        if (userDao.findByLogin(login) == null) {
            throw new UsernameNotFoundException("Wrong username");
        } else {
            user = userDao.findByLogin(login);
        }
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            } else throw new UsernameNotFoundException("User not found");
        }
        return null;
    }

    @Transactional
    @Override
    public String delete(int id) {
        if (userDao.findAllUsers() == null) {
            throw new UserServiceException("Users not found");
        } else {
            for (User user : userDao.findAllUsers()) {
                if (user.getId() == id) {
                    userDao.deleteUser(user);
                    return "User deleted";
                }
            }
        }
        return "User already deleted";
    }

    @Transactional
    @Override
    public UserDto save(UserDto entity) {
        if (entity.getEmail() == null || entity.getPassword() == null
                || entity.getUsername() == null || entity.getRole() == null) {
            throw new UserServiceException("User service throws null");
        } else {
            User user = userMapper.toEntity(entity);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userMapper.toDto(userDao.saveUser(user));
        }
    }

    @Transactional
    @Override
    public UserDto userRegistration(UserDto userDto) {
        if (userDto.getUsername() == null || userDto.getPassword() == null || userDto.getEmail() == null) {
            throw new UserServiceException("User service throws null");
        } else {
            User user = userMapper.toEntity(userDto);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole(Role.USER);
            return userMapper.toDto(userDao.saveUser(user));
        }
    }
}
