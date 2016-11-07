package com.university.project.agendat2.util.subscriber;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.university.project.agendat2.model.Contact;
import com.university.project.agendat2.ui.activity.RegisterActivity;
import com.university.project.agendat2.util.error.RetrofitException;

import rx.SingleSubscriber;

/**
 * Created by santiago on 07/11/16.
 *
 */

public class InserContactSubscriber extends SingleSubscriber<Integer> {
    private static final String TAG = InserContactSubscriber.class.getSimpleName();

    private RegisterActivity activity;
    private Contact newContact;

    private InserContactSubscriber(RegisterActivity activity, Contact contact){
        this.activity = activity;
        this.newContact = contact;
    }

    public static InserContactSubscriber create(RegisterActivity activity, Contact contact){
        return new InserContactSubscriber(activity, contact);
    }

    @Override
    public void onSuccess(Integer newIdContact) {
        try{
            activity.hideProgress();
            newContact.setId(newIdContact);
            Intent resultIntent = new Intent();
            resultIntent.putExtra("newContact", newContact);
            activity.setResult(Activity.RESULT_OK, resultIntent);
            activity.finish();
        }catch (Exception e){
            Log.e(TAG, "MyError:contactSubscriberOnSuccess", e);
        }
    }

    @Override
    public void onError(Throwable throwable) {
        activity.hideProgress();
        RetrofitException error = (RetrofitException) throwable;
        Toast.makeText(activity, error.getResponse().message(), Toast.LENGTH_SHORT).show();
        Log.e(TAG, "MyError:contactSubscriberOnError", error);
    }
}
