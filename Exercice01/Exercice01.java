
// Class Abstaraite Personne
abstract class Personne {
    protected String nom;
    protected String prenom;
    protected String rue;
    protected String ville;
    protected static int nbPersonnes = 0;

    public Personne(String nom, String prenom, String rue, String ville) {
        this.nom = nom;
        this.prenom = prenom;
        this.rue = rue;
        this.ville = ville;
        nbPersonnes++;
    }

    @Override
    public String toString() {
        return "Nom: " + nom + ", Prénom: " + prenom + ", Rue: " + rue + ", Ville: " + ville;
    }

    public abstract void ecrirePersonne();

    public static void nbPersonne() {
        System.out.println("Nombre de personnes: " + nbPersonnes);
    }

    public void modifierPersonne(String rue, String ville) {
        this.rue = rue;
        this.ville = ville;
    }
}

// Classe Secretaire
class Secretaire extends Personne {

    protected String numeroBureau;
    protected static int nbSecretaire = 0;

    public Secretaire(String nom, String prenom, String rue, String ville, String numeroBureau) {
        super(nom, prenom, rue, ville);
        this.numeroBureau = numeroBureau;
        nbSecretaire++;
    }

    @Override
    public String toString() {
        return super.toString() + ", Numéro de bureau: " + numeroBureau;
    }

    @Override
    public void ecrirePersonne() {
        System.out.println("Secretaire: " + toString());
    }

    public static void nbSecretaires() {
        System.out.println("Nombre de secrétaires: " + nbSecretaire);
    }
}

// Classe Enseignant

class Enseignant extends Personne {
    protected String specialite;
    protected static int nbEnseignants = 0;

    public Enseignant(String nom, String prenom, String rue, String ville, String specialite) {
        super(nom, prenom, rue, ville);
        this.specialite = specialite;
        nbEnseignants++;
    }

    @Override
    public String toString() {
        return super.toString() + ", Spécialité: " + specialite;
    }

    @Override
    public void ecrirePersonne() {
        System.out.println("Enseignant: " + toString());
    }

    public static void nbEnseignants() {
        System.out.println("Nombre d'enseignants: " + nbEnseignants);
    }
}

// Classe Etudiant
class Etudiant extends Personne {
    protected String diplomeEnCours;
    protected static int nbEtudiants = 0;

    public Etudiant(String nom, String prenom, String rue, String ville, String diplomeEnCours) {
        super(nom, prenom, rue, ville);
        this.diplomeEnCours = diplomeEnCours;
        nbEtudiants++;
    }

    @Override
    public String toString() {
        return super.toString() + ", Diplôme en cours: " + diplomeEnCours;
    }

    @Override
    public void ecrirePersonne() {
        System.out.println("Etudiant: " + toString());
    }

    public static void nbEtudiants() {
        System.out.println("Nombre d'étudiants: " + nbEtudiants);
    }
}

public class Exercice01 {
    public static void main(String[] args) {

        Secretaire s = new Secretaire("Said", "Abidi", "Rue Elamal", "Casablanca", "A123");
        s.ecrirePersonne();

        Enseignant e = new Enseignant("Ahmed", "Shihi", "Rue Bel Air", "Fès", "Informatique");
        e.ecrirePersonne();

        Etudiant e1 = new Etudiant("Samir", "Merras", "rue saules", "Oujda", "licence informatique");
        Etudiant e2 = new Etudiant("Hamid", "Nissani", "rue d’olivier", "Taza", "DUT informatique");
        e2.ecrirePersonne();

        e.modifierPersonne("rue du grenadier", "Rabat");
        s.modifierPersonne("rue Taounat", "Safi");

        Personne.nbPersonne();
        Secretaire.nbSecretaires();
        Enseignant.nbEnseignants();
        Etudiant.nbEtudiants();

        e.ecrirePersonne();
        s.ecrirePersonne();

        Etudiant etu01 = new Etudiant("Zarqi", "Ezzoubair", "rue ouezzane", "Ouezzane", "licence IDAI");

        etu01.ecrirePersonne();

        etu01.modifierPersonne("derb omar", "casablanca");

        etu01.ecrirePersonne();
    }
}