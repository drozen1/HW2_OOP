package OOP2.Tests;

import OOP2.Provided.Person;
import OOP2.Provided.Status;
import OOP2.Solution.PersonImpl;
import OOP2.Solution.StatusImpl;
import org.junit.Assert;
import org.junit.Test;

public class StatusTests
{
    private final Status status;
    private Person p;

    public StatusTests(){
        p = new PersonImpl(1, "Denis");
        status = new StatusImpl(p, "Loyd", 1);
    }

    @Test
    public void Getters_Init_Test(){
        Person p2 = new PersonImpl(1, "Denis");
        Assert.assertEquals(status.getId(),(Integer) 1);
        Assert.assertEquals(status.getPublisher(), p2);
        Assert.assertEquals(status.getLikesCount(), (Integer) 0);
        Assert.assertEquals(status.getContent(),"Loyd");

    }

    @Test
    public void GetLikesCount_NoDupes_Test(){
        Person p2 = new PersonImpl(2, "Denis2");
        Person p3 = new PersonImpl(3, "Denis3");
        Person p4 = new PersonImpl(4, "Denis4");
        Person p5 = new PersonImpl(5, "Denis5");
        status.like(p2);
        status.like(p3);
        status.like(p4);
        status.like(p5);
        Assert.assertEquals(status.getLikesCount(),(Integer) 4);
    }

    @Test
    public void GetLikesCount_Dupes_Test(){
        Person p2 = new PersonImpl(2, "Denis2");
        Person p3 = new PersonImpl(3, "Denis3");
        Person p4 = new PersonImpl(4, "Denis4");
        Person p5 = new PersonImpl(5, "Denis5");
        status.like(p2);
        status.like(p2);
        status.like(p3);
        status.like(p3);
        status.like(p4);
        status.like(p4);
        status.like(p5);
        status.like(p5);
        Assert.assertEquals(status.getLikesCount(),(Integer) 4);
    }

    @Test
    public void GetLikesCount_Owner_Test(){
        status.like(p);
        Assert.assertEquals(status.getLikesCount(),(Integer) 1);
    }

    @Test
    public void GetLikesCount_Unlike_Test(){
        Person p2 = new PersonImpl(2, "Denis2");
        status.like(p2);
        Assert.assertEquals(status.getLikesCount(),(Integer) 1);
        status.unlike(p2);
        status.unlike(p2);
        Assert.assertEquals(status.getLikesCount(),(Integer) 0);
    }

    @Test
    public void Equals_Equal_Test(){
        Person p2 = new PersonImpl(1, "Denis");
        Person p3 = new PersonImpl(1, "NotDenis");
        Status status2 = new StatusImpl(p2, "Loyd", 1);status.like(p);
        Status status3 = new StatusImpl(p2, "NotLoyd", 1);status.like(p);
        Status status4 = new StatusImpl(p3, "NotLoyd", 1);status.like(p);
        Assert.assertEquals(status2, status);
        Assert.assertEquals(status3, status);
        Assert.assertEquals(status4, status);
    }

    @Test
    public void Equals_NotEqualPublisherID_Test(){
        Person p2 = new PersonImpl(2, "Denis");
        Status status2 = new StatusImpl(p2, "Loyd", 1);status.like(p);
        Assert.assertNotEquals(status2, status);
    }

    @Test
    public void Equals_NotEqualStatusId_Test(){
        Person p2 = new PersonImpl(1, "Denis");
        Status status2 = new StatusImpl(p2, "Loyd", 2);status.like(p);
        Assert.assertNotEquals(status2, status);
    }


}
