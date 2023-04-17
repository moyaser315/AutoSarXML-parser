public class Div implements java.lang.Comparable<Div> {
    private String id;
    private String lname;
    private String sname;

    public Div(String i, String lna, String sna) {
        this.id = i;
        this.lname = lna;
        this.sname = sna;

    }

    public String getName() {
        return this.sname;
    }

    @Override
    public int compareTo(Div o) {
        String s1 = o.getName();
        int co = s1.compareTo(this.getName());
        if (co > 0)
            return -1;
        else if (co == 0)
            return 0;

        return 1;
    }

    public String toString() {
        return "    <CONTAINER UUID=\"" + this.id + "\">\n"
                + "	    <SHORT-NAME>" + this.sname + "</SHORT-NAME>\n"
                + "	    <LONG-NAME>" + this.lname + "</LONG-NAME>\n"
                + "    </CONTAINER>";
    }

}
