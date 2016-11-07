package com.university.project.agendat2.ui.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ListView;

import com.university.project.agendat2.R;
import com.university.project.agendat2.model.Contact;
import com.university.project.agendat2.model.User;
import com.university.project.agendat2.service.AgendaAgendaRestServiceImpl;
import com.university.project.agendat2.service.IAgendaRestService;
import com.university.project.agendat2.ui.adapter.ContactAdapter;

import com.university.project.agendat2.util.Session;
import butterknife.BindView;
import butterknife.ButterKnife;


public class DiaryActivity extends BaseActivity {
    private static final String TAG = DiaryActivity.class.getSimpleName();
    public static final int RC_ADD_NEW_CONTACT = 1;

    @BindView(R.id.list_diary) ListView list_diary;
    @BindView(R.id.layout_no_data) FrameLayout layout_no_data;

    private IAgendaRestService restService;
    private ContactAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        ButterKnife.bind(this);
        init();
        loadData();
    }

    private void init(){
        session = Session.getSession(this);
        restService = new AgendaAgendaRestServiceImpl(this);
        progressDialog = new ProgressDialog(this);
        progressDialog.setTitle("Cargando");
        progressDialog.setMessage("Cargando lista de contactos...");
        progressDialog.setCancelable(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_diary, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_new_contact:
                try {
                    Intent intent = new Intent(DiaryActivity.this, RegisterActivity.class);
                    intent.putExtra(RegisterActivity.PARAM_VISIBLE_USER_DATA_INPUTS, false);
                    startActivityForResult(intent, RC_ADD_NEW_CONTACT);
                }catch (Exception e){
                    Log.e(TAG, "MyError:onOptionsItemSelected", e);
                }
                return true;
            case R.id.action_logout:
                if (session != null)
                    session.clear();
                finish();
                return true;
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_ADD_NEW_CONTACT){
            if (resultCode == RESULT_OK){
                Contact contact = (Contact)data.getSerializableExtra("newContact");
                adapter.add(contact);
            }
        }
    }

    private void loadData(){
        try {
            User user = (User) session.get("user");
            restService.listContacts(user.getId());
        }catch (Exception e){
            Log.e(TAG, "MyError:loadData", e);
        }
    }

    public void setAdapter(ContactAdapter adapter) {
        this.adapter = adapter;
        list_diary.setAdapter(adapter);
    }

    public void setVisibilityLayoutNoData(int value){
        layout_no_data.setVisibility(value);
    }
}
