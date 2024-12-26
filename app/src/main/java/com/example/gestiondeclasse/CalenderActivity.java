package com.example.gestiondeclasse;

import android.content.Intent;
<<<<<<< HEAD
import android.os.Bundle;
import android.util.Log;

=======
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63
import android.view.View;
import android.widget.CalendarView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
<<<<<<< HEAD
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


=======
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import java.util.ArrayList;
import java.util.List;

public class CalenderActivity extends AppCompatActivity {
<<<<<<< HEAD

    private static final String TAG = "CalenderActivity";
    private TextView tvResult;
=======
    private static final String TAG = "CalenderActivity";
    private static final int REQUEST_CODE_SCAN = 1;
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63
    private List<String> sessions;
    private List<String> presence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendrer);

<<<<<<< HEAD
=======
        // Trouver les éléments de l'interface
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63
        TextView btnScanQr = findViewById(R.id.btn_scan_qr);
        CalendarView calendarView = findViewById(R.id.calendarView);
        RecyclerView recyclerView = findViewById(R.id.recycler_view_sessions);

<<<<<<< HEAD
        // Initialize session data
        sessions = new ArrayList<>();
        presence = new ArrayList<>();
=======
        sessions = new ArrayList<>();
        presence = new ArrayList<>();

        // Remplir les données de session et de présence
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63
        for (int i = 1; i <= 4; i++) {
            sessions.add("S" + i);
            presence.add("P");
        }

<<<<<<< HEAD
        // Set up RecyclerView
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        SessionAdapter adapter = new SessionAdapter(sessions, presence ,this);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new TopDividerItemDecoration(this));

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            // Display sessions for the selected day
            // Here sessions are randomized as per your requirement
            adapter.notifyDataSetChanged();
        });

        btnScanQr.setOnClickListener(v -> {
            IntentIntegrator integrator = new IntentIntegrator(this);
            integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
            integrator.setPrompt("Scan a QR Code");
            integrator.setCameraId(0);  // Use a specific camera of the device
            integrator.setBeepEnabled(true);
            integrator.setBarcodeImageEnabled(true);
            integrator.initiateScan();
        });
        // Configuration du bouton retour
        ImageView iconBack = findViewById(R.id.icon_back); // Déplacement ici dans onCreate
        iconBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Rediriger vers DashboardActivity
                Intent intent = new Intent(CalenderActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() == null) {
                Log.d(TAG, "Cancelled scan");
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            } else {
                Log.d(TAG, "Scanned");
                tvResult.setText(result.getContents());
                Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


=======
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        SessionAdapter adapter = new SessionAdapter(sessions, presence, this);
        recyclerView.setAdapter(adapter);

        // Écouteur pour les changements sur le calendrier
        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            adapter.notifyDataSetChanged();
        });

        // Scanner le code QR lorsque l'utilisateur clique sur le bouton
        btnScanQr.setOnClickListener(v -> {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            IntentIntegrator integrator = new IntentIntegrator(this);
            integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
            integrator.setPrompt("Scan a QR Code");
            integrator.setCameraId(0); // Utiliser l'appareil photo arrière
            integrator.setBeepEnabled(true); // Son lors du scan
            integrator.setBarcodeImageEnabled(true); // Enregistrer une image du code scanné
            integrator.initiateScan();
        });

        // Bouton de retour à l'écran Dashboard
        ImageView iconBack = findViewById(R.id.icon_back);
        iconBack.setOnClickListener(v -> {
            Intent intent = new Intent(CalenderActivity.this, DashboardActivity.class);
            startActivity(intent);
        });
    }

    // Gérer le résultat du scan dans onActivityResult
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Vérifier que c'est le bon code de demande (le scan QR)
        if (requestCode == IntentIntegrator.REQUEST_CODE) {
            // Analyser les résultats du scan
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

            if (result != null && result.getContents() != null) {
                // Si le scan est réussi, afficher un AlertDialog avec le contenu scanné
                String scannedContent = result.getContents();
                Log.d(TAG, "QR Code scanné: " + scannedContent);

                // Créer et afficher l'AlertDialog
                runOnUiThread(() -> {
                    AlertDialog.Builder builder = new AlertDialog.Builder(CalenderActivity.this);
                    View dialogView = getLayoutInflater().inflate(R.layout.dialo_scan_success, null);


                    AppCompatButton closeButton = dialogView.findViewById(R.id.dialog_close_button);
                    builder.setView(dialogView);

                    AlertDialog dialog = builder.create();
                    dialog.show();

                    // Fermer le dialog lorsque l'utilisateur clique sur le bouton
                    closeButton.setOnClickListener(v -> dialog.dismiss());
                });
            } else {
                Log.d(TAG, "Scan annulé ou pas de contenu");
                Toast.makeText(this, "Scan annulé ou pas de contenu", Toast.LENGTH_LONG).show();
            }
        }
    }
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63
}
