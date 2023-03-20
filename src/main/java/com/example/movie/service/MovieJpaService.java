package com.example.movie.service;

 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.HttpStatus;
 import org.springframework.stereotype.Service;
 import org.springframework.web.server.ResponseStatusException;
 import java.util.*;

 import com.example.movie.repository.MovieJpaRepository;
 import com.example.movie.model.Movie;
 import com.example.movie.repository.MovieRepository;

 @Service
 public class MovieJpaService implements MovieRepository{

    @Autowired
    private MovieJpaRepository movieRepository;

    @Override 
    public ArrayList<Movie> getAllMovies(){
        List<Movie> list = movieRepository.findAll();
        return new ArrayList<>(list);
    }


    @Override 
    public Movie getMovieById(int movieId){
        try{
            Movie movie = movieRepository.findById(movieId).get();
            return movie;
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override 
    public Movie addMovie(Movie movie){
        try{
            movieRepository.save(movie);
            return movie;
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Movie updateMovie(int movieId,Movie movie){
        try{
            Movie existingMovie = movieRepository.findById(movieId).get();

            if(movie.getMovieName()!=null){
                existingMovie.setMovieName(movie.getMovieName());
            }
            if(movie.getLeadActor()!=null){
                existingMovie.setLeadActor(movie.getLeadActor());
            }
            movieRepository.save(existingMovie);
            return existingMovie;
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @Override 
    public void deleteMovie(int movieId){
        try{
            movieRepository.deleteById(movieId);
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

 }