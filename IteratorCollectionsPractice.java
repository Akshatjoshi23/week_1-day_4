import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Practice examples for iterators, collection choices, and common problems.
 *
 * Collection choice guide:
 * - ArrayList: O(1) indexed access; append is amortized O(1), middle insert/remove O(n).
 * - LinkedList: O(1) operations at either end; indexed access is O(n).
 * - HashSet / HashMap: average O(1) add, lookup, and remove; iteration is O(n).
 * - LinkedHashSet / LinkedHashMap: average O(1) lookup while preserving insertion order.
 * - TreeSet / TreeMap: O(log n) add, lookup, and remove with keys kept sorted.
 * - ArrayDeque: O(1) operations at either end; a good queue or stack choice.
 *
 * Hash-based complexity is average-case and depends on a reasonable hash distribution.
 */
public class IteratorCollectionsPractice {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(4, 1, 2, 4, 3, 2, 5, 6));

        System.out.println("Original numbers: " + numbers);
        removeEvenNumbers(numbers);
        System.out.println("After iterator removes evens: " + numbers);

        List<Integer> problemInput = Arrays.asList(4, 1, 2, 4, 3, 2, 5, 6);
        System.out.println("Frequency counts: " + countFrequencies(problemInput));
        System.out.println("First duplicate: " + findFirstDuplicate(problemInput));
        System.out.println("Unique in input order: " + uniqueInInputOrder(problemInput));
        System.out.println("Pair adds to 11: " + hasPairWithSum(problemInput, 11));
    }

    /**
     * Iterator.remove safely removes the current item during iteration.
     * Time: O(n). Extra space: O(1).
     */
    private static void removeEvenNumbers(List<Integer> numbers) {
        Iterator<Integer> iterator = numbers.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() % 2 == 0) {
                iterator.remove();
            }
        }
    }

    /**
     * Counts values with a map rather than repeatedly scanning the list.
     * Time: O(n) average. Extra space: O(u), where u is the number of unique values.
     */
    private static Map<Integer, Integer> countFrequencies(List<Integer> numbers) {
        Map<Integer, Integer> frequencies = new HashMap<>();
        for (int number : numbers) {
            frequencies.put(number, frequencies.getOrDefault(number, 0) + 1);
        }
        return frequencies;
    }

    /**
     * Returns the first value encountered for a second time, or null if none exists.
     * Time: O(n) average. Extra space: O(u), where u is the number checked.
     */
    private static Integer findFirstDuplicate(List<Integer> numbers) {
        Set<Integer> seen = new HashSet<>();
        for (int number : numbers) {
            if (!seen.add(number)) {
                return number;
            }
        }
        return null;
    }

    /**
     * Removes duplicates while retaining their first-occurrence order.
     * Time: O(n) average. Extra space: O(u), where u is the number of unique values.
     */
    private static List<Integer> uniqueInInputOrder(List<Integer> numbers) {
        return new ArrayList<>(new LinkedHashSet<>(numbers));
    }

    /**
     * Checks whether two distinct positions contain values that sum to target.
     * Time: O(n) average. Extra space: O(n).
     */
    private static boolean hasPairWithSum(List<Integer> numbers, int target) {
        Set<Integer> seen = new HashSet<>();
        for (int number : numbers) {
            long complement = (long) target - number;
            if (complement >= Integer.MIN_VALUE && complement <= Integer.MAX_VALUE
                    && seen.contains((int) complement)) {
                return true;
            }
            seen.add(number);
        }
        return false;
    }
}