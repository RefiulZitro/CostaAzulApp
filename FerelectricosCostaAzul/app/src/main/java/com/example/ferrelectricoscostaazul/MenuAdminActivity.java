package com.example.ferrelectricoscostaazul;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MenuAdminActivity extends AppCompatActivity {

    TextView administradornombreTextView;
    ImageView administradorImageView,nuevo_pedidoImageView,reporteImageView,facturasImageView,empleadosImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_admin);

    }
}