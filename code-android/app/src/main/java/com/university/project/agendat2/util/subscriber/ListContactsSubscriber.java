package com.university.project.agendat2.util.subscriber;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.university.project.agendat2.model.Contact;
import com.university.project.agendat2.ui.activity.DiaryActivity;
import com.university.project.agendat2.ui.adapter.ContactAdapter;
import com.university.project.agendat2.util.error.RetrofitException;

import java.util.ArrayList;

import rx.SingleSubscriber;

/**
 * Created by santiago on 07/11/16.
 *
 */

public class ListContactsSubscriber extends SingleSubscriber<ArrayList<Contact>> {
    private static final String TAG = ListContactsSubscriber.class.getSimpleName();

    private DiaryActivity activity;

    private  ListContactsSubscriber(DiaryActivity activity){
        this.activity = activity;
    }

    public static ListContactsSubscriber create(DiaryActivity activity){
        return new ListContactsSubscriber(activity);
    }

    @Override
    public void onSuccess(ArrayList<Contact> contacts) {
        try {
            activity.hideProgress();
            if (contacts != null && !contacts.isEmpty()){
                activity.setVisibilityLayoutNoData(View.INVISIBLE);
                final ContactAdapter adapter = new ContactAdapter(activity, contacts);
                activity.setAdapter(adapter);
            }else{
                activity.setVisibilityLayoutNoData(View.VISIBLE);
            }
        }catch (Exception e){
            Log.e(TAG, "MyError:onSuccess", e);
        }
    }

    @Override
    public void onError(Throwable throwable) {
        activity.hideProgress();
        RetrofitException error = (RetrofitException) throwable;
        Toast.makeText(activity, error.getResponse().message(), Toast.LENGTH_SHORT).show();
        Log.e(TAG, "MyError:onError", throwable);
    }
}
