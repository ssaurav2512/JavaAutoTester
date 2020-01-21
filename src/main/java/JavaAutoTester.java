import com.google.common.base.CharMatcher;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

public class JavaAutoTester {

    private static String outputfile = "";
    private static int argument = 0;
    LinkedList<String> stu_ans = new LinkedList<String>; // collects the student's answer
    private boolean bStartExp = false;

    public static int find_last_of(String sequence, String str) {
        return CharMatcher.anyOf( str ).lastIndexIn( sequence );
    }

    public int find_first_not_of( String sequence, String str ) {
        return CharMatcher.anyOf( str ).negate().indexIn( sequence );
    }

    public int find_last_not_of( String sequence, String str ) {
        return CharMatcher.anyOf( str ).negate().lastIndexIn( sequence );
    }

    private static void writeToOutput(String s) throws IOException {
        FileWriter fileWriter = new FileWriter(outputfile);
        fileWriter.write(s);
        fileWriter.close();
    }

    private static void printList(LinkedList<String> ListC, String sBeg, String sAft, String sSep) throws IOException {
        String sFullContent = "";
        Iterator<String> ListIterator;
        int iCount = ListC.size();
        for (ListIterator = ListC.iterator(); ListIterator.hasNext();)
        {
            String current = ListIterator.next();
            System.out.print(current);
            System.out.print(" ");
            String sContent = current;
            iCount--;
            sFullContent += sContent + (iCount == 0?"":sSep);
        }
        writeToOutput(sBeg + sFullContent + sAft + "\n");
        System.out.print("\n");
    }

