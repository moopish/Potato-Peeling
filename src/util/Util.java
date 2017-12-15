package util;

/**
 * === Util Class ===
 * For methods not really related to other classes
 * @author Michael van Dyk
 */
public final class Util {

    /**
     * Can't create an object of this
     */
    private Util() {}

    /**
     *  Function to use in place of the build in '%' operator as
     * that does not work on negative numbers for modulus.
     * @param a the value to mod
     * @param n the modulus
     * @return a mod n
     */
    public static int mod(int a, int n) {
        if (a < 0) {
            int res = (a + n * (-a/n + 1));
            return ((res == n ? 0 : res));
        } else {
            return (a % n);
        }
    }
}
