public class Tree {

    private Node root;  //корень дерева

    Node findElem(int d)   //поиск элемента по ключу
    {
        Node current = root;

        if (current == null)    //если пусто
            return null;

        while (current.data != d) {
            if (current.data > d) {
                current = current.leftChild;
            } else current = current.rightChild;

        }
        return current;


    }

    Node getRoot() {
        return root;
    }


    int compareTo(Node n) {
        if (root.data > n.data)
            return 1;
        else if (root.data < n.data) return -1;
        return 0;
    }

    void add(int data) {
        Node node = new Node(data);  //создание новой вершины
        if (root == null)
            root = node;
        else addTo(data, root);

    }


    void addTo(int data, Node node) {
        Node current = node;

        if (current.data > data)     //если вставляемое значение меньше значения в узле
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
    void delete(int data)
    {
        remove(root,data);
    }

    boolean remove(Node node, int data) {
        Node current = node;

        if (current == null)   //если дерево пусто
            return false;
        else {
            if (current.leftChild != null && current.leftChild.data > data)  //если значение меньше, идем налево
                remove(current.leftChild, data);
            if (current.rightChild != null && current.rightChild.data < data) //если значение больше, идем направо
                remove(current.rightChild, data);

            if (current.data == data)        //если удаляется корень
            {
                if (current.leftChild == null && current.rightChild == null) {  //если потомков нет
                    current = null;
                } else {
                    if (current.leftChild != null && current.rightChild == null) {  //если есть только левый потомок
                        current.data = current.leftChild.data;
                        current.leftChild = current.leftChild.leftChild;  //подтянули левое поддерево

                    } else {
                        if (current.rightChild != null && current.leftChild == null) {  //если есть только правый потомок
                            current.data = current.rightChild.data;
                            current.rightChild = current.rightChild.rightChild;  //подтянули правое поддерево

                        } else {
                            if (current.rightChild != null && current.leftChild != null) {  //если есть оба потомка

                                if (current.rightChild.leftChild == null) {     //если у правого потомка нет левого потомка
                                    current.data = current.rightChild.data;
                                    current.rightChild = current.rightChild.rightChild;
                                } else {
                                    if (current.rightChild.leftChild != null) {   //если у правого потомка есть левый потомок
                                        Node l = current.rightChild.leftChild;
                                        while (l.leftChild != null)  //до крайнего левого
                                            l = l.leftChild;
                                        current.data = l.data;
                                        remove(current.rightChild, l.data);

                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (current.leftChild != null && current.leftChild.data == data) {
                Node left = current.leftChild;

                if (left.leftChild == null && left.rightChild == null) //если удаляем лист: без потомков
                {
                    left = null;
                    current.leftChild = null;
                } else {
                    if (left.leftChild == null && left.rightChild != null) {   //если есть только правый потомок
                        left.data = left.rightChild.data;
                        current.leftChild = left.rightChild;

                    } else {
                        if (left.rightChild == null && left.leftChild != null) {   //если есть только левый потомок
                            left.data = left.leftChild.data;
                            current.leftChild = left.leftChild;
                        } else {
                            if (left.leftChild != null && left.rightChild != null) {  //два потомка
                                if (left.rightChild.leftChild == null) //если у правого нет левого потомка
                                {
                                    left.data = left.rightChild.data;
                                    left.rightChild = left.rightChild.rightChild;  //подтягиваем указатель на правое поддерево правого узла
                                } else {
                                    if (left.rightChild.leftChild != null) {
                                        Node l = left.rightChild;
                                        while (l.leftChild != null)
                                            l = l.leftChild;      // в конце хранится указатель на крайний левый элемент правого поддерева left
                                        left.data = l.data;       //заменим значение
                                        remove(left, l.data);        //удалим элемент
                                    }
                                }

                            }
                        }
                    }
                }
            } else {

                if (current.rightChild != null && current.rightChild.data == data) {  //если есть правый
                    Node right = current.rightChild;

                    if (right.leftChild == null && right.rightChild == null) //если удаляем лист: без потомков
                    {
                        right = null;
                        current.rightChild = null;
                    } else {
                        if (right.leftChild == null && right.rightChild != null) {
                            right.data = right.rightChild.data;
                            current.rightChild = right.rightChild;
                        } else {
                            if (right.rightChild == null && right.leftChild != null) {
                                right.data = right.leftChild.data;
                                current.rightChild = right.leftChild;
                            } else {
                                if (right.leftChild != null && right.rightChild != null) {
                                    if (right.rightChild.leftChild == null) //если нет самого крайнего левого в правом поддереве
                                    {
                                        right.data = right.rightChild.data;
                                        right.rightChild = right.rightChild.rightChild;  //подтягиваем указатель на правое поддерево правого узла
                                    } else {
                                        if (right.rightChild.leftChild != null) {
                                            Node r = right.rightChild;
                                            while (r.leftChild != null)
                                                r = r.leftChild;      // в конце хранится указатель на крайний левый элемент правого поддерева left
                                            right.data = r.data;       //заменим значение
                                            remove(right, r.data);        //удалим элемент
                                        }
                                    }


                                }
                            }
                        }
                    }
                }
            }

        }
        return true;
    }

    Node findMin(Node root) {
        if (root.leftChild != null) {
            return findMin(root.leftChild);
        } else {
            return root;
        }
    }

    void printTree(Node tree, int level)
    {
        if(tree != null)
        {
            printTree(tree.leftChild,level + 1);
            for(int i = 0;i< level;i++)
                System.out.print(" ");
            System.out.println(tree.data);
            printTree(tree.rightChild,level + 1);
        }
    }

}

