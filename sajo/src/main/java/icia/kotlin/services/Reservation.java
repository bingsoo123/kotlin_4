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
import com.google.gson.JsonElement;

import icia.kotlin.bean.Movie;
import icia.kotlin.mapper.Mapper;
import icia.kotlin.mapper.ReservationIf;

@Service
public class Reservation {
	@Autowired
	private ReservationIf mapper;
	@Autowired
	private PlatformTransactionManager tran;
	@Autowired
	private Gson son;
	private ModelAndView mav;
	public ModelAndView entrance(Movie movie) {
		
		ModelAndView mav = null;
		
		if(movie.getMvCode() ==null) {
			mav = this.movieListCtl();
	
		}else {
			
			switch(movie.getSCode()) {
			case"1":
				mav = this.screeningDate(movie);
				break;
			case"2":
	            mav = this.selectScreen(movie);
	            break;
			case"3":
	            mav = this.screeningTime(movie);
	            break;
			case"4":
	            mav = this.selectSeat(movie);
	            break;
			}
		}
		
		return mav;
	}
	
	private ModelAndView selectSeat(Movie movie) {
		ModelAndView mav = new ModelAndView();
		
		System.out.println(movie.getMvCode());
		System.out.println(movie.getMvName());
		System.out.println(movie.getMvGrade());
		System.out.println(movie.getMvThcode());
		System.out.println(movie.getMvScreen());
		System.out.println(movie.getMvTime());
		
		mav.addObject("Access", this.getCurrentDate('f'));
		mav.setViewName("step5");
		return mav;
		
	}
	
	private ModelAndView screeningDate(Movie movie) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("Access", this.getCurrentDate('d'));
		String jsonData = son.toJson(this.getMovieDetail(movie));
		mav.addObject("movieData", jsonData);
		
		mav.setViewName("step2");
		return mav;
	}
	private ModelAndView screeningTime(Movie movie) {
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("Access", this.getCurrentDate('d'));
		
		String jsonData = son.toJson(this.getScreening(movie));
		
		System.out.println(jsonData);
		
		mav.addObject("ScreeningData", jsonData);
		
		
		return mav;
				
		
	}

	  

	private ModelAndView selectScreen(Movie movie) {
	      
	      ModelAndView mav = new ModelAndView();
	      
	      if(this.getScreen(movie).size()==0) {
	         
	         System.out.println("영화정보 없음");
	         mav.addObject("sList" , "죄송합니다 해당영화는 상영 예정이 없습니다");
	      }else {
	         System.out.println("영화정보 있음");
	         
	         Gson son = new Gson();
	         String sonData = son.toJson(this.getScreen(movie));
	         mav.addObject("sList" , sonData);
	      }
	      
	      mav.setViewName("home");
	      return mav;
	   }


	private ArrayList<Movie> getScreen(Movie movie) {
		 return mapper.getScreenList(movie);
	}

	private ModelAndView movieListCtl() {
		ModelAndView mav = new ModelAndView();
		
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E요일");
		mav.addObject("Access", sdf.format(date));
		mav.addObject("MovieList", this.makeMovieList(this.getMovieList()));
		System.out.println(this.getMovieList().size());
		
		Gson gson = new Gson();
		String jsonData = gson.toJson(this.getMovieList());
		
		mav.addObject("jsonMovie", jsonData);
		mav.setViewName("mList");
		return mav;
	
	}
	private ArrayList<Movie> getMovieList() {
		
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
	private boolean convetToBoolean(int data) {
	      return data>=1 ? true : false;
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
	mv.addObject("Comment",this.getMovieDetail(movie).getMvComments());
	mv.addObject("mvCode",this.getMovieDetail(movie).getMvCode());
	
	mv.addObject("today",day[0]);
	mv.addObject("tomorrow",day[1]);
	mv.addObject("three",day[2]);
	mv.addObject("four",day[3]);
	mv.addObject("five",day[4]);
	
	mv.setViewName("movieDetail");
	
	return mav;
	}
private Movie getMovieDetail(Movie movie) {
	
	return mapper.getMovieDetail(movie);
}
private String getCurrentDate(char dateType) {
	Date date = new Date();
	
	SimpleDateFormat sdf = (dateType=='f')? new SimpleDateFormat("YYYY-MM-DD HH:mm:ss E요일") : 
		(dateType=='d')? new SimpleDateFormat("yyyy-MM-dd"):
			(dateType=='t')? new SimpleDateFormat("HH:mm E요일") : null;
			
			return sdf.format(date);
}
private JsonElement getScreening(Movie movie) {
	
	return null;
}

}