package vn.edu.vinaenter.util;

import java.util.ArrayList;
import java.util.List;

public class PaginationUtil {
	
	public static void main(String[] args) {
		for (int i = 0; i < 30; i++) {
			display(PaginationUtil.show(30, 7, i));
			System.out.println();
		}
	}
	
	public static void display(List<Integer> list) {
		for (Integer i : list) {
			if (i == -1) {
				System.out.print("<--\t");
			} else if (i == -2) {
				System.out.print("-->\t");
			} else {
				System.out.print(i + "\t");
			}
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
