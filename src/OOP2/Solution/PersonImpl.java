package OOP2.Solution;

import OOP2.Provided.ConnectionAlreadyExistException;
import OOP2.Provided.Person;
import OOP2.Provided.SamePersonException;
import OOP2.Provided.Status;

import java.util.Collection;

public class PersonImpl implements Person {

	/**
	 * Constructor receiving person's id and name.
	 */
	public PersonImpl(Integer id, String name)
	{}

	@Override
	public Integer getId() {
		return null;
	}

	@Override
	public String getName() {
		return null;
	}

	@Override
	public Status postStatus(String content) {
		return null;
	}

	@Override
	public void addFriend(Person p) throws SamePersonException, ConnectionAlreadyExistException {

	}

	@Override
	public Collection<Person> getFriends() {
		return null;
	}

	@Override
	public Iterable<Status> getStatusesRecent() {
		return null;
	}

	@Override
	public Iterable<Status> getStatusesPopular() {
		return null;
	}

	@Override
	public int compareTo(Person o) {
		return 0;
	}
}
