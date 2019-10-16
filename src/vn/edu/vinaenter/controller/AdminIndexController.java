package vn.edu.vinaenter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vn.edu.vinaenter.model.dao.CategoryDAO;
import vn.edu.vinaenter.model.dao.ContactDAO;
import vn.edu.vinaenter.model.dao.LandDAO;
import vn.edu.vinaenter.model.dao.SlideDAO;
import vn.edu.vinaenter.model.dao.UserDAO;

@Controller
@RequestMapping("admin")
public class AdminIndexController {
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private LandDAO landDAO;
	
	@Autowired
	private ContactDAO contactDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private SlideDAO slideDAO;
	
	@GetMapping("")
	public String index(ModelMap modelMap) {
		int numberOfCategories = categoryDAO.countItems();
		modelMap.addAttribute("numberOfCategories", numberOfCategories);
		
		int numberOfLands = landDAO.countItems();
		modelMap.addAttribute("numberOfLands", numberOfLands);
		
		int numberOfContacts = contactDAO.countItems();
		modelMap.addAttribute("numberOfContacts", numberOfContacts);
		
		int numberOfUsers = userDAO.countItems();
		modelMap.addAttribute("numberOfUsers", numberOfUsers);
		
		int numberOfSlides = slideDAO.countItems();
		modelMap.addAttribute("numberOfSlides", numberOfSlides);
		
		return "cland.admin.index.index";
	}

}
