package telran.persistence;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

public class CodeCommentsSeparation {
public static void main(String[] args) throws IOException {
    splitFile(args[0], args[1], args[2]);
    //TODO - data from args[0] split to two files: args[1], args[2]
    //for sake of simplicity comments may be only on one line, like comments at this file
    // /* */ cannot be
    // code ...// comment .... cannot be
         //However // may be not only at beginning of line, like this
    //args[0] - path to file containing code and comments 
    //args[1] - path to file for placing only code
    //args[2] - path to file for placing only comments
    
}
private static void splitFile(String fileFrom, String fileCode, String fileComments) throws IOException {
    BufferedReader bufferedReader = new BufferedReader(new FileReader(fileFrom));
    PrintWriter printWriterCode = new PrintWriter(fileCode);
    PrintWriter printWriterComments = new PrintWriter(fileComments);
    String str;

    while ((str = bufferedReader.readLine()) != null) {
        String strCode = str;
        String strComment = ""; 
        int index = str.indexOf("//");
        
        if (index >= 0) {
            strCode = str.substring(0, index).strip();
            strComment = str.substring(index).strip();
        }

        if (!strCode.isEmpty()) {
            printWriterCode.println(strCode);    
        }

        if (!strComment.isEmpty()) {
            printWriterComments.println(strComment);    
        }
    }

    bufferedReader.close();
    printWriterCode.close();
    printWriterComments.close();
}

}