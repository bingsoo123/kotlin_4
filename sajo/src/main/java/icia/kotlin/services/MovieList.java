package icia.kotlin.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.ModelAndView;

import icia.kotlin.beans.Movie;
import icia.kotlin.mapper.Reservation;

@Service
public class MovieList {

	@Autowired
	private Reservation mapper;
	@Autowired
	private PlatformTransactionManager tran;
	
	public ModelAndView entrance(Movie movie) {
		
		ModelAndView mv = null;
		
		if(movie.getMvCode() == null) {
			
			mv = this.movieListCtl();
		}else {
			
			switch(movie.getSCode()) {
			case"":
				
				break;
			
			}
			
		}
		
		return mv;
	}

	private ModelAndView movieListCtl() {
		
		ModelAndView mv = new ModelAndView();
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E요일");
		mv.addObject("Access" , sdf.format(date));
		
		System.out.println(this.getmovieList().size());
		
		mv.setViewName("home");
		
		return mv;
	}

	private ArrayList<Movie> getmovieList() {
		return mapper.getMovieList();
	}
	
}
