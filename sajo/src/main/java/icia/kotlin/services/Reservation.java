package icia.kotlin.services;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.web.servlet.ModelAndView;

import icia.kotlin.beans.Movie;
import icia.kotlin.mapper.ReservationIf;


@Service
public class Reservation {
	@Autowired
	private ReservationIf mapper;
	@Autowired
	private PlatformTransactionManager tran;
	
	public ModelAndView entrance(Movie movie) {
		ModelAndView mav = null;
		
		if(movie.getMvCode() == null) {
			mav = this.movieListCtl();
		}else {
			switch(movie.getSCode()) {
			case "1":
				mav = this.movieDetail(movie);
				break;
			}
		}
		return mav;
	}

	private ModelAndView movieDetail(Movie movie) {
		
		//System.out.println("movieDetail success");
		
		ModelAndView mav = new ModelAndView();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd");
		
		//System.out.println("코멘트=" + this.getMovieDetail(movie).getMvComments());
		
		mav.addObject("Image", this.getMovieDetail(movie).getMvImage());
		mav.addObject("Name", this.getMovieDetail(movie).getMvName());
		mav.addObject("Grade", this.getMovieDetail(movie).getMvGrade());
		mav.addObject("Comments", this.getMovieDetail(movie).getMvComments());
		
		mav.addObject("one", sdf.format(cal.getTime()));
		cal.add(Calendar.DATE, +1);
		mav.addObject("two", sdf.format(cal.getTime()));
		cal.add(Calendar.DATE, +1);
		mav.addObject("three", sdf.format(cal.getTime()));
		cal.add(Calendar.DATE, +1);
		mav.addObject("four", sdf.format(cal.getTime()));
		cal.add(Calendar.DATE, +1);
		mav.addObject("five", sdf.format(cal.getTime()));
		
		mav.setViewName("movieDetail");
		return mav;
	}

	private Movie getMovieDetail(Movie movie) {
		
		return mapper.getMovieDetail(movie);
	}

	private ModelAndView movieListCtl() {
		ModelAndView mav = new ModelAndView();
		
		/* AccessTime */
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss E요일");
		mav.addObject("Access", sdf.format(date));
		mav.addObject("MovieList", this.makeMovieList(this.getMovieList()));
		
		System.out.println(this.getMovieList().size());
				
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
}
