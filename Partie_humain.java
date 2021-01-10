/** Cette classe contient les constructeurs permettant de lancer une nouvelle partie de
 * Puissance 4 entre deux joueurs humains
 *
 *
 * @author AIT KHELIFA Tanina
 * @version 24/12/2020
 */

public class Partie_humain {

    private int[][] tab_de_jeu;
    private int nb_coups;
    private int lignes;
    private int colonnes;
    private String joueur_1 = "Joueur 1";
    private String joueur_2 = "Joueur 2";
    private final int num_partie;


    /** Constructeur de copie d'objet de la classe Partie_humain
     *
     * @param p Parie_humain, la partie à copier
     */
    public Partie_humain(Partie_humain p){
        lignes = p.lignes;
        colonnes = p.colonnes;
        joueur_1 = p.joueur_1;
        joueur_2 = p.joueur_2;
        tab_de_jeu = new int[p.lignes][p.colonnes];
        for(int i=0; i<p.lignes; i++){
            for(int j=0; j<p.colonnes; j++){
                tab_de_jeu[i][j] = p.tab_de_jeu[i][j];
            }
        }
        nb_coups = p.nb_coups;
        num_partie = p.num_partie;
    }


    /** Constructeur d'objet sans paramètres de la classe Partie_humain, permettant de créer un partie
     * sur une grille 6x7, les noms des joueurs sont initialisés à "Joueur 1" et "Joueur 2"
     * @param n int, numéro de sauvegarde
     */
    public Partie_humain(int n){
        lignes = 6;
        colonnes = 7;
        tab_de_jeu = new int[6][7];
        nb_coups = 0;
        num_partie = n;
    }


    /** Constructeur d'objet de la classe Partie_humain permettant de créer un partie
     * sur une grille 6x7, les joueurs peuvent alors choisir leur noms
     *
     * @param j1 String, correspond au nom du joueur 1
     * @param j2 String, correspond au nom du joueur 2
     * @param n int, numéro de sauvegarde
     */
    public Partie_humain(String j1, String j2, int n){
        lignes = 6;
        colonnes = 7;
        tab_de_jeu = new int[6][7];
        joueur_1 = j1;
        joueur_2 = j2;
        nb_coups = 0;
        num_partie = n;
    }


    /** Constructeur d'objet de la classe Partie_humain permettant de choisir la taille de la grille
     * de jeu, les noms des joueurs sont initialisés à "Joueur 1" et "Joueur 2"
     *
     * @param l int, correspond à la hauteur de la grille
     * @param c int, correspond à la largeur de la grille
     * @param n int, numéro de sauvegarde
     */
    public Partie_humain(int l, int c, int n){
        tab_de_jeu = new int[l][c];
        lignes = l;
        colonnes = c;
        nb_coups = 0;
        num_partie = n;
    }


    /** Constructeur d'objet de la classe Partie_humain permettant de choisir la taille de la grille
     * de jeu, les joueurs peuvent alors choisir leur noms
     *
     * @param l int, correspond à la hauteur de la grille
     * @param c int, correspond à la largeur de la grille
     * @param j1 String, correspond au nom du joueur 1
     * @param j2 String, correspond au nom du joueur 2
     * @param n int, numéro de sauvegarde
     */
    public Partie_humain(int l, int c, String j1, String j2, int n){
        tab_de_jeu = new int[l][c];
        lignes = l;
        colonnes = c;
        joueur_1 = j1;
        joueur_2 = j2;
        nb_coups = 0;
        num_partie = n;
    }


    /** Fonction retournant la grille de jeu d'une partie entre deux humains
     *
     * @return int[][] correspondant au champ tab_de_jeu d'un objet de classe Partie_humain
     */
    public int[][] getTab_de_jeu() {
        return tab_de_jeu;
    }

    /** Fonction retournant le nom du premier joueur d'une partie entre deux humains
     *
     * @return String correspondant au champ joueur_1 d'un objet de classe Partie_humain
     */
    public String getJoueur_1() {
        return joueur_1;
    }


