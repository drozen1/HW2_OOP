// Your First Program

import OOP2.Provided.Person;

import java.util.HashSet;
import java.util.Set;

class hello {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        Set<Integer> likes  =  new HashSet<>();
        likes.add(5);
        System.out.println(likes.size());
        likes.add(5);
        System.out.println(likes.size());
        likes.remove(4);
        System.out.println(likes.size());
    }

    public static int id(int x){
        return x;
    }

    public static int inc(int x){
        return x+1;
    }
}