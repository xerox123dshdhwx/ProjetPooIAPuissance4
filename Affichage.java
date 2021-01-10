/** Cette classe contient une fonction permetant d'afficher le tableau en couleur
 *
 *
 * @author AIT KHELIFA Tanina et BOUGHANMI Rami
 * @version 11/11/2020
 */

public class Affichage {

    /**
     * affiche le contenu d'un tableau en couleur de type char
     *
     * @param tab un tableau de char
     */
    public static void affichage(int[][] tab){
        //tab = new int [tab.length+1][tab[0].length+1];
        for(int i = 0 ; i < tab.length;i++) {
            for(int j = 0; j < tab[i].length;j++) {
                if(tab[i][j] == 1) {
                    System.out.print(String_color.ANSI_PURPLE + "|" +String_color.ANSI_RED + "X" +String_color.ANSI_PURPLE + "|" + String_color.ANSI_RESET);
                } else if(tab[i][j] == 2) {
                    System.out.print(String_color.ANSI_PURPLE + "|" + String_color.ANSI_BLUE + "O" + String_color.ANSI_PURPLE + "|" +String_color.ANSI_RESET);
                } else {
                    System.out.print(String_color.ANSI_PURPLE + "| " + String_color.ANSI_PURPLE + "|" + String_color.ANSI_RESET);
                }
            }
            System.out.println("");
        }

        for(int j = 0; j < tab[0].length;j++ ){
            System.out.print(" "+String_color.ANSI_YELLOW + j + String_color.ANSI_RESET +" ");
        }
        System.out.println("");

    }

}