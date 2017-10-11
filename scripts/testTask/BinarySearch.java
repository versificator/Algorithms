class BinarySearch{
  
  public int binarySearch( int[] arr, int l, int r, int x){
    System.out.println(l + " " + r);
    if (r>=l){
      int middle = l + (r-l)/2;
      
      if (arr[middle] == x) return x;      
      if (arr[middle] < x) return binarySearch(arr,middle+1,r,x);
      if (arr[middle] > x) return binarySearch(arr,l,middle-1,x);
    }
    return -1;
  }
  
  public int binarySearch(int[] arr, int x){
    int l = 0;int r = arr.length - 1; int middle;
    
    while (r>=l){
      middle = l + (r-l)/2;
      
      if (arr[middle] == x) return x;
      if (arr[middle] > x) {
        r = middle -1;
      }
      else l = middle + 1;
    }
    return -1;
  }
  public static void main(String args[]){
    BinarySearch bs = new BinarySearch();
    int[] a = new int[]{1,2,3,4,5,6,7,8,9};
    int result1 = bs.binarySearch(a,0,a.length - 1, 3);
    int result2 = bs.binarySearch(a, 3);
    
    System.out.println("result1 " + result1 + "  result2 " + result2) ;
  }
  
}