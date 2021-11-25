// Your First Program

import OOP2.Provided.Person;
import OOP2.Provided.Status;
import OOP2.Solution.PersonImpl;

import java.util.HashSet;
import java.util.Set;

class hello {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        Person p1 = new PersonImpl(1,"dor1");
        Person p2 = new PersonImpl(2,"dor2");
        Person p3 = new PersonImpl(3,"dor3");
        Person p4 = new PersonImpl(4,"dor4");
        Person p5 = new PersonImpl(5,"dor5");


        Status s1 = p1.postStatus("status 1");
        Status s2 = p1.postStatus("status 2");
        Status s3 = p1.postStatus("status 3");

        s1.like(p2);
        s1.like(p3);
        s2.like(p2);
        s2.like(p3);
        s2.like(p4);
        s3.like(p2);
        s3.like(p2);
        s3.like(p2);
        s3.like(p2);

        Iterable<Status> list = p1.getStatusesPopular();
        for (Status it : list){
            System.out.println(it.getContent());
        }
    }

    public static int id(int x){
        return x;
    }

    public static int inc(int x){
        return x+1;
    }
}