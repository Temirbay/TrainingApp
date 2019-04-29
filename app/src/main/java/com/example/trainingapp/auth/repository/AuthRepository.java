package com.example.trainingapp.auth.repository;

import com.example.trainingapp.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class AuthRepository {
    private static FirebaseAuth auth = FirebaseAuth.getInstance();
    private static FirebaseDatabase database = FirebaseDatabase.getInstance();


    public static Boolean checkLoggedIn() {
        //auth.signOut();
        return auth.getCurrentUser() != null;
    }

    public static void register (User user) {
        database.getReference().child("users").child(user.uuid).setValue(user);
    }
}
