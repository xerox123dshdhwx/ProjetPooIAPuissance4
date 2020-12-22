import java.util.Scanner;


// Rappel pour moi : les lignes c'est sur l'axe vertical (i), les colonnes sur l'axe horizontal (j)

public class Deroulement_partie {


    /** Méthode qui détermine si c'est au joueur 1 ou 2 de jouer, et permet à ce joueur de placer un pion dans la grille de jeu.
     * Elle prend en paramètre un objet de la classe Partie_humain et modifie son champ tab_de_jeu et incrémente son champ nb_tours.
     *
     * @param game représente une partie de Puissance 4 en cours
     */
    public static void tour(Partie_humain game){
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
    public static void add_pion(Partie_humain game, int pion){
        int [][]grille = game.getTab_de_jeu();
        Scanner input = new Scanner(System.in);
        System.out.println("Dans quel colonne voulez-vous mettre un pion ?" +
                "Veuillez entrez un nombre entre 0 et " + (game.getColonnes() -1) + ".");
        int col = input.nextInt();

        while(!colonne_correcte(game, col)){
            col = input.nextInt();
        }
        //On place le pion au fond de la colonne
        grille[gravite(grille, col)][col] = pion;
    }

    /** Fonction qui permet de déterminer si une colonne d'un tableau d'entiers à deux dimensions est remplie (tous ses éléments sont
     * différents de 0)
     *
     * @param tab représentant le grille d'une partie en cours
     * @param c int représentant la colonne sur laquelle on fait la vérification
     * @return boolean qui vaut "true" si la colonne testée est pleine, "false" sinon
     */
    public static boolean colonne_pleine(int[][] tab, int c){
        if(tab[0][c] != 0){
            return true;
        } else {
            return false;
        }
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
                System.out.println("La colonne " + col + "est pleine, veuillez choisir une autre colonne");
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
    private static boolean ligne_vide(int[] tab){
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
            for (int j = 0; (j < p.getColonnes()); j++) {
                if (tab[p.getLignes() - 1][j] != 0) {
                    for (int i = p.getLignes() - 1; (i >= 3) && (tab[i][j] != 0); i--) {
                        if ((tab[i][j] == tab[i - 1][j]) && (tab[i][j] == tab[i - 2][j])
                                && (tab[i][j] == tab[i - 3][j])) {
                            if(tab[i][j] == 1){
                                System.out.println(p.getJoueur_1() + " a gagné !");
                            } else {
                                System.out.println(p.getJoueur_2() + " a gagné !");
                            }
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
            while ((!ligne_vide(tab[k])) && (k>=0)) {
                for (int j = p.getColonnes()-1; j >= 3; j--) {
                    if(tab[k][j] != 0){
                        if ((tab[k][j] == tab[k][j - 1]) && (tab[k][j] == tab[k][j - 2])
                            && (tab[k][j] == tab[k][j - 3])) {
                            if (tab[k][j] == 1) {
                            System.out.println(p.getJoueur_1() + " a gagné !");
                            } else {
                            System.out.println(p.getJoueur_2() + " a gagné !");
                            }
                            return true;
                        } else {
                            if ((tab[k][j] == tab[k][j - 1]) && (tab[k][j] == tab[k][j - 2])
                                && (tab[k][j] != tab[k][j - 3])) {
                                k -= 2;
                            } else {
                                if ((tab[k][j] == tab[k][j - 1]) && (tab[k][j] != tab[k][j - 2])
                                    && (tab[k][j] != tab[k][j - 3])) {
                                    k -= 1;
                                }
                            }
                        }
                    }
                }
                k--;
            }

            //Recherche des alignements diagonaux \
            for (int i = p.getLignes()-1; i > 2; i--){
                 for (int j = p.getColonnes() - 1; j > 2; j--){
                    if (tab[i][j] != 0) {
                        if ((tab[i][j] == tab[i - 1][j - 1]) && (tab[i][j] == tab[i - 2][j - 2])
                                && (tab[i][j] == tab[i - 3][j - 3])) {
                            if (tab[i][j] == 1) {
                                System.out.println(p.getJoueur_1() + " a gagné !");
                            } else {
                                System.out.println(p.getJoueur_2() + " a gagné !");
                            }
                            return true;
                        }
                    }
                }

            }
            //Recherche des alignements diagonaux /
            for (int i = p.getLignes()-1; i > 2; i--){
                  for (int j = 0; j < p.getColonnes() - 3; j++){
                      if(tab[i][j] != 0) {
                          if ((tab[i][j] == tab[i - 1][j + 1]) && (tab[i][j] == tab[i - 2][j + 2])
                                  && (tab[i][j] == tab[i - 3][j + 3])) {
                              if (tab[i][j] == 1) {
                                  System.out.println(p.getJoueur_1() + " a gagné !");
                              } else {
                                  System.out.println(p.getJoueur_2() + " a gagné !");
                              }
                              return true;
                          }
                      }

                }
            }
        }
        return false;
    }

    /** Fonction qui retourne le String qui servira de sauvegarde pour la partie
     *
     * @param p la partie de jeu en cours
     * @return la String de sauvegarde de la partie
     */
    public static String save(Partie_humain p){
        int[][] temp = p.getTab_de_jeu();
        //Préparation des premiers caractères (@ lignes colonnes )
        String sauvegarde;
        sauvegarde = "@" + p.getLignes() + " " + p.getColonnes() + " ";
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
        sauvegarde = sauvegarde + "@";
        return sauvegarde;
    }

}


