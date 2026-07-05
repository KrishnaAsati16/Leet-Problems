class Solution {
    boolean isPowerTwo(long n){
        return (n > 0) && ((n & (n-1)) == 0);
    }
    boolean isSquare(long n) {
        long root = (long)(Math.sqrt(n));
        return (root*root == n);
    }
    boolean isPowerOfFour(long n){
        return (isPowerTwo(n) && isSquare(n));
    }
}