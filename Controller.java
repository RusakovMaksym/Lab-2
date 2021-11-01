package sample;

import java.sql.Connection;
import java.sql.SQLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class Controller {

    Connection connection;

    @FXML
    private Tab chair;

    @FXML
    private TextField chair_chair_id;

    @FXML
    private TextField chair_hall_id;

    @FXML
    private TextField chair_place;

    @FXML
    private TextField chair_row;

    @FXML
    private Tab film;

    @FXML
    private TextField film_film_id;

    @FXML
    private TextField film_name;

    @FXML
    private TextField film_duration;

    @FXML
    private Tab session;

    @FXML
    private TextField session_session_id;

    @FXML
    private TextField session_film_id;

    @FXML
    private TextField session_hall_id;

    @FXML
    private TextField session_time;

    @FXML
    private TextField session_price;

    @FXML
    private Tab hall;

    @FXML
    private TextField hall_hall_id;

    @FXML
    private TextField hall_name;

    @FXML
    private Tab ticket;

    @FXML
    private TextField ticket_ticket_id;

    @FXML
    private TextField ticket_chair_id;

    @FXML
    private TextField ticket_session_id;

    @FXML
    private TextField ticket_isbought;

    @FXML
    private TextField time1;

    @FXML
    private TextField price1;

    @FXML
    private TextField film_name2;

    @FXML
    private TextField hall_name2;

    @FXML
    private TextField film_name3;

    @FXML
    private TextField time3;

    @FXML
    private TextField idChange;

    @FXML
    private TextArea textArea;

    @FXML
    private TextField randomCount;

    int currentTab() {
        if (chair.isSelected()) return 1;
        if (film.isSelected()) return 2;
        if (session.isSelected()) return 3;
        if (hall.isSelected()) return 4;
        if (ticket.isSelected()) return 5;
        return 0;
    }

    @FXML
    //Поиск по коду товара
    public void findALL(ActionEvent actionEvent) throws SQLException {
        textArea.clear();
        connection = Model.getConnection();
        switch (currentTab()) {
            case 1:
                textArea.setText(textArea.getText() + Model.findAllChair(connection, chair_chair_id.getText(), chair_hall_id.getText(), chair_place.getText(), chair_row.getText()));
                break;
            case 2:
                textArea.setText(textArea.getText() + Model.findAllFilm(connection, film_film_id.getText(), film_name.getText(), film_duration.getText()));
                break;
            case 3:
                textArea.setText(textArea.getText() + Model.findAllSession(connection, session_session_id.getText(), session_film_id.getText(), session_hall_id.getText(), session_time.getText(), session_price.getText()));
                break;
            case 4:
                textArea.setText(textArea.getText() + Model.findAllHall(connection, hall_hall_id.getText(), hall_name.getText()));
                break;
            case 5:
                textArea.setText(textArea.getText() + Model.findAllTicket(connection, ticket_ticket_id.getText(), ticket_session_id.getText(), ticket_chair_id.getText(), ticket_isbought.getText()));
                break;
            default:
                break;

        }
    }

    @FXML
    public void create(ActionEvent actionEvent) throws SQLException {
        textArea.clear();
        connection = Model.getConnection();
        switch (currentTab()) {
            case 1:
                textArea.setText(textArea.getText() + Model.createChair(connection, chair_chair_id.getText(), chair_hall_id.getText(), chair_place.getText(), chair_row.getText()));
                break;
            case 2:
                textArea.setText(textArea.getText() + Model.createFilm(connection, film_film_id.getText(), film_name.getText(), film_duration.getText()));
                break;
            case 3:
                textArea.setText(textArea.getText() + Model.createSession(connection, session_session_id.getText(), session_film_id.getText(), session_hall_id.getText(), session_time.getText(), session_price.getText()));
                break;
            case 4:
                textArea.setText(textArea.getText() + Model.createHall(connection, hall_hall_id.getText(), hall_name.getText()));
                break;
            case 5:
                textArea.setText(textArea.getText() + Model.createTicket(connection, ticket_ticket_id.getText(), ticket_session_id.getText(), ticket_chair_id.getText(), ticket_isbought.getText()));
                break;
            default:
                break;

        }
    }

    @FXML
    public void delete(ActionEvent actionEvent) throws SQLException {
        textArea.clear();
        connection = Model.getConnection();
        switch (currentTab()) {
            case 1:
                textArea.setText(textArea.getText() + Model.deleteChair(connection, chair_chair_id.getText(), chair_hall_id.getText(), chair_place.getText(), chair_row.getText()));
                break;
            case 2:
                textArea.setText(textArea.getText() + Model.deleteFilm(connection, film_film_id.getText(), film_name.getText(), film_duration.getText()));
                break;
            case 3:
                textArea.setText(textArea.getText() + Model.deleteSession(connection, session_session_id.getText(), session_film_id.getText(), session_hall_id.getText(), session_time.getText(), session_price.getText()));
                break;
            case 4:
                textArea.setText(textArea.getText() + Model.deleteHall(connection, hall_hall_id.getText(), hall_name.getText()));
                break;
            case 5:
                textArea.setText(textArea.getText() + Model.deleteTicket(connection, ticket_ticket_id.getText(), ticket_session_id.getText(), ticket_chair_id.getText(), ticket_isbought.getText()));
                break;
            default:
                break;
        }
    }

    public void genRandom(ActionEvent actionEvent) throws SQLException {
        textArea.clear();
        connection = Model.getConnection();
        textArea.setText(textArea.getText() + Model.genRandom(connection, randomCount.getText(), currentTab()));
    }

    @FXML
    public void change(ActionEvent actionEvent) throws SQLException {
        textArea.clear();
        connection = Model.getConnection();
        switch (currentTab()) {
            case 1:
                textArea.setText(textArea.getText() + Model.changeChair(connection, chair_chair_id.getText(), chair_hall_id.getText(), chair_place.getText(), chair_row.getText(), idChange.getText()));
                break;
            case 2:
                textArea.setText(textArea.getText() + Model.changeFilm(connection, film_film_id.getText(), film_name.getText(), film_duration.getText(), idChange.getText()));
                break;
            case 3:
                textArea.setText(textArea.getText() + Model.changeSession(connection, session_session_id.getText(), session_film_id.getText(), session_hall_id.getText(), session_time.getText(), session_price.getText(), idChange.getText()));
                break;
            case 4:
                textArea.setText(textArea.getText() + Model.changeHall(connection, hall_hall_id.getText(), hall_name.getText(), idChange.getText()));
                break;
            case 5:
                textArea.setText(textArea.getText() + Model.changeTicket(connection, ticket_ticket_id.getText(), ticket_session_id.getText(), ticket_chair_id.getText(), ticket_isbought.getText(), idChange.getText()));
                break;
            default:
                break;
        }
    }

    @FXML
    public void interface1(ActionEvent actionEvent) throws SQLException {
        textArea.clear();
        connection = Model.getConnection();
        textArea.setText(textArea.getText() + Model.getInterface1(connection, time1.getText(), price1.getText()));
    }

    @FXML
    void interface2(ActionEvent event) throws SQLException {
        textArea.clear();
        connection = Model.getConnection();
        textArea.setText(textArea.getText() + Model.getInterface2(connection, film_name2.getText(), hall_name2.getText()));
    }

    @FXML
    void interface3(ActionEvent event) throws SQLException {
        textArea.clear();
        connection = Model.getConnection();
        textArea.setText(textArea.getText() + Model.getInterface3(connection, film_name3.getText(), time3.getText()));
    }
}
