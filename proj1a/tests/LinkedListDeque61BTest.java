import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

/**
 * Performs some basic linked list tests.
 */
public class LinkedListDeque61BTest {
    @Test
    public void to_list_empty() {
        Deque61B<Integer> test = new LinkedListDeque61B<>();
        assertThat(test.toList())
                .containsExactly()
                .inOrder();
    }

    @Test
    public void to_list_non_empty() {
        Deque61B<Integer> test = new LinkedListDeque61B<>();
        test.addLast(1);
        test.addLast(2);
        test.addLast(3);

        assertThat(test.toList())
                .containsExactly(1, 2, 3)
                .inOrder();
    }

    @Test
    /** In this test, we have three different assert statements that verify that addFirst works correctly. */
    public void addFirstTestBasic() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addFirst("back"); // after this call we expect: ["back"]
        assertThat(lld1.toList()).containsExactly("back").inOrder();

        lld1.addFirst("middle"); // after this call we expect: ["middle", "back"]
        assertThat(lld1.toList()).containsExactly("middle", "back").inOrder();

        lld1.addFirst("front"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();

         /* Note: The first two assertThat statements aren't really necessary. For example, it's hard
            to imagine a bug in your code that would lead to ["front"] and ["front", "middle"] failing,
            but not ["front", "middle", "back"].
          */
    }

    @Test
    /** In this test, we use only one assertThat statement. IMO this test is just as good as addFirstTestBasic.
     *  In other words, the tedious work of adding the extra assertThat statements isn't worth it. */
    public void addLastTestBasic() {
        Deque61B<String> lld1 = new LinkedListDeque61B<>();

        lld1.addLast("front"); // after this call we expect: ["front"]
        lld1.addLast("middle"); // after this call we expect: ["front", "middle"]
        lld1.addLast("back"); // after this call we expect: ["front", "middle", "back"]
        assertThat(lld1.toList()).containsExactly("front", "middle", "back").inOrder();
    }

    @Test
    /** This test performs interspersed addFirst and addLast calls. */
    public void addFirstAndAddLastTest() {
        Deque61B<Integer> lld1 = new LinkedListDeque61B<>();

         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
        lld1.addLast(0);   // [0]
        lld1.addLast(1);   // [0, 1]
        lld1.addFirst(-1); // [-1, 0, 1]
        lld1.addLast(2);   // [-1, 0, 1, 2]
        lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

        assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();
    }

    // Below, you'll write your own tests for LinkedListDeque61B.
    @Test
    public void isEmptyTest() {
        Deque61B<Integer> test1 = new LinkedListDeque61B<>();
        Deque61B<Integer> test2 = new LinkedListDeque61B<>();
        test2.addLast(1);
        test2.addLast(2);
        test2.addLast(3);
        assertThat(test1.isEmpty()).isTrue();
        assertThat(test2.isEmpty()).isFalse();
    }

    @Test
    public void sizeTest() {
        Deque61B<Integer> test1 = new LinkedListDeque61B<>();
        Deque61B<Integer> test2 = new LinkedListDeque61B<>();
        test2.addLast(1);
        test2.addLast(2);
        test2.addLast(3);
        assertThat(test1.size()).isEqualTo(0);
        assertThat(test2.size()).isEqualTo(3);
    }

    @Test
    public void getTest() {
        Deque61B<Integer> test1 = new LinkedListDeque61B<>();
        Deque61B<Integer> test2 = new LinkedListDeque61B<>();
        test2.addLast(1);
        test2.addLast(2);
        test2.addLast(3);

        assertThat(test1.get(-1)).isEqualTo(null);
        assertThat(test1.get(0)).isEqualTo(null);
        assertThat(test1.get(1)).isEqualTo(null);

        assertThat(test2.get(-1)).isEqualTo(null);
        assertThat(test2.get(0)).isEqualTo(1);
        assertThat(test2.get(1)).isEqualTo(2);
        assertThat(test2.get(2)).isEqualTo(3);
        assertThat(test2.get(3)).isEqualTo(null);
    }