    /** Fonction initialisant le nom du premier joueur d'une partie entre deux humains selon la chaîne
     * de caractères passée en paramètre
     *
     * @param j1 String correspondant au nouveau joueur_1 d'un objet de classe Partie_humain
     */
    public void setJoueur_1(String j1) {
        this.joueur_1 = j1;
    }


    /** Fonction retournant le nom du deuxième joueur d'une partie entre deux humains
     *
     * @return String correspondant au champ joueur_2 d'un objet de classe Partie_humain
     */
    public String getJoueur_2() {
        return joueur_2;
    }


    /** Fonction initialisant le nom du deuxième joueur d'une partie entre deux humains selon la chaîne
     * de caractères passée en paramètre
     *
     * @param j2 String correspondant au nouveau joueur_2 d'un objet de classe Partie_humain
     */
    public void setJoueur_2(String j2) {
        this.joueur_2 = j2;
    }


    /** Fonction retournant le nombre de colonnes de la grille d'une partie entre deux humains
     *
     * @return int correspondant au champ colonnes d'un objet de classe Partie_humain
     */
    public int getColonnes() {
        return colonnes;
    }


    /** Fonction retournant le nombre de lignes de la grille d'une partie entre deux humains
     *
     * @return int correspondant au champ lignes d'un objet de classe Partie_humain
     */
    public int getLignes() {
        return lignes;
    }


    /** Fonction qui retourne le numéro de la partie (qui est aussi le numéro de sauvegarde de la partie) de l'objet courant
     *
     * @return int : le numéro de la partie
     */
    public int getNumPartie(){ return this.num_partie;}

    /** Fonction retournant le nombre de coups qui ont été joués dans une partie entre deux humains
     *
     * @return int correspondant au champ nb_coups d'un objet de classe Partie_humain
     */
    public int getNb_coups() {
        return nb_coups;
    }


    /** Fonction initialisant le nombre de de coups qui ont été joués dans partie entre deux humains
     *
     * @param coups int correspondant au nouveau champ nb_coups d'un objet de classe Partie_humain
     */
    public void setNb_coups(int coups) {
        this.nb_coups = coups;
    }


