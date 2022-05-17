package recursion;

//递归计算调和级数 1/1 + 1/2 + 1/3 + ... 1/n
public class harmonic {

    public static float harmonic(int n) {
        if (n <= 1) return 1;
        return harmonic(n-1)+1f/n;
    }

    public static void main(String[] args) {
        System.out.println(harmonic.harmonic(1));
    }

}
