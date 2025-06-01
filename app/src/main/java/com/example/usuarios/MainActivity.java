package com.example.usuarios;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lista;
    ArrayList<User> usuarios = new ArrayList<>();
    UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lista = findViewById(R.id.listaUsuarios);
        adapter = new UserAdapter(this, usuarios);
        lista.setAdapter(adapter);

        String url = "https://jsonplaceholder.typicode.com/users";

        RequestQueue queue = Volley.newRequestQueue(this);

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                response -> {
                    try {
                        usuarios.clear();
                        for (int i = 0; i < response.length(); i++) {
                            JSONObject obj = response.getJSONObject(i);
                            String name = obj.getString("name");
                            String email = obj.getString("email");
                            String phone = obj.getString("phone");
                            String city = obj.getJSONObject("address").getString("city");
                            String company = obj.getJSONObject("company").getString("name");
                            usuarios.add(new User(name, email, phone, city, company));
                        }
                        adapter.notifyDataSetChanged();

                        // Salvando localmente
                        getSharedPreferences("usuarios_pref", MODE_PRIVATE)
                                .edit()
                                .putString("json_usuarios", response.toString())
                                .apply();

                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Erro ao processar dados JSON", Toast.LENGTH_LONG).show();
                    }
                },
                error -> {
                    error.printStackTrace();
                    Toast.makeText(this, "Erro de conexão: " + error.getMessage(), Toast.LENGTH_LONG).show();
                    carregarUsuariosOffline(); // Tenta usar cache
                }
        );

        queue.add(request);


        lista.setOnItemClickListener((parent, view, position, id) -> {
            User user = usuarios.get(position);
            Intent intent = new Intent(MainActivity.this, DetalhesActivity.class);
            intent.putExtra("nome", user.getName());
            intent.putExtra("email", user.getEmail());
            intent.putExtra("telefone", user.getPhone());
            intent.putExtra("cidade", user.getCity());
            intent.putExtra("empresa", user.getCompany());
            startActivity(intent);
        });
    }

    private void carregarUsuariosOffline() {
        String json = getSharedPreferences("usuarios_pref", MODE_PRIVATE)
                .getString("json_usuarios", null);

        if (json != null) {
            try {
                JSONArray response = new JSONArray(json);
                usuarios.clear();
                for (int i = 0; i < response.length(); i++) {
                    JSONObject obj = response.getJSONObject(i);
                    String name = obj.getString("name");
                    String email = obj.getString("email");
                    String phone = obj.getString("phone");
                    String city = obj.getJSONObject("address").getString("city");
                    String company = obj.getJSONObject("company").getString("name");
                    usuarios.add(new User(name, email, phone, city, company));
                }
                adapter.notifyDataSetChanged();
                Toast.makeText(this, "Dados carregados offline", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Erro ao carregar dados salvos", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(this, "Nenhum dado salvo encontrado", Toast.LENGTH_SHORT).show();
        }
    }
}
