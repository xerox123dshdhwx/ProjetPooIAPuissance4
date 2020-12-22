/** Cette classe contient les constructeurs permettant de lancer une nouvelle partie de
 * Puissance 4 entre deux joueurs humains
 *
 *
 * @author AIT KHELIFA Tanina & BOUGHANMI Rami
 * @version 11/11/2020
 */

public class Partie_humain {

    private int[][] tab_de_jeu;
    private int nb_coups;
    private int lignes;
    private int colonnes;
    private String joueur_1 = "Joueur 1";
    private String joueur_2 = "Joueur 2";
    private int num_partie;


    /** Constructeur d'objet sans paramètres de la classe Partie_humain, permettant de créer un partie
     * sur une grille 6x7, les noms des joueurs sont initialisés à "Joueur 1" et "Joueur 2"
     * */

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

    /** Fonction initialisant la grille de jeu d'une partie entre deux humains
     *
     * @param tab int[][] correspondant au nouveau champ tab_de_jeu d'un objet de classe Partie_humain
     */
    public void setTab_de_jeu(int[][] tab) {
        this.tab_de_jeu = tab;
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

    /** Fonction initialisant le nombre de lignes de la grille d'une partie entre deux humains
     *
     * @param l int correspondant au nouveau champ lignes d'un objet de classe Partie_humain
     */
    public void setLignes(int l) {
        this.lignes = l;
    }

    /** Fonction initialisant le nombre de colonnnes de la grille d'une partie entre deux humains
     *
     * @param c int correspondant au nouveau champ colonnes d'un objet de classe Partie_humain
     */
    public void setColonnes(int c) {
        this.colonnes = c;
    }

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
}