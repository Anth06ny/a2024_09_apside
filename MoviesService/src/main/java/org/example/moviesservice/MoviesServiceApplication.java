package org.example.moviesservice;

import org.example.moviesservice.model.Movie;
import org.example.moviesservice.model.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MoviesServiceApplication implements CommandLineRunner {

    @Autowired
    private MovieRepository movieRepository;

    public static void main(String[] args) {
        SpringApplication.run(MoviesServiceApplication.class, args);
    }

    @Override
    public void run(String... args)  {
        if(movieRepository.count() ==  0){
            movieRepository.save(new Movie(null, "Inception", 220));
            movieRepository.save(new Movie(null, "Scream", 165));
            movieRepository.save(new Movie(null, "Memento", 183));
        }
    }
}
