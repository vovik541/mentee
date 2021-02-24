package com.learn.mentee.entity.xml;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@NoArgsConstructor
@AllArgsConstructor
public class UserXML {

    @XmlElement
    public long id;
    @XmlElement
    public String name;
    @XmlElement
    public String email;
}
