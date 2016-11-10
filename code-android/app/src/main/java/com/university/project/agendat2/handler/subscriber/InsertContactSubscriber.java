package com.university.project.agendat2.handler.subscriber;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.university.project.agendat2.model.Contact;
import com.university.project.agendat2.ui.activity.BaseActivity;
import com.university.project.agendat2.ui.activity.RegisterActivity;
import com.university.project.agendat2.handler.error.RetrofitException;
import rx.SingleSubscriber;

/**
 * Created by santiago on 07/11/16.
 *
 */

public class InsertContactSubscriber extends SingleSubscriber<Integer> {
    private static final String TAG = InsertContactSubscriber.class.getSimpleName();

    private RegisterActivity activity;
    private Contact newContact;

    private InsertContactSubscriber(RegisterActivity activity, Contact contact){
        this.activity = activity;
        this.newContact = contact;
    }

    public static InsertContactSubscriber create(BaseActivity activity, Contact contact){
        return new InsertContactSubscriber((RegisterActivity)activity, contact);
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
            Toast.makeText(activity, "No se pudo guardar en la base de datos", Toast.LENGTH_SHORT).show();
        }
        Log.e(TAG, "MyError:onError", error);
    }
}
