package com.university.project.agendat2.handler.subscriber;

import android.util.Log;
import android.view.View;
import android.widget.Toast;
import com.university.project.agendat2.model.Contact;
import com.university.project.agendat2.ui.activity.BaseActivity;
import com.university.project.agendat2.ui.activity.DiaryActivity;
import com.university.project.agendat2.ui.adapter.ContactAdapter;
import com.university.project.agendat2.handler.error.RetrofitException;
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

    public static ListContactsSubscriber create(BaseActivity activity){
        return new ListContactsSubscriber((DiaryActivity)activity);
    }

    @Override
    public void onSuccess(ArrayList<Contact> contacts) {
        try {
            final ContactAdapter adapter;
            activity.hideProgress();
            if (contacts != null && !contacts.isEmpty()){
                activity.setVisibilityLayoutNoData(View.INVISIBLE);
                adapter = new ContactAdapter(activity, contacts);
            }else{
                activity.setVisibilityLayoutNoData(View.VISIBLE);
                adapter = new ContactAdapter(activity, new ArrayList<Contact>());
            }
            activity.setAdapter(adapter);
        }catch (Exception e){
            Log.e(TAG, "MyError:onSuccess", e);
        }
    }

    @Override
    public void onError(Throwable throwable) {
        //TODO obtener mensaje de error en la peticion http
        activity.hideProgress();
        RetrofitException error = (RetrofitException) throwable;
        if (error.getKind() == RetrofitException.Kind.NETWORK){
            Toast.makeText(activity, error.getMessage(), Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(activity, "No se pudo listar los contactos", Toast.LENGTH_SHORT).show();
        }
        Log.e(TAG, "MyError:onError", throwable);
    }
}
