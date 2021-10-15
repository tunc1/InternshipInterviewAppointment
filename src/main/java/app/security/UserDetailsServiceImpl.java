package app.security;

import app.repository.UserRepository;
import app.service.StudentService;
import app.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    @Autowired
    private UserRepository userRepository;
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        if(userRepository.existsByUsername(username))
            return userRepository.findByUsername(username);
        else
            throw new UsernameNotFoundException("No user found by this username: "+username);
    }
}