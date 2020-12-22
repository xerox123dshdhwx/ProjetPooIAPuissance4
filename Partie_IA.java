import java.util.Random;

/** Cette classe contient les constructeurs permettant de lancer une nouvelle partie de
 * Puissance 4 contre une Ia
 *
 *
 * @author AIT KHELIFA Tanina & BOUGHANMI Rami
 * @version 22/12/2020
 */

public class Partie_IA {
    Partie_humain partie;
    int niveau_IA;


    /** Constructeur d'objet sans paramètres de la classe Partie_IA, permettant de créer un partie
     * sur une grille 6x7, le nom du joueur est initialisé à "Joueur 1"
     *
     * @param lvl le niveau de difficulté de l'IA
     * @param num le numéro de sauvegarde de la partie
     * */

    public Partie_IA(int lvl, int num){
        partie = new Partie_humain("Joueur 1", "IA", num);
        niveau_IA = lvl;
    }

    /** Constructeur d'objet de la classe Partie_IA permettant de créer un partie
     * sur une grille 6x7, le joueur peut alors choisir son nom
     *
     * @param j1 String, correspond au nom du joueur
     * @param lvl le niveau de difficulté de l'IA
     * @param num le numéro de sauvegarde de la partie
     */

    public Partie_IA(String j1, int lvl, int num){
        partie = new Partie_humain(j1, "IA", num);
        niveau_IA = lvl;
    }

    /** Constructeur d'objet de la classe Partie_IA permettant de choisir la taille de la grille
     * de jeu, le nom du joueur est initialisé à "Joueur 1"
     *
     * @param l int, correspond à la hauteur de la grille
     * @param c int, correspond à la largeur de la grille
     * @param lvl le niveau de difficulté de l'IA
     * @param num le numéro de sauvegarde de la partie
     */

    public Partie_IA(int l, int c, int lvl, int num){
        partie = new Partie_humain(l, c,"Joueur 1", "IA", num);
        niveau_IA = lvl;
    }

    /** Constructeur d'objet de la classe Partie_IA permettant de choisir la taille de la grille
     * de jeu, le joueur peut alors choisir son nom
     *
     * @param l int, correspond à la hauteur de la grille
     * @param c int, correspond à la largeur de la grille
     * @param j1 String, correspond au nom du joueur
     * @param lvl le niveau de difficulté de l'IA
     * @param num le numéro de sauvegarde de la partie
     */

    public Partie_IA(int l, int c, String j1, int lvl, int num){
        partie = new Partie_humain(l, c, j1, "IA", num);
        niveau_IA = lvl;
    }

    /** Fonction qui permet à l'IA naïve de poser un pion aléatoirement dans la grille de jeu
     *
     * @param game la partie en cours
     */
    public void add_pion_naive(Partie_IA game){
        //On choisit aléatoirement une colonne où l'IA va jouer
        int col = new Random().nextInt(game.partie.getColonnes());
        int [][]grille = game.partie.getTab_de_jeu();

        while(!Deroulement_partie.colonne_correcte(game.partie, col)){
            col = new Random().nextInt(game.partie.getColonnes());
        }
        //On place le pion au fond de la colonne
        grille[Deroulement_partie.gravite(grille, col)][col] = 2;
    }
}
