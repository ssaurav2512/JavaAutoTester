import java.util.*;

public interface IAbstractWrapper
{
    //C++ TO JAVA CONVERTER TODO TASK: 'volatile' has a different meaning in Java:
//ORIGINAL LINE: static volatile boolean GlobalStop;
    public static boolean GlobalStop = false;
    // method for parsing the SIMPLE source
    void parse(String filename);

    // method for evaluating a query
    static void evaluate(String query, LinkedList<String> results) {

    }
}