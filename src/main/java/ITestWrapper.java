import java.io.*;
import java.util.LinkedList;

public interface ITestWrapper extends Closeable, IAbstractWrapper {

    // default constructor
//C++ TO JAVA CONVERTER TODO TASK: The implementation of the following method could not be found:
//  TestWrapper();

    // destructor
//C++ TO JAVA CONVERTER TODO TASK: The implementation of the following method could not be found:
    public void close();

    // method for parsing the SIMPLE source
//C++ TO JAVA CONVERTER TODO TASK: The implementation of the following method could not be found:
    public void parse(String filename);

    // method for evaluating a query
//C++ TO JAVA CONVERTER TODO TASK: The implementation of the following method could not be found:
//    public static void evaluate(String query, LinkedList<String> results);
}