    /** Méthode qui permet de calculer le score d'un joueur sur l'instance de Partie_humain courante
     *
     * @param pion int représentant le pion associé au joueur dont on calcule le score (1 pour le joueur 1, 2 pour le Joueur 2)
     * @return int représentant le score du Joueur
     */
    public int score(int pion){
        Partie_humain p = this;
        int score = 0;
        int[][] tab = p.tab_de_jeu;
        //On compte les alignements verticaux
        for (int j = 0; j < p.colonnes; j++) {
            if (tab[p.getLignes() - 1][j] != 0) {
                //Alignement de 4
                int hauteur = Deroulement_partie.gravite(tab, j)-1;
                if (hauteur >= 0){
                    if (tab[hauteur][j] == pion) {
                        if ((tab[hauteur][j] == tab[hauteur - 1][j]) && (tab[hauteur][j] == tab[hauteur - 2][j]) &&
                                (tab[hauteur][j] == tab[hauteur - 3][j])) {
                            score += 4;

                            //Conditions sur la hauteur pour vérifier qu'il reste une case de libre pour faire l'alignement
                        } else if ((hauteur < p.lignes - 1) && (tab[hauteur][j] == pion) && (tab[hauteur][j] == tab[hauteur - 1][j])
                                && (tab[hauteur][j] == tab[hauteur - 2][j]) && (tab[hauteur - 3][j] == 0)) {
                            score += 3;

                        } else if ((hauteur < p.lignes - 2) && (tab[hauteur][j] == tab[hauteur - 1][j]) &&
                                (tab[hauteur - 2][j] == 0) && (tab[hauteur - 3][j] == 0)) {
                            score += 2;
                        } else if ((hauteur < p.colonnes - 3) && (tab[hauteur - 1][j] == 0) &&
                                (tab[hauteur - 2][j] == 0) && (tab[hauteur - 3][j] == 0)) {
                            score += 1;
                        }
                    }
                }
            }
        }

        //Alignement horizontaux
        int k = p.lignes - 1;
        while (k >= 0) {
            for (int j = 0; (j < p.colonnes) && (!Deroulement_partie.ligne_vide(tab[k])) ; j++) {
                if (tab[k][j] == pion) {
                    if (j < p.colonnes - 3) {
                        if ((tab[k][j] == tab[k][j + 1]) && (tab[k][j] == tab[k][j + 2])
                                && (tab[k][j] == tab[k][j + 3])) {
                            score += 4;
                        } else if ((tab[k][j] == tab[k][j + 1]) && (tab[k][j] == tab[k][j + 2])
                                && (tab[k][j + 3] == 0)) {
                            score += 3;
                        } else if ((tab[k][j] == tab[k][j + 1]) && (tab[k][j + 2] == 0)
                                && (tab[k][j + 3] == 0)) {
                            score += 2;
                        } else if ((tab[k][j + 1] == 0) && (tab[k][j + 2] == 0)
                                && (tab[k][j + 3] == 0)) {
                            score += 1;
                        }
                    } else if (j >= 3) {
                        if ((tab[k][j] == tab[k][j - 1]) && (tab[k][j] == tab[k][j - 2])
                                && (tab[k][j - 3] == 0)) {
                            score += 3;
                        } else if ((tab[k][j] == tab[k][j - 1]) && (tab[k][j - 2] == 0)
                                && (tab[k][j - 3] == 0)) {
                            score += 2;
                        } else if ((tab[k][j - 1] == 0) && (tab[k][j - 2] == 0)
                                && (tab[k][j - 3] == 0)) {
                            score += 1;
                        }
                    }

                }
            }
            k--;
        }

        //Alignements diagonaux en / (bas gauche vers haut droit)
        for (int i = p.lignes - 1; i >= 3; i--){
            for (int j = 0; j < p.colonnes-3; j++){
                if (tab[i][j] == pion) {
                    if ((tab[i][j] == tab[i - 1][j + 1]) && (tab[i][j] == tab[i - 2][j + 2])
                            && (tab[i][j] == tab[i - 3][j + 3])) {
                        score += 4;
                    } else if ((tab[i][j] == tab[i - 1][j + 1]) && (tab[i][j] == tab[i - 2][j + 2])
                            && (tab[i - 3][j + 3] == 0)) {
                        score += 3;
                    } else if ((tab[i][j] == tab[i - 1][j + 1]) && (tab[i - 2][j + 2] == 0)
                            && (tab[i - 3][j + 3] == 0)) {
                        score += 2;
                    } else if ((tab[i - 1][j + 1] == 0) && (tab[i - 2][j + 2] == 0)
                            && (tab[i - 3][j + 3] == 0)) {
                        score += 1;
                    }
                }
            }
        }


        //Alignements diagonaux en \
        for (int i = p.lignes - 1; i >= 3; i--){
            for (int j = p.colonnes-1; j >= 3; j--){
                if (tab[i][j] == pion) {
                    if ((tab[i][j] == tab[i - 1][j - 1]) && (tab[i][j] == tab[i - 2][j - 2])
                            && (tab[i][j] == tab[i - 3][j - 3])) {
                        score += 4;
                    } else if ((tab[i][j] == tab[i - 1][j - 1]) && (tab[i][j] == tab[i - 2][j - 2])
                            && (tab[i - 3][j - 3] == 0)) {
                        score += 3;
                    } else if ((tab[i][j] == tab[i - 1][j - 1]) && (tab[i - 2][j - 2] == 0)
                            && (tab[i - 3][j - 3] == 0)) {
                        score += 2;
                    } else if ((tab[i - 1][j - 1] == 0) && (tab[i - 2][j - 2] == 0)
                            && (tab[i - 3][j - 3] == 0)) {
                        score += 1;
                    }
                }
            }
        }

        return score;
    }

}
