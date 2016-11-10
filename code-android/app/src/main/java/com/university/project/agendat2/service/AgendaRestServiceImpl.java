package com.university.project.agendat2.service;

import android.util.Log;

import com.university.project.agendat2.handler.error.RxErrorHandlingCallAdapterFactory;
import com.university.project.agendat2.model.Contact;
import com.university.project.agendat2.model.User;
import com.university.project.agendat2.ui.activity.BaseActivity;
import com.university.project.agendat2.handler.subscriber.AuthUserSubscriber;
import com.university.project.agendat2.handler.subscriber.InsertContactSubscriber;
import com.university.project.agendat2.handler.subscriber.InsertUserSubscriber;
import com.university.project.agendat2.handler.subscriber.ListContactsSubscriber;
import java.util.ArrayList;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by santiago on 05/11/16.
 * Reference guide: https://guides.codepath.com/android/Consuming-APIs-with-Retrofit
 */

public class AgendaRestServiceImpl implements IAgendaRestService {
    private static final String TAG = AgendaRestServiceImpl.class.getSimpleName();

    private IAgendaRetrofitService service;
    private BaseActivity activity;

    public AgendaRestServiceImpl(BaseActivity activity){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(IAgendaRetrofitService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxErrorHandlingCallAdapterFactory.create())
                .build();
        service = retrofit.create(IAgendaRetrofitService.class);
        this.activity = activity;
    }

    @Override
    public void authUser(String username, String password){
        try {
            activity.showProgress();
            Observable<User> call = service.authUser(username, password);
            call.toSingle().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(AuthUserSubscriber.create(activity));
        }catch (Exception e){
            Log.e(TAG, "MyError:authUser", e);
        }
    }

    @Override
    public void listContacts(int idUser){
        try {
            activity.showProgress();
            Observable<ArrayList<Contact>> call = service.listContacts(idUser);
            call.toSingle().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(ListContactsSubscriber.create(activity));
        }catch (Exception e){
            Log.e(TAG, "MyError:listContacts", e);
        }
    }

    @Override
    public void insertContact(int idUser, Contact contact) {
        try {
            activity.showProgress();
            Observable<Integer> call = service.insertContact(idUser, contact);
            call.toSingle().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(InsertContactSubscriber.create(activity, contact));
        }catch (Exception e){
            Log.e(TAG, "MyError:insertContact", e);
        }
    }

    @Override
    public void insertUser(User user) {
        try {
            activity.showProgress();
            Observable<Integer> call = service.insertUser(user);
            call.toSingle().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(InsertUserSubscriber.create(activity));
        }catch (Exception e){
            Log.e(TAG, "MyError:insertUser", e);
        }
    }
}
