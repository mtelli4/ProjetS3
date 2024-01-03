/*package Model;

import java.util.ArrayList;
import java.sql.*;
import java.awt.*;

public class pizzaiolo {
    private int id;
    private Commande faire;
    private String nom;




    public int getId() {
        return id;
    }



    public void setId(int id) {
        this.id = id;
    }



    public Commande getFaire() {
        return faire;
    }



    public void setFaire(Commande faire) {
        this.faire = faire;
    }



    public String getNom() {
        return nom;
    }



    public void setNom(String nom) {
        this.nom = nom;
    }



    public pizzaiolo(int ids,String nm,Commande c) {
        faire=c;
        nom=nm;
        id=ids;

    }



    public static void main(String[] args) {

	/*Connection connexion = null;
	Statement statement = null;
	ResultSet resultSet = null;
	try {
        // Chargement du pilote JDBC
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Connexion à la base de données MySQL (remplacez les paramètres par les vôtres)
        String url = "jdbc:mysql://192.70.36.54/saes3-aratovo";
        String user = "saes3-aratovo";
        String password = "Projet_rma_s3";
        connexion = DriverManager.getConnection(url, user, password);

        // Exemple : exécution d'une requête SELECT
        statement = connexion.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM CLIENT");

        // Traitement des résultats
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String data = resultSet.getString(2);
            // ... récupérez d'autres colonnes selon vos besoins
            System.out.println("ID: " + id + ", Data: " + data);
        }

    } catch (ClassNotFoundException | SQLException e) {
        e.printStackTrace();
    } finally {
        // Fermeture des ressources
        try {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connexion != null) connexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }




}


*/