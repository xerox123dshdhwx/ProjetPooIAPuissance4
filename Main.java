import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Classe principale qui permet de jouer
 *
 * @author Rami et Tanina
 */

public class Main {
    /** Méthode qui lance le jeu
     *
     * @param args string
     * @throws FileNotFoundException exception de fichier non trouvée
     */
    public static void main(String[] args) throws FileNotFoundException {

        Scanner scanner = new Scanner(System.in);
        boolean val_b = true;
        Data_base dta = new Data_base();
        boolean interupteur_partie = true;

        while(interupteur_partie){
            System.out.println(String_color.ANSI_GREEN +"Bienvenue dans le Puissance 4 !" + String_color.ANSI_RESET);
            System.out.println(String_color.ANSI_YELLOW + "Voulez-vous jouer ? (True = oui, false = non) " + String_color.ANSI_RESET);
            val_b = New_partie.isBoolean();

            if(!val_b){
                //Si le mec veut pas jouer
                System.out.println(String_color.ANSI_BLUE + "Merci de votre visite revenez vite !" + String_color.ANSI_RESET);
                interupteur_partie = val_b;
            } else {
                System.out.println(String_color.ANSI_YELLOW + "Voulez-vous charger une ancienne partie ? (True = oui, false = non)" + String_color.ANSI_RESET);
                boolean rep1 = New_partie.isBoolean();

                //Le mec veut charger une ancienne partie
                if(rep1) {
                    clearConsole();
                    System.out.println(String_color.ANSI_YELLOW + "Entrez le numero de la sauvegarde de la partie a charger : " + String_color.ANSI_RESET);
                    int n = 0;

                    do {
                        System.out.println(String_color.ANSI_YELLOW +"Veuillez entrez un nombre supérieur à 0. "+ String_color.ANSI_RESET);
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
                            System.out.println("Niveau de l'IA : facile");

                        } else if (save_temp.charAt(save_temp.length()-1) == 'm') {
                            prt_ia = Charger_partie.relance_save_ia(save,1);
                            System.out.println("Niveau de l'IA : moyen");

                        } else {
                            prt_ia = Charger_partie.relance_save_ia(save,2);
                            System.out.println("Niveau de l'IA : dificle");
                        }
                        Affichage.affichage(prt_ia.getPartie().getTab_de_jeu());
                        jouer_ancienne_partie(prt_ia);

                    } else {
                        System.out.println("Partie entre humains relancée !");
                        Partie_humain prth = Charger_partie.relancer_save(save);
                        Affichage.affichage(prth.getTab_de_jeu());
                        if(jouer_ancienne_partie(prth)){
                        }else{
                            break;
                        }
                    }


                    //On lance une nouvelle partie
                }else{
                    System.out.println(String_color.ANSI_YELLOW + "Voulez-vous crée une partie contre une ia ? (True = oui, false = non)" +String_color.ANSI_RESET);
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
                        }else{
                            break;
                        }
                    }
                }
            }
        }
        if(val_b) {
            System.out.println(String_color.ANSI_YELLOW + "Merci de votre visite revenez vite !"
                    + String_color.ANSI_RESET);
        }else{
            System.out.print("");
        }

    }

    private  static void clearConsole()
    {
        for(int k = 0 ; k <50 ;k++){System.out.println();}
    }

    private static boolean jouer_partie(Partie_humain prth) throws FileNotFoundException {
        Data_base data_base = new Data_base();
        String save = "";
        boolean rep = false;
        boolean interupteur = true;
        Affichage.affichage(prth.getTab_de_jeu());
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
            Charger_partie.injecter_save(save, data_base.getFichier());
            System.out.println(String_color.ANSI_BLUE + "JEUX MIS EN PAUSE ! -> SAVE AJOUTER" + String_color.ANSI_RESET);
        }
        return false;
    }

    private static boolean jouer_partieIA(Partie_IA p) throws FileNotFoundException {
        Data_base data_base = new Data_base();
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
            System.out.println(String_color.ANSI_BLUE + "Partie terminée ! Sauvegarde effacée." + String_color.ANSI_RESET);
        }else{
            Charger_partie.injecter_save(save, data_base.getFichier());
            System.out.println(String_color.ANSI_BLUE + "Partie mise en pause. Sauvegarde effectuée" + String_color.ANSI_RESET);
        }
        return false;
    }

    private static Boolean jouer_ancienne_partie(Partie_humain prth) throws FileNotFoundException {
        Data_base data_base = new Data_base();
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

            Charger_partie.delete_save_number(save_num2, data_base.getFichier());
            System.out.println(String_color.ANSI_BLUE + "JEUX TERMINER ! -> SAVE SURPPRIMER" + String_color.ANSI_RESET);
        }else{

            int save_num2 = save_num_of(save);
            Charger_partie.replace_save_number_(save,save_num2, data_base.getFichier());
            System.out.println(String_color.ANSI_BLUE + "JEUX MIS EN PAUSE ! -> SAVE REMPLACER" + String_color.ANSI_RESET);
        }
        return false;
    }


    private static Boolean jouer_ancienne_partie(Partie_IA p) throws FileNotFoundException {
        Data_base data_base = new Data_base();
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
                Charger_partie.delete_save_number(save_num2, data_base.getFichier());
                System.out.println(String_color.ANSI_BLUE + "Partie terminée ! Sauvegarde supprimée." + String_color.ANSI_RESET);
            } else if (Deroulement_partie.check_win(p.getPartie())){
                if (p.getPartie().getNb_coups() % 2 == 0) {
                    System.out.println(p.getPartie().getJoueur_2() + " a gagné!");
                } else {
                    System.out.println(p.getPartie().getJoueur_1() + " a gagné!");
                }
                int save_num2 = save_num_of(save);
                Charger_partie.delete_save_number(save_num2, data_base.getFichier());
                System.out.println(String_color.ANSI_BLUE + "Partie terminée ! Sauvegarde supprimée." + String_color.ANSI_RESET);
            }

            if (!rep) {
                interupteur = false;
                int save_num2 = save_num_of(save);
                Charger_partie.replace_save_number_(save, save_num2, data_base.getFichier());
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
