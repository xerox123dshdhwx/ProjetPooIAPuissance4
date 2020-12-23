import java.util.Random;

/** Cette classe contient les constructeurs permettant de lancer une nouvelle partie de
 * Puissance 4 contre une Ia
 *
 *
 * @author AIT KHELIFA Tanina & BOUGHANMI Rami
 * @version 23/12/2020
 */

public class Partie_IA {
    private Partie_humain partie;
    private Niveau lvl;


    /** Constructeur d'objet de la classe Partie_IA, permettant de créer une partie contre une IA
     * à partir d'une partie humain
     *
     * @param p Partie_humain qui contiendra les paramèrtes de la partie
     * @param niv Niveau, le numéro de sauvegarde de la partie
     * */

    public Partie_IA(Partie_humain p, Niveau niv){
        partie = p;
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

}
