import java.util.Scanner;


/** Cette classe contient des méthodes et fonctions utiles au déroulement de parties de puissances 4, qu'elles soient contre
 * un humain, ou contre une IA.
 *
 * @author AIT KHELIFA Tanina
 * @version 03/01/2021
 */

// Rappel pour moi : les lignes c'est sur l'axe vertical (i), les colonnes sur l'axe horizontal (j)

public class Deroulement_partie {


    /** Fonction qui détermine si c'est au joueur 1 ou 2 de jouer, et permet à ce joueur de placer un pion dans la grille de jeu.
     * Elle prend en paramètre un objet de la classe Partie_humain, altère son champ tab_de_jeu et incrémente son champ nb_tours.
     *
     * @param game représente une partie de Puissance 4 en cours
     * @return  le String qui servira de sauvegarde de la partie
     */
    public static String tour(Partie_humain game){
            if(game.getNb_coups() %2 == 0){
                System.out.println("Au tour de " + game.getJoueur_1() + " de jouer !");
                add_pion(game, 1);
            } else {
                System.out.println("Au tour de " + game.getJoueur_2() + " de jouer !");
                add_pion(game, 2);
            }
            game.setNb_coups(game.getNb_coups()+1);
            return save(game);
    }


    /** Fonction qui permet à un joueur d'ajouter un de ses pions dans la grille
     *
     * @param game représente la partie de Puissance 4 en cours
     * @param pion int : représente un pion (1 si le pion est au joueur 1, 2 s'il est au joueur 2)
     */

    public static void add_pion (Partie_humain game, int pion){
        int[][] grille = game.getTab_de_jeu();
        Scanner input = new Scanner(System.in);
        System.out.println("Dans quel colonne voulez-vous mettre un pion ? " +
                "Veuillez entrez un nombre entre 0 et " + (game.getColonnes() -1) + ".");
        String col = input.next(); //Je prends une entrée

        //Pour ne pas avoir d'exception : il faut vérifier qu'on entre bien un entier, pas autre chose
        while(!stringIsInt(col)){
            System.out.println("Veuillez entrez un entier entre 0 et " + (game.getColonnes() -1) + ".");
            input.nextLine(); //Je passe à la ligne suivante (qui est vide)
            col = input.next(); //Je prends une nouvelle entrée
        }

        int c = Integer.parseInt(col);

        while(!colonne_correcte(game, c)){
            //Pour ne pas avoir d'exception : il faut vérifier qu'on entre bien un entier, pas autre chose
            input.nextLine();
            col = input.next();
            while(!stringIsInt(col)){
                System.out.println("Veuillez entrez un entier entre 0 et " + (game.getColonnes() -1) + ".");
                input.nextLine();
                col = input.nextLine();
            }
            c = Integer.parseInt(col);
        }

        //On place le pion au fond de la colonne
        grille[gravite(grille, c)][c] = pion;
    }


    /** Fonction qui permet de déterminer si une colonne d'un tableau d'entiers à deux dimensions est remplie (tous ses éléments sont
     * différents de 0)
     *
     * @param tab représentant le grille d'une partie en cours
     * @param c int représentant la colonne sur laquelle on fait la vérification
     * @return boolean qui vaut "true" si la colonne testée est pleine, "false" sinon
     */
    public static boolean colonne_pleine(int[][] tab, int c){
        return tab[0][c] != 0;
    }


    /** Fonction qui vérifie si le numéro de colonne donné par le joueur est valide, et s'il reste de al place pour y déposer un pion.
     *
     * @param game la partie en cours
     * @param col le numéro de colonne entré par le joueur
     * @return "true" si le numéro est valide, "false" sinon
     */
    public static boolean colonne_correcte(Partie_humain game, int col){
        //On vérifie que le nombre entré est valide
        if((col >= game.getColonnes()) || (col < 0)){
            System.out.println("Veuillez entrer un numéro de colonne compris entre 0 et " + (game.getColonnes() - 1) + " (inclus).");
            return false;
        } else {
            //On vérifie que la colonne n'est pas pleine
            if ((colonne_pleine(game.getTab_de_jeu(), col)) || (col < 0)) {
                System.out.println("La colonne " + col + " est pleine, veuillez choisir une autre colonne.");
                return false;
            } else {
                return true;
            }
        }
    }


