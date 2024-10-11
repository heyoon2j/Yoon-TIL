import java.util.*;

public class QuadTree {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();
        sc.nextLine();

        char[][] result = new char[testCase][];

        for(int i = 0; i < testCase; i++) {

            String quadTreeStr = sc.nextLine();
            char[] quadTreeChar = quadTreeStr.toCharArray();
            quadTree(quadTreeChar, 0);
            result[i] = quadTreeChar;
        }

        for(int i = 0; i < testCase; i++){
            System.out.println(result[i]);
        }
    }

    public static int quadTree(char[] quadTreeStr, int index){

        // if 'w' or 'b'
        if(quadTreeStr[index] == 'w' || quadTreeStr[index] == 'b' ){
            return index;
        }

        // if 'x'
        int firstOfX = quadTree(quadTreeStr, index+1);      // index+1 ~ firstOfX
        int secondOfX = quadTree(quadTreeStr, firstOfX+1);  // firstOfX+1 ~ secondOfX
        int thirdOfX = quadTree(quadTreeStr, secondOfX+1);  // secondOfX+1 ~ thirdOfX
        int fourthOfX = quadTree(quadTreeStr, thirdOfX+1);  // thirdOfX+1 ~ fourthOfX

        rotationQuadTree(quadTreeStr, index+1, firstOfX, secondOfX, thirdOfX, fourthOfX);

        return fourthOfX;
    }

    public static void rotationQuadTree(char[] quadTreeStr, int index0, int index1, int index2, int index3, int index4){
        char[] rotationStr = new char[index4-index0+1];

        System.arraycopy(quadTreeStr, index0, rotationStr,0, index4-index0+1);

        int firstSize = index1-index0+1;        // index+1 ~ firstOfX, 1
        int secondSize = index2-(index1+1)+1;   // firstOfX+1 ~ secondOfX, 2
        int thirdSize = index3-(index2+1)+1;    // secondOfX+1 ~ thirdOfX, 3
        int fourthSize = index4-(index3+1)+1;   // thirdOfX+1 ~ fourthOfX, 4


        for(int i = 0; i < thirdSize+fourthSize; i++){
            quadTreeStr[index0+i] = rotationStr[i+firstSize+secondSize];
        }

        for(int i = 0; i < firstSize+secondSize; i++){
            quadTreeStr[index0+thirdSize+fourthSize+i] = rotationStr[i];
        }
    }
}
