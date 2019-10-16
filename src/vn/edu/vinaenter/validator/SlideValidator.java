package vn.edu.vinaenter.validator;

import org.apache.commons.io.FilenameUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import vn.edu.vinaenter.constant.Defines;
import vn.edu.vinaenter.model.bean.Slide;

public class SlideValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Slide.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Slide slide = (Slide) target;
		String filename = slide.getPicture();
		if ("".equals(filename)) {
			errors.rejectValue("picture", "messageChooseImage");
		} else {
			boolean check = false;
			String ex = FilenameUtils.getExtension(filename);
			for (String item : Defines.IMAGES_FILE_NAME_EX) {
				if (item.equalsIgnoreCase(ex)) {
					check = true;
					break;
				}
			}
			if (!check) {
				errors.rejectValue("picture", "messageChooseImageType");
			}
		}
		
		if (slide.getSort() == null) {
			errors.rejectValue("sort", "messageInputSortSlide");
		}
		
		/*URLValidator urlValidator = new URLValidator();
		boolean check = urlValidator.isValid(slide.getLink(), null);
		System.out.println(check);*/
	}

	

}
