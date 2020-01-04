import java.util.*;

//C++ TO JAVA CONVERTER WARNING: The original C++ declaration of the following method implementation was not found:
//ORIGINAL LINE: void Query::getAnswers(STLLinkedList<String>& ans)

public class Query implements IQuery
{
    public String id = ""; // the query number (starts from 1)
    public String declarations = ""; // the declarations (ends with ;)
    public String content = ""; // the query itself
    public String results = ""; // the expected results (none or delim = ,)
    public int duration = -1; // max time to run for query (in millisec)

    public void getAnswers(LinkedList<String> ans)
    {
        // if there are no results, then ans is empty
        if (results.equals("none"))
        {
            return;
        }

        // otherwise, break the answers which are separated by commas
        Tokenizer tokenizer = new Tokenizer(results, ",");
        while (tokenizer.moreToken())
        {
            ans.addLast(tokenizer.nextToken());
        }
    }

    public void setId(String n) {
        id = n;
    }

    public String getId() {
        return id;
    }

    public void setDeclarations(String s) {
        declarations = s;
    }

    public String getDeclarations() {
        return declarations;
    }

    public void setContent(String s) {
        content = s;
    }

    public String getContent() {
        return content;
    }

    public void setResults(String s) {
        results = s;
    }

    public String getResults() {
        return results;
    }

    public void setDuration(int n) {
        duration = n;
    }

    public int getDuration() {
        return duration;
    }

//    public void print(String strm) {
//
//    }

    public String getQueryString() {
        return declarations + " " + content;
    }
}
