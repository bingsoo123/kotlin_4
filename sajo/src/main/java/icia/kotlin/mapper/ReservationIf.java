package icia.kotlin.mapper;

import java.util.ArrayList;

import icia.kotlin.bean.Movie;

public interface ReservationIf {
	
	public ArrayList<Movie> getMovieList();
	
	public Movie getMovieDetail(Movie movie);

	public ArrayList<Movie> getScreenList(Movie movie);
	
	public int isMovie(Movie movie);
	
	public ArrayList<Movie> getScreening(Movie movie);

	
}
