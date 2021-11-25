import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.*;

class helloTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
        System.out.println("starting test");
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
        System.out.println("finishing test");
    }

    @org.junit.jupiter.api.Test
    void id1() {
        Assertions.assertEquals(1, 1);
        Assertions.assertEquals(21, 21);
        Assertions.assertEquals(31, 31);
        Assertions.assertEquals(14, 14);
        Assertions.assertEquals(12, 12);
    }

    @org.junit.jupiter.api.Test
    void id2() {
        Assertions.assertEquals(1, 1);
        Assertions.assertEquals(21, 21);
        Assertions.assertEquals(31, 31);
        Assertions.assertEquals(12, 12);
    }

}