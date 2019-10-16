package vn.edu.vinaenter.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vn.edu.vinaenter.constant.Defines;
import vn.edu.vinaenter.constant.MessageEnum;
import vn.edu.vinaenter.model.bean.Role;
import vn.edu.vinaenter.model.bean.User;
import vn.edu.vinaenter.model.dao.RoleDAO;
import vn.edu.vinaenter.model.dao.UserDAO;
import vn.edu.vinaenter.util.PaginationUtil;
import vn.edu.vinaenter.validator.UserValidator;


@Controller
@RequestMapping("admin/user")
public class AdminUserController {

	@Autowired
	private UserDAO userDAO;
	@Autowired
	private RoleDAO roleDAO;
	
	@Autowired
	private BCryptPasswordEncoder bCrypt; 
	
	/*s:index*/
	@GetMapping(path = { "", "/page-{page}" })
	public String index(@PathVariable(name = "page", required = false) Integer page, ModelMap modelMap) {
		int numberOfItems = userDAO.countItems();
		int numberOfPages = (int) Math.ceil((float) numberOfItems / Defines.ROW_COUNT);
		if (page == null) {
			page = 1;
		} else if (page < 1) {
			return "redirect:/admin/user/page-1";
		} else if (page > numberOfPages) {
			return "redirect:/admin/user/page-" + numberOfPages;
		}
		int offset = (page - 1) * Defines.ROW_COUNT;
		
		List<Integer> paginations = PaginationUtil.show(numberOfPages, 7, page);
		
		modelMap.addAttribute("paginations", paginations);
		modelMap.addAttribute("page", page);
		modelMap.addAttribute("numberOfPages", numberOfPages);
		modelMap.addAttribute("numberOfItems", numberOfItems);
		List<User> listUsers = userDAO.getItemsPagination(offset);
		modelMap.addAttribute("listUsers", listUsers);
		return "cland.admin.user.index";
	}
	/*e:index*/
	
	/*start add*/
	@GetMapping("add")
	public String add(ModelMap modelMap) {
		List<Role> listRoles = roleDAO.getItems();
		modelMap.addAttribute("listRoles", listRoles);
		return "cland.admin.user.add";
	}
	
	@PostMapping("add")
	public String add(@Valid @ModelAttribute("objUser") User objUser, BindingResult errors,
			@RequestParam("roleId") int roleId, 
			ModelMap modelMap, RedirectAttributes ra, UserValidator validator) {
		
		objUser.setRole(new Role(roleId, null));
		validator.validate(objUser, errors, userDAO);
		
		if (errors.hasErrors()) {
			List<Role> listRoles = roleDAO.getItems();
			modelMap.addAttribute("listRoles", listRoles);
			return "cland.admin.user.add";
		}
		
		objUser.setEnable(1);
		objUser.setPassword(bCrypt.encode(objUser.getPassword()));
		
		if (userDAO.addItem(objUser) > 0) {
			ra.addFlashAttribute("eMsg", MessageEnum.MSG_ADD_SUCCESS);
			return "redirect:/admin/user";
		} else {
			ra.addFlashAttribute("eMsg", MessageEnum.MSG_ERROR);
			return "cland.admin.user.add";
		}
	}
	
	
	/*end add*/
	
	/*start delete*/
	@GetMapping("del/{id}")
	public String del(@PathVariable("id") int id, RedirectAttributes ra) {
		
		/*tồn tại một user có username là user và bất biến giữa dòng đời*/
		User user = userDAO.getItem(id);
		if (user.getUsername().equalsIgnoreCase("admin")) {
			ra.addFlashAttribute("eMsg", MessageEnum.MSG_DELETE_DENIED);
			return "redirect:/admin/user";
		}
		
		if (userDAO.delItem(id) > 0) {
			ra.addFlashAttribute("eMsg", MessageEnum.MSG_DELETE_SUCCESS);
		} else {
			ra.addFlashAttribute("eMsg", MessageEnum.MSG_ERROR);
		}
		return "redirect:/admin/user";
	}
	/*end delete*/
	
