import org.labs.wt.tour.model.Tour;
import org.labs.wt.tour.control.User;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class TourAgencyAppl {
    public static void main(String args[])
    {
        User user = null;
        List<Tour> tourList = new LinkedList<>();
        System.out.println("Вас приветствует турагенство:\n 1 - зайти как пользователь;\n 2 - как турагент;\n\n Введите цифру:\n");
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();

        if (str.length() == 1 && (str.equals("1") || str.equals("2")))
        {
            if(str.equals("1"))
            {
               // System.out.println("Вы выбрали роль пользователя!");
                 user = new User("Alex", false);
                System.out.println("Ваш функционал:\n" +
                        "1 - поиск тура\n0 - выход\n");
            }else {
                user = new User("Alex", true);
                System.out.println("Ваш функционал:\n" +
                        "1 - поиск тура\n2 - удаление тура\n3 - модификация тура\n4 - создание тура\n");
            }
        }else{
            System.exit(0);
        }

        str = sc.nextLine();
        while(!str.equals("0"))
        {
            switch (str){
                case "1":{
                    tourList = user.GetToursList();
                    String[] CountryArr;

                }
            }
            str = sc.nextLine();
        }



    }
}
