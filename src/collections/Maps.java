package collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Maps {
    public static class RepeatingAndNonRepeatingNumber {

        public static void main(String[] args) {
            int[] arr = { 1, 2, 1, 2, 3, 4, 5, 4, 6 };
            int result = firstNonRepeatingNumber(arr);
            System.out.println("Result:" + result);
            Optional<Entry<Integer, Long>> results = firstNonRepeatingNumberJava8(arr);
            System.out.println("Result java 8:" + results);
            Set<Integer> nonRepeating = findAllNonRepeatingNumbers(arr);
            System.out.println("Result:" + nonRepeating);
            Set<Integer> nonRepeatings = findAllNonRepeatingNumbersJava8(arr);
            System.out.println("Result:" + nonRepeatings);
        }

        private static int firstNonRepeatingNumber(int[] arr) {
            Map<Integer, Integer> map = new LinkedHashMap<>();
            for (int num : arr) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }
            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (entry.getValue() == 1)
                    return entry.getKey();
            }
            return -1;

        }

        private static Optional<Entry<Integer, Long>> firstNonRepeatingNumberJava8(int[] arr) {
            return Arrays.stream(arr).boxed()
                    .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                    .entrySet().stream().filter(e -> e.getValue() == 1).findFirst();

        }

        private static Set<Integer> findAllNonRepeatingNumbers(int[] arr) {
            Map<Integer, Integer> map = new LinkedHashMap<>();
            Set<Integer> set = new LinkedHashSet<>(); // To maintain order

            for (int num : arr) {
                map.put(num, map.getOrDefault(num, 0) + 1);
            }

            for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
                if (entry.getValue() == 1) {
                    set.add(entry.getKey());
                }
            }

            return set;
        }

        private static Set<Integer> findAllNonRepeatingNumbersJava8(int[] arr) {
            Map<Integer, Long> freMap = Arrays.stream(arr).boxed()
                    .collect(Collectors.groupingBy(i -> i, LinkedHashMap::new, Collectors.counting()));
            return freMap.entrySet().stream().filter(e -> e.getValue() == 1).map(Map.Entry::getKey)
                    .collect(Collectors.toCollection(LinkedHashSet::new));

        }
    }

    public static class FindNonRepeatedCharct {
        public static void main(String[] args) {
            String str = "swiss";
            String outputs = firstNonRepeatingChars(str);
            System.out.println("Output chars::" + outputs);
            Optional<String> outputss = firstNonRepeatingCharsJava8(str);
            System.out.println("Output chars::" + outputss);
        }

        private static String firstNonRepeatingChars(String str) {
            Map<Character, Integer> map = new LinkedHashMap<>();
            for (char ch : str.toCharArray()) {
                map.put(ch, map.getOrDefault(ch, 0) + 1);

            }
            for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                if (entry.getValue() == 1)
                    return String.valueOf(entry.getKey());
            }
            return "NoUnique Elements";
        }
    }

    private static Optional<String> firstNonRepeatingCharsJava8(String str) {
        return str.chars().mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .map(e -> String.valueOf(e.getKey()))
                .findFirst();
    }
    // All Non-Repeated Characters

    public static class FindAllNonRepeatingCharacters {
        public static void main(String[] args) {
            String str = "hello world";
            List<Character> list = findAllNonRepeatingCharacters(str);
            System.out.println("findAllNonRepeatingCharacters::: " + list);
            List<Character> listJava8 = findAllNonRepeatingCharactersJava8(str);
            System.out.println("findAllNonRepeatingCharacters::: " + listJava8);
        }

        private static List<Character> findAllNonRepeatingCharacters(String str2) {
            str2 = str2.replaceAll("\s+", ""); // ✅ remove whitespaces

            Map<Character, Integer> map = new LinkedHashMap<>();
            for (char c : str2.toCharArray()) {
                map.put(c, map.getOrDefault(c, 0) + 1);
            }

            List<Character> list = new ArrayList<>();
            for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                if (entry.getValue() == 1) {
                    list.add(entry.getKey());
                }
            }

            return list;
        }
    }

    public static List<Character> findAllNonRepeatingCharactersJava8(String str) {
        str = str.replaceAll("\s+", "");
        return str.chars().mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, LinkedHashMap::new, Collectors.counting()))
                .entrySet().stream().filter(e -> e.getValue() == 1).map(Map.Entry::getKey).collect(Collectors.toList());
    }
}
