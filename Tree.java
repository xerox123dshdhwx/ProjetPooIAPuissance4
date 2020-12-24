public class Tree

/** Cette classe permettra de modéliser les arbres de possibilité de coups des IA en cours de partie
 *
 *
 * @author AIT KHELIFA Tanina & BOUGHANMI Rami
 * @version 24/12/2020
 */

{
    private Node root;

    /** Constructeur d'objets de classe Tree */
    public Tree() { root = null; }

    /** Constructeur d'objet de la classe Tree qui initialise le champ root du Tree créé au Node passé en paramètre
     *
     * @param r Node qui sera le champ root de l'instance de Tree créée
     */
    public Tree(Node r)
    {
        root = r;
    }

    /** Méthode retournant le champ root d'une instance de la classe Tree
     *
     * @return Node, le champ root de l'objet courant
     */
    public Node getRoot(){return this.root;}

    public boolean estVide() {
        return (root == null);
    }

    //Generation possibilités IA naive

    /** Fonction qui génère un Tree contenant en champ root le Node qui a pour fils les possibilités de coup de l'IA naïve sur
     * un tour, pour la partie passée en paramètre.
     *
     * @param p Partie_humain (correspondant au champ partie d'une instance de la classe Partie_IA)
     * @return Tree
     */
    public static Tree generation_naive(Partie_humain p){
        return (new Tree(Node.naive(p)));

    }

}