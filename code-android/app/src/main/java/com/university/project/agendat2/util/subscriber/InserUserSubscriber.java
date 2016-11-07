package com.university.project.agendat2.util.subscriber;

import android.util.Log;
import android.widget.Toast;

import com.university.project.agendat2.ui.activity.RegisterActivity;
import com.university.project.agendat2.util.error.RetrofitException;

import rx.SingleSubscriber;

/**
 * Created by santiago on 07/11/16.
 *
 */

public class InserUserSubscriber extends SingleSubscriber<Integer> {
    private static final String TAG = InserUserSubscriber.class.getSimpleName();

    private RegisterActivity activity;

    private  InserUserSubscriber(RegisterActivity activity){
        this.activity = activity;
    }

    public static InserUserSubscriber create(RegisterActivity activity){
        return new InserUserSubscriber(activity);
    }

    @Override
    public void onSuccess(Integer newIdUser) {
        try {
            activity.hideProgress();
            activity.finish();
        }catch (Exception e){
            Log.e(TAG, "MyError:onSuccess", e);
        }
    }

    @Override
    public void onError(Throwable throwable) {
        activity.hideProgress();
        RetrofitException error = (RetrofitException) throwable;
        Toast.makeText(activity, error.getResponse().message(), Toast.LENGTH_SHORT).show();
        Log.e(TAG, "MyError:onError", error);
    }
}
