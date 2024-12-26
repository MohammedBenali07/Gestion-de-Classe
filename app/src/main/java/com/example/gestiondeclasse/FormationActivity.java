package com.example.gestiondeclasse;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
<<<<<<< HEAD
import android.widget.EditText;
import android.widget.ImageView;
=======
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FormationActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private FormationAdapter formationAdapter;
    private ArrayList<Course> courseList = new ArrayList<>();
    private ArrayList<Course> filteredCourseList = new ArrayList<>();
<<<<<<< HEAD
=======
    private ProgressBar progressBar;
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formation);

<<<<<<< HEAD
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        formationAdapter = new FormationAdapter(filteredCourseList);
        recyclerView.setAdapter(formationAdapter);

        // Fetch data from multiple Coursera API URLs
        fetchCourseData();

        // Configure back button
=======
        // Initializing views
        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.progressBar);  // Getting reference to the ProgressBar

        // Set up RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        formationAdapter = new FormationAdapter(filteredCourseList);
        recyclerView.setAdapter(formationAdapter);

        // Fetch courses from the API
        fetchCourseData();

        // Setup Back button functionality
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63
        ImageView iconBack = findViewById(R.id.icon_back);
        iconBack.setOnClickListener(v -> {
            Intent intent = new Intent(FormationActivity.this, DashboardActivity.class);
            startActivity(intent);
        });

<<<<<<< HEAD
        // Set up search functionality
        EditText searchInput = findViewById(R.id.search_formation); // Assurez-vous d'avoir un EditText avec l'id 'searchInput' dans votre layout
=======
        // Implement search functionality
        EditText searchInput = findViewById(R.id.search_formation);
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63
        searchInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                filterCourses(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });
    }

    private void fetchCourseData() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

<<<<<<< HEAD
        executor.execute(() -> {
            try {
                // URLs de l'API Coursera
=======
        // Display ProgressBar while loading data
        progressBar.setVisibility(View.VISIBLE);

        executor.execute(() -> {
            try {
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63
                String[] urls = {
                        "https://api.coursera.org/api/courses.v1?q=search&query=computer%20science&fields=name,slug,photoUrl,partnerLogo",
                        "https://api.coursera.org/api/courses.v1?q=search&query=data%20science&fields=name,slug,photoUrl,partnerLogo",
                        "https://api.coursera.org/api/courses.v1?q=search&query=programming&fields=name,slug,photoUrl"
                };

<<<<<<< HEAD
                // Pour chaque URL, on effectue la requête
                for (String urlString : urls) {
                    URL url = new URL(urlString);

                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("Accept", "application/json");

                    int responseCode = connection.getResponseCode();

                    if (responseCode == 200) { // Si la requête réussit
                        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        StringBuilder response = new StringBuilder();
                        String line;

                        while ((line = reader.readLine()) != null) {
                            response.append(line);
                        }
                        reader.close();

                        // Parse JSON
                        JSONObject jsonResponse = new JSONObject(response.toString());
                        JSONArray courses = jsonResponse.getJSONArray("elements");

                        // Pour chaque cours dans la réponse
                        for (int i = 0; i < courses.length(); i++) {
                            JSONObject course = courses.getJSONObject(i);
                            String title = course.getString("name");
                            String link = "https://www.coursera.org/learn/" + course.getString("slug");
                            String imageUrl = course.optString("photoUrl", "");

                            // Ajout à la liste des cours
                            Course newCourse = new Course(title, link, imageUrl);
                            courseList.add(newCourse);
                        }

                    } else {
                    }
                }

                // Mise à jour du RecyclerView après avoir récupéré toutes les données
                handler.post(() -> {
                    filteredCourseList.addAll(courseList);  // Afficher tous les cours au départ
                    formationAdapter.notifyDataSetChanged();
                    Toast.makeText(FormationActivity.this, "Cours récupérés avec succès!", Toast.LENGTH_SHORT).show();
                });

            } catch (Exception e) {
                handler.post(() -> Toast.makeText(FormationActivity.this, "Erreur lors de la récupération des données.", Toast.LENGTH_SHORT).show());
=======
                // Fetch data from multiple URLs
                for (String urlString : urls) {
                    fetchCourseFromApi(urlString);
                }

                // Once data is retrieved, update UI
                handler.post(() -> {
                    filteredCourseList.addAll(courseList); // Display all courses initially
                    formationAdapter.notifyDataSetChanged();
                    Toast.makeText(FormationActivity.this, "Courses retrieved successfully", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE); // Hide ProgressBar after data is fetched
                });

            } catch (Exception e) {
                // Handle errors
                handler.post(() -> {
                    Toast.makeText(FormationActivity.this, "Error retrieving data", Toast.LENGTH_SHORT).show();
                    progressBar.setVisibility(View.GONE);  // Hide ProgressBar if error occurs
                });
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63
            }
        });
    }

<<<<<<< HEAD
    // Méthode pour filtrer les cours en fonction du texte saisi
    private void filterCourses(String query) {
        filteredCourseList.clear();
        if (query.isEmpty()) {
            filteredCourseList.addAll(courseList); // Si la recherche est vide, afficher tous les cours
=======
    private void fetchCourseFromApi(String urlString) throws Exception {
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json");

        int responseCode = connection.getResponseCode();
        if (responseCode == 200) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();

            // Parse the JSON response
            JSONObject jsonResponse = new JSONObject(response.toString());
            JSONArray courses = jsonResponse.getJSONArray("elements");

            // Loop through each course and add it to the list
            for (int i = 0; i < courses.length(); i++) {
                JSONObject course = courses.getJSONObject(i);
                String title = course.getString("name");
                String link = "https://www.coursera.org/learn/" + course.getString("slug");
                String imageUrl = course.optString("photoUrl", "");

                Course newCourse = new Course(title, link, imageUrl);
                courseList.add(newCourse);
            }
        }
    }

    private void filterCourses(String query) {
        filteredCourseList.clear();
        if (query.isEmpty()) {
            filteredCourseList.addAll(courseList);  // If search is empty, show all courses
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63
        } else {
            for (Course course : courseList) {
                if (course.getTitle().toLowerCase().contains(query.toLowerCase())) {
                    filteredCourseList.add(course);
                }
            }
        }
<<<<<<< HEAD
        formationAdapter.notifyDataSetChanged();
=======
        formationAdapter.notifyDataSetChanged();  // Notify adapter of changes
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63
    }
}
