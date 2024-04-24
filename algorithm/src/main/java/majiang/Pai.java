package majiang;

import java.util.Objects;

public class Pai {
    public static String wang = "萬";
    public static String tiao = "条";
    public static String tong = "同";


    int id;
    String type;
    int typeInt;
    int val;
    private boolean isUsed;

    Pai next;
    Pai pre;

    public Pai(int typeInt, int val){
        if(typeInt != 0 && typeInt != 1 && typeInt != 2){
            throw new IllegalArgumentException("typeInt=" + typeInt + "不合法");
        }
        String type;
        switch(typeInt){
            case 0: type = wang; break;
            case 1: type = tong; break;
            case 2: type = tiao; break;
            default: type = null;
        }
        this.typeInt = typeInt;
        this.type = type;
        this.val = val;
    }

    public boolean isUsed(){
        return isUsed;
    }

    public void setUsed(boolean used){
        this.isUsed = used;
    }

    @Override
    public String toString(){
        return this.val + this.type + "(" + id + ")_";
    }

    public String toStringWithoutId(){
        return this.val + this.type;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || getClass() != obj.getClass()) return false;
        if(this == obj) return true;
        Pai pai = (Pai) obj;
        return this.typeInt == pai.typeInt && this.val == pai.val;
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeInt, val);
    }
}
