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
		mv.addObject("MovieList" , this.makeMovieList(this.getmovieList()));
		
		System.out.println(this.getmovieList().size());
		System.out.println(this.getmovieList().get(0).getMvName());
		
		mv.setViewName("mList");
		
		return mv;
	}

	private ArrayList<Movie> getmovieList() {
		return mapper.getMovieList();
	}
	
	private String makeMovieList(ArrayList<Movie> mList) {
		
		StringBuffer sb = new StringBuffer();
		
		
		for(Movie movie : mList) {
			
			sb.append("<div class=\"movie\" onClick=\"goData(\'"+movie.getMvCode()+"\')\">");
			sb.append("<div class=\"movie_top\"><img src=\"../resources/img/"+movie.getMvImage()+"\"></div>");
			sb.append("<div class=\"movie_name\">"+movie.getMvName()+"</div>");
			sb.append("<div class=\"movie_age\">"+movie.getMvGrade()+"</div>");
			sb.append("</div>");
		}
		
		return sb.toString();
	}
	
	
	

//	<div class="movie">
//
//	<div class="movie_top">
//		<img src="../resources/img/01412345.jpg">
//	</div>
//
//	<div class="movie_name">극한직업</div>
//
//	<div class="movie_age">15세이상이용가</div>
//
//	</div>	
	
	
	
	
	
	
}









