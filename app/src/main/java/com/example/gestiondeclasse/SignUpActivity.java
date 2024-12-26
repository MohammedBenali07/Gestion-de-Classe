package com.example.gestiondeclasse;

<<<<<<< HEAD
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
=======
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

<<<<<<< HEAD
public class SignUpActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner_status, spinner_career;
=======
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.regex.Pattern;

public class SignUpActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner spinner_status, spinner_career;
    private EditText editTextName, editTextSurname, editTextEmail, editTextPassword, birthdayInput;
    private Button buttonSignUp, profileImgInput;
    private TextView textError;
    private ImageView profileImageView;

    private String selectedStatus, selectedCareer, birthday;
    private Bitmap profileImageBitmap = null;
    private static final int PICK_IMAGE_REQUEST = 1;

    private DatabaseHelper databaseHelper;
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

<<<<<<< HEAD
        // Initialiser les Spinners
        spinner_status = findViewById(R.id.status_spinner);
        spinner_career = findViewById(R.id.career_spinner);

        // Configurer les adapters pour chaque Spinner
        ArrayAdapter<CharSequence> statusAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.status_spinner, // Array pour status_spinner
                R.layout.spinners_item  // Assurez-vous que ce layout existe
=======
        // Initialize views
        editTextName = findViewById(R.id.nameInput);
        editTextSurname = findViewById(R.id.surnameInput);
        editTextEmail = findViewById(R.id.emailInput);
        editTextPassword = findViewById(R.id.passwordInput);
        buttonSignUp = findViewById(R.id.signUp_button);
        birthdayInput = findViewById(R.id.birthdayInput);
        profileImgInput = findViewById(R.id.profileImgInput);
        spinner_status = findViewById(R.id.status_spinner);
        spinner_career = findViewById(R.id.career_spinner);
        textError = findViewById(R.id.text_error);
        profileImageView = findViewById(R.id.profileImageView);

        // Initialize the database through the singleton
        databaseHelper = DatabaseHelper.getInstance(this);

        // Set up adapters for Spinners
        ArrayAdapter<CharSequence> statusAdapter = ArrayAdapter.createFromResource(
                this,
                R.array.status_spinner,
                android.R.layout.simple_spinner_item
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63
        );
        statusAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_status.setAdapter(statusAdapter);

        ArrayAdapter<CharSequence> careerAdapter = ArrayAdapter.createFromResource(
                this,
<<<<<<< HEAD
                R.array.career_spinner, // Array pour career_spinner
                R.layout.spinners_item
=======
                R.array.career_spinner,
                android.R.layout.simple_spinner_item
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63
        );
        careerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_career.setAdapter(careerAdapter);

<<<<<<< HEAD
        // Définir les listeners pour les deux Spinners
        spinner_status.setOnItemSelectedListener(this);
        spinner_career.setOnItemSelectedListener(this);
=======
        spinner_status.setOnItemSelectedListener(this);
        spinner_career.setOnItemSelectedListener(this);

        buttonSignUp.setOnClickListener(v -> signUpStudent());
        profileImgInput.setOnClickListener(v -> openImagePicker());
        birthdayInput.setOnClickListener(v -> openDatePicker());
    }

    private void openImagePicker() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            try {
                profileImageBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                profileImageView.setImageBitmap(profileImageBitmap);
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Error loading image", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void signUpStudent() {
        String name = editTextName.getText().toString().trim();
        String surname = editTextSurname.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        birthday = birthdayInput.getText().toString().trim();

        textError.setText("");

        if (name.isEmpty() || surname.isEmpty() || email.isEmpty() || password.isEmpty() || birthday.isEmpty() || selectedStatus == null || selectedCareer == null) {
            textError.setText("All fields are required");
            textError.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
            return;
        }

        if (!isValidEmail(email)) {
            textError.setText("Invalid email address");
            textError.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
            return;
        }

        byte[] profileImageByteArray = null;
        if (profileImageBitmap != null) {
            profileImageByteArray = convertBitmapToByteArray(profileImageBitmap);
        }

        long userId = databaseHelper.addUser(name, surname, email, password, selectedStatus, selectedCareer, birthday, profileImageByteArray);

        if (userId != -1) {
            Toast.makeText(this, "Registration successful!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            textError.setText("Registration failed");
            textError.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
        }
    }

    private boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }

    private void openDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this,
                R.style.CustomDatePicker,
                (view, selectedYear, selectedMonth, selectedDay) -> birthdayInput.setText(selectedDay + "/" + (selectedMonth + 1) + "/" + selectedYear),
                year, month, day
        );
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
<<<<<<< HEAD
        // Identifier quel Spinner a déclenché l'événement
        if (parent.getId() == R.id.status_spinner) {
            String selectedStatus = parent.getItemAtPosition(position).toString();
            Toast.makeText(this, "Statut sélectionné : " + selectedStatus, Toast.LENGTH_SHORT).show();
        } else if (parent.getId() == R.id.career_spinner) {
            String selectedCareer = parent.getItemAtPosition(position).toString();
            Toast.makeText(this, "Carrière sélectionnée : " + selectedCareer, Toast.LENGTH_SHORT).show();
=======
        if (parent.getId() == R.id.status_spinner) {
            selectedStatus = parent.getItemAtPosition(position).toString();
        } else if (parent.getId() == R.id.career_spinner) {
            selectedCareer = parent.getItemAtPosition(position).toString();
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63
        }
    }

    @Override
<<<<<<< HEAD
    public void onNothingSelected(AdapterView<?> parent) {
        // Action si rien n'est sélectionné
=======
    public void onNothingSelected(AdapterView<?> parent) {}

    private byte[] convertBitmapToByteArray(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        return byteArrayOutputStream.toByteArray();
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63
    }
}
