import java.util.*;

public interface IQuery
{
    // constructor
//    public Query()
//    {
//        this.id = "";
//        this.declarations = "";
//        this.content = "";
//        this.results = "";
//        this.duration = -1;
//    }

    // pairs of accessors and mutators
    public void setId(String n);

    //C++ TO JAVA CONVERTER WARNING: 'const' methods are not available in Java:
//ORIGINAL LINE: String getId() const
    public String getId();

    public void setDeclarations(String s);
    //C++ TO JAVA CONVERTER WARNING: 'const' methods are not available in Java:
//ORIGINAL LINE: String getDeclarations() const
    public String getDeclarations();

    public void setContent(String s);

    //C++ TO JAVA CONVERTER WARNING: 'const' methods are not available in Java:
//ORIGINAL LINE: String getContent() const
    public String getContent();

    public void setResults(String s);

    //C++ TO JAVA CONVERTER WARNING: 'const' methods are not available in Java:
//ORIGINAL LINE: String getResults() const
    public String getResults();

    public void setDuration(int n);

    //C++ TO JAVA CONVERTER WARNING: 'const' methods are not available in Java:
//ORIGINAL LINE: int getDuration() const
    public int getDuration();

    // method to print the query
//C++ TO JAVA CONVERTER WARNING: 'const' methods are not available in Java:
//ORIGINAL LINE: void print(std::ostream& strm) const
    //public void print(String strm);

    // method to get the query string
    public String getQueryString();

    // method to get the expected answers
//C++ TO JAVA CONVERTER TODO TASK: The implementation of the following method could not be found:
//	void getAnswers(STLLinkedList<String> ans);

//    public String id = ""; // the query number (starts from 1)
//    public String declarations = ""; // the declarations (ends with ;)
//    public String content = ""; // the query itself
//    public String results = ""; // the expected results (none or delim = ,)
//    public int duration = -1; // max time to run for query (in millisec)
}

//public class GlobalMembers
//{
//    // method to write the query
//    private std::ostream leftShift(std::ostream strm, final Query q)
//{
//    q.print(strm);
//    return strm;
//}
//}
