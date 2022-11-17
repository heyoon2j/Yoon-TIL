
/**
 * 도메인 뒤집기
 *
 * 주어진 홈페이지 주소를 .을 기준으로 각각 뒤집어 출력하시오.
 *
 * ex) www.google.com -> www.elgoog.moc
 *
 * 인자
 * string: 홈페이지 주소
 */
 class DomainReverse {
     public static void main(String[] args){
         String string = "www.google.com";

         String[] splitString = string.split("\\.");

         for(int i = 0; i < splitString.length; i++){

            char[] addressChar = splitString[i].toCharArray();

            for(int j = 0; j < addressChar.length; j++)
                System.out.print(addressChar[addressChar.length-1-j]);;

            if(i != splitString.length-1)
                System.out.print(".");
         }
     }

}
