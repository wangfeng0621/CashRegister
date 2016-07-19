package View;

import Presenter.OutPutBill;
import Presenter.ResetBillEmpty;

/**
 * Created by feng on 2016/7/17.
 */
public class PrintBill {

    public PrintBill() {
        OutPutBill print = new OutPutBill();
        ResetBillEmpty reset = new ResetBillEmpty();
        print.printFullBill();
        reset.resetAll();
    }

}
