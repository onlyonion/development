package com.onion.test.common.args;

import com.beust.jcommander.Parameter;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;

public class Args {

    @Parameter
    private List<String> parameters = new ArrayList<>();
    @Parameter(names = {"-log", "-verbose"}, description = "Level of verbosity")
    private Integer verbose = 1;
    @Parameter(names = "-groups", description = "Comma-separated list of group names to be run")
    private String groups;
    @Parameter(names = "-debug", description = "Debug mode")
    private boolean debug = false;

    @Test
    public void test() {
        Args args = new Args();
        String[] argv = {"-log", "2", "-groups", "unit"};
    }
}
