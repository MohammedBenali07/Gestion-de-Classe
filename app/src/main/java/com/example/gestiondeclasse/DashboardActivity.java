package com.example.gestiondeclasse;

import android.content.Intent;
<<<<<<< HEAD
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ProgressBar;
=======
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ImageView;
import android.view.View;
import android.widget.Toast;
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DashboardActivity extends AppCompatActivity {
<<<<<<< HEAD
=======

    private TextView fullName;
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63
    private RecyclerView top3RecyclerView;
    private TextView overallRanking;
    private ProgressBar progressBar;
    private List<Student> students;
    private BottomNavigationView bottomNavigationView;
    private TextView pourcentageProgress;
<<<<<<< HEAD
=======
    private ImageView imageProfile;

    // Variables pour stocker les données utilisateur
    private int userId;
    private String userName, userEmail, userSurname, userBirtday;
    private byte[] userImageProfile;
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Initialisation des vues
        top3RecyclerView = findViewById(R.id.ranking_recycler_view);
        overallRanking = findViewById(R.id.overall_tanking_text);
<<<<<<< HEAD
        progressBar = findViewById(R.id.progressCircular); // Récupérer la ProgressBar
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        pourcentageProgress = findViewById(R.id.ProgressPercentage);

        // Simuler une liste d'étudiants
        students = new ArrayList<>();
        students.add(new Student("Alice", 95));
        students.add(new Student("Bob", 88));
        students.add(new Student("Charlie", 92));
        students.add(new Student("Diana", 85));
        students.add(new Student("Eve", 78));
=======
        progressBar = findViewById(R.id.progressCircular);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        pourcentageProgress = findViewById(R.id.ProgressPercentage);
        fullName = findViewById(R.id.fullName);
        imageProfile = findViewById(R.id.profileImageView);

        // Récupérer les données utilisateur transmises par Intent
        Intent intent = getIntent();
        userId = intent.getIntExtra("userId", -1);
        userName = intent.getStringExtra("userName");
        userSurname = intent.getStringExtra("userSurName");
        userEmail = intent.getStringExtra("userEmail");
        userBirtday = intent.getStringExtra("userBirthday");
        userImageProfile = intent.getByteArrayExtra("userImageProfile");

        // Vérifier que les données sont valides et les afficher
        if (userName != null && userSurname != null) {
            fullName.setText(userName + " " + userSurname);
        }

        // Afficher l'image de profil si disponible
        if (userImageProfile != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(userImageProfile, 0, userImageProfile.length);
            imageProfile.setImageBitmap(bitmap);
        } else {
            // Afficher un message Toast si l'image n'est pas disponible
            Toast.makeText(getApplicationContext(), "Aucune image de profil disponible", Toast.LENGTH_SHORT).show();
        }


        // Simuler une liste d'étudiants
        students = new ArrayList<>();
        students.add(new Student("Mohamed", 95));
        students.add(new Student("Said", 88));
        students.add(new Student("Imane", 92));
        students.add(new Student("Salma", 85));
        students.add(new Student("Mouad", 78));
        students.add(new Student("Khalid", 73));
        students.add(new Student("Samira", 68));
        students.add(new Student("Hiba", 60));
        students.add(new Student("Ridwane", 40));
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63

        // Trier la liste par score décroissant
        Collections.sort(students, (s1, s2) -> s2.getScore() - s1.getScore());

        // Configurer le RecyclerView pour afficher les trois meilleurs
        List<Student> top3 = students.subList(0, Math.min(3, students.size()));
        top3RecyclerView.setLayoutManager(new LinearLayoutManager(this));
<<<<<<< HEAD
        top3RecyclerView.setAdapter(new RankAdapter(top3, false)); // Mode compact pour le top 3
=======
        top3RecyclerView.setAdapter(new RankAdapter(top3, false));
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63

        // Ajouter un listener pour afficher le popup des classements
        overallRanking.setOnClickListener(v -> showOverallRankingPopup());

        // Mise à jour du ProgressBar en fonction du score
        updateProgressBar();

        // Configurer le BottomNavigationView pour changer d'activité
        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            if (item.getItemId() == R.id.nav_home) {
<<<<<<< HEAD
                startActivity(new Intent(DashboardActivity.this, DashboardActivity.class));
                return true;
            } else if (item.getItemId() == R.id.nav_competence) {
                startActivity(new Intent(DashboardActivity.this, CompetenceActivity.class));
                return true;
            } else if (item.getItemId() == R.id.nav_contact) {
                startActivity(new Intent(DashboardActivity.this, ContactActivity.class));
                return true;
            } else if (item.getItemId() == R.id.nav_absence) {
                startActivity(new Intent(DashboardActivity.this, CalenderActivity.class));
                return true;
            } else if (item.getItemId() == R.id.nav_profile) {
                startActivity(new Intent(DashboardActivity.this, ProfileActivity.class));
=======
                redirectToActivity(DashboardActivity.class);
                return true;
            } else if (item.getItemId() == R.id.nav_competence) {
                redirectToActivity(CompetenceActivity.class);
                return true;
            } else if (item.getItemId() == R.id.nav_contact) {
                redirectToActivity(ContactActivity.class);
                return true;
            } else if (item.getItemId() == R.id.nav_absence) {
                redirectToActivity(CalenderActivity.class);
                return true;
            } else if (item.getItemId() == R.id.nav_profile) {
                redirectToActivity(ProfileActivity.class);
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63
                return true;
            }
            return false;
        });

        TextView seeCourses = findViewById(R.id.see_courses);
