package com.example.reto1;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.reto1.databinding.FragmentPerfilEditBinding;
import com.google.gson.Gson;

import java.io.File;


public class EditProfileFragment extends Fragment {

    private FragmentPerfilEditBinding binding;
    private OnProfile listener;
    private ImageButton imgEditBtn;
    private File file;
    private String uri;
    private View view;
    private Profile profile;
    private int fotos;

    //lo utilizamos cuando queremos respuesta de la actividad que lanzamos
    private ActivityResultLauncher<Intent> desicion;
    private ActivityResultLauncher<Intent> cameralauncher;
    private ActivityResultLauncher<Intent> galerialauncher;



    public EditProfileFragment() {
        // Required empty public constructor
        fotos = 0;
    }

    public static EditProfileFragment newInstance() {
        EditProfileFragment fragment = new EditProfileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        desicion = registerForActivityResult(new StartActivityForResult(),this::onDesicionResult);
        cameralauncher = registerForActivityResult(new StartActivityForResult(),this::onCameraResult);
        galerialauncher = registerForActivityResult(new StartActivityForResult(),this::onGalleryResult);


        binding = FragmentPerfilEditBinding.inflate(inflater, container, false);
        view = binding.getRoot();
        imgEditBtn = binding.imgEditBtn;

        imgEditBtn.setOnClickListener(this::desicion);
        if(uri!=null){
            Uri uri2 = Uri.parse(uri);
            imgEditBtn.setImageURI(uri2);
        }
        binding.editInfoBtn.setOnClickListener(v->{

            String title = binding.titleNeg.getText().toString();
            String description = binding.descripcionText.getText().toString();

            Profile profile = new Profile(title,description,uri);

            listener.onProfile(profile);

        });
        uri  = profile.getUri();

        binding.descripcionText.setText(profile.getDescription());
        binding.titleNeg.setText(profile.getTitle());
        if(uri != null){
            binding.imgEditBtn.setImageURI(Uri.parse(uri));
        }

        return view;
    }



    private void desicion(View view) {
        Intent intent = new Intent(getContext(),DesicionFoto.class);
        desicion.launch(intent);
    }

    private void onDesicionResult(ActivityResult result){
        //0 camara 1 galeria
        if(result.getResultCode() == RESULT_OK){
            if(result.getData().getExtras().getInt("select") == 0){
                openCamera(view);
            }else{
                openGallery(view);
            }
        }

    }

    private void openGallery(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        galerialauncher.launch(intent);
    }
    private  void onGalleryResult(ActivityResult result) {
        if(result.getResultCode() == RESULT_OK){
            uri = result.getData().getData().toString();

          imgEditBtn.setImageURI(result.getData().getData());
        }
    }


    private void openCamera(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = new File(getContext().getExternalFilesDir(null)+"/photo"+ fotos + ".png");
        fotos++;
        //File -> Uri
        Uri uri = FileProvider.getUriForFile(getContext(),getContext().getPackageName(),file);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,uri);
        cameralauncher.launch(intent);

    }
    private void onCameraResult(ActivityResult result) {
        if( result.getResultCode()  == RESULT_OK){
           //para la foto pequena THUMBNAIL
          //Bitmap bitmap = (Bitmap) result.getData().getExtras().get("data");
          //imgEditBtn.setImageBitmap(bitmap);
            //para carga la foto completa
            Bitmap  bitmap = BitmapFactory.decodeFile(file.getPath());

            //97dp,68dp
            //thumbnail = Bitmap.createScaledBitmap(bitmap,bitmap.getWidth()/4,bitmap.getHeight()/4,true);
            Uri uri2 = FileProvider.getUriForFile(getContext(),getContext().getPackageName(),file);
            uri = uri2.toString();
            imgEditBtn.setImageURI(uri2);
           // imgEditBtn.setImageBitmap(thumbnail);

        }else if (result.getResultCode()  == RESULT_CANCELED){
            Toast.makeText(getContext(),"Operacion cancelada",Toast.LENGTH_LONG).show();
        }
    }

    public void setListener(OnProfile listener) {
        this.listener = listener;
    }

    public interface OnProfile{
        void onProfile(Profile profile);
    }

    public FragmentPerfilEditBinding getBinding() {
        return binding;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}