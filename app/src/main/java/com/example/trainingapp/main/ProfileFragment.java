package com.example.trainingapp.main;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.CursorIndexOutOfBoundsException;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.trainingapp.notifications.NotificationsActivity;
import com.example.trainingapp.R;
import com.example.trainingapp.auth.login.LoginActivity;
import com.example.trainingapp.model.CurrentUser;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileFragment extends Fragment implements View.OnClickListener {

    private final static Integer PICK_IMAGE_CODE = 1234;

    @BindView(R.id.avatar)
    CircleImageView avatar;

    @BindView(R.id.addPhoto)
    ImageView addPhoto;

    @BindView(R.id.bnSignOut)
    TextView bnSignOut;

    @BindView(R.id.bnNotifications)
    TextView bnNotifications;

    @BindView(R.id.height_edit)
    EditText height;

    @BindView(R.id.weight_edit)
    EditText weight;

    @BindView(R.id.need_weight_edit)
    EditText needWeight;

    @BindView(R.id.name)
    TextView name;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews();
    }

    @SuppressLint("SetTextI18n")
    private void initViews() {
        bnSignOut.setOnClickListener(this);
        bnNotifications.setOnClickListener(this);
        name.setText(CurrentUser.name);
        height.setText(CurrentUser.height.toString() + " см");
        weight.setText(CurrentUser.weight.toString() + " кг");
        needWeight.setText(CurrentUser.needWeight.toString() + " кг");
        if (CurrentUser.imageUrl != null)
            if (!CurrentUser.imageUrl.equals(""))
                showAvatar(CurrentUser.imageUrl);

        addPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Выберите фото"), PICK_IMAGE_CODE);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == PICK_IMAGE_CODE) {
                Uri path = data.getData();
                writeFile(path);
            }
        }
    }

    private void writeFile(Uri path) {
        final FirebaseStorage storage = FirebaseStorage.getInstance();
        final StorageReference reference = storage.getReference().child(CurrentUser.uuid);
        reference.putFile(path)
                .continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
                    @Override
                    public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                        if (task.isSuccessful())
                            return reference.getDownloadUrl();
                        throw task.getException();
                    }
                }).addOnCompleteListener(new OnCompleteListener<Uri>() {
                    @Override
                    public void onComplete(@NonNull Task<Uri> task) {
                        if (task.isSuccessful()) {
                            CurrentUser.imageUrl = task.getResult().toString();
                            showAvatar(task.getResult().toString());
                            writeDownloadUrl(task.getResult());
                        }
                    }
                });
    }

    private void writeDownloadUrl(Uri result) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.getReference()
                .child("users")
                .child(CurrentUser.uuid)
                .child("imageUrl")
                .setValue(result.toString());
    }

    private void showAvatar(String imageUrl) {
        Glide.with(this).load(imageUrl).into(avatar);
    }

    private void signOut() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        auth.signOut();
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bnSignOut:
                signOut();
                break;

            case R.id.bnNotifications:
                Intent intent = new Intent(getActivity(), NotificationsActivity.class);
                startActivity(intent);
                break;
        }
    }
}
