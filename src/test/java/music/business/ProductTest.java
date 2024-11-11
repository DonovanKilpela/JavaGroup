package music.business;

import music.business.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductTest {
    
    public ProductTest() {
    }

    @BeforeAll
    public static void setUpClass() throws Exception {
    }

    @AfterAll
    public static void tearDownClass() throws Exception {
    }

    @BeforeEach
    public void setUp() throws Exception {
    }

    @AfterEach
    public void tearDown() throws Exception {
    }

    @Test
    public void testProductGettersAndSetters() {
        Product product = new Product();
        product.setCode("pf01");
        product.setDescription("Paddlefoot - The first CD");
        product.setPrice(12.95);

        assertEquals("pf01", product.getCode());
        assertEquals("Paddlefoot - The first CD", product.getDescription());
        assertEquals(12.95, product.getPrice(), 0.01);
    }

    @Test
    public void testPriceCurrencyFormat() {
        Product product = new Product();
        product.setPrice(14.95);
        String expectedFormat = "$14.95";

        assertEquals(expectedFormat, product.getPriceCurrencyFormat());
    }
}
