package br.senai.sc.cadastrodeeventos;

import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
//esteregg
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import br.senai.sc.cadastrodeeventos.DB.EventoDAO;
import br.senai.sc.cadastrodeeventos.modelo.Evento;


public class CadastroEventoActivity extends AppCompatActivity {

    private final int RESULT_CODE_NOVO_EVENTO = 10;
    private final int RESULT_CODE_EVENTO_ATUALIZADO = 20;

    private boolean edicao = false;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_evento);
        setTitle("Cadastro de Evento");

        carregarEvento();
    }

    public void carregarEvento(){
        Intent intent = getIntent();
        if(intent != null && intent.getExtras() != null && intent.getExtras().get("eventoEdicao") != null) {

            Evento evento = (Evento) intent.getExtras().get("eventoEdicao");

            EditText editTextNome = findViewById(R.id.editText_nome);
            EditText editTextLocal = findViewById(R.id.editText_local);
            EditText editTextData = findViewById(R.id.editText_data);
            editTextNome.setText(evento.getNome());
            editTextLocal.setText(evento.getLocal());
            editTextData.setText(evento.getData());
            id = evento.getId();
        }
    }

    public void onClickSalvar(View v){
        EditText editTextNome = findViewById(R.id.editText_nome);
        EditText editTextLocal = findViewById(R.id.editText_local);
        EditText editTextData = findViewById(R.id.editText_data);

        String nome = editTextNome.getText().toString();
        String local = editTextLocal.getText().toString();
        String data = editTextData.getText().toString();

        Evento evento = new Evento(id, nome, local, data);
        EventoDAO produtoDAO = new EventoDAO(getBaseContext());
        boolean salvou = produtoDAO.salvar(evento);

        if(salvou) {
            finish();
        }else{
            Toast.makeText(CadastroEventoActivity.this, "Erro ao salvar", Toast.LENGTH_LONG).show();
        }

    }

    public void onClickVoltar(View v){
        finish();
    }

}