package OOP2.Solution;
import OOP2.Provided.Person;
import OOP2.Provided.Status;
import OOP2.Provided.StatusIterator;

import java.awt.*;
import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class StatusIteratorImpl implements StatusIterator {

    Iterator<Person> personIterator;
    Iterator<Status> statusIterator;
    boolean isRecent;

    public StatusIteratorImpl(Collection<Person> personCollection, boolean isRecent){
        this.personIterator = personCollection.iterator();
        this.isRecent = isRecent;
        if (isRecent) {
            this.statusIterator = this.personIterator.next().getStatusesRecent().iterator();
        } else {
            this.statusIterator = this.personIterator.next().getStatusesPopular().iterator();
        }
        while (!statusIterator.hasNext()) {
            if (!this.personIterator.hasNext()){
                break;
            }
            this.personIterator.next();
            if (isRecent) {
                this.statusIterator = this.personIterator.next().getStatusesRecent().iterator();
            } else {
                this.statusIterator = this.personIterator.next().getStatusesPopular().iterator();
            }
        }
    }

    @Override
    public boolean hasNext() {
        return statusIterator.hasNext();
    }

    @Override
    public Status next() throws NoSuchElementException {
        if (!this.hasNext()){
            throw new NoSuchElementException();
        }
        Status ret = statusIterator.next();
        while (!statusIterator.hasNext()) {
            if (!this.personIterator.hasNext()){
                break;
            }
            this.personIterator.next();
            if (isRecent) {
                this.statusIterator = this.personIterator.next().getStatusesRecent().iterator();
            } else {
                this.statusIterator = this.personIterator.next().getStatusesPopular().iterator();
            }
        }
        return ret;
    }

}
