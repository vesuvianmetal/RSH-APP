package rsh.rsh;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Traslados extends AppCompatActivity {


    TextView personaltraslado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traslados);

        personaltraslado = findViewById(R.id.nombrepersonatraslado);

        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");

        personaltraslado.setText(nombre);

    }
}
