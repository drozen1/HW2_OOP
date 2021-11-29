package OOP2.Solution;

import OOP2.Provided.*;

import java.util.*;


public class FaceOOPImpl implements FaceOOP {

	class Vertrx {
		public PersonImpl p;
		public Integer dist;
		Vertrx (PersonImpl p){
			this.p = p;
			this.dist = Integer.MAX_VALUE;
		}

		public boolean equals(Object o) {
			if(o == null) return false;
			if (o.getClass() != this.getClass()) return false;
			Vertrx v = (Vertrx) o;
			return this.p.equals(v.p);
		}
	}

	private List<Vertrx> allUsers;
	private int nextIndex;

	/**
	 * Constructor - receives no parameters and initializes the system.
	 */
	public FaceOOPImpl()
	{
		allUsers = new ArrayList<>() ;
		nextIndex = 0;
	}

	@Override
	public Person joinFaceOOP(Integer id, String name) throws PersonAlreadyInSystemException {
		PersonImpl p = new PersonImpl(id, name);
		p.visitedIndex = nextIndex;
		Vertrx v = new Vertrx(p);
		if (allUsers.contains(v)){
			throw new PersonAlreadyInSystemException();
		}
		this.allUsers.add(v);
		this.nextIndex++;
		return p;
	}

	@Override
	public int size() {
		return allUsers.size();
	}

	@Override
	public Person getUser(Integer id) throws PersonNotInSystemException {
		PersonImpl p = new PersonImpl(id, "name");
		Vertrx v = new Vertrx(p);
		if (!allUsers.contains(v)){
			throw new PersonNotInSystemException();
		}
		for (Vertrx it : this.allUsers) {
			if (it.p.getId().equals(id)) {
				return it.p;
			}
		}
		throw new PersonNotInSystemException();
	}

	@Override
	public void addFriendship(Person p1, Person p2) throws PersonNotInSystemException, SamePersonException, ConnectionAlreadyExistException {
		PersonImpl p1Imp = new PersonImpl(p1.getId(),p1.getName());
		PersonImpl p2Imp = new PersonImpl(p2.getId(),p2.getName());
		Vertrx v1 = new Vertrx(p1Imp);
		Vertrx v2 = new Vertrx(p2Imp);
		if ((!allUsers.contains(v1)) || (!allUsers.contains(v2))){
			throw new PersonNotInSystemException();
		}
		if (p1.equals(p2)) {
			throw new SamePersonException();
		}
		if (p1.getFriends().contains(p2)) {
			throw new ConnectionAlreadyExistException();
		}
		p1.addFriend(p2);
	}

	@Override
	public StatusIterator getFeedByRecent(Person p) throws PersonNotInSystemException {
		PersonImpl pImp = new PersonImpl(p.getId(),p.getName());
		Vertrx v = new Vertrx(pImp);
		if (!allUsers.contains(v)){
			throw new PersonNotInSystemException();
		}
		Collection<Person> personCollection = sortingFriends(p.getFriends());
		if (personCollection.contains(p)){
			personCollection.remove(p);
		}
		return new StatusIteratorImpl(personCollection,true);
	}

	@Override
	public StatusIterator getFeedByPopular(Person p) throws PersonNotInSystemException {
		PersonImpl pImp = new PersonImpl(p.getId(),p.getName());
		Vertrx v = new Vertrx(pImp);
		if (!allUsers.contains(v)) {
			throw new PersonNotInSystemException();
		}
		Collection<Person> personCollection = sortingFriends(p.getFriends());
		if (personCollection.contains(p)){
			personCollection.remove(p);
		}
		return new StatusIteratorImpl(personCollection,false);
	}

	@Override
	public Integer rank(Person source, Person target) throws PersonNotInSystemException, ConnectionDoesNotExistException {

		PersonImpl p1Imp = new PersonImpl(source.getId(),source.getName());
		PersonImpl p2Imp = new PersonImpl(target.getId(),target.getName());
		Vertrx v1 = new Vertrx(p1Imp);
		Vertrx v2 = new Vertrx(p2Imp);
		if ((!allUsers.contains(v1)) || (!allUsers.contains(v2))){
			throw new PersonNotInSystemException();
		}

		PersonImpl sourceImpl = (PersonImpl) source;
		Vertrx v = new Vertrx(sourceImpl);
		LinkedList<Vertrx> neighboors = BFS(v);

		for (Vertrx it : neighboors) {
			if (it.p.equals(p2Imp)) {
				return it.dist;
			}
		}
		throw new ConnectionDoesNotExistException();
	}

	// prints BFS traversal from a given source s
	private LinkedList<Vertrx> BFS(Vertrx source)
	{
		// Mark all the vertices as not visited(By default
		// set as false)
		boolean visited[] = new boolean[this.allUsers.size()];

		source.dist = 0;

		// Create a queue for BFS
		LinkedList<Vertrx> queue = new LinkedList<Vertrx>();

		LinkedList<Vertrx> ret = new LinkedList<Vertrx>();

		// Mark the current node as visited and enqueue it
		visited[source.p.visitedIndex]=true;
		queue.add(source);
		ret.add(source);

		while (queue.size() != 0)
		{
			source = queue.poll();
			Collection<Person> myFriends =  source.p.getFriends();
			Iterator itr = 	myFriends.iterator();
			while(itr.hasNext()) {
				Object element = itr.next(); //TODO: check it iterate on the first element
				PersonImpl friend = (PersonImpl)element;
				if (!visited[friend.visitedIndex]) {
					visited[friend.visitedIndex] = true;
					Vertrx newVertex = new Vertrx(friend);
					newVertex.dist = source.dist +1;
					queue.add(newVertex);
					ret.add(newVertex);
				}
			}
		}
		return ret;
	}


	@Override
	public Iterator<Person> iterator() {
		List<Person> personList = new ArrayList<>();
		for (Vertrx it : this.allUsers) {
			personList.add(it.p);
		}
		personList.sort((o1, o2) -> o1.getId() - o2.getId());
		return personList.iterator();
	}

	public Collection<Person> sortingFriends(Collection<Person> friends) {
		List<Person> ret = new ArrayList<>();
		ret.addAll(friends);
		ret.sort((o1, o2) -> o1.getId() - o2.getId());
		return ret;
	}
}
