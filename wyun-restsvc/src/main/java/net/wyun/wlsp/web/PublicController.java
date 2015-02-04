/**
 * 
 */
package net.wyun.wlsp.web;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Xuecheng
 *
 */
@Controller
@RequestMapping("/public")
public class PublicController {
	
	@RequestMapping(value = "/about", method=RequestMethod.GET)
	public String about(Locale locale, Model model) {
		model.addAttribute("greeting", "Hello!");

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);        
		String formattedDate = dateFormat.format(date);
		model.addAttribute("currentTime", formattedDate);

		return "public/about";
	}

}