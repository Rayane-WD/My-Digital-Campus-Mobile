import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class getStudentValuesFromFile {
  public static void main(String[] args) {
    
    try {

      //Ouvre le fichier
      Scanner myScanner = new Scanner(new File("file_esme_database.txt"), "UTF-8");

      //Parcours lignes par lignes le fichier
      while (myScanner.hasNextLine()) {
        String data = myScanner.nextLine();
        String[] line = data.split("\\*");
        String e_nom = line[0];
        String e_prenom = line[1];
        String e_classe = line[2];
        String e_mail = line[3];
        String e_mdp = line[4];
        String n_an1 = line[5];
        String n_an2 = line[6];
        String n_aut1 = line[7];
        String n_aut2 = line[8];
        String n_bdd1 = line[9];
        String n_bdd2 = line[10];
        String n_dd1 = line[11];
        String n_dd2 = line[12];
        String n_lm1 = line[13];
        String n_lm2 = line[14];
        String n_me1 = line[15];
        String n_me2 = line[16];
        String n_mi1 = line[17];
        String n_mi2 = line[18];
        String n_op1 = line[19];
        String n_op2 = line[20];
        String n_pr1 = line[21];
        String n_pr2 = line[22];
        String n_pe1 = line[23];
        String n_pw1 = line[24];
        String n_pw2 = line[25];
        String n_tss1 = line[26];
        String n_tss2 = line[27];
        String n_tn1 = line[28];
        String n_tn2 = line[29];

        //Tests
        System.out.println(e_nom);
        System.out.println(e_mail);
        System.out.println(e_mdp);
        System.out.println(n_an1);
        System.out.println(n_tss1);
        System.out.println(n_tn2);


        //BDD
        String[] liste_matieres = { "Anglais", "Automatique Lineaire",
        "Base de Donnees", "Developpement durable", "Lean Management",
        "Machines Electriques", "Microsystemes", "Optimisation", "Panorama des Reseaux",
        "Parcours Excellence", "Programmation Web", "Traitement Statistique du Signal",
        "Transmissions Numeriques"};



//Collection : classes nclasse matieres nmatieres
Map<String, Object> fields1Lun = new HashMap<>();
/**Lundi**/
//1B
fields1Lun = new HashMap<>();
fields1Lun.put("emplacement", "1");
fields1Lun.put("enseignant", "Maizy");
fields1Lun.put("jour", "lundi");
fields1Lun.put("salle", "M12");
fields1Lun.put("type", "TP");
db.collection("classes").document("1B")
        .collection("matieres").document("Traitement Statistique Du Signal").set(fields1Lun, SetOptions.merge());
fields1Lun = new HashMap<>();
fields1Lun.put("emplacement", "2");
fields1Lun.put("enseignant", "Maizy");
fields1Lun.put("jour", "lundi");
fields1Lun.put("salle", "M12");
fields1Lun.put("type", "TP");
db.collection("classes").document("1B")
        .collection("matieres").document("Traitement Statistique Du Signal").set(fields1Lun, SetOptions.merge());

//1C
fields1Lun.put("emplacement", "1");
fields1Lun.put("enseignant", "Wong");
fields1Lun.put("jour", "lundi");
fields1Lun.put("salle", "B11");
fields1Lun.put("type", "TD");
db.collection("classes").document("1C")
        .collection("matieres").document("Developpement durable").set(fields1Lun, SetOptions.merge());

fields1Lun = new HashMap<>();
fields1Lun.put("emplacement", "2");
fields1Lun.put("enseignant", "Gagne");
fields1Lun.put("jour", "lundi");
fields1Lun.put("salle", "B06A");
fields1Lun.put("type", "TD");
db.collection("classes").document("1D")
        .collection("matieres").document("Lean Management").set(fields1Lun, SetOptions.merge());

//1D
fields1Lun = new HashMap<>();
fields1Lun.put("emplacement", "1");
fields1Lun.put("enseignant", "Gagne");
fields1Lun.put("jour", "lundi");
fields1Lun.put("salle", "B06A");
fields1Lun.put("type", "TD");
db.collection("classes").document("1B")
        .collection("matieres").document("Lean Management").set(fields1Lun, SetOptions.merge());

fields1Lun = new HashMap<>();
fields1Lun.put("emplacement", "2");
fields1Lun.put("enseignant", "Wong");
fields1Lun.put("jour", "lundi");
fields1Lun.put("salle", "B11");
fields1Lun.put("type", "TD");
db.collection("classes").document("1B")
        .collection("matieres").document("Developpement durable").set(fields1Lun, SetOptions.merge());

//1A 1B 1C 1D
fields1Lun = new HashMap<>();
fields1Lun.put("emplacement", "4");
fields1Lun.put("enseignant", "Maizy");
fields1Lun.put("jour", "lundi");
fields1Lun.put("salle", "M0B");
fields1Lun.put("type", "TG");
db.collection("classes").document("1A")
        .collection("matieres").document("Traitement Statistique Du Signal").set(fields1Lun, SetOptions.merge());
db.collection("classes").document("1B")
        .collection("matieres").document("Traitement Statistique Du Signal").set(fields1Lun, SetOptions.merge());
db.collection("classes").document("1C")
        .collection("matieres").document("Traitement Statistique Du Signal").set(fields1Lun, SetOptions.merge());
db.collection("classes").document("1D")
        .collection("matieres").document("Traitement Statistique Du Signal").set(fields1Lun, SetOptions.merge());

fields1Lun = new HashMap<>();
fields1Lun.put("emplacement", "5");
fields1Lun.put("enseignant", "Ossonce");
fields1Lun.put("jour", "lundi");
fields1Lun.put("salle", "M0B");
fields1Lun.put("type", "TG");
db.collection("classes").document("1A")
        .collection("matieres").document("Transmissions Numeriques").set(fields1Lun, SetOptions.merge());
db.collection("classes").document("1B")
        .collection("matieres").document("Transmissions Numeriques").set(fields1Lun, SetOptions.merge());
db.collection("classes").document("1C")
        .collection("matieres").document("Transmissions Numeriques").set(fields1Lun, SetOptions.merge());
db.collection("classes").document("1D")
        .collection("matieres").document("Transmissions Numeriques").set(fields1Lun, SetOptions.merge());



//1E
fields1Lun = new HashMap<>();
fields1Lun.put("emplacement", "1");
fields1Lun.put("enseignant", "Heladi");
fields1Lun.put("jour", "lundi");
fields1Lun.put("salle", "B12A");
fields1Lun.put("type", "TD");
db.collection("classes").document("1E")
        .collection("matieres").document("Optimisation").set(fields1Lun, SetOptions.merge());

fields1Lun = new HashMap<>();
fields1Lun.put("emplacement", "2");
fields1Lun.put("enseignant", "Kamoun");
fields1Lun.put("jour", "lundi");
fields1Lun.put("salle", "B13");
fields1Lun.put("type", "TD");
db.collection("classes").document("1E")
        .collection("matieres").document("Base de Donnee").set(fields1Lun, SetOptions.merge());

//1F
fields1Lun = new HashMap<>();
fields1Lun.put("emplacement", "4");
fields1Lun.put("enseignant", "Ledoux");
fields1Lun.put("jour", "lundi");
fields1Lun.put("salle", "M07A");
fields1Lun.put("type", "TP");
db.collection("classes").document("1F")
        .collection("matieres").document("Machines electriques").set(fields1Lun, SetOptions.merge());
fields1Lun = new HashMap<>();
fields1Lun.put("emplacement", "5");
fields1Lun.put("enseignant", "Ledoux");
fields1Lun.put("jour", "lundi");
fields1Lun.put("salle", "M07A");
fields1Lun.put("type", "TP");
db.collection("classes").document("1F")
        .collection("matieres").document("Machines electriques").set(fields1Lun, SetOptions.merge());

//1F
fields1Lun = new HashMap<>();
fields1Lun.put("emplacement", "2");
fields1Lun.put("enseignant", "Heladi");
fields1Lun.put("jour", "lundi");
fields1Lun.put("salle", "B12A");
fields1Lun.put("type", "TD");
db.collection("classes").document("1F")
        .collection("matieres").document("Optimisation").set(fields1Lun, SetOptions.merge());

fields1Lun = new HashMap<>();
fields1Lun.put("emplacement", "1");
fields1Lun.put("enseignant", "Kamoun");
fields1Lun.put("jour", "lundi");
fields1Lun.put("salle", "B13");
fields1Lun.put("type", "TD");
db.collection("classes").document("1F")
        .collection("matieres").document("Base de Donnee").set(fields1Lun, SetOptions.merge());


//1G
fields1Lun = new HashMap<>();
fields1Lun.put("emplacement", "1");
fields1Lun.put("enseignant", "Jouida");
fields1Lun.put("jour", "lundi");
fields1Lun.put("salle", "B14");
fields1Lun.put("type", "TD");
db.collection("classes").document("1G")
        .collection("matieres").document("Programmation Web").set(fields1Lun, SetOptions.merge());

//1H
fields1Lun = new HashMap<>();
fields1Lun.put("emplacement", "2");
fields1Lun.put("enseignant", "Jouida");
fields1Lun.put("jour", "lundi");
fields1Lun.put("salle", "B14");
fields1Lun.put("type", "TD");
db.collection("classes").document("1H")
        .collection("matieres").document("Programmation Web").set(fields1Lun, SetOptions.merge());





/**Mardi**/
//1A 1B 1C 1D
fields1Lun = new HashMap<>();
fields1Lun.put("emplacement", "1");
fields1Lun.put("enseignant", "Kraiem");
fields1Lun.put("jour", "mardi");
fields1Lun.put("salle", "Teams");
fields1Lun.put("type", "TG");
db.collection("classes").document("1A")
        .collection("matieres").document("Automatique Lineaire").set(fields1Lun, SetOptions.merge());
db.collection("classes").document("1B")
        .collection("matieres").document("Automatique Lineaire").set(fields1Lun, SetOptions.merge());
db.collection("classes").document("1C")
        .collection("matieres").document("Automatique Lineaire").set(fields1Lun, SetOptions.merge());
db.collection("classes").document("1D")
        .collection("matieres").document("Automatique Lineaire").set(fields1Lun, SetOptions.merge());

fields1Lun = new HashMap<>();
fields1Lun.put("emplacement", "2");
fields1Lun.put("enseignant", "Dandoush");
fields1Lun.put("jour", "mardi");
fields1Lun.put("salle", "Teams");
fields1Lun.put("type", "TG");
db.collection("classes").document("1A")
        .collection("matieres").document("Panorama des Reseaux").set(fields1Lun, SetOptions.merge());
db.collection("classes").document("1B")
        .collection("matieres").document("Panorama des Reseaux").set(fields1Lun, SetOptions.merge());
db.collection("classes").document("1C")
        .collection("matieres").document("Panorama des Reseaux").set(fields1Lun, SetOptions.merge());
db.collection("classes").document("1D")
        .collection("matieres").document("Panorama des Reseaux").set(fields1Lun, SetOptions.merge());



//1A
fields1Lun = new HashMap<>();
fields1Lun.put("emplacement", "5");
fields1Lun.put("enseignant", "Abdallah");
fields1Lun.put("jour", "mardi");
fields1Lun.put("salle", "B13");
fields1Lun.put("type", "TD");
db.collection("classes").document("1A")
        .collection("matieres").document("Base de Donnee").set(fields1Lun, SetOptions.merge());


//1B
fields1Lun = new HashMap<>();
fields1Lun.put("emplacement", "4");
fields1Lun.put("enseignant", "Abdallah");
fields1Lun.put("jour", "mardi");
fields1Lun.put("salle", "B13");
fields1Lun.put("type", "TD");
db.collection("classes").document("1A")
        .collection("matieres").document("Base de Donnee").set(fields1Lun, SetOptions.merge());



//1E
fields1Lun = new HashMap<>();
fields1Lun.put("emplacement", "1");
fields1Lun.put("enseignant", "Adamou");
fields1Lun.put("jour", "mardi");
fields1Lun.put("salle", "B14");
fields1Lun.put("type", "TD");
db.collection("classes").document("1E")
        .collection("matieres").document("Programmation Web").set(fields1Lun, SetOptions.merge());
//1F
fields1Lun = new HashMap<>();
fields1Lun.put("emplacement", "2");
fields1Lun.put("enseignant", "Adamou");
fields1Lun.put("jour", "mardi");
fields1Lun.put("salle", "B14");
fields1Lun.put("type", "TD");
db.collection("classes").document("1F")
        .collection("matieres").document("Programmation Web").set(fields1Lun, SetOptions.merge());


//1E 1F 1G 1H
fields1Lun = new HashMap<>();
fields1Lun.put("emplacement", "4");
fields1Lun.put("enseignant", "Palessonga");
fields1Lun.put("jour", "mardi");
fields1Lun.put("salle", "M0B");
fields1Lun.put("type", "TG");
db.collection("classes").document("1E")
        .collection("matieres").document("Microsystemes").set(fields1Lun, SetOptions.merge());
db.collection("classes").document("1F")
        .collection("matieres").document("Microsystemes").set(fields1Lun, SetOptions.merge());
db.collection("classes").document("1G")
        .collection("matieres").document("Microsystemes").set(fields1Lun, SetOptions.merge());
db.collection("classes").document("1H")
        .collection("matieres").document("Microsystemes").set(fields1Lun, SetOptions.merge());

fields1Lun = new HashMap<>();
fields1Lun.put("emplacement", "5");
fields1Lun.put("enseignant", "Ledoux");
fields1Lun.put("jour", "mardi");
fields1Lun.put("salle", "M0B");
fields1Lun.put("type", "TG");
db.collection("classes").document("1E")
        .collection("matieres").document("Panorama des Reseaux").set(fields1Lun, SetOptions.merge());
db.collection("classes").document("1F")
        .collection("matieres").document("Panorama des Reseaux").set(fields1Lun, SetOptions.merge());
db.collection("classes").document("1G")
        .collection("matieres").document("Panorama des Reseaux").set(fields1Lun, SetOptions.merge());
db.collection("classes").document("1H")
        .collection("matieres").document("Panorama des Reseaux").set(fields1Lun, SetOptions.merge());



/**Mercredi**/
//1A
fields1Lun = new HashMap<>();
fields1Lun.put("emplacement", "1");
fields1Lun.put("enseignant", "Rosier");
fields1Lun.put("jour", "mercredi");
fields1Lun.put("salle", "B12A");
fields1Lun.put("type", "TD");
db.collection("classes").document("1A")
        .collection("matieres").document("Optimisation").set(fields1Lun, SetOptions.merge());
//1B
fields1Lun = new HashMap<>();
fields1Lun.put("emplacement", "2");
fields1Lun.put("enseignant", "Rosier");
fields1Lun.put("jour", "mercredi");
fields1Lun.put("salle", "B12A");
fields1Lun.put("type", "TD");
db.collection("classes").document("1B")
        .collection("matieres").document("Optimisation").set(fields1Lun, SetOptions.merge());


//1C
fields1Lun = new HashMap<>();
fields1Lun.put("emplacement", "1");
fields1Lun.put("enseignant", "Benabou");
fields1Lun.put("jour", "mercredi");
fields1Lun.put("salle", "B14");
fields1Lun.put("type", "TD");
db.collection("classes").document("1C")
        .collection("matieres").document("Programmation Web").set(fields1Lun, SetOptions.merge());
//1D
fields1Lun = new HashMap<>();
fields1Lun.put("emplacement", "2");
fields1Lun.put("enseignant", "Benabou");
fields1Lun.put("jour", "mercredi");
fields1Lun.put("salle", "B14");
fields1Lun.put("type", "TD");
db.collection("classes").document("1D")
        .collection("matieres").document("Programmation Web").set(fields1Lun, SetOptions.merge());

//1C
fields1Lun = new HashMap<>();
fields1Lun.put("emplacement", "2");
fields1Lun.put("enseignant", "Munn/Beauroy-Eustache");
fields1Lun.put("jour", "mercredi");
fields1Lun.put("salle", "B06A/B06B");
fields1Lun.put("type", "TD");
db.collection("classes").document("1C")
        .collection("matieres").document("Anglais").set(fields1Lun, SetOptions.merge());
//1D
fields1Lun = new HashMap<>();
fields1Lun.put("emplacement", "1");
fields1Lun.put("enseignant", "Munn/Beauroy-Eustache");
fields1Lun.put("jour", "mercredi");
fields1Lun.put("salle", "B06A");
fields1Lun.put("type", "TD");
db.collection("classes").document("1D")
        .collection("matieres").document("Anglais").set(fields1Lun, SetOptions.merge());



//1E 1F 1G 1H
fields1Lun = new HashMap<>();
fields1Lun.put("emplacement", "4");
fields1Lun.put("enseignant", "Kraiem");
fields1Lun.put("jour", "mercredi");
fields1Lun.put("salle", "Teams");
fields1Lun.put("type", "TG");
db.collection("classes").document("1E")
        .collection("matieres").document("Automatique Lineaire").set(fields1Lun, SetOptions.merge());
db.collection("classes").document("1F")
        .collection("matieres").document("Automatique Lineaire").set(fields1Lun, SetOptions.merge());
db.collection("classes").document("1G")
        .collection("matieres").document("Automatique Lineaire").set(fields1Lun, SetOptions.merge());
db.collection("classes").document("1H")
        .collection("matieres").document("Automatique Lineaire").set(fields1Lun, SetOptions.merge());


//1G
fields1Lun = new HashMap<>();
fields1Lun.put("emplacement", "1");
fields1Lun.put("enseignant", "Richet");
fields1Lun.put("jour", "mercredi");
fields1Lun.put("salle", "B11");
fields1Lun.put("type", "TD");
db.collection("classes").document("1G")
        .collection("matieres").document("Developpement Durable").set(fields1Lun, SetOptions.merge());

fields1Lun = new HashMap<>();
fields1Lun.put("emplacement", "2");
fields1Lun.put("enseignant", "Gagne");
fields1Lun.put("jour", "mercredi");
fields1Lun.put("salle", "B02B");
fields1Lun.put("type", "TD");
db.collection("classes").document("1G")
        .collection("matieres").document("Lean Management").set(fields1Lun, SetOptions.merge());

//1H
fields1Lun = new HashMap<>();
fields1Lun.put("emplacement", "2");
fields1Lun.put("enseignant", "Richet");
fields1Lun.put("jour", "mercredi");
fields1Lun.put("salle", "B11");
fields1Lun.put("type", "TD");
db.collection("classes").document("1H")
        .collection("matieres").document("Developpement Durable").set(fields1Lun, SetOptions.merge());
fields1Lun = new HashMap<>();
fields1Lun.put("emplacement", "1");
fields1Lun.put("enseignant", "Gagne");
fields1Lun.put("jour", "mercredi");
fields1Lun.put("salle", "B02B");
fields1Lun.put("type", "TD");
db.collection("classes").document("1H")
        .collection("matieres").document("Lean Management").set(fields1Lun, SetOptions.merge());








/**Jeudi**/
//1A 1B 1C 1D 1E 1F 1G 1H
fields1Lun = new HashMap<>();
fields1Lun.put("emplacement", "1");
fields1Lun.put("enseignant", " ~ ");
fields1Lun.put("jour", "jeudi");
fields1Lun.put("salle", " ~ ");
fields1Lun.put("type", "TP");
db.collection("classes").document("1A")
        .collection("matieres").document("Parcours Excellence").set(fields1Lun, SetOptions.merge());
db.collection("classes").document("1B")
        .collection("matieres").document("Parcours Excellence").set(fields1Lun, SetOptions.merge());
db.collection("classes").document("1C")
        .collection("matieres").document("Parcours Excellence").set(fields1Lun, SetOptions.merge());
db.collection("classes").document("1D")
        .collection("matieres").document("Parcours Excellence").set(fields1Lun, SetOptions.merge());
db.collection("classes").document("1E")
        .collection("matieres").document("Parcours Excellence").set(fields1Lun, SetOptions.merge());
db.collection("classes").document("1F")
        .collection("matieres").document("Parcours Excellence").set(fields1Lun, SetOptions.merge());
db.collection("classes").document("1G")
        .collection("matieres").document("Parcours Excellence").set(fields1Lun, SetOptions.merge());
db.collection("classes").document("1H")
        .collection("matieres").document("Parcours Excellence").set(fields1Lun, SetOptions.merge());


/**Vendredi**/
//1A
fields1Lun = new HashMap<>();
fields1Lun.put("emplacement", "1");
fields1Lun.put("enseignant", "Abdallah");
fields1Lun.put("jour", "vendredi");
fields1Lun.put("salle", "B14");
fields1Lun.put("type", "TD");
db.collection("classes").document("1A")
        .collection("matieres").document("Programmation Web").set(fields1Lun, SetOptions.merge());


//1B
fields1Lun = new HashMap<>();
fields1Lun.put("emplacement", "2");
fields1Lun.put("enseignant", "Abdallah");
fields1Lun.put("jour", "vendredi");
fields1Lun.put("salle", "B14");
fields1Lun.put("type", "TD");
db.collection("classes").document("1B")
        .collection("matieres").document("Programmation Web").set(fields1Lun, SetOptions.merge());


//1A
fields1Lun = new HashMap<>();
fields1Lun.put("emplacement", "2");
fields1Lun.put("enseignant", "Ghinai/Beauroy-Eustache");
fields1Lun.put("jour", "vendredi");
fields1Lun.put("salle", "B06A/B06B");
fields1Lun.put("type", "TD");
db.collection("classes").document("1A")
        .collection("matieres").document("Anglais").set(fields1Lun, SetOptions.merge());


//1B
fields1Lun = new HashMap<>();
fields1Lun.put("emplacement", "1");
fields1Lun.put("enseignant", "Abdallah");
fields1Lun.put("jour", "Ghinai/Beauroy-Eustache");
fields1Lun.put("salle", "B06A/B06B");
fields1Lun.put("type", "TD");
db.collection("classes").document("1B")
        .collection("matieres").document("Anglais").set(fields1Lun, SetOptions.merge());



//1A 1B 1C 1D
fields1Lun = new HashMap<>();
fields1Lun.put("emplacement", "4");
fields1Lun.put("enseignant", "Ledoux");
fields1Lun.put("jour", "vendredi");
fields1Lun.put("salle", "M0B");
fields1Lun.put("type", "TG");
db.collection("classes").document("1A")
        .collection("matieres").document("Machines Electriques").set(fields1Lun, SetOptions.merge());
db.collection("classes").document("1B")
        .collection("matieres").document("Machines Electriques").set(fields1Lun, SetOptions.merge());
db.collection("classes").document("1C")
        .collection("matieres").document("Machines Electriques").set(fields1Lun, SetOptions.merge());
db.collection("classes").document("1D")
        .collection("matieres").document("Machines Electriques").set(fields1Lun, SetOptions.merge());

fields1Lun = new HashMap<>();
fields1Lun.put("emplacement", "5");
fields1Lun.put("enseignant", "Palessonga");
fields1Lun.put("jour", "vendredi");
fields1Lun.put("salle", "M0B");
fields1Lun.put("type", "TG");
db.collection("classes").document("1A")
        .collection("matieres").document("Microsystemes").set(fields1Lun, SetOptions.merge());
db.collection("classes").document("1B")
        .collection("matieres").document("Microsystemes").set(fields1Lun, SetOptions.merge());
db.collection("classes").document("1C")
        .collection("matieres").document("Microsystemes").set(fields1Lun, SetOptions.merge());
db.collection("classes").document("1D")
        .collection("matieres").document("Microsystemes").set(fields1Lun, SetOptions.merge());


//1E 1F 1G 1H
fields1Lun = new HashMap<>();
fields1Lun.put("emplacement", "1");
fields1Lun.put("enseignant", "Maizy");
fields1Lun.put("jour", "vendredi");
fields1Lun.put("salle", "M0B");
fields1Lun.put("type", "TG");
db.collection("classes").document("1E")
        .collection("matieres").document("Traitement Statistique Du Signal").set(fields1Lun, SetOptions.merge());
db.collection("classes").document("1F")
        .collection("matieres").document("Traitement Statistique Du Signal").set(fields1Lun, SetOptions.merge());
db.collection("classes").document("1G")
        .collection("matieres").document("Traitement Statistique Du Signal").set(fields1Lun, SetOptions.merge());
db.collection("classes").document("1H")
        .collection("matieres").document("Traitement Statistique Du Signal").set(fields1Lun, SetOptions.merge());

fields1Lun = new HashMap<>();
fields1Lun.put("emplacement", "2");
fields1Lun.put("enseignant", "Ossonce");
fields1Lun.put("jour", "vendredi");
fields1Lun.put("salle", "M0B");
fields1Lun.put("type", "TG");
db.collection("classes").document("1E")
        .collection("matieres").document("Transmissions Numeriques").set(fields1Lun, SetOptions.merge());
db.collection("classes").document("1F")
        .collection("matieres").document("Transmissions Numeriques").set(fields1Lun, SetOptions.merge());
db.collection("classes").document("1G")
        .collection("matieres").document("Transmissions Numeriques").set(fields1Lun, SetOptions.merge());
db.collection("classes").document("1H")
        .collection("matieres").document("Transmissions Numeriques").set(fields1Lun, SetOptions.merge());

//1E
fields1Lun = new HashMap<>();
fields1Lun.put("emplacement", "4");
fields1Lun.put("enseignant", "Ossonce");
fields1Lun.put("jour", "vendredi");
fields1Lun.put("salle", "M12");
fields1Lun.put("type", "TP");
db.collection("classes").document("1E")
        .collection("matieres").document("Traitement Statistique").set(fields1Lun, SetOptions.merge());
fields1Lun = new HashMap<>();
fields1Lun.put("emplacement", "5");
fields1Lun.put("enseignant", "Ossonce");
fields1Lun.put("jour", "vendredi");
fields1Lun.put("salle", "M12");
fields1Lun.put("type", "TP");
db.collection("classes").document("1E")
        .collection("matieres").document("Traitement Statistique").set(fields1Lun, SetOptions.merge());



//1G
fields1Lun = new HashMap<>();
fields1Lun.put("emplacement", "4");
fields1Lun.put("enseignant", "Amour");
fields1Lun.put("jour", "vendredi");
fields1Lun.put("salle", "B12A");
fields1Lun.put("type", "TP");
db.collection("classes").document("1G")
        .collection("matieres").document("Panorama des Reseaux").set(fields1Lun, SetOptions.merge());
fields1Lun = new HashMap<>();
fields1Lun.put("emplacement", "5");
fields1Lun.put("enseignant", "Amour");
fields1Lun.put("jour", "vendredi");
fields1Lun.put("salle", "B12A");
fields1Lun.put("type", "TP");
db.collection("classes").document("1G")
        .collection("matieres").document("Panorama des Reseaux").set(fields1Lun, SetOptions.merge());

try {

    //Ouvre le fichier
    DataInputStream textFileStream = new DataInputStream(getAssets().open(String.format("file_esme_database.txt")));
    Scanner myScanner = new Scanner(textFileStream, "UTF-8");

    //Parcours lignes par lignes le fichier
    while (myScanner.hasNextLine()) {
        String data = myScanner.nextLine();
        String[] line = data.split("\\*");

        String e_nom = line[0];
        String e_prenom = line[1];
        String e_classe = line[2];
        String e_mail = line[3];
        String e_mdp = line[4];

        //Collection : eleves email notes nmatiere field
        int index_matiere=-1;
        boolean new_field_to_add=false;
        Map<String, Object> fields2 = new HashMap<>();
        for (int n=0; n<=line.length; n++){

            if (n==5) {
                fields2 = new HashMap<>();
                fields2.put("Examen", line[n]);
                fields2.put("NE", line[n + 1]);
                index_matiere+=1;
                new_field_to_add = true;
            }

            else if (n==7 || n==9 || n==15 || n==17 || n==21 || n==24 || n==26) {
                fields2 = new HashMap<>();
                fields2.put("Examen", line[n]);
                fields2.put("TP", line[n + 1]);
                index_matiere+=1;
                new_field_to_add = true;
            }
            else if (n==11 || n==13) {
                fields2 = new HashMap<>();
                fields2.put("NE", line[n]);
                fields2.put("Projet", line[n + 1]);
                index_matiere+=1;
                new_field_to_add = true;
            }
            else if (n==19 || n==28) {
                fields2 = new HashMap<>();
                fields2.put("DS", line[n]);
                fields2.put("Examen", line[n + 1]);
                index_matiere+=1;
                new_field_to_add = true;
            }
            else if (n==23) {
                fields2 = new HashMap<>();
                fields2.put("Projet", line[n]);
                index_matiere+=1;
                new_field_to_add = true;
            }
            if (new_field_to_add) {
                db.collection("eleves").document(e_mail)
                        .collection("notes").document(liste_matieres[index_matiere]).set(fields2, SetOptions.merge());
                new_field_to_add = false;
            }
        }

        //Collection : eleves email fields
        Map<String, Object> fields1 = new HashMap<>();
        fields1.put("classe", e_classe);
        fields1.put("mdp", e_mdp);
        fields1.put("nom", e_nom);
        fields1.put("prenom", e_prenom);
        db.collection("eleves").document(e_mail).set(fields1, SetOptions.merge());
        
    }

    myScanner.close();
}


catch (IOException e) {
    e.printStackTrace();
}
      }
      
      myScanner.close();
    } 
    
    catch (FileNotFoundException e) {
      System.out.println("y a eu erreur poto");
      e.printStackTrace();
    }
  }

}
