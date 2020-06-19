package com.zzz.demo.other;

public class Algorithm {

    private static int[] array = new int[]{9,1,7,3,5,4,8,2,10};

    //插入排序
    public static void insertSort(){
        for (int i = 1; i < array.length; i++) {
            int temp = array[i];
            int k=i-1;
            while (k>=0 && array[k]>temp)k--;
            for (int j = i; j >k+1; j--) {
                array[j]=array[j-1];
            }
            array[k+1]=temp;
        }
        for (int i:array) {
            System.out.println(i);
        }
    }

    //选择排序
    public static void easySort() {
        for (int i = 0; i < array.length; i++) {
            int min = i;
            for (int j = i + 1; j < array.length; j++) {
                if (array[min] > array[j]) min = j;
            }
            int temp = array[min];
            array[min] = array[i];
            array[i] = temp;
        }
        for (int i:array) {
            System.out.println(i);
        }
    }

    //冒泡
    public static void bubbleSort(){
        for (int i = 0; i <array.length ; i++) {
            for (int j = 0; j <array.length-1-i ; j++) {
                if(array[j]>array[j+1]){
                    int temp =array[j+1];
                    array[j+1]=array[j];
                    array[j]=temp;
                }
            }
        }
        for (int i:array) {
            System.out.println(i);
        }
    }

    //归并排序
    public static int[] mergeSort(int[] arr, int left, int right) {
        // 如果 left == right，表示数组只有一个元素，则不用递归排序
        if (left < right) {
            // 把大的数组分隔成两个数组
            int mid = (left + right) / 2;
            // 对左半部分进行排序
            arr = mergeSort(arr, left, mid);
            // 对右半部分进行排序
            arr = mergeSort(arr, mid + 1, right);
            //进行合并
            merge(arr, left, mid, right);
        }
        return arr;
    }

    private static void merge(int[] arr, int left, int mid, int right) {
        //先用一个临时数组把他们合并汇总起来
        int[] a = new int[right - left + 1];
        int i = left;
        int j = mid + 1;
        int k = 0;
        while (i <= mid && j <= right) {
            if (arr[i] < arr[j]) {
                a[k++] = arr[i++];
            } else {
                a[k++] = arr[j++];
            }
        }
        while (i <= mid) a[k++] = arr[i++];
        while (j <= right) a[k++] = arr[j++];
        // 把临时数组复制到原数组
        for (i = 0; i < k; i++) {
            arr[left++] = a[i];
        }
    }


    //快排
    public static void quickSort(int[] arr,int left,int right){

        if (left<right){
            int temp = arr[left];
            int i=left;
            int j=right;
            while (i<j && arr[j]>=temp)j--;
            if(i<j)arr[i++]=arr[j];
            while (i<j && arr[i]<temp)i++;
            if(i<j)arr[j--]=arr[i];
            arr[i]=temp;
            quickSort(arr,left,i-1);
            quickSort(arr,i+1,right);
        }
    }

    public static int f(int n){
        if(n<=2){
            return 1;
        }
        return f(n-1)+f(n-2);
    }

    public static void main(String[] args) {
        //Algorithm.easySort();
        //Algorithm.bubbleSort();
        //Algorithm.insertSort();
        //Algorithm.mergeSort(array,0,8);
        /*Algorithm.quickSort(array,0,8);
        for (int i = 0; i <array.length ; i++) {
            System.out.println(array[i]);
        }*/

        int x =Algorithm.f(30);
        System.out.println(x);
    }

}
