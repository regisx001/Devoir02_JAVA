interface VendableKilo {
    double vendre(double quantite);
}

interface VendablePiece {
    double vendre(int quantite);
}

interface Solde {
    void lanceSolde(double pourcentage);

    void termineSolde(double pourcentage);
}

class Article {
    protected double prixAchat;
    protected double prixVente;
    protected String nom;
    protected String fournisseur;

    public Article(double prixAchat, double prixVente, String nom, String fournisseur) {
        this.prixAchat = prixAchat;
        this.prixVente = prixVente;
        this.nom = nom;
        this.fournisseur = fournisseur;
    }

    public double rendement() {
        return (prixVente - prixAchat) / prixAchat * 100;
    }

    @Override
    public String toString() {
        return "Nom: " + nom + ", Prix Achat: " + prixAchat + ", Prix Vente: " + prixVente +
                ", Fournisseur: " + fournisseur + ", Rendement: " + rendement() + "%";
    }
}

class ArticleElectromenager extends Article implements VendablePiece, Solde {
    private int stock;

    public ArticleElectromenager(double prixAchat, double prixVente, String nom, String fournisseur) {
        super(prixAchat, prixVente, nom, fournisseur);
        this.stock = 0;
    }

    public void remplirStock(int quantite) {
        this.stock += quantite;
    }

    @Override
    public double vendre(int quantite) {
        if (quantite > stock) {
            System.out.println("Quantité demandée dépasse le stock disponible.");
            return 0;
        }
        stock -= quantite;
        return quantite * prixVente;
    }

    @Override
    public void lanceSolde(double pourcentage) {
        prixVente -= prixVente * (pourcentage / 100);
    }

    @Override
    public void termineSolde(double pourcentage) {
        prixVente += prixVente * (pourcentage / 100);
    }

    @Override
    public String toString() {
        return super.toString() + ", Stock: " + stock + " pièces";
    }
}

class ArticlePrimeur extends Article implements VendableKilo {
    private double stock;

    public ArticlePrimeur(double prixAchat, double prixVente, String nom, String fournisseur) {
        super(prixAchat, prixVente, nom, fournisseur);
        this.stock = 0; // Stock initial vide
    }

    public void remplirStock(double quantite) {
        this.stock += quantite;
    }

    @Override
    public double vendre(double quantite) {
        if (quantite > stock) {
            System.out.println("Quantité demandée dépasse le stock disponible.");
            return 0;
        }
        stock -= quantite;
        return quantite * prixVente;
    }

    @Override
    public String toString() {
        return super.toString() + ", Stock: " + stock + " kg";
    }
}

class Magasin {
    private double depense;
    private double revenu;
    private ArticleElectromenager[] articlesElectromenagers;
    private ArticlePrimeur[] articlesPrimeurs;
    private int indexElectromenager;
    private int indexPrimeur;

    public Magasin(int tailleElectromenagers, int taillePrimeurs) {
        this.depense = 0;
        this.revenu = 0;
        this.articlesElectromenagers = new ArticleElectromenager[tailleElectromenagers];
        this.articlesPrimeurs = new ArticlePrimeur[taillePrimeurs];
        this.indexElectromenager = 0;
        this.indexPrimeur = 0;
    }

    public void ajouterArticleElectromenager(ArticleElectromenager article) {
        if (indexElectromenager < articlesElectromenagers.length) {
            articlesElectromenagers[indexElectromenager++] = article;
            depense += article.prixAchat;
        } else {
            System.out.println("Pas de place pour plus d'articles électroménagers.");
        }
    }

    public void ajouterArticlePrimeur(ArticlePrimeur article) {
        if (indexPrimeur < articlesPrimeurs.length) {
            articlesPrimeurs[indexPrimeur++] = article;
            depense += article.prixAchat;
        } else {
            System.out.println("Pas de place pour plus d'articles primeurs.");
        }
    }

    @Override
    public String toString() {
        return "Magasin [dépense=" + depense + ", revenu=" + revenu + ", rendement=" + rendement() + "%]";
    }

    public double rendement() {
        return (revenu - depense) / depense * 100;
    }

    public void vendreArticleElectromenager(int index, int quantite) {
        if (index >= 0 && index < indexElectromenager) {
            ArticleElectromenager article = articlesElectromenagers[index];
            revenu += article.vendre(quantite);
        } else {
            System.out.println("Index d'article électroménager invalide.");
        }
    }

    public void vendreArticlePrimeur(int index, double quantite) {
        if (index >= 0 && index < indexPrimeur) {
            ArticlePrimeur article = articlesPrimeurs[index];
            revenu += article.vendre(quantite);
        } else {
            System.out.println("Index d'article primeur invalide.");
        }
    }
}

public class Exercice02Partie2 {
    public static void main(String[] args) {
        Magasin magasin = new Magasin(2, 2);

        ArticleElectromenager frigo = new ArticleElectromenager(300, 500, "Frigo", "ElectroSupplier");
        ArticleElectromenager laveLinge = new ArticleElectromenager(200, 400, "Lave-Linge", "ElectroSupplier");
        ArticlePrimeur tomat = new ArticlePrimeur(2, 4, "tomat", "PrimeurSupplier");
        ArticlePrimeur banane = new ArticlePrimeur(3, 5, "banane", "PrimeurSupplier");

        magasin.ajouterArticleElectromenager(frigo);
        magasin.ajouterArticleElectromenager(laveLinge);
        magasin.ajouterArticlePrimeur(tomat);
        magasin.ajouterArticlePrimeur(banane);

        frigo.remplirStock(10);
        laveLinge.remplirStock(5);
        tomat.remplirStock(100);
        banane.remplirStock(50);

        magasin.vendreArticleElectromenager(0, 3);
        magasin.vendreArticlePrimeur(0, 20);

        System.out.println(magasin);

        System.out.println(frigo);
        System.out.println(laveLinge);
        System.out.println(tomat);
        System.out.println(banane);

        frigo.lanceSolde(10);
        System.out.println("Après solde: " + frigo);

        frigo.termineSolde(10);
        System.out.println("Après fin de solde: " + frigo);
    }
}