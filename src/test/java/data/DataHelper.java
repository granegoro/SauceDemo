package data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;
import java.util.Random;

public class DataHelper {

    static Faker faker = new Faker(new Locale("en"));

    static Faker fakerCyrillic = new Faker(new Locale("ru"));

    public static String generateValidFirstName() {
        return (faker.address().firstName());
    }

    public static String generateValidLastName() {
        return (faker.address().lastName());
    }

    public static String generateCyrillicFirstName() {
        return (fakerCyrillic.address().firstName());
    }

    public static String generateCyrillicLastName() {
        return (fakerCyrillic.address().lastName());
    }

    public static String generatePostalCode() {
        return (faker.address().zipCode());
    }

    public static String generateSpace() {
        return (" ");
    }

    public static String generateSymbols() {
        var symbols = new String[]{
                "*%&^@", "#@$", "!@%"
        };
        return symbols[new Random().nextInt(symbols.length)];
    }

    public static String getPassword() {
        return ("secret_sauce");
    }

    public static String getStandardUserLogin() {
        return ("standard_user");
    }

//    public static String getLockedOutUserLogin() {
//        return ("locked_out_user");
//    }

    public static ItemInfo get0ItemAddInfo() {
        return new ItemInfo("add-to-cart-sauce-labs-bike-light");
    }

    public static ItemInfo get1ItemAddInfo() {
        return new ItemInfo("add-to-cart-sauce-labs-bolt-t-shirt");
    }

    public static ItemInfo get2ItemAddInfo() {
        return new ItemInfo("add-to-cart-sauce-labs-onesie");
    }

    public static ItemInfo get3ItemAddInfo() {
        return new ItemInfo("add-to-cart-test.allthethings()-t-shirt-(red)");
    }

    public static ItemInfo get4ItemAddInfo() {
        return new ItemInfo("add-to-cart-sauce-labs-backpack");
    }

    public static ItemInfo get5ItemAddInfo() {
        return new ItemInfo("-sauce-labs-fleece-jacket");
    }

    public static ItemInfo get0ItemRemoveInfo() {
        return new ItemInfo("remove-sauce-labs-bike-light");
    }

    public static ItemInfo get1ItemRemoveInfo() {
        return new ItemInfo("remove-sauce-labs-bolt-t-shirt");
    }

    public static ItemInfo get2ItemRemoveInfo() {
        return new ItemInfo("remove-sauce-labs-onesie");
    }

    public static ItemInfo get3ItemRemoveInfo() {
        return new ItemInfo("remove-test.allthethings()-t-shirt-(red)");
    }

    public static ItemInfo get4ItemRemoveInfo() {
        return new ItemInfo("remove-sauce-labs-backpack");
    }

    public static ItemInfo get5ItemRemoveInfo() {
        return new ItemInfo("remove-sauce-labs-fleece-jacket");
    }

    public static class Auth {
        private Auth() {
        }

        public static UserInfo getStandardUser() {

            return new UserInfo(
                    getStandardUserLogin(),
                    getPassword()
            );
        }

//        public static UserInfo getLockedOutUser() {
//
//            return new UserInfo(
//                    getLockedOutUserLogin(),
//                    getPassword()
//            );
//        }
    }

    public static class Order {
        private Order() {
        }

        public static OrderInfo getValidOrderInfo() {

            return new OrderInfo(
                    generateValidFirstName(),
                    generateValidLastName(),
                    generatePostalCode()
            );
        }

        public static OrderInfo getCyrillicOrderInfo() {

            return new OrderInfo(
                    generateCyrillicFirstName(),
                    generateCyrillicLastName(),
                    generatePostalCode()
            );
        }

        public static OrderInfo getSpacesOrderInfo() {

            return new OrderInfo(
                    generateSpace(),
                    generateSpace(),
                    generateSpace()
            );
        }

        public static OrderInfo getNumericFirstNameOrderInfo() {

            return new OrderInfo(
                    generateSpace(),
                    generateValidLastName(),
                    generatePostalCode()
            );
        }

        public static OrderInfo getNumericLastNameOrderInfo() {

            return new OrderInfo(
                    generateValidFirstName(),
                    generateSpace(),
                    generatePostalCode()
            );
        }

        public static OrderInfo getLettersInPostalCodeOrderInfo() {

            return new OrderInfo(
                    generateValidFirstName(),
                    generateValidLastName(),
                    generateValidLastName()
            );
        }

        public static OrderInfo getSymbolicFirstNameOrderInfo() {

            return new OrderInfo(
                    generateSymbols(),
                    generateValidLastName(),
                    generatePostalCode()
            );
        }

        public static OrderInfo getSymbolicLastNameOrderInfo() {

            return new OrderInfo(
                    generateValidFirstName(),
                    generateSymbols(),
                    generatePostalCode()
            );
        }

        public static OrderInfo getSymbolicPostalCodeOrderInfo() {

            return new OrderInfo(
                    generateValidFirstName(),
                    generateValidLastName(),
                    generateSymbols()
            );
        }
    }


    @Value
    public static class ItemInfo {
        String testId;
    }

    @Value
    public static class UserInfo {
        String login;
        String password;
    }

    @Value
    public static class OrderInfo {
        String firstName;
        String lastName;
        String postalCode;
    }

}
