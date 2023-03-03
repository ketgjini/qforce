package nl.qnh.qforce.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class PeopleList {
    private Integer count;
    private String next;
    private String previous;
    private List<PersonResult> results;

    public PeopleList() {
        results = new ArrayList<>();
    }

}
