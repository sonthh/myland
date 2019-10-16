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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import vn.edu.vinaenter.constant.Defines;
import vn.edu.vinaenter.constant.MessageEnum;
import vn.edu.vinaenter.model.bean.Slide;
import vn.edu.vinaenter.model.dao.SlideDAO;
import vn.edu.vinaenter.util.FilenameUtil;
import vn.edu.vinaenter.util.PaginationUtil;
import vn.edu.vinaenter.validator.SlideEditValidator;
import vn.edu.vinaenter.validator.SlideValidator;


@Controller
@RequestMapping("admin/slide")
public class AdminSlideController {

	@Autowired
	private SlideDAO slideDAO;
	@Autowired
	private ServletContext servletContext;
	
	/*s:index*/
	@GetMapping(path = { "", "/page-{page}" })
	public String index(@PathVariable(name = "page", required = false) Integer page, ModelMap modelMap) {
		int numberOfItems = slideDAO.countItems();
		int numberOfPages = (int) Math.ceil((float) numberOfItems / Defines.ROW_COUNT);
		if (page == null) {
			page = 1;
		} else if (page < 1) {
			return "redirect:/admin/slide/page-1";
		} else if (page > numberOfPages) {
			return "redirect:/admin/slide/page-" + numberOfPages;
		}
		int offset = (page - 1) * Defines.ROW_COUNT;
		
		List<Integer> paginations = PaginationUtil.show(numberOfPages, 7, page);
		
		modelMap.addAttribute("paginations", paginations);
		modelMap.addAttribute("page", page);
		modelMap.addAttribute("numberOfPages", numberOfPages);
		modelMap.addAttribute("numberOfItems", numberOfItems);
		
		List<Slide> listSlides = slideDAO.getItemsPagination(offset);
		modelMap.addAttribute("listSlides", listSlides);
		return "cland.admin.slide.index";
	}
	/*e:index*/
	
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
		
		int numberOfItems = slideDAO.countSearchItems(searchString);
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
		
		List<Slide> listSlides = slideDAO.getSearchItemsPagination(searchString, offset);
		modelMap.addAttribute("listSlides", listSlides);
		return "cland.admin.slide.search";
	}
	/*e:search*/
	
	
	/*ajax enable slide*/
	@ResponseBody
	@PostMapping("enableSlide")
	public String enableSlide(@RequestParam("aid") int id, @RequestParam("aenable") int enable) {
		enable = enable == 1 ? 0 : 1;
		if (slideDAO.enableSlide(id, enable) > 0) {
			return "1";
		} else {
			return "0";
		}
	}
	
	/* start add */
	@GetMapping("add")
	public String add(ModelMap modelMap) {
		return "cland.admin.slide.add";
	}
	
	@PostMapping("add")
	public String add(@Valid @ModelAttribute("objSlide") Slide objSlide, BindingResult errors,
			@RequestParam("images") CommonsMultipartFile cmf, SlideValidator validator,
			RedirectAttributes ra, ModelMap modelMap) {
		objSlide.setPicture(cmf.getOriginalFilename());
		validator.validate(objSlide, errors);
		if (errors.hasErrors()) {
			return "cland.admin.slide.add";
		}
		
		String filename =  FilenameUtil.rename(cmf.getOriginalFilename());
		objSlide.setPicture(filename);
		if (slideDAO.addItem(objSlide) > 0) {
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
			return "redirect:/admin/slide";
		} else {
			ra.addFlashAttribute("eMsg", MessageEnum.MSG_ERROR);
			return "cland.admin.slide.add";
		}
	}
	/* end add */
	
	/* start edit */
	@GetMapping("edit/{id}-{page}")
	public String edit(@PathVariable("id") int id, @PathVariable("page") int page,  ModelMap modelMap) {
		Slide objSlide = slideDAO.getItem(id);
		modelMap.addAttribute("objSlide", objSlide);
		modelMap.addAttribute("page", page);
		return "cland.admin.slide.edit";
	}
	
	@PostMapping("edit/{id}-{page}")
	public String edit(@PathVariable("id") int id, @PathVariable("page") int page, @Valid @ModelAttribute("objSlide") Slide objSlide,
			BindingResult errors, SlideEditValidator validator,
			@RequestParam("images") CommonsMultipartFile cmf,
			RedirectAttributes ra, ModelMap modelMap) {
		
		objSlide.setPicture(cmf.getOriginalFilename());
		Slide oldSlide = slideDAO.getItem(id);
		validator.validate(objSlide, errors);
		if (errors.hasErrors()) {
			objSlide.setPicture(oldSlide.getPicture());
			modelMap.addAttribute("objSlide", objSlide);
			return "cland.admin.slide.edit";
		}
		
		
		objSlide.setPicture(oldSlide.getPicture());
		String filename =  "";
		if (!"".equals(cmf.getOriginalFilename())) {
			filename = FilenameUtil.rename(cmf.getOriginalFilename());;
			objSlide.setPicture(filename);
		}
		if (slideDAO.editItem(objSlide) > 0) {
			if (!"".equals(filename)) {
				String dirPath = servletContext.getRealPath("") + "WEB-INF" + File.separator + Defines.DIR_UPLOAD;
				File dir = new File(dirPath);
				if (!dir.exists()) {
					dir.mkdir();
				}
				
				File oldFile = new File(dirPath + File.separator + oldSlide.getPicture());
				oldFile.delete();
				
				try {
					cmf.transferTo(new File(dirPath + File.separator + filename));
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
			
			ra.addFlashAttribute("eMsg", MessageEnum.MSG_UPDATE_SUCCESS);
			ra.addFlashAttribute("objSlide", objSlide);
			return "redirect:/admin/slide/page-" + page;
		} else {
			ra.addFlashAttribute("eMsg", MessageEnum.MSG_ERROR);
			return "cland.admin.slide.edit";
		}
	}
	/* end edit */
	
	/*start delete*/
	@GetMapping("del/{id}")
	public String del(@PathVariable("id") int id, RedirectAttributes ra) {
		if (slideDAO.delItem(id) > 0) {
			ra.addFlashAttribute("eMsg", MessageEnum.MSG_DELETE_SUCCESS);
		} else {
			ra.addFlashAttribute("eMsg", MessageEnum.MSG_ERROR);
		}
		return "redirect:/admin/slide";
	}
	/*end delete*/
}
