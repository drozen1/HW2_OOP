package OOP2.Tests;

import java.util.Iterator;

import OOP2.Provided.*;
import OOP2.Solution.PersonImpl;
import junit.framework.Assert;

import org.junit.Test;

import OOP2.Solution.FaceOOPImpl;
import org.junit.jupiter.api.Assertions;

public class FaceOOPImplTest {
    @Test
    public void rankTest() throws PersonNotInSystemException, SamePersonException, ConnectionAlreadyExistException, ConnectionDoesNotExistException, PersonAlreadyInSystemException {
        FaceOOP fo = new FaceOOPImpl();
        int counter = 0;
        Assertions.assertEquals(fo.size(), 0);
        try {
            fo.joinFaceOOP(1, "karen1");
        } catch (PersonAlreadyInSystemException e) {
            Assertions.fail();
        }
        Assertions.assertEquals(fo.size(), 1);
        try {
            fo.joinFaceOOP(1, "karen1");
        } catch (PersonAlreadyInSystemException e) {
            counter++;
        }
        Assertions.assertEquals(counter, 1);
        try {
            fo.joinFaceOOP(2, "karen2");
            fo.joinFaceOOP(3, "karen3");
            fo.joinFaceOOP(4, "karen4");
            fo.joinFaceOOP(5, "karen5");
        } catch (PersonAlreadyInSystemException e) {
            Assertions.fail();
        }
        Assertions.assertEquals(fo.size(), 5);

        try {
            fo.getUser(8);
        } catch (PersonNotInSystemException e) {
            counter++;
        }
        Assertions.assertEquals(counter, 2);

        Person p1 = fo.getUser(1);
        Person p2 = fo.getUser(2);
        Person p3 = fo.getUser(3);
        Person p4 = fo.getUser(4);
        Person p5 = fo.getUser(5);
        Person pNot = new PersonImpl(6, "karen6");

        Assertions.assertEquals(p1.getId(),1);
        Assertions.assertEquals(p1.getName(),"karen1");
        Assertions.assertEquals(p2.getId(),2);
        Assertions.assertEquals(p2.getName(),"karen2");
        Assertions.assertEquals(p3.getId(),3);
        Assertions.assertEquals(p3.getName(),"karen3");
        Assertions.assertEquals(p4.getId(),4);
        Assertions.assertEquals(p4.getName(),"karen4");
        Assertions.assertEquals(p5.getId(),5);
        Assertions.assertEquals(p5.getName(),"karen5");

        try {
            Assertions.assertEquals(fo.rank(p1,p1), 0);
        } catch (ConnectionDoesNotExistException e) {
            Assertions.fail();
        }
        try {
            fo.rank(p1,p2);
        } catch (ConnectionDoesNotExistException e) {
            counter++;
        }
        catch (PersonNotInSystemException e){
            Assertions.fail();
        }
        Assertions.assertEquals(counter, 3);

        try {
            fo.rank(p1,pNot);
        } catch (ConnectionDoesNotExistException e) {
            Assertions.fail();
        }
        catch (PersonNotInSystemException e){
            counter++;
        }
        Assertions.assertEquals(counter, 4);

        try {
            fo.rank(pNot, p1);
        } catch (ConnectionDoesNotExistException e) {
            Assertions.fail();
        }
        catch (PersonNotInSystemException e){
            counter++;
        }
        Assertions.assertEquals(counter, 5);

        try {
            fo.addFriendship(p1, p1);
        } catch (ConnectionAlreadyExistException e) {
            Assertions.fail();
        } catch (SamePersonException e) {
            counter++;
        }
        Assertions.assertEquals(counter, 6);

        fo.addFriendship(p1, p2);
        fo.addFriendship(p1, p3);
        fo.addFriendship(p3, p5);
        fo.addFriendship(p4, p2);

        try {
            fo.addFriendship(p2, p1);
        } catch (ConnectionAlreadyExistException e) {
            counter++;
        } catch (SamePersonException e) {
            Assertions.fail();
        }
        Assertions.assertEquals(counter, 7);

        try {
            fo.addFriendship(pNot, p1);
        } catch (ConnectionAlreadyExistException e) {
            Assertions.fail();
        } catch (SamePersonException e) {
            Assertions.fail();
        } catch (PersonNotInSystemException e){
            counter++;
        }
        Assertions.assertEquals(counter, 8);

        try {
            fo.addFriendship(p1, pNot);
        } catch (ConnectionAlreadyExistException e) {
            Assertions.fail();
        } catch (SamePersonException e) {
            Assertions.fail();
        } catch (PersonNotInSystemException e){
            counter++;
        }
        Assertions.assertEquals(counter, 9);

        Assertions.assertEquals(fo.rank(p1,p2), 1);
        Assertions.assertEquals(fo.rank(p2,p1), 1);

        Assertions.assertEquals(fo.rank(p1,p3), 1);
        Assertions.assertEquals(fo.rank(p3,p1), 1);

        Assertions.assertEquals(fo.rank(p3,p2), 2);
        Assertions.assertEquals(fo.rank(p2,p3), 2);

        Assertions.assertEquals(fo.rank(p1,p5), 2);
        Assertions.assertEquals(fo.rank(p5,p1), 2);

        Assertions.assertEquals(fo.rank(p1,p4), 2);
        Assertions.assertEquals(fo.rank(p4,p1), 2);

        Assertions.assertEquals(fo.rank(p5,p4), 4);
        Assertions.assertEquals(fo.rank(p4,p5), 4);
        fo.addFriendship(p5, p2);
        Assertions.assertEquals(fo.rank(p5,p4), 2);
        Assertions.assertEquals(fo.rank(p4,p5), 2);
        fo.addFriendship(p2, p3);
        Assertions.assertEquals(fo.rank(p2,p3), 1);
        Assertions.assertEquals(fo.rank(p3,p2), 1);

        Assertions.assertEquals(fo.rank(p1,p4), 2);
        Assertions.assertEquals(fo.rank(p4,p1), 2);

        fo.joinFaceOOP(7, "karen7");
        Person p7 = fo.getUser(7);

        fo.addFriendship(p4, p7);
        Assertions.assertEquals(fo.rank(p1,p7), 3);
        Assertions.assertEquals(fo.rank(p7,p1), 3);

        Assertions.assertEquals(3, fo.rank(p7,p3));
        Assertions.assertEquals(3, fo.rank(p3,p7));

        fo.addFriendship(p2, p7);

        Assertions.assertEquals(1, fo.rank(p7,p2));
        Assertions.assertEquals(1, fo.rank(p2,p7));

        Assertions.assertEquals(fo.rank(p1,p7), 2);
        Assertions.assertEquals(fo.rank(p7,p1), 2);

        Assertions.assertEquals(2, fo.rank(p7,p3));
        Assertions.assertEquals(2, fo.rank(p3,p7));

        fo.joinFaceOOP(8, "karen8");
        Person p8 = fo.getUser(8);
        fo.addFriendship(p8, p5);
        fo.addFriendship(p8, p7);

        Assertions.assertEquals(2, fo.rank(p8,p4));
        Assertions.assertEquals(2, fo.rank(p4,p8));

        Assertions.assertEquals(2, fo.rank(p8,p2));
        Assertions.assertEquals(2, fo.rank(p2,p8));

        Assertions.assertEquals(3, fo.rank(p8,p1));
        Assertions.assertEquals(3, fo.rank(p1,p8));

        Assertions.assertEquals(2, fo.rank(p7,p1));
        Assertions.assertEquals(2, fo.rank(p1,p7));

        fo.joinFaceOOP(9, "karen9");
        Person p9 = fo.getUser(9);
        fo.joinFaceOOP(10, "karen10");
        Person p10 = fo.getUser(10);
        fo.joinFaceOOP(11, "karen11");
        Person p11 = fo.getUser(11);
        fo.joinFaceOOP(12, "karen12");
        Person p12 = fo.getUser(12);

        fo.addFriendship(p3, p9);
        fo.addFriendship(p11, p9);
        fo.addFriendship(p10, p9);
        fo.addFriendship(p10, p12);

        Assertions.assertEquals(4, fo.rank(p7,p11));
        Assertions.assertEquals(4, fo.rank(p11,p7));

        Assertions.assertEquals(3, fo.rank(p12,p11));
        Assertions.assertEquals(3, fo.rank(p11,p12));

        Assertions.assertEquals(5, fo.rank(p12,p4));
        Assertions.assertEquals(5, fo.rank(p4,p12));

        Assertions.assertEquals(5, fo.rank(p12,p8));
        Assertions.assertEquals(5, fo.rank(p8,p12));

        Assertions.assertEquals(4, fo.rank(p10,p8));
        Assertions.assertEquals(4, fo.rank(p8,p10));

        fo.addFriendship(p7, p12);

        Assertions.assertEquals(4, fo.rank(p7,p11));
        Assertions.assertEquals(4, fo.rank(p11,p7));

        Assertions.assertEquals(3, fo.rank(p12,p11));
        Assertions.assertEquals(3, fo.rank(p11,p12));

        Assertions.assertEquals(2, fo.rank(p12,p4));
        Assertions.assertEquals(2, fo.rank(p4,p12));

        Assertions.assertEquals(2, fo.rank(p12,p8));
        Assertions.assertEquals(2, fo.rank(p8,p12));

        Assertions.assertEquals(3, fo.rank(p10,p8));
        Assertions.assertEquals(3, fo.rank(p8,p10));

        Assertions.assertEquals(3, fo.rank(p9,p7));
        Assertions.assertEquals(3, fo.rank(p7,p9));


    }



