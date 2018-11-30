package uk.co.benezet.spitspot;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


public class FunctionsTest {

    @Test
    public void subsetBehavesAppropriately() {

        assertThat(Functions.subset("A", "D", ImmutableList.of("A","B","C","D"))).isEqualTo(ImmutableList.of("A","B","C","D"));
        assertThat(Functions.subset("B", "D", ImmutableList.of("A","B","C","D"))).isEqualTo(ImmutableList.of("B","C","D"));
        assertThat(Functions.subset("A", "C", ImmutableList.of("A","B","C","D"))).isEqualTo(ImmutableList.of("A","B","C"));
        assertThat(Functions.subset("B", "C", ImmutableList.of("A","B","C","D"))).isEqualTo(ImmutableList.of("B","C"));


    }

    @Test
    public void filteredDoesFilter() {
        List<String> unfiltered = ImmutableList.of("AAAA","AAAB","AADD", "AAAC");
        assertThat(Functions.filtered.apply(unfiltered)).isEqualTo(ImmutableList.of("AAAA","AAAB","AAAC"));
    }

    @Test
    public void testFilter() {
        assertThat(Functions.filter.apply(new Tuple(ImmutableList.of(), ImmutableList.of("AA","AB","AC")))).isEqualTo(new Tuple(ImmutableList.of("AA"),ImmutableList.of("AB","AC")));
        assertThat(Functions.filter.apply(new Tuple(ImmutableList.of("AA"), ImmutableList.of("AB","AC")))).isEqualTo(new Tuple(ImmutableList.of("AA","AB"),ImmutableList.of("AC")));
        assertThat(Functions.filter.apply(new Tuple(ImmutableList.of("AA","AB"), ImmutableList.of("AC")))).isEqualTo(new Tuple(ImmutableList.of("AA","AB","AC"), ImmutableList.of()));
    }

    @Test
    public void firstBehavesAppropriately() {
        assertThat(Functions.firstOf(ImmutableList.of("A","B","C"))).isEqualTo("A");
    }

    @Test
    public void lastBehavesAppropriately() {
        assertThat(Functions.lastOf(ImmutableList.of("A","B","C"))).isEqualTo("C");
    }

    @Test
    public void characterDifferencesOfBehavesAppropriately() {
        assertThat(Functions.characterDifferencesOf("AAA","AAA")).isEqualTo(0);
        assertThat(Functions.characterDifferencesOf("AAA","ABA")).isEqualTo(1);
        assertThat(Functions.characterDifferencesOf("AAA","ABC")).isEqualTo(2);
    }
}