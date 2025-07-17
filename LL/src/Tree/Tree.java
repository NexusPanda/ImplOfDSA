package Tree;

import java.util.Scanner;
public class Tree{
    Node head;
    static class Node{
        int data;
        Node next;
        Node(int d){
            this.data = d;
            this.next = null;
        }
    }

    Tree(){
        this.head = null;
    }

    public void add(int d){
        Node newNode = new Node(d);
        if(head == null){
            head = newNode;
        }
        else{
            Node temp = head;
            while(temp.next != null){
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    public void display(){
        Node cur = head;
        while(cur != null){
            System.out.print(cur.data + "->");
            cur = cur.next;
            if(cur == null){
                System.out.print("null");
            }
        }
    }

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        Tree lt = new Tree();
        int n = s.nextInt();
        for(int i=0;i<n;i++) {
            lt.add(s.nextInt());
        }
        lt.display();
        s.close();
    }
}