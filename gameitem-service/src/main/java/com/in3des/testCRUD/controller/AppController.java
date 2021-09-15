package com.in3des.testCRUD.controller;

import java.util.List;

import com.in3des.testCRUD.model.GameItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.in3des.testCRUD.services.GameItemServices;

@Controller
public class AppController {

	@Autowired
	GameItemServices service;
	
	@RequestMapping("/")
	public String viewHomePage(Model model) {
		List<GameItem> listGameItem = service.listAll();
		model.addAttribute("listGameItem", listGameItem);
		return "index";
	}
	
	@RequestMapping("/new")
	public String newGameItemPage(Model model) {
		GameItem gameItem =new GameItem();
		model.addAttribute(gameItem);
		return "new_gameitem";
	}
	
	@RequestMapping(value="/save", method=RequestMethod.POST)
	public String saveGameItem(@ModelAttribute ("student") GameItem gameItem) {
		service.save(gameItem);
		return "redirect:/";
	}
	@RequestMapping("edit/{id}")
	public ModelAndView showEditGameItemPage(@PathVariable (name="id") Long id) {
		ModelAndView mav=new ModelAndView("edit_gameitem");
		GameItem gameItem =service.get(id);
		mav.addObject("gameItem", gameItem);
		return mav;
	}
	@RequestMapping("delete/{id}")
	public String deleteGameItemPage(@PathVariable (name="id") Long id) {
		service.delete(id);
		return "redirect:/";
	}
	
}
