package com.example.mycrudapp;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private EditText nameEditText, phoneEditText;
    private ListView listView;
    private ArrayAdapter<Contact> adapter;
    private List<Contact> contactList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseHelper = new DatabaseHelper(this);
        nameEditText = findViewById(R.id.nameEditText);
        phoneEditText = findViewById(R.id.phoneEditText);
        Button addButton = findViewById(R.id.addButton);
        listView = findViewById(R.id.listView);
        loadContacts();
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameEditText.getText().toString();
                String phone = phoneEditText.getText().toString();
                if (!name.isEmpty() && !phone.isEmpty()) {
                    databaseHelper.addContact(new Contact(0, name, phone));
                    loadContacts();
                    nameEditText.setText("");
                    phoneEditText.setText("");
                }
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long
                    id) {
                Contact selectedContact = contactList.get(position);
                nameEditText.setText(selectedContact.getName());
                phoneEditText.setText(selectedContact.getPhone());
// Supprimer le contact
                databaseHelper.deleteContact(selectedContact.getId());
                loadContacts();
            }

        });
    }
    private void loadContacts() {
        contactList = databaseHelper.getAllContacts();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,
                contactList);
        listView.setAdapter(adapter);
    }
}