package com.cme.service.User;

import com.cme.entity.User.UserDetails;

import java.util.List;
import java.util.Map;
public interface UserInterface {
    Map<String, Object> registerUser(UserDetails user);
    Map<String, String> validateUser(String employeeId, String password);
    String redirectUserByEmail(String email);
    List<UserDetails> searchUsersByName(String name);
}
