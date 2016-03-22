package com.excilys.cdb.services;

import com.excilys.cdb.daos.repositories.UserRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
public class UserService implements UserDetailsService {

  // Logger
  static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

  @Autowired
  UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    LOGGER.info("Service: UserService: loadUserByUsername: username:{}", username);

    com.excilys.cdb.models.User u = userRepository.findByUsername(username);

    if (u == null) {
      throw new UsernameNotFoundException("user name not found");
    }

    List<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority(u.getRole().getName()));

    LOGGER.info("Service: Userservice: LoadUserByUsername: user found:{}", u);

    return buildUserForAuthentication(u, authorities);

  }

  public long createUser(com.excilys.cdb.models.User user) {

    // Add user to DB
    return userRepository.save(user).getId();
  }

  private User buildUserForAuthentication(com.excilys.cdb.models.User user,
      List<GrantedAuthority> authorities) {
    return new User(user.getUsername(), user.getPassword(), authorities);
  }

}
