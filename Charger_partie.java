/**
 *Cette classe permet de charger une partie a partire d'une et fournis des methode pour sauvgarder une partie.
 *
 * @author BOUGHANMI Rami
 * @version 22/11/2020
 */

import java.io.*;
import java.util.Scanner;

public class Charger_partie {

    /**
     *fonction qui vas chercher la partie N sauvgarder et la renvoie sous forme de string
     *
     * @return revoie la save de la partie N
     */
    public static String get_fichier_texte(int n){
        int radix = 10;
        char n_char = Character.forDigit(n , radix);
        String save = null;
        Scanner fluxEntree = null;
        try
        {
            fluxEntree = new Scanner(new FileInputStream("leFichier.txt"));
        }
        catch(FileNotFoundException e)
        { System.out.println("Erreur ouverture fichier. y");
            System.exit(0);
        }
        String ligne = null; int no = 0;
        while (fluxEntree.hasNextLine( )) {
            ligne = fluxEntree.nextLine( );
            no++;
            save = ligne;
            if(save.charAt(0) == n_char){
                System.out.println("La save a été trouver, a la ligne : " + no);
                fluxEntree.close();
                return save;
            }else{
                System.out.println("pas la bonne save trouver");
            }
            //System.out.println("ligne Numero :" +no + " " + ligne);
        }
        fluxEntree.close( );
        return save;
    }

    /**
     *fonction qui prend une save et la relance sous forme de tableau
     *
     * @param save prend une save
     * @return un tableau
     */
    public static Partie_humain relancer_save(String save){

        boolean interputeur = true;
        char l_c = save.charAt(2);
        int l = Integer.parseInt(String.valueOf(l_c));

        char c_c = save.charAt(4);
        int c = Integer.parseInt(String.valueOf(c_c));

        Partie_humain prth = New_partie.partie_custom_humain(c,l);
        prth.setColonnes(l);
        prth.setLignes(c);
        int x = 6;
        save = save.substring(6);
        for (int i = 0 ; (i < prth.getTab_de_jeu().length )&& (interputeur);i++){

            for(int k = 0; k<= c+k;k++){
                if(k!=0 && (k)%l == 0){
                        break;
                }else if(save.charAt(k) == '@'){
                    interputeur = false;
                    break;
                }
                if(save.charAt(k) == 'x'){
                    prth.getTab_de_jeu()[c-i-1][k] = 1;
                }else if(save.charAt(k) == 'o'){
                    prth.getTab_de_jeu()[c-i-1][k] = 2;
                }else if(save.charAt(k) == ' '){
                    prth.getTab_de_jeu()[c-i-1][k] = 0;
                }
            }
            int temp = save.length();
            save = save.substring(l%temp);
        }
        return prth;
    }


    /**
     *Fonction qui remplace la ligne de la save  de la partie numero n
     * par la nouvelle sauvagrde de la partie numero n
     *
     * @param new_save nouvelle sauvgarde
     * @param numero_de_la_save numero de la save
     * @param fichier_originel save du fichier avant la compression pour novelle save
     * @throws FileNotFoundException le fichir charger n'est pas trouver
     */
    public static void replace_save_number_(String new_save,int numero_de_la_save,File fichier_originel) throws FileNotFoundException {
        String save = get_fichier_texte(numero_de_la_save);

        File fichier = new File("leFichier_copie.txt");
        PrintWriter writer = new PrintWriter("leFichier_copie.txt");
        boolean interupteur = false;
        Scanner fluxEntree = null;
        try
        {
            fluxEntree = new Scanner(new FileInputStream("leFichier.txt"));
        }
        catch(FileNotFoundException e)
        {
            System.out.println("Error ouverture fichier. dans Replace save number");
            System.exit(0);
        }
        String ligne;

        while(fluxEntree.hasNextLine()) {
            ligne = fluxEntree.nextLine();
            interupteur = false;
            if(ligne.equals(save)){
                writer.println(new_save);
                interupteur = true;
            }else if(!interupteur) {
                writer.println(ligne);
            }
        }
        writer.close();

        fluxEntree.close();
        fichier_originel.delete();
        fichier.renameTo(fichier_originel);

    }

    /**
     *fonction qui permet de savoir combien de save au total on été faite ( et combien de ligne dans le fichier)
     *
     * @return le nombre de save au total
     */
    public static int compteur_du_nombre_de_save(){
    int cpts = 0 ;
    Scanner fluxEntree = null;
    try
    {
        fluxEntree = new Scanner(new FileInputStream("leFichier.txt"));
    }
    catch(FileNotFoundException e)
    { System.out.println("Error ouverture fichier. Dans compteur du nombre de save");
        System.exit(0);
    }
    String ligne = fluxEntree.nextLine( );

    while(fluxEntree.hasNextLine()) {
        ligne = fluxEntree.nextLine();
        if(ligne != null) {
            cpts++;
        }
    }
    fluxEntree.close();
        return cpts+1;
}

    /**
     * Fonction qui prend une une partie "save" et qui la rajoute a la fin du fichier de save
     *
     * @param save partie save
     * @param fichier_originel fichier de base avec la save
     * @throws FileNotFoundException verifie que le fichier existe
     */
    public static void injecter_save(String save,File fichier_originel) throws FileNotFoundException {

    File fichier = new File("leFichier_copie.txt");
    PrintWriter writer = new PrintWriter("leFichier_copie.txt");
    String[] tab_teste = new String[10];

    Scanner fluxEntree = null;
    try
    {
        fluxEntree = new Scanner(new FileInputStream("leFichier.txt"));
    }
    catch(FileNotFoundException e)
    { System.out.println("Error ouverture fichier. Dans injecter save");
        System.exit(0);
    }
        String ligne;

        System.err.println(compteur_du_nombre_de_save() + "avant la boucle");

        for(int i  = 0 ; i < compteur_du_nombre_de_save();i++){
            ligne = fluxEntree.nextLine();
            writer.println(ligne);
        }

        writer.println(save);
        writer.close();

        fluxEntree.close();
        fichier_originel.delete();
        fichier.renameTo(fichier_originel);

}


}