    private int runOneTest(Object iValue) throws IOException {
        bStartExp = false;
        double save_clock = System.currentTimeMillis();
//        IAbstractWrapper.GlobalStop = false;
        writeToOutput("<query>\n");
        LinkedList<String> right_ans = new LinkedList<String>(); // collects the right's answer
        Query query = (Query) iValue;
        String iId = query.getId();
        int seqID = 0;
        int commentsPos = iId.indexOf("-");
        String comment = "";
        // no tags
        if (commentsPos != -1) {
            String id = iId.substring(0, commentsPos);
            seqID = Integer.parseInt(id);
            comment = iId.substring(commentsPos + 1);
        }
        // none
        else {
            seqID = Integer.parseInt(iId);
        }
        writeToOutput("<id ");
        String[][] arrayTag =
                {
                        {"Follows", "Follows"},
                        {"Follows*", "Followsstar"},
                        {"Parent", "Parent"},
                        {"Parent*", "Parentstar"},
                        {"Modifies", "Modifies"},
                        {"Uses", "Uses"},
                        {"Calls", "Calls"},
                        {"Calls*", "Callsstar"},
                        {"Next", "Next"},
                        {"Next*", "Nextstar"},
                        {"Affects", "Affects"},
                        {"Affects*", "Affectsstar"},
                        {"pattern", "Pattern"},
                        {"with", "With"},
                        {"BOOLEAN", "ReturnBoolean"},
                        {"such", "SuchThat"},
                        {"UnnamedVar", "UnnamedVar"}
                };
        int happenUnnamedVar = 0;
        String queryString = query.getQueryString();
        final String seps = "\\)\\( ";
        String[] token;
        TreeMap<String, Integer> tagInsert = new TreeMap<String, Integer>();
        //We tokenise the queryString here with the help of strtok.
        token = queryString.split(seps);
        // We iterate each token.
        for (int j = 0; j < token.length; j++) {
            String tah = token[j];
            int found = tah.indexOf("_");
            //We check for UnnamedVar "_".
            if (found != -1) {
                System.out.print(tah);
                System.out.print(" is an Unnamedvar ");
                System.out.print("\n");
                happenUnnamedVar = happenUnnamedVar + 1;
//                Iterator<String, Integer> iPairFound = tagInsert.find("UnnamedVar");
                boolean iPairFound = tagInsert.containsKey("UnnamedVar");
//C++ TO JAVA CONVERTER TODO TASK: Iterators are only converted within the context of 'while' and 'for' loops:
                if (iPairFound != false) {
                    tagInsert.remove("UnnamedVar");
                }
                tagInsert.put("UnnamedVar", happenUnnamedVar);
            }
            //Now compare the token with the 17 items in arrayTag to check if the token belongs to which items: Follows* or Parent or Such That
            for (int i = 0; i < 16; i++) {
                if (arrayTag[i][0].equals(tah)) {
                    boolean iPairFound = tagInsert.containsKey("UnnamedVar");
//C++ TO JAVA CONVERTER TODO TASK: Iterators are only converted within the context of 'while' and 'for' loops:
                    if (iPairFound != false) {
                        int happen = 0;
//C++ TO JAVA CONVERTER TODO TASK: Iterators are only converted within the context of 'while' and 'for' loops:
                        happen = tagInsert.get("UnnamedVar");
                        happen = happen + 1;
                        System.out.print("The happen is ");
                        System.out.print(happen);
                        System.out.print("\n");
                        tagInsert.remove(tah);
                        tagInsert.put(tah, happen);
                    } else {
                        tagInsert.put(tah, 1);
                    }
                }
            }
        }
        //To calculate the sum of all the values of all the keys
        int sumOfTagValues = 0;
//C++ TO JAVA CONVERTER TODO TASK: There is no equivalent to implicit typing in Java unless the Java 10 inferred typing option is selected:
//        for (auto iElement = tagInsert.cbegin() ; iElement != tagInsert.cend(); ++iElement) {
//            ////	cout << "The key is " << iElement->first << " and the value is " << iElement->second << "\n";
//            sumOfTagValues = sumOfTagValues + iElement.second;
//        }
        Set<String> keys = tagInsert.keySet();
        for (String key : keys) {
            sumOfTagValues = sumOfTagValues + tagInsert.get(key);
        }
        String tagStr = ""; // Declare the tag string
        boolean booleanFound = tagInsert.containsKey("BOOLEAN");
        // If no boolean, then the query is a tuple.
        if (booleanFound == true) {
            System.out.print("No ReturnBoolean, ReturnTuple=1 ");
            System.out.print("\n");
            tagInsert.put("ReturnTuple", 1);
            tagStr = "ReturnTuple=\"1\" ";
            sumOfTagValues = sumOfTagValues + 1;
        }
        //Now we attempt to build the tagString by iterating through all the items in the tagInsert array.
//C++ TO JAVA CONVERTER TODO TASK: There is no equivalent to implicit typing in Java unless the Java 10 inferred typing option is selected:
        for (String key : keys) {
            for (int i = 0; i < 17; i++) {
                if (arrayTag[i][0] == key) {
//                    std::stringstream out = new std::stringstream();
//                    out << iElement.second;
                    System.out.println(tagInsert.get(key));
//C++ TO JAVA CONVERTER TODO TASK: There are no gotos or labels in Java:
//                    std:
                    String s = String.valueOf(tagInsert.get(key));
                    tagStr = tagStr + arrayTag[i][1] + "=\"" + s + "\" ";
                }
            }
        }
//        std::stringstream outsumOfTagValues = new std::stringstream();
//        outsumOfTagValues << sumOfTagValues;
        String s1 = String.valueOf(sumOfTagValues);
        tagStr = tagStr + "CondNum=\"" + s1 + "\" ";
        // Now we try to obtain the number of relationship items
        int tagRelationshipCount = 0;
//C++ TO JAVA CONVERTER TODO TASK: There is no equivalent to implicit typing in Java unless the Java 10 inferred typing option is selected:
        for (String key : keys) {
            for (int i = 0; i < 12; i++) {
                if (arrayTag[i][0] == key) {
//                    std::stringstream outsumOfTagRelationValues = new std::stringstream();
//                    outsumOfTagRelationValues << iElement.second;
                    String s2 = String.valueOf(tagInsert.get(key));
                    tagRelationshipCount = tagRelationshipCount + tagInsert.get(key);
                }
            }
        }
//        std::stringstream output = new std::stringstream();
//        output << tagRelationshipCount;
        String s = String.valueOf(tagRelationshipCount);
        tagStr = tagStr + "RelNum=" + "\"" + s + "\" ";
        System.out.print("The final tag string is ");
        System.out.print(tagStr);
        System.out.print("\n");
        String tag = tagStr;
        writeToOutput(tag);
        // print comments
//        comment.erase(0, comment.find_first_not_of(" \n"));
        comment = comment.substring(find_first_not_of(" \n",comment),comment.length());
//        comment = find_first_not_of(" \n",comment);
//        comment.erase(comment.find_last_not_of(' ') + 1);
        comment = comment.replace(String.valueOf(comment.charAt(find_last_not_of(" ",comment) + 1)),"");
        if (commentsPos != -1) {
            writeToOutput("comment=\"" + comment + "\"");
        }
        // print id
        String buf = new String(new char[10]);
        buf = String.format("%d", seqID);
        writeToOutput(">" + buf + "</id>");
        writeToOutput("<querystr><![CDATA[" + query.getQueryString() + "]]></querystr>\n");
        int duration = query.getDuration();
//C++ TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: DWORD dwGenericThread;
        int dwGenericThread;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    StartThread(query);
                } catch (IOException e) {
                    System.out.println(e);
                    System.out.print("\n");
                }
            }
        }).start();
        HANDLE hThread1 = CreateThread(null, 0, StartThread, query, 0, dwGenericThread);
        if (hThread1 == null) {
//C++ TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: DWORD dwError = GetLastError();
            int dwError = GetLastError();
            System.out.print("SCM:Error in Creating thread");
            System.out.print(dwError);
            System.out.print("\n");
            return 1;
        }
