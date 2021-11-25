package OOP2.Solution;

import OOP2.Provided.Person;
import OOP2.Provided.Status;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class StatusImpl implements Status {

	private Person publisher;
	private String content;
	private Integer id;
	Set<Person> likes;

	/*
	 * A constructor that receives the status publisher, the text of the status
	 *  and the id of the status.
	 */
	public StatusImpl(Person publisher, String content, Integer id)
	{
		this.publisher = new PersonImpl(publisher.getId(), publisher.getName());
		this.content = content;
		this.id = id;
		this.likes = new HashSet<>();
	}

	@Override
	public Integer getId() {
		return this.id;
	}

	@Override
	public Person getPublisher() {
		return this.publisher;
	}

	@Override
	public String getContent() {
		return this.content;
	}

	@Override
	public void like(Person p) {
		likes.add(p);
	}

	@Override
	public void unlike(Person p) {
		likes.remove(p);
	}

	@Override
	public Integer getLikesCount() {
		return likes.size();
	}

	@Override
	public boolean equals(Object o){
		if(o == null) return false;
		if (o.getClass() != this.getClass()) return false;
		StatusImpl s = (StatusImpl) o;
		return s.publisher.equals(publisher)  && s.id.equals(id);
	}

	@Override
	public int hashCode(){
		return (this.publisher.getId() + this.id) % 10;
	}
}
