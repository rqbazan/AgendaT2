package com.university.project.agendat2.util.subscriber;

import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.university.project.agendat2.model.User;
import com.university.project.agendat2.ui.activity.DiaryActivity;
import com.university.project.agendat2.ui.activity.LoginActivity;
import com.university.project.agendat2.util.error.RetrofitException;

import rx.SingleSubscriber;

/**
 * Created by santiago on 07/11/16.
 *
 */

public class AuthUserSubscriber extends SingleSubscriber<User> {
    private static final String TAG = AuthUserSubscriber.class.getSimpleName();

    private LoginActivity activity;

    private  AuthUserSubscriber(LoginActivity activity){
        this.activity = activity;
    }

    public static AuthUserSubscriber create(LoginActivity activity){
        return new AuthUserSubscriber(activity);
    }

    @Override
    public void onSuccess(User user) {
        try{
            activity.hideProgress();
            activity.getSession().put("user", user);
            Intent intent = new Intent(activity, DiaryActivity.class);
            activity.startActivity(intent);
        }catch (Exception e){
            Log.e(TAG, "MyError:onSuccess", e);
        }
    }

    @Override
    public void onError(Throwable throwable) {
        //TODO obtener mensaje de error en la peticion http
        activity.hideProgress();
        RetrofitException error = (RetrofitException) throwable;
        Toast.makeText(activity, error.getResponse().message(), Toast.LENGTH_SHORT).show();
        Log.e(TAG, "MyError:onError", error);
    }
}
