package app.security;

import app.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService
{
    private UserRepository userRepository;
    public UserDetailsServiceImpl(UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        if(userRepository.existsByUsername(username))
            return userRepository.findByUsername(username);
        else
            throw new UsernameNotFoundException("No user found by this username: "+username);
    }
}