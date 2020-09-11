package com_client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import projet.bin.Client;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;


import com_connection.ConnectionDB;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;

import javafx.scene.control.TableView;

import javafx.scene.control.TableColumn;

public class ClientController  implements Initializable {
	@FXML
	private TextField idclient;
	@FXML
	private TextField nomtxt;
	@FXML
	private TextField txtPrenom;
	@FXML
	private TextField txtadresse;
	@FXML
	private TextField idtxt;
	@FXML
	private FontAwesomeIcon searchIcon;
	@FXML
	private Button btnAjouter;
	@FXML
	private Button btnSupp;
	@FXML
	private Button btnModifier;
	@FXML
	private Button btnAfficher;
	@FXML
	private Button btnVider;
	@FXML
	private TableView<Client> table;
	public ObservableList<Client> data = FXCollections.observableArrayList();
	@FXML
	private TableColumn <Client, Integer> idcolumn;
	@FXML
	private TableColumn<Client, String> nomcolumn;
	@FXML
	private TableColumn<Client, String> prenomcolumn;
	@FXML
	private TableColumn<Client, String> adressecolumn;
	@FXML
	private TableColumn<Client, Integer> telecolumn;
	@FXML
	private TableColumn<Client, String> emailcolumn;
	@FXML
	private TextField txttele;
	@FXML
	private TextField txtemail;

	@FXML
	private TextField rechercher;
		
/*--------------------------------------------------AJOUTER----------------------------------------------------------------------------------*/	    

	
    public static int Ajouter(Client cl)
    {
    	Connection conn = ConnectionDB.conDB();
    	int rs = 0;
    	try {
    		
    		String sql = "insert into client(id_client , nom , prenom , adresse , telephone , email ) values(?,?,?,?,?,?)";
    		PreparedStatement stm = conn.prepareStatement(sql);
    		
    		stm.setString(1, cl.getNom());
    		stm.setString(2, cl.getPrenom());
    		stm.setString(3, cl.getAdresse());
    		stm.setString(4, cl.getTelephone());
    		stm.setString(5, cl.getEmail());
    		
    		
    		
    		 rs = stm.executeUpdate();
    		
    		
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
    	
    return rs;	
    }
    
    
    
    public void insertdata (ActionEvent event)
    {
    	String NOM = nomtxt.getText();
    	String PRENOM = txtPrenom.getText();
    	String ADDRESSE = txtadresse.getText();
    	String TELE = txttele.getText();
    	String EMAIL = txtemail.getText();
    	
    	
    	Client cl = new Client();
    	
    	
    	cl.setNom(NOM);
    	cl.setPrenom(PRENOM);
    	cl.setAdresse(ADDRESSE);
    	cl.setTelephone(TELE);
    	cl.setEmail(EMAIL);
    	
    	
    	
    	int etat = Ajouter(cl);
    	
    	
    	if (etat > 0) {
    	
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Ajouter Client");
    	alert.setHeaderText("Information");
    	alert.setContentText("Client bien ajout�");
    	alert.showAndWait();
    	
              }
    	else
    	{
    		Alert alert = new Alert(AlertType.ERROR);
	    	alert.setTitle("Ajouter Client");
	    	alert.setHeaderText("Information");
	    	alert.setContentText("Client Non ajout�");
	    	alert.showAndWait();	
    
    	}
    	
    	
    	viewAbonnee();
    	
    	}
	
	
/*--------------------------------------------------AFFICHER----------------------------------------------------------------------------------*/	    
    
    
    public void viewAbonnee() {
		// TODO Autogenerated
		
		try {
			
    		Connection conn = ConnectionDB.conDB();
    		String sql = "SELECT * FROM `client`" ;
    		PreparedStatement stm  = conn.prepareStatement(sql);
    		ResultSet rs = stm.executeQuery();
    		
    		while (rs.next())
    		{
    			data.add(new Client(rs.getString(1) , rs.getString(2) , rs.getString(3) , rs.getString(4) , rs.getString(5) ,  rs.getString(6) ) );
    			
    		}
    		conn.close();
    		
		} catch (Exception e) {
			// TODO: handle exception
		}
    	
		idcolumn.setCellValueFactory(new PropertyValueFactory<Client, Integer>("id_client"));
    	nomcolumn.setCellValueFactory(new PropertyValueFactory<Client, String>("nom"));
    	prenomcolumn.setCellValueFactory(new PropertyValueFactory<Client, String>("prenom"));
    	adressecolumn.setCellValueFactory(new PropertyValueFactory<Client, String>("adresse"));
    	telecolumn.setCellValueFactory(new PropertyValueFactory<Client, Integer>("telephone"));
    	emailcolumn.setCellValueFactory(new PropertyValueFactory<Client, String>("email"));
    	
    	table.setItems(data);	
	}
    
	
	
/*--------------------------------------------------MODIFIER----------------------------------------------------------------------------------*/	    
    
    public static int modifier(Client cl)
	{
		Connection conn = ConnectionDB.conDB();
    	int d = 0;
    	try {
    		
    		String sql = "update client set  nom = ? , prenom= ?  , adresse = ? , telephone = ? , email = ? where id_client = ?  ";
    		PreparedStatement stm = conn.prepareStatement(sql);
    		
    		stm.setString(1, cl.getNom());
    		stm.setString(2, cl.getPrenom());
    		stm.setString(3, cl.getAdresse());
    		stm.setString(4, cl.getTelephone());
    		stm.setString(5, cl.getEmail());
    		stm.setString(5, cl.getId_client());
    		
    		
    		 d = stm.executeUpdate();
    		
    		
			
		} catch (SQLException e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
    	
    return d;	
		
		
		
	}
	
    
    public void update (ActionEvent event) 
	{
    	
    	String NOM = nomtxt.getText();
    	String PRENOM = txtPrenom.getText();
    	String ADRESSE = txtadresse.getText();
    	String TELE = txttele.getText();
    	String EMAIL = txtemail.getText();
    	
    	
    	Client cl = new Client();
    	
    	
    	cl.setNom(NOM);
    	cl.setPrenom(PRENOM);
    	cl.setAdresse(ADRESSE);
    	cl.setTelephone(TELE);
    	cl.setEmail(EMAIL);
    	
    	
    	 Client selected =table.getSelectionModel().getSelectedItem();
	        String idd = selected.getId_client();
    	int etat = modifier(cl);
    	
    	
    	if (etat > 0) {
    	
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Modifier Client");
    	alert.setHeaderText("Information");
    	alert.setContentText("Client bien Modifi�");
    	alert.showAndWait();
    	
              }
    	else
    	{
    		Alert alert = new Alert(AlertType.ERROR);
	    	alert.setTitle("Modifier Client");
	    	alert.setHeaderText("Information");
	    	alert.setContentText("Client Non Modifi�");
	    	alert.showAndWait();	
    
    	}
    	
    	viewAbonnee();
    	
	}
    
    
 /*--------------------------------------------------SUPPRIMER----------------------------------------------------------------------------------*/	    
    
    
    public static int supp(String id)
	{
		Connection conn = ConnectionDB.conDB();
		int d = 0;
		
		
		try {
			
			String sql = "delete from client where id_client = ? ";
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, id);
			d = stm.executeUpdate();
			
		} catch (SQLException e) {
			// TODO: handle exception
			
			System.out.println(e.getMessage());
		}
		
		
		
		return d;
	
		
	}
    
    
    public void delete (ActionEvent event)
	{
    	Client selected =table.getSelectionModel().getSelectedItem();
        String idd = selected.getId_client();
        table.getItems().removeAll(selected);
		int etat = supp(idd);
		
		if(etat > 0) 
        {
    	
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Supprimer Abonnee");
    	alert.setHeaderText("Information");
    	alert.setContentText("Abonnee bien Supprim�");
    	alert.showAndWait();
    	
              }
    	else
    	{
    		Alert alert = new Alert(AlertType.ERROR);
	    	alert.setTitle("Supprimer Abonnee");
	    	alert.setHeaderText("Information");
	    	alert.setContentText("Abonnee Non Supprim�");
	    	alert.showAndWait();	
    
    	}
		
		
	}
    
    
    
   

    
/*--------------------------------------------------Vider les champs----------------------------------------------------------------------------------*/	 	    	    
    
    
    public void vider ( )
    {
    	try {
    		
	    	nomtxt.clear();
	    	txtPrenom.clear();
	    	txtadresse.clear();
	    	txttele.clear();
	    	txtemail.clear();
	    	
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
    	
    	
    	
    }

/*------------------------------------------------Table TO TEXTFIELD-------------------------------------------------------------------------------*/
    private void TableToText() {
		table.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				Client l =table.getItems().get(table.getSelectionModel().getSelectedIndex());
			//	id.setText(l.getId());
				nomtxt.setText(l.getNom());
				txtPrenom.setText(l.getPrenom());
				txtadresse.setText(l.getAdresse());
				txttele.setText(l.getTelephone());
				txtemail.setText(l.getEmail());
			
			}
			
			
		});
	}
