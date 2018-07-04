package rsh.rsh;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login extends AppCompatActivity {

    TextInputLayout txtinputusuario;
    TextInputLayout txtinputcontra;
    Button btniniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    txtinputusuario = findViewById(R.id.log_edit_usuario);
    txtinputcontra = findViewById(R.id.log_edit_contra);
    btniniciar = findViewById(R.id.btn_log_iniciar);

    btniniciar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {


        validarusuario();
        validarcontraseña();

        }
    });

        }

    private boolean validarusuario() {
        String inputusuario = txtinputusuario.getEditText().getText().toString().trim();

        if (inputusuario.isEmpty()) {
            txtinputusuario.setError("El Campo Esta Vacio");
            return false;
        }else {
            txtinputusuario.setError(null);
            return true;
        }
    }

    private boolean validarcontraseña(){
        String inputcontraseña = txtinputcontra.getEditText().getText().toString().trim();

        if(inputcontraseña.isEmpty()) {
            txtinputcontra.setError("El Campo Esta Vacio");

            return false;
        }else{

            txtinputcontra.setError(null);
            return true;
        }


    }








    }

