package rsh.rsh;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Traslados extends AppCompatActivity {


    TextView personaltraslado;
    ListView listView;
    Button btn_cargar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_traslados);

        personaltraslado = findViewById(R.id.nombrepersonatraslado);
        listView = findViewById(R.id.listview);
        btn_cargar = findViewById(R.id.btncargar);


        Intent intent = getIntent();
        String nombre = intent.getStringExtra("nombre");

        personaltraslado.setText(nombre);


        btn_cargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String consulta = "http://192.168.0.11/ConsultaLista.php";
                EnviarRecibirDatos(consulta);

            }

        });

    }


    public void EnviarRecibirDatos(String URL) {

        Toast.makeText(getApplicationContext(), "" + URL, Toast.LENGTH_SHORT).show();

        RequestQueue queue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                response = response.replace("][", ",");
                if (response.length() > 0) {
                    try {
                        JSONArray ja = new JSONArray(response);
                        Log.i("sizejson", "" + ja.length());
                        CargarListView(ja);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest);

    }

    public void CargarListView(JSONArray ja) {

        ArrayList<String> lista = new ArrayList<>();

        for (int i = 0; i < ja.length(); i += 9) {

            try {

                lista.add(" ID: " + ja.getString(i) + " Estado: " + ja.getString(i + 1) + " Direccion " + ja.getString(i + 2) + " Comentarios " + ja.getString(i + 3)
                        + " Ubicacion: " + ja.getString(i + 4) + " Destino: " + ja.getString(i + 5)+ " Fecha Solicitud: " + ja.getString(i + 6) + " Fecha Finzalizada: " + ja.getString(i + 7)
                        + " ID Carro:  " + ja.getString(i + 8) + " ID Personal: " + ja.getString(i + 9) + " ID Folio: " + ja.getString(i + 10));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }


        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, lista);
        listView.setAdapter(adaptador);


    }
}

