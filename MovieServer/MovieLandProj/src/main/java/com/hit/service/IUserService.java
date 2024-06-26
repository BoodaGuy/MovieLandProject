package com.hit.service;

import com.hit.dm.movie.Movie;
import com.hit.dm.user.User;

import java.io.IOException;
import java.util.List;

public interface IUserService  {
    List<User>  getAllUsersFromDb() throws IOException, ClassNotFoundException;
    User getUserById(int userId) throws IOException, ClassNotFoundException;
    void register(User user) throws Exception;
    void updateUser(User user) throws Exception;
    void addToWatchlist(User user, Movie movie) throws Exception;
    void removeFromWatchlist(User user, Movie movie) throws Exception;
    void deleteUser(User user) throws Exception;
    User login(String username, String password);
    boolean isAdmin(User user);

}
