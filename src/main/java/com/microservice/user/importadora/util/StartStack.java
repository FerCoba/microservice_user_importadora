package com.microservice.user.importadora.util;

import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.microservice.user.importadora.service.UserService;

@Controller
public class StartStack extends TimerTask {

	@Autowired
	UserService userService;
	
	@Override
	public void run() {
		UserService userSe = new UserService();
		System.out.println( " userSe " + userSe);
//		UserService userService = new UserService();
		userService.obtainInformationOfAllUser();
		try {
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		

	}

}
