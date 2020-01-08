//C++ TO JAVA CONVERTER WARNING: The original C++ declaration of the following method implementation was not found:
//ORIGINAL LINE: QueryManager::QueryManager(String filename)

import com.google.common.base.CharMatcher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

public class QueryManager extends Query implements IQueryManager
{
    public QueryManager(String filename) throws FileNotFoundException {
        // open the query file
        //infile.open(filename);
        File ifile = new File(filename);

        // check whether file was really opened
        if (ifile.exists()) {
            FileInputStream infile = new FileInputStream(filename);
        } else {
            // fail - throw exception
            throw new FileNotFoundException("cannot open file " + filename + " for reading");
        }

        // read in the total number of queries
	 /*
		std::string buffer;
	    std::getline(infile, buffer, '\n');
	    total = atoi(buffer.c_str());
	  */
    }

    public void close() {

    }

    public IQuery getQuery(String[] newCategory) throws IOException {
        if (infile.available() == 0)
        {
            return null;
        }

        // otherwise, retrieve the next query - 5 lines
        Query q = new Query(); // memory deallocated by the query thread
        //      std::string cat("cat:");
        String buffer = "";
        //    std::getline(infile, buffer, '\n');
        //   std::string sFirstLine = std::string(buffer);


	/*
		if (buffer.find (cat) != std::string::npos) {
			std::string Category = sFirstLine.substr (4);
			*newCategory = new std::string(Category.c_str());
			std::cout << "New Category : " << (**newCategory) << std::endl;
			std::getline(infile, buffer, '\n');        // get id
		}

		std::string sFirstLine = "THE";
		std::string Category = sFirstLine;
		*newCategory = new std::string(Category.c_str());
	*/
//        getline(infile, buffer, '\n'); // get id
        assert infile != null;
        Scanner scanner = new Scanner(infile);
        scanner.useDelimiter("\n");
        if(scanner.hasNext()) {
            buffer = scanner.next();
        }
		/*
		if (buffer.find(whitespaces) != std::string.npos){
			std::string gotorno = "yes";
		}
		*/
        if (!buffer.equals("")) {
            String gotTabSpace = "no";
            String tabspace = "\t";
            int foundTabSpace = find_first_of(tabspace, buffer);
            if (foundTabSpace == 0) {
                gotTabSpace = "yes";
            }
            String gotWhiteSpace = "no";
            String whitespace = " ";
            int foundWhiteSpace = find_first_of(whitespace, buffer);
            if (foundWhiteSpace == 0) {
                gotWhiteSpace = "yes";
            }
            if ((gotTabSpace.equals("no")) && (gotWhiteSpace.equals("no"))) {
                String comment = "\\\\";
                while (buffer.indexOf(comment) != -1) {
                    // std::cout << "Found a comment line !!!" << std::endl;
//                    getline(infile, buffer, '\n'); // get old declaration
                    scanner.useDelimiter("\n");
                    if(scanner.hasNext()) {
                        buffer = scanner.next();
                    }
//                    getline(infile, buffer, '\n'); // get old content query
                    scanner.useDelimiter("\n");
                    if(scanner.hasNext()) {
                        buffer = scanner.next();
                    }
//                    getline(infile, buffer, '\n'); // get old results
                    scanner.useDelimiter("\n");
                    if(scanner.hasNext()) {
                        buffer = scanner.next();
                    }
//                    getline(infile, buffer, '\n'); // get old duration
                    scanner.useDelimiter("\n");
                    if(scanner.hasNext()) {
                        buffer = scanner.next();
                    }
//                    getline(infile, buffer, '\n'); // get new id
                    scanner.useDelimiter("\n");
                    if(scanner.hasNext()) {
                        buffer = scanner.next();
                    }
                }
                System.out.print(buffer);
                System.out.print("\n");
                q.setId(buffer);
//                getline(infile, buffer, '\n'); // get declarations
                scanner.useDelimiter("\n");
                if(scanner.hasNext()) {
                    buffer = scanner.next();
                }
                System.out.print(buffer);
                System.out.print("\n");
                q.setDeclarations(buffer);
//                getline(infile, buffer, '\n'); // get content of query
                scanner.useDelimiter("\n");
                if(scanner.hasNext()) {
                    buffer = scanner.next();
                }
                System.out.print(buffer);
                System.out.print("\n");
                q.setContent(buffer);
//                getline(infile, buffer, '\n'); // get results
                scanner.useDelimiter("\n");
                if(scanner.hasNext()) {
                    buffer = scanner.next();
                }
                System.out.print(buffer);
                System.out.print("\n");
                q.setResults(buffer);
//                getline(infile, buffer, '\n'); // get duration
                scanner.useDelimiter("\n");
                if(scanner.hasNext()) {
                    buffer = scanner.next();
                }
                System.out.print(buffer);
                System.out.print("\n");
                q.setDuration(Integer.parseInt(buffer));
            } else {
                q = null;
            }
            // return the query
            //return q;
        } else {
            q = null;
            //return q;
        }
        return q;
    }

