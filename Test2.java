import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Test2 {
    public static void main(String[] args){
        ArrayList<Test2> tokens = new ArrayList<Test2>();
        File file = new File("Q1.txt");
        try{
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String fileAsString = null;
            while((fileAsString = reader.readLine()) != null){
                tokens.addAll(createArrayOfTokens(fileAsString));
            }
        }
        catch(Exception f){
            System.out.println(f);
        }
    }

    //Creates object for all the non-alphanumeric tokens found
    public Test2(String token, String name){
        token = " ";
        name = " ";
    }

    //Creates the list of tokens
    public static ArrayList<Test2> createArrayOfTokens (String test){
        ArrayList<Test2> foundTokens = new ArrayList<Test2>();
        char[] testChar = test.toCharArray();
        for(int i = 0; i < testChar.length;i++){
            if(testChar[i] == '$' || testChar[i] == '@'){
                foundTokens.add(getPerlId(testChar,i));
                continue;
            }
            if(testChar[i] == '"' || testChar[i] == '\''){
                foundTokens.add(getStringAndCharLit(testChar,i));
                continue;
            }
            if(Character.isDigit(testChar[i])){
                foundTokens.add(getIntOrFloat(testChar,i));
                continue;
            }
            if(Character.isWhitespace(testChar[i])){
                continue;
            }
            if(testChar[i] == '+'){
                if(testChar[i+1] == '+'){
                    Test2 object = new Test2("++","increment");
                    foundTokens.add(object);
                    continue;
                }
                else{
                    Test2 object = new Test2("+","addition");
                    foundTokens.add(object);
                    continue;
                }
            }

            if(testChar[i] == '-'){
                if(testChar[i+1] == '-'){
                    Test2 object = new Test2("--","decrement");
                    foundTokens.add(object);
                    continue;
                }
                else if(testChar[i+1] == '>'){
                    Test2 object = new Test2("->","assignment");
                    foundTokens.add(object);
                    continue;
                }
                else{
                    Test2 object = new Test2("--","subtraction");
                    foundTokens.add(object);
                    continue;
                }
            }

            if(testChar[i] == '/'){
                Test2 object = new Test2("/","division");
                foundTokens.add(object);
                continue;
            }

            if(testChar[i] == '*'){
                Test2 object = new Test2("*","multiplication");
                foundTokens.add(object);
                continue;
            }

            if(testChar[i] == '%'){
                Test2 object = new Test2("%","modulo");
                foundTokens.add(object);
                continue;
            }

            if(testChar[i] == '&'){
                if(testChar[i+1] == '&'){
                    Test2 object = new Test2("&&","logicalAndOp");
                    foundTokens.add(object);
                    continue;
                }
                else if(!Character.isWhitespace(testChar[i+1])){
                    errorHandling();
                }
            }

            if(testChar[i] == '|'){
                if(testChar[i+1] == '|'){
                    Test2 object = new Test2("||","logicalOrOp");
                    foundTokens.add(object);
                }
                else{
                    errorHandling();
                }
            }

            if(testChar[i] == '`'){
                Test2 object = new Test2("`","negationOp");
                foundTokens.add(object);
            }

            if(testChar[i] == '{'){
                Test2 object = new Test2("{","openCodeBracket");
                foundTokens.add(object);
            }

            if(testChar[i] == '}'){
                Test2 object = new Test2("}","closeCodeBracket");
                foundTokens.add(object);
            }

            if(testChar[i] == '('){
                Test2 object = new Test2("(","openFunctionParameter");
                foundTokens.add(object);
            }

            if(testChar[i] == ')'){
                Test2 object = new Test2(")","closeFunctionParameter");
                foundTokens.add(object);
            }
        }
        return foundTokens;
    }

    public static Test2 getPerlId(char[] array, int index){
        String name = "";
        int currentIndex = index;

        while(!Character.isWhitespace(array[currentIndex])){
            name += array[currentIndex];
            currentIndex++;
        }
        Test2 object = new Test2(name,"perlIdentifier");
        return object;
    }

    public static Test2 getStringAndCharLit(char[] array, int index){
        String name = "";
        int currentIndex = index;
        Test2 object = new Test2("","");

        while(array[currentIndex] != '"' || array[currentIndex] != '\''){
            name += array[currentIndex];
            currentIndex++;
        }

        if(array[currentIndex] == '"'){
            object = new Test2(name,"javaStringLit");
        }
        else if(array[currentIndex] == '\''){
            object = new Test2(name,"cCharLit");
        }

        return object;
    }

    public static Test2 getIntOrFloat(char[] array, int index){
        String name = "";
        int currentIndex = index;
        boolean isFloat = false;
        Test2 object = new Test2("","");

        while(Character.isDigit(array[currentIndex]) || array[currentIndex] == '.'){
            if(array[currentIndex] == '.'){
                isFloat = true;
            }
            name += array[currentIndex];
            currentIndex++;
        }

        if(isFloat == true){
            object = new Test2(name,"floatNum");
        }
        else if( isFloat == false){
            object = new Test2(name, "intNum");
        }
        return object;
    }

    public static void errorHandling(){
        System.out.print("Invalid token");
    }


}
