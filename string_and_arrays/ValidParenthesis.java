import java.util.*;

public class ValidParenthesis {
    public static void main(String[] args) {
	String parens = "(())";
	System.out.println("For the following input: " + parens);
	System.out.println("Its a valid result? " + new Solution().result(parens));
    }

    static class Solution {
	Map<Character, Character> map = Map.of('{', '}',
					       '(', ')',
					       '[', ']');
						 
	public boolean result(String parens) {
	    Stack<Character> stack = new Stack<>();

	    for(int i = 0; i < parens.length(); i++) {
		if(map.containsKey(parens.charAt(i))) {
		    stack.push(parens.charAt(i));
		} else if(stack.size() > 0 && map.get(stack.peek()) == parens.charAt(i)) {
		    stack.pop();
		} else {
		    return false;
		}
	    }

	    return stack.isEmpty();
	}
    }
}
