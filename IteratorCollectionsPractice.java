import java.util.*;

public class IteratorCollectionsPractice {

    public static void main(String[] args) {

        List<Integer> numbers = new ArrayList<>(
                Arrays.asList(4, 1, 2, 4, 3, 2, 5, 6)
        );

        System.out.println("Original numbers: " + numbers);

        removeEvenNumbers(numbers);
        System.out.println("After removing even numbers: " + numbers);

        List<Integer> input = Arrays.asList(4, 1, 2, 4, 3, 2, 5, 6);

        System.out.println("Frequency of each number: " + countFrequencies(input));
        System.out.println("First duplicate number: " + findFirstDuplicate(input));
        System.out.println("Numbers without duplicates: " + uniqueNumbers(input));
        System.out.println("Is there a pair with sum 11? " + hasPairWithSum(input, 11));
    }

    // Removes even numbers using an Iterator.
    private static void removeEvenNumbers(List<Integer> numbers) {
        Iterator<Integer> iterator = numbers.iterator();

        while (iterator.hasNext()) {
            int number = iterator.next();

            if (number % 2 == 0) {
                iterator.remove();
            }
        }
    }

    // Counts how many times each number appears.
    private static Map<Integer, Integer> countFrequencies(List<Integer> numbers) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        for (int number : numbers) {
            frequencyMap.put(
                    number,
                    frequencyMap.getOrDefault(number, 0) + 1
            );
        }

        return frequencyMap;
    }

    // Finds the first number that appears more than once.
    private static Integer findFirstDuplicate(List<Integer> numbers) {
        Set<Integer> seenNumbers = new HashSet<>();

        for (int number : numbers) {
            if (seenNumbers.contains(number)) {
                return number;
            }

            seenNumbers.add(number);
        }

        return null; // No duplicate found.
    }

    // Returns unique numbers while keeping their original order.
    private static List<Integer> uniqueNumbers(List<Integer> numbers) {
        Set<Integer> uniqueSet = new LinkedHashSet<>(numbers);

        return new ArrayList<>(uniqueSet);
    }

    // Checks whether any two different positions add up to the target.
    private static boolean hasPairWithSum(List<Integer> numbers, int target) {
        Set<Integer> seenNumbers = new HashSet<>();

        for (int number : numbers) {
            int complement = target - number;

            if (seenNumbers.contains(complement)) {
                return true;
            }

            seenNumbers.add(number);
        }

        return false;
    }
}
