import java.util.LinkedList;

//private AbstractWrapper * WrapperFactory.wrapper = 0;
//C++ TO JAVA CONVERTER WARNING: The original C++ declaration of the following method implementation was not found:
//ORIGINAL LINE: AbstractWrapper* WrapperFactory::createWrapper()

//C++ TO JAVA CONVERTER TODO TASK: 'volatile' has a different meaning in Java:
//ORIGINAL LINE: volatile boolean TestWrapper::GlobalStop = false;
//private boolean TestWrapper.GlobalStop = false;

// a default constructor
//C++ TO JAVA CONVERTER WARNING: The original C++ declaration of the following method implementation was not found:
//ORIGINAL LINE: TestWrapper::TestWrapper()

// method for parsing the SIMPLE source
//C++ TO JAVA CONVERTER WARNING: The original C++ declaration of the following method implementation was not found:
//ORIGINAL LINE: void TestWrapper::parse(String filename)

public class MyWrapper implements ITestWrapper, IAbstractWrapper {
//    public AbstractWrapper createWrapper() {
//        if (GlobalMembers.wrapper == null)
//        {
//            GlobalMembers.wrapper = new TestWrapper();
//        }
//        return GlobalMembers.wrapper;
//    }

    public void close() {

    }

    public void parse(String filename) {
        int[] x = new int[10000];

        // throw "Testing parse error!";
        System.out.print("Init start parsing...");
        System.out.print("\n");
        for (int i = 0; i < 5; i++)
        {
            for (int j = 0; j < 50000000; j++)
            {
                ;
            }
        }
        System.out.print("Init parsing completed!");
        System.out.print("\n");
    }

