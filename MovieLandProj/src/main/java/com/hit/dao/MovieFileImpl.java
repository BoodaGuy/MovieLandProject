package com.hit.dao;

import com.hit.dm.movie.Movie;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class MovieFileImpl implements IDao<Integer, Movie> {

    //TODO dont forget to close file

    private String m_file_path;
    private ObjectOutputStream m_objectOutputStream;
    private ObjectInputStream m_objectInputStream;

    public MovieFileImpl(String file_path) {
        this.m_file_path = file_path;
        boolean isEmptyFile = isEmptyDb();
        try {
            if (isEmptyFile) {
                this.m_objectOutputStream = new ObjectOutputStream(new FileOutputStream(m_file_path));
                m_objectOutputStream.writeObject(new Movie());
                m_objectOutputStream.flush();
                closeObjectOutStream();
            } else {
                this.m_objectInputStream = new ObjectInputStream(new FileInputStream(m_file_path));
                closeObjectInputStream();
            }
        } catch (IOException e) {
            System.out.println("file not open");
        }
    }

    private void closeObjectInputStream() {
        if (m_objectInputStream != null) {
            try {
                m_objectInputStream.close();
            } catch (IOException e) {
                System.out.println("IO exception");
            }
        }
    }

    private void closeObjectOutStream() {
        if (m_objectOutputStream != null) {
            try {
                m_objectOutputStream.close();
            } catch (IOException e) {
                System.out.println("IO exception");
            }
        }
    }


    @Override
    public List<Movie> getAll() throws IllegalArgumentException, IOException, ClassNotFoundException {
        List<Movie> allMoviesList = new ArrayList<>();
        try {
            while (true) {
                Movie movieFromDb = (Movie) m_objectInputStream.readObject();
                allMoviesList.add(movieFromDb);
            }
        } catch (EOFException eofe) {
            System.out.println(eofe.getMessage());
        } finally {
            closeObjectInputStream();
        }
        return allMoviesList;
    } //V


    @Override
    public List<Movie> getElementsByCount(List<Movie> elementsList, int elementsCount) throws IllegalArgumentException, NullPointerException {
        List<Movie> allMoviesList = new ArrayList<>();
        int i = 0;
        try {
            while (i < elementsCount) {
                Movie movieFromDb = (Movie) m_objectInputStream.readObject();
                allMoviesList.add(movieFromDb);
                i++;
            }
            closeObjectInputStream();
        } catch (EOFException eof) {
            if (allMoviesList.size() < elementsCount) {
                System.out.println("there are only" + allMoviesList.size());
                closeObjectInputStream();
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Error reading movie from database", e);
        } finally {
            closeObjectInputStream();
        }
        return allMoviesList;
    } //V

    @Override
    public Movie getElementById(Integer elementId) throws NoSuchElementException, IOException, ClassNotFoundException {
        List<Movie> allMovies = getAll();
        for (Movie movie : allMovies) {
            if (movie.getMovieId() == elementId) {
                return movie;
            }
        }
        throw new NoSuchElementException("Movie with ID " + elementId + " not found");
    }

    @Override
    public void addElement(Movie movie) throws IOException, Exception {
        try {
            m_objectOutputStream = new ObjectOutputStream(new FileOutputStream(m_file_path));
            m_objectOutputStream.writeObject(movie);
            m_objectOutputStream.flush();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        } finally {
            closeObjectOutStream();
        }
    }


    @Override
    public void deleteElement(Movie movieToDelete) throws IOException, Exception {
        List<Movie> allMovies = getAll();
        if (allMovies.contains(movieToDelete)) {
            allMovies.remove(movieToDelete);
            updateElementsInFile(allMovies,m_file_path);
            closeObjectOutStream();
        } else {
            throw new IllegalArgumentException("Movie not found: " + movieToDelete);
        }
    }




    @Override
    public void updateElement(Movie movieToUpdate) throws IOException, Exception {
        List<Movie> allMovies = getAll();
        if (allMovies.contains(movieToUpdate)) {
            updateElementsInFile(allMovies,m_file_path);
            closeObjectOutStream();
        } else {
            throw new IllegalArgumentException("Movie not found: " + movieToUpdate);
        }
    }

    private boolean isEmptyDb() {
        File dbMovieFile = new File(m_file_path);
        return !dbMovieFile.exists() || dbMovieFile.length() == 0;
    }
}
