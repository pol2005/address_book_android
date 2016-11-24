package gr.unipi.pavlos.addressbook;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import gr.unipi.pavlos.addressbook.data.DBManager;

public class ViewContact extends AppCompatActivity {

    private DBManager dbManager;
    private TextView txt_name;
    private TextView txt_phone;
    private TextView txt_kinito;
    private TextView txt_address;
    private TextView txt_email;
    private TextView txt_birth_date;
    private Button edit;
    private Button delete;
    private Button start;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_contact);
        setTitle("Λεπτομέρειες Επαφής");
        txt_name = (TextView)findViewById(R.id.name);
        txt_phone = (TextView)findViewById(R.id.phone);
        txt_kinito = (TextView)findViewById(R.id.kinito);
        txt_address = (TextView)findViewById(R.id.address);
        txt_email = (TextView)findViewById(R.id.email);
        txt_birth_date = (TextView)findViewById(R.id.birth_date);
        edit = (Button)findViewById(R.id.edit_button);
        delete = (Button)findViewById(R.id.delete_button);;
        start = (Button)findViewById(R.id.start_button);
        Bundle extras = getIntent().getExtras();
        id = extras.getInt("id");
        Log.d("id",String.valueOf(id));
        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetchSingle(id);
        String name = "Ονομα: "+cursor.getString(1);
        String phone = "Τηλέφωνο: "+cursor.getString(2);
        String kinito = "Κινητό: "+cursor.getString(3);
        String address = "Διεύθυνση: "+cursor.getString(4);
        String email = "Email: "+cursor.getString(5);
        String birth_date = "Ημ-νία Γέννησης: "+cursor.getString(6);
        txt_name.setText(name);
        txt_phone.setText(phone);
        txt_kinito.setText(kinito);
        txt_address.setText(address);
        txt_email.setText(email);
        txt_birth_date.setText(birth_date);

        edit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent modify_intent = new Intent(getApplicationContext(), EditContact.class);
                modify_intent.putExtra("id", id);
                startActivity(modify_intent);
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dbManager.delete(id);
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                Context context = getApplicationContext();
                Toast.makeText(context, "Επιτυχής διαγραφή", Toast.LENGTH_LONG).show();
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
