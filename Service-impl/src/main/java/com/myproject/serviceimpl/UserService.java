package com.myproject.serviceimpl;

import com.myproject.daoapi.HistoryDao;
import com.myproject.daoapi.ScooterDao;
import com.myproject.daoapi.UserDao;
import com.myproject.domain.entity.History;
import com.myproject.domain.entity.Scooter;
import com.myproject.domain.entity.User;
import com.myproject.domain.enums.OfferType;
import com.myproject.dto.dto.HistoryDto;
import com.myproject.dto.dto.UserDto;
import com.myproject.dto.mapper.HistoryMapper;
import com.myproject.dto.mapper.UserMapper;
import com.myproject.serviceapi.UserServiceApi;
import com.myproject.serviceimpl.exceptions.UserServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Service
public class UserService implements UserServiceApi {

    public UserService() {
    }

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

//    OAuth2ResourceServerConfigurer.JwtConfigurer jwtConfigurer;


    private Double offerCost = 1.0;
    private Double offerSubscription = offerCost * 5;
    private Double discount = 0.9;

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
        User newUser = userMapper.toEntity(entity);
        return userMapper.toDto(userDao.updateUser(newUser));
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
        History history = historyMapper.toEntity(historyDto);
        Scooter scooter = scooterDao.findScooterById(history.getScooterId());
        String scooterDiscount = history.getDiscount();
        if (history.getOfferType().equals(OfferType.SUBSCRIPTION.toString())) {
            if (history.getDiscount().equals(scooterDiscount)) {
                offerCost = scooter.getCost() * 10 * discount;
            } else {
                offerCost = scooter.getCost() * 10;
            }
        }
        offerCost = scooter.getCost() * 10 * discount;

        if (history.getOfferType().equals(OfferType.ONCE_TIME.toString())) {
            if (history.getDiscount().equals(scooterDiscount)) {
                offerCost = scooter.getCost() * discount;
            } else {
                offerCost = scooter.getCost();
            }
        }
        history.setUserId(id);
        history.setMileage(0.0);
        history.setOfferCost(0.0);
        history.setOfferType(history.getOfferType());
        history.setStartLocationId(scooter.getRentalPointId());
        history.setFinishLocationId(0);
        history.setScooterId(history.getScooterId());
        history.setStartTime(LocalDateTime.now());
        history.setFinishTime(LocalDateTime.now());
        historyDao.saveHistory(history);

        return historyMapper.toDto(historyDao.findHistoryById(history.getId()));
    }

    @Transactional
    @Override
    public HistoryDto finishTrip(int id, HistoryDto historyDto, int historyId) {
        History newHistory = historyMapper.toEntity(historyDto);
        History history = historyDao.findHistoryById(historyId);
        history.setUserId(id);
        history.setMileage(newHistory.getMileage());
        history.setOfferCost(offerCost);
        history.setOfferType(history.getOfferType());
        history.setStartLocationId(history.getStartLocationId());
        history.setFinishLocationId(newHistory.getFinishLocationId());
        history.setStartTime(history.getStartTime());
        history.setFinishTime(LocalDateTime.now());
        history.setScooterId(history.getScooterId());
        Scooter scooter = scooterDao.findScooterById(history.getScooterId());
        scooter.setRentalPointId(newHistory.getFinishLocationId());
        scooterDao.updateScooter(scooter);
        return historyMapper.toDto(historyDao.updateHistory(history));
    }

    @Transactional
    public User findByLogin(String login) {
        return userDao.findByLogin(login);
    }

    @Override
    @Transactional
    public User findByLoginAndPassword(String login, String password) {
        User user = userDao.findByLogin(login);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        //FIXME throw exception
        return user;
    }


    @Transactional
    @Override
    public HttpStatus delete(int id) {
        ArrayList<User> users;
        if (userDao.findAllUsers() != null) {
            users = userDao.findAllUsers();
            for (User user : users) {
                if (user.getId() == id) {
                    userDao.deleteUser(user);
                    return HttpStatus.OK;
                }
            }
        } else throw new UserServiceException("User service throws null");
        return HttpStatus.BAD_REQUEST;
    }

    @Transactional
    @Override
    public UserDto save(UserDto entity) {
        User user = userMapper.toEntity(entity);
        if (user != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userMapper.toDto(userDao.saveUser(user));
        } else {
            throw new UserServiceException("User service throws null");
        }
    }

}
