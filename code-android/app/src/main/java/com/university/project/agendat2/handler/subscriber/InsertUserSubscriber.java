package com.university.project.agendat2.handler.subscriber;

import android.util.Log;
import android.widget.Toast;

import com.university.project.agendat2.ui.activity.BaseActivity;
import com.university.project.agendat2.ui.activity.RegisterActivity;
import com.university.project.agendat2.handler.error.RetrofitException;
import rx.SingleSubscriber;

/**
 * Created by santiago on 07/11/16.
 *
 */

public class InsertUserSubscriber extends SingleSubscriber<Integer> {
    private static final String TAG = InsertUserSubscriber.class.getSimpleName();

    private RegisterActivity activity;

    private InsertUserSubscriber(RegisterActivity activity){
        this.activity = activity;
    }

    public static InsertUserSubscriber create(BaseActivity activity){
        return new InsertUserSubscriber((RegisterActivity)activity);
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
        //TODO obtener mensaje de error en la peticion http
        activity.hideProgress();
        RetrofitException error = (RetrofitException) throwable;
        if (error.getKind() == RetrofitException.Kind.NETWORK){
            Toast.makeText(activity, error.getMessage(), Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(activity, "No se puedo guardar en la base de datos", Toast.LENGTH_SHORT).show();
        }
        Log.e(TAG, "MyError:onError", error);
    }
}
