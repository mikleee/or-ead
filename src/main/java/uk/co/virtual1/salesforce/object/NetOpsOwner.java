package uk.co.virtual1.salesforce.object;

import java.util.ArrayList;
import java.util.List;

public class NetOpsOwner {

    private String name;

    private List<NetOpsCase> cases;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<NetOpsCase> getCases() {
        if (cases == null) {
            cases = new ArrayList<NetOpsCase>();
        }
        return cases;
    }

    public void setCases(List<NetOpsCase> cases) {
        this.cases = cases;
    }

}
