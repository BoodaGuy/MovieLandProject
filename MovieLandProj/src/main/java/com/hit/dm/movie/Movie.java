package com.hit.dm.movie;

import com.hit.dm.actor.Actor;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Movie implements Serializable {
    private static Integer countOfRating = 0;
    private Integer m_movieId;
    private String m_movieName;
    private List<MovieRateRange> m_movieRate;
    private String m_movieSynopsis;
    private MovieCategory m_movieCategory;
    private String m_movieTime;
    private List<Actor> m_movieActors = new ArrayList<>();

    public Movie(Integer m_movieId, String m_movieName, List<MovieRateRange> m_movieRate, String m_movieSynopsis, MovieCategory m_movieCategory, String m_movieTime, List<Actor> m_movieActors) {
        this.m_movieId = m_movieId;
        this.m_movieName = m_movieName;
        this.m_movieRate = m_movieRate;
        this.m_movieSynopsis = m_movieSynopsis;
        this.m_movieCategory = m_movieCategory;
        this.m_movieTime = m_movieTime;
        this.m_movieActors = m_movieActors;
    }

    public Movie() {
        this.m_movieId = 0;
        this.m_movieName = "";
        this.m_movieRate = new ArrayList<>();
        this.m_movieSynopsis = "";
        this.m_movieCategory = MovieCategory.ACTION;
        this.m_movieTime = "";
        this.m_movieActors.add(new Actor());
    }

    public Integer getMovieId() {
        return m_movieId;
    }

    public void setMovieId(Integer m_movieId) {
        this.m_movieId = m_movieId;
    }

    public String getMovieName() {
        return m_movieName;
    }

    public void setMovieName(String m_movieName) {
        this.m_movieName = m_movieName;
    }

    public List<MovieRateRange> getMovieRate() {
        return m_movieRate;
    }

    public void setMovieRate(List<MovieRateRange> m_movieRate) {
        this.m_movieRate = m_movieRate;
    }

    public String getMovieSynopsis() {
        return m_movieSynopsis;
    }

    public void setMovieSynopsis(String m_movieSynopsis) {
        this.m_movieSynopsis = m_movieSynopsis;
    }

    public MovieCategory getMovieCategory() {
        return m_movieCategory;
    }

    public void setMovieCategory(MovieCategory m_movieCategory) {
        this.m_movieCategory = m_movieCategory;
    }

    public String getMovieTime() {
        return m_movieTime;
    }

    public void setMovieTime(String m_movieTime) {
        this.m_movieTime = m_movieTime;
    }

    public List<Actor> getMovieActors() {
        return m_movieActors;
    }

    public void setMovieActors(List<Actor> m_movieActors) {
        this.m_movieActors = m_movieActors;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Movie ID: ").append(m_movieId).append("\n");
        stringBuilder.append("Movie Name: ").append(m_movieName).append("\n");
        stringBuilder.append("Movie Rate: ").append(m_movieRate).append("\n");
        stringBuilder.append("Movie Synopsis: ").append(m_movieSynopsis).append("\n");
        stringBuilder.append("Movie Category: ").append(m_movieCategory).append("\n");
        stringBuilder.append("Movie Time: ").append(m_movieTime).append("\n");
        stringBuilder.append("Movie Actors: \n");
        for (Actor actor : m_movieActors) {
            stringBuilder.append("\t").append(actor).append("\n");
        }
        return stringBuilder.toString();
    }
}