	/*start edit*/
	@GetMapping("edit/{id}-{page}")
	public String edit(@PathVariable("id") int id, @PathVariable("page") int page, ModelMap modelMap) {
		User objUser = userDAO.getItem(id);
		modelMap.addAttribute("objUser", objUser);
		modelMap.addAttribute("page", page);
		List<Role> listRoles = roleDAO.getItems();
		modelMap.addAttribute("listRoles", listRoles);
		return "cland.admin.user.edit";
	}
	
	@PostMapping("edit/{id}-{page}")
	public String edit(@Valid @ModelAttribute("objUser") User objUser, BindingResult errors,
			@PathVariable("id") int id, @PathVariable("page") int page, @RequestParam("roleId") int roleId,
			RedirectAttributes ra, ModelMap modelMap) {
		
		User oldUser = userDAO.getItem(id);
		objUser.setRole(new Role(roleId, null));
		
		/*s:validation*/
		if (!"".equals(objUser.getPassword().trim()) && bCrypt.matches(objUser.getPassword(), oldUser.getPassword())) {
			errors.rejectValue("password", "messageMatchesPassword");/*trùng password cũ*/
		} else {
			/*encode into bCrypt and update database*/
			objUser.setPassword(bCrypt.encode(objUser.getPassword()));
		}
		
		if (errors.hasErrors()) {
			List<Role> listRoles = roleDAO.getItems();
			modelMap.addAttribute("listRoles", listRoles);
			return "cland.admin.user.edit";
		}
		/*e:validation*/
		
		
		if (userDAO.editItem(objUser) > 0) {
			ra.addFlashAttribute("eMsg", MessageEnum.MSG_UPDATE_SUCCESS);
			ra.addFlashAttribute("objUser", objUser);
			return "redirect:/admin/user/page-" + page;
		} else {
			ra.addFlashAttribute("eMsg", MessageEnum.MSG_ERROR);
			return "cland.admin.user.edit";
		}
	}
	/*end edit*/
	
	@GetMapping({"search", "search-{name}-{page}"})
	public String search(
			@RequestParam(name="name", required=false) String searchParam,   //lấy từ doget của form tìm kiếm
			@PathVariable(name="name", required=false) String searchPathVar, //lấy từ đường dẫn
			@PathVariable(name="page", required=false) Integer page, ModelMap modelMap) {
		
		String searchString = null;
		if (searchParam == null) {
			searchString = searchPathVar;
		} else {
			searchString = searchParam;
		}
		int numberOfItems =userDAO.countSearchItems(searchString);
		int numberOfPages = (int) Math.ceil((float) numberOfItems / Defines.ROW_COUNT);
		
		if (page == null || page < 1) {
			page = 1;
		} else if (page > numberOfPages) {
			page = numberOfPages;
		}
		
		int offset = (page - 1) * Defines.ROW_COUNT;
		
		List<Integer> paginations = PaginationUtil.show(numberOfPages, 7, page);
		
		modelMap.addAttribute("paginations", paginations);
		modelMap.addAttribute("page", page);
		modelMap.addAttribute("numberOfPages", numberOfPages);
		modelMap.addAttribute("numberOfItems", numberOfItems);
		modelMap.addAttribute("searchString", searchString);
		
		List<User> listUsers = userDAO.getSearchItemsPagination(searchString, offset);
		modelMap.addAttribute("listUsers", listUsers);
		return "cland.admin.user.search";
	}
	
	@ResponseBody
	@PostMapping("ajaxHasUser")
	public String ajaxHasUser(@RequestParam("ausername") String username) {
		if (userDAO.hasUser(username)) {
			return "1";
		} else {
			return "0";
		}
	}
	
	/*ajax enable user*/
	@ResponseBody
	@PostMapping("enableUser")
	public String enableUser(@RequestParam("aid") int id, @RequestParam("aenable") int enable) {
		enable = enable == 1 ? 0 : 1;
		if (userDAO.enableUser(id, enable) > 0) {
			return "1";
		} else {
			return "0";
		}
	}
	
}
