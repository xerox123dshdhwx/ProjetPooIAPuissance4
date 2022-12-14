import java.util.Random;

/** Cette classe contient les constructeurs permettant de lancer une nouvelle partie de
 * Puissance 4 contre une IA
 *
 *
 * @author AIT KHELIFA Tanina
 * @version 28/12/2020
 */

//Pour évaluation : on compte le nombre de d'alignement et pions alignés + on soustrait l'évaluation ennemie = delta bg
//Pour la save : h = humains, f = facile

public class Partie_IA {
    private Partie_humain partie;
    private final Niveau lvl;


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
    public static void add_pion_naive(Partie_IA game){
        //On choisit aléatoirement une colonne où l'IA va jouer
        int col = new Random().nextInt(game.partie.getColonnes());
        int [][]grille = game.partie.getTab_de_jeu();

        //On affiche les coups possibles dans l'arbre
        System.out.println("Possibilités de l'IA :");
        System.out.println((Tree.generation_naive(game.partie).getRoot().toString()));
        System.out.println();

        //On vérifie que la colonne n'est pas pleine/qu'elle ne dépasse pas le tableau
        while(!Deroulement_partie.colonne_correcte(game.partie, col)){
            col = new Random().nextInt(game.partie.getColonnes());
        }
        //On place le pion au fond de la colonne
        grille[Deroulement_partie.gravite(grille, col)][col] = 2;
    }


    /** Méthode qui retourne le champ partie d'une instance de la classe Partie_IA
     *
     * @return Partie_humain le champ partie de l'objet courant
     */
    public Partie_humain getPartie(){return this.partie;}


    /** Méthode qui initialise le champ partie d'une instance de la classe Partie_IA en fonction du paramètre donné
     *
     * @param p la valeur que l'on souhaite donner au champ partie de l'objet courant
     */
    public void setPartie(Partie_humain p){
        this.partie = p;
    }


    /** Méthode qui retourne le champ lvl d'une instance de la classe Partie_IA
     *
     * @return Niveau, le champ lvl de l'objet courant
     */
    public Niveau getLvl(){return this.lvl;}


    /** Fonction qui permet de claculer le score de l'IA minMax. On donne une priorité aux coups qui bloquent les combinaisons
     *  du joueur adverse.
     *
     * @param p représentant le champ partie d'une instance de Partie_IA
     * @return le score de l'IA associé à la disposition des pions
     */
    public static int scoreIA(Partie_humain p){
        return (p.score(2) - 2*p.score(1));
    }
}
