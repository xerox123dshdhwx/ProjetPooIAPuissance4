/** Cette classe contient les constructeurs permettant de lancer une nouvelle partie de
 * Puissance 4 entre deux joueurs humains
 *
 * @author  BOUGHANMI Rami
 * @version 11/11/2020
 */


import java.util.Scanner;

public class New_partie {
    /**
     * Cree une partie avec parametre par defaut
     * @return une nouvelle partie par defaut
     */
    public static Partie_humain partie_humain(int n){
        return new Partie_humain(n);
    }

    /**
     * Cree une partie qui customise une grille en fonction du nombre de colone et ligne rentrer ainsi que
     * des noms de joueur choisi par l'utilisteur
     *  @param l represente les lignes
     * @param c represente les colones
     * @param j1 represente le nom du joueur 1
     * @param j2 represente le nom du joueur 2
     * @return une nouvelle partie
     */
    public static Partie_humain partie_custom_humain_j(int l, int c, String j1 , String j2, int n){
        return new Partie_humain(l,c,j1,j2, n);
    }

    /**
     * Cree une partie qui customise une grille en fonction du nombre de colone et ligne rentrer
     *  @param l represente les lignes
     * @param c represente les colones
     * @return une nouvelle partie de humain
     */
    public static Partie_humain partie_custom_humain(int l, int c, int n){
        return new Partie_humain(l,c,n);
    }

    /**
     *Cree une partie avec des noms de joueur choisi par l'utilisteur
     *  @param j1 represente le nom du joueur 1
     * @param j2 represente le nom du joueur 2
     * @return une nouvelle partie
     */
    public static Partie_humain partie_entre_humain_j(String j1 , String j2, int n){
        return new Partie_humain(j1,j2, n);
    }

    /**
     * new parti est une fonction qui utilise toute les foonction precedente pour cree une nouvelle partie
     *
     * @return une nouvelle partie choisi par lutilisateur
     */
    public static Partie_humain new_partie(){
        Partie_humain prth;
        Scanner sc = new Scanner(System.in);
        System.out.println("voulez vous choisir un nom ou le laisser par defaut :");
        if(sc.nextBoolean()){
            System.out.println("Ecrivez le nom du joueur 1 :");
            String nom_du_joueur_1 = sc.nextLine();
            System.out.println("Ecrivez le nom du joueur 2 :");
            String nom_du_joueur_2 = sc.nextLine();
            System.out.println("Voulez vous cree une parti custom (true or false) :");
            if(sc.nextBoolean()){
                System.out.println("Ecrivez un entier representant le nombre de colone :");
                int c = sc.nextInt();
                System.out.println("Ecrivez un entier representant le nombre de ligne ");
                int l = sc.nextInt();
                prth = partie_custom_humain_j(l,c,nom_du_joueur_1,nom_du_joueur_2, Charger_partie.compteur_du_nombre_de_save());
            }else{
                prth = partie_entre_humain_j(nom_du_joueur_1,nom_du_joueur_2, Charger_partie.compteur_du_nombre_de_save());
            }
        }else{
            System.out.println("voulez vous choisir de customiser la taille de la grille ? (true or false) :");
            if(sc.nextBoolean()){
                System.out.println("Ecrivez un entier representant le nombre de colone :");
                int c = sc.nextInt();
                System.out.println("Ecrivez un entier representant le nombre de ligne ");
                int l = sc.nextInt();
                prth = partie_custom_humain(l,c, Charger_partie.compteur_du_nombre_de_save());
            }else{
                prth = partie_humain(Charger_partie.compteur_du_nombre_de_save());
            }
        }
        return prth;
    }

    public static Partie_humain new_partie_bis(){
        Partie_humain prth;
        Scanner sc = new Scanner(System.in);
        System.out.println("voulez vous choisir un nom ou le laisser par defaut :");
        if(sc.nextBoolean()){
            System.out.println("Ecrivez le nom du joueur 1 :");
            String nom_du_joueur_1 = sc.nextLine();
            System.out.println("Voulez vous cree une parti custom (true or false) :");
            if(sc.nextBoolean()){
                System.out.println("Ecrivez un entier representant le nombre de colone :");
                int c = sc.nextInt();
                System.out.println("Ecrivez un entier representant le nombre de ligne ");
                int l = sc.nextInt();
                prth = partie_custom_humain_j(l,c,nom_du_joueur_1,"ia", Charger_partie.compteur_du_nombre_de_save());
            }else{
                prth = partie_entre_humain_j(nom_du_joueur_1,"ia", Charger_partie.compteur_du_nombre_de_save());
            }
        }else{
            System.out.println("voulez vous choisir de customiser la taille de la grille ? (true or false) :");
            if(sc.nextBoolean()){
                System.out.println("Ecrivez un entier representant le nombre de colone :");
                int c = sc.nextInt();
                System.out.println("Ecrivez un entier representant le nombre de ligne ");
                int l = sc.nextInt();
                prth = partie_custom_humain(l,c, Charger_partie.compteur_du_nombre_de_save());
            }else{
                prth = partie_humain(Charger_partie.compteur_du_nombre_de_save());
            }
        }
        return prth;
    }
    public static Partie_IA new_partie_ia(int lvl){
        Partie_IA p;
        if(lvl == 0){
            p = new Partie_IA(new_partie_bis(), Niveau.FACILE);

        }else if(lvl == 1){
            p = new Partie_IA(new_partie_bis(), Niveau.MOYEN);
        }else{
            p = new Partie_IA(new_partie_bis(), Niveau.DIFFICILE);
        }

        return p;
    }

}


/*
public static void partie_custom_ia(int l,int c,String j1 ,String j2){
         Partie_entre_ia prt_ia_c = new Partie_entre_ia();//param a mettre quand class partie ia sera cree !
        //!!!!!!!!!!
    }

     public static Partie_entre_ia partie_ia(){

        Partie_entre_ia prt_ia = new Partie_entre_ia();
        return prt_ia;
    }

 */