package vn.edu.vinaenter.controller;

import java.io.File;
import java.io.IOException;
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
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vn.edu.vinaenter.constant.Defines;
import vn.edu.vinaenter.constant.MessageEnum;
import vn.edu.vinaenter.model.bean.Category;
import vn.edu.vinaenter.model.bean.Land;
import vn.edu.vinaenter.model.dao.CategoryDAO;
import vn.edu.vinaenter.model.dao.LandDAO;
import vn.edu.vinaenter.util.FilenameUtil;
import vn.edu.vinaenter.util.PaginationUtil;
import vn.edu.vinaenter.validator.LandValidator;

@Controller
@RequestMapping("admin/land")
public class AdminLandController {
	
	@Autowired
	private ServletContext servletContext;
	
	@Autowired
	private CategoryDAO categoryDAO;
	
	@Autowired
	private LandDAO landDAO;
	
	/*s:index*/
	@GetMapping({ "", "/page-{page}"})
	public String index(@PathVariable(name = "page", required = false) Integer page, ModelMap modelMap) {
		int numberOfItems = landDAO.countItems();
		int numberOfPages = (int) Math.ceil((float) numberOfItems / Defines.ROW_COUNT);
		if (page == null) {
			page = 1;
		} else if (page < 1) {
			return "redirect:/admin/land/page-1";
		} else if (page > numberOfPages) {
			return "redirect:/admin/land/page-" + numberOfPages;
		}
		int offset = (page - 1) * Defines.ROW_COUNT;
		
		List<Integer> paginations = PaginationUtil.show(numberOfPages, 7, page);
		
		modelMap.addAttribute("paginations", paginations);
		modelMap.addAttribute("page", page);
		modelMap.addAttribute("numberOfPages", numberOfPages);
		modelMap.addAttribute("numberOfItems", numberOfItems);
		
		List<Land> listLands = landDAO.getItemsPagination(offset);
		modelMap.addAttribute("listLands", listLands);
		return "cland.admin.land.index";
	}
	/*e:index*/
	
	/* start add */
	@GetMapping("add")
	public String add(ModelMap modelMap) {
		List<Category> listCategories = categoryDAO.getItems();
		modelMap.addAttribute("listCategories", listCategories);
		return "cland.admin.land.add";
	}
	
	@PostMapping("add")
	public String add(@Valid @ModelAttribute("objLand") Land objLand, BindingResult errors,
			@RequestParam("images") CommonsMultipartFile cmf, LandValidator validator,
			@RequestParam("categoryId") int categoryId,
			RedirectAttributes ra, ModelMap modelMap) {
		
		objLand.setPicture(cmf.getOriginalFilename());
		objLand.setCategory(new Category(categoryId, null));
		validator.validate(objLand, errors);
		if (errors.hasErrors()) {
			//khi bị lỗi thì objLand tự động được truyền qua trang add
			List<Category> listCategories = categoryDAO.getItems();
			modelMap.addAttribute("listCategories", listCategories);
			return "cland.admin.land.add";
		}
		
		String filename =  FilenameUtil.rename(cmf.getOriginalFilename());
		objLand.setPicture(filename);
		if (landDAO.addItem(objLand) > 0) {
			String dirPath = servletContext.getRealPath("") + "WEB-INF" + File.separator + Defines.DIR_UPLOAD;
			File dir = new File(dirPath);
			if (!dir.exists()) {
				dir.mkdir();
			}
			try {
				cmf.transferTo(new File(dirPath + File.separator +filename));
			} catch (IllegalStateException | IOException e) {
				e.printStackTrace();
			}
			
			ra.addFlashAttribute("eMsg", MessageEnum.MSG_ADD_SUCCESS);
			return "redirect:/admin/land";
		} else {
			List<Category> listCategories = categoryDAO.getItems();
			modelMap.addAttribute("listCategories", listCategories);
			ra.addFlashAttribute("eMsg", MessageEnum.MSG_ERROR);
			return "cland.admin.land.add";
		}
	}
	/* end add */
	
	/* start edit */
	@GetMapping("edit/{id}-{page}")
	public String edit(@PathVariable("id") int id, @PathVariable("page") int page,  ModelMap modelMap) {
		List<Category> listCategories = categoryDAO.getItems();
		modelMap.addAttribute("listCategories", listCategories);
		Land objLand = landDAO.getItem(id);
		
		modelMap.addAttribute("objLand", objLand);
		modelMap.addAttribute("page", page);
		return "cland.admin.land.edit";
	}
	
	@PostMapping("edit/{id}-{page}")
	public String edit(@PathVariable("id") int id, @PathVariable("page") int page, @Valid @ModelAttribute("objLand") Land objLand, BindingResult errors,
			@RequestParam("images") CommonsMultipartFile cmf,
			@RequestParam("categoryId") int categoryId,
			RedirectAttributes ra, ModelMap modelMap) {
		
		if (errors.hasErrors()) {
			ra.addFlashAttribute("objLand", objLand);
			List<Category> listCategories = categoryDAO.getItems();
			modelMap.addAttribute("listCategories", listCategories);
			return "cland.admin.land.edit";
		}
		Land oldLand = landDAO.getItem(id);
		
		objLand.setCategory(new Category(categoryId, null));
		objLand.setPicture(oldLand.getPicture());
		String filename =  "";
		if (!"".equals(cmf.getOriginalFilename())) {
			filename = FilenameUtil.rename(cmf.getOriginalFilename());;
			objLand.setPicture(filename);
		}
		if (landDAO.editItem(objLand) > 0) {
			if (!"".equals(filename)) {
				String dirPath = servletContext.getRealPath("") + "WEB-INF" + File.separator + Defines.DIR_UPLOAD;
				File dir = new File(dirPath);
				if (!dir.exists()) {
					dir.mkdir();
				}
				
				File oldFile = new File(dirPath + File.separator + oldLand.getPicture());
				oldFile.delete();
				
				try {
					cmf.transferTo(new File(dirPath + File.separator + filename));
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
			
			ra.addFlashAttribute("eMsg", MessageEnum.MSG_UPDATE_SUCCESS);
			ra.addFlashAttribute("objLand", objLand);
			return "redirect:/admin/land/page-" + page;
		} else {
			List<Category> listCategories = categoryDAO.getItems();
			modelMap.addAttribute("listCategories", listCategories);
			ra.addFlashAttribute("eMsg", MessageEnum.MSG_ERROR);
			return "cland.admin.land.edit";
		}
	}
	/* end edit */
	
	/* start delete */
	@GetMapping("del/{id}")
	public String delete(@PathVariable("id") int id, RedirectAttributes ra) {
		Land objLand = landDAO.getItem(id);
		File file = new File(servletContext.getRealPath("") + "WEB-INF" + File.separator + Defines.DIR_UPLOAD + File.separator + objLand.getPicture());
		file.delete();
		if (landDAO.delItem(id) > 0) {
			ra.addFlashAttribute("eMsg", MessageEnum.MSG_DELETE_SUCCESS);
		} else {
			ra.addFlashAttribute("eMsg", MessageEnum.MSG_ERROR);
		}
		return "redirect:/admin/land";
	}
	
	/* start search */
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
		
		int numberOfItems = landDAO.countSearchItems(searchString);
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
		
		List<Land> listLands = landDAO.getSearchItemsPagination(searchString, offset);
		modelMap.addAttribute("listLands", listLands);
		return "cland.admin.land.search";
	}
	/* end search */
}
