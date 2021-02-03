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
	@Autowired
	private Gson son;
	
	public ModelAndView entrance(Movie movie) {
		
		ModelAndView mv = null;
		
		if(movie.getMvCode() == null) {
			
			mv = this.movieListCtl();
			
		}else {
			
			switch(movie.getSCode()) {
			case"1":
				mv = this.screeningDate(movie);
				break;
			case"2":
				mv = this.selectScreen(movie);
				break;
				
			}
			
		}
		
		return mv;
	}
	
	private ModelAndView screeningDate(Movie movie) {
		
		ModelAndView mv = new ModelAndView();
		
		/* Start Date */
		mv.addObject("Access",this.getCurrentDate('d'));
		
		String[] day = movie.getMvDate().split(":");
		
		mv.addObject("today",day[0]);
		mv.addObject("tomorrow",day[1]);
		mv.addObject("three",day[2]);
		mv.addObject("four",day[3]);
		mv.addObject("five",day[4]);
		
		/* Movie Info & Convert to JSON */
		String jsonData = son.toJson(this.getMovieDetail(movie));
		mv.addObject("movieData",jsonData);
		
		/* VIEW */
		mv.setViewName("step2");
		
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
			System.out.println(sonData);
			mv.addObject("sList" , sonData);
		}
		
		mv.setViewName("home");
		return mv;
	}

	private ArrayList<Movie> getScreen(Movie movie) {
		return mapper.getScreenList(movie);
	}

	private ModelAndView movieDetail(Movie movie) {
		
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("Access",this.getCurrentDate('d'));
		
		String[] day = movie.getMvDate().split(":");
		
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
	
	private String getCurrentDate(char dateType) {
		
		Date date = new Date();
		
		SimpleDateFormat sdf = (dateType=='f')? new SimpleDateFormat("YYYY-MM-dd HH:mm:ss E요일"):
				(dateType=='d') ? new SimpleDateFormat("YYYY-MM-dd"):
					(dateType=='t') ? new SimpleDateFormat("HH:mm E요일") : null;
		
		return sdf.format(date);
	}
	
	private Movie getMovieDetail(Movie movie) {
		
		return mapper.getMovieDetail(movie);
	}

	private ModelAndView movieListCtl() {
		
		ModelAndView mv = new ModelAndView();
		mv.addObject("Access" , this.getCurrentDate('d'));
		
		String jsonData = son.toJson(this.getmovieList());
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









