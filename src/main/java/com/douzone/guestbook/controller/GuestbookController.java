package com.douzone.guestbook.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.douzone.guestbook.dao.GuestbookDao;
import com.douzone.guestbook.vo.GuestbookVo;

@Controller
public class GuestbookController {
	@Autowired
	private GuestbookDao guestbookDao;
	
//	@RequestMapping("")
//	public ModelAndView list() {
//		ModelAndView mav = new ModelAndView();
//		mav.addObject("list", emaillistDao.getList());
//		mav.setViewName("/WEB-INF/views/list.jsp");
//		return mav;
//	}
	@RequestMapping({"", "/list"})
	public String list(Model model) {
		model.addAttribute("list", guestbookDao.getList());
		return "index";
	}
	
	@RequestMapping("/deleteform&no={no}")
	public String form(
			@PathVariable(value="no") Long no, Model model) {
		model.addAttribute("no", no);
		return "deleteform";
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST)
	public String add(GuestbookVo guestbookVo) {
		guestbookDao.insert(guestbookVo);
		return "redirect:/";
	}
	
	@RequestMapping(value="/delete", method = RequestMethod.POST)
	public String delete(GuestbookVo guestbookVo) {
		guestbookDao.delete(guestbookVo);
		return "redirect:/";
	}
}