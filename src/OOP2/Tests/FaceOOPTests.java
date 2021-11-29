package OOP2.Tests;

import OOP2.Provided.*;
import OOP2.Solution.FaceOOPImpl;
import OOP2.Solution.PersonImpl;
import org.junit.Assert;
import org.junit.Test;

public class FaceOOPTests {
    FaceOOPImpl faceOOP;
    public FaceOOPTests(){
        faceOOP = new FaceOOPImpl();
    }

    @Test
    public void joinFaceOOP_NewPerson_Test() throws PersonAlreadyInSystemException {
        var person = faceOOP.joinFaceOOP(1, "Ash");
        Assert.assertEquals(person, new PersonImpl(1, "dont care"));
    }

    @Test
    public void joinFaceOOP_OldPerson_Test() throws PersonAlreadyInSystemException {
        var person = faceOOP.joinFaceOOP(1, "Ash");
        Assert.assertThrows(PersonAlreadyInSystemException.class, ()-> faceOOP.joinFaceOOP(1, "dont care"));
    }

    @Test
    public void Size_Test() throws PersonAlreadyInSystemException {
        Assert.assertEquals(0, faceOOP.size());
        faceOOP.joinFaceOOP(1, "Ash");
        Assert.assertEquals(1, faceOOP.size());
        faceOOP.joinFaceOOP(2, "Ash");
        Assert.assertEquals(2, faceOOP.size());
        Assert.assertThrows(PersonAlreadyInSystemException.class, ()-> faceOOP.joinFaceOOP(2, "dont care"));
    }

    @Test
    public void GetUser_Exists_Test() throws PersonAlreadyInSystemException, PersonNotInSystemException {
        faceOOP.joinFaceOOP(1, "Ash");
        Assert.assertEquals(faceOOP.getUser(1), new PersonImpl(1, "Kacham"));
    }

    @Test
    public void GetUser_NotExists_Test() {
        Assert.assertThrows(PersonNotInSystemException.class, ()-> faceOOP.getUser(2));
    }

    @Test
    public void addFriendship_PersonNotInSystem_Test() {
        Assert.assertThrows(PersonNotInSystemException.class, ()->
                faceOOP.addFriendship(new PersonImpl(1, "Ash"), new PersonImpl(2, "Brock")));


        Assert.assertThrows(PersonNotInSystemException.class, ()->{
            faceOOP.joinFaceOOP(1, "Misty");
            faceOOP.addFriendship(new PersonImpl(1, "Misty"), new PersonImpl(2, "Brock"));
        });

        Assert.assertThrows(PersonNotInSystemException.class, ()->{
            faceOOP.joinFaceOOP(2, "Dude");
            faceOOP.addFriendship(new PersonImpl(3, "NotMisty"), new PersonImpl(2, "Dude"));
        });
    }

    @Test
    public void addFriendship_SamePerson_Test() {
        Assert.assertThrows(PersonNotInSystemException.class, ()->
                faceOOP.addFriendship(new PersonImpl(1, "Ash"), new PersonImpl(1, "Ash")));

        Assert.assertThrows(SamePersonException.class, ()->{
            faceOOP.joinFaceOOP(2, "Dude");
            faceOOP.addFriendship(new PersonImpl(2, "Dude"), new PersonImpl(2, "Dude"));
        });
    }

    @Test
    public void addFriendship_AlreadyExistsNewObjects_Test() {
        Assert.assertThrows(ConnectionAlreadyExistException.class, ()->{
            faceOOP.joinFaceOOP(2, "Dude");
            faceOOP.joinFaceOOP(4, "Two Dudes");
            faceOOP.addFriendship(new PersonImpl(2, "Dude"), new PersonImpl(4, "Two Dudes"));
            faceOOP.addFriendship(new PersonImpl(2, "Dude"), new PersonImpl(4, "Two Dudes"));
        });
    }

    @Test
    public void addFriendship_AlreadyExistsSameObjects_Test() {
        Assert.assertThrows(ConnectionAlreadyExistException.class, ()->{
            faceOOP.joinFaceOOP(2, "Dude");
            faceOOP.joinFaceOOP(4, "Two Dudes");
            var p1 = new PersonImpl(2, "Dude");
            var p2 = new PersonImpl(4, "Two Dudes");
            faceOOP.addFriendship(p1 , p2);
            faceOOP.addFriendship(p1 , p2);
        });
    }

