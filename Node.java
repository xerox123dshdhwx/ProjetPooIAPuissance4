import java.util.ArrayList;

/** Cette classe permettra de modéliser les noeuds des arbres de possibilité de coups des IA en cours de partie.
 * Chaque Node aura un champ info (qui contiendra l'état d'une partie en cours après un coup possible de l'IA),
 * un score (un entier qui servira à évaluer le coup de l'IA), et un champ fils (une liste chaînée qui contiendra
 * les Node fils du Node actuel).
 *
 *
 * @author AIT KHELIFA Tanina & BOUGHANMI Rami
 * @version 24/12/2020
 */

public class Node
{
    private Partie_humain info;
    private int score;
    private boolean parcourir = false;
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


    /** Méthode qui retourne le champ info d'une instance de la classe Node
     *
     * @return Partie_humain le champ info de l'objet courant
     */
    public Partie_humain getInfo() { return info; }


    /** Méthode qui retourne le champ fils d'une instance de la classe Node
     *
     * @return ArrayList<Node> le champ fils de l'objet courant
     */
    public ArrayList<Node> getFils() {return this.fils;}


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


    /** Fonction qui permet de créer et retourner un Node qui sera la racine d'un Tree (arbre) des possibilités de coup
     * de l'IA à partir d'un état de jeu (paramètre Partie_humain)
     *
     * @param p correspondant au champ partie d'une Partie_IA en cours
     * @param profondeur la profondeur à la quelle on souhaite s'arrêter
     * @return la racine de l'arbre des possibilité de coups sur une profondeur donnée
     */
    public static Node node_c_air(Partie_humain p, int profondeur){
        if(Deroulement_partie.check_win(p)){
            return null;
        } else {
            Node racine = new Node(p);
            racine.evalNoeud();
            node_c_air_aux(racine, racine.info, profondeur);
            return racine;
        }

    }

    /** Méthode qui permet de créer les fils d'un Node, créant ainsi une arborescence représentant les coups possibles
     * pour une partie jusqu'à la profondeur souhaitée
     *
     * @param n le Node dont on souhaite créer les fils
     * @param p la Partie_humain stockée dans ce Node
     * @param profondeur int, la profondeur à laquelle on souhaite arrêter la creation de l'arbre
     */
    public static void node_c_air_aux(Node n, Partie_humain p, int profondeur){
        if((Deroulement_partie.check_win(p)) || (profondeur == 0)) {
            n.fils = null;
        } else {
            ArrayList<Node> f = new ArrayList<>();
            //Variable qui permettra de modéliser les possibilités sans altérer la partie en cours
            Partie_humain temp;
            for(int c = 0; c<p.getColonnes(); c++){
                temp = new Partie_humain(p);
                if(temp.getNb_coups()%2 == 1){
                    if(!Deroulement_partie.colonne_pleine(temp.getTab_de_jeu(), c)){
                        //On ajoute le pion au fond de la colonne choisie de la grille de jeu
                        temp.getTab_de_jeu()[Deroulement_partie.gravite(temp.getTab_de_jeu(), c)][c] = 2;
                        //On augmente le nb de coups joués
                        temp.setNb_coups(temp.getNb_coups() + 1);
                        //On ajoute la possibilité dans l'ArrayList des fils
                        Node a = new Node(temp);
                        a.evalNoeud();
                        f.add(a);
                    }
                } else {
                    //On simule les coups de l'autre joueur
                    if(!Deroulement_partie.colonne_pleine(temp.getTab_de_jeu(), c)){
                        //On ajoute le pion au fond de la colonne choisie de la grille de jeu
                        temp.getTab_de_jeu()[Deroulement_partie.gravite(temp.getTab_de_jeu(), c)][c] = 1;
                        temp.setNb_coups(temp.getNb_coups() + 1);
                        //On ajoute la possibilité dans l'ArrayList des fils
                        Node a = new Node(temp);
                        a.evalNoeud();
                        f.add(a);
                    }
                }
            }
            n.fils = f;
            for(Node fils : n.fils){
                //On fait l'appel récursif pour chaque fils
                node_c_air_aux(fils, fils.info, profondeur-1);
            }
        }
    }

    /** Méthode qui permet de déterminer et de jouer le meilleur coup pour l'IA
     *
     * @return Partie_humain, l'évolution de la partie une fois que l'IA a joué
     */
    public Partie_humain meilleurCoup(){
        if(this.fils != null){
            return (this.meilleurCoup_aux(new ArrayList<Node>()).info);
        } else {
            return null;
        }
    }


