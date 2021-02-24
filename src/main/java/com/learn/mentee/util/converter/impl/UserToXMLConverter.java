package com.learn.mentee.util.converter.impl;

import com.learn.mentee.entity.User;
import com.learn.mentee.entity.xml.UserXML;
import com.learn.mentee.util.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToXMLConverter implements Converter<User, UserXML> {

    @Override
    public UserXML convert(User user) {
        return new UserXML(user.getId(), user.getName(), user.getEmail());
    }
}
