/******************************************************************************
 *                   Confidential Proprietary                                 *
 *         (c) Copyright Haifeng Li 2011, All Rights Reserved                 *
 ******************************************************************************/
package smile.data.parser.microarray;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import smile.data.Attribute;
import smile.data.AttributeDataset;

/**
 *
 * @author Haifeng Li
 */
public class RESParserTest {
    
    public RESParserTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of parse method, of class RESParser.
     */
    @Test
    public void testParse() throws Exception {
        System.out.println("parse");
        RESParser parser = new RESParser();
        try {
            AttributeDataset data = parser.parse("RES", this.getClass().getResourceAsStream("/smile/data/microarray/all_aml_test.res"));
            
            double[][] x = data.toArray(new double[data.size()][]);
            String[] id = data.toArray(new String[data.size()]);
            
            for (Attribute attribute : data.attributes()) {
                assertEquals(Attribute.Type.NUMERIC, attribute.type);
                System.out.println(attribute.name + "\t" + attribute.description);
            }

            assertEquals(7129, data.size());
            assertEquals(35, data.attributes().length);

            assertEquals("AFFX-BioB-5_at", id[0]);
            assertEquals(-214, x[0][0], 1E-7);
            assertEquals(-342, x[0][1], 1E-7);
            assertEquals(-87, x[0][2], 1E-7);

            assertEquals("Z78285_f_at", id[7128]);
            assertEquals(16, x[7128][32], 1E-7);
            assertEquals(-73, x[7128][33], 1E-7);
            assertEquals(-60, x[7128][34], 1E-7);
        } catch (Exception ex) {
            System.err.println(ex);
        }
    }
}
