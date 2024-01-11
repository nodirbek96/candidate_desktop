/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package candidate.st;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author nodirbek
 */
public class StaticValues {
    public static String DB_USERNAME="root";
    public static String DB_PASSWORD="1234";
    
    public static Double LOGIN_FONT_VALUE=20.0;
    public static Double MAINPAGE_FONT_VALUE=18.0;
    
    public static Double LOGIN_TITLE_FONT=30.0;
    public static String IMAGE_URL="/images/login.jpeg";
    public static String ADD_USER="Add user";
    public static String ADD_CANDIDATE="Добавить кандидата";
    public static String SEARCH="Поиск: ";
    public static String USERNAME="Имя пользователя";
    public static String TITLE_LOGIN="Авторизоваться";
    public static String PASSWORD="Пароль";
    public static String SUBMIT="Входить";
    public static String FIO="ФИО";
    public static String PHONEORPASS="Телефон и паспорт";
    public static String BIRTHDATE="Дата рождения";
    public static String FIRSTNAME="Firstname";
    public static String LASTNAME="Lastname";
    public static String DEV_USERNAME="admin";
    public static String DEV_PASSWORD="1234";
    
    public static Scene sceneStart;
    public static Stage STAGE;
    public static BorderPane ROOT;
    
    public static  double HEIGHT = Screen.getPrimary().getVisualBounds().getHeight();
    public static  double WIDTH = Screen.getPrimary().getVisualBounds().getWidth();
    
}
