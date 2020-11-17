import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.*;
import java.util.*;
public class LexemeTester {
	public static void main(String[] args) {
		File myFile = new File("src/question1.txt");
		ArrayList<LexemeTester> tokens = new ArrayList<LexemeTester>();
		
		try{
            BufferedReader reader = new BufferedReader(new FileReader(myFile));
            String lineNow = reader.readLine();
            String fromthefile = "";
            while(lineNow != null){
                for(int i = 0; i < lineNow.length();i++){
                    char x = lineNow.charAt(i);
                    fromthefile += x;
                }
            }

            tokens = getMeSomeTokens(fromthefile);
        }
        catch(Exception f){
            System.out.println(f);
        }
		
	}
		
	public static ArrayList<LexemeTester> getMeSomeTokens(String s) {
		ArrayList<LexemeTester> tokens = new ArrayList<LexemeTester>(); 
		ArrayList<Character> chars = new ArrayList<Character>();
			for (char c : s.toCharArray()) {
			  chars.add(c);
			}
	    	  for(int i=0; i<chars.size(); i++) {
	        	  char x = chars.get(i);
	               
	        	  //Perl style identifiers
	        	  if(x == '$' || x == '%' || x == '@') {
	        		  for(int j=i+1; j<chars.size(); j++) {
	        			  String holdMe = "";
	            		  char y = chars.get(j);
	            		  if(y != '*') {
	            			  holdMe += y;
	            			  continue;
	            		  }
	            		  else {
		            		  LexemeTester token = new LexemeTester(holdMe,"Perl");
		            		  tokens.add(token);
		            		  continue;
	            		  }
	        		  }
	              }

	              //Java-Style string literals
	              if(x == '<') {	            	   
	            	  for(int j=i+1; j<chars.size(); j++) {
	            		  String holdMe = "";
	            		  char y = chars.get(j);
	            		  if(y != '<' ) {
	            			  holdMe += y;
	            			  continue;
	            		  }
	            		  else {
	            			  LexemeTester token = new LexemeTester(holdMe,"Java Style String Literal");
		            		  tokens.add(token);
		            		  continue;
	            		  }
	            	  }
	              }

	              //C-Style integer literals
	              if(x == '#') {            	   
	            	  for(int j=i+1; j<chars.size(); j++) {
	            		  String holdMe = "";
	            		  char y = chars.get(j);
	            		   
	            		  if(Character.isDigit(y) == true) {
	            			  holdMe += y;
	            			  continue;
	            		  }
	            		   
	            		  if(Character.toString(y).matches("[A-F?]") || Character.toString(y).matches("[a-f?]")) {
	            			  holdMe += y;
	            			  continue;
	            		  }
	            		   
	            		  else {
	            			  LexemeTester token = new LexemeTester(holdMe,"C Style Integer Literal");
		            		  tokens.add(token);
		            		  continue;
	            		  }
	            		   
	            	  }
	              }

	               
	              //C-Style character literals
	              if(x == '\'') {	            	   
	            	  for(int j=i+1; j<chars.size(); j++) {
	            		  String holdMe = "";
	            		  char y = chars.get(j);  
	            		   
	            		  if(y != '\'') {
	            			  holdMe += y;
	            			  continue;
	            		  }
	            		  
	            		  else {
	            			  LexemeTester token = new LexemeTester(holdMe,"C Style Character Literal");
		            		  tokens.add(token);
		            		  continue;
	            		  }
	            		   
	            		  }
	            	  }
	               
	              //C-Style floating point literals
	              if(x == '.') {	            	   
	            	  for(int j=i+1; j<chars.size(); j++) {
	            		  String holdMe = "";
	            		  char y = chars.get(j);
	            		   
	            		  if(y != '.') {
	            			  holdMe += y;
	            			  continue;
	            		  }
	            		  
	            		  else {
	            			  LexemeTester token = new LexemeTester(holdMe,"C Style Floating Point Literal");
		            		  tokens.add(token);
		            		  continue;
	            		  }
	            		   
	            		  }

	            	  }
	               
	              //Addition or Increment
	              if(x == '+') {
	            	  if(chars.get(i+1) == '+') {
	            		  LexemeTester token = new LexemeTester("++","Increment");
	            		  tokens.add(token);
	            		  continue;
	            	  }
	            	   
	            	  else {
	            		  LexemeTester token = new LexemeTester("+","Addition");
	            		  tokens.add(token);
	            		  continue;
	            	  }
	              }
	               
	              // Assignment
	              if(x == '=') {
	            	  LexemeTester token = new LexemeTester("=","Assignment");
            		   tokens.add(token); 
            		   continue;
	              }
	               
	              //Subtraction or Decrement
	              if(x == '-') {
	            	  if(chars.get(i+1) == '-') {
	            		  LexemeTester token = new LexemeTester("--","Decrement");
	            		  tokens.add(token);
	            		  continue;
	            	  }
	            	   
	            	  else {
	            		  LexemeTester token = new LexemeTester("-","Subtraction");
	            		  tokens.add(token);
	            		  continue;
	            	  }
	              }

	              // Division
	              if(x == '/') {
	            	  LexemeTester token = new LexemeTester("/","Division");
            		   tokens.add(token);
            		   continue;
	              }

	              // Multiplication
	              if(x == '*') {
	            	  LexemeTester token = new LexemeTester("*","Multiplication");
            		   tokens.add(token); 
            		   continue;
	              }

	              // Modulo
	              if(x == '%') {
	            	  LexemeTester token = new LexemeTester("%","Modulo");
            		   tokens.add(token);  
            		   continue;
	              }

	              // Logical NOT
	              if(x == '!') {
	            	  LexemeTester token = new LexemeTester("!","Logical Not");
            		   tokens.add(token);
            		   continue;
	              }

	              // Logical OR
	              if(x == '|') {
	            	  if(chars.get(i+1) == '|') {
	            		  LexemeTester token = new LexemeTester("||","Logical Or");
	            		  tokens.add(token);
	            		  continue;
	            	  }
	              }

	              // Logical AND
	              if(x == '&') {
	            	  if(chars.get(i+1) == '&') {
	            		  LexemeTester token = new LexemeTester("&&","Logical And");
	            		  tokens.add(token);
	            		  continue;
	            	  } 
	              }

	              // Open code block
	              if(x == '{') {
	            	  LexemeTester token = new LexemeTester("{","Open Code Block");
            		   tokens.add(token);
            		   continue;
	              }
	               
	              // Close code block
	              if(x == '}') {
	            	  LexemeTester token = new LexemeTester("}","Close Code Block");
	            	  tokens.add(token);
	            	  continue;
	              }
	               
	              // Open Function parameter
	              if(x == '(') {
	            	  LexemeTester token = new LexemeTester("(","Open Function Parameter");
	            	  tokens.add(token); 
	            	  continue;
	              }
	               
	              // Close Function parameter
	              if(x == ')') {
	            	  LexemeTester token = new LexemeTester("(","Close Function Parameter");
	            	  tokens.add(token); 
	            	  continue;
	              }
	               
	               
	    	  }//for loop end
	    	   
	    	  return tokens;
	    	   
	}//end of getMeSomeTokens 

	
	public LexemeTester (String token, String tokenName){
        token = "";
        tokenName = "";
    }
}
