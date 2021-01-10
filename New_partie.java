import java.util.InputMismatchException;
import java.util.Scanner;

/** Cette classe contient les constructeurs permettant de lancer une nouvelle partie de
 * Puissance 4 entre deux joueurs humains
 *
 * @author  BOUGHANMI Rami
 * @version 31/12/2020
 */

public class New_partie {
    /**
     * Cree une partie avec parametre par defaut
     *
     * @param n le numéro de sauvegarde de la partie
     * @return une nouvelle partie par defaut
     */
    public static Partie_humain partie_humain(int n) {
        return new Partie_humain(n);
    }

    /**
     * Cree une partie qui customise une grille en fonction du nombre de colone et ligne rentrer ainsi que
     * des noms de joueur choisi par l'utilisteur
     *
     * @param l  represente les lignes
     * @param c  represente les colones
     * @param j1 represente le nom du joueur 1
     * @param j2 represente le nom du joueur 2
     * @param n int, le numéro de sauvegarde
     * @return une nouvelle partie
     */
    public static Partie_humain partie_custom_humain_j(int l, int c, String j1, String j2, int n) {
        return new Partie_humain(l, c, j1, j2, n);
    }

    /**
     * Cree une partie qui customise une grille en fonction du nombre de colone et ligne rentrer
     *
     * @param l represente les lignes
     * @param c represente les colones
     * @param n int, numéro de sauvegarde
     * @return une nouvelle partie de humain
     */
    public static Partie_humain partie_custom_humain(int l, int c, int n) {
        return new Partie_humain(l, c, n);
    }

    /**
     * Cree une partie avec des noms de joueur choisi par l'utilisteur
     *
     * @param j1 represente le nom du joueur 1
     * @param j2 represente le nom du joueur 2
     * @param n int, numéro de sauvegarde
     * @return une nouvelle partie
     */
    public static Partie_humain partie_entre_humain_j(String j1, String j2, int n) {
        return new Partie_humain(j1, j2, n);
    }

