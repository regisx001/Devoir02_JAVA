
import java.util.ArrayList;
import java.util.ListIterator;

abstract class ObjetGraphique {
    protected int x;
    protected int y;

    public ObjetGraphique() {
        this.x = 0;
        this.y = 0;
    }

    public ObjetGraphique(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public ObjetGraphique(ObjetGraphique obj) {
        this.x = obj.x;
        this.y = obj.y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void deplacer(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public abstract void affiche();
}

class Rectangle extends ObjetGraphique {
    protected double largeur;
    protected double hauteur;

    public Rectangle() {
        super();
        this.largeur = 0;
        this.hauteur = 0;
    }

    public Rectangle(int x, int y, double largeur, double hauteur) {
        super(x, y);
        this.largeur = largeur;
        this.hauteur = hauteur;
    }

    public Rectangle(Rectangle rect) {
        super(rect);
        this.largeur = rect.largeur;
        this.hauteur = rect.hauteur;
    }

    public double getHauteur() {
        return hauteur;
    }

    public double getLargeur() {
        return largeur;
    }

    public void setTaille(double largeur, double hauteur) {
        this.largeur = largeur;
        this.hauteur = hauteur;
    }

    @Override
    public void affiche() {
        System.out.println("Rectangle: Position (" + x + ", " + y + "), Largeur: " + largeur + ", Hauteur: " + hauteur);
    }
}

class Bouton extends Rectangle {
    protected String text;

    public Bouton() {
        super();
        this.text = "";
    }

    public Bouton(int x, int y, double largeur, double hauteur, String text) {
        super(x, y, largeur, hauteur);
        this.text = text;
    }

    public Bouton(Bouton bouton) {
        super(bouton);
        this.text = bouton.text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void affiche() {
        System.out.println("Bouton: Position (" + getX() + ", " + getY() + "), Largeur: " + getLargeur() + ", Hauteur: "
                + getHauteur() + ", Texte: " + text);
    }
}

class Cercle extends ObjetGraphique {
    protected double rayon;

    public Cercle() {
        super();
        this.rayon = 0;
    }

    public Cercle(int x, int y, double rayon) {
        super(x, y);
        this.rayon = rayon;
    }

    public Cercle(Cercle cercle) {
        super(cercle);
        this.rayon = cercle.rayon;
    }

    public double getRayon() {
        return rayon;
    }

    public void setRayon(double rayon) {
        this.rayon = rayon;
    }

    @Override
    public void affiche() {
        System.out.println("Cercle: Position (" + x + ", " + y + "), Rayon: " + rayon);
    }
}

class ListeObjetGraphique {
    private ObjetGraphique[] liste;
    private int taille;
    private int index;

    public ListeObjetGraphique(int taille) {
        this.taille = taille;
        this.liste = new ObjetGraphique[taille];
        this.index = 0;
    }

    public void ajouter(ObjetGraphique obj) {
        if (index < taille) {
            liste[index] = obj;
            index++;
        } else {
            System.out.println("La liste est pleine, impossible d'ajouter un nouvel objet.");
        }
    }

    public void afficheObjetsGraphique() {
        for (int i = 0; i < index; i++) {
            liste[i].affiche();
        }
    }
}

public class Exercice02Partie1 {
    public static void main(String[] args) {

        System.out.println("--------------------Test avec un tableau---------------------------");
        ObjetGraphique[] tableau = new ObjetGraphique[9];
        tableau[0] = new Cercle(10, 20, 5);
        tableau[1] = new Cercle(12, 0, 3);
        tableau[2] = new Cercle(1, 10, 15);
        tableau[3] = new Rectangle(30, 40, 15, 10);
        tableau[4] = new Bouton(50, 60, 20, 10, "Cliquez-ici");
        tableau[5] = new Bouton(20, 80, 10, 5, "Cliquez-moi");
        tableau[6] = new Bouton(0, 0, 50, 20, "Submit ");
        tableau[7] = new Cercle(70, 80, 8);
        tableau[8] = new Rectangle(100, 0, 100, 200);

        for (ObjetGraphique obj : tableau) {
            obj.affiche();
        }
        System.out.println("--------------------------------------------------------------");

        System.out.println("--------------------Test Classe ListObjetGraphique---------------------------");
        ListeObjetGraphique listeObjet = new ListeObjetGraphique(10);

        listeObjet.ajouter(new Cercle(10, 20, 5));
        listeObjet.ajouter(new Rectangle(30, 40, 15, 10));
        listeObjet.ajouter(new Bouton(50, 60, 20, 10, "Cliquez-moi"));
        listeObjet.ajouter(new Bouton(50, 60, 20, 10, "Cliquez-ici"));
        listeObjet.ajouter(new Cercle(70, 80, 8));
        listeObjet.ajouter(new Rectangle(100, 0, 100, 200));

        listeObjet.afficheObjetsGraphique();
        System.out.println("--------------------------------------------------------------");

        System.out.println("--------------------Test Classe ArrayList et ListIterator ---------------------------");
        ArrayList<ObjetGraphique> arrayListe = new ArrayList<>();

        arrayListe.add(new Cercle(10, 20, 5));
        arrayListe.add(new Rectangle(30, 40, 15, 10));
        arrayListe.add(new Bouton(50, 60, 20, 10, "Cliquez-moi"));
        arrayListe.add(new Cercle(70, 80, 8));

        ListIterator<ObjetGraphique> iterator = arrayListe.listIterator();
        while (iterator.hasNext()) {
            ObjetGraphique obj = iterator.next();
            obj.affiche();
        }
        System.out.println("--------------------------------------------------------------");
    }
}
