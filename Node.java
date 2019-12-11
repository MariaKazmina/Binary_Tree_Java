import java.lang.Comparable;

public class Node<T extends Comparable<T>> {

   // public int key; //ключ узла
    public T data; //некоторые данные в узле
    public Node<T> leftChild; //левый потомок
    public Node<T> rightChild; //правый потомок




    Node(T d)
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
