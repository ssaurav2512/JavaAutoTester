import java.util.*;

public interface ITokenizer
{
    // constructor
    //Tokenizer(final String str, final String delim);

    // method to check whether there are more tokens
//C++ TO JAVA CONVERTER WARNING: 'const' methods are not available in Java:
//ORIGINAL LINE: boolean moreToken() const;
//C++ TO JAVA CONVERTER TODO TASK: The implementation of the following method could not be found:
    boolean moreToken();

    // method to get the next token
//C++ TO JAVA CONVERTER TODO TASK: The implementation of the following method could not be found:
    String nextToken();

    // method to count the number of tokens
//C++ TO JAVA CONVERTER WARNING: 'const' methods are not available in Java:
//ORIGINAL LINE: int tokenCount() const;
//C++ TO JAVA CONVERTER TODO TASK: The implementation of the following method could not be found:
    int tokenCount();

    static final String WHITE = "\r \n\t"; // whitespace characters
    String target = ""; // string to be tokenized
    String delimiter = ""; // the delimiter to be used
    int position = 0; // keeps track of position in string

    // method to trim any whitespace at both ends
//C++ TO JAVA CONVERTER TODO TASK: The implementation of the following method could not be found:
    String trim(String str);
}
