public class AlgorithmsSort {
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
    public Node<Product> partition(Node<Product> start, Node<Product> end){
        if(start==end||start==null||end==null){
            return start;
        }
        Node<Product> curNode=start;//curNode dùng để trao đổi dữ liệu của phần tử nhỏ hơn pivot
        Node<Product> prevCur=start;//preCur là bố của curNode
        Node<Product> pivot=end;//pivot dùng để xét duyệt
        while (start!=null){
            if(compareID(start.info.bcode,pivot.info.bcode)==1){
                Product temp= start.info;
                start.info= curNode.info;
                curNode.info=temp;
                prevCur=curNode;
                curNode=curNode.next;
            }
            start=start.next;
        }
        Product temp= end.info;
        end.info= curNode.info;
        curNode.info=temp;
        return   prevCur;

    }
    public void quickSort(Node<Product> start, Node<Product> end){
        if(start==end||start==null||start == end.next){
            return;
        }
        Node<Product> partition= partition(start,end);
        quickSort(start,partition);
        if(partition!=null&&partition==start){
            quickSort(partition.next,end);
        }else if(partition.next!=null&&partition.next!=null){
            quickSort(partition.next.next,end);
        }

    }

//Tìm kiếm bằng sắp xếp chèn
    public Node<Product> myInsertSort(Node<Product> head){
        if(head.next==null){
            return head;
        }
        Node current= head.next;
        Node<Product>n= myInsertSort(current);
        Node<Product>a=n;
        boolean logic=false;
        Node<Product>parentHead=null;
        int count=1;
        while (n!=null&&compareID(n.info.bcode,head.info.bcode)==1){
            if(count==1) {
                head.next = n.next;
                n.next = head;
                parentHead = n;
                n = head.next;
            }else {
                head.next = n.next;
                n.next = head;
                parentHead.next = n;
                n = head.next;
                parentHead=parentHead.next;
            }
            count++;
            logic = true;
        }
        if(logic){
            head=a;
            return head;
        }else {
            return   head;
        }

    }
}
