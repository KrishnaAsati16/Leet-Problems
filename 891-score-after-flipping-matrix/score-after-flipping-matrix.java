// yeh h mera concept ----------------------------------------------->

//  class Solution {
//         public int matrixScore(int[][] arr) {
//             // 0th col of matrix should have all ones
//             // har us col ko flip karo jisme no of 0 > no of 1
//         int m = arr.length, n = arr[0].length;
//         for(int i = 0; i<m;i++){
//             if(arr[i][0]==0){    // flip that row
//                 for(int j=0; j<n;j++){
// //                    arr[i][j] ^=1;
//                     arr[i][j] =1 -arr[i][j];
//                 }
//             }
//         }
//             // har us col ko flip karo jisme no of 0 > no of 1
//             for(int j = 0; j<m;j++) {
//                 int zeroes = 0 , ones =0;
//                 for (int i = 0; i < n; i++) {
//                     if(arr[i][j]==0) zeroes++;
//                     else ones++;
//                 }
//                 if(zeroes>ones){ // flip that column
//                     for(int i=0;i<m;i++){
// //                        if(arr[i][j]==0) zeroes++;
// //                        else  ones++;
//                         arr[i][j] ^=1;
//                     }
//                 }
//             }

//             int sum =0;
//               int pow =1;

//             for(int j = n-1; j>=0;j--) {
//                 int ones =0;
//                 for (int i = 0; i < n; i++) {
//                     if(arr[i][j]==0) ones++;
//                 }
//               sum+= pow*ones;
//                 pow*=2;
//             }
//             return sum;
//         }
//     }



// ---------------------------------------------------------------------------------------->

class Solution {
    public int matrixScore(int[][] arr) {
        int m = arr.length;      // rows count
        int n = arr[0].length;   // columns count

        // Step 1: Har row ko flip karo jiska pehla element (0th col) 0 hai
        // taaki 0th column me sab 1 ho jaye (MSB hamesha 1 hona chahiye max value ke liye)
        for (int i = 0; i < m; i++) {
            if (arr[i][0] == 0) { // is row ka first bit 0 hai, isko flip karo
                for (int j = 0; j < n; j++) {
                    arr[i][j] = 1 - arr[i][j]; // flip: 0->1, 1->0
                }
            }
        }

        // Step 2: Har column ke liye check karo zeroes > ones hai ya nahi
        // Agar zeroes zyada hai to us column ko flip kar do (isse ones badhenge -> score badhega)
        for (int j = 0; j < n; j++) { // BUG FIX: columns ke liye loop 'n' tak jaana chahiye (pehle 'm' tha)
            int zeroes = 0, ones = 0;
            for (int i = 0; i < m; i++) { // BUG FIX: rows ke liye loop 'm' tak jaana chahiye (pehle 'n' tha)
                if (arr[i][j] == 0) zeroes++;
                else ones++;
            }
            if (zeroes > ones) { // is column me zeroes zyada hai, isko flip karo
                for (int i = 0; i < m; i++) { // BUG FIX: yahan bhi 'm' use karo, 'n' nahi
                    arr[i][j] ^= 1;
                }
            }
        }

        // Step 3: Final score calculate karo
        // Har column ka contribution = (us column me ones ki count) * 2^(position weight)
        int sum = 0;
        int pow = 1;
        for (int j = n - 1; j >= 0; j--) { // last column (LSB) se start, pow har baar double hoga
            int ones = 0;
            for (int i = 0; i < m; i++) { // BUG FIX: rows ke liye loop 'm' tak jaana chahiye (pehle 'n' tha)
                if (arr[i][j] == 1) ones++; // BUG FIX: 'ones' ginna hai, isliye condition ==1 honi chahiye (pehle ==0 tha)
            }
            sum += pow * ones; // is column ka contribution total sum me add karo
            pow *= 2;          // agli (left wali) column ke liye weight double
        }
        return sum;
    }
}