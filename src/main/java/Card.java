public class Card {
    private int id;
    private String name;
    private boolean matched = false;
    public Card(){

    }
    public Card(int id, boolean matched,String name) {
        this.id = id;
        this.matched = matched;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isMatched() {
        return matched;
    }

    public void setMatched(boolean matched) {
        this.matched = matched;
    }

    @Override
    public String toString() {
        return name;
    }
}
