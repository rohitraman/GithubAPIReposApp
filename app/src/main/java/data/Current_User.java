package data;

public class Current_User implements  ReturnNames{
    String names;

    public String getNames() {
        return names;
    }

    @Override
    public void returnnames(String names) {
        this.names=names;
    }
}
