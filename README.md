# shuati

Array category algorithm takeaways:

1. If there's a range for the defined numbers in the array, create an array with numbers of the range
eg. range given to you is integer from 0 to 10000, create an array with 10000 elements, this way you can achieve sorting in O(n) time
eg 561.java

2. If the range given by the problem is positive integer, then you can achieve O(1) space by using negative integer as a visited mark.
eg. 442.java

3. Subarray series
	53
	238
	713
	628
	152
	560
	209
	525

 数组类题型解法：

 1. 查找类：
 	在数组内查找特定的值, 例题：771， 561
 	查找类型一般会想到用hashmap，可是hashmap开销比较大，在特定情况下其实可以用数组代替hashmap -> 
 	特定范围类型：指的是题目中数组的值是有范围的，比如数组内的数只能是小写字母，
 	1）与其使用hashmap存储可以建立一个有26个元素的数组，数组的每一位代表某个字母是否存在
 	另外，创建这样的数组之后可以用来排序数组
 	例子：[b,c,d,a,g] -> arr[26], arr[0] 代表字母a，arr[1]代表字母b
 	2）可以用范围以外的数标记是否存在 例题：442，448， 用该元素的负数来标记此位置数存在数组中
