package com.example.gestiondeclasse;

import android.content.Intent;
<<<<<<< HEAD
=======
import android.net.Uri;
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
<<<<<<< HEAD
=======
import androidx.appcompat.widget.AppCompatButton;
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63

public class DetailsCourseActivity extends AppCompatActivity {

    private GridView gridCompetencesCourse;
    private GridView gridInfosCompetencesCourse;
<<<<<<< HEAD
=======
    private AppCompatButton course_ressourse;
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details_course);

        // Initialisation des composants de l'interface
        gridCompetencesCourse = findViewById(R.id.gridCompetencesCourse);
        gridInfosCompetencesCourse = findViewById(R.id.gridInfosCompetencesCourse);
<<<<<<< HEAD
=======
        course_ressourse = findViewById(R.id.course_ressourse);
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63

        // Création et attribution des adaptateurs au GridView
        CompetancesCourseAdapter adapter1 = new CompetancesCourseAdapter(this, getCompetences());
        gridCompetencesCourse.setAdapter(adapter1);

        InfosCompetencesCourseAdapter adapter2 = new InfosCompetencesCourseAdapter(this, getCompetencesTitles(), getCompetencesValues());
        gridInfosCompetencesCourse.setAdapter(adapter2);
<<<<<<< HEAD
=======

        // Gestion du clic sur le bouton "course_ressourse"
        course_ressourse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lien par défaut vers Google Drive
                String driveLink = "https://drive.google.com/drive/folders/1QR3PjR8Zws84z81nvgDqM3SnEzQJNOgF";  // Remplace ce lien par ton lien réel
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(driveLink));
                startActivity(intent);
            }
        });

>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63
        // Configuration du bouton retour
        ImageView iconBack = findViewById(R.id.icon_back); // Déplacement ici dans onCreate
        iconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
<<<<<<< HEAD
                // Rediriger vers DashboardActivity
=======
                // Rediriger vers CoursesActivity
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63
                Intent intent = new Intent(DetailsCourseActivity.this, CoursesActivity.class);
                startActivity(intent);
            }
        });
    }

    // Méthode pour obtenir une liste de compétences
    private String[] getCompetences() {
        return new String[]{
                "Html", "Css", "Js", "Figma",
                "React", "PHP", "SQL"
        };
    }

    // Méthode pour obtenir les titres des compétences
    private String[] getCompetencesTitles() {
        return new String[]{
                "DS1", "DS2", "Sessions", "Absences"
        };
    }

    // Méthode pour obtenir les valeurs des compétences
    private String[] getCompetencesValues() {
        return new String[]{
                "18", "17", "07", "02"
        };
    }
}
