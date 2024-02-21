import org.junit.jupiter.api.*;

import java.util.Comparator;

import deque.MaxArrayDeque61B;

import static com.google.common.truth.Truth.assertThat;

public class MaxArrayDeque61BTest {
    private static class StringLengthComparator implements Comparator<String> {
        public int compare(String a, String b) {
            return a.length() - b.length();
        }
    }

    private static class NumberComparator implements Comparator<Integer> {

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1 - o2;
        }
    }

    @Test
    public void basicTest() {
        MaxArrayDeque61B<String> mad = new MaxArrayDeque61B<>(new StringLengthComparator());
        mad.addFirst("");
        mad.addFirst("2");
        mad.addFirst("fury road");
        assertThat(mad.max()).isEqualTo("fury road");
        assertThat(mad.max(new StringLengthComparator())).isEqualTo("fury road");
    }

    @Test
    public void numberTest() {
        MaxArrayDeque61B<Integer> mad = new MaxArrayDeque61B<>(new NumberComparator());
        mad.addFirst(2);
        mad.addFirst(3);
        mad.addFirst(1);
        assertThat(mad.max()).isEqualTo(3);
        assertThat(mad.max(new NumberComparator())).isEqualTo(3);
    }
}
