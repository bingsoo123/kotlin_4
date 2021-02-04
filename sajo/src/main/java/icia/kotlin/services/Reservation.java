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
      
      ModelAndView mv = null;
      
      if(movie.getMvCode() == null) {
         System.out.println("test");
         mv = this.movieListCtl();
         
      }else {
         
         switch(movie.getSCode()) {
         case"1":
            mv = this.screeningDate(movie);
            break;
         case"2":
        	System.out.println("cas2도착");
            mv = this.selectScreen(movie);
            break;
         default:
				break;   
         }         
      }      
      return mv;
   }

   /* Screening Date(날짜선택) */
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
   
	/* Screening Date(시간선택) */
	private ModelAndView selectScreen(Movie movie) {

		ModelAndView mv = new ModelAndView();
		
		System.out.println("test1 ::" +movie.getMvDate() + "test2 ::" + movie.getMvCode());
		
		String sonData = son.toJson(this.getScreenList(movie));
		mv.addObject("ScreeningData" , sonData);
		System.out.println(sonData);
		mv.setViewName("step2");
		return mv;
	}

   private ArrayList<Movie> getScreenList(Movie movie) {
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
      mv.addObject("Comments",this.getMovieDetail(movie).getMvComments());
      mv.addObject("mvCode",this.getMovieDetail(movie).getMvCode());
      
      mv.addObject("today",day[0]);
      mv.addObject("tomorrow",day[1]);
      mv.addObject("three",day[2]);
      mv.addObject("four",day[3]);
      mv.addObject("five",day[4]);
      
      mv.setViewName("movieDetail");
      
      return mv;
   }
  
   /* Current DataTime */
   private String getCurrentDate(char dateType) {
	   Date date = new Date();
	   
	   SimpleDateFormat sdf = (dateType == 'f')? new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E요일") :
		   (dateType == 'd')? new SimpleDateFormat("yyyy-MM-dd"):
			   (dateType == 't')? new SimpleDateFormat("HH:mm:ss E요일"):null;
			   
			   return sdf.format(date);
   }
   
   
   private Movie getMovieDetail(Movie movie) {
      
      return mapper.getMovieDetail(movie);
   }

   private ModelAndView movieListCtl() {
      
      ModelAndView mv = new ModelAndView();
      
     //날짜
      mv.addObject("Access" , this.getCurrentDate('f'));
      
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