    /**
     * new parti est une fonction qui utilise toute les foonction precedente pour cree une nouvelle partie
     * d'un joueur humain contre un autre joueur humain
     *
     * @return une nouvelle partie entre humain choisi par lutilisateur
     */
    public static Partie_humain new_partie() {
        Partie_humain prth;
        Scanner sc = new Scanner(System.in);
        System.out.println("Voulez vous choisir un nom ou le laisser par defaut ? (True = oui, false = non)");
        boolean repi = isBoolean();
        if (repi){

            String nom_du_joueur_1;
            do {
                System.out.println("Ecrivez le nom du joueur 1 :");
                nom_du_joueur_1 = sc.nextLine();
                System.out.println(nom_du_joueur_1);
            }while(stringIsInt(nom_du_joueur_1));

            String nom_du_joueur_2;
            do {
                System.out.println("Ecrivez le nom du joueur 2 :");
                nom_du_joueur_2 = sc.nextLine();
                System.out.println(nom_du_joueur_2);
            }while (stringIsInt(nom_du_joueur_2));


            boolean val = false;
            System.out.println("Voulez vous cree une parti custom ? (True = oui, false = non)");
            val = isBoolean();

            if (val) {
                System.out.println("Ecrivez un entier representant le nombre de colone (chiffre >= 4) :");
                int c = -1;
                do {
                    c = isint();
                }while(c < 4);
                System.out.println("Ecrivez un entier representant le nombre de ligne (chiffre >= 4) ");
                int l = -1;
                do{
                    l = isint();
                }while(l < 4);
                prth = partie_custom_humain_j(l, c, nom_du_joueur_1, nom_du_joueur_2, Charger_partie.compteur_du_nombre_de_save()+1);

            } else {
                prth = partie_entre_humain_j(nom_du_joueur_1, nom_du_joueur_2, Charger_partie.compteur_du_nombre_de_save()+1);

            }
        } else {
            System.out.println("Voulez vous choisir de customiser la taille de la grille ? (True = oui, false = non)");
            boolean val = isBoolean();
            if (val) {
                System.out.println("Ecrivez un entier representant le nombre de colone (chiffre >= 4) :");
                int c = -1;
                do {
                    c = isint();
                }while(c < 4);
                System.out.println("Ecrivez un entier representant le nombre de ligne (chiffre >= 4)");
                int l = -1;
                do{
                    l = isint();
                }while(l < 4);
                prth = partie_custom_humain(l, c, Charger_partie.compteur_du_nombre_de_save()+1);
            } else {
                prth = partie_humain(Charger_partie.compteur_du_nombre_de_save()+1);
            }
        }
        System.out.println(String_color.ANSI_RED + prth.getJoueur_1()+ " -> " + prth.getJoueur_2() + String_color.ANSI_RESET);
        return prth;
    }
    /**
     * Fonction qui utilise toute les foonction precedente pour cree une nouvelle partie
     * d'un joueur humain qui sera utiliser pour cree une partie ia
     *
     * @return une nouvelle partie d'humain choisi par l'utilisateur
     */
    public static Partie_humain new_partie_bis() {
        Partie_humain prth;
        Scanner sc = new Scanner(System.in);
        System.out.println("Voulez vous choisir un nom ou le laisser par defaut ? (True = oui, false = non)");
        boolean temp = isBoolean();
        if (temp) {
            String nom_du_joueur_1;
            System.out.println("Ecrivez le nom du joueur 1 :");
            nom_du_joueur_1 = sc.nextLine();

            boolean val = false;
            System.out.println("Voulez-vous customiser la grille ? (True = oui, false = non)");
            val = isBoolean();
            if (val = isBoolean()) {
                System.out.println("Ecrivez un entier representant le nombre de colone :");
                int c = -1;
                do {
                    c = isint();
                }while(c <= 4);
                System.out.println("Ecrivez un entier representant le nombre de ligne ");
                int l = -1;
                do{
                    l = isint();
                }while(l <= 4);
                prth = partie_custom_humain_j(l, c, nom_du_joueur_1, "L'IA", Charger_partie.compteur_du_nombre_de_save()+1);

            } else {
                prth = partie_entre_humain_j(nom_du_joueur_1, "L'IA", Charger_partie.compteur_du_nombre_de_save()+1);
            }
        } else {
            System.out.println("voulez vous choisir de customiser la taille de la grille ? (True = oui, false = non)");
            if (sc.nextBoolean()) {
                System.out.println("Ecrivez un entier representant le nombre de colone :");
                int c = -1;
                do {
                    c = isint();
                }while(c <= 4);
                System.out.println("Ecrivez un entier representant le nombre de ligne ");
                int l = -1;
                do{
                    l = isint();
                }while(l <= 4);
                prth = partie_custom_humain(l, c, Charger_partie.compteur_du_nombre_de_save()+1);
            } else {
                prth = partie_humain(Charger_partie.compteur_du_nombre_de_save()+1);
            }
        }
        return prth;
    }

    /**utilise une partie humain customiser pour une ia (cf new_partie_bis)
     *
     * @param lvl niveau de difficulté de l'ia
     * @return une partie ia
     */
    public static Partie_IA new_partie_ia(int lvl) {
        Partie_IA p;
        if (lvl == 0) {
            p = new Partie_IA(new_partie_bis(), Niveau.FACILE);

        } else if (lvl == 1) {
            p = new Partie_IA(new_partie_bis(), Niveau.MOYEN);
        } else {
            p = new Partie_IA(new_partie_bis(), Niveau.DIFFICILE);
        }
        return p;
    }

    /**
     * fonction qui permet de virifier si le string passer en parametre est un string de int
     * @param entree un strg
     * @return un boolean
     */
    public static boolean stringIsInt(String entree) {
        try {
            Integer.parseInt(entree);
            System.out.println("Erreur int rentrer");
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
    /**
     * fonction qui permet de virifier si le boolean demander est bien un boolean et rien d'autres
     * @return un boolean
     */
    public static boolean isBoolean(){
        boolean y = true;
        boolean x = true;
        Scanner scanner = new Scanner(System.in);
        do {
            try {
                x = scanner.nextBoolean();
                y = false;
            } catch (InputMismatchException e) {
                System.out.println(String_color.ANSI_RED + "Veuillez entrer un booléen" + String_color.ANSI_RESET);
                y = true;
                scanner.nextLine();
            }
        }while(y);
        return x;
    }
    /**
     * fonction qui permet de virifier si le int demander est bien un int et rien d'autres
     * @return un int
     */
    public static int isint(){
        boolean y = true;
        int x=0;
        Scanner scanner = new Scanner(System.in);
        do {
            try {
                x = scanner.nextInt();
                y = false;
            } catch (InputMismatchException e) {
                System.out.println("Veuillez entrer un entier");
                y = true;
                scanner.nextLine();
            }
        } while (y);

        return x;
    }

}