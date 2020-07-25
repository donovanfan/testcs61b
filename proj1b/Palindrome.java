public class Palindrome {
    /** Method to change a string into a character deque with the
     * order same as characters in the string.
     * @param word: The input string.
     * @return: A deque of characters.
     */
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> result = new LinkedListDeque<>();
        int length = word.length();
        for (int i = 0; i < length; i++) {
            char temp = word.charAt(i);
            result.addLast(temp);
        }
        return result;
    }

    /** Helper method to convert deque of characters into string.
     * @param deque: A character deque.
     * @return: A string made of all characters from the input deque.
     */
    private String dequeToString(Deque<Character> deque) {
        String result = "";
        while (deque.size() > 0) {
            char temp = deque.removeFirst();
            result += temp;
        }
        return result;
    }

    /** Method that should return true if the given word is a palindrome,
     * false otherwise.
     * @param word: The input string.
     * @return: True if the input is a palindrome, false otherwise.
     */
    public boolean isPalindrome(String word) {
        Deque<Character> deque = wordToDeque(word);
        if (deque.size() == 0 || deque.size() == 1) {
            return true;
        } else {
            if (deque.removeFirst() == deque.removeLast()) {
                return (isPalindrome(dequeToString(deque)));
            } else {
                return false;
            }
        }
    }

    /** The method will return true if the word is a palindrome according to the
     * character comparison test provided by the CharacterComparator passed in as argument cc.
     * @param word: Input string to check.
     * @param cc: Provide character comparison test.
     * @return: True if it is palindrome according to the comparison method, false otherwise.
     */
    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> deque = wordToDeque(word);
        if (deque.size() == 0 || deque.size() == 1) {
            return true;
        } else {
            if (cc.equalChars(deque.removeFirst(), deque.removeLast())) {
                return (isPalindrome(dequeToString(deque), cc));
            } else {
                return false;
            }
        }
    }
}
