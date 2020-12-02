package com.myproject.serviceimpl;

import com.myproject.daoapi.HistoryDao;
import com.myproject.daoapi.ScooterDao;
import com.myproject.daoapi.UserDao;
import com.myproject.domain.entity.History;
import com.myproject.domain.entity.Scooter;
import com.myproject.domain.entity.User;
import com.myproject.domain.enums.OfferType;
import com.myproject.dto.dto.UserDto;
import com.myproject.dto.mapper.UserMapper;
import com.myproject.serviceapi.UserServiceApi;
import com.myproject.serviceimpl.exceptions.UserServiceException;
import org.springframework.beans.factory.annotation.Autowired;
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

    private Double offerCost = 1.0;
    private Double offerSubscription = offerCost * 5;
//    private Double discount = 0.9;

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
        User newUser = userMapper.toEntity(entity);
        User user = userDao.findUserById(id);
        user.setId(id);
        user.setUserName(newUser.getUserName());
        user.setEmail(newUser.getEmail());
        user.setPassword(newUser.getPassword());
        if (user.getRole().equals("USER")) {
            user.setRole("USER");
        } else user.setRole(newUser.getRole());
        return userMapper.toDto(userDao.updateUser(user));
    }


    @Override
    public UserDto findById(int id) {
        User user = userDao.findUserById(id);
        if (user == null) {
            throw new UserServiceException("Error");
        } else {
            return userMapper.toDto(userDao.findUserById(id));
        }
    }

    @Transactional
    @Override
    public void startTrip(int id, int scooterId, String offerType, String discount) {
//        User user = userDao.findUserById(id);
        Scooter scooter = scooterDao.findScooterById(scooterId);
        History history = new History();
        Double scooterDiscount = history.getDiscount();
        if (offerType.equals(OfferType.SUBSCRIPTION.toString())) {
            if (discount.equals("discount")) {
                offerCost = scooter.getCost() * 10 * scooterDiscount;
            } else {
                offerCost = scooter.getCost() * 10;
            }
        }
        offerCost = scooter.getCost() * 10 * scooterDiscount;

        if (offerType.equals(OfferType.ONCE_TIME.toString())) {
            if (discount.equals("discount")) {
                offerCost = scooter.getCost() * scooterDiscount;
            } else {
                offerCost = scooter.getCost();
            }
        }
        history.setUserId(id);
        history.setMileade(0.0);
        history.setOfferCost(0.0);
        history.setOfferType(offerType);
        history.setFinishLocationId(0);
        history.setScooterId(scooterId);
        history.setStartTime(LocalDateTime.now());
        history.setFinishTime(LocalDateTime.now());
        history.setStartLocationId(scooter.getRentalPointId());
        historyDao.saveHistory(history);
    }

    @Transactional
    @Override
    public void finishTrip(int id, int finishLocationId, Double mileage) {
//        User user = userDao.findUserById(id);
        History history = historyDao.findHistoryById(id);
        history.setUserId(id);
        history.setMileade(mileage);
        history.setOfferCost(offerCost);
        history.setOfferType(history.getOfferType());
        history.setStartLocationId(history.getStartLocationId());
        history.setFinishLocationId(finishLocationId);
        history.setStartTime(history.getStartTime());
        history.setFinishTime(LocalDateTime.now());
        history.setScooterId(history.getScooterId());
        historyDao.updateHistory(history);
        Scooter scooter = scooterDao.findScooterById(history.getScooterId());
        scooter.setRentalPointId(finishLocationId);
        scooterDao.updateScooter(scooter);
    }

    @Transactional
    @Override
    public void delete(int id) {
        ArrayList<User> users;
        if (userDao.findAllUsers() != null) {
            users = userDao.findAllUsers();
            for (User user : users) {
                if (user.getId() == id) {
                    userDao.deleteUser(user);
                }
            }
        } else throw new UserServiceException("User service throws null");
    }

    @Transactional
    @Override
    public UserDto save(UserDto entity) {
        User user = userMapper.toEntity(entity);
        if (user != null) {
            return userMapper.toDto(userDao.saveUser(user));
        } else {
            throw new UserServiceException("User service throws null");
        }
    }

    public UserDto getUserByName(String name) {
        if (userDao.findAllUsers() != null) {
            ArrayList<User> users = userDao.findAllUsers();
            ArrayList<UserDto> userDtos = null;
            for (User user : users) {
                if (user.getUserName().equals(name)) {
                    userDtos.add(userMapper.toDto(user));
                }
            }
            return userDtos.get(0);
        } else throw new UserServiceException("User service throws null");
    }

}
