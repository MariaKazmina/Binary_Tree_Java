public class Node {

   // public int key; //ключ узла
    public int data; //некоторые данные в узле
    public Node leftChild; //левый потомок
    public Node rightChild; //правый потомок




    Node(int d)
    {
        data = d;
        leftChild = null;
        rightChild = null;
    }

    /**
     * Метод, который выводит на экран содержимое узла
     */
    public void printNode(){
        System.out.println(" DATA: " + data);
    }
}
