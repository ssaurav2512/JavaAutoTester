import com.google.common.base.CharMatcher;
import java.util.*;


public class Tokenizer implements ITokenizer
{

    // constructor
    public Tokenizer(final String str)
    {
        this(str, WHITE);
    }
    //C++ TO JAVA CONVERTER NOTE: Java does not allow default values for parameters. Overloaded methods are inserted above:
//ORIGINAL LINE: Tokenizer(const String& str, const String& delim = WHITE)
    public Tokenizer(final String str, final String delim)
    {
        // initialize
        target = str;
        delimiter = delim;
        position = 0;
    }

    // method to check whether there are more tokens

    // method to check whether there are more tokens
//C++ TO JAVA CONVERTER WARNING: 'const' methods are not available in Java:
//ORIGINAL LINE: boolean moreToken() const
    public final boolean moreToken()
    {
        // check whether reach end of the input string
        if (position == target.length())
        {
            return false;
        }

        // get the next position that is not a delimiter
        int pos = find_first_not_of(delimiter, target);
        return pos != -1;
    }

    public int find_first_of( String sequence, String str ) {
        return CharMatcher.anyOf( str ).indexIn( sequence );
    }

    public int find_first_not_of( String sequence, String str ) {
        return CharMatcher.anyOf( str ).negate().indexIn( sequence );
    }

    public int find_last_of( String sequence, String str ) {
        return CharMatcher.anyOf( str ).lastIndexIn( sequence );
    }

    public int find_last_not_of( String sequence, String str ) {
        return CharMatcher.anyOf( str ).negate().lastIndexIn( sequence );
    }

    // method to get the next token
    public final String nextToken()
    {
        int start = position; // start index
        int end = position; // end index

        // if reach the end of string or beyond, return empty string
        if (start >= target.length())
        {
            return new String();
        }

        // find the first position that is not a delimiter
        start = find_first_not_of(delimiter, target);
        if (start == -1) // reach the end of string
        {
            return new String();
        }

        // find the position of the delimiter
        end = find_first_of(delimiter, target);
        if (end == -1)
        {
            end = target.length();
        }

        // update the position to after extracting
        position = end;
        return trim( target.substring(start, end));
    }

    // method to count the number of tokens

    // method to count the number of tokens
//C++ TO JAVA CONVERTER WARNING: 'const' methods are not available in Java:
//ORIGINAL LINE: int tokenCount() const
    public final int tokenCount()
    {
        // if end of string, no more tokens left
        if (position == target.length())
        {
            return 0;
        }

        // count tokens
        int count = 0;
        int start = position;
        int end = position;
        while (true)
        {
            if (end == -1)
            {
                return count;
            }

            start = find_first_not_of(delimiter, target);
            if (start == -1)
            {
                return count;
            }

            count++;
            end = find_first_of(delimiter, target);
        } // while
    }

    private static final String WHITE = "\r \n\t"; // whitespace characters
    private String target; // string to be tokenized
    private String delimiter; // the delimiter to be used
    private int position; // keeps track of position in string

    // method to trim any whitespace at both ends

    // method to trim whitespace from both ends of a string
    public String trim(String str)
    {
        int start = find_first_not_of(WHITE, str);
        int end = find_last_not_of(WHITE, str);
        return str.substring(start, end + 1);
    }
}

//C++ TO JAVA CONVERTER TODO TASK: The following package should be saved to a separate file:




// whitespace characters
