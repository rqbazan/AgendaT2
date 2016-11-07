package com.university.project.agendat2.service;

import com.university.project.agendat2.model.Contact;
import com.university.project.agendat2.model.Person;
import com.university.project.agendat2.model.User;

import java.util.ArrayList;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by santiago on 07/11/16.
 */

interface IAgendaRetrofitService {
    String BASE_URL = "http://40.112.60.102:8082/agenda/";

    @GET("restauthuser")
    Observable<User> authUser(@Query("username") String username, @Query("password") String password);

    @GET("restlistcontacts")
    Observable<ArrayList<Contact>> listContacts(@Query("idUser") int idUser);

    @Headers({"Accept: application/json; charset=UTF-8", "Content-Type: application/json; charset=UTF-8"})
    @POST("restinsertcontact")
    Observable<Integer> insertContact(@Query("idUser") int idUser, @Body Contact contact);

    @Headers({"Accept: application/json", "Content-Type: application/json"})
    @POST("restinsertuser")
    Observable<Integer> insertUser(@Body User user);
}