    @Test
    public void Rank_PersonNotInSystem_Test(){
        Assert.assertThrows(PersonNotInSystemException.class, ()->
                faceOOP.rank(new PersonImpl(1, "Ash"), new PersonImpl(1, "Ash")));

        Assert.assertThrows(PersonNotInSystemException.class, ()->
                faceOOP.rank(new PersonImpl(1, "Ash"), new PersonImpl(2, "Brock")));


        Assert.assertThrows(PersonNotInSystemException.class, ()->{
            faceOOP.joinFaceOOP(1, "Misty");
            faceOOP.rank(new PersonImpl(1, "Misty"), new PersonImpl(2, "Brock"));
        });

        Assert.assertThrows(PersonNotInSystemException.class, ()->{
            faceOOP.joinFaceOOP(2, "Dude");
            faceOOP.rank(new PersonImpl(3, "NotMisty"), new PersonImpl(2, "Dude"));
        });
    }

    @Test
    public void Rank_SamePerson_Test() throws PersonAlreadyInSystemException, ConnectionDoesNotExistException, PersonNotInSystemException {
        faceOOP.joinFaceOOP(1, "Dijkstra");
        Assert.assertEquals((Integer)0, faceOOP.rank(new PersonImpl(1, "a"), new PersonImpl(1, "a")));
    }

    @Test
    public void Rank_NoConnection_Test() {
        Assert.assertThrows(ConnectionDoesNotExistException.class, ()->{
            faceOOP.joinFaceOOP(2, "Pulp");
            faceOOP.joinFaceOOP(4, "Fiction");
            faceOOP.rank(new PersonImpl(2, "Pulp"), new PersonImpl(4, "Fiction"));
        });
    }

    @Test
    public void Rank_Rank1_Test() throws PersonAlreadyInSystemException, ConnectionDoesNotExistException, PersonNotInSystemException, SamePersonException, ConnectionAlreadyExistException {
        faceOOP.joinFaceOOP(1, "Dijkstra");
        faceOOP.joinFaceOOP(2, "Turing");
        faceOOP.joinFaceOOP(3, "Linus");
        faceOOP.addFriendship(new PersonImpl(1, "dont care"), new PersonImpl(2, "dont care"));
        faceOOP.addFriendship(new PersonImpl(2, "dont care"), new PersonImpl(3, "dont care"));
        faceOOP.addFriendship(new PersonImpl(3, "dont care"), new PersonImpl(1, "dont care"));
        Assert.assertEquals((Integer)1, faceOOP.rank(new PersonImpl(1, "a"), new PersonImpl(2, "a")));
        Assert.assertEquals((Integer)1, faceOOP.rank(new PersonImpl(2, "a"), new PersonImpl(3, "a")));
        Assert.assertEquals((Integer)1, faceOOP.rank(new PersonImpl(3, "a"), new PersonImpl(1, "a")));
        Assert.assertEquals((Integer)1, faceOOP.rank(new PersonImpl(1, "a"), new PersonImpl(3, "a")));
    }

    @Test
    public void Rank_Rank2WithCircle_Test() throws PersonAlreadyInSystemException, SamePersonException, ConnectionAlreadyExistException, PersonNotInSystemException, ConnectionDoesNotExistException {
        faceOOP.joinFaceOOP(1, "pls");
        faceOOP.joinFaceOOP(2, "pass");
        faceOOP.joinFaceOOP(3, "all");
        faceOOP.joinFaceOOP(4, "tests");
        faceOOP.addFriendship(new PersonImpl(1, "dont care"), new PersonImpl(2, "dont care"));
        faceOOP.addFriendship(new PersonImpl(2, "dont care"), new PersonImpl(3, "dont care"));
        faceOOP.addFriendship(new PersonImpl(3, "dont care"), new PersonImpl(4, "dont care"));
        faceOOP.addFriendship(new PersonImpl(4, "dont care"), new PersonImpl(1, "dont care"));
        Assert.assertEquals((Integer)2, faceOOP.rank(new PersonImpl(1, "a"), new PersonImpl(3, "a")));
        Assert.assertEquals((Integer)2, faceOOP.rank(new PersonImpl(3, "a"), new PersonImpl(1, "a")));
        Assert.assertEquals((Integer)2, faceOOP.rank(new PersonImpl(4, "a"), new PersonImpl(2, "a")));
        Assert.assertEquals((Integer)2, faceOOP.rank(new PersonImpl(2, "a"), new PersonImpl(4, "a")));
    }
}
