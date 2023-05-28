import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AviaSoulsTest {
    private final AviaSouls as = new AviaSouls();
    private Ticket ticket1;
    private Ticket ticket2;
    private Ticket ticket3;
    private Ticket ticket4;
    private Ticket ticket5;
    private Ticket ticket6;
    private Ticket ticket7;
    private Ticket ticket8;
    private TicketTimeComparator comparator;

    @BeforeEach
    public void TestEquipment() {
        ticket1 = new Ticket("muhosransk", "heavenly Life", 1500, 0, 1700);
        ticket2 = new Ticket("hrenpoymegde", "Beijing", 15, 2022, 1759);
        ticket3 = new Ticket("USSR", "Russia", 1500, 1959, 1900);
        ticket4 = new Ticket("muhosransk", "heavenly Life", 200, 1414, 2000);
        ticket5 = new Ticket("hrenpoymegde", "Beijing", 300, 1457, 1800);
        ticket6 = new Ticket("USSR", "Russia", 1500, 1959, 2200);
        ticket7 = new Ticket("USSR 2.0", "Russian Federation", 1500, 1959, 2200);
        ticket8 = new Ticket("hrenpoymegde", "Beijing", 300, 1457, 1900);
        comparator = new TicketTimeComparator();
        as.add(ticket1);
        as.add(ticket2);
        as.add(ticket3);
        as.add(ticket4);
        as.add(ticket5);
        as.add(ticket6);
        as.add(ticket7);
        as.add(ticket8);
    }

    @Test
    public void shouldAdd() {
        int exp = 9;
        as.add(ticket4);

        Assertions.assertEquals(exp, as.findAll().length);
    }

    @Test
    public void shouldSearchOne() {
        Ticket[] exp = {ticket7};
        Ticket[] act = as.search("USSR 2.0", "Russian Federation");

        Assertions.assertArrayEquals(exp, act);
    }

    @Test
    public void shouldSearchSeveral() {
        Ticket[] exp = {ticket4, ticket1};
        Ticket[] act = as.search("muhosransk", "heavenly Life");

        Assertions.assertArrayEquals(exp, act);
    }

    @Test
    public void shouldNotSearch() {
        Ticket[] exp = {};
        Ticket[] act = as.search("fidget spinner", "trash can");

        Assertions.assertArrayEquals(exp, act);
    }

    @Test
    public void shouldCompareOne() {
        Assertions.assertEquals(1, ticket1.compareTo(ticket2));
    }

    @Test
    public void shouldCompareZero() {
        Assertions.assertEquals(0, ticket1.compareTo(ticket3));
    }

    @Test
    public void shouldCompareMOne() {
        Assertions.assertEquals(-1, ticket2.compareTo(ticket1));
    }

    @Test
    public void shouldCompareSearch() {
        Ticket[] exp = {ticket4, ticket1};
        Ticket[] act = as.search("muhosransk", "heavenly Life");

        Assertions.assertArrayEquals(exp, act);
    }

    @Test
    public void shouldCompareSearchEquals() {
        Ticket[] exp = {ticket3, ticket6};
        Ticket[] act = as.search("USSR", "Russia");

        Assertions.assertArrayEquals(exp, act);
    }

    @Test
    public void shouldNotCompareSearch() {
        Ticket[] exp = {ticket3, ticket7};
        Ticket[] act = as.search("hrenpoymegde", "Beijing");

        Assertions.assertNotEquals(exp, act);
    }

    @Test
    public void shouldCompareWithComparatorTicketSecond() {
        int exp = -1;
        int act = comparator.compare(ticket1, ticket2);

        Assertions.assertEquals(exp, act);
    }

    @Test
    public void shouldCompareWithComparatorTicketEquals() {
        int exp = 0;
        int act = comparator.compare(ticket6, ticket7);

        Assertions.assertEquals(exp, act);
    }

    @Test
    public void shouldCompareWithComparatorTicketFirst() {
        int exp = 1;
        int act = comparator.compare(ticket2, ticket1);

        Assertions.assertEquals(exp, act);
    }

    @Test
    public void shouldCompareWithComparatorNull() {
        Ticket[] exp = {};

        Ticket[] act = as.searchAndSortBy(ticket3.getFrom(), ticket5.getTo(), comparator);

        Assertions.assertArrayEquals(exp, act);
    }

    @Test
    public void shouldCompareWithComparatorOne() {
        Ticket[] exp = {ticket7};

        Ticket[] act = as.searchAndSortBy(ticket7.getFrom(), ticket7.getTo(), comparator);

        Assertions.assertArrayEquals(exp, act);
    }

    @Test
    public void shouldCompareWithComparatorTwo() {
        Ticket[] exp = {ticket3, ticket6};

        Ticket[] act = as.searchAndSortBy(ticket3.getFrom(), ticket6.getTo(), comparator);

        Assertions.assertArrayEquals(exp, act);
    }

    @Test
    public void shouldCompareWithComparatorSeveral() {
        Ticket[] exp = {ticket2, ticket5, ticket8};

        Ticket[] act = as.searchAndSortBy(ticket2.getFrom(), ticket5.getTo(), comparator);

        Assertions.assertArrayEquals(exp, act);
    }

}
