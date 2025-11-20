package constants;

import com.github.javafaker.Faker;

public class Constants {

    public static final String PAGE_URL = "https://stellarburgers.education-services.ru/";
    final static Faker userFaker = new Faker();
    public static final String EMAIL = userFaker.internet().emailAddress();
    public static final String PASSWORD = userFaker.internet().password();
    public static final String NAME = userFaker.name().firstName();
    public static final String INVALID_PASSWORD = userFaker.internet().password(1,5);

}