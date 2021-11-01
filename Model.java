package sample;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Integer.*;

class Model {
    static final String user = "sysdba";
    static final String password = "masterkey";
    static final String url = "jdbc:postgresql://localhost:5432/KPI";

    //Функция для подключения к БД
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public static String findAllChair(Connection connection, String chair_id, String hall_id, String place, String row) throws SQLException {
        if (!chair_id.matches("\\d+|") || !hall_id.matches("\\d+|") || !place.matches("\\d+|") || !row.matches("\\d+|")) {
            return "Некоректний ввід даних!";
        }

        String sql = "SELECT * FROM public.chair WHERE";
        sql += " chair_id = " + (chair_id.length() > 0 ? chair_id : "chair_id");
        sql += " and hall_id = " + (hall_id.length() > 0 ? hall_id : "hall_id");
        sql += " and place = " + (place.length() > 0 ? place : "place");
        sql += " and row = " + (row.length() > 0 ? row : "row");

        System.out.println("sql: " + sql);

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            String resultStr = "Результат пошуку по заданим параметрам:\n";
            try {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    resultStr += "-> chair_id = " + resultSet.getInt(1);
                    resultStr += " hall_id = " + resultSet.getInt(2);
                    resultStr += " place = " + resultSet.getInt(3);
                    resultStr += " row = " + resultSet.getInt(4);
                    resultStr += "\n";
                }
            } catch (Exception ex) {
                return ex.getMessage();
            }
            return (!resultStr.equals("Результат пошуку по заданим параметрам:\n") ? resultStr : "Нічого не знайдено!");
        }
    }

    public static String findAllFilm(Connection connection, String film_id, String name, String duration) throws SQLException {

        if (!duration.matches("")) {
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
            try {
                Date Dateduration = sdf.parse(duration);
            } catch (Exception ex) {
                return "Час заданий некроектно";
            }
        }
        if (!film_id.matches("\\d+|")) {
            return "Некоректний ввід даних!";
        }

        String sql = "SELECT * FROM public.film WHERE";
        sql += " film_id = " + (film_id.length() > 0 ? film_id : "film_id");
        sql += " and name like " + (name.length() > 0 ? ("'%" + name + "%'") : "name");
        sql += " and duration = " + (duration.length() > 0 ? ("'" + duration + "'") : "duration");

        System.out.println("sql: " + sql);

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            String resultStr = "Результат пошуку по заданим параметрам:\n";
            try {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    resultStr += "-> film_id = " + resultSet.getInt(1);
                    resultStr += " name = " + resultSet.getString(2);
                    resultStr += " duration = " + resultSet.getTime(3);
                    resultStr += "\n";
                }
            } catch (Exception ex) {
                return ex.getMessage();
            }
            return (!resultStr.equals("Результат пошуку по заданим параметрам:\n") ? resultStr : "Нічого не знайдено!");
        }
    }

    public static String findAllSession(Connection connection, String session_id, String film_id, String hall_id, String time, String price) throws SQLException {

        if (!time.matches("")) {
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
            try {
                Date Dateduration = sdf.parse(time);
            } catch (Exception ex) {
                return "Час заданий некроектно";
            }
        }
        if (!session_id.matches("\\d+|") || !film_id.matches("\\d+|") || !hall_id.matches("\\d+|") || !price.matches("[+]?\\d+|")) {
            return "Некоректний ввід даних!";
        }

        String sql = "SELECT * FROM public.session WHERE";
        sql += " session_id = " + (session_id.length() > 0 ? session_id : "session_id");
        sql += " and film_id = " + (film_id.length() > 0 ? film_id : "film_id");
        sql += " and hall_id = " + (hall_id.length() > 0 ? hall_id : "hall_id");
        sql += " and time = " + (time.length() > 0 ? ("'" + time + "'") : "time");
        sql += " and price = " + (price.length() > 0 ? price : "price");

        System.out.println("sql: " + sql);

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            String resultStr = "Результат пошуку по заданим параметрам:\n";
            try {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    resultStr += "-> session_id = " + resultSet.getInt(1);
                    resultStr += " film_id = " + resultSet.getInt(2);
                    resultStr += " hall_id = " + resultSet.getInt(3);
                    resultStr += " time = " + resultSet.getTime(4);
                    resultStr += " price = " + resultSet.getInt(5);
                    resultStr += "\n";
                }
            } catch (Exception ex) {
                return ex.getMessage();
            }
            return (!resultStr.equals("Результат пошуку по заданим параметрам:\n") ? resultStr : "Нічого не знайдено!");
        }
    }

    public static String findAllHall(Connection connection, String hall_id, String name) throws SQLException {
        if (!hall_id.matches("[+]?\\d+|")) {
            return "Некоректний ввід даних!";
        }

        String sql = "SELECT * FROM public.hall WHERE";
        sql += " hall_id = " + (hall_id.length() > 0 ? hall_id : "hall_id");
        sql += " and name like " + (name.length() > 0 ? ("'%" + name + "%'") : "name");

        System.out.println("sql: " + sql);

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            String resultStr = "Результат пошуку по заданим параметрам:\n";
            try {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    resultStr += "-> hall_id = " + resultSet.getInt(1);
                    resultStr += " name = " + resultSet.getString(2);
                    resultStr += "\n";
                }
            } catch (Exception ex) {
                return ex.getMessage();
            }
            return (!resultStr.equals("Результат пошуку по заданим параметрам:\n") ? resultStr : "Нічого не знайдено!");
        }
    }

    public static String findAllTicket(Connection connection, String ticket_id, String session_id, String chair_id, String isbought) throws SQLException {

        if (!ticket_id.matches("\\d+|") || !session_id.matches("\\d+|") || !chair_id.matches("\\d+|") || !isbought.matches("true|false|")) {
            return "Некоректний ввід даних!";
        }

        String sql = "SELECT * FROM public.ticket WHERE";
        sql += " ticket_id = " + (ticket_id.length() > 0 ? ticket_id : "ticket_id");
        sql += " and session_id = " + (session_id.length() > 0 ? session_id : "session_id");
        sql += " and chair_id = " + (chair_id.length() > 0 ? chair_id : "chair_id");
        sql += " and isbought = " + (isbought.length() > 0 ? isbought : "isbought");

        System.out.println("sql: " + sql);

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            String resultStr = "Результат пошуку по заданим параметрам:\n";
            try {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    resultStr += "-> ticket_id = " + resultSet.getInt(1);
                    resultStr += " session_id = " + resultSet.getInt(2);
                    resultStr += " chair_id = " + resultSet.getInt(3);
                    resultStr += " isbought = " + resultSet.getBoolean(4);
                    resultStr += "\n";
                }
            } catch (Exception ex) {
                return ex.getMessage();
            }
            return (!resultStr.equals("Результат пошуку по заданим параметрам:\n") ? resultStr : "Нічого не знайдено!");
        }
    }

    public static String createChair(Connection connection, String chair_id, String hall_id, String place, String row) throws SQLException {
        if (!chair_id.matches("\\d+") || !hall_id.matches("\\d+") || !place.matches("\\d+") || !row.matches("\\d+")) {
            return "Некоректний ввід даних!";
        }

        String sql = "INSERT INTO public.chair (chair_id,hall_id, place, row) VALUES (";
        sql += chair_id + ", ";
        sql += hall_id + ", ";
        sql += place + ", ";
        sql += row + ")";

        System.out.println("sql: " + sql);
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (Exception ex) {
            return ex.getMessage();
        }

        String answer = "Створено:\n";
        answer += "-> chair_id = " + chair_id;
        answer += " hall_id = " + hall_id;
        answer += " place = " + place;
        answer += " row = " + row;

        return answer;
    }

    public static String createFilm(Connection connection, String film_id, String name, String duration) throws SQLException {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        try {
            Date Dateduration = sdf.parse(duration);
        } catch (Exception ex) {
            return "Час заданий некроектно";
        }

        if (!film_id.matches("\\d+") || name.equals("")) {
            return "Некоректний ввід даних!";
        }

        String sql = "INSERT INTO public.film (film_id, name, duration) VALUES (";
        sql += film_id + ", ";
        sql += "'" + name + "', ";
        sql += "'" + duration + "')";

        System.out.println("sql: " + sql);
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (Exception ex) {
            return ex.getMessage();
        }

        String answer = "Створено:\n";
        answer += "-> film_id = " + film_id;
        answer += " name = " + name;
        answer += " duration = " + duration;

        return answer;
    }

    public static String createSession(Connection connection, String session_id, String film_id, String hall_id, String time, String price) throws SQLException {
        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        try {
            Date Dateduration = sdf.parse(time);
        } catch (Exception ex) {
            return "Час заданий некроектно";
        }

        if (!session_id.matches("\\d+") || !film_id.matches("\\d+") || !hall_id.matches("\\d+") || !price.matches("\\d+")) {
            return "Некоректний ввід даних!";
        }

        String sql = "INSERT INTO public.session (session_id, film_id, hall_id, time, price) VALUES (";
        sql += session_id + ", ";
        sql += film_id + ", ";
        sql += hall_id + ", ";
        sql += "'" + time + "',";
        sql += price + ")";

        System.out.println("sql: " + sql);
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (Exception ex) {
            return ex.getMessage();
        }

        String answer = "Створено:\n";
        answer += "-> session_id = " + session_id;
        answer += " film_id = " + film_id;
        answer += " hall_id = " + hall_id;
        answer += " time = " + time;
        answer += " price = " + price;

        return answer;
    }

    public static String createHall(Connection connection, String hall_id, String name) throws SQLException {

        if (!hall_id.matches("\\d+") || name.equals("")) {
            return "Некоректний ввід даних!";
        }

        String sql = "INSERT INTO public.hall (hall_id, name) VALUES (";
        sql += hall_id + ", ";
        sql += "'" + name + "')";

        System.out.println("sql: " + sql);
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (Exception ex) {
            return ex.getMessage();
        }

        String answer = "Створено:\n";
        answer += "-> hall_id = " + hall_id;
        answer += " name = " + name;

        return answer;
    }

    public static String createTicket(Connection connection, String ticket_id, String session_id, String chair_id, String isbought) throws SQLException {
        if (!ticket_id.matches("\\d+") || !session_id.matches("\\d+") || !chair_id.matches("\\d+") || !isbought.matches("true|false|")) {
            return "Некоректний ввід даних!";
        }

        String sql = "INSERT INTO public.ticket (ticket_id, session_id, chair_id, isbought) VALUES (";
        sql += ticket_id + ", ";
        sql += session_id + ", ";
        sql += chair_id + ", ";
        sql += isbought + ")";

        System.out.println("sql: " + sql);
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (Exception ex) {
            return ex.getMessage();
        }

        String answer = "Створено:\n";
        answer += "-> ticket_id = " + ticket_id;
        answer += " session_id = " + session_id;
        answer += " chair_id = " + chair_id;
        answer += " isbought = " + isbought;

        return answer;
    }

    public static String deleteChair(Connection connection, String chair_id, String hall_id, String place, String row) throws SQLException {
        if (!chair_id.matches("\\d+|") || !hall_id.matches("\\d+|") || !place.matches("\\d+|") || !row.matches("\\d+|")) {
            return "Некоректний ввід даних!";
        }

        String sql = "DELETE from ticket where chair_id in (select chair_id from chair where";
        sql += " chair_id = " + (chair_id.length() > 0 ? chair_id : "chair_id");
        sql += " and hall_id = " + (hall_id.length() > 0 ? hall_id : "hall_id");
        sql += " and place = " + (place.length() > 0 ? place : "place");
        sql += " and row = " + (row.length() > 0 ? row : "row") + ");\n";


        sql += "DELETE from chair where";
        sql += " chair_id = " + (chair_id.length() > 0 ? chair_id : "chair_id");
        sql += " and hall_id = " + (hall_id.length() > 0 ? hall_id : "hall_id");
        sql += " and place = " + (place.length() > 0 ? place : "place");
        sql += " and row = " + (row.length() > 0 ? row : "row") + ";";

        System.out.println("sql: " + sql);

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (Exception ex) {
            return ex.getMessage();
        }

        String answer = "Видалено все, де:\n->";
        if (chair_id.length() > 0) answer += " chair_id = " + chair_id;
        if (hall_id.length() > 0) answer += " hall_id = " + hall_id;
        if (place.length() > 0) answer += " place = " + place;
        if (row.length() > 0) answer += " row = " + row;

        return answer;
    }

    public static String deleteFilm(Connection connection, String film_id, String name, String duration) throws SQLException {
        if (!duration.matches("")) {
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
            try {
                Date Dateduration = sdf.parse(duration);
            } catch (Exception ex) {
                return "Час заданий некроектно";
            }
        }
        if (!film_id.matches("\\d+|")) {
            return "Некоректний ввід даних!";
        }

        String sql = "DELETE from ticket where session_id in (select session_id from session where film_id in (select film_id from film where";
        sql += " film_id = " + (film_id.length() > 0 ? film_id : "film_id");
        sql += " and name like " + (name.length() > 0 ? "'" + name + "'" : "name");
        sql += " and duration = " + (duration.length() > 0 ? "'" + duration + "'" : "duration") + "));\n";

        sql += "DELETE from session where film_id in (select film_id from film where";
        sql += " film_id = " + (film_id.length() > 0 ? film_id : "film_id");
        sql += " and name like " + (name.length() > 0 ? "'" + name + "'" : "name");
        sql += " and duration = " + (duration.length() > 0 ? "'" + duration + "'" : "duration") + ");\n";

        sql += "DELETE from film where";
        sql += " film_id = " + (film_id.length() > 0 ? film_id : "film_id");
        sql += " and name like " + (name.length() > 0 ? "'" + name + "'" : "name");
        sql += " and duration = " + (duration.length() > 0 ? "'" + duration + "'" : "duration") + ";";

        System.out.println("sql: " + sql);

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (Exception ex) {
            return ex.getMessage();
        }

        String answer = "Видалено все, де:\n->";
        if (film_id.length() > 0) answer += " film_id = " + film_id;
        if (name.length() > 0) answer += " name = " + name;
        if (duration.length() > 0) answer += " duration = " + duration;

        return answer;
    }

    public static String deleteSession(Connection connection, String session_id, String film_id, String hall_id, String time, String price) throws SQLException {
        if (!time.matches("")) {
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
            try {
                Date Dateduration = sdf.parse(time);
            } catch (Exception ex) {
                return "Час заданий некроектно";
            }
        }
        if (!session_id.matches("\\d+|") || !film_id.matches("\\d+|") || !hall_id.matches("\\d+|") || !price.matches("\\d+|")) {
            return "Некоректний ввід даних!";
        }

        String sql = "DELETE from ticket where session_id in (select session_id from session where";
        sql += " session_id = " + (session_id.length() > 0 ? session_id : "session_id");
        sql += " and film_id = " + (film_id.length() > 0 ? film_id : "film_id");
        sql += " and hall_id = " + (hall_id.length() > 0 ? hall_id : "hall_id");
        sql += " and time = " + (time.length() > 0 ? ("'" + time + "'") : "time");
        sql += " and price = " + (price.length() > 0 ? price : "price") + ");\n";

        sql += "DELETE from session where";
        sql += " session_id = " + (session_id.length() > 0 ? session_id : "session_id");
        sql += " and film_id = " + (film_id.length() > 0 ? film_id : "film_id");
        sql += " and hall_id = " + (hall_id.length() > 0 ? hall_id : "hall_id");
        sql += " and time = " + (time.length() > 0 ? ("'" + time + "'") : "time");
        sql += " and price = " + (price.length() > 0 ? price : "price") + ";";

        System.out.println("sql: " + sql);

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (Exception ex) {
            return ex.getMessage();
        }

        String answer = "Видалено все, де:\n->";
        if (session_id.length() > 0) answer += " session_id = " + session_id;
        if (film_id.length() > 0) answer += " film_id = " + film_id;
        if (hall_id.length() > 0) answer += " hall_id = " + hall_id;
        if (time.length() > 0) answer += " time = " + time;
        if (price.length() > 0) answer += " price = " + price;

        return answer;
    }

    public static String deleteHall(Connection connection, String hall_id, String name) throws SQLException {
        if (!hall_id.matches("\\d+|")) {
            return "Некоректний ввід даних!";
        }

        String sql = "DELETE from ticket where session_id in (select session_id from session where hall_id in (select hall_id from hall where";
        sql += " hall_id = " + (hall_id.length() > 0 ? hall_id : "hall_id");
        sql += " and name like " + (name.length() > 0 ? ("'" + name + "'") : "name") + "));\n";

        sql += "DELETE from session where hall_id in (select hall_id from hall where";
        sql += " hall_id = " + (hall_id.length() > 0 ? hall_id : "hall_id");
        sql += " and name like " + (name.length() > 0 ? ("'" + name + "'") : "name") + ");\n";

        sql += "DELETE from chair where hall_id in (select hall_id from hall where";
        sql += " hall_id = " + (hall_id.length() > 0 ? hall_id : "hall_id");
        sql += " and name like " + (name.length() > 0 ? ("'" + name + "'") : "name") + ");\n";

        sql += "DELETE from hall where";
        sql += " hall_id = " + (hall_id.length() > 0 ? hall_id : "hall_id");
        sql += " and name like " + (name.length() > 0 ? ("'" + name + "'") : "name") + ";";

        System.out.println("sql: " + sql);

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (Exception ex) {
            return ex.getMessage();
        }

        String answer = "Видалено все, де:\n->";
        if (hall_id.length() > 0) answer += " hall_id = " + hall_id;
        if (name.length() > 0) answer += " name = " + name;

        return answer;
    }

    public static String deleteTicket(Connection connection, String ticket_id, String session_id, String chair_id, String isbought) throws SQLException {
        if (!ticket_id.matches("\\d+|") || !session_id.matches("\\d+|") || !chair_id.matches("\\d+|") || !isbought.matches("true|false|")) {
            return "Некоректний ввід даних!";
        }

        String sql = "DELETE from ticket where";
        sql += " ticket_id = " + (ticket_id.length() > 0 ? ticket_id : "ticket_id");
        sql += " and session_id = " + (session_id.length() > 0 ? session_id : "session_id");
        sql += " and chair_id = " + (chair_id.length() > 0 ? chair_id : "chair_id");
        sql += " and isbought = " + (isbought.length() > 0 ? isbought : "isbought");

        System.out.println("sql: " + sql);

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (Exception ex) {
            return ex.getMessage();
        }

        String answer = "Видалено все, де:\n->";
        if (ticket_id.length() > 0) answer += " ticket_id = " + ticket_id;
        if (session_id.length() > 0) answer += " session_id = " + session_id;
        if (chair_id.length() > 0) answer += " chair_id = " + chair_id;
        if (isbought.length() > 0) answer += " isbought = " + isbought;

        return answer;
    }

    public static String genRandom(Connection connection, String count, int currentTab) throws SQLException {
        if (count.equals("")) count = "20";
        if (!count.matches("\\d+")) {
            return "Некоректний ввід даних!";
        }
        String sql = "";

        switch (currentTab) {
            case 1:
                sql = "INSERT INTO public.chair (chair_id, hall_id, place, row) VALUES ((select max(chair_id) from chair) + 1, (select hall_id from hall order by random() limit 1), FLOOR(RANDOM() * 100), FLOOR(RANDOM() * 100))";
                break;
            case 2:
                sql = "INSERT INTO public.film (film_id, name, duration) VALUES ((select max(film_id) from film) + 1, substr(md5(random()::text), 0, 25), to_timestamp(random()*2147483647)::time)";
                break;
            case 3:
                sql = "INSERT INTO public.session (session_id, film_id, hall_id, time, price) VALUES ((select max(session_id) from session) + 1,(select film_id from film order by random() limit 1), (select hall_id from hall order by random() limit 1), to_timestamp(random()*2147483647)::time, FLOOR(RANDOM() * 100))";
                break;
            case 4:
                sql = "INSERT INTO public.hall (hall_id, name) VALUES ((select max(hall_id) from hall) + 1, substr(md5(random()::text), 0, 25))";
                break;
            case 5:
                sql = "INSERT INTO public.ticket (ticket_id, session_id, chair_id, isbought) VALUES ((select max(ticket_id) from ticket) + 1, (select session_id from session order by random() limit 1), (select chair_id from chair order by random() limit 1), random() > 0.5)";
                break;
            default:
                break;
        }

        for (int i = 0; i < parseInt(count); i++) {
            System.out.println(i);

            try {
                Statement statement = connection.createStatement();
                statement.executeUpdate(sql);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }

        return "Об'єкти створені!";
    }

    public static String changeChair(Connection connection, String chair_id, String hall_id, String place, String row, String id) throws SQLException {
        if (!id.matches("\\d+")) {
            return "Введіть id для заміни!";
        }

        if (!chair_id.matches("\\d+|") || !hall_id.matches("\\d+|") || !place.matches("\\d+|") || !row.matches("\\d+|")) {
            return "Некоректний ввід даних!";
        }

        if (!(chair_id.matches("\\d+") || hall_id.matches("\\d+") || place.matches("\\d+") || row.matches("\\d+"))) {
            return "Немає чого переписувати!";
        }

        String sql = "UPDATE public.chair SET";
        sql += " chair_id = " + (chair_id.length() > 0 ? chair_id : "chair_id");
        sql += ", hall_id = " + (hall_id.length() > 0 ? hall_id : "hall_id");
        sql += ", place = " + (place.length() > 0 ? place : "place");
        sql += ", row = " + (row.length() > 0 ? row : "row");
        sql += "WHERE chair_id = " + id;

        System.out.println("sql: " + sql);

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (Exception ex) {
            return ex.getMessage();
        }

        return "Оновлено обьект з id = " + id;
    }

    public static String changeFilm(Connection connection, String film_id, String name, String duration, String id) throws SQLException {
        if (!id.matches("\\d+")) {
            return "Введіть id для заміни!";
        }

        if (!duration.matches("")) {
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
            try {
                Date Dateduration = sdf.parse(duration);
            } catch (Exception ex) {
                return "Час заданий некроектно";
            }
        }
        if (!film_id.matches("\\d+|")) {
            return "Некоректний ввід даних!";
        }

        if (film_id.equals("") && name.equals("") && duration.equals("")) {
            return "Немає чого переписувати!";
        }

        String sql = "UPDATE public.film SET";
        sql += " film_id = " + (film_id.length() > 0 ? film_id : "film_id");
        sql += ", name = " + (name.length() > 0 ? ("'" + name + "'") : "name");
        sql += ", duration = " + (duration.length() > 0 ? ("'" + duration + "'") : "duration");
        sql += " WHERE film_id = " + id;

        System.out.println("sql: " + sql);

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (Exception ex) {
            return ex.getMessage();
        }

        return "Оновлено обьект з id = " + id;
    }

    public static String changeSession(Connection connection, String session_id, String film_id, String hall_id, String time, String price, String id) throws SQLException {
        if (!id.matches("\\d+")) {
            return "Введіть id для заміни!";
        }

        if (!time.matches("")) {
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
            try {
                Date Dateduration = sdf.parse(time);
            } catch (Exception ex) {
                return "Час заданий некроектно";
            }
        }
        if (!session_id.matches("\\d+|") || !film_id.matches("\\d+|") || !hall_id.matches("\\d+|") || !price.matches("[+]?\\d+|")) {
            return "Некоректний ввід даних!";
        }

        if (session_id.equals("") && film_id.equals("") && hall_id.equals("") && time.equals("") && price.equals("")) {
            return "Немає чого переписувати!";
        }

        String sql = "UPDATE public.session SET";
        sql += " session_id = " + (session_id.length() > 0 ? session_id : "session_id");
        sql += ", film_id = " + (film_id.length() > 0 ? film_id : "film_id");
        sql += ", hall_id = " + (hall_id.length() > 0 ? hall_id : "hall_id");
        sql += ", time = " + (time.length() > 0 ? ("'" + time + "'") : "time");
        sql += ", price = " + (price.length() > 0 ? price : "price");
        sql += " WHERE session_id = " + id;

        System.out.println("sql: " + sql);

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (Exception ex) {
            return ex.getMessage();
        }

        return "Оновлено обьект з id = " + id;
    }

    public static String changeHall(Connection connection, String hall_id, String name, String id) throws SQLException {
        if (!id.matches("\\d+")) {
            return "Введіть id для заміни!";
        }

        if (!hall_id.matches("[+]?\\d+|")) {
            return "Некоректний ввід даних!";
        }

        if (hall_id.equals("") && name.equals("")) {
            return "Немає чого переписувати!";
        }

        String sql = "UPDATE public.hall SET";
        sql += " hall_id = " + (hall_id.length() > 0 ? hall_id : "hall_id");
        sql += ", name = " + (name.length() > 0 ? ("'" + name + "'") : "name");
        sql += " WHERE hall_id = " + id;

        System.out.println("sql: " + sql);

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (Exception ex) {
            return ex.getMessage();
        }

        return "Оновлено обьект з id = " + id;
    }

    public static String changeTicket(Connection connection, String ticket_id, String session_id, String chair_id, String isbought, String id) throws SQLException {
        if (!id.matches("\\d+")) {
            return "Введіть id для заміни!";
        }

        if (!ticket_id.matches("\\d+|") || !session_id.matches("\\d+|") || !chair_id.matches("\\d+|") || !isbought.matches("true|false|")) {
            return "Некоректний ввід даних!";
        }

        if (ticket_id.equals("") && session_id.equals("") && chair_id.equals("") && isbought.equals("")) {
            return "Немає чого переписувати!";
        }

        String sql = "UPDATE public.ticket SET";
        sql += " ticket_id = " + (ticket_id.length() > 0 ? ticket_id : "ticket_id");
        sql += ", session_id = " + (session_id.length() > 0 ? session_id : "session_id");
        sql += ", chair_id = " + (chair_id.length() > 0 ? chair_id : "chair_id");
        sql += ", isbought = " + (isbought.length() > 0 ? isbought : "isbought");
        sql += " WHERE ticket_id = " + id;

        System.out.println("sql: " + sql);

        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (Exception ex) {
            return ex.getMessage();
        }

        return "Оновлено обьект з id = " + id;
    }

    public static String getInterface1(Connection connection, String time, String price) {
        if (!price.matches("\\d+")) {
            return "Введіть ціну!";
        }

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        try {
            Date Dateduration = sdf.parse(time);
        } catch (Exception ex) {
            return "Час заданий некроектно";
        }
        String sql = "Select f.name, s.time, s.price from public.film f, public.session s where f.film_id = s.film_id and";
        sql += " s.price < (?)";
        sql += " and s.time > (?)";

        System.out.println("sql: " + sql);
        String resultStr = "Результат пошуку по заданим параметрам:\n";

        long m = System.currentTimeMillis();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, parseInt(price));
            statement.setTime(2, Time.valueOf(time));

            try {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    resultStr += "-> Фільм '" + resultSet.getString(1) + "'";
                    resultStr += " на (" + resultSet.getTime(2) + "),";
                    resultStr += " ціна - " + resultSet.getInt(3);
                    resultStr += "\n";
                }
            } catch (Exception ex) {
                return ex.getMessage();
            }
        } catch (SQLException exception) {
            return exception.getMessage();
        }

        m = System.currentTimeMillis() - m;

        return (!resultStr.equals("Результат пошуку по заданим параметрам:\n") ? resultStr + "\nВитрачено " + m + " мілісекунд" : "Нічого не знайдено!");
    }

    public static String getInterface2(Connection connection, String film_name, String hall_name) {

        if (film_name.equals("") || hall_name.equals("")) {
            return "Не всі поля заповнення!";
        }

        String sql = "Select f.name, s.time, h.name from public.film f, public.session s, public.hall h where f.film_id = s.film_id and s.hall_id = h.hall_id and";
        sql += " f.name like ?";
        sql += " and h.name like ?";

        System.out.println("sql: " + sql);
        String resultStr = "Результат пошуку по заданим параметрам:\n";

        long m = System.currentTimeMillis();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, film_name);
            statement.setString(2, hall_name);

            try {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    resultStr += "-> Фільм '" + resultSet.getString(1) + "'";
                    resultStr += " на (" + resultSet.getTime(2) + "),";
                    resultStr += " в залі '" + resultSet.getString(3) + "'";
                    resultStr += "\n";
                }
            } catch (Exception ex) {
                return ex.getMessage();
            }
        } catch (SQLException exception) {
            return exception.getMessage();
        }

        m = System.currentTimeMillis() - m;

        return (!resultStr.equals("Результат пошуку по заданим параметрам:\n") ? resultStr + "\nВитрачено " + m + " мілісекунд" : "Нічого не знайдено!");
    }

    public static String getInterface3(Connection connection, String film_name, String time) {

        if (film_name.equals("") || time.equals("")) {
            return "Не всі поля заповнення!";
        }

        SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss");
        try {
            Date Dateduration = sdf.parse(time);
        } catch (Exception ex) {
            return "Час заданий некроектно";
        }

        //SELECT COUNT(column_name) FROM table_name WHERE condition;
        String sql = "Select count(t.chair_id), f.name, s.time from public.ticket t, public.session s, public.film f where f.film_id = s.film_id and s.session_id = t.session_id and";
        sql += " f.name like ?";
        sql += " and s.time < ?";
        sql += " GROUP BY f.name, s.time";

        System.out.println("sql: " + sql);
        String resultStr = "Результат пошуку по заданим параметрам:\n";

        long m = System.currentTimeMillis();

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, film_name);
            statement.setTime(2, Time.valueOf(time));

            try {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    resultStr += "-> " + resultSet.getInt(1) + " місць на";
                    resultStr += " фільм '" + resultSet.getString(2) + "'";
                    resultStr += " час (" + resultSet.getTime(3) + ")";
                    resultStr += "\n";
                }
            } catch (Exception ex) {
                return ex.getMessage();
            }
        } catch (SQLException exception) {
            return exception.getMessage();
        }

        m = System.currentTimeMillis() - m;

        return (!resultStr.equals("Результат пошуку по заданим параметрам:\n") ? resultStr + "\nВитрачено " + m + " мілісекунд" : "Нічого не знайдено!");
    }
}

