/*
2. Write a subclass called SubClass that is derived from SuperClass and that adds an integer data
field called data2 and a public method called checkCondition() that will check if data1 is equal to
10 and data2 is equal to 15, the checkCondition () method should return “Condition True!”. Also,
create methods called setData2() and getData2() for setting and retrieving the value of data1 and
data2, as well as a constructor that accepts arguments for the starting values of data1 and data2.
data1 is data member of SuperClass.
*/
class SuperClass {
    protected int data1;

    public SuperClass(int data1) {
        this.data1 = data1;
    }

    public void setData1(int data1) {
        this.data1 = data1;
    }

    public int getData1() {
        return data1;
    }
}

class SubClass extends SuperClass {
    private int data2;

    // Constructor accepting starting values for data1 and data2
    public SubClass(int data1, int data2) {
        super(data1);
        this.data2 = data2;
    }

    public void setData2(int data2) {
        this.data2 = data2;
    }

    public int getData2() {
        return data2;
    }

    public String checkCondition() {
        if (data1 == 10 && data2 == 15) {
            return "Condition True!";
        }
        return "Condition False!";
    }
}

public class Exercise2 {
    public static void main(String[] args) {
        SubClass obj1 = new SubClass(10, 15);
        System.out.println("data1 = " + obj1.getData1() + ", data2 = " + obj1.getData2());
        System.out.println(obj1.checkCondition());

        SubClass obj2 = new SubClass(5, 15);
        System.out.println("data1 = " + obj2.getData1() + ", data2 = " + obj2.getData2());
        System.out.println(obj2.checkCondition());

        obj2.setData1(10);       // change data1 and data2 to satisfy the condition
        obj2.setData2(15);
        System.out.println("After update: data1 = " + obj2.getData1() + ", data2 = " + obj2.getData2());
        System.out.println(obj2.checkCondition());
    }
}