<<<<<<< HEAD
        Button DiscoverTutorialsButton = findViewById(R.id.discover_tutorials);
        // Rediriger vers CoursesActivity lorsque l'utilisateur clique sur "see_courses"
        seeCourses.setOnClickListener(v -> redirectToActivity(CoursesActivity.class));
        DiscoverTutorialsButton.setOnClickListener(v -> redirectToActivity(FormationActivity.class));
    }

    private void updateProgressBar() {
        // Vérifiez si le texte contient un pourcentage valide
        try {
            String progressText = pourcentageProgress.getText().toString().trim(); // Récupérer le texte brut
            // Supprimer le symbole % si présent
            if (progressText.endsWith("%")) {
                progressText = progressText.substring(0, progressText.length() - 1);
            }
            int progressPercentage = Integer.parseInt(progressText); // Convertir en entier
            progressBar.setProgress(progressPercentage); // Mettre à jour la ProgressBar
=======
        Button discoverTutorialsButton = findViewById(R.id.discover_tutorials);

        // Rediriger vers CoursesActivity lorsque l'utilisateur clique sur "see_courses"
        seeCourses.setOnClickListener(v -> redirectToActivity(CoursesActivity.class));
        discoverTutorialsButton.setOnClickListener(v -> redirectToActivity(FormationActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Récupérer à nouveau les données utilisateur dans le cas où l'activité a été redémarrée
        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");
        userSurname = intent.getStringExtra("userSurName");

        // Vérifiez que les données sont récupérées correctement
        if (userName != null && userSurname != null) {
            fullName.setText(userName + " " + userSurname);
        }

        // Vous pouvez également récupérer l'image de profil si elle est mise à jour
        userImageProfile = intent.getByteArrayExtra("userImageProfile");
        if (userImageProfile != null) {
            Bitmap bitmap = BitmapFactory.decodeByteArray(userImageProfile, 0, userImageProfile.length);
            imageProfile.setImageBitmap(bitmap);
            imageProfile.invalidate();  // Force le redessin de l'ImageView
        }
    }

    private void updateProgressBar() {
        try {
            String progressText = pourcentageProgress.getText().toString().trim();
            if (progressText.endsWith("%")) {
                progressText = progressText.substring(0, progressText.length() - 1);
            }
            int progressPercentage = Integer.parseInt(progressText);
            progressBar.setProgress(progressPercentage);
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

<<<<<<< HEAD

    private void showOverallRankingPopup() {
        // Inflater la vue du dialog
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_overall_ranking, null);
        RecyclerView dialogRecyclerView = dialogView.findViewById(R.id.popup_recycler_view);
        Button cancelButton = dialogView.findViewById(R.id.popup_button_cancel); // Bouton de fermeture

        // Configurer le RecyclerView du dialog
        dialogRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        dialogRecyclerView.setAdapter(new RankAdapter(students, false)); // Adapter pour afficher la liste complète des étudiants

        // Créer le Dialog avec une vue personnalisée
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView)
                .setCancelable(false);  // Empêcher le dialog de se fermer en cliquant à l'extérieur

        // Créer l'AlertDialog
        final AlertDialog dialog = builder.create();

        // Ajouter un fond personnalisé au dialog
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_dialog); // Utilisez un drawable comme fond

        // Configurer le bouton de fermeture
        cancelButton.setOnClickListener(v -> dialog.dismiss());

        // Afficher le dialog
        dialog.show();
    }

    // Méthode générique pour la redirection vers une activité
    private void redirectToActivity(Class<?> targetActivity) {
        Intent intent = new Intent(DashboardActivity.this, targetActivity);
=======
    private void showOverallRankingPopup() {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_overall_ranking, null);
        RecyclerView dialogRecyclerView = dialogView.findViewById(R.id.popup_recycler_view);
        Button cancelButton = dialogView.findViewById(R.id.popup_button_cancel);

        dialogRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        dialogRecyclerView.setAdapter(new RankAdapter(students, false));

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(dialogView).setCancelable(false);

        final AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.background_dialog);

        cancelButton.setOnClickListener(v -> dialog.dismiss());

        dialog.show();
    }

    // Méthode pour inclure les données utilisateur dans l'Intent
    private void redirectToActivity(Class<?> targetActivity) {
        Intent intent = new Intent(DashboardActivity.this, targetActivity);
        intent.putExtra("userId", userId);
        intent.putExtra("userName", userName);
        intent.putExtra("userSurName", userSurname);
        intent.putExtra("userEmail", userEmail);
        intent.putExtra("userBirthday", userBirtday);
        intent.putExtra("userImageProfile", userImageProfile); // Si nécessaire
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63
        startActivity(intent);
    }
}
