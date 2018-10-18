/*
 * == Created Date ==
 * October 18, 2018
 * 
 * == Question - Letter Combinations of a Phone Number ==
 *   
 * == Notes == 
 * LeetCode 17
 * 
 */

package dfsRelated;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LetterCombinationsOfPhoneNumber {
	
    private Map<Integer, String> map;
    
    public List<String> letterCombinations(String digits) {
        this.map = getMatchingMap();
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        if (digits == null || digits.length() == 0) {
            return result;
        }
        dfs(digits, 0, result, sb);
        return result;
    }
    
    private void dfs(String digits, int index, List<String> result, StringBuilder sb) {
        if (index == digits.length()){
            result.add(sb.toString());
            return;
        }
        int curDig = digits.charAt(index) - '1' + 1;
        for (int i = 0; i < map.get(curDig).length(); i++) {     		
            sb.append(map.get(curDig).charAt(i));
            dfs(digits, index + 1, result, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
    
    private Map<Integer, String> getMatchingMap() {
        Map<Integer, String> map = new HashMap<>();
        map.put(2, "abc");
        map.put(3, "def");
        map.put(4, "ghi");
        map.put(5, "jkl");
        map.put(6, "mno");
        map.put(7, "pqrs");
        map.put(8, "tuv");
        map.put(9, "wxyz");
        return map;
    }
    
	// Time Complexity: O(?);
	// Space Complexity: O(?);
	
	/* ----------------------< test stub >-------------------------*/
    private static void print(List<String> list) {
    		for (String s: list) {
    			System.out.print(s + " "); 
    		}
    		System.out.println();
    }
	public static void main(String[] args) {
		
		LetterCombinationsOfPhoneNumber testObj = new LetterCombinationsOfPhoneNumber();
		
		/* Test Case 0 */
		System.out.println("---< Test Case 0 >---");
		
		/* Test Case 1 */
		System.out.println("---< Test Case 1 >---");		
		print(testObj.letterCombinations("23"));
		
		/* Test Case 2 */
		System.out.println("---< Test Case 2 >---");
		print(testObj.letterCombinations("234"));
		
		/* Test Case 3 */
		System.out.println("---< Test Case 3 >---");
		
	}

}
