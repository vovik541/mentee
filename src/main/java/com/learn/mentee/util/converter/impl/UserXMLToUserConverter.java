package com.learn.mentee.util.converter.impl;

import com.learn.mentee.entity.User;
import com.learn.mentee.entity.xml.UserXML;
import com.learn.mentee.util.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserXMLToUserConverter implements Converter<UserXML, User> {

    @Override
    public User convert(UserXML userXML) {
        return new User(userXML.id, userXML.name, userXML.email);
    }
}
