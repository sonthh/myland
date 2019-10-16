package vn.edu.vinaenter.controller;

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.WebUtils;

import vn.edu.vinaenter.constant.Defines;
import vn.edu.vinaenter.model.bean.Category;
import vn.edu.vinaenter.model.bean.Contact;
import vn.edu.vinaenter.model.bean.Land;
import vn.edu.vinaenter.model.bean.Slide;
import vn.edu.vinaenter.model.dao.CategoryDAO;
import vn.edu.vinaenter.model.dao.ContactDAO;
import vn.edu.vinaenter.model.dao.LandDAO;
import vn.edu.vinaenter.model.dao.SlideDAO;
import vn.edu.vinaenter.util.PaginationUtil;

@Controller
public class PublicIndexController {
	
	@Autowired
	private CategoryDAO categoryDAO;

	@Autowired
	private ContactDAO contactDAO;
	
	@Autowired
	private LandDAO landDAO;
	
	@Autowired
	private SlideDAO slideDAO;
	
	/**
	 * phương thức commonObject chỉ có hiệu lực với những url trong class này.
	 * @param modelMap
	 */
	@ModelAttribute
	public void commonObject(ModelMap modelMap) {
		//System.out.println("PublicIndexController.commonObject()");
		
		//tất cả danh mục
		List<Category> listCategories = categoryDAO.getItems();
		modelMap.addAttribute("listCategories", listCategories);
		
		//danh mục hot
		List<Category> listTopCategories = categoryDAO.getItemsTop(4);
		modelMap.addAttribute("listTopCategories", listTopCategories);
		
		//tin mới nhất
		List<Land> listTopLands = landDAO.getTopItems(6);
		modelMap.addAttribute("listTopLands", listTopLands);
		
		//tin xem nhiều
		List<Land> listTopViewsLands = landDAO.getTopViewsItems(6);
		modelMap.addAttribute("listTopViewsLands", listTopViewsLands);
		
		modelMap.addAttribute("landDAO", landDAO);
	}
	
	/* s:index */
	@GetMapping(path = { "", "/page-{page}" })
	public String index(@PathVariable(name = "page", required = false) Integer page, ModelMap modelMap) {
		int numberOfItems = landDAO.countItems();
		int numberOfPages = (int) Math.ceil((float) numberOfItems / Defines.ROW_COUNT);
		if (page == null) {
			page = 1;
		} else if (page < 1) {
			return "redirect:/page-1";
		} else if (page > numberOfPages) {
			return "redirect:/page-" + numberOfPages;
		}
		int offset = (page - 1) * Defines.ROW_COUNT;
		
		List<Integer> paginations = PaginationUtil.show(numberOfPages, 7, page);
		
		modelMap.addAttribute("paginations", paginations);
		modelMap.addAttribute("page", page);
		modelMap.addAttribute("numberOfPages", numberOfPages);
		modelMap.addAttribute("numberOfItems", numberOfItems);
		
		List<Land> listLands = landDAO.getItemsPagination(offset);
		modelMap.addAttribute("listLands", listLands);
		
		List<Slide> listTopOrderSlides = slideDAO.getTopOrderItems(3);
		modelMap.addAttribute("listTopOrderSlides", listTopOrderSlides);
		return "cland.public.index.index";
	}
	/*e:index*/
	
	
	/*s:cat*/
	@GetMapping({ "category/{catName}-{categoryId}", "category/{catName}-{categoryId}/page-{page}" })
	public String cat(@PathVariable("catName") String catName, @PathVariable("categoryId") int categoryId,
			@PathVariable(name = "page", required = false) Integer page, ModelMap modelMap) {
		int numberOfItems = landDAO.countItemsByCategoryId(categoryId);
		int numberOfPages = (int) Math.ceil((float) numberOfItems / Defines.ROW_COUNT);
		if (page == null) {
			page = 1;
		} else if (page < 1) {
			return "redirect:/category/" + catName + "-" + categoryId + "/page-" + page;
		} else if (page > numberOfPages) {
			return "redirect:/category/" + catName + "-" + categoryId + "/page-" + numberOfPages;
		}
		int offset = (page - 1) * Defines.ROW_COUNT;
		
		List<Integer> paginations = PaginationUtil.show(numberOfPages, 7, page);
		
		modelMap.addAttribute("paginations", paginations);
		modelMap.addAttribute("page", page);
		modelMap.addAttribute("numberOfPages", numberOfPages);
		modelMap.addAttribute("numberOfItems", numberOfItems);
		
		Category objCat = categoryDAO.getItem(categoryId);
		modelMap.addAttribute("objCat", objCat);
		List<Land> listLands = landDAO.getItemsPaginationsByCategoryId(offset,categoryId);
		modelMap.addAttribute("listLands", listLands);
		return "cland.public.index.cat";
	}
	/* e:cat */
	
	/*s:detail*/
	@GetMapping("{landName}-{id}.html")
	public String single(@PathVariable("id") int id, ModelMap modelMap,
			HttpServletRequest request, HttpServletResponse response) {
		
		Land objLand = landDAO.getItem(id);
		modelMap.addAttribute("objLand", objLand);
		
		List<Land> listRelatedLands = landDAO.getRelatedItemsLimit(objLand, 3);
		modelMap.addAttribute("listRelatedLands", listRelatedLands);
		
		//kiểm tra cookie có tồn tại hay không thông qua name phải duyệt for rồi so sánh
		/*Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			System.out.println(cookie.getName());
			System.out.println(cookie.getMaxAge());
			System.out.println(cookie.getValue());
			System.out.println("--------------");
		}*/
		
		//nếu không tồn tại cookies theo name là "increaseView-" + id thì trả về null
		Cookie cookie =	WebUtils.getCookie(request, "increaseView-" + id);
		//System.out.println(cookie);
		if (cookie == null) {
			landDAO.increaseView(id);
			cookie = new Cookie("increaseView-" + id, "increaseView-" + id); //value gì cũng được vì ta kiểm tra theo name mà
			cookie.setMaxAge(60 * 60);
			response.addCookie(cookie);
		}
		
		return "cland.public.index.single";
	}
	/*e:detail*/
	
	/*s:contact*/
	@GetMapping("contact")
	public String contact() {
		return "cland.public.index.contact";
	}
	
	/* handel ajax for send contact */
	@ResponseBody
	@PostMapping("contact")
	public String contact(@ModelAttribute("objContact") Contact objContact) {
		if (contactDAO.addItem(objContact) > 0) {
			return "1";
		} else {
			return "0";
		}
	}
	/*e:contact*/
	
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
		if (page == null) {
			page = 1;
		}
		
		int numberOfItems = landDAO.countSearchItems(searchString);
		int numberOfPages = (int) Math.ceil((float) numberOfItems / Defines.ROW_COUNT);
		int offset = (page - 1) * Defines.ROW_COUNT;
		
		List<Integer> paginations = PaginationUtil.show(numberOfPages, 7, page);
		
		modelMap.addAttribute("paginations", paginations);
		modelMap.addAttribute("page", page);
		modelMap.addAttribute("numberOfPages", numberOfPages);
		modelMap.addAttribute("numberOfItems", numberOfItems);
		modelMap.addAttribute("searchString", searchString);
		
		List<Land> listLands = landDAO.getSearchItemsPagination(searchString, offset);
		modelMap.addAttribute("listLands", listLands);
		
		//slide
		List<Slide> listTopOrderSlides = slideDAO.getTopOrderItems(3);
		modelMap.addAttribute("listTopOrderSlides", listTopOrderSlides);
		return "cland.public.index.search";
	}
	/*e:search*/
}
