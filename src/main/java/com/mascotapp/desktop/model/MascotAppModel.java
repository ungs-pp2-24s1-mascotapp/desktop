package com.mascotapp.desktop.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class MascotAppModel extends Observable {
    public void searchPets(String query) {
        List<String> results = new ArrayList<>();
        results.add("Gato: Tom");
        results.add("Perro: Rex");
        results.add("Loro: Polly");

        // Notifica a los observadores (en este caso, la vista)
        setChanged();
        notifyObservers(results);
    }
}
