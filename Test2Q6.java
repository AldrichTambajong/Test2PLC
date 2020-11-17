import java.util.ArrayList;

public class Test2Q6 {
    /*
        whileLoop --> "while (" <parameter> "){" <expressions> "}"
        ifStmt --> "if (" <parameter> ") {" <expressions> "} "{<elseif>}{<else>}
        mathAssign --> {a-Z}{0-9} "=" {0-9}
        elseif-->"else if (" <parameter> ") {" <expression> "}"
        else --> "else {" <expression> "}"
        <parameter> --> {a-Z}{0-9} (== | > | < | <= | >= | != | && | ||) {a-Z}{0-9}

     */
    public static int subIndex = 0;
    public static String subString = "";
    public Test2Q6(String op, String name){
        op = "";
        name = "";
    }

    public static void makeTokens(String input){
        ArrayList<Test2Q6> tokenArray = new ArrayList<Test2Q6>();
        char[] charArr = input.toCharArray();
        String test ="";

        for(int i =0; i < charArr.length;i++){
            if(charArr[i] == 'w'){
                String target = "while";
                int currentIndex = i;
                test += charArr[i];

                while(test.length() < 5){
                    test += charArr[currentIndex+1];
                    currentIndex++;
                }

                currentIndex++;
                if(charArr[currentIndex] != ' '){
                    while(charArr[currentIndex] != ' ') {
                        test += charArr[currentIndex];
                        currentIndex++;
                    }
                }
                else if(test == target){
                        Test2Q6 object = new Test2Q6(test,"whileLoop");
                        tokenArray.add(object);
                        whileLoopIfParam(charArr, currentIndex);
                        i = subIndex;
                        Test2Q6 object2 = new Test2Q6(subString,"parameter");
                        tokenArray.add(object2);
                }
            }
            else if(charArr[i] == 'i'){
                String target = "if";
                int currentIndex = i;
                test += charArr[i];

                while(test.length() < 2){
                    test += charArr[currentIndex+1];
                    currentIndex++;
                }

                currentIndex++;
                if(charArr[currentIndex] != ' '){
                    while(charArr[currentIndex] != ' ') {
                        test += charArr[currentIndex];
                        currentIndex++;
                    }
                }
                else if(test == target){
                    Test2Q6 object = new Test2Q6(test,"ifStmt");
                    tokenArray.add(object);
                    whileLoopIfParam(charArr, currentIndex);
                    i = subIndex;
                    Test2Q6 object2 = new Test2Q6(subString,"parameter");
                    tokenArray.add(object2);
                }
            }
        }
    }

    public static void whileLoopIfParam(char[] array, int index){
        for(int i = index; i < array.length; i++){
            while(array[i] != ')'){
                subString += array[i];
                subIndex = i;
            }
        }
    }
    
}
