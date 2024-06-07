package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entity.Item;
import com.example.demo.model.Account;
import com.example.demo.repository.ItemRepository;

import jakarta.servlet.http.HttpSession;
import lombok.Data;

@Data@Controller
public class ItemController {

	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private Account account;
	
	@GetMapping({"/login","/logout"})
	public String in() {
		session.invalidate();
		return "/login";
	}
	
	@PostMapping("/login")
	public String login(Model model,
			@RequestParam("logname")String logname
			) {
		
		account.setLogname(logname);
		
//		List<Item>list=itemRepository.findAll();
//		model.addAttribute("list", list);
		
		return "redirect:/items";
		
	}

	

	@GetMapping("/items")
	public String index(Model model,
			@RequestParam(name="maxPrice", required=false)Integer maxPrice,
			@RequestParam(name="prod", required=false)String prod
			) {

		List<Item> list = null;
		
		if(maxPrice==null&&prod==null) {
			list=itemRepository.findAll();
		}
		if(maxPrice!=null&&prod==null) {
			list=itemRepository.findByPriceLessThanEqual(maxPrice);
		}
		if(maxPrice==null&&prod!=null) {
			list=itemRepository.findByNameLike("%" +prod + "%");
		}
		if(maxPrice!=null&&prod!=null) {
			list=itemRepository.findByNameLikeAndPriceLessThanEqual("%" +prod + "%", maxPrice);
		}

		model.addAttribute("list", list);
		model.addAttribute("prod", prod);		
		model.addAttribute("maxPrice", maxPrice);

		return "/item";
	}

	@GetMapping("/items/add")
	public String create() {

		return "/addItem";
	}

	@PostMapping("/items/add")
	public String store(Model model,
			@RequestParam("categoryId") Integer categoryId,
			@RequestParam("name") String name,
			@RequestParam("price") Integer price

	) {
		Item item = new Item(categoryId, name, price);
		itemRepository.save(item);

		model.addAttribute("item", item);

		return "redirect:/items";
	}

	@GetMapping("/items/{id}/edit")
	public String edit(Model model,
			@PathVariable("id") Integer id

	) {
		Item item = null;

		Optional<Item> record = itemRepository.findById(id);
		if (record.isEmpty() == false) {
			item = record.get();
		}

		model.addAttribute("item", item);

		return "/editItem";
	}

	@PostMapping("/items/{id}/edit")
	public String update(Model model,
			@PathVariable("id") Integer id,
			@RequestParam("categoryId") Integer categoryId,
			@RequestParam("name") String name,
			@RequestParam("price") Integer price

	) {
		Item item = new Item(id, categoryId, name, price);
		itemRepository.saveAndFlush(item);

		return "redirect:/items";
	}

	@PostMapping("/items/{id}/delete")
	public String delete(Model model,
			@PathVariable("id") Integer id

	) {

		itemRepository.deleteById(id);

		return "redirect:/items";
	}

}
