public class Main {

    public static void main(String[] args) {
        Tree t = new Tree();
        int arr[] = {2,4,0,1,5,6,-1};

        for(int i=0; i<arr.length; i++)
            t.add(arr[i]);
        t.delete(2);
        t.printTree(t.getRoot(), 0);

    }
}