    /** Fonction qui permet de simuler la gravité lorsqu'un joueur ajoute un pion dans une colonne de la grille de jeu,
     * elle permettra de placer un pion par-dessus ceux qui sont déjà dans la grille.
     *
     * @param tab int[][] représentant la grille d'une partie en cours
     * @param colonne int représentant la colonne dans laquelle on veut placer un pion
     * @return int : indice de la case vide la plus basse de la colonne "colonne" du tableau passé en paramètre
     */
    public static int gravite(int[][] tab, int colonne){
        for(int i = tab.length-1; i>=0; i--){
            if(tab[i][colonne] == 0){
                return i;
            }
        }
        return -1;
    }


    /**Fonction qui permet de vérifier si un tableau d'entier est vide (si tous ses éléments sont égaux à 0).
     *
     * @param tab  représentant une ligne de la grille d'une partie en cours
     * @return boolean qui vaut "true" si le tableau en paramètre est vide, "false" sinon
     */
    public static boolean ligne_vide(int[] tab){
        for(int i = tab.length-1; i>=0 ; i--){
            if(tab[i] != 0){
                return false;
            }

        }
        return true;
    }


    /**Fonction qui recherche une victoire d'un des joueurs de la partie.
     * La fonction recherche les alignements de pions verticaux, horizontaux et diagonaux
     *
     * @param p la partie en cours
     * @return boolean la présence on non d'un alignement gagnant
     */
    public static boolean check_win(Partie_humain p){
        int[][] tab = p.getTab_de_jeu();
        if(p.getNb_coups() >= 7) {
            //Recherche des alignements verticaux
            for (int j = 0; j < p.getColonnes(); j++) {
                if (tab[p.getLignes() - 1][j] != 0) {
                    for (int i = p.getLignes() - 1; (i >= 3) && (tab[i][j] != 0); i--) {
                        if ((tab[i][j] == tab[i - 1][j]) && (tab[i][j] == tab[i - 2][j])
                                && (tab[i][j] == tab[i - 3][j])) {
                            return true;
                        } else {
                            if ((tab[i][j] == tab[i - 1][j]) && (tab[i][j] == tab[i - 2][j])
                                    && (tab[i][j] != tab[i - 3][j])) {
                                i -= 2;
                            } else {
                                if ((tab[i][j] == tab[i - 1][j]) && (tab[i][j] != tab[i - 2][j])
                                        && (tab[i][j] != tab[i - 3][j])) {
                                    i -= 1;
                                }
                            }
                        }
                    }
                }
            }
            //Recherche des alignement horizontaux
            int k = p.getLignes() - 1;
            while((k>=0)){
                if(!ligne_vide(p.getTab_de_jeu()[k])) {
                    for (int j = 0; j < p.getColonnes() - 3; j++) {
                        if (tab[k][j] != 0) {
                            if ((tab[k][j] == tab[k][j + 1]) && (tab[k][j] == tab[k][j + 2])
                                    && (tab[k][j] == tab[k][j + 3])) {
                                return true;
                            } else if ((tab[k][j] == tab[k][j + 1]) && (tab[k][j] == tab[k][j + 2])
                                    && (tab[k][j] != tab[k][j + 3])) {
                                j += 2;
                            } else if ((tab[k][j] == tab[k][j + 1]) && (tab[k][j] != tab[k][j + 2])
                                    && (tab[k][j] != tab[k][j + 3])) {
                                j += 1;
                            }
                        }
                    }
                }
                k--;
            }

        }



        //Recherche des alignements diagonaux \
        for (int i = p.getLignes()-1; i > 2; i--){
            for (int j = p.getColonnes() - 1; j > 2; j--){
                if (tab[i][j] != 0) {
                    if ((tab[i][j] == tab[i - 1][j - 1]) && (tab[i][j] == tab[i - 2][j - 2])
                            && (tab[i][j] == tab[i - 3][j - 3])) {
                        return true;
                    }
                }
            }
        }

        //Recherche des alignements diagonaux /
        for (int i = p.getLignes()-1; i >= 3; i--){
            for (int j = 0; j < p.getColonnes() - 3; j++){
                if(tab[i][j] != 0) {
                    if ((tab[i][j] == tab[i - 1][j + 1]) && (tab[i][j] == tab[i - 2][j + 2])
                            && (tab[i][j] == tab[i - 3][j + 3])) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /** Fonction qui permet de déterminer si une partie s'est achevée sur une égalité (grille remplie ET aucun alignement)
     *
     * @param p Partie_humain, la partie en cours
     * @return boolean, true si il y a bien égalité
     */
    public static boolean egalite(Partie_humain p){
        return ((p.getNb_coups() == p.getColonnes()*p.getLignes()) && (!check_win(p)));
    }


    /** Fonction qui retourne le String qui servira de sauvegarde pour une partie entre joueurs humains
     *
     * @param p la partie de jeu en cours
     * @return la String de sauvegarde de la partie
     */
    public static String save(Partie_humain p){
        int[][] temp = p.getTab_de_jeu();
        //Préparation des premiers caractères (@ lignes colonnes )
        String sauvegarde = p.getNumPartie() + "@" + p.getLignes() + " " + p.getColonnes() + " ";
        for(int i=p.getLignes()-1; i>=0; i--){
            for(int j=0; j<p.getColonnes(); j++){
                if(temp[i][j] == 1){
                   sauvegarde = sauvegarde + "x";
                } else if (temp[i][j] == 2){
                    sauvegarde = sauvegarde + "o";
                } else {
                    sauvegarde = sauvegarde + " ";
                }
            }
        }
        sauvegarde = sauvegarde + "@h";

        //On stocke les noms des joueurs si nécessaire
        if(!p.getJoueur_1().equals("Joueur 1")){
            sauvegarde = sauvegarde + "@" + p.getJoueur_1() + "@" + p.getJoueur_2() + "@";
        }

        return sauvegarde;
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
    public static String tour (Partie_IA game){
        //Le joueur 1 commence tjr en 1er, d'où cette condition modulo 2
        if(game.getPartie().getNb_coups() %2 == 0){
            System.out.println("Au tour de " + game.getPartie().getJoueur_1() + " de jouer !");
            Deroulement_partie.add_pion(game.getPartie(), 1);
            game.getPartie().setNb_coups(game.getPartie().getNb_coups() + 1);
        } else {
            System.out.println("Au tour de l'IA de jouer !");
            if(game.getLvl() == Niveau.FACILE) {
                Partie_IA.add_pion_naive(game);
                game.getPartie().setNb_coups(game.getPartie().getNb_coups() + 1);
            } else if (game.getLvl() == Niveau.MOYEN){
                Tree coups = Tree.minMaxProfondeur(game.getPartie(), 2);
                game.setPartie(coups.getRoot().meilleurCoup());
                } else {
                Tree coups = Tree.minMaxProfondeur(game.getPartie(), 5);
                coups.elagage();
                game.setPartie(coups.getRoot().meilleurCoup());
                }
            /*Affichage.affichage(game.getPartie().getTab_de_jeu());*/
            }
        return Deroulement_partie.save(game);
    }


    /** Fonction qui retourne le String qui servira de sauvegarde pour une partie contre une IA
     *
     * @param p la partie de jeu en cours
     * @return la String de sauvegarde de la partie
     */
    public static String save(Partie_IA p){
        int[][] temp = p.getPartie().getTab_de_jeu();
        //On ajoute le numéro de partie, ainsi que le nombre de lignes et de colonnes de la grille
        String sauvegarde = p.getPartie().getNumPartie() + "@" + p.getPartie().getLignes() + " "
                + p.getPartie().getColonnes() + " ";

        //Conversion du tableau en String et ajout à la sauvegarde de celui-ci
        for(int i=p.getPartie().getLignes()-1; i>=0; i--){
            for(int j=0; j<p.getPartie().getColonnes(); j++){
                if(temp[i][j] == 1){
                    sauvegarde = sauvegarde + "x";
                } else if (temp[i][j] == 2){
                    sauvegarde = sauvegarde + "o";
                } else {
                    sauvegarde = sauvegarde + " ";
                }
            }
        }
        sauvegarde = sauvegarde + "@";

        //Pas de switch sinon erreur car sauvegarde aurait pu ne pas etre initialisée
        if(p.getLvl() == Niveau.FACILE){
            sauvegarde = sauvegarde + "f";
        } else if (p.getLvl() == Niveau.MOYEN){
            sauvegarde = sauvegarde + "m";
        } else {
            sauvegarde = sauvegarde + "d";
        }

        //Ajout du nom du Joueur 1 si nécessaire
        if(!p.getPartie().getJoueur_1().equals("Joueur 1")){
            sauvegarde = sauvegarde + "@" + p.getPartie().getJoueur_1() + "@";
        }

        return sauvegarde;
    }


    /** Fonction permettant de déterminer si un String "est" un int.
     * Par exemple stringIsInt("101") renvoie true et stringIsInt("fzyvdcb") renvoie false.
     * @param entree le String à tester
     * @return boolean indiquant si le String est un int
     * */
    public static boolean stringIsInt(String entree){
        try {
            Integer.parseInt(entree);
            return true;
        } catch (NumberFormatException ex) {
            return false;
        }
    }
}
