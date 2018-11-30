package uk.co.benezet.spitspot;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@ToString
@EqualsAndHashCode
class Tuple {
    public List<String> filtered;
    public List<String> theRest;

    public Tuple(List<String> filtered, List<String> theRest) {
        this.filtered = filtered;
        this.theRest = theRest;
    }
}
