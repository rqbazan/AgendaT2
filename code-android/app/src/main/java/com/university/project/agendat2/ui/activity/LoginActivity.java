package com.university.project.agendat2.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.university.project.agendat2.R;
import com.university.project.agendat2.service.AgendaAgendaRestServiceImpl;
import com.university.project.agendat2.service.IAgendaRestService;
import com.university.project.agendat2.util.Session;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity{
    private static final String TAG = LoginActivity.class.getSimpleName();

    @BindView(R.id.et_username) EditText et_username;
    @BindView(R.id.et_password) EditText et_password;

    private IAgendaRestService restService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        init();
    }

    protected void init() {
        et_password.setTypeface(Typeface.DEFAULT);
        session = Session.getSession(this);
        restService = new AgendaAgendaRestServiceImpl(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Cargando");
        progressDialog.setMessage("Autentificación en progreso...");
        progressDialog.setCancelable(false);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(session != null)
            session.clear();
    }

    @OnClick(R.id.btn_login)
    void onClickBtnLogin(){
        try {
            String username = et_username.getText().toString().trim();
            String password = et_password.getText().toString().trim();
            restService.authUser(username, password);
        }catch (Exception e){
            Log.e(TAG, "MyError:onClickBtnLogin", e);
        }
    }

    @OnClick(R.id.tv_signup)
    void onClickTvSignup(){
        try {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            intent.putExtra(RegisterActivity.PARAM_VISIBLE_USER_DATA_INPUTS, true);
            startActivity(intent);
        }catch (Exception e){
            Log.e(TAG, "MyError:onClickTvSignup", e);
        }
    }
}
