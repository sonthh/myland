package vn.edu.vinaenter.controller;

import java.io.File;
import java.util.List;

import javax.servlet.ServletContext;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vn.edu.vinaenter.constant.Defines;
import vn.edu.vinaenter.constant.MessageEnum;
import vn.edu.vinaenter.model.bean.Category;
import vn.edu.vinaenter.model.dao.CategoryDAO;
import vn.edu.vinaenter.model.dao.LandDAO;
import vn.edu.vinaenter.util.PaginationUtil;


@Controller
@RequestMapping("admin/category")
public class AdminCatController {

	@Autowired
	private CategoryDAO catDAO;
	@Autowired
	private LandDAO landDAO;
	@Autowired
	private ServletContext servletContext;
	
	/*s:index*/
	@GetMapping(path = { "", "/page-{page}" })
	public String index(@PathVariable(name = "page", required = false) Integer page, ModelMap modelMap) {
		int numberOfItems = catDAO.countItems();
		int numberOfPages = (int) Math.ceil((float) numberOfItems / Defines.ROW_COUNT);
		if (page == null) {
			page = 1;
		} else if (page < 1) {
			return "redirect:/admin/category/page-1";
		} else if (page > numberOfPages) {
			return "redirect:/admin/category/page-" + numberOfPages;
		}
		int offset = (page - 1) * Defines.ROW_COUNT;
		
		List<Integer> paginations = PaginationUtil.show(numberOfPages, 7, page);
		
		modelMap.addAttribute("paginations", paginations);
		modelMap.addAttribute("page", page);
		modelMap.addAttribute("numberOfPages", numberOfPages);
		modelMap.addAttribute("numberOfItems", numberOfItems);
		
		List<Category> listCategories = catDAO.getItemsPagination(offset);
		modelMap.addAttribute("listCategories", listCategories);
		return "cland.admin.cat.index";
	}
	/*e:index*/
	
	/*start add*/
	@GetMapping("add")
	public String add() {
		return "cland.admin.cat.add";
	}
	
	@PostMapping("add")
	public String add(@Valid @ModelAttribute("objCat") Category objCat, BindingResult errors, RedirectAttributes ra) {
		if (errors.hasErrors()) {
			return "cland.admin.cat.add";
		}
		if (catDAO.addItem(objCat) > 0) {
			ra.addFlashAttribute("eMsg", MessageEnum.MSG_ADD_SUCCESS);
			return "redirect:/admin/category";
		} else {
			ra.addFlashAttribute("eMsg", MessageEnum.MSG_ERROR);
			return "cland.admin.cat.add";
		}
	}
	/*end add*/
	
	/*start delete*/
	@GetMapping("del/{id}")
	public String del(@PathVariable("id") int id, RedirectAttributes ra) {
		if (catDAO.delItem(id) > 0) {
			/* get all picture of all lands of category by id */
			List<String> pictures = landDAO.getPicturesByCatId(id);
			//System.out.println(pictures);
			for (String picture : pictures) {
				File file = new File(servletContext.getRealPath("") + "WEB-INF" + File.separator + Defines.DIR_UPLOAD
						+ File.separator + picture);
				file.delete();
			}
			landDAO.delItemsByCategoryId(id);
			
			ra.addFlashAttribute("eMsg", MessageEnum.MSG_DELETE_SUCCESS);
		} else {
			ra.addFlashAttribute("eMsg", MessageEnum.MSG_ERROR);
		}
		return "redirect:/admin/category";
	}
	/*end delete*/
	
	/*start edit*/
	@GetMapping("edit/{id}-{page}")
	public String edit(@PathVariable("id") int id, @PathVariable("page") int page, ModelMap modelMap) {
		Category objCat = catDAO.getItem(id);
		modelMap.addAttribute("objCat", objCat);
		modelMap.addAttribute("page", page);
		return "cland.admin.cat.edit";
	}
	
	@PostMapping("edit/{id}-{page}")/*đặt cid map với setter để @ModelAtribure nhận được*/
	public String edit(@Valid @ModelAttribute("objCat") Category objCat, BindingResult errors, @PathVariable("page") int page, RedirectAttributes ra) {
		if (errors.hasErrors()) {
			return "cland.admin.cat.edit";
		}
		
		if (catDAO.editItem(objCat) > 0) {
			ra.addFlashAttribute("eMsg", MessageEnum.MSG_UPDATE_SUCCESS);
			ra.addFlashAttribute("objCat", objCat);
			return "redirect:/admin/category/page-" + page;
		} else {
			ra.addFlashAttribute("eMsg", MessageEnum.MSG_ERROR);
			return "cland.admin.cat.edit";
		}
	}
	/*end edit*/
	
	/*s:search*/
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
		
		int numberOfItems = catDAO.countSearchItems(searchString);
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
		
		List<Category> listCategories = catDAO.getSearchItemsPagination(searchString, offset);
		modelMap.addAttribute("listCategories", listCategories);
		return "cland.admin.cat.search";
	}
	
}
