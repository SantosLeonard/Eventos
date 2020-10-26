package br.senai.sc.cadastrodeeventos.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import br.senai.sc.cadastrodeeventos.DB.entity.EventoEntity;
import br.senai.sc.cadastrodeeventos.modelo.Evento;

import java.util.ArrayList;
import java.util.List;

public class EventoDAO {

    private final String SQL_LISTAR_TODOS = "SELECT * FROM " + EventoEntity.TABLE_NAME;
    private DBGateway dbGateway;

    public EventoDAO(Context context){
        dbGateway = DBGateway.getInstance(context);
    }

    public boolean salvar(Evento evento){
        ContentValues contentValues = new ContentValues();
        contentValues.put(EventoEntity.COLUMN_NAME_NOME, evento.getNome());
        contentValues.put(EventoEntity.COLUMN_NAME_LOCAL, evento.getLocal());
        contentValues.put(EventoEntity.COLUMN_NAME_DATA, evento.getData());

        if (evento.getId() > 0) {
            return dbGateway.getDatabase().update( EventoEntity.TABLE_NAME, contentValues,
                    EventoEntity._ID + "=?", new String[]{ String.valueOf( evento.getId() ) }) > 0;
        }
        return dbGateway.getDatabase().insert(EventoEntity.TABLE_NAME,null, contentValues) > 0;

    }

    public List<Evento> listar() {
        List<Evento> eventos = new ArrayList<>();
        Cursor cursor = dbGateway.getDatabase().rawQuery(SQL_LISTAR_TODOS, null);

        while (cursor.moveToNext()){
            int id = cursor.getInt(cursor.getColumnIndex(EventoEntity._ID));
            String nome = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_NOME));
            String local = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_LOCAL));
            String data = cursor.getString(cursor.getColumnIndex(EventoEntity.COLUMN_NAME_DATA));
            eventos.add(new Evento(id, nome, local, data));
        }
        cursor.close();
        return eventos;
    }

    public void excluir(Evento evento) {
        dbGateway.getDatabase().delete(EventoEntity.TABLE_NAME, EventoEntity._ID + "=?", new String[]{ String.valueOf( evento.getId() ) });
    }

}