    @Test
    public void GetIteratorTest() throws PersonNotInSystemException, SamePersonException, ConnectionAlreadyExistException, ConnectionDoesNotExistException, PersonAlreadyInSystemException {
        FaceOOP fo = new FaceOOPImpl();
        int counter = 0;
        fo.joinFaceOOP(1, "karen1");
        fo.joinFaceOOP(2, "karen2");
        fo.joinFaceOOP(3, "karen3");
        fo.joinFaceOOP(4, "karen4");
        fo.joinFaceOOP(5, "karen5");
        fo.joinFaceOOP(6, "karen6");
        fo.joinFaceOOP(7, "karen7");

        Person p1 = fo.getUser(1);
        Person p2 = fo.getUser(2);
        Person p3 = fo.getUser(3);
        Person p4 = fo.getUser(4);
        Person p5 = fo.getUser(5);
        Person p6 = fo.getUser(6);
        Person p7 = fo.getUser(7);
        Person pNot = new PersonImpl(8, "karen8");

        Status p1s1 = p1.postStatus("p1s1");
        Status p1s2 = p1.postStatus("p1s2");
        Status p1s3 = p1.postStatus("p1s3");

        Status p2s1 = p2.postStatus("p2s1");

        Status p3s1 = p3.postStatus("p3s1");
        Status p3s2 = p3.postStatus("p3s2");

        Status p4s1 = p4.postStatus("p4s1");
        Status p4s2 = p4.postStatus("p4s2");
        Status p4s3 = p4.postStatus("p4s3");

        try {
            fo.getFeedByRecent(pNot);
        } catch (PersonNotInSystemException e) {
            counter++;
        }
        Assertions.assertEquals(1,counter);

        try {
            fo.getFeedByPopular(pNot);
        } catch (PersonNotInSystemException e) {
            counter++;
        }
        Assertions.assertEquals(2,counter);

        StatusIterator statusIterator = fo.getFeedByRecent(p1);
        Assertions.assertEquals(statusIterator.hasNext(), false);

        fo.addFriendship(p1,p2);
        fo.addFriendship(p1,p7);
        fo.addFriendship(p1,p5);

        statusIterator = fo.getFeedByRecent(p1);
        Assertions.assertEquals(statusIterator.hasNext(), true);
        Assertions.assertEquals(statusIterator.next().getContent(),"p2s1");
        Assertions.assertEquals(statusIterator.hasNext(), false);

        statusIterator = fo.getFeedByPopular(p1);
        Assertions.assertEquals(statusIterator.hasNext(), true);
        Assertions.assertEquals(statusIterator.next().getContent(),"p2s1");
        Assertions.assertEquals(statusIterator.hasNext(), false);

        statusIterator = fo.getFeedByRecent(p2);
        Assertions.assertEquals(statusIterator.hasNext(), true);
        Assertions.assertEquals(statusIterator.next().getContent(),"p1s3");
        Assertions.assertEquals(statusIterator.hasNext(), true);
        Assertions.assertEquals(statusIterator.next().getContent(),"p1s2");
        Assertions.assertEquals(statusIterator.hasNext(), true);
        Assertions.assertEquals(statusIterator.next().getContent(),"p1s1");
        Assertions.assertEquals(statusIterator.hasNext(), false);

        fo.addFriendship(p1,p3);

        statusIterator = fo.getFeedByRecent(p1);
        Assertions.assertEquals(statusIterator.hasNext(), true);
        Assertions.assertEquals(statusIterator.next().getContent(),"p2s1");
        Assertions.assertEquals(statusIterator.hasNext(), true);
        Assertions.assertEquals(statusIterator.next().getContent(),"p3s2");
        Assertions.assertEquals(statusIterator.hasNext(), true);
        Assertions.assertEquals(statusIterator.next().getContent(),"p3s1");
        Assertions.assertEquals(statusIterator.hasNext(), false);

        fo.addFriendship(p6,p1);
        Status p6s1 = p6.postStatus("p6s1");


        statusIterator = fo.getFeedByRecent(p1);
        Assertions.assertEquals(statusIterator.hasNext(), true);
        Assertions.assertEquals(statusIterator.next().getContent(),"p2s1");
        Assertions.assertEquals(statusIterator.hasNext(), true);
        Assertions.assertEquals(statusIterator.next().getContent(),"p3s2");
        Assertions.assertEquals(statusIterator.hasNext(), true);
        Assertions.assertEquals(statusIterator.next().getContent(),"p3s1");
        Assertions.assertEquals(statusIterator.hasNext(), true);
        Assertions.assertEquals(statusIterator.next().getContent(),"p6s1");
        Assertions.assertEquals(statusIterator.hasNext(), false);

        fo.addFriendship(p7,p4);

        statusIterator = fo.getFeedByRecent(p4);
        Assertions.assertEquals(statusIterator.hasNext(), false);

        fo.addFriendship(p5,p4);

        statusIterator = fo.getFeedByRecent(p4);
        Assertions.assertEquals(statusIterator.hasNext(), false);

        statusIterator = fo.getFeedByRecent(p5);
        Assertions.assertEquals(statusIterator.next().getContent(),"p1s3");
        Assertions.assertEquals(statusIterator.hasNext(), true);
        Assertions.assertEquals(statusIterator.next().getContent(),"p1s2");
        Assertions.assertEquals(statusIterator.hasNext(), true);
        Assertions.assertEquals(statusIterator.next().getContent(),"p1s1");
        Assertions.assertEquals(statusIterator.hasNext(), true);
        Assertions.assertEquals(statusIterator.next().getContent(),"p4s3");
        Assertions.assertEquals(statusIterator.hasNext(), true);
        Assertions.assertEquals(statusIterator.next().getContent(),"p4s2");
        Assertions.assertEquals(statusIterator.hasNext(), true);
        Assertions.assertEquals(statusIterator.next().getContent(),"p4s1");
        Assertions.assertEquals(statusIterator.hasNext(), false);

        fo.joinFaceOOP(0, "karen0");
        Person p0 = fo.getUser(0);
        fo.addFriendship(p5,p0);

        statusIterator = fo.getFeedByRecent(p5);
        Assertions.assertEquals(statusIterator.next().getContent(),"p1s3");
        Assertions.assertEquals(statusIterator.hasNext(), true);
        Assertions.assertEquals(statusIterator.next().getContent(),"p1s2");
        Assertions.assertEquals(statusIterator.hasNext(), true);
        Assertions.assertEquals(statusIterator.next().getContent(),"p1s1");
        Assertions.assertEquals(statusIterator.hasNext(), true);
        Assertions.assertEquals(statusIterator.next().getContent(),"p4s3");
        Assertions.assertEquals(statusIterator.hasNext(), true);
        Assertions.assertEquals(statusIterator.next().getContent(),"p4s2");
        Assertions.assertEquals(statusIterator.hasNext(), true);
        Assertions.assertEquals(statusIterator.next().getContent(),"p4s1");
        Assertions.assertEquals(statusIterator.hasNext(), false);

        /** getFeedByPopular */
        p3s1.like(p0);
        p3s1.like(p1);

        statusIterator = fo.getFeedByPopular(p1);
        Assertions.assertEquals(statusIterator.hasNext(), true);
        Assertions.assertEquals(statusIterator.next().getContent(),"p2s1");
        Assertions.assertEquals(statusIterator.hasNext(), true);
        Assertions.assertEquals(statusIterator.next().getContent(),"p3s1");
        Assertions.assertEquals(statusIterator.hasNext(), true);
        Assertions.assertEquals(statusIterator.next().getContent(),"p3s2");
        Assertions.assertEquals(statusIterator.hasNext(), true);
        Assertions.assertEquals(statusIterator.next().getContent(),"p6s1");
        Assertions.assertEquals(statusIterator.hasNext(), false);


        p1s1.like(p0);
        p1s2.like(p0);
        p4s3.like(p0);
        p4s1.like(p0);

        fo.addFriendship(p5,p7);

        statusIterator = fo.getFeedByPopular(p5);
        Assertions.assertEquals(statusIterator.next().getContent(),"p1s2");
        Assertions.assertEquals(statusIterator.hasNext(), true);
        Assertions.assertEquals(statusIterator.next().getContent(),"p1s1");
        Assertions.assertEquals(statusIterator.hasNext(), true);
        Assertions.assertEquals(statusIterator.next().getContent(),"p1s3");
        Assertions.assertEquals(statusIterator.hasNext(), true);
        Assertions.assertEquals(statusIterator.next().getContent(),"p4s3");
        Assertions.assertEquals(statusIterator.hasNext(), true);
        Assertions.assertEquals(statusIterator.next().getContent(),"p4s1");
        Assertions.assertEquals(statusIterator.hasNext(), true);
        Assertions.assertEquals(statusIterator.next().getContent(),"p4s2");
        Assertions.assertEquals(statusIterator.hasNext(), false);

        fo.addFriendship(p3,p5);

        statusIterator = fo.getFeedByPopular(p5);
        Assertions.assertEquals(statusIterator.next().getContent(),"p1s2");
        Assertions.assertEquals(statusIterator.hasNext(), true);
        Assertions.assertEquals(statusIterator.next().getContent(),"p1s1");
        Assertions.assertEquals(statusIterator.hasNext(), true);
        Assertions.assertEquals(statusIterator.next().getContent(),"p1s3");
        Assertions.assertEquals(statusIterator.hasNext(), true);
        Assertions.assertEquals(statusIterator.next().getContent(),"p3s1");
        Assertions.assertEquals(statusIterator.hasNext(), true);
        Assertions.assertEquals(statusIterator.next().getContent(),"p3s2");
        Assertions.assertEquals(statusIterator.hasNext(), true);
        Assertions.assertEquals(statusIterator.next().getContent(),"p4s3");
        Assertions.assertEquals(statusIterator.hasNext(), true);
        Assertions.assertEquals(statusIterator.next().getContent(),"p4s1");
        Assertions.assertEquals(statusIterator.hasNext(), true);
        Assertions.assertEquals(statusIterator.next().getContent(),"p4s2");
        Assertions.assertEquals(statusIterator.hasNext(), false);



    }

    @Test
    public void addFriendship_AlreadyExists_Test(){
        int count =0;
        FaceOOP faceOOP = new FaceOOPImpl();
            try {
                faceOOP.joinFaceOOP(2, "Dude");
                faceOOP.joinFaceOOP(4, "Two Dudes");
                faceOOP.addFriendship(new PersonImpl(2, "Dude"), new PersonImpl(4, "Two Dudes"));
                faceOOP.addFriendship(new PersonImpl(2, "Dude"), new PersonImpl(4, "Two Dudes"));
            }
            catch (PersonAlreadyInSystemException e) {
                Assertions.fail();
            }
            catch (ConnectionAlreadyExistException e) {
                count++;
            }
            catch (PersonNotInSystemException e) {
                Assertions.fail();
            }
            catch (SamePersonException e) {
                Assertions.fail();
            }
        Assertions.assertEquals(1,count);
    }
}


