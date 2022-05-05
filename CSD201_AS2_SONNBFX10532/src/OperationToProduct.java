import java.io.*;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Scanner;

/**
 * This class manages all functions relate to the product
 */
public class OperationToProduct {

    public void outConsoleToFile(String str,boolean logic){
        if(logic) {
            System.out.println(str);
        }else {
            System.out.print(str);
        }
        try(FileWriter file=new FileWriter("./console_output.txt",true);
        PrintWriter printWriter=new PrintWriter(file,true))
        {
            printWriter.println(str);
            printWriter.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void outConsoleToFile(){
            templateProduct();
        try(FileWriter file=new FileWriter("./console_output.txt",true);
            PrintWriter printWriter=new PrintWriter(file,true))
        {
            String t="|%-3s| %-6s|%-8s|%-5s";
            printWriter.format(t,"ID","Title","Quantity","Price");
            printWriter.println();
            printWriter.println("-------------------------------");
            printWriter.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    //Template mẫu để xuất ra màn hình
    public void templateProduct(){
        String t="|%-3s| %-6s|%-8s|%-5s";
        System.out.format(t,"ID","Title","Quantity","Price");
        System.out.println();
        System.out.println("-------------------------------");
    }
    /**
     * Searching and returning the index of product p in the list. If not found
     * return -1.
     *
     * @param p    Product for searching
     * @param list The Linked List
     * @return The index of product p in the list
     */
    public int index(Product p, MyList<Product> list) {
        if(list.isEmpty()){
            throw new EmptyStackException();
        }
        Node<Product> currentNode=list.head;
        //dựa vào id của Product để tìm node trên list
        String find= p.bcode;
        //dùng count để đếm và ghi nhận lại vị trí của node nếu tìm được node
        int count=0;
        boolean logic= true;
        while (currentNode!=null){
            //so sánh product đã cho với product dang duyệt trên list
            if(find.equals(currentNode.info.bcode)){
                logic=false;
                break;
            }
            currentNode=currentNode.next;
            count++;
        }
        if(logic){
            //Không tìm thấy thì trả về -1
            return -1;
        }else{
            return count;
        }

    }

    /**
     * Creating and returning a product with info input from keyboard.
     *
     * @return The product
     */
    public Product createProduct() {
        Scanner ip= new Scanner(System.in);
        Product pro= new Product();
        outConsoleToFile("Input new ID: ",false);
        String bcode= ip.next();
        outConsoleToFile("Input Product's Name: ",false);
        String title=ip.next();
        outConsoleToFile("Input Product's quantity: ",false);
        int quantity= ip.nextInt();
        outConsoleToFile("Input Product's price: ",false);

        double price= ip.nextDouble();
        pro.bcode=bcode;
        pro.title=title;
        pro.quantity=quantity;
        pro.price=price;

        outConsoleToFile("",true);
        return pro;
        }
// readFile đọc dữ liệu từ một file
    public String[] readFile(String fileName, String[] str) throws FileNotFoundException {
        File f = new File(fileName);
        Scanner sc = new Scanner(f); //Sử dụng sacnner để đọc và duyệt file
        int i=0;
        while (sc.hasNextLine()) {
            String c=sc.nextLine();
            //Nếu có một dòng trống thì scanner tuwjj ngắt và không đọc file nữa
            if(c.isEmpty()){
                break;
            }
            str[i] = c;
            i++;
        }
        sc.close();
        return str;
    }

    //Phương thức countFloat dùng để lấy số đếm dòng trong file
    public int countProduct(String fileName) throws FileNotFoundException {
        File f = new File(fileName);
        Scanner sc = new Scanner(f);
        int i = 0;
        //duyệt từng dòng để đếm số dòng có ký tự, không đếm dòng trống
        while (sc.hasNextLine()) {
           if(sc.nextLine().isEmpty()){
               break;
           }
            i++;
        }
        sc.close();
        return i;
    }
    /**
     * Reading all products from the file and insert them to the list at tail.
     *
     * @param fileName The file name of the file
     * @param list     The Linked List contains all products that read from file
     */
    public void getAllItemsFromFile(String fileName, MyList<Product> list) throws FileNotFoundException {
        //đếm số dòng
        int countP = countProduct(fileName);
        //tạo bucketPd để chứa mỗi dòng là một phần tử mảng
        String[] bucketPd = new String[countP];
        bucketPd = readFile(fileName, bucketPd);
        //tạo infoP để chứa thông tin ID, title, quantity, price
        String[] infoP = new String[4];
        //duyệt từng phần tử bucketPd, mỗi một phần tử là một đối tượng
        for (String product : bucketPd) {
            infoP = product.split("\\s+"+"\\|"+"\\s+");
            Product newProduct = new Product(infoP[0], infoP[1], Integer.parseInt(infoP[2]), Float.parseFloat(infoP[3]));
            list.insertToTail(newProduct);
        }
    }



    /**
     * Reading all products from the file and insert them to the stack.
     *
     * @param fileName The file name of the file
     * @param stack     The Stack contains all products that read from file
     */
    public void getAllItemsFromFile(String fileName, MyStack<Product> stack) throws FileNotFoundException {
        //đếm số dòng
        int countP= countProduct(fileName);
        //tạo bucketPd để chứa mỗi dòng là một phần tử mảng
        String[] bucketPd= new String[countP];
        bucketPd= readFile(fileName,bucketPd);
        //tạo infoP để chứa thông tin ID, title, quantity, price
        String[] infoP= new String[4];
        //duyệt từng phần tử bucketPd, mỗi một phần tử là một đối tượng
        for(String product:bucketPd){
            infoP=product.split("\\s+"+"\\|"+"\\s+");
            //taọ ra product mới chứa thông tin từng phần tử của mảng infoP
            Product newProduct= new Product(infoP[0],infoP[1],Integer.parseInt(infoP[2]),Float.parseFloat(infoP[3]));
            stack.addStack(newProduct);
        }
        //In ra màn hình
        outConsoleToFile();
        while (!stack.isEmpty()){
            Product product= new Product();
            product= stack.pop();
            outConsoleToFile(product.toString(),true);
        }
        outConsoleToFile("",true);
        outConsoleToFile("Successfully!",true);
        outConsoleToFile("",true);
    }

    /**
     * Reading all products from the file and insert them to the queue.
     *
     * @param fileName The file name of the file
     * @param queue     The Queue contains all products that read from file
     */
    public void getAllItemsFromFile(String fileName, MyQueue<Product> queue) throws FileNotFoundException {
        //đếm số dòng
        int countP= countProduct(fileName);
        //tạo bucketPd để chứa mỗi dòng là một phần tử mảng
        String[] bucketPd= new String[countP];
        bucketPd= readFile(fileName,bucketPd);
        //tạo infoP để chứa thông tin ID, title, quantity, price
        String[] infoP= new String[4];
        //duyệt từng phần tử bucketPd, mỗi một phần tử là một đối tượng
        for(String product:bucketPd){
            infoP=product.split("\\s+"+"\\|"+"\\s+");
            //taọ ra product mới chứa thông tin từng phần tử của mảng infoP
            Product newProduct= new Product(infoP[0],infoP[1],Integer.parseInt(infoP[2]),Double.parseDouble(infoP[3]));
            queue.enqueue(newProduct);
        }
        outConsoleToFile();
        //In ra màn hình
        while (!queue.isEmpty()){
            Product product= new Product();
            product= queue.dequeue();
            outConsoleToFile(product.toString(),true);
        }
        outConsoleToFile("",true);
        outConsoleToFile("Successfully!",true);
        outConsoleToFile("",true);
    }

    /**
     * Adding a product to the list, info of the product input from keyboard.
     *
     * @param list The Linked list
     */
    public void addLast(MyList<Product> list) {
        Product product= new Product();
        product= product.createProduct();
        list.insertToTail(product);
    }

    /**
     * Printing all prodcuts of the list to console screen
     *
     * @param list
     */
    public void displayAll(MyList<Product> list) {
        if(list.isEmpty()){
            return;
        }
        outConsoleToFile();
        Node<Product> current = list.head;
        while (current!=null){
            outConsoleToFile(current.info.toString(),true);
            current=current.next;
        }
        outConsoleToFile("",true);
    }

    /**
     * Writing all products from the list to the file
     *
     * @param fileName Input file name
     * @param list     Input Linked list
     */
    public void writeAllItemsToFile(String fileName, MyList<Product> list) throws IOException {
        File file = new File(fileName);
        //tạo ra đối tượng để ghi vào file
        if (list.isEmpty()) {
            return;
        }
       try( FileWriter fileWriter = new FileWriter(file, true);
        PrintWriter printWriter = new PrintWriter(fileWriter, true);) {


           Node<Product> current = list.head;
           Product p = new Product();
           printWriter.println();
           //duyệt từng phần tử trong list để ghi vào file
           while (current != null) {
               p = current.info;
               printWriter.println(p.toString());
               current = current.next;
           }
           outConsoleToFile("Successfully!",true);
           outConsoleToFile("",true);
           printWriter.close();
           fileWriter.close();
       }catch (Exception e){
           e.printStackTrace();
       }
    }

    /**
     * Searching product by ID input from keyboard.
     *
     * @param list
     */
    public void searchByCode(MyList<Product> list) {
        Scanner ip = new Scanner(System.in);
        //Nếu Id có chữ cái dầu thì không cần quan trong chữ hoa vơi chữ thường
        outConsoleToFile("Input the ID to search = ",false);
        String id=ip.next();
        // Tìm node trong MyList theo ID đã nhập
        Product pro= infoNode(list,id);
        outConsoleToFile();
        //Nếu như không tìm thấy ID thì in kết quả là -1
        if(pro==null){
            outConsoleToFile("-1",true);
        }else {
           outConsoleToFile(pro.toString(),true);
        }
        outConsoleToFile("\n",true);
    }
//Tìm node trong Mylist dựa vào ID
    public Product infoNode(MyList<Product> list, String value){
        if(list.isEmpty()){
            return null;
        }
        Node<Product> current= list.head;
        while (current!=null){
            Product product= current.info;
            //Nếu Id có chữ cái dầu thì không cần quan trong chữ hoa vơi chữ thường
            if(product.bcode.equalsIgnoreCase(value)){
                return current.info;
            }
            current=current.next;
        }
        return null;
    }
    /**
     * Deleting first product that has the ID input from keyboard from the list.
     *
     * @param list
     */
    public void deleteByCode(MyList<Product> list) {
        if(list.isEmpty()){
            return ;
        }
        Scanner ip = new Scanner(System.in);
        //Phải nhập đúng id cần tìm không phân biệt chữ hoa và không tìm kiếm tương đương
        outConsoleToFile("Input the ID to delete = ",false);
        String id=ip.next();

        Node<Product> current= list.head;
        Product product= current.info;
        //Nếu node dầu tiên bằng với ID cần tìm thì xóa
        if(product.bcode.equals(id)){
            list.head=current.next;
            outConsoleToFile("Deleted!",true);
            outConsoleToFile("",true);
            return;
        }
        //Nếu node đầu không phải ID cần tìm thì duyệt hết các node còn lại
        Node<Product> parentNode =current;
        current=current.next;
        while (current!=null){
            product= current.info;
            //Nếu Id có chữ cái dầu thì không cần quan trong chữ hoa vơi chữ thường
            if(product.bcode.equalsIgnoreCase(id)){
                parentNode.next=current.next;
                outConsoleToFile("Deleted!",true);
                outConsoleToFile("",true);
                return;
            }
            parentNode=current;
            current=current.next;
        }
        //Nếu không tìm thấy ID cần xóa thì trả về fail
        outConsoleToFile("Fail",true);
        outConsoleToFile("",false);
    }
    //so sánh hai chuỗi bằng bằng cơ chế mã ACII
    public int compareID(String str1, String str2){
        char t1=str1.charAt(0);
        char t2=str2.charAt(0);

        //TH1 cả hai đều là chữ
        if(!Character.isDigit(t1)&&!Character.isDigit(t2)){
           String tr1=str1.substring(1,3);
           String tr2=str2.substring(1,3);
           if(Integer.parseInt(tr1)<Integer.parseInt(tr2)) {
               //Nếu Str1<Str2 thì sẽ trả về 1
               return 1;
           }
           //TH 2 cả 2 đều là số
        }else if(Character.isDigit(t1)&&Character.isDigit(t2)){
            if(Integer.parseInt(str1)<Integer.parseInt(str2)){
                //Nếu Str1<Str2 thì sẽ trả về 1
                return 1;
            }
            //TH 3 một trong hai là số hoặc là chữ
        }else {
            if((int)t1<(int)t2){
                //Nếu Str1<Str2 thì sẽ trả về 1
                return 1;
            }
        }
        ////Nếu Str1>Str2 thì sẽ trả về -1
        return -1;
    }
    /**
     * Sorting products in linked list by ID
     *
     * @param list The Linked list
     */
    public void sortByCode(MyList<Product> list) {
        AlgorithmsSort quickSortList=new AlgorithmsSort();
        quickSortList.quickSort(list.head,list.tail);
        outConsoleToFile("Successfully!",true);
        outConsoleToFile("",true);
    }


    /**
     * Adding new product to head of Linked List. The info input from keyboard.
     *
     * @param list The linked list
     */
    public void addFirst(MyList<Product> list) {
        Scanner ip=new Scanner(System.in);
        Product product= new Product();
        product.createProduct();
        list.insertToHead(product);
    }

    /**
     * Convert a decimal to an array of binary. Example: input i = 18 -> Output =
     * {0, 1, 0, 0, 0, 1}
     *
     * @param i Input decimal number
     * @return Array of binary numbers
     */

    public int[] convertToBinary(int i){
        MyStack<Integer> stack=new MyStack<>();
        int[] arrBinary=convertToBinaryByRecursion(i,stack);
        return arrBinary;
    }
    public int[] convertToBinaryByRecursion(int i,MyStack<Integer> stack) {

        if(i==0) {
            ArrayList<Integer> ar= new ArrayList<>();
            while (!stack.isEmpty()){
                ar.add(stack.pop());
            }
            int[] num= new int[ar.size()];
            int count=0;
            for(int item:ar){
                num[count++]=item;
            }
            return num;
        }
        stack.addStack(i%2);
        i/=2;
        return convertToBinaryByRecursion(i,stack);
    }

    /**
     * Deleting the product at position
     *
     * @param list The Linked List
     * @param pos  The position of product to be deleted
     */
    public void deleteAtPosition(MyList<Product> list, int pos) {
        if(list.isEmpty()){
            return;
        }
        Node currentNode= list.head;
        Node parCurNode=null;
        int count=0;
        while (currentNode!=null){
            if(pos==0){
                list.head= list.head.next;
            }else if(pos==count){
                parCurNode.next=currentNode.next;
                outConsoleToFile("Successfully",true);
                return;
            }
            parCurNode=currentNode;
            count++;
            currentNode=currentNode.next;
        }

        if(pos>count){
            System.out.println("Not Found");
        }
    }


}
