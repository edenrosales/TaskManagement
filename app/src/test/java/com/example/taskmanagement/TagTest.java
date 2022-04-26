package com.example.taskmanagement;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TagTest {
    @Test
    void setName() {
        Tag example = new Tag();
        example.setName("School");
        example.setColor("Yellow");
        assertEquals("School", example.getName());
    }

    @Test
    void getName() {
    }

    @Test
    void setColor() {
        Tag example = new Tag();
        example.setName("School");
        example.setColor("Yellow");
        assertEquals("Yellow", example.getColor());
    }

    @Test
    void getColor() {
    }
}