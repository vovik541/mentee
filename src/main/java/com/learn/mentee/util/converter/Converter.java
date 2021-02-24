package com.learn.mentee.util.converter;

public interface Converter <I, O> {

    /**
     * Converts I object to an O object,
     * @param input Input object
     * @return converted output Object
     */
    O convert(I input);
}
