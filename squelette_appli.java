import java.sql.*;
import java.io.*;

public class squelette_appli {
	
	static final String CONN_URL = "jdbc:oracle:thin:@im2ag-oracle.e.ujf-grenoble.fr:1521:im2ag";
	
	static final String USER = "nguyenmt";
	static final String PASSWD = "Cyi7ggmB";

	static Connection conn = null;


		
	private static void menu() {
		System.out.println("*** Choisir une action a effectuer : ***");
		System.out.println("0 : Quitter");
		System.out.println("1 : Afficher la liste des animaux");
		System.out.println("2 : Deplacer un animal de cage");
		System.out.println("3 : Ajouter une maladie");
	}

	private static void listeAnimaux() throws SQLException {
		// A COMPLETER
    try{
      BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
      System.out.println("Entrez le numéro de la cage des animaux à lister");
      String s = buff.readLine();
      int no = Integer.parseInt(s);
  		Statement stm = conn.createStatement();
  		ResultSet res = stm.executeQuery("select * from lesanimaux where nocage="+no);
  		while(res.next()){
        String noCage = res.getString("noCage");
  			String nomA = res.getString("nomA");
        String sexe = res.getString("sexe");
        String type_an = res.getString("type_an");
  			System.out.println(noCage+ "  "+ nomA +"  " + sexe +"  "+ type_an );
      }
    }catch(IOException e){
      System.err.println(e);
    }
     
	}

	private static void deplacerAnimal() throws SQLException {
		// A COMPLETER
   Statement stm = conn.createStatement();
   ResultSet res = stm.executeQuery("select fonction from LesCages");
  while(res.next()){
      String s = res.getString("fonction");
      System.out.println(s);
  }
   //ResultSet res = stm.executeQuery("update lesanimaux set fonction_cage ='' where nomA ='' ");
	}	

	private static void ajouterMaladie() throws SQLException {
		// A COMPLETER
    String n,s,t,f;
      try{
        BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Entrez le nom de l'animal");
        n = buff.readLine();
        System.out.println("Entrez le sexe de l'animal");
        s = buff.readLine();
        System.out.println("Entrez le type de l'animal");
        t = buff.readLine();
        System.out.println("Entrez le fonction de cage de l'animal");
        f = buff.readLine();

        /*Chercher les cages disponible*/
        Statement stm = conn.createStatement();
        ResultSet res = stm.executeQuery("select noCage from lesanimaux where fonction_cage="+f);

        /*Choisir la cage qui contient moins d'animaux*/
        System.out.println("Liste de cages possible");
        while(res.next()){
          String ss = res.getString("noCage");
          System.out.println(ss);
        }
    
      }catch(IOException e){
            System.err.println(e);
          }
	}		

	private static void commit() throws SQLException {
		// A COMPLETER
    conn.commit();
	}				

	private static void rollback() throws SQLException {
		// A COMPLETER
    conn.rollback();
	}		
	
	private static void getIsolation() throws SQLException {
		// A COMPLETER
    conn.getTransactionIsolation();
	}

	private static void setIsolation() throws SQLException {
		// A COMPLETER
    conn.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
	}	
	
    public static void main(String args[]) {

        try {
        
        int action;
        boolean exit = false;

  	    // Enregistrement du driver Oracle
  	    System.out.print("Loading Oracle driver... "); 
  	    // A COMPLETER
  	    DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
  	    System.out.println("loaded");
  	    
  	    // Etablissement de la connection
  	    System.out.print("Connecting to the database... "); 
  	    // A COMPLETER - fini
  	    conn = DriverManager.getConnection(CONN_URL, USER, PASSWD);
  	    System.out.println("connected");
  	    
  	    // Desactivation de l'autocommit
  	    // A COMPLETER
  	    conn.setAutoCommit(false);
  	    System.out.println("Autocommit disabled");

  	    while(!exit) {
  	    	menu();
  	    	action = LectureClavier.lireEntier("votre choix ?");
  	    	switch(action) {
  	    		case 0 : exit = true; break;
  	    		case 1 : listeAnimaux(); break;
  	    		case 2 : deplacerAnimal(); break;
  	    		case 3 : ajouterMaladie(); break;
  	    		default : System.out.println("=> choix incorrect"); menu();
  	    	}
  	    } 	    

  	    // Liberation des ressources et fermeture de la connexion...
		// A COMPLETER
        conn.close();
  	    
  	    System.out.println("au revoir");
  	    
  	    // traitement d'exception
          } catch (SQLException e) {
              System.err.println("failed");
              System.out.println("Affichage de la pile d'erreur");
  	          e.printStackTrace(System.err);
              System.out.println("Affichage du message d'erreur");
              System.out.println(e.getMessage());
              System.out.println("Affichage du code d'erreur");
  	          System.out.println(e.getErrorCode());	    

          } 
	
        }
}