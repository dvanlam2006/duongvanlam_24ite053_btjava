package oop5;

public class InvoiceItem {
    private String id;
    private String desc;
    private double qty;
    private double unitPrice;

    public InvoiceItem() {}
    public InvoiceItem(String id, String desc, double qty, double unitPrice) {
        this.id = id;
        this.desc = desc;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public String getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }
    public double getTotal() {
        return qty * unitPrice;
    }
    public String toString() {
        return "InvoiceItem[id=" + id +", `desc`=" + desc + ", qty=" + qty + ", unitPrice=" + unitPrice + "]";
    }
}
