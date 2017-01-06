/**
 * Created by antz on 27/11/2016.
 */
import java.util.ArrayList;
import java.util.Stack;
//See koodijupp pärineb suures osas
// http://codereview.stackexchange.com/questions/36214/find-all-subsets-of-an-int-array-whose-sums-equal-a-given-target
//Mõned muudatused ise juurde kirjutatud
public class GetAllSubsetsByStack {

    /**
     * Set a value for target sum
     */
    ArrayList<ArrayList> stackslist = new ArrayList<>();
    ArrayList stacks = new ArrayList<>();
    ArrayList list = new ArrayList();
    int TARGET_SUM;

    public GetAllSubsetsByStack(int TARGET, int [] DATA) {
        this.TARGET_SUM = TARGET;
        populateSubset(DATA, 0, DATA.length);
    }

    public void setTARGET(int value) {
        this.TARGET_SUM = value;
    }

    private Stack<Integer> stack = new Stack();

    /**
     * Store the sum of current elements stored in stack
     */
    private int sumInStack = 0;

    public void populateSubset(int[] data, int fromIndex, int endIndex) {

        /*
        * Check if sum of elements stored in Stack is equal to the expected
        * target sum.
        *
        * If so, call print method to print the candidate satisfied result.
        */

        if (sumInStack == TARGET_SUM) {
            print(stack);

        }

        for (int currentIndex = fromIndex; currentIndex < endIndex; currentIndex++) {

            if (sumInStack + data[currentIndex] <= TARGET_SUM) {
                stack.push(data[currentIndex]);
                sumInStack += data[currentIndex];


                /*
                * Make the currentIndex +1, and then use recursion to proceed
                * further.
                */
                populateSubset(data, currentIndex + 1, endIndex);
                sumInStack -= stack.pop();

            }

        }
    }

    /**
     * Print satisfied result. i.e. 15 = 4+6+5
     */

    private void print(Stack<Integer> stack) {
        StringBuilder sb = new StringBuilder();
        sb.append(TARGET_SUM).append(" = ");
        for (Integer i : stack) {
            stacks.add(i);
            sb.append(i).append("+");
        }
        //System.out.println(sb.deleteCharAt(sb.length() - 1).toString()); //Prindiks välja konsooli summad ja liidetavad
    }

    public void toStacklist(int summa) {
        int sum = 0;
        for (int i = 0; i < stacks.size(); i++) {
            sum += (int)stacks.get(i);
            list.add(stacks.get(i));
            if (sum == summa){
                ArrayList temp = new ArrayList(list);
                stackslist.add(temp);
                list.clear();
                sum = 0;
            }
        }
    }
}