package DTO;

public class RecycleItem {

    int itemId;

    float amount;

    float weight;

    String type;

    public RecycleItem setId(int itemId){
        this.itemId = itemId;
        return this;
    }

    public RecycleItem setAmount(float amount){
        this.amount = amount;
        return this;
    }

    public RecycleItem setWeight(float weight){
        this.weight = weight;
        return this;
    }

    public RecycleItem setType(String type){
        this.type = type;
        return this;
    }

    public int getItemId() {
        return itemId;
    }

    public float getAmount() {
        return amount;
    }

    public float getWeight() {
        return weight;
    }

    public String getType() {
        return type;
    }
}
