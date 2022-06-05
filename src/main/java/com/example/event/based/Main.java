package com.example.event.based;

import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        String className = "GeneratedClass";
        ClassWriter classWriter = generateClass(className);
        writeClassToFile(className, classWriter);
    }

    private static ClassWriter generateClass(String className) {
        ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
        cw.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, className, null, "java/lang/Object", null);

        addMethod(cw);

        cw.visitEnd();
        return cw;
    }

    private static void addMethod(ClassWriter cw) {
        MethodVisitor mv = cw.visitMethod(Opcodes.ACC_PUBLIC + Opcodes.ACC_STATIC, "main", "([Ljava/lang/String;)V", null, null);
        mv.visitCode();
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitLdcInsn("Hello world!");
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        mv.visitInsn(Opcodes.RETURN);
        mv.visitMaxs(0, 0);
        mv.visitEnd();
    }

    private static void writeClassToFile(final String fileName, final ClassWriter cw) throws IOException {
        byte[] byteArray = cw.toByteArray();
        byte[] bytes = cw.toByteArray();
        try (FileOutputStream stream = new FileOutputStream("generated_classes/" + fileName + ".class")) {
            stream.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
