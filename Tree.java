public class Tree
{
    private Node root;

    /**
     * Constructeur d'objets de classe Tree
     */
    public Tree()
    {
        root = null;
    }

    public Tree(Node r)
    {
        root = r;
    }

    public Node getRoot(){
        return this.root;
    }

    public boolean estVide() {
        return (root == null);
    }

    //Generation possibilité IA naive
    public static Tree generation_naive(Partie_humain p){
        return (new Tree(Node.naive(p)));

    }

}