@Override
public void initialize(URL location, ResourceBundle resources) {
	// TODO Auto-generated method stub
	viewAbonnee();
	TableToText();
}
    
@FXML
public void back(MouseEvent event) throws IOException {
	// TODO Autogenerated
	Parent homePage = FXMLLoader.load(getClass().getResource("/application/login.fxml"));
    Scene homepageScene = new Scene(homePage);
    Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    appStage.setScene(homepageScene);
    appStage.show();
}
    	
	
    
private void loadDataDB() {
	data.clear();
	try {
		Connection conn = ConnectionDB.conDB();
		PreparedStatement pst=conn.prepareStatement("Select * from client");
		ResultSet rs=pst.executeQuery();
		while(rs.next()) {
			data.add(new Client(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
		}
	}
	catch(SQLException ex){
		Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null,ex);
	}
	table.setItems(data);
}
    
private void search() {
	rechercher.setOnKeyReleased(e->{
		if(rechercher.getText().equals("")) {
			loadDataDB();
		}
		else {
			data.clear();
			String sql= "Select * from client where id_client LIKE '%"+rechercher.getText()+"%' " + "UNION Select * from client where nom LIKE '%"+rechercher.getText()+"%' "+ "UNION Select * from client where prenom LIKE '%"+rechercher.getText()+"%' "+ "UNION Select * from client where adresse LIKE '%"+rechercher.getText()+"%' "+ "UNION Select * from client where email LIKE '%"+rechercher.getText()+"%' ";
			try {
				Connection conn = ConnectionDB.conDB();
				PreparedStatement pst=conn.prepareStatement(sql);
				ResultSet rs=pst.executeQuery();
				while(rs.next()) {
					data.add(new Client(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6)));
				}
				table.setItems(data);
			}catch(SQLException ex) {
				Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null,ex);
			}
		}
				
	}
	);
}
	
}
