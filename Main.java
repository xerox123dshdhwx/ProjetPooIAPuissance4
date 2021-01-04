import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(System.in);
        boolean val_b = true;
        Date_base dta = new Date_base();
        boolean interupteur_partie = true;

        while(interupteur_partie){
            System.out.println(String_color.ANSI_GREEN +"Bienvenue  Dans le⁴ !!" + String_color.ANSI_RESET);
            System.out.println(String_color.ANSI_YELLOW + "Voulez vous jouez a notre jeux ??? (true or false) : " + String_color.ANSI_RESET);
            val_b = New_partie.isBoolean();

            if(!val_b){
                //Si le mec veut pas jouer
                System.out.println(String_color.ANSI_BLUE + "Merci de votre visite revenez nous vite !" +"ლ(｡-﹏-｡ ლ)"
                        + String_color.ANSI_RESET);
                interupteur_partie = val_b;
            } else {
                System.out.println(String_color.ANSI_CYAN + "SUPER JE SUIS TROP HEUREUX JOUEUR-SAMA !! " + String_color.ANSI_RED+" (ﾉ◕ヮ◕)ﾉ*:･ﾟ✧ " + String_color.ANSI_RESET);
                System.out.println(String_color.ANSI_YELLOW + "Voulez vous charger une ancienne partie (true or false) : " + String_color.ANSI_RESET);
                boolean rep1 = New_partie.isBoolean();

                //Le mec veut charger une ancienne partie
                if(rep1) {
                    clearConsole();
                    System.out.println(String_color.ANSI_YELLOW + "Entre le numero de la sauvgarde de lancienne partie a charger !: " + String_color.ANSI_RESET);
                    int n = 0;

                    do {
                        System.out.println(String_color.ANSI_YELLOW +"Quelle est le numero de la sauvgarde que vous voulez lancer ! "+ String_color.ANSI_RESET);
                        n = New_partie.isint();
                    } while(n < 1);

                    String save = Charger_partie.get_fichier_texte(n);
                    int val = Charger_partie.nombre_char_avant_le_type_de_partie(save);
                    String save_temp = save.substring(0,val);
                    clearConsole();

                    if((save_temp.charAt(save_temp.length()-1) != 'h')){
                        Partie_IA prt_ia;
                        if(save_temp.charAt(save_temp.length()-1) == 'f'){
                            prt_ia = Charger_partie.relance_save_ia(save,0);
                            System.out.println("difficulté : facile");

                        } else if (save_temp.charAt(save_temp.length()-1) == 'm') {
                            prt_ia = Charger_partie.relance_save_ia(save,1);
                            System.out.println("difficulté : moyen");

                        } else {
                            prt_ia = Charger_partie.relance_save_ia(save,2);
                            System.out.println("difficulté : dificle");
                        }
                        Affichage.affichage(prt_ia.getPartie().getTab_de_jeu());
                        jouer_ancienne_partie(prt_ia);

                    } else {
                        System.out.println("Save entre humain relancée !");
                        Partie_humain prth = Charger_partie.relancer_save(save);
                        Affichage.affichage(prth.getTab_de_jeu());
                        if(jouer_ancienne_partie(prth)){
                            System.out.println("ok continue ><");
                        }else{
                            break;
                        }
                    }


                    //On lance une nouvelle partie
                }else{
                    System.out.println(String_color.ANSI_YELLOW + "Voulez vous cree une partie contre une ia ?(true or false)  " +
                            "si false cela crera une partie contre humain" + String_color.ANSI_RESET);
                    boolean rep2 = New_partie.isBoolean();

                    //Partie avec IA
                    if(rep2){
                        System.out.println("Choisissez la difficulté de l'IA (entrez l'entier correspondant au niveau que vous souhaitez) :");
                        System.out.println("(0) Facile");
                        System.out.println("(1) Moyen");
                        System.out.println("(3) Difficile");
                        jouer_partieIA(New_partie.new_partie_ia(New_partie.isint()));

                    }else{
                        if(jouer_partie(New_partie.new_partie())){
                            System.out.println("ok continue la");
                        }else{
                            break;
                        }
                    }
                }
            }
        }
        if(val_b) {
            System.out.println(String_color.ANSI_YELLOW + "Merci de votre visite revenez nous vite !" + "ლ(｡-﹏-｡ ლ)"
                    + String_color.ANSI_RESET);
        }else{
            System.out.print("");
        }
       /* int x = 2;
        Date_base.data_base();


        String save = Charger_partie.get_fichier_texte();
       // System.out.println(save);
        int val = Charger_partie.nombre_char_avant_le_type_de_partie(save);
        //System.out.println(String_color.ANSI_BLUE + val +String_color.ANSI_RESET);
        String save_temp = save.substring(0,val);

        if((save_temp.charAt(save_temp.length()-1) != 'h')){
            Partie_IA prt_ia;
            if(save_temp.charAt(save_temp.length()-1) == 'f'){
                 prt_ia = Charger_partie.relance_save_ia(save,0);
                 System.err.println("difficulté : facile");

            }else if(save_temp.charAt(save_temp.length()-1) == 'm') {
                 prt_ia = Charger_partie.relance_save_ia(save,1);
                System.err.println("difficulté : moyen");
            }else{
                 prt_ia = Charger_partie.relance_save_ia(save,2);
                System.err.println("difficulté : dificle");
            }
            prt_ia.getPartie().getTab_de_jeu();
            Affichage.affichage(prt_ia.getPartie().getTab_de_jeu());
        }else {
            System.err.println("Save entre humain relancer !");
            Partie_humain prth = Charger_partie.relancer_save(save);
            //prth.getTab_de_jeu();
            Affichage.affichage(prth.getTab_de_jeu());
        }




        //System.out.print(Charger_partie.compteur_du_nombre_de_save());
        ////////////////////
        //Charger_partie.replace_save(3,"*3 3 xxx x o*");
        Date_base dta = new Date_base();
        int n = 0;
        do{
            System.out.println(String_color.ANSI_YELLOW +"Quelle est le numero de la sauvgarde que vous voulez lancer ! "+ String_color.ANSI_RESET);
            n = New_partie.isint();
        }while(n < 1);
        Charger_partie.replace_save_number_("1@3 3 xox x o @j122222@j33@",n, dta.getFichier());
        //System.out.println(save);

        //Charger_partie.injecter_save("5@3 6 xox x o @",dta.getFichier());
        //System.out.println(Charger_partie.compteur_du_nombre_de_save()+ "Dans le main");
*/

    }
    public  static void clearConsole()
    {
        for(int k = 0 ; k <50 ;k++){System.out.println();}
    }

    public static boolean jouer_partie(Partie_humain prth) throws FileNotFoundException {
        Date_base date_base = new Date_base();
        String save = "";
        boolean rep = false;
        boolean interupteur = true;
        while ((!Deroulement_partie.check_win(prth)) && (!Deroulement_partie.egalite(prth))) {
            save = Deroulement_partie.tour(prth);
            Affichage.affichage(prth.getTab_de_jeu());

            System.out.println();
            System.out.println("Etat de la sauvegarde :" + Deroulement_partie.save(prth));
            System.out.println();
            if(!Deroulement_partie.egalite(prth)){
                System.out.println(String_color.ANSI_RED + "VOULEZ VOUS CONTINUER LA PARTIE ?" + String_color.ANSI_RESET);
                rep = New_partie.isBoolean();
                if (!rep) {
                    interupteur = false;
                    break;
                }
            }else{
                rep = true;
                interupteur = true;
            }
        }
        if((!Deroulement_partie.check_win(prth)) && (Deroulement_partie.egalite(prth))){
            System.out.println("Egalité");

        } else {
            if(prth.getNb_coups()%2 == 0){
                System.out.println(prth.getJoueur_2() + " a gagné!");
            } else {
                System.out.println(prth.getJoueur_1() + " a gagné!");
            }
        }
        if(interupteur) {

            System.out.println(String_color.ANSI_BLUE + "JEUX TERMINER ! -> NOUVEAU JEUX DONC PAS DE SAVE SURPPRIMER " + String_color.ANSI_RESET);
        }else{
            Charger_partie.injecter_save(save,date_base.getFichier());
            System.out.println(String_color.ANSI_BLUE + "JEUX MIS EN PAUSE ! -> SAVE AJOUTER" + String_color.ANSI_RESET);
        }
        return false;
    }

    public static boolean jouer_partieIA(Partie_IA p) throws FileNotFoundException {
        Date_base date_base = new Date_base();
        String save = "";
        boolean rep = false;
        boolean interupteur = true;
        while ((!Deroulement_partie.check_win(p.getPartie())) && (!Deroulement_partie.egalite(p.getPartie()))) {
            Affichage.affichage(p.getPartie().getTab_de_jeu());
            save = Deroulement_partie.tour(p);
            System.out.println();
            System.out.println(String_color.ANSI_RED + "VOULEZ VOUS CONTINUER LA PARTIE ?" + String_color.ANSI_RESET);
            rep = New_partie.isBoolean();
            if (!rep) {
                interupteur = false;
                break;
            } else {
                rep = true;
                interupteur = true;
            }
        }

        if (Deroulement_partie.egalite(p.getPartie())) {
            System.out.println("Egalité");
            System.out.println();
        } else {
            if (p.getPartie().getNb_coups() % 2 == 0) {
                System.out.println(p.getPartie().getJoueur_2() + " a gagné!");
            } else {
                System.out.println(p.getPartie().getJoueur_1() + " a gagné!");
            }
            System.out.println();
        }

        if(interupteur) {
            System.out.println(String_color.ANSI_BLUE + "Partie mise en pause. Sauvegarde effectuée" + String_color.ANSI_RESET);
        }else{
            Charger_partie.injecter_save(save,date_base.getFichier());
            System.out.println(String_color.ANSI_BLUE + "Partie mise en pause. Sauvegarde effectuée" + String_color.ANSI_RESET);
        }
        return false;
    }

    public static Boolean jouer_ancienne_partie(Partie_humain prth) throws FileNotFoundException {
        Date_base date_base = new Date_base();
        String save = "";
        boolean rep = false;
        boolean interupteur = true;
        while ((!Deroulement_partie.check_win(prth)) && (!Deroulement_partie.egalite(prth))) {
            save = Deroulement_partie.tour(prth);
            Affichage.affichage(prth.getTab_de_jeu());

            System.out.println();
            System.out.println("Etat de la sauvegarde :" + Deroulement_partie.save(prth));
            System.out.println();
            if(!Deroulement_partie.egalite(prth)){
                System.out.println(String_color.ANSI_RED + "VOULEZ VOUS CONTINUER LA PARTIE ?" + String_color.ANSI_RESET);
                rep = New_partie.isBoolean();
                if (!rep) {
                    interupteur = false;
                    break;
                }
            }else{
                rep = true;
                interupteur = true;
            }
        }
        if(!Deroulement_partie.check_win(prth)){
            System.out.println("Egalité");

        } else {
            if(prth.getNb_coups()%2 == 0){
                System.out.println(prth.getJoueur_2() + " a gagné!");
            } else {
                System.out.println(prth.getJoueur_1() + " a gagné!");
            }
        }
        if(interupteur) {

            int save_num2 = save_num_of(save);

            Charger_partie.delete_save_number(save_num2, date_base.getFichier());
            System.out.println(String_color.ANSI_BLUE + "JEUX TERMINER ! -> SAVE SURPPRIMER" + String_color.ANSI_RESET);
        }else{

            int save_num2 = save_num_of(save);
            Charger_partie.replace_save_number_(save,save_num2, date_base.getFichier());
            System.out.println(String_color.ANSI_BLUE + "JEUX MIS EN PAUSE ! -> SAVE REMPLACER" + String_color.ANSI_RESET);
        }
        return false;
    }


    public static Boolean jouer_ancienne_partie(Partie_IA p) throws FileNotFoundException {
        Date_base date_base = new Date_base();
        String save = "";
        boolean rep = false;
        boolean interupteur = true;
        while ((!Deroulement_partie.check_win(p.getPartie())) && (!Deroulement_partie.egalite(p.getPartie())) && interupteur) {
            save = Deroulement_partie.tour(p);
            Affichage.affichage(p.getPartie().getTab_de_jeu());

            System.out.println();
            System.out.println("Etat de la sauvegarde :" + Deroulement_partie.save(p));
            System.out.println();
            System.out.println(String_color.ANSI_RED + "VOULEZ VOUS CONTINUER LA PARTIE ?" + String_color.ANSI_RESET);
            rep = New_partie.isBoolean();
            if (Deroulement_partie.egalite(p.getPartie())) {
                System.out.println("Egalité");
                int save_num2 = save_num_of(save);
                Charger_partie.delete_save_number(save_num2, date_base.getFichier());
                System.out.println(String_color.ANSI_BLUE + "Partie terminée ! Sauvegarde supprimée." + String_color.ANSI_RESET);
            } else if (Deroulement_partie.check_win(p.getPartie())){
                if (p.getPartie().getNb_coups() % 2 == 0) {
                    System.out.println(p.getPartie().getJoueur_2() + " a gagné!");
                } else {
                    System.out.println(p.getPartie().getJoueur_1() + " a gagné!");
                }
                int save_num2 = save_num_of(save);
                Charger_partie.delete_save_number(save_num2, date_base.getFichier());
                System.out.println(String_color.ANSI_BLUE + "Partie terminée ! Sauvegarde supprimée." + String_color.ANSI_RESET);
            }

            if (!rep) {
                interupteur = false;
                int save_num2 = save_num_of(save);
                Charger_partie.replace_save_number_(save, save_num2, date_base.getFichier());
                System.out.println(String_color.ANSI_BLUE + "Partie mise en pause ! Sauvegarde remplacée" + String_color.ANSI_RESET);
            }
        }
        return false;
    }

    /**
     * permet de savoir le numero de la partie de la partie en cours sans avoir a le demander a l'utilisateur
     * @param save sauvgarde d'une parti en string
     * @return le numero de la partie
     */
    public static int save_num_of(String save){
        int decalage = Charger_partie.nb_av_arobase(save);
        System.out.println("On doit declaer de :" + decalage);
        String save_num = save.substring(0, decalage + 1);
        System.out.println("le string de save num est :" + save_num);
        int save_num2 = Integer.parseInt(save_num);
        System.out.println("numero de  la save /" + save_num2);
        return save_num2;
    }

}
