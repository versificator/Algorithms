//Given an array of distinct integers and a sum value. Find count of triplets with sum smaller than given sum value.
//Expected Time Complexity is O(n2).

import java.util.Arrays;
class CountTriplets {
  
  static int[] arr = new int[]{6,5,4,3,2,1};
  
  
  static int countTriplets(int n, int sum) {
    int tripletsCount=0;
    Arrays.sort(arr);
    for (int i=0; i<n-2; i++){
      
      int j = i+1;
      int k = n-1;
      while (j<k){
        
        if (arr[i] + arr[j] + arr[k] < sum){
          System.out.println(i + "  " + j + "  " + k);
          tripletsCount+= k - j;
          j++;
        }
        else
          k--;
      }
      
    }
    return tripletsCount;
  }
  
  public static void main(String[] args) {
   System.out.println(countTriplets(6,10)); 
  }
}