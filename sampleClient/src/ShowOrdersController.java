import data.Order;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;

public class ShowOrdersController {

    @FXML private TableView<Order> ordersTable;
    @FXML private TableColumn<Order, Integer> orderNumberCol;
    @FXML private TableColumn<Order, Integer> parkingSpaceCol;
    @FXML private TableColumn<Order, String> orderDateCol;

    @FXML
    public void initialize() {
    	orderNumberCol.setCellValueFactory(new PropertyValueFactory<>("order_number"));
    	parkingSpaceCol.setCellValueFactory(new PropertyValueFactory<>("parking_space"));
    	orderDateCol.setCellValueFactory(new PropertyValueFactory<>("order_date"));


        // Replace Main.clientConsole with a temporary one to intercept the response
        Main.clientConsole = new ClientConsole("localhost", 5555) {
            @Override
            public void display(Object message) {
            	try {
                if (message instanceof ArrayList<?> list && !list.isEmpty() && list.get(0) instanceof Order) {
                	ObservableList<Order> data = FXCollections.observableArrayList();
                    for (Object o : list) {
                        data.add((Order) o);
                    }
                    ordersTable.setItems(data);
                } 
                
            	}catch(Exception e){
            		 e.printStackTrace();
                }
            }
        };

        Main.clientConsole.accept("showAllOrders");
    }

    public void goBack() {
        try {
            Main.switchScene("MainPage.fxml");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