    public void evaluate(String query, LinkedList<String> results) {
        System.out.print(query);
        System.out.print("\n");
        try {
            if (query.equals("stmt s, s1; Select s such that Parent(s, s1) with s1.stmt# = 16")) {
                results.add("14");
            } else if (query.equals("stmt s; Select s such that Parent(s, 19)")) {
            } else if (query.equals("stmt s; Select s such that Parent(6, s)")) {
                results.add("7");
                results.add("8");
            } else if (query.equals("while w; Select w such that Parent(w, 10)")) {
            } else if (query.equals("while w; Select w such that Parent*(w, 17)")) {
                results.add("14");
            } else if (query.equals("stmt s; Select s such that Follows(s, 4)")) {
                throw new RuntimeException("Pattern not allowed");
            } else if (query.equals("assign a; Select a such that Follows(a, 6)")) {
                results.add("5");
            } else if (query.equals("while w; Select w such that Follows(3, w)")) {
                results.add("4");
            } else if (query.equals("stmt s; Select s such that Follows*(s, 11)")) {
                results.add("9");
                results.add("10");
                results.add("5");
                results.add("6");
                results.add("9");
                results.add("10");
                results.add("5");
                results.add("6");
                results.add("9");
                results.add("10");
            } else if (query.equals("if ifs; Select ifs such that Follows*(ifs, 4)")) {
                results.add("13");
            } else if (query.equals("assign a; Select a such that Follows*(a, 12)")) {
                results.add("4");
                results.add("2");
                results.add("3");
                results.add("5");
            } else if (query.equals("assign a; Select a pattern a(\"x\", _)")) {
                results.add("1");
                results.add("5");
                results.add("15");
                results.add("18");
                results.add("24");
            } else if (query.equals("assign a; Select a pattern a(\"x\", _\"2 *y\")")) {
                results.add("20");
                results.add("15");
            } else if (query.equals("assign a; while w; Select a pattern a(\"x\", _) such that Follows(w, a)")) {
                results.add("18");
            } else if (query.equals("stmt s; Select BOOLEAN such that Next(4, 12)")) {
                results.add("true");
            } else if (query.equals("stmt s; Select s such that Next(s, 3)")) {
                results.add("2");
            } else if (query.equals("stmt s; Select s such that Next(4, s)")) {
                results.add("5");
                results.add("12");
            } else if (query.equals("assign a; Select a such that Modifies(a, \"z\")")) {
                results.add("2");
                results.add("7");
                results.add("9");
                results.add("19");
                results.add("20");
                results.add("21");
                results.add("23");
            } else if (query.equals("while w; Select w such that Modifies(w, \"i\")")) {
                results.add("4");
            } else if (query.equals("assign a; Select a such that Uses(a, \"i\")")) {
                results.add("9");
                results.add("11");
                results.add("17");
                results.add("21");
                results.add("9");
                results.add("11");
                results.add("17");
                results.add("21");
                results.add("17");
                results.add("21");
                results.add("30");
                results.add("28");
            } else if (query.equals("variable v; Select v such that Uses(4, v)")) {
                results.add("x");
                results.add("z");
                results.add("z");
                results.add("z");
            } else if (query.equals("variable v; assign a; Select v such that Uses(a, v)")) {
                results.add("x");
                results.add("y");
                results.add("z");
                results.add("w");
                results.add("x");
                results.add("y");
                results.add("z");
                results.add("x");
                results.add("y");
                results.add("z");
            } else if (query.equals("assign a; Select a such that Modifies(a, \"i\") and Uses(a, \"i\")")) {
                results.add("11");
                results.add("17");
            } else if (query.equals("assign a; while w; Select a such that Modifies(a, \"x\") and Parent(w, a)")) {
                int k = 0;
                for (int i = 0; i < 5; i++) {
                    k = i + k;
                    for (int j = 0; j < 500000000; j++) {
                        k = k + k;
                    }
                }
                //std::cout << "Shouldn't print" << std::endl;
            } else if (query.equals("assign a; Select a such that Live(a, 8)")) {
                results.add("2");
                results.add("5");
            } else if (query.equals("variable v; Select a such that Live(5, v)")) {
                results.add("7");
                results.add("8");
                results.add("9");
            } else if (query.equals("assign a; Select a such that Next*(a, 15)")) {
            } else if (query.equals("assign a; Select a such that Next*(5, a)")) {
                results.add("7");
                results.add("8");
                results.add("9");
            } else if (query.equals("assign a; while w; Select a such that Parent(w, a) and Modifies(a, \"x\")")) {
                results.add("5");
                results.add("15");
            } else if (query.equals("while w; Select w")) {
                results.add("4");
                results.add("14");
            } else if (query.equals("assign s1, s2, s3; Select <s1, s3> with s2.stmt# = 9 such that Follows*(s1, s2) and Follows*(s2, s3)")) {
                results.add("5 11");
            } else if (query.equals("procedure p, q; Select <p, q> such that Calls(p, q)")) {
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 500000000; j++) {
                        if (IAbstractWrapper.GlobalStop) {
                            System.out.print("receive cancel: cleaning up");
                            System.out.print("\n");
                            return;
                        }
                    }
                }
                results.add("6 x");
                results.add("6 z");
                results.add("13 x");
                results.add("13 z");
                results.add("13 i");
                results.add("13 y");
                results.add("22 x");
                results.add("22 z");
            } else if (query.equals("variable v; if ifs; Select <ifs, v> such that Uses(ifs, v)")) {
                results.add("6 x");
                results.add("6 z");
                results.add("13 x");
                results.add("13 z");
                results.add("13 i");
                results.add("13 y");
                results.add("22 x");
                results.add("22 z");
            } else if (query.equals("while w; call c; Select w such that Follows*(w, c)")) {
                results.add("3");
                results.add("1");
                results.add("2");
            } else if (query.equals("stmt s; while w; Select <w, s> such that Parent*(w, s)")) {
                results.add("4 5");
                results.add("4 6");
                results.add("4 7");
                results.add("4 8");
                results.add("4 9");
                results.add("4 10");
                results.add("4 11");
                results.add("14 15");
                results.add("14 16");
                results.add("14 17");
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
}