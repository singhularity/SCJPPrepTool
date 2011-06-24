package Material;


/**
 * Created by IntelliJ IDEA.
 * User: saurabh.singh
 * Date: 5/17/11
 * Time: 2:18 PM
 * To change this template use File | Settings | File Templates.
 */
public class CollectionsEqualsExample {
    public static void main(String[] args){
        Bob f = new Bob("GoBob", 19);
        System.out.println(f);
    }
}

class Bob{
    int shoeSize;
    String nickName;
    Bob(String nickName, int shoeSize){
        this.shoeSize = shoeSize;
        this.nickName = nickName;
    }

    @Override
    public String toString(){
        return ("I am a Bob, but you cannot call me " + nickName + ". My shoe size is " + shoeSize);
    }
}
