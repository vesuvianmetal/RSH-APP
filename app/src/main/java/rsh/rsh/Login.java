package rsh.rsh;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

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


                final String usuario = txtinputusuario.getEditText().getText().toString();
                final String contra = txtinputcontra.getEditText().getText().toString();

                Response.Listener<String> respondeListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonresponse = new JSONObject(response);

                            boolean success = jsonresponse.getBoolean("success");
                            if (success) {

                                String name = jsonresponse.getString("usuario");



                                Intent intent = new Intent(Login.this, Traslados.class);
                                intent.putExtra("nombre", name);
                                ;


                                Login.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                                builder.setMessage("Error Al Iniciar Session PRRO").setNegativeButton("Retry", null).create().show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };


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

