import java.io.File;

/**
 * Classe qui permet d'avoir un chemin vers le fichier txt
 */

public class Data_base {

    private  File  fichier = new File("leFichier.txt");

    /** Fonction permettant d'accéder au fichier de sauvegarde
     *
     * @return le fichier de sauvegarde
     */
    public  File getFichier() {
        return fichier;
    }
}





