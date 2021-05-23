package ru.gb.april.market.april_market.error_handlings;

public class UserIsAlreadyRegisteredException extends Throwable {
    public UserIsAlreadyRegisteredException(String message) {

        super(message);
    }
}
