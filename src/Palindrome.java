
public class Palindrome {
	
	//Determines if string length is even or odd
	public static boolean isEven(String word){
		if(word.length() % 2 == 0)
			return true;
		return false;
	}
	
	//Returns the front half of a string including the middle char if the legnth is odd
	public static String getFrontHalf(String num){
		if(isEven(num))
			return num.substring(0, num.length() /2);
		return num.substring(0, (num.length() /2)+1);
	}
	
	//Finds highest palindrome number between two given numbers
	public static String findPalindrome(int highNum, int lowNum){
		String snum = Integer.toString(highNum);
		String frontHalf = getFrontHalf(snum);
		String palindrome = "";
		if(isEven(snum)){
			palindrome = frontHalf + new StringBuilder(frontHalf).reverse().toString();
			if(Integer.parseInt(palindrome) <= lowNum)
				return "No palindrome found.";
			else if(Integer.parseInt(palindrome) >= highNum){
				frontHalf = Integer.toString(Integer.parseInt(frontHalf) - 1);
				palindrome = frontHalf + new StringBuilder(frontHalf).reverse().toString();
				if(Integer.parseInt(palindrome) <= lowNum)
					return "No palindrome found.";
			}
		}
		else{
			palindrome = frontHalf + new StringBuilder(frontHalf.substring(0, frontHalf.length()-1)).reverse().toString();
			if(Integer.parseInt(palindrome) <= lowNum)
				return "No palindrome found.";
			else if(Integer.parseInt(palindrome) >= highNum){
				frontHalf = Integer.toString(Integer.parseInt(frontHalf) - 1);
				palindrome = frontHalf + new StringBuilder(frontHalf.substring(0, frontHalf.length()-1)).reverse().toString();
				if(Integer.parseInt(palindrome) <= lowNum)
					return "No palindrome found.";
			}
		}
			
		return palindrome;
	}
	
	public static void main(String[] args){
		int a = 46332;
		int b = 46260;
		System.out.println(findPalindrome(a,b));
	}
}
