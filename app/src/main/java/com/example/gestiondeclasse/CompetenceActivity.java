package com.example.gestiondeclasse;

import android.app.Dialog;
import android.content.Intent;
<<<<<<< HEAD
import android.os.Bundle;
import android.view.View;
=======
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
<<<<<<< HEAD
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CompetenceActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private String[] skillNames = {"Java", "Kotlin", "XML", "Android", "SQLite", "UI Design"};
    private int[] skillLogos = {
            R.drawable.java,
            R.drawable.java,
            R.drawable.java,
            R.drawable.java,
            R.drawable.java,
            R.drawable.java
    };
    private int[] skillProgress = {90, 80, 70, 85, 75, 95}; // Progression des compétences

    private CompetenceAdapter adapter;

=======
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class CompetenceActivity extends AppCompatActivity {

    private String[] skillNames = {"Java", "Kotlin", "XML", "Android", "SQLite", "UI Design"};
    private int[] skillProgress = {80, 70, 50, 90, 60, 75};

    private ArrayList<Competence> competenceList = new ArrayList<>();
    private CompetenceAdapter adapter;

    private static final int REQUEST_CODE_PERMISSION = 1;
    private static final int PICK_IMAGE_REQUEST = 2;

    private ImageView dialogImagePreview;
    private int userId;
    private String userName, userEmail, userSurname;

    private DatabaseHelper dbHelper;

>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competence);

<<<<<<< HEAD
        // Configurer l'adaptateur pour le GridView
        GridView gridView = findViewById(R.id.gridCompetences);
        adapter = new CompetenceAdapter(this, skillNames, skillLogos, skillProgress);
        gridView.setAdapter(adapter);

        // Bouton pour ajouter une compétence
        ImageView addCompetenceButton = findViewById(R.id.ajouter_competence);
        addCompetenceButton.setOnClickListener(v -> showAddCompetenceDialog());

        // Configuration du bouton retour
        ImageView iconBack = findViewById(R.id.icon_back); // Déplacement ici dans onCreate
        iconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Rediriger vers DashboardActivity
                Intent intent = new Intent(CompetenceActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });
    }

    // Méthode pour afficher le dialog
    private void showAddCompetenceDialog() {
        Dialog dialog = new Dialog(this); // Optionnel : Appliquer un style
        dialog.setContentView(R.layout.dialog_ajouter_competence);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_dialog);

        // Récupérer les vues du dialog
        EditText inputName = dialog.findViewById(R.id.input_name);
        Spinner careerSpinner = dialog.findViewById(R.id.career_spinner);
        Spinner coursesSpinner = dialog.findViewById(R.id.courses_spinner);
        Button btnSave = dialog.findViewById(R.id.btn_save);
        Button btnCancel = dialog.findViewById(R.id.btn_cancel);

        // Vérifiez que les Spinners ne sont pas null avant de les utiliser
        if (careerSpinner != null && coursesSpinner != null) {
            // Configurer les Spinners dans le dialog
            SpinnerUtils.setupSpinner(
                    this,
                    careerSpinner,
                    R.array.career_spinner,
                    R.layout.spinners_item,
                    null
            );

            SpinnerUtils.setupSpinner(
                    this,
                    coursesSpinner,
                    R.array.courses_spinner,
                    R.layout.spinners_item,
                    null
            );
        } else {
            Toast.makeText(this, "Erreur d'initialisation des spinners", Toast.LENGTH_SHORT).show();
            return; // Ne pas poursuivre si un spinner est null
        }

        // Action pour le bouton "Enregistrer"
        btnSave.setOnClickListener(v -> {
            String name = inputName.getText().toString().trim();
            String career = careerSpinner.getSelectedItem().toString();
            String course = coursesSpinner.getSelectedItem().toString();

=======
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE},
                    REQUEST_CODE_PERMISSION);
        }

        dbHelper = new DatabaseHelper(this);

        GridView gridView = findViewById(R.id.gridCompetences);
        adapter = new CompetenceAdapter(this, competenceList);
        gridView.setAdapter(adapter);

        // Charger les compétences par défaut
        loadDefaultCompetences();

        ImageView addCompetenceButton = findViewById(R.id.ajouter_competence);
        addCompetenceButton.setOnClickListener(v -> showAddCompetenceDialog());

        ImageView iconBack = findViewById(R.id.icon_back);
        iconBack.setOnClickListener(v -> {
            Intent intent = new Intent(CompetenceActivity.this, DashboardActivity.class);
            startActivity(intent);
        });

        Intent intent = getIntent();
        userId = intent.getIntExtra("userId", -1);
        userName = intent.getStringExtra("userName");
        userEmail = intent.getStringExtra("userEmail");
        userSurname = intent.getStringExtra("userSurName");
    }

    // Charger les compétences par défaut pour les utilisateurs
    private void loadDefaultCompetences() {
        for (int i = 0; i < skillNames.length; i++) {
            Competence competence = new Competence(i + 1, skillNames[i], skillProgress[i]);
            competenceList.add(competence);
        }
        adapter.notifyDataSetChanged();
    }

    // Afficher la boîte de dialogue pour ajouter une nouvelle compétence
    private void showAddCompetenceDialog() {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_ajouter_competence);
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_dialog);

        EditText inputName = dialog.findViewById(R.id.input_name);
        Spinner careerSpinner = dialog.findViewById(R.id.career_spinner);
        Button btnSave = dialog.findViewById(R.id.btn_save);
        Button btnCancel = dialog.findViewById(R.id.btn_cancel);
        dialogImagePreview = dialog.findViewById(R.id.competenceLogo);

        // Initialisation du spinner
        SpinnerUtils.setupSpinner(this, careerSpinner, R.array.career_spinner, R.layout.spinners_item, null);

        btnSave.setOnClickListener(v -> {
            String name = inputName.getText().toString().trim();
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63
            if (name.isEmpty()) {
                Toast.makeText(this, "Le nom de la compétence est requis", Toast.LENGTH_SHORT).show();
                return;
            }

<<<<<<< HEAD
            // Ajouter la nouvelle compétence
            addNewCompetence(name, career, course);

            // Fermer le dialog
            dialog.dismiss();
        });

        // Action pour le bouton "Annuler"
