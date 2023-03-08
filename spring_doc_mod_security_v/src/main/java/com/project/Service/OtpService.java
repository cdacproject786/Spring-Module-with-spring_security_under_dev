package com.project.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.IService.IOtpService;
import com.project.Repository.DoctorPrimaryRepository;
import com.project.entity.DoctorPrimary;
import com.project.exception.DataDoesntExistException;
import com.project.jdbc.utils.GenerateOtpRepository;
import com.project.jdbc.utils.ValidateOtpRepository;

@Service
public class OtpService implements IOtpService{
	
	@Autowired
	private DoctorPrimaryRepository doctorPrimaryRepository;

	public int generateOtp(String email) throws DataDoesntExistException
	{
		DoctorPrimary doctorExist = doctorPrimaryRepository.findByemail(email);
		if(doctorExist == null)
		throw new DataDoesntExistException("The email doesnt exist in database");
		else
		{
			GenerateOtpRepository.insertAutogenerateOtp();
			return GenerateOtpRepository.getOtp();
		}
		
	}
	
	public boolean validateOtpService(int otp)
	{
		int otpInDb = ValidateOtpRepository.validateOtp();
		
		if(otp == otpInDb)
		return true;
		else
		return false;
	}
}
