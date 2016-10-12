import java.sql.*;

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
		Statement stm = conn.createStatement();
		ResultSet res = stm.executeQuery("select type_an from lesanimaux");
		while(res.next()){
			String s = res.getString("type_an");
			System.out.println(s);
		}

	}

	private static void deplacerAnimal() throws SQLException {
		// A COMPLETER
	}	

	private static void ajouterMaladie() throws SQLException {
		// A COMPLETER
	}		

	private static void commit() throws SQLException {
		// A COMPLETER
	}				

	private static void rollback() throws SQLException {
		// A COMPLETER
	}		
	
	private static void getIsolation() throws SQLException {
		// A COMPLETER
	}

	private static void setIsolation() throws SQLException {
		// A COMPLETER
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