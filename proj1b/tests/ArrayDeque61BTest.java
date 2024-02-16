import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.Deque;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

//     @Test
//     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
//     void noNonTrivialFields() {
//         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
//                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
//                 .toList();
//
//         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
//     }


    // Flags for toList tests
    @Test
    public void to_list_empty() {
        Deque61B<Integer> test = new ArrayDeque61B<>();
        assertThat(test.toList()).containsExactly().inOrder();
    }

    @Test
    public void to_list_nonempty() {
        Deque61B<Integer> test = new ArrayDeque61B<>();
        test.addLast(1);
        test.addLast(2);
        test.addLast(3);

        assertThat(test.toList()).containsExactly(1, 2, 3).inOrder();
    }

    // Flags for add tests
    @Test
    public void add_first_from_empty() {
        Deque61B<Integer> test = new ArrayDeque61B<>();
        test.addFirst(1);

        assertThat(test.toList()).containsExactly(1).inOrder();
    }

    @Test
    public void add_last_from_empty() {
        Deque61B<Integer> test = new ArrayDeque61B<>();
        test.addLast(1);

        assertThat(test.toList()).containsExactly(1).inOrder();
    }

    @Test
    public void add_first_nonempty() {
        Deque61B<Integer> test = new ArrayDeque61B<>();
        ;
        test.addFirst(1);
        test.addFirst(2);

        assertThat(test.toList()).containsExactly(2, 1).inOrder();

    }

    @Test
    public void add_last_nonempty() {
        Deque61B<Integer> test = new ArrayDeque61B<>();
        ;
        test.addLast(1);
        test.addLast(2);

        assertThat(test.toList()).containsExactly(1, 2).inOrder();
    }

    @Test
    public void add_first_trigger_resize() {
        Deque61B<Integer> test = new ArrayDeque61B<>();
        ;
        for (int i = 1; i <= 8; i++) {
            test.addLast(i);
        }
        test.addFirst(0);
        assertThat(test.toList()).containsExactly(0, 1, 2, 3, 4, 5, 6, 7, 8).inOrder();
    }

    @Test
    public void add_last_trigger_resize() {
        Deque61B<Integer> test = new ArrayDeque61B<>();
        ;
        for (int i = 1; i <= 8; i++) {
            test.addLast(i);
        }
        test.addLast(0);
        assertThat(test.toList()).containsExactly(1, 2, 3, 4, 5, 6, 7, 8, 0).inOrder();
    }

    // Flags for add after remove tests
    @Test
    public void add_first_after_remove_to_empty() {
        Deque61B<Integer> test = new ArrayDeque61B<>();
        test.addLast(1);
        test.addLast(2);
        test.addLast(3);
        test.removeLast();
        test.removeLast();
        test.removeLast();

        test.addFirst(1);
        test.addFirst(2);
        assertThat(test.toList()).containsExactly(2, 1).inOrder();
    }

    @Test
    public void add_last_after_remove_to_empty() {
        Deque61B<Integer> test = new ArrayDeque61B<>();
        test.addLast(1);
        test.addLast(2);
        test.addLast(3);
        test.removeLast();
        test.removeLast();
        test.removeLast();

        test.addLast(1);
        test.addLast(2);
        assertThat(test.toList()).containsExactly(1, 2).inOrder();
    }

    // Flags for remove Tests
    @Test
    public void remove_first() {
        Deque61B<Integer> test = new ArrayDeque61B<>();
        test.addLast(1);
        test.addLast(2);
        test.addLast(3);

        assertThat(test.removeFirst()).isEqualTo(1);
        assertThat(test.toList()).containsExactly(2, 3).inOrder();
    }

    @Test
    public void remove_last() {
        Deque61B<Integer> test = new ArrayDeque61B<>();
        test.addLast(1);
        test.addLast(2);
        test.addLast(3);

        assertThat(test.removeLast()).isEqualTo(3);
        assertThat(test.toList()).containsExactly(1, 2).inOrder();
    }

    @Test
    public void remove_first_to_empty() {
        Deque61B<Integer> test = new ArrayDeque61B<>();
        test.addLast(1);
        test.addLast(2);
        test.addLast(3);

        test.removeFirst();
        test.removeFirst();
        test.removeFirst();
        assertThat(test.toList()).containsExactly().inOrder();
    }

    @Test
    public void remove_last_to_empty() {
        Deque61B<Integer> test = new ArrayDeque61B<>();
        test.addLast(1);
        test.addLast(2);
        test.addLast(3);

        test.removeLast();
        test.removeLast();
        test.removeLast();
        assertThat(test.toList()).containsExactly().inOrder();
    }

    @Test
    public void remove_first_to_one() {
        Deque61B<Integer> test = new ArrayDeque61B<>();
        test.addLast(1);
        test.addLast(2);
        test.addLast(3);

        test.removeFirst();
        test.removeFirst();

        assertThat(test.toList()).containsExactly(3).inOrder();
    }

    @Test
    public void remove_last_to_one() {
        Deque61B<Integer> test = new ArrayDeque61B<>();
        test.addLast(1);
        test.addLast(2);
        test.addLast(3);

        test.removeLast();
        test.removeLast();

        assertThat(test.toList()).containsExactly(1).inOrder();
    }

    @Test
    public void remove_first_trigger_resize() {
        Deque61B<Integer> test = new ArrayDeque61B<>();
        for (int i = 1; i <= 16; i++) {
            test.addLast(i);
        }

        for (int i = 1; i <= 16; i++) {
            test.removeLast();
        }

        test.removeFirst();
        assertThat(test.toList()).containsExactly().inOrder();
        assertThat(test.size()).isEqualTo(0);
    }

    @Test
    public void remove_last_trigger_resize() {
        Deque61B<Integer> test = new ArrayDeque61B<>();
        for (int i = 1; i <= 16; i++) {
            test.addLast(i);
        }

        for (int i = 1; i <= 16; i++) {
            test.removeLast();
        }

        assertThat(test.toList()).containsExactly().inOrder();
        assertThat(test.size()).isEqualTo(0);
    }

    // Flags for get Tests
    @Test
    public void get_valid() {
        Deque61B<Integer> test = new ArrayDeque61B<>();
        test.addLast(1);
        test.addLast(2);
        test.addLast(3);

        assertThat(test.get(0)).isEqualTo(1);
    }

    @Test
    public void get_oob_large() {
        Deque61B<Integer> test = new ArrayDeque61B<>();
        test.addLast(1);
        test.addLast(2);
        test.addLast(3);

        assertThat(test.get(3)).isEqualTo(null);
    }

    @Test
    public void get_oob_neg() {
        Deque61B<Integer> test = new ArrayDeque61B<>();
        test.addLast(1);
        test.addLast(2);
        test.addLast(3);

        assertThat(test.get(-1)).isEqualTo(null);
    }

    // Flags for size tests
    @Test
    public void size() {
        Deque61B<Integer> test = new ArrayDeque61B<>();
        test.addLast(1);
        test.addLast(2);
        test.addLast(3);

        assertThat(test.size()).isEqualTo(3);
    }

    @Test
    public void size_after_remove_to_empty() {
        Deque61B<Integer> test = new ArrayDeque61B<>();
        test.addLast(1);
        test.addLast(2);
        test.addLast(3);

        test.removeLast();
        test.removeLast();
        test.removeLast();

        assertThat(test.size()).isEqualTo(0);
    }

    @Test
    public void size_after_remove_from_empty() {
        Deque61B<Integer> test = new ArrayDeque61B<>();

        test.removeLast();

        assertThat(test.size()).isEqualTo(0);
    }

    // Flags for isEmpty Tests
    @Test
    public void is_empty_true() {
        Deque61B<Integer> test = new ArrayDeque61B<>();

        assertThat(test.isEmpty()).isEqualTo(true);
    }

    @Test
    public void is_empty_false() {
        Deque61B<Integer> test = new ArrayDeque61B<>();
        test.addLast(1);

        assertThat(test.isEmpty()).isEqualTo(false);
    }

    // Flags for advanced resize tests
    @Test
    public void resize_up_and_resize_down() {
        Deque61B<Integer> test = new ArrayDeque61B<>();
        for (int i = 1; i <= 16; i++) {
            test.addLast(i);
        }
        for (int i = 1; i <= 12; i++) {
            test.removeLast();
        }
        assertThat(test.toList()).containsExactly(1, 2, 3, 4).inOrder();
        assertThat(test.size()).isEqualTo(4);
    }


}
