
public class Palindrome {

		public static void main(String[] args) {
			System.out.println(isPalindrome("noon"));
			System.out.println(isPalindrome("Madam I'm Adam"));
			System.out.println(isPalindrome("A man, a plan, a canal, Panama"));
			System.out.println(isPalindrome("A Toyota"));
			System.out.println(isPalindrome("Not a palindrome"));
			System.out.println(isPalindrome("asdfghfdsa"));
		}
		
		public static boolean isPalindrome(String s){
			if (s!=null && isPalindromeHelper(s.toUpperCase()))
					return true;
			return false;
		}
		
		public static boolean isPalindromeHelper(String s){
			int length = s.length();
			if (length<=1) return true;
			char first = s.charAt(0);
			char last = s.charAt(length-1);
			if (!Character.isLetter(first))
				return isPalindromeHelper(s.substring(1));
			else if (!Character.isLetter(last))
				return isPalindromeHelper(s.substring(0,length-1));
			else if (first==last)
				return isPalindromeHelper(s.substring(1,length-1));
			return false;
		}
}
