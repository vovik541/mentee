package com.learn.mentee.util.exporter.impl;

import com.learn.mentee.entity.User;
import com.learn.mentee.entity.xml.UserXML;
import com.learn.mentee.util.converter.impl.UserXMLToUserConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

@Component
public class XMLToUserExporter {

    @Resource
    private UserXMLToUserConverter userXMLToUserConverter;

    @Value("${path.resources}${path.xml.users}")
    private String path;

    @Resource
    private Jaxb2Marshaller marshaller;

    public User exportUserFromXml() {
        File initialFile = new File(path);
        InputStream targetStream = null;
        try {
            targetStream = new FileInputStream(initialFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        UserXML userXML = (UserXML) marshaller.unmarshal(new StreamSource(targetStream));
        return userXMLToUserConverter.convert(userXML);
    }
}