    public IQuery specifyQuery(String queryID, String[] newCategory) throws IOException {
        // newCategory = new std::string ("X");
        // reached the last query
        if (infile.available() == 0)
        {
            return null;
        }


        Query q = new Query();
        //	  std::string cat("cat:");
        String buffer = "";
        //getline(infile, buffer, '\n'); // get nextline
        //	  std::string sFirstLine = std::string(buffer);
        assert infile != null;
        Scanner scanner = new Scanner(infile);
        scanner.useDelimiter("\n");
        if(scanner.hasNext()) {
            buffer = scanner.next();
        }
	/*
		  if (buffer.find (cat) != std::string::npos) {
			std::string Category = sFirstLine.substr (4);
			*newCategory = new std::string(Category.c_str());
			std::cout << "New Category : " << (**newCategory) << std::endl;
			std::getline(infile, buffer, '\n');        // get id
		  }
	 */
        while (buffer.indexOf(queryID) == -1)
        {
            //getline(infile, buffer, '\n'); // get id
            scanner.useDelimiter("\n");
            if(scanner.hasNext()) {
                buffer = scanner.next();
            }
        }
        System.out.print(buffer);
        System.out.print("\n");
        q.setId(buffer);
//        getline(infile, buffer, '\n'); // get declarations
        scanner.useDelimiter("\n");
        if(scanner.hasNext()) {
            buffer = scanner.next();
        }
        System.out.print(buffer);
        System.out.print("\n");
        q.setDeclarations(buffer);
//        getline(infile, buffer, '\n'); // get content of query
        scanner.useDelimiter("\n");
        if(scanner.hasNext()) {
            buffer = scanner.next();
        }
        System.out.print(buffer);
        System.out.print("\n");
        q.setContent(buffer);
//        getline(infile, buffer, '\n'); // get results
        scanner.useDelimiter("\n");
        if(scanner.hasNext()) {
            buffer = scanner.next();
        }
        System.out.print(buffer);
        System.out.print("\n");
        q.setResults(buffer);
//        getline(infile, buffer, '\n'); // get duration
        scanner.useDelimiter("\n");
        if(scanner.hasNext()) {
            buffer = scanner.next();
        }
        System.out.print(buffer);
        System.out.print("\n");
        q.setDuration(Integer.parseInt(buffer));
        return q;

    }

    public IQuery specifyQuery(String queryID, String timeout, String[] newCategory) throws IOException {
        if (infile.available() == 0)
        {
            return null;
        }


        Query q = new Query();
//	  std::string cat("cat:");
        String buffer = "";
        assert infile != null;
        Scanner scanner = new Scanner(infile);
        scanner.useDelimiter("\n");
        if(scanner.hasNext()) {
            buffer = scanner.next();
        }
//        getline(infile, buffer, '\n'); // get nextline
//	  std::string sFirstLine = std::string(buffer);

/*
	  if (buffer.find (cat) != std::string::npos) {
		std::string Category = sFirstLine.substr (4);
		*newCategory = new std::string(Category.c_str());
		std::cout << "New Category : " << (**newCategory) << std::endl;
		std::getline(infile, buffer, '\n');        // get id
	  }
 */
        while (buffer.indexOf(queryID) == -1)
        {
//            getline(infile, buffer, '\n'); // get id
            scanner.useDelimiter("\n");
            if(scanner.hasNext()) {
                buffer = scanner.next();
            }
        }
        System.out.print(buffer);
        System.out.print("\n");
        q.setId(buffer);
//        getline(infile, buffer, '\n'); // get declarations
        scanner.useDelimiter("\n");
        if(scanner.hasNext()) {
            buffer = scanner.next();
        }
        System.out.print(buffer);
        System.out.print("\n");
        q.setDeclarations(buffer);
//        getline(infile, buffer, '\n'); // get content of query
        scanner.useDelimiter("\n");
        if(scanner.hasNext()) {
            buffer = scanner.next();
        }
        System.out.print(buffer);
        System.out.print("\n");
        q.setContent(buffer);
//        getline(infile, buffer, '\n'); // get results
        scanner.useDelimiter("\n");
        if(scanner.hasNext()) {
            buffer = scanner.next();
        }
        System.out.print(buffer);
        System.out.print("\n");
        q.setResults(buffer);
//        getline(infile, buffer, '\n'); // get duration
        scanner.useDelimiter("\n");
        if(scanner.hasNext()) {
            buffer = scanner.next();
        }
        System.out.print(buffer);
        System.out.print("\n");
        q.setDuration(Integer.parseInt(buffer));
        return q;
    }

    public int find_first_of( String sequence, String str ) {
        return CharMatcher.anyOf( str ).indexIn( sequence );
    }
}
