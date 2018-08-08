package com.example.administrator.jackwaiting_greendao.realm;

import android.content.Context;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by JackWaiting on 2016/8/9.
 */
public class RealmDBManager {

    private Realm myOtherRealm;
    public RealmDBManager() {
        // The RealmConfiguration is created using the builder pattern.
// The Realm file will be located in Context.getFilesDir() with name "myrealm.realm"
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("myOtherRealm.realm")
                .build();
// Use the config
        myOtherRealm = Realm.getInstance(config);
    }


    public List<RealmUser> readRealmObject(){
        RealmResults<RealmUser> results = myOtherRealm.where(RealmUser.class)
                .findAll();
        return  results;
    }

    public void beginTransaction(){
        myOtherRealm.beginTransaction();
    }

    public void saveRealmObject(RealmUser user){
        myOtherRealm.copyToRealmOrUpdate(user);

    }

    public void saveRealmObjects(List<RealmUser> users){
        myOtherRealm.beginTransaction();
        myOtherRealm.copyToRealmOrUpdate(users);
        myOtherRealm.commitTransaction();

    }

    public void commitTransaction(){
        myOtherRealm.commitTransaction();
    }

    public void deleteRealmObject(){
        final RealmResults<RealmUser> results = myOtherRealm.where(RealmUser.class)
                .findAll();
        myOtherRealm.beginTransaction();
        results.deleteAllFromRealm();
        myOtherRealm.commitTransaction();
    }

}
