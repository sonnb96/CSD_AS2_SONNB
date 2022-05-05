import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class AS2_Main {


    public static void showMenu() throws IOException {
        OperationToProduct act= new OperationToProduct();
        MyList<Product> list= new MyList<>();
        Scanner ip= new Scanner(System.in);
        boolean loop= true;
        do {

            act.outConsoleToFile("Choose one of this options:",true);
            act.outConsoleToFile("Product list:",true);
            act.outConsoleToFile("1. Load data from file and display",true);
            act.outConsoleToFile("2. Input & add to the end.",true);
            act.outConsoleToFile("3. Display data",true);
            act.outConsoleToFile("4. Save product list to file.",true);
            act.outConsoleToFile("5. Search by ID",true);
            act.outConsoleToFile("6. Delete by ID",true);
            act.outConsoleToFile("7. Sort by ID.",true);
            act.outConsoleToFile("8. Convert to Binary",true);
            act.outConsoleToFile("9. Load to stack and display",true);
            act.outConsoleToFile("10. Load to queue and display.",true);
            act.outConsoleToFile("0. Exit",true);
            act.outConsoleToFile("",true);
            act.outConsoleToFile("Choice : ",false);
            int num = ip.nextInt();
            act.outConsoleToFile("",true);
            switch (num) {
                //1. Load data from file and display
                case 1:
                    act.getAllItemsFromFile("data.txt", list);
                    act.displayAll(list);
                    act.outConsoleToFile("Successfully!",true);
                    act.outConsoleToFile("",true);
                    break;
                    //2. Input & add to the end.
                case 2:
                    act.addLast(list);
                    break;
                //3. Display data
                case 3:
                    act.displayAll(list);
                    break;
                //4. Save product list to file.
                case 4:
                    act.writeAllItemsToFile("data.txt",list);
                    break;
                //5. Search by ID
                case 5:
                    act.searchByCode(list);
                    break;
                //6. Delete by ID
                case 6:
                    act.deleteByCode(list);
                    break;
                //7. Sort by ID.
                case 7:
                    act.sortByCode(list);
                    break;
                //8. Convert to Binary
                case 8:

                    Product productHead= list.head.info;
                    int binary=productHead.quantity;
                    int[] result=act.convertToBinary(binary);
                    act.outConsoleToFile("Quantity "+binary+"=> "+ Arrays.toString(result),true);
                    act.outConsoleToFile("",true);
                    break;
                    //9. Load to stack and display
                case 9:
                    MyStack<Product> stack=new MyStack<>();
                    act.getAllItemsFromFile("data.txt", stack);

                    break;
                //10. Load to queue and display.
                case 10:
                    MyQueue<Product> q=new MyQueue<>();
                    act.getAllItemsFromFile("data.txt", q);

                    break;
                    //0. Exit
                case 0:
                    loop=false;
                    act.outConsoleToFile("Thank!!!",true);
                    break;
            }

        }while (loop);
    }

    public static void main(String[] args) throws IOException {
        showMenu();
    }

}
