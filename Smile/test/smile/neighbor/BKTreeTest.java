/******************************************************************************
 *                   Confidential Proprietary                                 *
 *         (c) Copyright Haifeng Li 2011, All Rights Reserved                 *
 ******************************************************************************/

package smile.neighbor;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import smile.math.distance.EditDistance;
import static org.junit.Assert.*;

/**
 *
 * @author Haifeng Li
 */
public class BKTreeTest {

    List<String> words = new ArrayList<String>();
    BKTree<String> bktree;
    LinearSearch<String> naive;

    public BKTreeTest() {
        try {
            InputStream stream = this.getClass().getResourceAsStream("/smile/neighbor/index.noun");
            BufferedReader input = new BufferedReader(new InputStreamReader(stream));
            String line = input.readLine();
            while (line != null) {
                if (!line.startsWith(" ")) {
                    String[] w = line.split("\\s");
                    words.add(w[0].replace('_', ' '));
                }
                line = input.readLine();
            }
        } catch (Exception e) {
            System.err.println(e);
        }

        String[] data = words.toArray(new String[1]);
        bktree = new BKTree<String>(new EditDistance(50, true));
        bktree.add(data);
        naive = new LinearSearch<String>(data, new EditDistance(50, true));
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
     * Test of range method, of class BKTree.
     */
    @Test
    public void testRange() {
        System.out.println("range");
        List<Neighbor<String, String>> n1 = new ArrayList<Neighbor<String, String>>();
        List<Neighbor<String, String>> n2 = new ArrayList<Neighbor<String, String>>();
        for (int i = 1000; i < 1100; i++) {
            bktree.range(words.get(i), 1, n1);
            naive.range(words.get(i), 1, n2);
            assertEquals(n1.size(), n2.size());
            String[] s1 = new String[n1.size()];
            String[] s2 = new String[n2.size()];
            for (int j = 0; j < s1.length; j++) {
                s1[j] = n1.get(j).value;
                s2[j] = n2.get(j).value;
            }
            Arrays.sort(s1);
            Arrays.sort(s2);
            for (int j = 0; j < s1.length; j++) {
                assertEquals(s1[j], s2[j]);
            }
            n1.clear();
            n2.clear();
        }
    }
}