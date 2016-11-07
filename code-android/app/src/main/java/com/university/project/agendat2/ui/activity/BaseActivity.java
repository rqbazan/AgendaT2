package com.university.project.agendat2.ui.activity;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.university.project.agendat2.util.Session;


/**
 * Activity padre de todas las demás Activities
 *
 * El uso de un progress dialog es tan común que
 * es mejor tenerlo como propiedad para ser heredada lo mismo ocurre
 * con el objeto session
 */
public class BaseActivity extends AppCompatActivity{
    protected ProgressDialog progressDialog;
    protected Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public void hideProgress() {
        if(progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }

    public void showProgress() {
        if(progressDialog != null)
            progressDialog.show();
    }

    public Session getSession() {
        return session;
    }
}
