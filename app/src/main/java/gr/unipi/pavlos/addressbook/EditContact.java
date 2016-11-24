package gr.unipi.pavlos.addressbook;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import gr.unipi.pavlos.addressbook.data.DBManager;

public class EditContact extends AppCompatActivity {

    private DBManager dbManager;
    private Button update;
    private TextView txt_name;
    private TextView txt_phone;
    private TextView txt_kinito;
    private TextView txt_address;
    private TextView txt_email;
    private TextView txt_birth_date;
    private EditText name;
    private EditText phone;
    private EditText kinito;
    private EditText address;
    private EditText email;
    private EditText birth_date;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_contact);

        txt_name = (TextView)findViewById(R.id.name);
        txt_phone = (TextView)findViewById(R.id.phone);
        txt_kinito = (TextView)findViewById(R.id.kinito);
        txt_address = (TextView)findViewById(R.id.address);
        txt_email = (TextView)findViewById(R.id.email);
        txt_birth_date = (TextView)findViewById(R.id.birth_date);
        name = (EditText)findViewById(R.id.editText_name);
        phone = (EditText)findViewById(R.id.editText_phone);
        kinito = (EditText)findViewById(R.id.editText_kinito);
        address = (EditText)findViewById(R.id.editText_address);
        email = (EditText)findViewById(R.id.editText_email);
        birth_date = (EditText)findViewById(R.id.editText_birth_date);
        update = (Button)findViewById(R.id.update_button);
        Bundle extras = getIntent().getExtras();
        id = extras.getInt("id");
        Log.d("id",String.valueOf(id));
        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetchSingle(id);
        name.setText(cursor.getString(1));
        phone.setText(cursor.getString(2));
        kinito.setText(cursor.getString(3));
        address.setText(cursor.getString(4));
        email.setText(cursor.getString(5));
        birth_date.setText(cursor.getString(6));

        update.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                final String i_name = name.getText().toString();
                final String i_phone = phone.getText().toString();
                final String i_kinito = kinito.getText().toString();
                final String i_address = address.getText().toString();
                final String i_email = email.getText().toString();
                final String i_birth_date = birth_date.getText().toString();
                if(TextUtils.isEmpty(i_name)){
                    name.setError("Αδειο πεδίο!");
                    return;
                }
                if(TextUtils.isEmpty(i_phone)){
                    phone.setError("Αδειο πεδίο!");
                    return;
                }
                if(TextUtils.isEmpty(i_kinito)){
                    kinito.setError("Αδειο πεδίο!");
                    return;
                }
                if(TextUtils.isEmpty(i_address)){
                    address.setError("Αδειο πεδίο!");
                    return;
                }
                if(TextUtils.isEmpty(i_email)){
                    email.setError("Αδειο πεδίο!");
                    return;
                }
                if(TextUtils.isEmpty(i_birth_date)){
                    birth_date.setError("Αδειο πεδίο!");
                    return;
                }
                dbManager.update(id,i_name,i_phone,i_kinito,i_address,i_email,i_birth_date);
                Intent view_intent = new Intent(getApplicationContext(), ViewContact.class);
                view_intent.putExtra("id", id);
                startActivity(view_intent);

            }
        });
    }
}
