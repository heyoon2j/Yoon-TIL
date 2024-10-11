import java.util.Scanner;

public class FanMeeting {
    public static char[] idolMember = null;
    public static char[] idolFan = null;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int testCase = sc.nextInt();
        sc.nextLine();

        int[] result = new int[testCase];

        for(int i = 0; i < testCase; i++) {
            idolMember = sc.nextLine().toCharArray();
            idolFan = sc.nextLine().toCharArray();


            result[i] = countOfMeeting(0, idolFan.length-1);
        }
        for(int i = 0; i < testCase; i++){
            System.out.println(result[i]);
        }
    }

    public static int countOfMeeting(int start, int last){
        int count = 0;

        // 나눌 필요도 없고, AllHug 인지 판단할 필요 없음
        if((last-start+1) < idolMember.length){
            return 0;
        }

        // Divide 2
        int half = (last+start)/2;
        count += countOfMeeting(start, half);
        count += countOfMeeting(half+1, last);

        // pivot is half
        boolean isOutBound = false;
        for(int i = 0; i < idolMember.length-1; i++){
            char[] fan = new char[idolMember.length];
            isOutBound = false;

            for(int j = 0; j < idolMember.length; j++){
                if(half-i+j < start || half-i+j > last){//idolMember.length){
                    isOutBound = true;
                    break;
                }
                fan[j] = idolFan[half-i+j];
            }

            if(isOutBound)
                continue;

//            for(int j = 0; j < i; j++){
//                if(half-j < 0) {
//                    isOutBound = true;
//                    break;
//                }
//                fan[j] = idolFan[half-j-1];
//            }
//
//            for(int j = i; j < idolMember.length; j++ ){
//                if(half+j >= idolMember.length) {
//                    isOutBound = true;
//                    break;
//                }
//                fan[j] = idolFan[half+j-i];
//            }
//
//            if(isOutBound)
//                continue;

            count=(isAllHug(fan))?count+1:count+0;
        }

        return count;
    }

    public static boolean isAllHug(char[] fan){
        for(int i = 0; i < idolMember.length; i++){
            if(idolMember[i] == 'M' && idolMember[i] == fan[i]){
                return false;
            }
        }
        return true;
    }
}
