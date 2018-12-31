package org.orion.app.component.contract;

public interface IPasswordEncoder {
    String encodePassword(String password);
    boolean matches(String clearPassword, String encodedPassword);
}
