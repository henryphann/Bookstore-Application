package coe528.project;

import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;                    
import javafx.scene.Scene;        
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

/**
 *
 * @author Jack Nguyen
 */
public class Main extends Application 
{   
    // Instance Variables
    Owner admin = new Owner();
    Bookstore store = new Bookstore();
    List<Book> customersBooks = new ArrayList();
    FileHandler fileHandler = new FileHandler();
    
    double totalCost = 0.0;
    int userIndex;
    boolean tempBool;
    
    final int btHeight = 25;
    final int btWidth = 100;
    
    final int windowHeight = 500;
    final int windowWidth = 300;
    
    final int paHGap = 5;
    final int paVGap = 5;
    
    final int tableHeight = 200;
    final int tableWidth = 450;
    
    final Insets defaultInset = new Insets(10, 10, 10, 10);
    
    // Returns true is the string can be turned into a double
    public static boolean isNumeric(String str) {
        if (str == null) {
            return false;
        }
        
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException e) {
            return false;
        }
        
        return true;
    }
    
    // Start of the application
    @Override
    public void start(Stage primaryStage) {
        // ==================== Pull from .txt files ===========================
        store.getBooks().addAll(fileHandler.loadBooks());
        store.getCustomer().addAll(fileHandler.loadCustomers());
        
        // ==================== Login ========================================== 
        // GridPane
        GridPane paLogin = new GridPane();
        paLogin.setPadding(defaultInset);
        paLogin.setAlignment(Pos.CENTER);
        paLogin.setHgap(paHGap);
        paLogin.setVgap(paVGap);
        
        // Buttons
        Button btLogin = new Button("Login");
        
        // TextFields
        TextField tfLoginUsername = new TextField();
        TextField tfLoginPassword = new TextField();
        
        // Add elements to the pane
        paLogin.add(new Text("Welcome to the BookStore App"), 0, 0);
        paLogin.add(new Text("Username:"), 0, 1);
        paLogin.add(tfLoginUsername, 1, 1);
        paLogin.add(new Text("Password:"), 0, 2);
        paLogin.add(tfLoginPassword, 1, 2);
        paLogin.add(btLogin, 1, 3);
        
        // Create the scene
        Scene scLogin = new Scene(paLogin, windowHeight, windowWidth);
        
        // ==================== Owner Start ==================================== 
        // GridPane
        GridPane paOwner = new GridPane();
        paOwner.setPadding(defaultInset);
        paOwner.setAlignment(Pos.CENTER);
        paOwner.setHgap(paHGap);
        paOwner.setVgap(paVGap);
        
        // Buttons
        Button btBooks = new Button("Books");
        Button btCustomers = new Button("Customers");
        Button btOwnerLogout = new Button("Logout");
        
        btBooks.setPrefSize(btWidth, btHeight);
        btCustomers.setPrefSize(btWidth, btHeight);
        btOwnerLogout.setPrefSize(btWidth, btHeight);
        
        // Add elements to the pane
        paOwner.add(btBooks, 0, 0);
        paOwner.add(btCustomers, 0, 1);
        paOwner.add(btOwnerLogout, 0, 2);
        
        // Create the scene
        Scene scOwner = new Scene(paOwner, windowHeight, windowWidth);
        
        // ==================== Owner Books ====================================
        // GridPane
        GridPane paBooks = new GridPane();
        paBooks.setPadding(defaultInset);
        paBooks.setAlignment(Pos.CENTER);
        paBooks.setHgap(paHGap);
        paBooks.setVgap(paVGap);
        
        // Buttons
        Button btBooksAdd = new Button("Add");
        Button btBooksBack = new Button("Back");
        Button btBooksDelete = new Button("Delete");
                
        btBooksAdd.setPrefSize(btWidth, btHeight);
        btBooksBack.setPrefSize(btWidth, btHeight);
        btBooksDelete.setPrefSize(btWidth, btHeight);
        
        // Textfields
        TextField tfBookName = new TextField();
        TextField tfBookPrice = new TextField();
        
        // Columns
        TableColumn<Book, String> tcBooksBookName = new TableColumn<>("Book Name");
        TableColumn<Book, String> tcBooksBookPrice = new TableColumn<>("Book Price");
        
        tcBooksBookName.setCellValueFactory(new PropertyValueFactory<>("title"));
        tcBooksBookPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        
        // TableView
        TableView<Book> tvBooksBooks = new TableView<>();
        tvBooksBooks.getColumns().addAll(tcBooksBookName, tcBooksBookPrice);
        tvBooksBooks.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tvBooksBooks.setPrefHeight(tableHeight);
        tvBooksBooks.setPrefWidth(tableWidth);
        
        ObservableList<Book> obsTvBooks = FXCollections.observableArrayList(store.getBooks());
        tvBooksBooks.setItems(obsTvBooks);
        
        // Add elements to the pane
        paBooks.add(tvBooksBooks, 0, 0, 5, 1);
        paBooks.add(new Text("Name"), 0, 1);
        paBooks.add(tfBookName, 1, 1);
        paBooks.add(new Text("Price"), 2, 1);
        paBooks.add(tfBookPrice, 3, 1);
        paBooks.add(btBooksAdd, 4, 1);
        paBooks.add(btBooksBack, 0, 2, 2, 1);
        paBooks.add(btBooksDelete, 4, 2);
        
        // Fix sizing
        paBooks.getColumnConstraints().add(new ColumnConstraints(40));
        paBooks.getColumnConstraints().add(new ColumnConstraints(200));
        paBooks.getColumnConstraints().add(new ColumnConstraints(30));
        paBooks.getColumnConstraints().add(new ColumnConstraints(50));
        
        // Create the scene
        Scene scBooks = new Scene(paBooks, windowHeight, windowWidth);
        
        // ==================== Owner Customers ================================
        // GridPane
        GridPane paCustomers = new GridPane();
        paCustomers.setPadding(defaultInset);
        paCustomers.setAlignment(Pos.CENTER);
        paCustomers.setHgap(paHGap);
        paCustomers.setVgap(paVGap);
        
        // Buttons
        Button btCustomersAdd = new Button("Add");
        Button btCustomersBack = new Button("Back");
        Button btCustomersDelete = new Button("Delete");
                
        btCustomersAdd.setPrefSize(btWidth, btHeight);
        btCustomersBack.setPrefSize(btWidth, btHeight);
        btCustomersDelete.setPrefSize(btWidth, btHeight);
        
        // TextFields
        TextField tfCustomersUsername = new TextField();
        TextField tfCustomersPassword = new TextField();
        
        // Columns
        TableColumn<Book, String> tcUsername = new TableColumn<>("Username");
        TableColumn<Book, String> tcPassword = new TableColumn<>("Password");
        TableColumn<Book, String> tcPoints = new TableColumn<>("Points");
        
        tcUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
        tcPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        tcPoints.setCellValueFactory(new PropertyValueFactory<>("points"));
        
        // TableView
        TableView tvCustomers = new TableView();
        tvCustomers.getColumns().addAll(tcUsername, tcPassword, tcPoints);
        tvCustomers.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tvCustomers.setPrefHeight(tableHeight);
        tvCustomers.setPrefWidth(tableWidth);
        
        ObservableList<Customer> obsTvCustomers = FXCollections.observableArrayList(store.getCustomer());
        tvCustomers.setItems(obsTvCustomers);
        
        // Add elements to the pane
        paCustomers.add(tvCustomers, 0, 0, 5, 1);
        paCustomers.add(new Text("Username"), 0, 1);
        paCustomers.add(tfCustomersUsername, 1, 1);
        paCustomers.add(new Text("Password"), 2, 1);
        paCustomers.add(tfCustomersPassword, 3, 1);
        paCustomers.add(btCustomersAdd, 4, 1);
        paCustomers.add(btCustomersBack, 0, 2, 2, 1);
        paCustomers.add(btCustomersDelete, 4, 2);
        
        // Fix sizing
        paCustomers.getColumnConstraints().add(new ColumnConstraints(60));
        paCustomers.getColumnConstraints().add(new ColumnConstraints(110));
        paCustomers.getColumnConstraints().add(new ColumnConstraints(60));
        paCustomers.getColumnConstraints().add(new ColumnConstraints(110));
        
        // Create the scene
        Scene scCustomers = new Scene(paCustomers, windowHeight, windowWidth);
        
        // ==================== Customer Start =================================
        // GridPane
        GridPane paUser = new GridPane();
        paUser.setPadding(defaultInset);
        paUser.setAlignment(Pos.CENTER);
        paUser.setHgap(paHGap);
        paUser.setVgap(paVGap);
        
        // Buttons
        Button btBuy = new Button("Buy");
        Button btRedeem = new Button("Redeem points and Buy");
        Button btUserLogout = new Button("Logout");
        
        btBuy.setPrefSize(btWidth, btHeight);
        btRedeem.setPrefSize(btWidth + 50, btHeight);
        btUserLogout.setPrefSize(btWidth, btHeight);
        
        // Columns
        TableColumn<Book, String> tcUserBookName = new TableColumn<>("Book Name");
        TableColumn<Book, String> tcUserBookPrice = new TableColumn<>("Book Price");
        TableColumn<Book, Boolean> tcUserSelect = new TableColumn<>("Select");
        
        tcUserBookName.setCellValueFactory(new PropertyValueFactory<Book, String>("title"));
        tcUserBookPrice.setCellValueFactory(new PropertyValueFactory<Book, String>("price"));
        tcUserSelect.setCellValueFactory(new PropertyValueFactory<Book, Boolean>("select"));
        
        
        // TableView
        TableView tvUserBooks = new TableView();
        tvUserBooks.getColumns().addAll(tcUserBookName, tcUserBookPrice, tcUserSelect);
        tvUserBooks.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        tvUserBooks.setPrefHeight(tableHeight);
        tvUserBooks.setPrefWidth(tableWidth);
        
        tvUserBooks.setItems(obsTvBooks);
      
        // Add elements to the pane
        if (store.getCustomer().size() != 0) {
            paUser.add(new Text("Welcome " + store.getCustomer().get(userIndex).getUsername() + ". You have " + 
                                store.getCustomer().get(userIndex).getPoints() + " points. Your status is " + 
                                store.getCustomer().get(userIndex).getStatus().toString() + "."), 0, 0);
        }
        
        paUser.add(tvUserBooks, 0, 1, 3, 1);
        paUser.add(btBuy, 0, 2);
        paUser.add(btRedeem, 1, 2);
        paUser.add(btUserLogout, 2, 2);
        
        // Fix sizing
        paUser.getColumnConstraints().add(new ColumnConstraints(100));
        paUser.getColumnConstraints().add(new ColumnConstraints(150));
        
        // Create the scene
        Scene scUser = new Scene(paUser, windowHeight, windowWidth);
        
        // ==================== Customer Cost ==================================
        // GridPane
        GridPane paCost = new GridPane();
        paCost.setPadding(defaultInset);
        paCost.setAlignment(Pos.CENTER);
        paCost.setHgap(paHGap);
        paCost.setVgap(paVGap);
        
        // Buttons
        Button btCostLogout = new Button("Logout");

        btCostLogout.setPrefSize(btWidth, btHeight);

        // Add elements to the pane
        paCost.add(new Text("Total Cost: " + totalCost), 0, 0);
        
        if (store.getCustomer().size() != 0) {
            paCost.add(new Text("Points: " + store.getCustomer().get(userIndex).getPoints() +
                                ", Status: " + store.getCustomer().get(userIndex).getStatus().toString()), 0, 1);
        }
        
        paCost.add(btCostLogout, 0, 2);
        
        // Create the scene
        Scene scCost = new Scene(paCost, windowHeight, windowWidth);
        
        // ==================== Stage ==========================================
        primaryStage.setTitle("Bookstore App");
        primaryStage.setScene(scLogin);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> {
            fileHandler.saveBooks(store.getBooks());
            fileHandler.saveCustomers(store.getCustomer());
        });
        
        // ==================== Button Actions =================================
        // Login 
        btLogin.setOnAction(e -> {
            if (admin.login(tfLoginUsername.getText(), tfLoginPassword.getText())) {
                primaryStage.setScene(scOwner);
            } else {
                for (int i = 0; i < store.getCustomer().size(); i++) {
                    if (store.getCustomer().get(i).login(tfLoginUsername.getText(), tfLoginPassword.getText())) {
                        userIndex = i;
                        
                        paUser.getChildren().clear();
                        
                        if (store.getCustomer().size() != 0) {
                            paUser.add(new Text("Welcome " + store.getCustomer().get(userIndex).getUsername() + ". You have " + 
                                store.getCustomer().get(userIndex).getPoints() + " points. Your status is " + 
                                store.getCustomer().get(userIndex).getStatus().toString() + "."), 0, 0);
                        }
                        
                        paUser.add(tvUserBooks, 0, 1, 3, 1);
                        paUser.add(btBuy, 0, 2);
                        paUser.add(btRedeem, 1, 2);
                        paUser.add(btUserLogout, 2, 2);
                        
                        primaryStage.setScene(scUser);
                    }
                }
            }
        });
        
        // Logout from Owner
        btOwnerLogout.setOnAction(e -> {
            tfLoginUsername.setText("");
            tfLoginPassword.setText("");
            
            primaryStage.setScene(scLogin);
        });
        
        // Logout from User
        btUserLogout.setOnAction(e -> {
            tfLoginUsername.setText("");
            tfLoginPassword.setText("");
            
            primaryStage.setScene(scLogin);
        });
        
        // Logout from Buy inside User
        btCostLogout.setOnAction(e -> {
            tfLoginUsername.setText("");
            tfLoginPassword.setText("");
            
            primaryStage.setScene(scLogin);
        });
        
        // To Books
        btBooks.setOnAction(e -> {
            tvBooksBooks.refresh();
            primaryStage.setScene(scBooks);
        });    
        
        // Add Book
        btBooksAdd.setOnAction(e -> {
            tempBool = false;
            
            for (int i = 0; i < obsTvBooks.size(); i++) {
                if (tfBookName.getText().equals(obsTvBooks.get(i).getTitle())) {
                    tempBool = true;
                }
            }
            
            if (!tempBool && !tfBookName.getText().equals("") && 
                !tfBookPrice.getText().equals("") && isNumeric(tfBookPrice.getText())) {
                admin.addBook(store, new Book(tfBookName.getText(), tfBookPrice.getText()));
                obsTvBooks.add(store.getBooks().get(store.getBooks().size() - 1));
            }
            
            tvBooksBooks.refresh();
        });  
        
        // Delete Book
        btBooksDelete.setOnAction(e -> {
            TableView.TableViewSelectionModel<Book> bookSelectionModel = tvBooksBooks.getSelectionModel();
            if (!bookSelectionModel.isEmpty()) {
                admin.removeBook(store, obsTvBooks.get(bookSelectionModel.getFocusedIndex()));
                obsTvBooks.remove(bookSelectionModel.getFocusedIndex());
            }
            
            tvBooksBooks.refresh();
        });   
        
        // Back from books
        btBooksBack.setOnAction(e -> {
            tfBookName.setText("");
            tfBookPrice.setText("");
            primaryStage.setScene(scOwner);
        });
        
        // To Customers
        btCustomers.setOnAction(e -> {
            tvCustomers.refresh();
            primaryStage.setScene(scCustomers);
        });
        
        // Add Customer
        btCustomersAdd.setOnAction(e -> {
            tempBool = false;
            
            for (int i = 0; i < obsTvCustomers.size(); i++) {
                if (tfCustomersUsername.getText().equals(obsTvCustomers.get(i).getUsername())) {
                    tempBool = true;
                }
            }
            
            if (!tempBool && !tfCustomersUsername.getText().equals("") && !tfCustomersPassword.getText().equals("")) {
                admin.registerCustomer(store, new Customer(tfCustomersUsername.getText(), tfCustomersPassword.getText()));
                obsTvCustomers.add(store.getCustomer().get(store.getCustomer().size() - 1));
            }
            
            tvCustomers.refresh();
        });  
        
        // Delete Customer
        btCustomersDelete.setOnAction(e -> {
            TableView.TableViewSelectionModel<Customer> customerSelectionModel = tvCustomers.getSelectionModel();
            if (!customerSelectionModel.isEmpty()) {
                admin.deleteCustomer(store, obsTvCustomers.get(customerSelectionModel.getFocusedIndex()));
                obsTvCustomers.remove(customerSelectionModel.getFocusedIndex());
            }
            
            tvCustomers.refresh();
        });   

        // Back from Customers
        btCustomersBack.setOnAction(e -> {
            primaryStage.setScene(scOwner);
        });
        
        // Buy Book
        btBuy.setOnAction(e -> {
            customersBooks.clear();
            tempBool = false;
            totalCost = 0;
            
            for (int i = 0; i < obsTvBooks.size(); i++) {
                if (obsTvBooks.get(i).getSelect().isSelected()) {
                    customersBooks.add(obsTvBooks.get(i));
                    
                    admin.removeBook(store, store.getBooks().get(i));
                    obsTvBooks.remove(obsTvBooks.get(i));
                }
            }
            
            totalCost = store.getCustomer().get(userIndex).buyBook(customersBooks, tempBool);
            
            paCost.getChildren().clear();
            
            paCost.add(new Text("Total Cost: " + totalCost), 0, 0);
            paCost.add(new Text("Points: " + store.getCustomer().get(userIndex).getPoints() +
                                ", Status: " + store.getCustomer().get(userIndex).getStatus().toString()), 0, 1);
            paCost.add(btCostLogout, 0, 2);

            primaryStage.setScene(scCost);
        });
        
        // Buy and Redeem Book
        btRedeem.setOnAction(e -> {
            customersBooks.clear();
            tempBool = true;
            totalCost = 0;
            
            for (int i = 0; i < obsTvBooks.size(); i++) {
                if (obsTvBooks.get(i).getSelect().isSelected()) {
                    customersBooks.add(obsTvBooks.get(i));
                    
                    admin.removeBook(store, store.getBooks().get(i));
                    obsTvBooks.remove(obsTvBooks.get(i));
                }
            }
            
            totalCost = store.getCustomer().get(userIndex).buyBook(customersBooks, tempBool);
            
            paCost.getChildren().clear();
            
            paCost.add(new Text("Total Cost: " + totalCost), 0, 0);
            paCost.add(new Text("Points: " + store.getCustomer().get(userIndex).getPoints() +
                                ", Status: " + store.getCustomer().get(userIndex).getStatus().toString()), 0, 1);
            paCost.add(btCostLogout, 0, 2);

            primaryStage.setScene(scCost);
        });
    }
    
    public static void main(String[] args)
    {
        Application.launch(args);
    }
}
