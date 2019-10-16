package vn.edu.vinaenter.constant;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {

		/*int numberOfPages = 30;
		for (int i = 1; i <= numberOfPages; i++) {
			System.out.print("current page = " + i + "\t");
			List<Integer> list = show(numberOfPages, 7, i);
			for (Integer integer : list) {
				if (integer == -1) {
					System.out.print("<--\t");
				} else if (integer == -2) {
					System.out.print("-->\t");
				} else {
					System.out.print(integer + "\t");
				}
			}
			System.out.println();
		}*/
		
		try {
			String uri = URLDecoder.decode("search-" + "sơn" + "-1", "UTF-8");
			System.out.println(uri);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

	}

	public static List<Integer> show(int numberOfPages, int numberOfPagesDisplay, int currentPage) {
		List<Integer> list = new ArrayList<>();
		if (numberOfPages < numberOfPagesDisplay) {
			for (int i = 1; i <= numberOfPages; i++) {
				list.add(i);
			}
		} else {
			if (currentPage <= (numberOfPagesDisplay / 2 + 1)) {
				for (int i = 1; i <= numberOfPagesDisplay - 1; i++) {
					list.add(i);
				}
				list.add(-2);
				list.add(numberOfPages);
			} else if (currentPage <= (numberOfPages - (numberOfPagesDisplay / 2 + 1))) {
				list.add(1);
				list.add(-1);
				int sizeFor = currentPage + ((numberOfPagesDisplay - 2) / 2);
				for (int i = currentPage - ((numberOfPagesDisplay - 2) / 2); i <= sizeFor; i++) {
					list.add(i);
				}
				list.add(-2);
				list.add(numberOfPages);
			} else {
				list.add(1);
				list.add(-1);
				for (int i = numberOfPages - numberOfPagesDisplay + 2; i <= numberOfPages; i++) {
					list.add(i);
				}
			}
		}
		return list;
	}
}
