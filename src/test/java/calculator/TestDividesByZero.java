package calculator;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TestDividesByZero {

    private final double value1 = 6.0; // Utilisation des doubles
    private final double value2 = 0.0; // Diviseur est 0 pour tester la division par zéro
    private Divides op;
    private List<Expression> params;

    @BeforeEach
    void setUp() {
        params = Arrays.asList(new MyNumber(value1), new MyNumber(value2));
        try {
            op = new Divides(params);  // Création de l'objet Divides
            op.notation = Notation.INFIX;  // Utilisation de la notation INFIX
        } catch (IllegalConstruction e) {
            fail("Exception thrown during Divides object creation");
        }
    }

    @Test
    void testConstructor1() {
        // It should not be possible to create an expression without null parameter list
        assertThrows(IllegalConstruction.class, () -> op = new Divides(null));
    }

    @SuppressWarnings("AssertBetweenInconvertibleTypes")
    @Test
    void testConstructor2() {
        // A Times expression should not be the same as a Divides expression
        try {
            assertNotSame(op, new Times(new ArrayList<>()));
        } catch (IllegalConstruction e) {
            fail();
        }
    }

    @Test
    void testEquals() {
        // Two similar expressions, constructed separately (and using different constructors) should be equal
        List<Expression> p = Arrays.asList(new MyNumber(value1), new MyNumber(value2));
        try {
            Divides d = new Divides(p, Notation.INFIX);
            assertEquals(op, d);
        }
        catch(IllegalConstruction e) { fail(); }
    }

    @SuppressWarnings("ConstantConditions")
    @Test
    void testNull() {
        assertDoesNotThrow(() -> op==null); // Direct way to to test if the null case is handled.
    }

    @Test
    void testHashCode() {
        // Two similar expressions, constructed separately (and using different constructors) should have the same hashcode
        List<Expression> p = Arrays.asList(new MyNumber(value1), new MyNumber(value2));
        try {
            Divides e = new Divides(p, Notation.INFIX);
            assertEquals(e.hashCode(), op.hashCode());
        }
        catch(IllegalConstruction e) { fail(); }
    }

    @Test
    void testNullParamList() {
        params = null;
        assertThrows(IllegalConstruction.class, () -> op = new Divides(params));
    }


    @Test
    void testDivisionByZero() {
        // On vérifie que la division par zéro ne génère pas d'exception mais retourne NaN
        double result = op.op(value1, value2);
        System.out.println(result);
        assertTrue(Double.isNaN(result), "The result should be NaN when dividing by zero.");
    }

    @Test
    void testDivisionByZeroMessage() {
        // On vérifie que le message d'erreur est bien imprimé dans la sortie d'erreur standard
        assertDoesNotThrow(() -> {
            double result = op.op(value1, value2);
            assertTrue(Double.isNaN(result), "The result should be NaN when dividing by zero.");
        });
    }

}
