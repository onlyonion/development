package com.onion.test.common.groovy;

import java.util.HashMap;
import java.util.Map;
import javax.script.Bindings;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class GroovyUtils {
    ScriptEngineManager manager = new ScriptEngineManager();

    public ScriptEngine getScriptEngine() {
        ScriptEngine engine = manager.getEngineByName("groovy");
        return engine;
    }


    public void execute(ScriptEngine engine, String tname) throws ScriptException {
        Bindings bindings = engine.createBindings();
        Map<String, Object> map = new HashMap<>();
        map.put("name", tname);
        bindings.put("map", map);
        Object e = engine.eval(" map.remove('a');  map['name']='" + tname + "'; return map;", bindings);
        System.out.println(e);

        assert ("{name=" + tname + "}").equals(e.toString());
    }


    public static void main(String[] args) {
        GroovyUtils g = new GroovyUtils();
        ScriptEngine scriptEngine1 = g.getScriptEngine();

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        g.execute(scriptEngine1, Thread.currentThread().getName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, "T" + i).start();

        }

    }
}
