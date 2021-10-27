import java.util.*;
import java.util.stream.*;

public class PermutateString {
    public static void main(String[] args) {
	System.out.println("Return all permutations of the following string: \"" + args[0] + "\"");
	System.out.println("Result: " + new Solution().result(args[0]));
    }

    static class Solution {
	public List<String> result(String s) {
	    Set<String> result = new HashSet<String>();
	    permutate("", s, result);
	    result.remove("");
	    return result.stream().collect(Collectors.toList());
	}

	private void permutate(String fixed, String s, Set<String> result) {
	    result.add(fixed);
	    result.add(s);

	    for(int i = 0; i < s.length(); i++) {
		StringBuffer buffer = new StringBuffer(s);
		permutate(fixed+buffer.substring(i, i+1), buffer.deleteCharAt(i).toString(), result);
	    }
	}
    }
}
