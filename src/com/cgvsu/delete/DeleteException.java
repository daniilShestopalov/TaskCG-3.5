package com.cgvsu.delete;

public class DeleteException extends Exception {

    public DeleteException() {
        super("The entered index is greater than the maximum");
    }
}
