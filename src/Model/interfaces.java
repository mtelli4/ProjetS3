/*package Model;


import java.sql.*;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class interfaces {

    public static Connection openConnection() {
        Connection connexion = null;

        try {
            // Chargement du pilote JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connexion à la base de données MySQL (remplacez les paramètres par les vôtres)
            String url = "jdbc:mysql://192.70.36.54/saes3-aratovo";
            String user = "saes3-aratovo";
            String password = "Projet_rma_s3";
            connexion = DriverManager.getConnection(url, user, password);




        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();

        }
        return connexion;
    }

    public static void closeConnection(Connection c) {
        try {
            c.close();
        }
        catch ( SQLException e) {
            e.printStackTrace();

        }
    }

    public static String recupNom() {   //faire une pop-up
        String nm;
        Scanner sc=new Scanner(System.in);
        System.out.print("Saisisez votre nom :");
        nm=sc.nextLine();
        sc.close();
        return nm;
    }


    public static pizzaiolo donnepizzaiolo(Connection c,ArrayList<Commande>cd,String nm){
        pizzaiolo pso=new pizzaiolo(1,null,null);

        try {
            Statement state=c.createStatement();
            ResultSet rs=state.executeQuery("SELECT * FROM PIZZAIOLO WHERE nom_Pzlo='"+nm+"'");

            while (rs.next()) {


                int id=rs.getInt(1);
                String nom=rs.getString(2);
                int num=rs.getInt(3);

                Commande ec=cd.get(num-1);


                pso=new pizzaiolo(id,nom,ec);



            }

        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return pso;
    }




    public static ArrayList<Commande> donneCommande(Connection c,ArrayList<Pizza> piz){
        ArrayList<Commande> cm=new ArrayList<Commande>();

        try {
            Statement state=c.createStatement();
            ResultSet rs=state.executeQuery("SELECT * FROM COMMANDE");
            Connection u=openConnection();
            while (rs.next()) {
                ArrayList<Pizza> ps=new ArrayList<Pizza>();
                Statement states=u.createStatement();

                int id=rs.getInt(1);
                String mode=rs.getString(3);
                float prix=rs.getFloat(2);


                ResultSet resu=states.executeQuery("SELECT num_Piz from contient  where num_Cmd="+id);
                while (resu.next()) {
                    int idss=resu.getInt(1);
                    ps.add(piz.get(idss-1));

                }
                Commande cd=new Commande(id,prix,mode,ps);
                cm.add(cd);

            }
            closeConnection(u);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return cm;
    }


    public static ArrayList<Pizza> donnePizza(Connection c,ArrayList<Stock> stc){
        ArrayList<Pizza> pz=new ArrayList<Pizza>();

        try {
            Statement state=c.createStatement();
            ResultSet rs=state.executeQuery("SELECT * FROM PIZZA;");
            Connection u=openConnection();
            while (rs.next()) {
                ArrayList<Ingredient> igs=new ArrayList<Ingredient>();

                Statement states=u.createStatement();

                int id=rs.getInt(1);
                String nom=rs.getString(2);
                double prix=rs.getFloat(3);
                int tempsFab=rs.getInt(4);
                String type=rs.getString(5);
                boolean pret=rs.getBoolean(6);

                ResultSet resu=states.executeQuery("SELECT i.nom_Ing,cd.quantite from INGREDIENT i join contient_des cd on i.id_Ing=cd.id_Ing where cd.num_Piz="+id);
                while (resu.next()) {
                    String noms=resu.getString(1);
                    int quanti=resu.getInt(2);
                    Stock sc=stc.get(id-1);
                    Ingredient ig=new Ingredient(quanti,noms,sc);
                    igs.add(ig);
                }
                Pizza piz=new Pizza(id,nom,prix,tempsFab,type,pret,igs);
                pz.add(piz);


            }
            closeConnection(u);
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return pz;
    }



    public static ArrayList<Stock> donneStock(Connection c){

        ArrayList<Stock> pz=new ArrayList<Stock>();
        try {
            Statement state=c.createStatement();
            ResultSet rs=state.executeQuery("SELECT * FROM STOCK");

            while (rs.next()) {

                int quantite=rs.getInt(2);
                Stock stc=new Stock(quantite);
                pz.add(stc);
            }

        }
        catch(SQLException e) {
            e.printStackTrace();
        }
        return pz;
    }

    public static boolean verifieNom(String n,Connection c) {
        boolean verif=false;
        try {
            Statement state=c.createStatement();
            ResultSet rs=state.executeQuery("SELECT * FROM PIZZAIOLO WHERE nom_Pzlo='"+n+"'");
            while (rs.next()) {
                verif= true;
            }
        }
        catch(SQLException e) {
            recupNom();
        }

        return verif;
    }

    public static void choisirCommande(int id,Connection c,int piz) {
        try {
            PreparedStatement preparedStatement = c.prepareStatement("UPDATE PIZZAIOLO SET num_Cmd= ? WHERE num_Pzlo=?");

            // Paramètres pour la mise à jour
            preparedStatement.setInt(1, id);
            preparedStatement.setInt(2, piz);

            preparedStatement.executeUpdate();
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
    }




    public static void main(String[] args) {
		/*Connection c=openConnection();
		ArrayList<Stock> stc=donneStock(c);
		ArrayList<Pizza> ps=donnePizza(c,stc);
		ArrayList<Commande>com=donneCommande(c,ps);
		String nom =recupNom();

			nom=recupNom();


		pizzaiolo mr=donnepizzaiolo(c,com,nom);
		*/






        //choisirCommande(24,c,24);






    //}




	/*public static ArrayList<Ingredient> donneIngredient(Connection c,ArrayList<Stock> stc){

	ArrayList<Ingredient> pz=new ArrayList<Ingredient>();
	try {
	Statement state=c.createStatement();
	ResultSet rs=state.executeQuery("SELECT * FROM INGREDIENT");
	int i=0;
	 while (rs.next()) {
			String nom=rs.getString(1);
			int quanti=rs.getInt(2);

}

	}
	catch(SQLException e) {
		System.out.print("erreur");
	}
	return pz;
}



}
*/