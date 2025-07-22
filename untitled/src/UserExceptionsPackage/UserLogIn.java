package UserExceptionsPackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserLogIn {

    String[] usersArray = {"abhishek", "ajay"};
    List<String> users = new ArrayList<>(Arrays.asList(usersArray));

    boolean checkUser(String userName) throws UserNameException {

        if (users.contains(userName)) {
            return true;
        } else {
            throw new UserNameException("Enter valid username");
        }
    }

    public static void main(String[] args) {

        String userName = "aaaaa";
        boolean isValidUser = false;

        UserLogIn obj = new UserLogIn();

        try {
            isValidUser = obj.checkUser(userName);
        } catch (UserNameException e) {
            System.out.println(e.getMessage());

        }


        if (isValidUser) {
            System.out.println(userName + " is a valid user");
        }

    }
}
