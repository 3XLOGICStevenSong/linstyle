package com.djb.highway.road.dto.travel;

public class DoubleLinkObject {
    public Link first;
    public Link last;
    int size;

    public DoubleLinkObject() {
        this.first = null;
        this.last = null;
        size = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void insertFirst(Object data) {
        Link link = new Link(data);
        if (isEmpty())
            last = link;
        else
            first.previous = link;
        link.next = first;
        first = link;
        size++;
    }

    public void insertLast(Object data) {
        Link link = new Link(data);
        if (isEmpty())
            first = link;
        else
            last.next = link;
        link.previous = last;
        last = link;
        size++;
    }

    public boolean insertAfter(Object key, Object data) {
        Link current = first;
        while (!current.data.equals(key)) {
            current = current.next;
            if (current == null)
                return false;
        }
        Link link = new Link(data);
        if (current == last) {
            link.next = null;
            last = link;
        } else {
            link.next = current.next;
            current.next.previous = link;
        }
        link.previous = current;
        current.next = link;
        size++;
        return true;
    }

    public Link delectKey(Object key) {
        Link current = first;
        while (!current.data.equals(key)) {
            current = current.next;
            if (current == null)
                return null;
        }
        if (current == first)
            first = current.next;
        else
            current.previous.next = current.next;
        if (current == last)
            last = current.previous;
        else
            current.next.previous = current.previous;
        size--;
        return current;
    }

    public Link delectFirst() {
        Link temp = first;
        if (first.next == null)
            last = null;
        else
            first.next.previous = null;
        first = first.next;
        size--;
        return temp;
    }

    public Link delectLast() {
        Link temp = last;
        if (first.next == null)
            first = null;
        else
            last.previous.next = null;
        last = last.previous;
        size--;
        return temp;
    }
}
