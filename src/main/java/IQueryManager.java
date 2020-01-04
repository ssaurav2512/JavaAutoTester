import java.io.*;

public interface IQueryManager extends Closeable {
    // constructor - open the query file
//C++ TO JAVA CONVERTER TODO TASK: The implementation of the following method could not be found:
//	QueryManager(String filename);

    // destructor - close the query file stream
    public void close();

    // method to get the next query, 0 if there is no next query
//C++ TO JAVA CONVERTER TODO TASK: The implementation of the following method could not be found:
    IQuery getQuery(String[] sNewCategory);

    // method to get the next query, 0 if there is no next query
//C++ TO JAVA CONVERTER TODO TASK: The implementation of the following method could not be found:
    IQuery specifyQuery(String UnnamedParameter, String[] sNewCategory) throws IOException;

    // method to get the next query, 0 if there is no next query
//C++ TO JAVA CONVERTER TODO TASK: The implementation of the following method could not be found:
    IQuery specifyQuery(String UnnamedParameter, String UnnamedParameter2, String[] sNewCategory) throws IOException;

    FileInputStream infile = null; // the input stream
//    //   int total;               // the total number of queries
}
