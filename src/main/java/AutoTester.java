import com.google.common.base.CharMatcher;
import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;

public class AutoTester {

    private static String outputfile = "";
    private static int argument = 0;

    public static int find_last_of(String sequence, String str) {
        return CharMatcher.anyOf( str ).lastIndexIn( sequence );
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
