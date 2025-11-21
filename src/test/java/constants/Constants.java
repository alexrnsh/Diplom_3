package constants;

import com.github.javafaker.Faker;

public class Constants {

    public static final String PAGE_URL = "https://stellarburgers.education-services.ru/";
    public final static Faker faker = new Faker();
    public static final String EMAIL = faker.internet().emailAddress();
    public static final String PASSWORD = faker.internet().password();
    public static final String NAME = faker.name().firstName();
    public static final String INVALID_PASSWORD = faker.internet().password(1,5);

}