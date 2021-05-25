package edu.vanderbilt.imagecrawler.utils;

import java.util.Spliterator;
import java.util.Spliterators;
import java.util.function.Consumer;

/**
 * Defines an object for traversing and partitioning
 * elements of an Array.
 */
public final class ArraySpliterator<E>
        extends Spliterators.AbstractSpliterator<E> {
    /**
     * The array to traverse and/or partition.
     */
    // TODO (graduates 1b and undergraduates 2a) - you fill in here.
    Array<E> array;

    /**
     * Current index, modified on advance/split.
     */
    // TODO (graduates 1b and undergraduates 2a) - you fill in here.
    int curIndex;

    /**
     * One past the end of the spliterator range.
     */
    // TODO (graduates 1b and undergraduates 2a) - you fill in here.
    int endIndex;

    /**
     * Create new spliterator covering the given range.
     */
    ArraySpliterator(Array<E> array,
                     int origin,
                     int end) {
        super(array.size(),
                Spliterator.ORDERED | Spliterator.SIZED | Spliterator.SUBSIZED);

        // TODO (graduates 1b and undergraduates 2a) - you fill in here.
        this.array = array;
        curIndex = origin;
        endIndex = end;
    }

    /**
     * If a remaining element exists, performs the given action on
     * it, returning true; else returns false.  Throw
     * NullPointerException of {@code action} is null.
     */
    public boolean tryAdvance(Consumer<? super E> action) {
        // TODO (graduates 1b and undergraduates 2a)
        //  you fill in here replacing this statement with your solution.
        if (action == null) {
            throw new NullPointerException();
        }
        if (curIndex < endIndex) {
            action.accept((E) array.uncheckedToArray()[curIndex]);
            curIndex++;
            return true;
        }
        return false;
    }

    /**
     * Returns a Spliterator covering elements that will, upon
     * return from this method, not be covered by this Spliterator.
     */
    public ArraySpliterator<E> trySplit() {
        // TODO (graduates 1b and undergraduates 2a)
        //  you fill in here replacing this statement with your solution.
        if (endIndex - curIndex <= 1) {
            return null;
        }
        int mid = curIndex + (endIndex - curIndex) / 2;
        ArraySpliterator<E> ret = new ArraySpliterator<E>(array, curIndex, mid);
        curIndex = mid;
        return ret;
    }
}
