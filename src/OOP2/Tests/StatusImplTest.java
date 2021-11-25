package OOP2.Tests;

import OOP2.Provided.ConnectionAlreadyExistException;
import OOP2.Provided.SamePersonException;
import OOP2.Solution.PersonImpl;
import OOP2.Solution.StatusImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import OOP2.Provided.Status;
import OOP2.Provided.Person;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class StatusImplTest {
    @Test
    public void test1()
    {
        PersonImpl p1 = new PersonImpl(1,"dor1");
        Status s11 = p1.postStatus("p1s1");
        Status s12 = p1.postStatus("p1s2");

        PersonImpl p2 = new PersonImpl(2,"dor2");
        Status s21 = p2.postStatus("p2s1");
        Status s22 = p2.postStatus("p2s2");
        Status s23 = p2.postStatus("p2s3");

        PersonImpl p3 = new PersonImpl(3,"dor3");

        PersonImpl p4 = new PersonImpl(4,"dor4");
        Status s41 = p4.postStatus("p4s1");

        PersonImpl p5 = new PersonImpl(5,"dor5");
        Status s51 = p5.postStatus("p5s1");
        Status s52 = p5.postStatus("p5s2");
        Status s53 = p5.postStatus("p5s3");

        /**
         * status
         */

        // checking getId
        Assertions.assertEquals(p3.getId(), 3);
        Assertions.assertEquals(p2.getId(), 2);
        Assertions.assertEquals(p5.getId(), 5);

        // checking getPublisher
        Assertions.assertEquals(s11.getPublisher().equals(p1), true);
        Assertions.assertEquals(s11.getPublisher().getId(), 1);
        Assertions.assertEquals(s41.getPublisher().equals(p4), true);
        Assertions.assertEquals(s51.getPublisher().getId(), 5);
        Assertions.assertEquals(s41.getPublisher().equals(p2), false);

        //getContent
        Assertions.assertEquals(s11.getContent(),"p1s1");
        Assertions.assertEquals(s41.getContent(),"p4s1");
        Assertions.assertEquals(s22.getContent(),"p2s2");
        Assertions.assertEquals(s51.getContent(),"p5s1");

        //like
        Assertions.assertEquals(s11.getLikesCount(),0);
        s11.like(p1);
        Assertions.assertEquals(s11.getLikesCount(),1);
        s11.like(p1);
        Assertions.assertEquals(s11.getLikesCount(),1);
        s11.like(p2);
        Assertions.assertEquals(s11.getLikesCount(),2);
        s21.like(p2);
        Assertions.assertEquals(s21.getLikesCount(),1);
        s23.like(p1);
        s23.like(p2);
        s23.like(p3);
        s23.like(p4);
        Assertions.assertEquals(s23.getLikesCount(),4);
        s22.like(p1);
        s22.like(p2);
        s22.like(p3);
        s22.like(p4);
        Assertions.assertEquals(s22.getLikesCount(),4);

        // unlike
        s22.unlike(p4);
        Assertions.assertEquals(s22.getLikesCount(),3);
        s22.unlike(p4);
        Assertions.assertEquals(s22.getLikesCount(),3);
        s22.unlike(p5);
        Assertions.assertEquals(s22.getLikesCount(),3);
        s22.unlike(p1);
        Assertions.assertEquals(s22.getLikesCount(),2);
        s22.like(p1);
        s22.like(p2);
        s22.like(p3);
        s22.like(p4);
        Assertions.assertEquals(s22.getLikesCount(),4);
        Assertions.assertEquals(s51.getLikesCount(),0);
        s51.unlike(p1);
        Assertions.assertEquals(s51.getLikesCount(),0);

        // equals
        Assertions.assertEquals(s11.equals(s11),true);
        Assertions.assertEquals(s11.equals(s12),false);
        Assertions.assertEquals(s12.equals(s11),false);
        Assertions.assertEquals(s51.equals(s51),true);
        Assertions.assertEquals(s21.equals(s11),false);
        Assertions.assertEquals(s11.equals(s21),false);

        /**
         * person
         */

        //getID
        Assertions.assertEquals(p1.getId(), 1);
        Assertions.assertEquals(p1.getId() == 2, false);
        Assertions.assertEquals(p2.getId(), 2);

        //getName
        Assertions.assertEquals(p1.getName(), "dor1");
        Assertions.assertEquals(p2.getName(), "dor2");
        Assertions.assertEquals(p3.getName(), "dor3");
        Assertions.assertEquals(p1.getName().equals("dor2"), false);

        //addFriends
        Assertions.assertEquals(p1.getFriends().size(), 0);
        Assertions.assertEquals(p2.getFriends().size(), 0);
        Assertions.assertEquals(p3.getFriends().size(), 0);
        try {
            p1.addFriend(p2);
            Assertions.assertEquals(p1.getFriends().size(), 1);
            Assertions.assertEquals(p2.getFriends().size(), 1);
            p1.addFriend(p3);
            Assertions.assertEquals(p1.getFriends().size(), 2);
            Assertions.assertEquals(p2.getFriends().size(), 1);
            Assertions.assertEquals(p3.getFriends().size(), 1);
            p2.addFriend(p3);
            Assertions.assertEquals(p1.getFriends().size(), 2);
            Assertions.assertEquals(p2.getFriends().size(), 2);
            Assertions.assertEquals(p3.getFriends().size(), 2);

        } catch (ConnectionAlreadyExistException e) {
            Assertions.assertEquals(0, 1);
        } catch (SamePersonException e) {
            Assertions.assertEquals(0, 1);
        }
        try {
            p1.addFriend(p3);
        }
        catch (ConnectionAlreadyExistException e) {
           System.out.println("pass ConnectionAlreadyExistException");
        } catch (SamePersonException e) {
            Assertions.assertEquals(0, 2);
        }

        try {
            p1.addFriend(p1);
        }
        catch (ConnectionAlreadyExistException e) {
            Assertions.assertEquals(0, 2);
        } catch (SamePersonException e) {
            System.out.println("pass ConnectionAlreadyExistException");
        }

        Assertions.assertEquals(p1.getFriends().size(), 2);
        Assertions.assertEquals(p2.getFriends().size(), 2);
        Assertions.assertEquals(p3.getFriends().size(), 2);

        Collection <Person> list = p1.getFriends();
        Assertions.assertEquals(list.contains(p2), true);
        Assertions.assertEquals(list.contains(p3), true);
        Assertions.assertEquals(list.contains(p4), false);




    }

}