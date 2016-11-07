package com.university.project.agendat2.ui.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.university.project.agendat2.R;
import com.university.project.agendat2.model.Contact;
import com.university.project.agendat2.model.SexType;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by santiago on 03/11/16.
 *
 */

public class ContactAdapter extends ArrayAdapter<Contact> {
    public ContactAdapter(Context context, ArrayList<Contact> model) {
        super(context, 0, model);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Contact contact = getItem(position);
        Holder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_contact, parent, false);
            holder = new Holder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (Holder) convertView.getTag();
        }
        holder.populate(contact);
        return convertView;
    }

    class Holder{
        @BindView(R.id.tv_fullname) TextView tv_fullname;
        @BindView(R.id.tv_letter_icon) TextView tv_letter_icon;
        @BindView(R.id.tv_cellphone_number) TextView tv_cellphone_number;
        @BindView(R.id.tv_email) TextView tv_email;

        Holder(View layout){
            ButterKnife.bind(this, layout);
        }

        void populate(Contact contact){
            try {
                Drawable background;

                tv_fullname.setText(contact.getName() + " " + contact.getLastName());
                tv_email.setText(contact.getEmail());
                tv_cellphone_number.setText(contact.getCellphoneNumber());

                tv_letter_icon.setText(String.valueOf(contact.getName().charAt(0)).toUpperCase());
                if(contact.getSex() == SexType.FEMALE.asChar())
                    background = ContextCompat.getDrawable(ContactAdapter.this.getContext(), R.drawable.dr_circle_female);
                else if(contact.getSex() == SexType.MALE.asChar())
                    background = ContextCompat.getDrawable(ContactAdapter.this.getContext(), R.drawable.dr_circle_male);
                else
                    background = ContextCompat.getDrawable(ContactAdapter.this.getContext(), R.drawable.dr_circle_default);
                tv_letter_icon.setBackground(background);
            }catch (Exception e){
                Log.e("Holder", "MyError:", e);
            }
        }
    }
}
