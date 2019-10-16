package vn.edu.vinaenter.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import vn.edu.vinaenter.model.bean.User;
import vn.edu.vinaenter.model.dao.UserDAO;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	private UserDAO userDAO;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("check: MyUserDetailsService.loadUserByUsername()");
		
		User user = userDAO.getItemByUsernameEnable(username); //username and enable
		if (user == null) {
			throw new UsernameNotFoundException("Username not found");
		}
		
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();  
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + user.getRole().getName());
        authorities.add(authority);
        
        MyUserDetails myUserDetail = new MyUserDetails(username, user.getPassword(), user.getFullname(), user.getRole(), authorities);  
        BeanUtils.copyProperties(user, myUserDetail);
        return myUserDetail;
	}

}
