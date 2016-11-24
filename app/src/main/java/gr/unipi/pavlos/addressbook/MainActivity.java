package gr.unipi.pavlos.addressbook;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.Locale;

import gr.unipi.pavlos.addressbook.data.AddressBookContract;
import gr.unipi.pavlos.addressbook.data.AddressDbHelper;
import gr.unipi.pavlos.addressbook.data.DBManager;

public class MainActivity extends AppCompatActivity {
    private DBManager dbManager;
    private ListView listView;
    private SimpleCursorAdapter adapter;
    final String[] from = new String[] { AddressBookContract.AddressBookEntry._ID,
            AddressBookContract.AddressBookEntry.COLUMN_NAME, AddressBookContract.AddressBookEntry.COLUMN_ADDRESS };

    final int[] to = new int[] { R.id.record_id, R.id.record_name, R.id.record_address };
    private EditText search;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Λίστα Επαφών");
        search = (EditText)findViewById(R.id.search);
        dbManager = new DBManager(this);
        dbManager.open();
        final Cursor cursor = dbManager.fetch();

        listView = (ListView) findViewById(R.id.list_view);
        listView.setEmptyView(findViewById(R.id.empty));

        adapter = new SimpleCursorAdapter(this, R.layout.activity_view_list_record, cursor, from, to, 0);
        adapter.notifyDataSetChanged();

        listView.setAdapter(adapter);
        //listView.setTextFilterEnabled(true);

        // OnCLickListiner For List Items
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long viewId) {
                TextView idTextView = (TextView) view.findViewById(R.id.record_id);

                int id = Integer.parseInt(idTextView.getText().toString());
                Intent view_intent = new Intent(getApplicationContext(), ViewContact.class);
                view_intent.putExtra("id", id);

                startActivity(view_intent);
            }
        });


        search.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1,
                                          int arg2, int arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                if(arg0.length()>2){
                    Cursor cursorFilterable = dbManager.fetchFilterable(arg0.toString());
                    adapter = new SimpleCursorAdapter(MainActivity.this, R.layout.activity_view_list_record, cursorFilterable, from, to, 0);
                    adapter.notifyDataSetChanged();

                    listView.setAdapter(adapter);
                }
                if(arg0.length()==0){
                    adapter = new SimpleCursorAdapter(MainActivity.this, R.layout.activity_view_list_record, cursor, from, to, 0);
                    adapter.notifyDataSetChanged();

                    listView.setAdapter(adapter);
                }

            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.top_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.item1){
            Intent add_intent = new Intent(getApplicationContext(), AddContact.class);
            startActivity(add_intent);
        }

        return super.onOptionsItemSelected(item);
    }

    }



