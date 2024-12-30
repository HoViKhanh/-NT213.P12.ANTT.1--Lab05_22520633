package com.example.lab3_1;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private EditText editTextId, editTextName, editTextPhone;
    private Button addButton;
    private ListView listViewContacts;
    private ArrayAdapter<String> adapter;
    private List<String> contactDisplayList;
    private List<Contact> contactList;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        editTextId = findViewById(R.id.editTextId);
        editTextName = findViewById(R.id.editTextName);
        editTextPhone = findViewById(R.id.editTextPhone);
        addButton = findViewById(R.id.addButton);
        listViewContacts = findViewById(R.id.listViewContacts);

        // Initialize database and data list
        db = new DatabaseHandler(this);
        contactDisplayList = new ArrayList<>();
        contactList = new ArrayList<>();

        // Load existing contacts
        loadContacts();

        // Set onClickListener for the Add button
        addButton.setOnClickListener(v -> {
            String idText = editTextId.getText().toString().trim();
            String name = editTextName.getText().toString().trim();
            String phone = editTextPhone.getText().toString().trim();

            // Validate inputs
            if (idText.isEmpty() || name.isEmpty() || phone.isEmpty()) {
                Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            int id = Integer.parseInt(idText);

            // Add new contact to the database
            Contact contact = new Contact(id, name, phone);
            db.addContact(contact);

            // Refresh the contact list and clear input fields
            loadContacts();
            clearFields();
        });

        // Set onItemClickListener for ListView
        listViewContacts.setOnItemClickListener((parent, view, position, id) -> {
            Contact selectedContact = contactList.get(position);
            showDetailDialog(selectedContact);
        });
    }

    // Load contacts from database and update ListView
    private void loadContacts() {
        contactDisplayList.clear();
        contactList = db.getAllContacts();

        for (Contact contact : contactList) {
            contactDisplayList.add("ID: " + contact.getId() + ", Name: " + contact.getName());
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, contactDisplayList);
        listViewContacts.setAdapter(adapter);
    }

    // Clear input fields
    private void clearFields() {
        editTextId.setText("");
        editTextName.setText("");
        editTextPhone.setText("");
    }

    // Show detail dialog
    private void showDetailDialog(Contact contact) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Contact Details");

        // Create the message to display
        String message = "ID: " + contact.getId() + "\n" +
                "Name: " + contact.getName() + "\n" +
                "Phone number: " + contact.getPhoneNumber();
        builder.setMessage(message);

//        //delete
//        builder.setNegativeButton("Delete", (dialog, which) -> {
//            db.deleteContact(contact.getId());
//            loadContacts(); // Cập nhật danh sách sau khi xóa
//            Toast.makeText(this, "Contact deleted", Toast.LENGTH_SHORT).show();
//        });

        //edit button
        builder.setNeutralButton("Edit", (dialog, which) -> {
            showEditDialog(contact); // Mở dialog chỉnh sửa
        });

        // Add a close button
        builder.setPositiveButton("OK", (dialog, which) -> dialog.dismiss());

        // Show the dialog
        AlertDialog dialog = builder.create();
        dialog.show();

//        builder.setNegativeButton("Delete", (dialog, which) -> {
//            db.deleteContact(contact.getId());
//            loadContacts(); // Cập nhật danh sách
//        });

    }

    private void showEditDialog(Contact contact) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Contact");

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(16, 16, 16, 16);

        EditText editTextId = new EditText(this);
        editTextId.setHint("ID");
        editTextId.setInputType(android.text.InputType.TYPE_CLASS_PHONE);
        editTextId.setText(contact.getId());
        layout.addView(editTextId);

        EditText editTextName = new EditText(this);
        editTextName.setHint("Name");
        editTextName.setText(contact.getName());
        layout.addView(editTextName);

        EditText editTextPhone = new EditText(this);
        editTextPhone.setHint("Phone Number");
        editTextPhone.setInputType(android.text.InputType.TYPE_CLASS_PHONE);
        editTextPhone.setText(contact.getPhoneNumber());
        layout.addView(editTextPhone);

        builder.setView(layout);

        builder.setPositiveButton("Save", (dialog, which) -> {
            String newName = editTextName.getText().toString().trim();
            String newPhone = editTextPhone.getText().toString().trim();

            if (newName.isEmpty() || newPhone.isEmpty()) {
                Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
                return;
            }

            // Cập nhật thông tin liên hệ
            contact.setName(newName);
            contact.setPhoneNumber(newPhone);
            db.updateContact(contact);

            // Refresh danh sách sau khi cập nhật
            loadContacts();
            Toast.makeText(this, "Contact updated", Toast.LENGTH_SHORT).show();

//            builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
//
//            AlertDialog dialog = builder.create();
//            dialog.show();
        });
    }
}
