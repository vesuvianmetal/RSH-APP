package rsh.rsh;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    TextInputLayout txtinputusuario;
    TextInputLayout txtinputcontra;
    Button btniniciar;
    TextView txtpueba;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        btniniciar = findViewById(R.id.btn_log_iniciar);

        btniniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                txtinputusuario = findViewById(R.id.log_edit_usuario);
                txtinputcontra = findViewById(R.id.log_edit_contra);

                txtpueba = findViewById(R.id.tvprueba);

                final String usuario = txtinputusuario.getEditText().getText().toString();
                final String contra = txtinputcontra.getEditText().getText().toString();

                txtpueba.setText(usuario + contra);

                Response.Listener<String> respondeListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonresponse = new JSONObject(response);

                            boolean success = jsonresponse.getBoolean("success");
                            if (success) {

                             //   String name = jsonresponse.getString("username");




                                    Intent intent = new Intent(Login.this, Traslados.class);

                                    Login.this.startActivity(intent);



                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };


                LoginRequest loginRequest = new LoginRequest(usuario , contra, respondeListener );
                RequestQueue queue = Volley.newRequestQueue(Login.this);
                queue.add(loginRequest);


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

