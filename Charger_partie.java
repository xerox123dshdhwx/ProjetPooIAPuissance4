/**
 *Cette classe permet de charger une partie a partire d'une et fournis des methode pour sauvgarder une partie.
 *
 * @author BOUGHANMI Rami
 * @version 22/11/2020
 */

import java.io.*;
import java.util.Scanner;

/** Classe permettant de charger une partie et d'écrire dans le fichier de Sauve
 *
 */

public class Charger_partie {

    /**
     *fonction qui vas chercher la partie N sauvgarder et la renvoie sous forme de string
     *
     * @param n int, numéro de sauvegarde
     * @return revoie la save de la partie N
     */
    public static String get_fichier_texte(int n) throws NullPointerException {
        Scanner scanner = new Scanner(System.in);
        int decalage = 0;


        String temp = Integer.toString(n);
        decalage = temp.length()-1;

        int radix = 10;
        //char n_char = Character.forDigit(n , radix);
        String n_char = String.valueOf(n);
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
        while(fluxEntree.hasNextLine( )) {
            ligne = fluxEntree.nextLine( );
            no++;
            save = ligne;
            //substring index depart + index de fin exclu
            String valeur = save.substring(0,1+decalage);
            /*save.charAt(0) was 0*/
            if(valeur.equals(n_char)){
                System.out.println("La save a été trouver, a la ligne : " + no);
                fluxEntree.close();
                return save;
            }else{
                System.out.println("pas la bonne save trouver");
            }
            //System.out.println("ligne Numero :" +no + " " + ligne);
        }
        fluxEntree.close( );
        throw new NullPointerException("Cette sauvgarde nexiste pas !") ;
    }
    /**
     * permet de verifier le nombre de chiffre present derriere le arrobase pour bien en prendre en compte
     * si il faut decaler ou pas l'indice pour recuper le nb de colone et de ligne
     *
     * @param save prend une save en parametre
     * @return le nombre de chiffre derriére l'arrobase
     */
    public static int nb_av_arobase (String save){
        int i = 0;
        int cpt = 0;

        while(save.charAt(i) != '@'){
            cpt++;
            i++;
        }

        return cpt-1;
    }
    /**
     * Fonction qui sert a relancer une sauvgarde d'une ia en appelant save
     * @param save sauvgarde d'une partie
     * @param lvl prend le niveau de difficuluté de la partie ia
     * @return une partie humain contre ia
     */
    public static Partie_IA relance_save_ia(String save, int lvl){

        System.out.println(String_color.ANSI_GREEN + save + String_color.ANSI_RESET );
        int cpt = 0;
        int decalage = nb_av_arobase(save);

        //save = save.substring(0, save.length()-1);
        System.out.println(String_color.ANSI_BLUE + save + String_color.ANSI_RESET );

        Partie_humain prth = relancer_save(save);
        Partie_IA prt_ia;

        if(lvl == 0) {
            prt_ia = new Partie_IA(prth,Niveau.FACILE);
        }else if(lvl == 1){
            prt_ia = new Partie_IA(prth, Niveau.MOYEN);
        }else{
            prt_ia = new Partie_IA(prth, Niveau.DIFFICILE);
        }

        return prt_ia;
    }
    /**
     *fonction qui prend une sauvgarde et renvoie sa partie humain avec sont affichage tableau
     *
     * @param save prend une sauvgarde
     * @return une partie entre humain
     */
    public static Partie_humain relancer_save(String save){
        System.out.println("save entre en debut de fct :" + save);
        String save_temp = save;
        int cpt = 0 ;
        int decalage = nb_av_arobase(save);

        boolean interputeur = true;

        char c_num = save.charAt(decalage);//0 was
        int num_save = Integer.parseInt(String.valueOf(c_num));

        char l_c = save.charAt(2+decalage);//2 was
        int l = Integer.parseInt(String.valueOf(l_c));

        char c_c = save.charAt(4+decalage);//4 was
        int c = Integer.parseInt(String.valueOf(c_c));

        Partie_humain prth = New_partie.partie_custom_humain(l,c, num_save);

        int x = 6+decalage;// was 6
        save = save.substring(6+decalage);//was 6
        //System.err.println("avant boucle " + save);
        for (int i = 0 ; (i < prth.getTab_de_jeu().length )&& (interputeur);i++){

            for(int k = 0; k<= l+k;k++){
                if(k!=0 && (k)%c == 0){
                    break;
                }else if(save.charAt(k) == '@'){
                    interputeur = false;
                    break;
                }
                if(save.charAt(k) == 'x'){
                    prth.getTab_de_jeu()[l-i-1][k] = 1;
                    cpt++;
                }else if(save.charAt(k) == 'o'){
                    prth.getTab_de_jeu()[l-i-1][k] = 2;
                    cpt++;
                }else if(save.charAt(k) == ' '){
                    prth.getTab_de_jeu()[l-i-1][k] = 0;
                }
            }
            int temp = save.length()-1;// was without -1
            save = save.substring(c%temp);

        }

        save_temp = save_temp.substring(nombre_char_avant_le_type_de_partie(save_temp));

        if(save_temp.length() == 0){
            System.out.println(String_color.ANSI_BLUE + "Save with out usernam !" + String_color.ANSI_RESET);
            System.out.println("Default username : "+prth.getJoueur_1());

            System.out.println("Default username : "+prth.getJoueur_2());
            prth.setNb_coups(cpt);
            return prth;
        } else{
            prth.setNb_coups(cpt);
            save_temp = save_temp.substring(1);

            String temp1 = "";
            String temp2 = "";
            int cpts = 0;

            for(int k = 0 ; k<save_temp.length();k++){
                if(save_temp.charAt(k) !='@' && (cpts == 0)){
                    temp1 += save_temp.charAt(k);
                }else if(save_temp.charAt(k) =='@'){
                    cpts++;
                }else{
                    temp2 += save_temp.charAt(k);
                }
            }

            System.out.println("Username 1 :" + temp1);
            System.out.println("Username 2 :" + temp2);

            if(temp2.equals("")){
                prth.setJoueur_1(temp1);
                prth.setJoueur_2("ia");
                System.out.println("Username 2 : ia");
                prth.setNb_coups(cpt);
                return prth;
            }else{
                prth.setJoueur_1(temp1);
                prth.setJoueur_2(temp2);
                prth.setNb_coups(cpt);

                return prth;
            }
        }
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

        System.out.println(compteur_du_nombre_de_save() + "avant la boucle");

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

    /** Fonction qui permet de supprimer une sauvegarde
     *
     * @param numero_de_la_save int, numéro de la sauvegarde à supprimer
     * @param fichier_originel fichier de sauvegarde
     * @throws FileNotFoundException exception
     */
    public static void delete_save_number(int numero_de_la_save,File fichier_originel) throws FileNotFoundException {
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
     * cette fonction permet de s'avoir le nombre de charactere avant le type de la sauvgarde.
     * @param save prend une sauvgarde d'une partie
     * @return le nombre de charactere entre le debut et le type de la sauvgarde(humain, ia_dificile, ia_moyen,ia_facil)
     */
    public static int nombre_char_avant_le_type_de_partie(String save){
        int cpt = 0;
        int cpt_aux = 0;
        for(int k = 0 ; k < save.length();k++){
            cpt_aux++;
            if(save.charAt(k) == '@'){cpt++;}
            if(cpt == 2){break;}
        }

        return cpt_aux+1;
    }
}
