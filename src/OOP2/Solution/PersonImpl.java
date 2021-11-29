package OOP2.Solution;

import OOP2.Provided.ConnectionAlreadyExistException;
import OOP2.Provided.Person;
import OOP2.Provided.SamePersonException;
import OOP2.Provided.Status;

import java.util.*;

public class PersonImpl implements Person{
	private final Integer id;
	private String name;
	private List<Status> statuses;
	private Integer nextIdStatus;
	private Set<Person> friends;
	public Integer visitedIndex;
	/**
	 * Constructor receiving person's id and name.
	 */
	public PersonImpl(Integer id, String name)
	{
		this.id = id;
		this.name = name;
		this.statuses = new ArrayList<>();
		this.nextIdStatus = 0;
		this.friends = new HashSet<>();
		this.visitedIndex = -1;
	}

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public Status postStatus(String content) {
		Status newStatus = new StatusImpl(this, content, nextIdStatus);
		this.nextIdStatus += 1;
		this.statuses.add(newStatus);
		return newStatus;
	}

	@Override
	public void addFriend(Person p) throws SamePersonException, ConnectionAlreadyExistException {
		if (this.equals(p)) {
			throw new SamePersonException();
		}
		if (this.friends.contains(p)){
			throw new ConnectionAlreadyExistException();
		}
		this.friends.add(p);
		PersonImpl pImpl = (PersonImpl) p;
		pImpl.addFriendAux(this);
	}

	@Override
	public Collection<Person> getFriends() {
		return this.friends;
	}

	@Override
	public Iterable<Status> getStatusesRecent() {
		List<Status> copyStatuses = new ArrayList<>(this.statuses) ;
		copyStatuses.sort(new Comparator<Status>() {
			@Override
			public int compare(Status o1, Status o2) {
				return o2.getId() - o1.getId();
			}
		});
		return copyStatuses;
	}

	@Override
	public Iterable<Status> getStatusesPopular() {
		List<Status> copyStatuses = new ArrayList<>(this.statuses) ;
		copyStatuses.sort(new Comparator<Status>() {
			@Override
			public int compare(Status o1, Status o2) {
				int diff = o2.getLikesCount() - o1.getLikesCount();
				if (diff == 0 ){
					return o2.getId() - o1.getId();
				}
				return diff;
			}
		});
		return copyStatuses;
	}


	@Override
	public boolean equals(Object o) {
		if(o == null) return false;
		if (o.getClass() != this.getClass()) return false;
		PersonImpl p = (PersonImpl) o;
		return this.id.equals(p.id);
	}

	@Override
	public int compareTo(Person other) {
		PersonImpl otherPerson = (PersonImpl) other;
		return this.id - otherPerson.id;
	}

	public void addFriendAux(Person p){
		this.friends.add(p);
	}
}
