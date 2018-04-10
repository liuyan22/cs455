public class BinaryTree{
    
    private Node root;

    public BinaryTree(int treeVal; BinaryTree leftNode; BinaryTree rightNode){
        root = new Node();
        root.val = treeVal;
        root.right = rightNode.root;
        root.left = leftNode.root;
    }
    
    public Node{
        public int val;
        public Node right;
        public Node left;

    }
    
    public boolean find(int target){
        Node current = this.root;
        while(current != null && current.value != target){
            if(target < current.value){
                current = current.left;
            }else {
                current = current.right;
            }
            return current != null;
        }
    }
}