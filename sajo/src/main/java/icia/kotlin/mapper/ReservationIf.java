package icia.kotlin.mapper;

import java.util.ArrayList;

import icia.kotlin.beans.Movie;

public interface ReservationIf {
	public ArrayList<Movie> getMovieList();
	public Movie getMovieDetail(Movie movie);
	public ArrayList<Movie> getScreenList(Movie movie);
	public int isMovie(Movie movie);
}



