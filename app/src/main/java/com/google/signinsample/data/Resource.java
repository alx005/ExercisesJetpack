package com.google.signinsample.data;

public class Resource <T>  {


    public enum State {
        INITIAL, SUCCESS, ERROR, IN_PROGRESS
    }

    private State state;
    private String message;
    private T data;

    private Resource (State state, T userData, String msg) {
        this.state = state;
        this.data = userData;
        this.message = msg;
    }

    public static <T> Resource initial(T userData) {
        return new Resource(State.INITIAL, userData, "");
    }
    public static <T> Resource success(T userData) {
        return new Resource(State.SUCCESS, userData, "");
    }
    public static <T> Resource error(T userData, String msg) {
        return new Resource(State.ERROR, userData, msg);
    }

    public static <T> Resource loading(T userData, String msg) {
        return new Resource(State.IN_PROGRESS, userData, "");
    }

    public State getState() {
        return state;
    }

    public T getData() {
        return data;
    }
}
