package com.example.gestiondeclasse;

<<<<<<< HEAD
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
=======
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

<<<<<<< HEAD
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Objects;

import Retrofit.ApiService;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

=======
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63
public class ContactActivity extends AppCompatActivity {

    private Spinner contactSpinner;
    private EditText subjectEditText, messageEditText;
<<<<<<< HEAD
    private Uri fileUri; // URI du fichier sélectionné
    AppCompatButton addFilesButton, sendButton;

    // Déclaration de l'ActivityResultLauncher
    private ActivityResultLauncher<Intent> filePickerLauncher;
=======
    private AppCompatButton addFilesButton, sendButton;
    private Uri fileUri;  // URI du fichier sélectionné
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        // Initialiser les vues
        contactSpinner = findViewById(R.id.contact_spinner);
        subjectEditText = findViewById(R.id.edit_subject);
        messageEditText = findViewById(R.id.edit_message);
        addFilesButton = findViewById(R.id.add_files_button);
<<<<<<< HEAD
        sendButton= findViewById(R.id.send_button);

        // Initialiser l'ActivityResultLauncher
        filePickerLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                        fileUri = result.getData().getData(); // Récupérer l'URI du fichier
                        Toast.makeText(ContactActivity.this, "File selected", Toast.LENGTH_SHORT).show();
=======
        sendButton = findViewById(R.id.send_button);

        // Configurer le Spinner
        SpinnerUtils.setupSpinner(
                this,
                contactSpinner,
                R.array.Spinner_professeurs,
                android.R.layout.simple_spinner_item,
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        String selectedTeacher = parent.getItemAtPosition(position).toString();
                        Toast.makeText(ContactActivity.this, "Selected: " + selectedTeacher, Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                        // Aucune action nécessaire si rien n'est sélectionné
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63
                    }
                }
        );

