package com.example.cineflix.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/home")
public class HomeController {
	
	@GetMapping("")
	public ModelAndView home() {
		ModelAndView andView = new  ModelAndView("home");
		
		int soA = 10;
		int soB = 20;
		int soC = 20;
		
		int tong = tinhTong(soA, soB);
		
		andView.addObject("ketqua", tong);	
		return andView;
	}
	
	public int tinhTong(int soA, int soB) {
		int tong = soA + soB;
		return tong;
	}
}
