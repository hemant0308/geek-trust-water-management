package com.example.geektrust;

import org.junit.jupiter.api.Test;

import java.io.*;

import static junit.framework.Assert.assertEquals;

public class MainTest {

    @Test
    public void testMain() throws Exception {
        Main.inputStream = new FileInputStream(new File("./sample_input/input1.txt"));
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        Main.outputStream = new PrintStream(outputStream);
        Main.main(new String[0]);
        String output = outputStream.toString();

        assertEquals(output, "2400 5215\n");

    }
}