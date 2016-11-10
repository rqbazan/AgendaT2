package com.university.project.agendat2.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

/**
 * Inspirado por WandoSession de Grupo Wando S.A.C
 */

public class Session {
    private final static String TAG = Session.class.getSimpleName();
    private final static String KEY = "com.university.project.agendat2.session";
    private Context context;
    private Map<String, Object> data;

    //{ Singleton
    private static Session instance;

    private Session(Context context){
        this.context = context;
    }

    public static Session getSession(Context context){
        if (instance == null){
            instance = new Session(context);
            instance.load();
        }
        return instance;
    }
    //} Singleton

    private void load(){
        try {
            String jsonString = context.getSharedPreferences(TAG, Context.MODE_PRIVATE).getString(KEY, "");
            data = new HashMap<String, Object>();
            if (!jsonString.isEmpty()) {
                Gson gson = new Gson();
                data = gson.fromJson(jsonString, data.getClass());
            }
        }catch (Exception e){
            Log.e(TAG, "MyError:load", e);
        }
    }

    public void update(){
        try{
            Gson gson = new Gson();
            String jsonString =  gson.toJson(data);
            SharedPreferences preferences = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(KEY, jsonString);
            editor.apply();
        }catch(Exception e){
            Log.e(TAG, "MyError:update", e);
        }
    }

    public void clear(){
        if (data != null)
            data.clear();
    }

    public Object get(String key){
        try {
            return data.get(key);
        }catch (Exception e){
            throw e;
        }
    }

    public void put(String key, Object value){
        data.put(key, value);
    }
}
