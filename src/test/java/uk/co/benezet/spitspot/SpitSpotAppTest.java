package uk.co.benezet.spitspot;

import com.google.common.collect.ImmutableList;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SpitSpotAppTest {

    @Test
    public void acceptanceTest() {
        assertThat(Functions.squash("Spin", "Spot", ImmutableList.of("Spin","Spit","Spat","Spot","Span")))
                .isEqualTo(ImmutableList.of("Spin","Spit","Spot"));
    }

}