//C++ TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: DWORD returnWait = WaitForSingleObject(hThread1,duration);
        int returnWait = WaitForSingleObject(hThread1, duration);
        if (returnWait == WAIT_TIMEOUT) {
            AbstractWrapper.GlobalStop = true;
            System.out.print("TIMEOUT");
            System.out.print("\n");
//C++ TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: DWORD returnWait = WaitForSingleObject(hThread1,duration);
            int returnWait = WaitForSingleObject(hThread1, duration);
            if (returnWait == WAIT_TIMEOUT) {
                System.out.print("Warning: Thread Refused to Stop");
                System.out.print("\n");
                WaitForSingleObject(hThread1, INFINITE);
            }
            writeToOutput("<timeout/>\n");
            writeToOutput("</query>\n");
            return 0;
        } else {
            if (!bStartExp) {
                System.out.print("Your answer: ");
                printList(stu_ans, "<stuans>", "</stuans>", ",");
                query.getAnswers(right_ans);
                System.out.print("Correct answer: ");
                printList(right_ans, "<correct>", "</correct>", ",");
                // stop timer and calculate time
//                double clocks_per_ms = (double) CLOCKS_PER_SEC / 1000;
//                double user_time = (clock() - save_clock) / clocks_per_ms;
                double user_time = (System.currentTimeMillis() - save_clock)
                String bufTimeTaken = new String(new char[20]);
                bufTimeTaken = String.format("%lf", user_time);
                writeToOutput("<time_taken>" + bufTimeTaken + "</time_taken>\n");
                verifyAnswers(stu_ans, right_ans);
            }
            writeToOutput("</query>\n");
            return 0;
        }
    }

    private int StartThread(Object iValue) throws IOException {
        Query query = (Query) iValue;
        try {
            stu_ans.clear();
            MyWrapper.evaluate(query.getQueryString(), stu_ans);
        }
        catch (Exception cr) {
            String s = "<exception>";
            s = s + cr;
            s = s + "</exception>\n";
            writeToOutput(s);
            bStartExp = true;
            return 1;
        }
        bStartExp = false;
        return 0;
	/*char lszParam[3];
	  strcpy(lszParam,(char *)iValue);
	  int iStart = atoi(lszParam);
	  for(int i=iStart;i<=iStart+10;i++)
	  cout<<i<<endl;
	  return 0;*/
    }

    void mainThread() {
        HANDLE hThread1,hThread2;
        DWORD dwGenericThread;
        char lszThreadParam[3];
        strcpy(lszThreadParam,"3");
        hThread1 = CreateThread(NULL,0,StartThread,&lszThreadParam,0,&dwGenericThread);
        if(hThread1 == NULL) {
            DWORD dwError = GetLastError();
            cout<<"SCM:Error in Creating thread"<<dwError<<endl ;
            return;
        }
        WaitForSingleObject(hThread1,INFINITE);
        //Second thread creation
        strcpy(lszThreadParam,"30");
        hThread2 = CreateThread(NULL,0,StartThread,&lszThreadParam,0,&dwGenericThread);
        if(hThread1 == NULL) {
            DWORD dwError = GetLastError();
            cout<<"SCM:Error in Creating thread"<<dwError<<endl ;
            return;
        }
        WaitForSingleObject(hThread2,INFINITE);
    }

    private static void verifyAnswers(LinkedList<String> stu_ans, LinkedList<String> my_ans) throws IOException {
        // sort expected and student's answers and remove duplicates from answers
        my_ans.sort(Comparator.comparingInt(String::length));
        stu_ans.sort(Comparator.comparingInt(String::length));
        LinkedHashSet<String> unique = new LinkedHashSet<String>(stu_ans);
        stu_ans.clear();
        stu_ans.addAll(unique);
//        stu_ans.unique();

        // verify the answers (if ignored answer set, automatically pass)
        if (stu_ans == my_ans) {
            // pass the test, write record
            writeToOutput("<passed/>\n");
        } else {
            int iExpected = my_ans.size();
            // fail the test, write record
            writeToOutput("<failed>\n");
            // check for missing answers in students' results
            LinkedList<String> diff_ans = new LinkedList<String>();
//            std::set_difference(my_ans.iterator(), my_ans.end(), stu_ans.iterator(), stu_ans.end(), std::back_inserter(diff_ans));
            diff_ans = my_ans;
            diff_ans.removeAll(stu_ans);
            System.out.print("Missing: ");
            printList(diff_ans,"<missing>","</missing>",",");
            //outfile << "Missing from your answers: "<< ansToString(diff_ans) << std::endl;
            int iMissing = diff_ans.size();
            diff_ans.clear();

            // check for additional answers in students' results
//            std::set_difference(stu_ans.iterator(), stu_ans.end(), my_ans.iterator(), my_ans.end(), std::back_inserter(diff_ans));
            diff_ans = stu_ans;
            diff_ans.removeAll(my_ans);
            System.out.print("Additional: ");
            printList(diff_ans,"<additional>","</additional>",",");
            int iAdditional = diff_ans.size();
            int iMatched = stu_ans.size() - iAdditional;
            diff_ans.clear();

            writeToOutput("<summary>\n");
            writeToOutput("<expected>");
            String idbufE = new String(new char[20]);
            idbufE = String.format("%d", iExpected);
            writeToOutput(idbufE);
            writeToOutput("</expected>\n");
            writeToOutput("<matched>");
            String idbufM = new String(new char[20]);
            idbufM = String.format("%d", iMatched);
            writeToOutput(idbufM);
            writeToOutput("</matched>\n");
            writeToOutput("<missing>");
            String idbufMis = new String(new char[20]);
            idbufMis = String.format("%d", iMissing);
            writeToOutput(idbufMis);
            writeToOutput("</missing>\n");
            writeToOutput("<additional>");
            String idbufA = new String(new char[20]);
            idbufA = String.format("%d", iAdditional);
            writeToOutput(idbufA);
            writeToOutput("</additional>\n");
            writeToOutput("</summary>\n");
            writeToOutput("</failed>\n");
        }
    }

    private static void evaluateAll(IQueryManager query_mgr, String outputfile, String fqueryID, String timeout) throws IOException {
        writeToOutput("<queries>\n");
	/*char str[2000];
	fstream file_op(queryfile.c_str(),ios::in);
	while (!file_op.eof())
	{
		file_op.getline(str,2000);
		cout << str << endl;
	}
	file_op.close();
	cout << endl;*/

        String[] sNewCategory = new String[0];
        boolean bFirst = true;
        // iterate over all the test queries
        IQuery query = null;
        if (argument == 4) {
            query = query_mgr.getQuery(sNewCategory);
        } else if (argument == 6) {
            query = query_mgr.specifyQuery(fqueryID, sNewCategory);
        } else if (argument == 8) {
            query = query_mgr.specifyQuery(fqueryID, timeout, sNewCategory);
        }
        while (query != null) {
		/*
		if (sNewCategory != 0 && (*sNewCategory) != "")
		{
			if (bFirst)
				bFirst = false;
			else
				writeToOutput (string("</category>\n"));

			writeToOutput (string("<category name=\"")+(*sNewCategory)+string("\">\n"));
		}
		*/
            System.out.print("Evaluating query ");
            System.out.print(query.getId());
            System.out.print("\n");
            // run this test

            HANDLE hThread1 = new HANDLE();
//C++ TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: DWORD dwGenericThread;
            int dwGenericThread;

            //cout << "Before entering runOneTest" << "\n";

            hThread1 = CreateThread(null, 0, runOneTest, query, 0, dwGenericThread);
            if (hThread1 == null)
            {
//C++ TO JAVA CONVERTER WARNING: Unsigned integer types have no direct equivalent in Java:
//ORIGINAL LINE: DWORD dwError = GetLastError();
                int dwError = GetLastError();
                System.out.print("SCM:Error in Creating thread");
                System.out.print(dwError);
                System.out.print("\n");
                return;
            }
            WaitForSingleObject(hThread1,INFINITE);
		/*hThread1 = CreateThread(NULL,0,runOneTest,query,0,&dwGenericThread);
		if(hThread1 == NULL)
		{
			DWORD dwError = GetLastError();
			cout<<"SCM:Error in Creating thread"<<dwError<<endl ;
			return;
		}
		WaitForSingleObject(hThread1,INFINITE);*/

            //runOneTest(query,wr,outputfile);
            // get the next query
            query = null;
            sNewCategory = null;

            if ((argument == 4) || (argument == 6))
            {
                query = query_mgr.getQuery(sNewCategory);
            }

            //query = query_mgr->getQuery(&sNewCategory);
        } // while
        if (!bFirst)
        {
            writeToOutput("</category>\n");
        }
        writeToOutput("</queries>\n");
    }

    private static void Main(int argc, String[] args) throws IOException {
        //cout << "This is the main" << "\n";
        if (argc < 4) {
            System.out.print("usage: AutoTester source_file query_file output_file");
            System.out.print("\n");
            System.exit(1);
        }
        String simplefile = "";
        String queryfile = "";
        String queryID = "";
        String fqueryID = "";
        String timeout = "";

        if (argc == 4) {
            System.out.print("Parameters : ");
            System.out.print(args[1]);
            System.out.print(" ");
            System.out.print(args[2]);
            System.out.print(" ");
            System.out.print(args[3]);
            System.out.print("\n");
            simplefile = args[argc - 3];
            queryfile = args[argc - 2];
            outputfile = args[argc - 1];
            fqueryID = "1";
            timeout = "5000";
            argument = 4;
        }
        if (argc == 6) {
            System.out.print("Parameters : ");
            System.out.print(args[1]);
            System.out.print(" ");
            System.out.print(args[2]);
            System.out.print(" ");
            System.out.print(args[3]);
            System.out.print(" ");
            System.out.print(args[4]);
            System.out.print(" ");
            System.out.print(args[5]);
            System.out.print(" ");
            System.out.print("\n");
            simplefile = args[argc - 5];
            queryfile = args[argc - 4];
            outputfile = args[argc - 3];
            fqueryID = args[argc - 1];
            argument = 6;
        }
        if (argc == 8) {
            System.out.print("Parameters : ");
            System.out.print(args[1]);
            System.out.print(" ");
            System.out.print(args[2]);
            System.out.print(" ");
            System.out.print(args[3]);
            System.out.print(" ");
            System.out.print(args[4]);
            System.out.print(" ");
            System.out.print(args[5]);
            System.out.print(" ");
            System.out.print(args[6]);
            System.out.print(" ");
            System.out.print(args[7]);
            System.out.print(" ");
            System.out.print("\n");
            simplefile = args[argc - 7];
            queryfile = args[argc - 6];
            outputfile = args[argc - 5];
            fqueryID = args[argc - 3];
            timeout = args[argc - 1];
            argument = 8;
        }
        File file = new File(outputfile);
        file.delete();
        MyWrapper wrapper = new MyWrapper();
        double save_clock = System.currentTimeMillis();

        System.out.print("Beginning to parse Simple Program.");
        System.out.print("\n");

        wrapper.parse(simplefile);

        System.out.print("End of parsing Simple Program.");
        System.out.print("\n");

//        double clocks_per_ms = (double)CLOCKS_PER_SEC / 1000;
        double user_time = (System.currentTimeMillis() - save_clock);
        String bufTimeTaken = new String(new char[20]);
        bufTimeTaken = String.format("%d", user_time);

        QueryManager qman = new QueryManager(queryfile);
        writeToOutput("<?xml-stylesheet type=\"text/xsl\" href=\"analysis.xsl\"?>\n");
        writeToOutput("<test_results>\n");

        writeToOutput("<info>\n");

//        final String curDir = "hello";
//        Directory.GetCurrentDirectory(1000,curDir);
//
//        String sCurDir = curDir;
        String sCurDir = System.getProperty("user.dir");
        int iPos = find_last_of("/", sCurDir);
        if (iPos == -1) {
            iPos = find_last_of("\\", sCurDir);
        }
        sCurDir = sCurDir.substring(iPos + 1, iPos + 1 + 100);
        System.out.print(sCurDir);
        System.out.print("\n");
        writeToOutput("<name>" + sCurDir + "</name>");
        writeToOutput("<parsing_time_taken>" + bufTimeTaken + "</parsing_time_taken>\n");

        writeToOutput("</info>\n");

        System.out.print("Beginning to evaluate Query File.");
        System.out.print("\n");

        evaluateAll(qman,outputfile,fqueryID, timeout);

        System.out.print("End of evaluating Query File.");
        System.out.print("\n");


        writeToOutput("</test_results>\n");
        System.out.print("AutoTester Completed !\n");
    }
}