    @Test
    public void getRecursiveTest() {
        Deque61B<Integer> test1 = new LinkedListDeque61B<>();
        Deque61B<Integer> test2 = new LinkedListDeque61B<>();
        test2.addLast(1);
        test2.addLast(2);
        test2.addLast(3);

        assertThat(test1.getRecursive(-1)).isEqualTo(null);
        assertThat(test1.getRecursive(0)).isEqualTo(null);
        assertThat(test1.getRecursive(1)).isEqualTo(null);

        assertThat(test2.getRecursive(-1)).isEqualTo(null);
        assertThat(test2.getRecursive(0)).isEqualTo(1);
        assertThat(test2.getRecursive(1)).isEqualTo(2);
        assertThat(test2.getRecursive(2)).isEqualTo(3);
        assertThat(test2.getRecursive(3)).isEqualTo(null);
    }

    @Test
    public void removeFirstTest() {
        Deque61B<Integer> test1 = new LinkedListDeque61B<>();
        Deque61B<Integer> test2 = new LinkedListDeque61B<>();
        test2.addLast(1);
        test2.addLast(2);
        test2.addLast(3);

        assertThat(test1.removeFirst()).isEqualTo(null);
        assertThat(test2.removeFirst()).isEqualTo(1);
        assertThat(test2.toList())
                .containsExactly(2, 3)
                .inOrder();
    }

    @Test
    public void removeLastTest() {
        Deque61B<Integer> test1 = new LinkedListDeque61B<>();
        Deque61B<Integer> test2 = new LinkedListDeque61B<>();
        test2.addLast(1);
        test2.addLast(2);
        test2.addLast(3);

        assertThat(test1.removeLast()).isEqualTo(null);
        assertThat(test2.removeLast()).isEqualTo(3);
        assertThat(test2.toList())
                .containsExactly(1, 2)
                .inOrder();
    }

    @Test
    public void removeFirstAndLastTest() {
        Deque61B<Integer> test = new LinkedListDeque61B<>();
        test.addLast(1);
        test.addLast(2);
        test.addLast(3);
        assertThat(test.removeLast()).isEqualTo(3);
        assertThat(test.removeFirst()).isEqualTo(1);
        assertThat(test.toList())
                .containsExactly(2)
                .inOrder();


    }

    @Test
    public void add_first_after_remove_to_empty() {
        Deque61B<Integer> test = new LinkedListDeque61B<>();
        test.addLast(1);
        test.addLast(2);
        test.addLast(3);

        test.removeFirst();
        test.removeFirst();
        test.removeFirst();

        test.addFirst(1);
        test.addFirst(2);

        assertThat(test.toList())
                .containsExactly(2, 1)
                .inOrder();

    }

    @Test
    public void add_last_after_remove_to_empty() {
        Deque61B<Integer> test = new LinkedListDeque61B<>();
        test.addLast(1);
        test.addLast(2);
        test.addLast(3);

        test.removeFirst();
        test.removeFirst();
        test.removeFirst();

        test.addLast(1);
        test.addLast(2);

        assertThat(test.toList())
                .containsExactly(1, 2)
                .inOrder();

    }

    @Test
    public void remove_first_to_empty() {
        Deque61B<Integer> test = new LinkedListDeque61B<>();
        test.addLast(1);
        test.addLast(2);
        test.addLast(3);

        test.removeFirst();
        test.removeFirst();
        test.removeFirst();

        assertThat(test.size()).isEqualTo(0);

        assertThat(test.removeFirst()).isEqualTo(null);
    }

    @Test
    public void remove_last_to_empty() {
        Deque61B<Integer> test = new LinkedListDeque61B<>();
        test.addLast(1);
        test.addLast(2);
        test.addLast(3);

        test.removeLast();
        test.removeLast();
        test.removeLast();

        assertThat(test.size()).isEqualTo(0);

        assertThat(test.removeLast()).isEqualTo(null);
    }

    @Test
    public void remove_first_to_one() {
        Deque61B<Integer> test = new LinkedListDeque61B<>();
        test.addLast(1);
        test.addLast(2);
        test.addLast(3);

        test.removeFirst();
        test.removeFirst();

        assertThat(test.get(0)).isEqualTo(3);
    }

    @Test
    public void remove_last_to_one() {
        Deque61B<Integer> test = new LinkedListDeque61B<>();
        test.addLast(1);
        test.addLast(2);
        test.addLast(3);

        test.removeLast();
        test.removeLast();

        assertThat(test.get(0)).isEqualTo(1);
    }

}