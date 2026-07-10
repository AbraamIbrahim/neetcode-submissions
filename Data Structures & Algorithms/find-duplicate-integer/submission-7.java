class Solution {
    public int findDuplicate(int[] nums) {
        int n = nums.length;
        for(int i = 0; i < n; i++){
            int indexVal = nums[Math.abs(nums[i]) - 1];
            if(indexVal < 0){
            //this value was accessed before --> what we landed on 
                //is the duplicate
                return Math.abs(nums[i]);
            }
            else{
                nums[Math.abs(nums[i]) - 1] *= - 1;
            }
        }
        for(int i = n - 1; i > 0; i--){
            int indexVal = nums[Math.abs(nums[i]) - 1];
            if(indexVal < 0){
            //this value was accessed before --> what we landed on 
                //is the duplicate
                return Math.abs(nums[i]);
            }
            else{
                nums[Math.abs(nums[i]) - 1] *= - 1;
            }
        }
        return 0;
    }
}
