import java.util.ArrayList;

public class Node
{
    private Partie_humain info;
    /*Comme on ne sait pas combien de possibilité il existe, on stocke les fils dans une
    * liste chaînée car les ArrayList ont une taille dynamique*/
    private ArrayList<Node> fils;


    /** Constructeur d'objet de la classe Node prenant en paramètre une instance de Partie_humain
     *
     * @param val qui sera la valeur du champ info du Node créé
     */
    public Node(Partie_humain val)
    {
        this.info = val;
    }

    /** Fonction qui permet de générer les possibilités de coup de l'IA sur un tour, à partir d'une partie en cours
     *
     * @param p la partie en cours
     * @return Node, contenant en info la partie en cours et en fils l'ArrayList qui contient les coups possibles
     */
    public static Node naive(Partie_humain p){
        Node retour = new Node(p);
        ArrayList<Node> f = new ArrayList<>();
        //Variable qui permettra de modéliser les possibilités sans altérer la partie en cours
        Partie_humain temp;
        for(int c = 0; c<p.getColonnes(); c++){
            temp = new Partie_humain(p);
            if(Deroulement_partie.colonne_correcte(temp, c)){
                //On ajoute le pion au fond de la colonne choisie de la grille de jeu
                temp.getTab_de_jeu()[Deroulement_partie.gravite(temp.getTab_de_jeu(), c)][c] = 2;
                f.add(new Node(temp));
            }
        }
        retour.fils = f;
        return retour;
    }

    /** Méthode qui retourne le champ info d'une instance de la classe Node
     *
     * @return Partie_humain le champ info de l'objet courant
     */
    public Partie_humain getInfo() { return info; }

    /** Méthode qui retourne le champ
     *
     * @param i
     */
    public void  setInfo(Partie_humain i) {info = i; }

      
    @Override
    public String toString()
    {
        String retour = Deroulement_partie.save(this.info) + " -> (";
        for(Node valeur : this.fils){
            retour = retour + Deroulement_partie.save(valeur.info) + " -> ";
        }
        return (retour + "x)");
    }
    
}
