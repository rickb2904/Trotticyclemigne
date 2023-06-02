package fr.bouyaghir.trotticycle.entity;

public class Trajet {

    int id ;
    double prix;
    double duree;

    public Trajet(int id, double prix, double duree) {
        this.id = id;
        this.prix = prix;
        this.duree = duree;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public double getDuree() {
        return duree;
    }

    public void setDuree(double duree) {
        this.duree = duree;
    }
}