    /** Méthode qui permet de détermineR le chemin de noeud (modélisé par une ArrayList de Node) menant à la grille la plus
     * avantageuse pour l'IA.
     *
     * @param chemin on initialise le paramètre à une ArrayList vide de Node
     * @return la suite de Node menant à la grille la plus avantageuse pour le joueur
     */
    public Node meilleurCoup_aux(ArrayList<Node> chemin){
        if(this.fils != null){
            int test = this.fils.get(0).score;
            Node temp = this.fils.get(0);
            for(Node f : this.fils){
                if(f.score > test){
                    test = f.score;
                    temp = f;
                }
           }
            chemin.add(temp);
            return temp.meilleurCoup_aux(chemin);
        } else {
            return chemin.get(0);
        }
    }


    /** Méthode qui permet d'afficher le sous-arbre que représente le Node courant*/
    public void affiche_Node() {
        System.out.println(this.toString());
        if (this.fils != null){
            for (Node fils : this.fils) {
                fils.affiche_Node();
            }
        }
    }

    /** Méthode qui permet d'afficher le sous-arbre que représente le Node courant*/
    public void affiche_NodeElagage() {
        System.out.println(this.toString());
        if (this.fils != null){
            for (Node fils : this.fils) {
                if(fils.parcourir) {
                    fils.affiche_Node();
                }
            }
        }
    }

    /** Méthode qui permet de faire l'élagage AlphaBeta d'un Node et de ses fils. Les Nodes élagués, après appel de cette méthode, auront
     * comme valeur du champ parcourir : "false". C'est grâce à ce boolean que l'on détermine si un Node doit être parcouru.
     *
     * @param alpha initialisé à la valeur minimale entière Integer.MINVALUE
     * @param beta initialisé à la valeur maximale entière Integer.MAXVALUE
     * @return int : si c'est au tour du joueur 1 correspond au plus petit champ score des fils de l'objet courant, sinon du plus grand score.
     */
    public int elagage(int alpha, int beta) {
        if (this.fils != null) {
            int v;
            if (this.info.getNb_coups() % 2 == 0) {
                v = Integer.MAX_VALUE;
                for (Node f : this.fils) {
                    if (f.elagage(alpha, beta) <= v) {
                        v = f.elagage(alpha, beta);
                    }
                    if (alpha >= v) {
                        f.parcourir = true;
                        return v;
                    } else {
                        return alpha;
                    }
                }
                if (v < beta) {
                    beta = v;
                }
            } else {
                v = Integer.MIN_VALUE;
                for (Node f : this.fils) {
                    if (f.elagage(alpha, beta) >= v) {
                        v = f.elagage(alpha, beta);
                    }
                    if (beta <= v) {
                        f.parcourir = true;
                        return v;
                    } else {
                        return beta;
                    }
                }
                if (v > alpha) {
                    alpha = v;
                }
            }
        }
        this.parcourir = true;
        return this.score;
    }


    /** Fonction qui permet de représenter les champ info d'un sous-arbre formé par
     *  un Noeud sous forme de String.
     *
     * @return String, correspondant aux champs info d'un Node et ses fils
     */
    @Override
    public String toString()
    {
        String retour = Deroulement_partie.save(this.info).substring(0, Deroulement_partie.save(this.info).length()-1) + " -> (";
        if(this.fils != null){
            for(Node valeur : this.fils){
                retour = retour + Deroulement_partie.save(valeur.info).substring(0, (Deroulement_partie.save(valeur.info).length()-1)) + " -> ";
            }
        }
        return (retour + "x)");
        }


    /** Méthode qui permet d'évaluer un Noeud (donner une valeur au champ score d'une instance de Node). Explication de l'évaluation :
     *  on considère que l'on calcule le score du joueur 1 (J1). L'évaluation est calculée de la manière suivante :
     *  on compte le nombre d'alignements que le J1 peut compléter (on ne compte pas les alignements "bloqués" car inutiles.
     *  On fait de même pour l'adversaire. Pour obtenir l'évaluation d'un coup (qui est donc le champ score d'un Node),
     *  on soustrait le score du J2 à celui du J1 (J1 - J2).
     *  Dans le cadre de l'utilisation de l'IA, on fait J2 - 2*J1 pour mettre une priorité au "blocage" des alignements du joueur humain.
     *
     */
    public void evalNoeud(){
        this.score = Partie_IA.scoreIA(this.info);
    }


    /** Méthode qui permet d'afficher le champ score d'une instance de Node */
    public void afficheEval(){
        System.out.println(this.score);
        if (this.fils != null){
            for (Node fils : this.fils) {
                fils.afficheEval();
            }
        }
    }
}
