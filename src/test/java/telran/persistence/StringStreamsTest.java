package telran.persistence;


import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;

import static org.junit.jupiter.api.Assertions.*;
import java.io.*;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
public class StringStreamsTest {
    static final String PRINT_STREAM_FILE = "printStreamFile.txt";
    static final String PRINT_WRITER_FILE = "printWriterFile.txt";
    
    static final String OUTPUT_FILE = "output.txt";
    static final String PATH = "C:\\JAVA\\Beersheva24\\java-projects";
    @Test
    @Disabled
    void printStreamTest() throws Exception{
        PrintStream printStream = new PrintStream(PRINT_STREAM_FILE);
        printStream.println("HELLO");
        printStream.close();
    }
    @Test
    @Disabled
    void printWriterTest() throws Exception{
        PrintWriter printWriter = new PrintWriter(PRINT_WRITER_FILE);
        printWriter.println("HELLO");
        printWriter.close();
    }
    
    @Test
    @Disabled
    void bufferedReaderTest() throws Exception{
        BufferedReader reader = new BufferedReader(new FileReader(PRINT_WRITER_FILE));
        assertEquals("HELLO",reader.readLine());
        reader.close();
    }
   
    @Test
    void printingDirectoryTest(){
        printDirectoryContent(PATH,3);
    }
    
    /**
     * 
     * @param path -  path to a directory
     * @param depth -  number of been walked levels
     */
     void printDirectoryContent(String path, int depth)  {
        Path startingDir = Paths.get(path);
        Path outputPath = Paths.get(OUTPUT_FILE);
        //TODO
        //dir1
          //dir11
            //file
            //dir111
          //dir12
        //Consider class Path
        //Consider class Files
        //Consider method https://docs.oracle.com/en/java/javase/11/docs/api/java.base/java/nio/file/Files.html#walkFileTree(java.nio.file.Path,java.util.Set,int,java.nio.file.FileVisitor)
        try {
            // Clear or create the output file
            Files.writeString(outputPath, "", StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            
            Files.walkFileTree(startingDir, EnumSet.noneOf(FileVisitOption.class), depth, new SimpleFileVisitor<Path>() {
                private int currentDepth = 0;

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    printIndented(dir.getFileName().toString(), currentDepth, outputPath);
                    currentDepth++;
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    printIndented(file.getFileName().toString(), currentDepth, outputPath);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    currentDepth--;
                    return FileVisitResult.CONTINUE;
                }
                
                private void printIndented(String name, int level, Path outputPath) throws IOException {
                    String indent = "  ".repeat(level);
                    Files.writeString(outputPath, indent + name + System.lineSeparator(), StandardOpenOption.APPEND);
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

   }

}