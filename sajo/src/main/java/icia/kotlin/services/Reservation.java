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

import icia.kotlin.beans.Movie;
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
	
	public ModelAndView entrance(Movie movie) {
		
		ModelAndView mav = null;
		
		if(movie.getMvCode() == null) {
			mav = this.movieListCtl();
			
		}else {
			switch(movie.getSCode()) {
			case "1":
				mav = this.screeningDate(movie);
				break;
			case "2" :
				mav = this.movieScreenCtl(movie);
				break;	
			case "3" :
				mav = this.screeningTime(movie);
				break;
			case "4" :
				mav = this.selectSeat(movie);
			}
		}
		
		return mav;
		
	}
	private ModelAndView selectSeat(Movie movie) {

		ModelAndView mav = new ModelAndView();
		
		System.out.println(movie.getMvCode());
		System.out.println(movie.getMvName());
		System.out.println(movie.getMvGrade());
		System.out.println(movie.getMvThCode());
		System.out.println(movie.getMvScreen());
		System.out.println(movie.getMvTime());
		
		mav.addObject("Access", this.getCurrentDate('f'));
		mav.setViewName("step4");
		return mav;
	}
	private ModelAndView screeningTime(Movie movie) {
		ModelAndView mav = new ModelAndView();
		
		/* Start Date */
		mav.addObject("Access", this.getCurrentDate('d'));
		
		/* Movie Info & Convert to JSON*/
		String jsonData = son.toJson(this.getScreening(movie));
		System.out.println("Reservation 에서 = " + jsonData);
		mav.addObject("ScreeningData",jsonData);
		return mav;
		
	}
	private ArrayList<Movie> getScreening(Movie movie) {

		return mapper.getScreening(movie);
	}
	private ModelAndView screeningDate(Movie movie) {
		
		ModelAndView mav = new ModelAndView();
		String[] day = movie.getMvDate().split(":");
		/* Start Date */
		mav.addObject("Access", this.getCurrentDate('d'));
		
		/* Movie Info & Convert to JSON*/
		String jsonData = son.toJson(this.getMovieDetail(movie));
		
		mav.addObject("movieData",jsonData);
		
		
		mav.addObject("today", day[0]);
		mav.addObject("tomorrow", day[1]);
		mav.addObject("three",day[2]);
		mav.addObject("four", day[3]);
		mav.addObject("five", day[4]);
		
		mav.setViewName("step2");
		
		return mav;
	}
	private ModelAndView movieScreenCtl(Movie movie) {
		 ModelAndView mv = new ModelAndView();
	      	
		
	      if(this.getScreen(movie).size()==0) {
	         
	         System.out.println("영화 정보가 없습니다.");
	         mv.addObject("sList" , "죄송합니다 해당영화는 상영 예정이 없습니다");
	      }else {
	         System.out.println(movie.getMvDate() + "  에" + "  영화정보가 있습니다.");
	         
	         Gson son = new Gson();
	         String sonData = son.toJson(this.getScreen(movie));
	         mv.addObject("sList" , sonData);
	         System.out.println(sonData);
	      }
	      
	      mv.setViewName("home");
	      return mv;
	   }


//	private boolean isMovie(Movie movie) {
//		return this.convetToBoolean(mapper.isMovie(movie));
//		
//	}
	private ArrayList<Movie> getScreen(Movie movie) {
		return mapper.getScreen(movie);
		
	}
//	private ModelAndView movieDetailCtl(Movie movie) {
//		
//		System.out.println("디테일 진입 성공!!");
//		
//		ModelAndView mav = new ModelAndView();
//		
//		String[] day = movie.getMvDate().split(":");
//		
//		
//		mav.addObject("today", day[0]);
//		mav.addObject("tomorrow", day[1]);
//		mav.addObject("three",day[2]);
//		mav.addObject("four", day[3]);
//		mav.addObject("five", day[4]);
//		
//		mav.addObject("Image", this.getMovieDetail(movie).getMvImage());
//		mav.addObject("Name", this.getMovieDetail(movie).getMvName());
//		mav.addObject("Grade", this.getMovieDetail(movie).getMvGrade());
//		mav.addObject("Comments", this.getMovieDetail(movie).getMvComments());
//		mav.addObject("mvCode", this.getMovieDetail(movie).getMvCode());
//		
//		
//		mav.setViewName("movieDetail");
//		return mav;
//	}

	private Movie getMovieDetail(Movie movie) {
		return mapper.getMovieDetail(movie);
		
	}
	private ModelAndView movieListCtl() {
		ModelAndView mav = new ModelAndView();
		
		/*AccessTime*/
		
		mav.addObject("Access", this.getCurrentDate('f'));
		
		/* Convert to json*/
		String jsonData = son.toJson(this.getMovieList());
		mav.addObject("jsonMovie" , jsonData);
		/* View */
		mav.setViewName("mList");
		return mav;
		
	}
	private ArrayList<Movie> getMovieList(){
		
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
	   /* Current DataTime*/
	   private String getCurrentDate(char dateType) {
		Date date = new Date();
		
		SimpleDateFormat sdf = (dateType=='f') ? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E요일") :
			(dateType=='d') ? new SimpleDateFormat("yyyy-MM-dd"):
				(dateType=='t') ? new SimpleDateFormat("HH:mm E요일") : null;
		   System.out.println(sdf);
		   return sdf.format(date);
		   
	   }


}
