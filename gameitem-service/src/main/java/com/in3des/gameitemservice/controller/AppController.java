package com.in3des.gameitemservice.controller;

import java.util.List;

import com.in3des.gameitemservice.model.GameItem;
import com.in3des.gameitemservice.model.GameItemList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.ModelAndView;

import com.in3des.gameitemservice.services.impl.GameItemServiceImpl;

@Controller
public class AppController {

	@Autowired
	private WebClient.Builder webClientBuilder;

	private final GameItemServiceImpl service;

	@Autowired
	public AppController(GameItemServiceImpl service) {
		this.service = service;
	}

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
	public String saveGameItem(@ModelAttribute ("game_item") GameItem gameItem) {
		service.save(gameItem);
		return "redirect:/";
	}
	@RequestMapping("edit/{id}")
	public ModelAndView showEditGameItemPage(@PathVariable (name="id") Long id) {
		ModelAndView mav = new ModelAndView("edit_gameitem");
		GameItem gameItem = service.get(id);
		mav.addObject("gameItem", gameItem);
		return mav;
	}
	@RequestMapping("delete/{id}")
	public String deleteGameItemPage(@PathVariable (name="id") Long id) {
		service.delete(id);
		return "redirect:/";
	}

	@RequestMapping("/checkstatus")
	public String checkGameItemStatus() {

		// не отрабатывает в такой связке, пока не нашел схожего примера
		webClientBuilder.build()
				.get()
//				.uri("http://status-service/status/update")
				.uri("http://localhost:8086/status/update")
				.retrieve()
				.bodyToMono(Void.class);
//				.block();

		return "redirect:/";

	}

}
