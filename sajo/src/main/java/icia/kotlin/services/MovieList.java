package icia.kotlin.services;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

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
			case"1":
				mv = this.movieDetail(movie);
				break;
			case"2":
				mv = this.selectScreen(movie);
				break;
				
			}
			
		}
		
		return mv;
	}

	private ModelAndView selectScreen(Movie movie) {
		
		ModelAndView mv = new ModelAndView();
		
		if(this.getScreen(movie).size()==0) {
			
			System.out.println("영화정보가 없다");
			mv.addObject("sList" , "죄송합니다 해당영화는 상영 예정이 없습니다");
		}else {
			System.out.println("영화정보 있다");
			
			Gson son = new Gson();
			String sonData = son.toJson(this.getScreen(movie));
			mv.addObject("sList" , sonData);
		}
		
		mv.setViewName("home");
		return mv;
	}

	private ArrayList<Movie> getScreen(Movie movie) {
		return mapper.getScreenList(movie);
	}

	private ModelAndView movieDetail(Movie movie) {
		
		System.out.println("디테일진입");
		
		ModelAndView mv = new ModelAndView();
		
		String[] day = movie.getMvDate().split(":");
		
		System.out.println("첫쨰날"+day[0]);
		System.out.println("둘쨰날"+day[1]);
		System.out.println("셋쨰날"+day[2]);
		System.out.println("넷쨰날"+day[3]);
		
		System.out.println("영화이름 = " + this.getMovieDetail(movie).getMvName());
		mv.addObject("Image", this.getMovieDetail(movie).getMvImage());
		mv.addObject("Name", this.getMovieDetail(movie).getMvName());
		mv.addObject("Grade",this.getMovieDetail(movie).getMvGrade());
		mv.addObject("Comment",this.getMovieDetail(movie).getMvComment());
		mv.addObject("mvCode",this.getMovieDetail(movie).getMvCode());
		
		mv.addObject("today",day[0]);
		mv.addObject("tomorrow",day[1]);
		mv.addObject("three",day[2]);
		mv.addObject("four",day[3]);
		mv.addObject("five",day[4]);
		
		mv.setViewName("movieDetail");
		
		return mv;
	}
	
	private Movie getMovieDetail(Movie movie) {
		
		return mapper.getMovieDetail(movie);
	}

	private ModelAndView movieListCtl() {
		
		ModelAndView mv = new ModelAndView();
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		mv.addObject("Access" , sdf.format(date));
//		mv.addObject("MovieList" , this.makeMovieList(this.getmovieList()));
		
		Gson son = new Gson();
		String jsonData = son.toJson(this.getmovieList());
		System.out.println(jsonData);
		mv.addObject("jsonMovie" , jsonData);
		mv.setViewName("mList");
		
		return mv;
	}

	private ArrayList<Movie> getmovieList() {
		return mapper.getMovieList();
	}
	
	private String makeMovieList(ArrayList<Movie> mList) {
		
		StringBuffer sb = new StringBuffer();
		
		
		for(Movie movie : mList) {

			sb.append("<div class=\"movie\" onClick=\"goData(\'"+movie.getMvCode()+"\')\""
					+ ">");
			sb.append("<div class=\"movie_top\"><img src=\"../resources/img/"+movie.getMvImage()+"\"></div>");
			sb.append("<div class=\"movie_name\">"+movie.getMvName()+"</div>");
			sb.append("<div class=\"movie_age\">"+movie.getMvGrade()+"</div>");
			sb.append("</div>");
		}
		
		return sb.toString();
	}
	
	private boolean convetToBoolean(int data) {
		return data>=1 ? true : false;
	}
	
}









