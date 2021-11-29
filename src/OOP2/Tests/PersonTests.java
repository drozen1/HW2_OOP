package OOP2.Tests;
import OOP2.Provided.ConnectionAlreadyExistException;
import OOP2.Provided.Person;
import OOP2.Provided.SamePersonException;
import OOP2.Provided.Status;
import OOP2.Solution.PersonImpl;
import OOP2.Solution.StatusImpl;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

public class PersonTests {

    private Person p;
    public PersonTests(){
        p = new PersonImpl(1, "Denis");
    }

    @Test
    public void Getters_Init_Test(){
        Assert.assertSame("Denis", p.getName());
        Assert.assertSame(p.getId(), 1);
        Assert.assertSame(p.getFriends().size(), 0);
        Assert.assertSame(p.getStatusesPopular().iterator().hasNext(), false);
        Assert.assertSame(p.getStatusesRecent().iterator().hasNext(), false);
    }

    @Test
    public void PostStatus_OneStatus_Test(){
        p.postStatus("sup");
        Iterable<Status> statuses = p.getStatusesRecent();
        List<Status> statusesList = new ArrayList<>();
        statuses.forEach(statusesList::add);
        Assert.assertEquals(1, statusesList.size());
        Status s = statusesList.get(0);
        Assert.assertEquals((Integer) 0, s.getId());
        Assert.assertEquals("sup", s.getContent());
    }

    @Test
    public void PostStatus_MultipleStatuses_Test(){
        p.postStatus("sup");
        p.postStatus("Piff The Magic Dragon");
        Iterable<Status> statuses = p.getStatusesRecent();
        List<Status> statusesList = new ArrayList<>();
        statuses.forEach(statusesList::add);
        Assert.assertEquals(2, statusesList.size());
        Status s = statusesList.get(0);
        Assert.assertEquals((Integer) 1, s.getId());
        Assert.assertEquals("Piff The Magic Dragon", s.getContent());
        s = statusesList.get(1);
        Assert.assertEquals((Integer) 0, s.getId());
        Assert.assertEquals("sup", s.getContent());
    }

    @Test
    public void AddFriend_SamePerson_Test(){
        Assert.assertThrows(SamePersonException.class, ()-> p.addFriend(p));
    }

    @Test
    public void AddFriend_AlreadyConnected_Test() {
        var p2 = new PersonImpl(2, "Giglipuff");
        Assert.assertThrows(ConnectionAlreadyExistException.class, ()-> {
            p.addFriend(p2);
            p.addFriend(p2);
        });
    }

    @Test
    public void GetFriends_Test() throws SamePersonException, ConnectionAlreadyExistException {
        var p2 = new PersonImpl(2, "Giglipuff");
        var p3 = new PersonImpl(3, "Snorlax");
        HashSet<Person> persons = new HashSet<>();
        persons.add(p2);
        persons.add(p3);

        p.addFriend(p2);
        p.addFriend(p3);

        var friends = p.getFriends();
        friends.forEach(x -> Assert.assertTrue(persons.contains(x)));
    }

    @Test
    public void GetSTatusesPopular_Test(){
        var midLikes = p.postStatus("report mid"); //1
        var highLikes = p.postStatus("report high"); //2
        var lowLikes = p.postStatus("report low"); //0

        midLikes.like(new PersonImpl(2, "adam"));
        highLikes.like(new PersonImpl(2, "adam"));
        highLikes.like(new PersonImpl(3, "hava"));

        var statuses = p.getStatusesPopular();
        List<Status> statusesList = new ArrayList<>();
        statuses.forEach(statusesList::add);
        Assert.assertEquals(statusesList.get(0).getContent(), "report high");
        Assert.assertEquals(statusesList.get(1).getContent(), "report mid");
        Assert.assertEquals(statusesList.get(2).getContent(), "report low");
    }

    @Test
    public void CompareTo_Test(){
        Assert.assertEquals(0, p.compareTo(new PersonImpl(1, "dude")));
        Assert.assertTrue(p.compareTo(new PersonImpl(0, "dude")) > 0);
        Assert.assertTrue(p.compareTo(new PersonImpl(2, "dude")) < 0);
    }

    @Test
    public void Equals_Test(){
        Assert.assertEquals(p, new PersonImpl(1, "dude"));
        Assert.assertNotEquals(p, new PersonImpl(0, "dude"));
        Assert.assertNotEquals(p, new PersonImpl(2, "dude"));
    }

}
