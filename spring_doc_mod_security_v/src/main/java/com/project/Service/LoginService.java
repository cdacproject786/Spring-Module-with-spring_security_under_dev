package com.project.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.IService.ILoginService;
import com.project.Repository.AdminPrimaryRepository;
import com.project.Repository.DoctorPrimaryRepository;
import com.project.entity.AdminPrimary;
import com.project.entity.DoctorPrimary;
import com.project.entity.proxy.LoginProxy;
import com.project.userdetails.CustomUserDetails;

@Service
public class LoginService implements ILoginService {
	
	@Autowired
	private DoctorPrimaryRepository doctorPrimaryRepository;
	
	@Autowired
	private AdminPrimaryRepository adminPrimaryRepository;
	
	@Autowired
	private PasswordEncoderService passwordEncoderServie;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public DoctorPrimary ValidateDoctorLogin(LoginProxy proxy)
	{
		DoctorPrimary primary = this.doctorPrimaryRepository.findByemail(proxy.getEmail());
		if(primary!= null)
		{
			if(primary.getPwd().equals(this.passwordEncoderServie.encodePassword(proxy.getPwd())));
			return primary;
			
		}
		
		return null;
		
	}

	public boolean ValidateAdmin(AdminPrimary adminPrimary)
	{
		AdminPrimary primary = this.adminPrimaryRepository.findByEmailAndPwd(adminPrimary.getEmail(), passwordEncoderServie.encodePassword(adminPrimary.getPwd()));
		if(primary == null)
			return false;
		else return true;
	}
	

	/*
	 * @Override public UserDetails loadUserByUsername(String email) throws
	 * UsernameNotFoundException { DoctorPrimary doctor =
	 * doctorPrimaryRepository.findByemail(email); if(doctor == null) throw new
	 * UsernameNotFoundException("User Not found");
	 * 
	 * return new CustomUserDetails(doctor); }
	 */
}
