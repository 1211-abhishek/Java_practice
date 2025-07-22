import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Task1 {

    //reverse the String
    String reverseString(String str) {

        StringBuilder rev_str = new StringBuilder();

        for (int i = str.length() - 1; i >= 0; i--) {

            rev_str = rev_str.append(str.charAt(i));
        }
        return rev_str.toString();

    }


    String reverseStringWithotBuilder(String str){
        char[] strarr = str.toCharArray();
        String string = "";
        System.out.println(string);
//        char temp = ' ';
//        int length = str.length();
//        for(int i = 0; i < length / 2; i++){
//
//            temp = strarr[i];
//            strarr[i] = strarr[length - 1 - i];
//            strarr[length - 1 - i] = temp;
//        }

        String revstr = "";

        for (int i = str.length() - 1; i >= 0; i--){

            revstr = revstr + str.charAt(i);
        }
        return  revstr;

    }

    //Reverse the Array
    void revArray(int[] arr) {

        int length = arr.length;
        int temp = arr[0];
        for (int i = 0; i < length / 2; i++) {

            temp = arr[i];
            arr[i] = arr[length - 1 - i];
            arr[length - 1 - i] = temp;
        }
    }

    //sum of all array()
    int sumArray(int[] arr) {

        int length = arr.length;
        int sum = arr[0];

        for (int j : arr) {
            sum = sum + j;
        }
        return sum;
    }

    //reverse the number()
    int revNumber(int num) {

        int revNumber = 0;

        while (num != 0) {

            int dec = num % 10;
            revNumber = revNumber * 10 + dec;
            num = num / 10;
        }
        return revNumber;
    }

    //reverse the number()
    void checkDuplicate(int[] arr) {

       // Set<Integer> integerSet = new HashSet<>();

        for (int i = 0; i < arr.length; i++) {
            int k = 0;
            for (int j = i + 1; j < arr.length; j++) {

                if (arr[i] == arr[j]) {
                    k++;
                    arr[j] = '0';
                }
            }
            if (k >= 1 && arr[i] != '0') {
                System.out.println(arr[i]);
            }
        }
    }

    //prime numbers from 1-100
    void print1To100() {
//int count = 0;
        for (int i = 2; i <= 100; i++) {

//            if(i % 2 == 0 || i % 3 == 0 || i % 5 == 0 ||i % 7 == 0){
//
//            }else{
//                count ++;
//               System.out.println(i + " is prime");
//            }

            int count = 0;
            for (int j = 2; j <= i / 2; j++) {

                if (i % j == 0) {
                    count++;
                    if (count > 1) {
                        break;
                    }
                }
            }
            if (count < 1) {
                System.out.println(i + " is prime");

            }
        }
        //System.out.println(count);
    }

    public static void main(String[] args) {

        Task1 obj = new Task1();

        String str = "abcd";

        String revStr = obj.reverseStringWithotBuilder(str);
        System.out.println("Reverse String : " + revStr);

        int[] arr = {1, 2, 3, 4, 3, 4, 5};
        obj.revArray(arr);

        System.out.print("Reversed Array : [");
        for (int i : arr) {
            System.out.print(" " + i + " ");
        }
        System.out.println("]");

        int sumOfArray = obj.sumArray(arr);
        System.out.println("Sum of all array element is : " + sumOfArray);

        int num = 1234;
        int revNum = obj.revNumber(num);
        System.out.println(revNum);

        obj.checkDuplicate(arr);

        obj.print1To100();
    }
}
