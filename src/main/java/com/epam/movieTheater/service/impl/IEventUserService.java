package com.epam.movieTheater.service.impl;

public interface IEventUserService extends IAuditoriumEventUserService {

    void save();

    void remove();

    Object getById(Integer id);

}