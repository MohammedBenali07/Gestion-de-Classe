package com.example.gestiondeclasse;

<<<<<<< HEAD
import android.content.Intent;
import android.net.Uri;
=======
import static androidx.core.content.ContextCompat.startActivity;

import android.content.Intent;
import android.net.Uri;
import android.util.Log;
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
<<<<<<< HEAD
=======
import android.widget.Toast;
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FormationAdapter extends RecyclerView.Adapter<FormationAdapter.FormationViewHolder> {

    private ArrayList<Course> courseList;

<<<<<<< HEAD
    // Constructeur de l'adaptateur
=======
    // Constructor for the adapter
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63
    public FormationAdapter(ArrayList<Course> courseList) {
        this.courseList = courseList;
    }

    @Override
    public FormationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
<<<<<<< HEAD
        // Inflate le layout pour chaque élément de la liste
=======
        // Inflate the layout for each item in the list
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_course, parent, false);
        return new FormationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FormationViewHolder holder, int position) {
<<<<<<< HEAD
        // Récupère un cours dans la liste
        Course course = courseList.get(position);

        // Remplir les données dans la vue
        holder.titleTextView.setText(course.getTitle());
        holder.linkTextView.setText(course.getLink());

        // Charger l'image à partir de l'URL (si présente) avec Glide
=======
        // Retrieve a course from the list
        Course course = courseList.get(position);

        // Fill data into the views
        holder.titleTextView.setText(course.getTitle());

        // Load image from URL (if present) using Glide
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63
        if (!course.getImageUrl().isEmpty()) {
            Glide.with(holder.itemView.getContext())
                    .load(course.getImageUrl())
                    .into(holder.courseImageView);
        }

<<<<<<< HEAD
        // Gérer les clics pour rediriger vers le lien de la formation
        holder.itemView.setOnClickListener(v -> {
            // Créer une Intent pour ouvrir le lien dans le navigateur
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(course.getLink()));
            // Vérifier si l'intent peut être résolu (c'est-à-dire si un navigateur est disponible)
            if (intent.resolveActivity(holder.itemView.getContext().getPackageManager()) != null) {
                holder.itemView.getContext().startActivity(intent);
=======
        // Handle clicks to redirect to the course link
        holder.itemView.setOnClickListener(v -> {
            String courseLink = course.getLink();

            // Log the URL to ensure it's correct
            Log.d("FormationAdapter", "Redirecting to: " + courseLink);

            // Ensure the URL starts with "http" or "https"
            if (courseLink != null && !courseLink.startsWith("http")) {
                courseLink = "https://" + courseLink;  // Add "https://" if missing
            }

            // Verify the URL format
            if (courseLink != null && !courseLink.isEmpty()) {
                // Create an Intent to open the link in a browser
                Uri uri = Uri.parse(courseLink);
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);

                // Check if there is a browser to handle the intent
                if (intent.resolveActivity(holder.itemView.getContext().getPackageManager()) != null) {
                    holder.itemView.getContext().startActivity(intent);  // Start activity to open the link
                } else {
                    Toast.makeText(holder.itemView.getContext(), "No browser available to open the link", Toast.LENGTH_SHORT).show();  // Show a message if no browser is available
                }
            } else {
                Toast.makeText(holder.itemView.getContext(), "Invalid course link", Toast.LENGTH_SHORT).show();  // Show a message for invalid link
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63
            }
        });
    }

    @Override
    public int getItemCount() {
        return courseList.size();
    }

<<<<<<< HEAD
    // Classe ViewHolder pour le recyclage des vues
    public static class FormationViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView;
        private TextView linkTextView;
        private ImageView courseImageView;
=======
    // ViewHolder class to optimize view recycling
    public static class FormationViewHolder extends RecyclerView.ViewHolder {

        private TextView titleTextView; // For the course title
        private ImageView courseImageView; // For the course image
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63

        public FormationViewHolder(View itemView) {
            super(itemView);

<<<<<<< HEAD
            // Initialiser les vues du layout de l'élément
            titleTextView = itemView.findViewById(R.id.titleTextView);
            linkTextView = itemView.findViewById(R.id.linkTextView);
=======
            // Initialize views from the item_course layout
            titleTextView = itemView.findViewById(R.id.titleTextView);
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63
            courseImageView = itemView.findViewById(R.id.courseImageView);
        }
    }
}
