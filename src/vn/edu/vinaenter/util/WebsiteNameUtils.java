package vn.edu.vinaenter.util;

import javax.servlet.http.HttpServletRequest;

public class WebsiteNameUtils {
	public static String websiteName(HttpServletRequest request) {
		System.out.println(request.getRequestURL().toString());
		return "";
	}
}
