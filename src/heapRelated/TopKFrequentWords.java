/*
 * == Created Date ==
 * Jan 4, 2019
 * 
 * == Question - Top K Frequent Words ==
 * Given a non-empty list of words, return the k most frequent elements.
 * 
 * Your answer should be sorted by frequency from highest to lowest. 
 * If two words have the same frequency, then the word with the lower alphabetical order comes first.
 * 
 * == Example ==
 * 
 * Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * 
 * Output: ["i", "love"]
 * 
 * Explanation: "i" and "love" are the two most frequent words.
 * Note that "i" comes before "love" due to a lower alphabetical order.
 *   
 *   
 * == Notes == 
 * LeetCode 347 (M) 
 * 
 */

package heapRelated;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentWords {
	
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> freqMap = new HashMap<>();
        for (String word : words) {
            freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
        }
        
        PriorityQueue<Map.Entry<String, Integer>> minHeap = new PriorityQueue<>((a, b) -> a.getValue() == b.getValue() ? b.getKey().compareTo(a.getKey()) : a.getValue() - b.getValue());
        
        // ["i" 2], ["love",2] ["leetcode", 1], ["coding", 1]
        for (Map.Entry<String, Integer> entry : freqMap.entrySet()) { 
            minHeap.offer(entry);
            if (minHeap.size() > k) {
                minHeap.poll();
            } 
        }
        
        List<String> topK = new ArrayList<>();
        for (int i = 0; i < minHeap.size(); i++) {
            topK.add("");
        }
        for (int i = minHeap.size() - 1; i >= 0; i--) {
            topK.set(i, minHeap.poll().getKey());
        }
        return topK;
    }
    
    /* ------------------< Different syntax >----------------------*/
    public List<String> topKFrequentII(String[] words, int k) {
        Map<String, Integer> freqMap = new HashMap<>();
        for (String word: words) {
        		freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
        }
        PriorityQueue<String> minHeap = new PriorityQueue<String>(
        (w1, w2) -> freqMap.get(w1).equals(freqMap.get(w2)) ? w2.compareTo(w1) : freqMap.get(w1) - freqMap.get(w2) );

        for (String word: freqMap.keySet()) {
        		minHeap.offer(word);
            if (minHeap.size() > k) {
            		minHeap.poll();
            }
        }

        List<String> topK = new ArrayList<>();
        while (!minHeap.isEmpty()) {
        		topK.add(minHeap.poll());
        }
        Collections.reverse(topK);
        return topK;
    }
}
