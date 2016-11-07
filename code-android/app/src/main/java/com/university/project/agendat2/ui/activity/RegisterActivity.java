package com.university.project.agendat2.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.university.project.agendat2.R;
import com.university.project.agendat2.model.Contact;
import com.university.project.agendat2.model.Person;
import com.university.project.agendat2.model.SexType;
import com.university.project.agendat2.model.User;
import com.university.project.agendat2.service.AgendaAgendaRestServiceImpl;
import com.university.project.agendat2.service.IAgendaRestService;
import com.university.project.agendat2.util.Session;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {
    private static final String TAG = RegisterActivity.class.getSimpleName();
    public static final String PARAM_VISIBLE_USER_DATA_INPUTS = "1";

    @BindView(R.id.tv_title) TextView tv_title;
    @BindView(R.id.et_username) EditText et_username;
    @BindView(R.id.et_password) EditText et_password;
    @BindView(R.id.et_confirm_password) EditText et_confirm_password;
    @BindView(R.id.et_name) EditText et_name;
    @BindView(R.id.et_last_name) EditText et_last_name;
    @BindView(R.id.et_email) EditText et_email;
    @BindView(R.id.et_cellphone_number) EditText et_cellphone_number;
    @BindView(R.id.et_dni) EditText et_dni;
    @BindView(R.id.rgroup_sex) RadioGroup rgroup_sex;

    private boolean isVisibleUserDataInputs;
    private Session session;
    private IAgendaRestService restService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        init();
        try {
            Intent intent = getIntent();
            if (intent != null){
                isVisibleUserDataInputs = intent.getExtras().getBoolean(PARAM_VISIBLE_USER_DATA_INPUTS);
                if (isVisibleUserDataInputs){
                    tv_title.setText(R.string.signup);
                    turnVisibilityViewsOfUser(View.VISIBLE);
                }else{
                    tv_title.setText(R.string.new_contact);
                    turnVisibilityViewsOfUser(View.GONE);
                }
            }
        }catch (Exception e){
            Log.e(TAG, "MyError:onCreate", e);
        }
    }

    private void init(){
        et_password.setTypeface(Typeface.DEFAULT);
        et_confirm_password.setTypeface(Typeface.DEFAULT);
        session = Session.getSession(this);
        restService = new AgendaAgendaRestServiceImpl(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Cargando");
        progressDialog.setMessage("Guardando en la base de datos...");
        progressDialog.setCancelable(false);
    }

    private void turnVisibilityViewsOfUser(int value){
        et_username.setVisibility(value);
        et_password.setVisibility(value);
        et_confirm_password.setVisibility(value);
    }

    @OnClick(R.id.btn_save)
    void onClickBtnSave(){
        if(isVisibleUserDataInputs && !et_password.getText().toString().equals(et_password.getText().toString())){
            Toast.makeText(this, R.string.msg_password, Toast.LENGTH_SHORT).show();
            return;
        }

        if(isVisibleUserDataInputs){
            final User newUser = new User();
            newUser.setUsername(et_username.getText().toString().trim());
            newUser.setPassword(et_password.getText().toString().trim());
            newUser.setPerson(createPerson());
            restService.insertUser(newUser);

        }else{
            User currentUser = (User)session.get("user");
            final Contact newContact = createContact();
            restService.insertContact(currentUser.getId(), newContact);
        }
    }

    private Person createPerson(){
        Person person = new Person();
        person.setName(et_name.getText().toString().trim());
        person.setLastName(et_last_name.getText().toString().trim());
        person.setEmail(et_email.getText().toString().trim());
        person.setCellphoneNumber(et_cellphone_number.getText().toString().trim());
        person.setDni(et_dni.getText().toString().trim());

        switch (rgroup_sex.getCheckedRadioButtonId()){
            case R.id.rbtn_male: person.setSex(SexType.MALE.asChar());
                break;
            case R.id.rbtn_female: person.setSex(SexType.FEMALE.asChar());
                break;
        }
        return person;
    }

    private Contact createContact(){
        Contact contact = new Contact();
        contact.setName(et_name.getText().toString().trim());
        contact.setLastName(et_last_name.getText().toString().trim());
        contact.setEmail(et_email.getText().toString().trim());
        contact.setCellphoneNumber(et_cellphone_number.getText().toString().trim());
        contact.setDni(et_dni.getText().toString().trim());

        switch (rgroup_sex.getCheckedRadioButtonId()){
            case R.id.rbtn_male: contact.setSex(SexType.MALE.asChar());
                break;
            case R.id.rbtn_female: contact.setSex(SexType.FEMALE.asChar());
                break;
        }
        return contact;
    }
}
