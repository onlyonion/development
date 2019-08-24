package groovy

class GroovyDemo {
    static int add(int x, int y) {
        return x + y;
    }

    static main(args) {
        int z = add(123, 321);
        println("x+y=" + z);
    }

}