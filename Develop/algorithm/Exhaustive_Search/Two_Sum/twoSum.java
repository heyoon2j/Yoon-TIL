class Solution {
    public int[] twoSum(int[] nums, int target) {
        
        int a = 0;
        int b = 0;
        boolean isB = false;

        for(int i = 0; i<nums.length; i++){
            // if(nums[i]>target)
            //     continue;

            a = i;
            for(int j = i+1; j < nums.length; j++){
                // if(nums[j]>target)
                //     continue;

                if((nums[a]+nums[j]) == target){
                    b = j;
                    isB = true;
                    break;
                }
            }
            if(isB == true)
                break;
        }
        
        int[] result= {a,b};
        return result;            
    }
}