<<<<<<< HEAD
        // Ajouter un écouteur pour le bouton "Add files"
        addFilesButton.setOnClickListener(v -> openFilePicker());

        // Ajouter un écouteur pour le bouton "Send"
        sendButton.setOnClickListener(v -> sendEmailWithAttachment());

        // Configuration du bouton retour
        ImageView iconBack = findViewById(R.id.icon_back);
        iconBack.setOnClickListener(v -> {
            // Rediriger vers DashboardActivity
            Intent intent = new Intent(ContactActivity.this, DashboardActivity.class);
            startActivity(intent);
        });
    }

    // Méthode pour ouvrir le sélecteur de fichiers
    private void openFilePicker() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("*/*"); // Permet de choisir tous types de fichiers
        filePickerLauncher.launch(intent); // Utiliser l'ActivityResultLauncher
    }

    // Méthode pour envoyer l'email avec une pièce jointe
    private void sendEmailWithAttachment() {
        String subject = subjectEditText.getText().toString().trim();
        String message = messageEditText.getText().toString().trim();
        String selectedTeacherEmail = contactSpinner.getSelectedItem().toString(); // Récupérez l'adresse email correcte ici.

        if (subject.isEmpty() || message.isEmpty()) {
            Toast.makeText(ContactActivity.this, "Please fill in all fields!", Toast.LENGTH_SHORT).show();
            return;
        }

        // Convertir les champs texte en RequestBody
        RequestBody to = RequestBody.create(selectedTeacherEmail, MediaType.get("text/plain"));
        RequestBody subjectRequestBody = RequestBody.create(subject, MediaType.get("text/plain"));
        RequestBody messageRequestBody = RequestBody.create(message, MediaType.get("text/plain"));

        // Vérifier si un fichier a été sélectionné
        if (fileUri != null) {
            File file = new File(getRealPathFromURI(fileUri));
            RequestBody fileRequestBody = RequestBody.create(file, MediaType.get(Objects.requireNonNull(getContentResolver().getType(fileUri))));
            MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), fileRequestBody);
            // Créer l'instance Retrofit
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://100.101.56.21:3000")  // Remplacer par l'URL de votre serveur Node.js
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            ApiService apiService = retrofit.create(ApiService.class);

            // Appel Retrofit pour envoyer l'email avec la pièce jointe
            apiService.sendEmailWithAttachment(to, subjectRequestBody, messageRequestBody, filePart)
                    .enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                Toast.makeText(ContactActivity.this, "Email sent successfully!", Toast.LENGTH_SHORT).show();
                                // Vider les champs subject et message après envoi de l'email
                                subjectEditText.setText("");  // Vider le champ "Subject"
                                messageEditText.setText("");  // Vider le champ "Message"
                            } else {
                                Toast.makeText(ContactActivity.this, "Failed to send email", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                            Toast.makeText(ContactActivity.this, "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
        } else {
            // Si aucun fichier n'est sélectionné
            Toast.makeText(ContactActivity.this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }

    // Méthode pour récupérer le chemin réel du fichier à partir de l'URI
    private String getRealPathFromURI(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        String fileName = System.currentTimeMillis() + ""; // Nom unique pour le fichier

        File cacheDir = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS);
        if (cacheDir == null) {
            Toast.makeText(this, "Unable to access storage", Toast.LENGTH_SHORT).show();
            return null;
        }

        File file = new File(cacheDir, fileName);
        try (InputStream inputStream = contentResolver.openInputStream(uri);
             FileOutputStream outputStream = new FileOutputStream(file)) {

            byte[] buffer = new byte[1024];
            int bytesRead;
            while (true) {
                assert inputStream != null;
                if ((bytesRead = inputStream.read(buffer)) == -1) break;
                outputStream.write(buffer, 0, bytesRead);
            }
            return file.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error copying file", Toast.LENGTH_SHORT).show();
            return null;
=======
        // Pré-sélectionner une personne par défaut dans le Spinner
        contactSpinner.setSelection(0); // Change l'index si nécessaire, ici c'est l'index 0 (premier élément)

        // Ajouter un écouteur pour le bouton "Add files"
        addFilesButton.setOnClickListener(v -> {
            // Ouvrir le sélecteur de fichiers
            Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.setType("*/*");  // Permet de choisir tous types de fichiers
            startActivityForResult(intent, 1);  // 1 est un code de requête arbitraire
        });

        // Ajouter un écouteur pour le bouton "Send"
        sendButton.setOnClickListener(v -> {
            String subject = subjectEditText.getText().toString().trim();
            String message = messageEditText.getText().toString().trim();
            String selectedTeacher = contactSpinner.getSelectedItem().toString();

            if (subject.isEmpty() || message.isEmpty()) {
                Toast.makeText(ContactActivity.this, "Please fill in all fields!", Toast.LENGTH_SHORT).show();
            } else {
                // Créer un Intent pour envoyer un e-mail sans choisir de client email
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("message/rfc822"); // Type pour les emails

                // Remplir les champs avec le sujet, message et email de destination
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{"mohammed07.benali00@gmail.com"});  // Remplacer recipient@example.com par l'email réel
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
                emailIntent.putExtra(Intent.EXTRA_TEXT, message);

                // Joindre un fichier si sélectionné
                if (fileUri != null) {
                    emailIntent.putExtra(Intent.EXTRA_STREAM, fileUri);
                }

                // Vérifier si il existe des applications pouvant gérer l'email
                try {
                    startActivity(Intent.createChooser(emailIntent, "Send email via"));
                } catch (android.content.ActivityNotFoundException e) {
                    Toast.makeText(ContactActivity.this, "No email clients found.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Configuration du bouton retour
        ImageView iconBack = findViewById(R.id.icon_back); // Déplacement ici dans onCreate
        iconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Rediriger vers DashboardActivity
                Intent intent = new Intent(ContactActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });
    }

    // Méthode qui capture le résultat du sélecteur de fichiers
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            if (data != null) {
                fileUri = data.getData();  // Récupérer l'URI du fichier sélectionné
                Toast.makeText(ContactActivity.this, "File selected: " + fileUri, Toast.LENGTH_SHORT).show();
            }
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63
        }
    }
}
