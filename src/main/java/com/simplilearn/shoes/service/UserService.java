package com.simplilearn.shoes.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.simplilearn.shoes.model.User;
import com.simplilearn.shoes.repository.UserRepository;

@Service
public class UserService {
@Autowired
UserRepository userRepo;
public List<User> getAllUser(){
	return userRepo.findAll();
}
public void removeUsertById(long id) {
	userRepo.deleteById(id);
}
public Optional<User>getUserById(long id){
	return userRepo.findById(id);
}

}
