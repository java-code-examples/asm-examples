package com.example.event.based;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;

import java.io.IOException;

public class CustomClassWriter {

    static String className = "java.lang.Integer";
    static String cloneableInterface = "java/lang/Cloneable";
    ClassReader reader;
    ClassWriter writer;

    public CustomClassWriter() throws IOException {
        reader = new ClassReader(className);
        writer = new ClassWriter(reader, 0);
    }

    AddFieldAdapter addFieldAdapter;
    //...
    public byte[] addField() {
        addFieldAdapter = new AddFieldAdapter(
                "aNewBooleanField",
                org.objectweb.asm.Opcodes.ACC_PUBLIC,
                writer);
        reader.accept(addFieldAdapter, 0);
        return writer.toByteArray();
    }
}