=======
            // Ajout de la compétence et enregistrement dans la base de données
            addNewCompetence(name, 0);
            dialog.dismiss();
        });

>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63
        btnCancel.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

<<<<<<< HEAD
    // Ajouter une compétence
    private void addNewCompetence(String name, String career, String course) {
        // Exemple pour ajouter une compétence au GridView (vous pouvez adapter selon votre logique)
        Toast.makeText(this, "Compétence ajoutée : " + name + " - Carrière : " + career + " - Cours : " + course, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // Ceci gère la sélection dans le Spinner du dialog
        if (parent.getId() == R.id.career_spinner) {
            String selectedCareer = parent.getItemAtPosition(position).toString();
            Toast.makeText(this, "Carrière sélectionnée : " + selectedCareer, Toast.LENGTH_SHORT).show();
        } else if (parent.getId() == R.id.courses_spinner) {
            String selectedCourse = parent.getItemAtPosition(position).toString();
            Toast.makeText(this, "Cours sélectionné : " + selectedCourse, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Aucune action
=======
    // Ajouter une compétence à la base de données et à la liste
    private void addNewCompetence(String name, int progress) {
        for (Competence competence : competenceList) {
            if (competence.getName().equalsIgnoreCase(name)) {
                Toast.makeText(this, "Cette compétence existe déjà.", Toast.LENGTH_SHORT).show();
                return;
            }
        }

        int newId = generateNewCompetenceId();
        Competence newCompetence = new Competence(newId, name, progress);

        competenceList.add(newCompetence);
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "Compétence ajoutée avec succès : " + name, Toast.LENGTH_SHORT).show();
    }

    // Méthode pour générer un ID unique
    private int generateNewCompetenceId() {
        return competenceList.size() + 1;
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63
    }
}
