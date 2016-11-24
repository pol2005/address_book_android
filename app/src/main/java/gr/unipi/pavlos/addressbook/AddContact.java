package gr.unipi.pavlos.addressbook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import gr.unipi.pavlos.addressbook.data.DBManager;

public class AddContact extends AppCompatActivity {

    private DBManager dbManager;
    private Button insert;
    private Button clear;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_operations);
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
        insert = (Button)findViewById(R.id.save_button);
        clear = (Button)findViewById(R.id.reset_button);
        dbManager = new DBManager(this);
        dbManager.open();

        insert.setOnClickListener(new View.OnClickListener() {
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
                dbManager.insert(i_name,i_phone,i_kinito,i_address,i_email,i_birth_date);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                name.setText("");
                phone.setText("");
                kinito.setText("");
                address.setText("");
                email.setText("");
                birth_date.setText("");
            }
        });
    }
}
