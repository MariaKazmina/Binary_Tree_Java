import java.lang.Comparable;

public class Tree<T extends Comparable<T>> {

    private Node<T> root;  //корень дерева

    Node findElem(T d)   //поиск элемента по ключу
    {
        Node current = root;

        if (current == null)    //если пусто
            return null;

        while (current.data != d) {
            if (current.data.compareTo((d)) > 0) {   //current.data > d
                current = current.leftChild;
            } else current = current.rightChild;

        }
        return current;


    }



    void add(T data) {
        Node node = new Node(data);  //создание новой вершины
        if (root == null)
            root = node;
        else addTo(data, root);

    }


    void addTo(T data, Node<T> node) {
        Node current = node;

        if (current.data.compareTo(data) > 0)     //если вставляемое значение меньше значения в узле   current.data > data
        {
            if (current.leftChild == null)
                current.leftChild = new Node(data);  //создаем новую вершину
            else addTo(data, current.leftChild);

        } else {
            if (current.rightChild == null)
                current.rightChild = new Node(data);  //создаем новую вершину
            else addTo(data, current.rightChild);
        }


    }

    void delete(T data) {
        remove(root, data);
    }

    boolean remove(Node<T> node, T data) {
        Node<T> current = node;

        if (current == null)   //если дерево пусто
            return false;
        else {
            if (current.leftChild != null && current.leftChild.data.compareTo(data) > 0)  //если значение меньше, идем налево   current.leftChild.data > data
                remove(current.leftChild, data);
            if (current.rightChild != null && current.rightChild.data.compareTo(data) < 0) //если значение больше, идем направо    current.rightChild.data < data
                remove(current.rightChild, data);

            if (current.data.compareTo(data) == 0)        //если удаляется корень
            {
                if (current.leftChild == null && current.rightChild == null) {  //если потомков нет
                    current = null;
                } else if (current.leftChild != null && current.rightChild == null) {  //если есть только левый потомок
                    current.data = current.leftChild.data;
                    current.leftChild = current.leftChild.leftChild;  //подтянули левое поддерево

                } else if (current.rightChild != null && current.leftChild == null) {  //если есть только правый потомок
                    current.data = current.rightChild.data;
                    current.rightChild = current.rightChild.rightChild;  //подтянули правое поддерево

                } else {  //если есть оба потомка

                    if (current.rightChild.leftChild == null) {     //если у правого потомка нет левого потомка
                        current.data = current.rightChild.data;
                        current.rightChild = current.rightChild.rightChild;
                    } else {
                        //если у правого потомка есть левый потомок
                        Node<T> l = current.rightChild.leftChild;
                        while (l.leftChild != null)  //до крайнего левого
                            l = l.leftChild;
                        current.data = l.data;
                        remove(current.rightChild, l.data);

                    }
                }


            }
        }
        if (current.leftChild != null && current.leftChild.data == data) {   //если искомое значение в левом потомке
            Node<T> left = current.leftChild;

            if (left.leftChild == null && left.rightChild == null) { //если нет потомков
                current.leftChild = null;
            } else {
                return remove(left, data);
            }
        } else if (current.rightChild != null && current.rightChild.data == data) {  //искомое значение в правом потомке
            Node<T> right = current.rightChild;

            if (right.leftChild == null && right.rightChild == null) {
                current.rightChild = null;
            } else {
                return remove(right, data);
            }
        }
        return true;

    }

    Node findMin(Node<T> root) {
        if (root.leftChild != null) {
            return findMin(root.leftChild);
        } else {
            return root;
        }
    }

    void print(int level)  //обертка printTree
    {
        printTree(root,level);
    }
    void printTree(Node<T> tree, int level) {
        if (tree != null) {
            printTree(tree.leftChild, level + 1);
            for (int i = 0; i < level; i++)
                System.out.print(" ");
            System.out.println(tree.data);
            printTree(tree.rightChild, level + 1);
        }
    }

}

