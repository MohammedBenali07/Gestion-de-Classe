package com.example.gestiondeclasse;

import android.text.TextWatcher;

import android.text.Editable;
<<<<<<< HEAD
=======
import android.text.TextWatcher;
>>>>>>> 6e32fa966283785abc8796fdb272892932cbdd63

public abstract class SimpleTextWatcher implements TextWatcher {

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        // Pas besoin d'implémentation ici
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        // Méthode abstraite à implémenter
    }

    @Override
    public void afterTextChanged(Editable s) {
        // Pas besoin d'implémentation ici
    }
}
