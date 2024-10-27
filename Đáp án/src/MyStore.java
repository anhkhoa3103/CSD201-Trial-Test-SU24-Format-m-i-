/* This program contains 2 parts: (1) and (2)
   YOUR TASK IS TO COMPLETE THE PART  (2)  ONLY
 */
//(1)==============================================================
import java.util.*;
import java.io.*;

class FruitList {

    Node head, tail;

    FruitList() {
        head = tail = null;
    }

    boolean isEmpty() {
        return (head == null);
    }

    void clear() {
        head = tail = null;
    }

    void loadDataFruit(int k) //do not edit this function
    {
        String[] a = Lib.readLineToStrArray("data.txt", k);
        int[] b = Lib.readLineToIntArray("data.txt", k + 1);
        int[] c = Lib.readLineToIntArray("data.txt", k + 2);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            addLast(a[i], b[i], c[i]);
        }
    }

    void addLast(String type, int amount, int price) {
        //You should write here appropriate statements to complete this function.
        //--------------------------------------------------------
        Fruit newFruit = new Fruit(type, amount, price);
        if (isEmpty()) {
            head = tail = new Node(newFruit);
            return;
        }
        Node newNode = new Node(newFruit);
        tail.next = newNode;
        tail = newNode;
        //---------------------------------------------------------
    }

}

class RequestQueue {

    Node front, rear;

    RequestQueue() {
        front = rear = null;
    }

    boolean isEmpty() {
        return (front == null);
    }

    void clear() {
        front = rear = null;
    }

    void loadDataRequest(int k) //do not edit this function
    {
        String[] a = Lib.readLineToStrArray("data.txt", k + 3);
        int[] b = Lib.readLineToIntArray("data.txt", k + 4);
        int n = a.length;
        for (int i = 0; i < n; i++) {
            enQueue(a[i], b[i]);
        }
    }

    void enQueue(String type, int amount) {
        //You should write here appropriate statements to complete this function.
        //--------------------------------------------------------
        Fruit newFruit = new Fruit(type, amount);
        if (isEmpty()) {
            front = rear = new Node(newFruit);
            return;
        }
        Node newNode = new Node(newFruit);
        rear.next = newNode;
        rear = newNode;
        //---------------------------------------------------------
    }

    Fruit deQueue() {
        Fruit tmp = new Fruit();
        //You should write here appropriate statements to complete this function.
        //--------------------------------------------------------
        tmp = front.info;
        front = front.next;
        //---------------------------------------------------------
        return tmp;
    }

}

class MyStore {

    FruitList FList = new FruitList();
    RequestQueue RQueue = new RequestQueue();

    MyStore() {
    }

    void fvisit(Node p, RandomAccessFile f) throws Exception {
        if (p != null) {
            f.writeBytes(p.info + " ");
        }
    }

    void ftraverse(RandomAccessFile f) throws Exception {
        Node p = FList.head;
        f.writeBytes("Data Fruit: ");
        if (p == null) {
            f.writeBytes("Empty");
        }
        while (p != null) {
            fvisit(p, f); // You will use this statement to write information of the node p to the file
            p = p.next;
        }
        f.writeBytes("\r\n");
        f.writeBytes("Request   : ");
        p = RQueue.front;
        if (p == null) {
            f.writeBytes("Empty");
        }
        while (p != null) {
            f.writeBytes("(" + p.info.getType() + "," + p.info.getAmount() + ") ");
            p = p.next;
        }
        f.writeBytes("\r\n");
    }

    void load(int k) throws Exception {
        FList.loadDataFruit(k);
        RQueue.loadDataRequest(k);
    }

    void f1() throws Exception {
        load(1);
        String fname = "f1.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);

        f.close();
    }

    void f2() throws Exception {
        load(1);
        String fname = "f2.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        //You should write here appropriate statements to complete this function.
        //--------------------------------------------------------
        purchasef2();
        //---------------------------------------------------------
        ftraverse(f);
        f.close();
    }

    void purchasef2() {
        Node currF = FList.head;
        Node currR = RQueue.front;
        while (!RQueue.isEmpty()) {
            currR = RQueue.front;
            currF = FList.head;
            String typeR = currR.info.getType();
            int amountR = currR.info.getAmount();
            while (currF != null) {
                String typeF = currF.info.getType();
                int amountF = currF.info.getAmount();
                if (typeR.equals(typeF)) {
                    if (amountR <= amountF) {
                        currF.info.setAmount(amountF - amountR);
                        RQueue.deQueue();
                        return;
                    }
                }
                currF = currF.next;
            }
            RQueue.deQueue();
        }
    }

    void f3() throws Exception {
        load(1);
        String fname = "f3.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        //You should write here appropriate statements to complete this function.
        //--------------------------------------------------------
        purchasef3();
        //---------------------------------------------------------
        ftraverse(f);
        f.close();
    }

    void purchasef3() {
        Node currF = FList.head;
        Node currR = RQueue.front;
        while (!RQueue.isEmpty()) {
            currR = RQueue.front;
            currF = FList.head;
            String typeR = currR.info.getType();
            int amountR = currR.info.getAmount();
            while (currF != null) {
                String typeF = currF.info.getType();
                int amountF = currF.info.getAmount();
                if (typeR.equals(typeF)) {
                    if (amountR <= amountF) {
                        currF.info.setAmount(amountF - amountR);
                        break;
                    }
                }
                currF = currF.next;
            }
            RQueue.deQueue();
        }
    }

    void f4() throws Exception {
        load(1);
        String fname = "f4.txt";
        File g123 = new File(fname);
        if (g123.exists()) {
            g123.delete();
        }
        RandomAccessFile f = new RandomAccessFile(fname, "rw");
        ftraverse(f);
        int S = 0;
        //You should write here appropriate statements to complete this function.
        //--------------------------------------------------------
        S = purchasef4();
        //---------------------------------------------------------
        f.writeBytes("Money     : " + S + " ");
        f.close();
    }

    int purchasef4() {
        int total = 0;
        Node currR = RQueue.front;
        Node currF = FList.head;
        while (currR != null) {
            currF = FList.head;
            String typeR = currR.info.getType();
            while (currF != null) {
                String typeF = currF.info.getType();
                if (typeR.equals(typeF) && currR.info.getAmount() <= currF.info.getAmount()) {
                    total += currF.info.getPrice() * currR.info.getAmount();
                }
                currF = currF.next;
            }
            currR = currR.next;
        }
        return total;
    }

}
