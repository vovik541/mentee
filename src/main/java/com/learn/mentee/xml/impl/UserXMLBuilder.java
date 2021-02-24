package com.learn.mentee.xml.impl;

import com.learn.mentee.entity.User;
import com.learn.mentee.entity.xml.UserXML;
import com.learn.mentee.util.converter.impl.UserToXMLConverter;
import com.learn.mentee.xml.XMLBuilder;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.xml.transform.stream.StreamResult;
import java.io.OutputStream;

@Component
public class UserXMLBuilder implements XMLBuilder<User> {

    @Resource
    private Jaxb2Marshaller marshaller;

    @Resource
    private UserToXMLConverter userToXMLConverter;

    @Override
    public void buildXml(User user, OutputStream outputStream) {
        UserXML userXML = userToXMLConverter.convert(user);
        marshaller.marshal(userXML, new StreamResult(outputStream));
    }
}
