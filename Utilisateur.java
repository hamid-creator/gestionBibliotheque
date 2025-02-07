import java.util.ArrayList;
import java.util.Scanner;
public class Utilisateur {

    private String nom;
    private int numeroIdentification;
    private ArrayList<Livre> livresEmpruntes;
    private boolean cotisationAJour;

    public Utilisateur(String nom, int numeroIdentification) {
        this.nom = nom;
        this.numeroIdentification = numeroIdentification;
        this.livresEmpruntes = new ArrayList<>();
        this.cotisationAJour = false;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNumeroIdentification() {
        return numeroIdentification;
    }

    public void setNumeroIdentification(int numeroIdentification) {
        this.numeroIdentification = numeroIdentification;
    }


    public ArrayList<Livre> getLivresEmpruntes() {
        return livresEmpruntes;
    }

    public void setLivresEmpruntes(ArrayList<Livre> livresEmpruntes) {
        this.livresEmpruntes = livresEmpruntes;
    }
    public boolean isCotisationAJour() {
        return cotisationAJour;
    }

    public void setCotisationAJour(boolean cotisationAJour) {
        this.cotisationAJour = cotisationAJour;
    }
    

    public void emprunterLivre(Bibliotheque bibliotheque) {
        Livre livre = bibliotheque.rechercherLivre();
        if (livre != null) {
            livre = new Livre(livre);
            livre.setQuantite(1);
            bibliotheque.enregistrerEmpruntLivre(this, livre);
        }
       
    }
    

    public void retournerLivre(Bibliotheque bibliotheque) {
        Scanner scanner = new Scanner(System.in);
        int numero;
        if (this.livresEmpruntes.isEmpty()) {
            System.out.println(this.nom+" n'a aucun emprunt en cours");
            return;
        }
        System.out.println("Veuillez indiquer le livre a retourner");
        this.afficherLivreEmpruntee();
        do {
            System.out.print("Numero du Livre: ");
            numero = scanner.nextInt();
        } while (numero > this.livresEmpruntes.size() || numero <=0);
        bibliotheque.enregistrerRetourLivre(this, this.livresEmpruntes.get(numero - 1));
    }

    public void afficherLivreEmpruntee(){
        if (this.livresEmpruntes.isEmpty()) {
            System.out.println(this.nom+" n'a aucun emprunt en cours");
            return;
        }
        int numero = 1;
        System.out.println("Livre Empruntee par "+this.nom);
        for(Livre livre : this.livresEmpruntes){
            System.out.printf("\t\t%d- %s\n",numero,livre);
            numero++;
        }
    }

    public void payerCotisation(Scanner sc){
        int montant;
        do {
            System.out.println("Veuillez Saisir le montant que vous souhaitez (Plus de 5000)");
            montant = sc.nextInt();
        } while (montant < 5000);
        this.cotisationAJour = true;
    }
}
