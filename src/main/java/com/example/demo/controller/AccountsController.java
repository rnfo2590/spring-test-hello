package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Usert;
import com.example.demo.model.Accounts;
import com.example.demo.repository.UsertRepository;

import jakarta.servlet.http.HttpSession;

@Controller

public class AccountsController {
	@Autowired
	UsertRepository usertRepository;
	
	@Autowired
	HttpSession session;
	
	@Autowired
	Accounts accounts;
	
	@GetMapping({"/login", "/logout"})
	public String in() {
		session.invalidate();
		
		return "/login1";
		
	}
	
	@PostMapping("/login1")
		public String login(Model model,
				@RequestParam("id")Integer id,
				@RequestParam("name")String name
				) {
		
		//一致するかのチェック
		Usert usert=null;
		
		Optional<Usert> record=usertRepository.findByNameAndId(name, id);
		
		if(record.isEmpty()==false) {
			usert=record.get();
			
		}
		
		//一致するidがなかった場合
		if(usert==null) {
			
			model.addAttribute("error", "ログインできません");
			return "/login1";
		}
	
		
		String name1=usert.getName();
		accounts.setName(name1);
		

		return "redirect:/items";
			
		}
	

}
