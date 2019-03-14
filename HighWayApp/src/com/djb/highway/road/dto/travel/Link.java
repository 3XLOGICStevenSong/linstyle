package com.djb.highway.road.dto.travel;

public class Link {
    public Object data;
    public Link previous;
    public Link next;

    public Link(Object data) {
        this.data = data;
    }

    public void showLink() {
        System.out.print(data + " ");
    }

}
