package com.example.reto1;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddPublicacionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddPublicacionFragment extends Fragment {

    private ActivityResultLauncher<Intent> ubicacion;
    private ImageButton ubicacionBtn;
    private Button crearBtn, inicioBtn, finalBtn;
    private String ubicacionEvento;
    private LatLng coordenadas;
    private OnEvento listener;
    private EditText nameET;
    private TextView hayUbicacionTV;

    public AddPublicacionFragment() {
        // Required empty public constructor
    }

    public void setListener(OnEvento listener) {
        this.listener = listener;
    }

    public static AddPublicacionFragment newInstance() {
        AddPublicacionFragment fragment = new AddPublicacionFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add_publicacion, container, false);
        ubicacion = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),this::onUbicacionResult);
        ubicacionBtn = view.findViewById(R.id.ubicacionBtn);
        ubicacionBtn.setOnClickListener(this::ubicacionLauncher);
        crearBtn = view.findViewById(R.id.crearBtn);
        nameET = view.findViewById(R.id.nameET);
        hayUbicacionTV = view.findViewById(R.id.hayUbicacionTV);
        inicioBtn = view.findViewById(R.id.inicioBtn);
        finalBtn = view.findViewById(R.id.finalBtn);
        inicioBtn.setOnClickListener(this::defineStartDate);
        finalBtn.setOnClickListener(this::defineEndDate);
        crearBtn.setOnClickListener(
                (v)->{
                    String name = nameET.getText().toString();
                    String inicio = inicioBtn.getText().toString();
                    String fin = finalBtn.getText().toString();

                    Evento evento = new Evento();
                    evento.setNombreEvento(name);
                    evento.setInicio(inicio);
                    evento.setFin(fin);
                    evento.setUbicacion(ubicacionEvento);
                    evento.setCoordenadas(coordenadas);
                    listener.onEvento(evento);
                }
        );
        return view;
    }

    private void ubicacionLauncher(View view) {
        Intent intent = new Intent(getContext(),EscogerUbicacionActivity.class);
        ubicacion.launch(intent);
    }

    private void onUbicacionResult(ActivityResult result) {
        if(result.getResultCode() == RESULT_OK){
            ubicacionEvento = result.getData().getExtras().get("ubicacion").toString();
            coordenadas = (LatLng) result.getData().getExtras().get("coordenadas");
            hayUbicacionTV.setText(ubicacionEvento);
        }
    }

    public interface OnEvento{
        void onEvento(Evento evento);
    }

    private void defineStartDate(View view) {
        showDatePicker(date->{
            inicioBtn.setText(formatDate(date));
        });
    }

    private String formatDate(long date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        return sdf.format(new Date(date));
    }

    private void defineEndDate(View view) {
        showDatePicker(date -> {
            finalBtn.setText(formatDate(date));
        });
    }

    private void showDatePicker(DateDialogFragment.OnDateSelectedListener listener) {
        DateDialogFragment dialog = new DateDialogFragment();
        dialog.setListener(listener);
        dialog.show(getActivity().getSupportFragmentManager(), "dialog");
    }
}