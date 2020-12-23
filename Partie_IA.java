import java.util.Random;

/** Cette classe contient les constructeurs permettant de lancer une nouvelle partie de
 * Puissance 4 contre une Ia
 *
 *
 * @author AIT KHELIFA Tanina & BOUGHANMI Rami
 * @version 22/12/2020
 */

public class Partie_IA {
    private Partie_humain partie;
    private Niveau lvl;


    /** Constructeur d'objet sans paramètres de la classe Partie_IA, permettant de créer un partie
     * sur une grille 6x7, le nom du joueur est initialisé à "Joueur 1"
     *
     * @param niv Niveau le niveau de difficulté de l'IA
     * @param num int, le numéro de sauvegarde de la partie
     * */

    public Partie_IA(Niveau niv, int num){
        partie = new Partie_humain("Joueur 1", "IA", num);
        lvl = niv;
    }

    /** Constructeur d'objet de la classe Partie_IA permettant de créer un partie
     * sur une grille 6x7, le joueur peut alors choisir son nom
     *
     * @param j1 String, correspond au nom du joueur
     * @param niv Niveau, le niveau de difficulté de l'IA
     * @param num int, le numéro de sauvegarde de la partie
     */

    public Partie_IA(String j1, Niveau niv, int num){
        partie = new Partie_humain(j1, "IA", num);
        lvl = niv;
    }

    /** Constructeur d'objet de la classe Partie_IA permettant de choisir la taille de la grille
     * de jeu, le nom du joueur est initialisé à "Joueur 1"
     *
     * @param l int, correspond à la hauteur de la grille
     * @param c int, correspond à la largeur de la grille
     * @param niv le niveau de difficulté de l'IA
     * @param num le numéro de sauvegarde de la partie
     */

    public Partie_IA(int l, int c, Niveau niv, int num){
        partie = new Partie_humain(l, c,"Joueur 1", "IA", num);
        lvl = niv;
    }

    /** Constructeur d'objet de la classe Partie_IA permettant de choisir la taille de la grille
     * de jeu, le joueur peut alors choisir son nom
     *
     * @param l int, correspond à la hauteur de la grille
     * @param c int, correspond à la largeur de la grille
     * @param j1 String, correspond au nom du joueur
     * @param niv Niveau, le niveau de difficulté de l'IA
     * @param num int, le numéro de sauvegarde de la partie
     */

    public Partie_IA(int l, int c, String j1, Niveau niv, int num){
        partie = new Partie_humain(l, c, j1, "IA", num);
        lvl = niv;
    }

    /** Fonction qui permet à l'IA naïve de poser un pion aléatoirement dans la grille de jeu
     *
     * @param game la partie avec IA en cours
     */
    public void add_pion_naive(Partie_IA game){
        //On choisit aléatoirement une colonne où l'IA va jouer
        int col = new Random().nextInt(game.partie.getColonnes());
        int [][]grille = game.partie.getTab_de_jeu();

        //On vérifie que la colonne n'est pas pleine/qu'elle ne dépasse pas le tableau
        while(!Deroulement_partie.colonne_correcte(game.partie, col)){
            col = new Random().nextInt(game.partie.getColonnes());
        }
        //On place le pion au fond de la colonne
        grille[Deroulement_partie.gravite(grille, col)][col] = 2;
    }

    /** Fonction qui détermine si c'est au joueur ou à l'IA, et permet au joueur ou à l'IA de placer un pion dans la grille de jeu.
     * La manière dont l'IA joue dépendra du niveau de difficulté sélectionné.
     * Elle prend en paramètre un objet de la classe Partie_IA et, dans son champ partie, altère le champ tab_de_jeu et
     * incrémente champ nb_tours.
     *
     *
     * @param game la partie avec IA en cours
     * @return le String qui servira de sauvegarde de la partie
     */
    public String tour (Partie_IA game){
        //Le joueur 1 commence tjr en 1er, d'où cette condition modulo 2
        if(game.partie.getNb_coups() %2 == 0){
            System.out.println("Au tour de " + game.partie.getJoueur_1() + " de jouer !");
            Deroulement_partie.add_pion(game.partie, 1);
        } else {
            System.out.println("Au tour de l'IA de jouer !");
            if(game.lvl == Niveau.FACILE) {
                add_pion_naive(game);
            }
        }
        game.partie.setNb_coups(game.partie.getNb_coups() + 1);
        return Deroulement_partie.save(game.partie);
    }

    public void genere_possi (Partie_IA game){

    }
}
