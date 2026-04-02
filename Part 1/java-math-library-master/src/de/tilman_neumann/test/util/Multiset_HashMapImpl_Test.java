package de.tilman_neumann.test.util;

import de.tilman_neumann.util.Multiset_HashMapImpl;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class Multiset_HashMapImpl_Test {
    // =========================================================
    // 1. add(T entry)
    // =========================================================
    // ISP --> add new element
    @Test
    void add_newElement() {
        // We create empty multiset
        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();

        // Add element "a" for first time
        int old = ms.add("a");

        // Old count should be 0 (it did not exist before)
        assertEquals(0, old);

        // Now "a" should exist with count = 1
        assertEquals(1, ms.get("a"));
    }
    // ISP --> add existing element
    @Test
    void add_existingElement() {
        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();

        // Add first time → count = 1
        ms.add("a");

        // Add again → count becomes 2
        int old = ms.add("a");

        // Old count should be 1 before adding again
        assertEquals(1, old);

        // New count should be 2
        assertEquals(2, ms.get("a"));
    }
    // ISP --> add multiple elements

    @Test
    void add_multipleTimes() {
        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();

        // Add same element many times
        ms.add("a");
        ms.add("a");
        ms.add("a");

        // Final count should be 3
        assertEquals(3, ms.get("a"));
    }
    // BVA --> add null
    @Test
    void add_nullKey() {
        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();

        // Add null (HashMap allows null key)
        ms.add(null);

        // Null should be stored with count 1
        assertEquals(1, ms.get(null));
    }

    // =========================================================
    // 2. add(T entry, int mult)
    // =========================================================
    // ISP --> add positive element , BVA >0 , mult
    @Test
    void add_mult_positive() {
        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();

        // Add "a" 3 times
        ms.add("a", 3);

        // Expect count = 3
        assertEquals(3, ms.get("a"));
    }

    // ISP --> add zero element , BVA --> 0 , mult
    @Test
    void add_mult_zero() {
        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();

        // mult = 0 → function ignores it (does nothing)
        ms.add("a", 0);

        // "a" should NOT exist
        assertNull(ms.get("a"));
    }
    // ISP --> add negative element BVA < 0, mult

    @Test
    void add_mult_negative() {
        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();

        // Negative mult → also ignored
        ms.add("a", -5);

        // "a" should NOT exist
        assertNull(ms.get("a"));
    }
    // ISP --> add null entry
    @Test
    void add_mult_nullKey() {
        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();

        // Add null multiple times
        ms.add(null, 2);

        assertEquals(2, ms.get(null));
    }
    // =========================================================
    // 3. addAll(Multiset<T> other)
    // =========================================================
    // ISP: other multiset has elements

    @Test
    void addAll_multiset_normal() {

        Multiset_HashMapImpl<String> ms1 = new Multiset_HashMapImpl<>();
        Multiset_HashMapImpl<String> ms2 = new Multiset_HashMapImpl<>();

        ms2.add("a", 2);

        ms1.addAll(ms2);

        // "a" should be copied with multiplicity
        assertEquals(2, ms1.get("a"));
    }

    @Test
    void addAll_multiset_mergeExisting() {
        // ISP: same key exists in both
        // Coverage: add() updates existing value

        Multiset_HashMapImpl<String> ms1 = new Multiset_HashMapImpl<>();
        Multiset_HashMapImpl<String> ms2 = new Multiset_HashMapImpl<>();

        ms1.add("a", 2);
        ms2.add("a", 3);

        ms1.addAll(ms2);

        // 2 + 3 = 5
        assertEquals(5, ms1.get("a"));
    }

    @Test
    void addAll_multiset_emptyOther() {
        // ISP: other is empty
        // BVA: size = 0
        // Coverage: loop not executed

        Multiset_HashMapImpl<String> ms1 = new Multiset_HashMapImpl<>();
        Multiset_HashMapImpl<String> ms2 = new Multiset_HashMapImpl<>();

        ms1.addAll(ms2);

        // ms1 should remain empty
        assertTrue(ms1.isEmpty());
    }

    @Test
    void addAll_multiset_null() {
        // ISP: other = null (BAD case)
        // Coverage: if condition fails

        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();

        ms.addAll((Multiset_HashMapImpl<String>) null);

        // Nothing should happen
        assertTrue(ms.isEmpty());
    }

    @Test
    void addAll_multiset_withNullKey() {
        // Edge: null key inside multiset

        Multiset_HashMapImpl<String> ms1 = new Multiset_HashMapImpl<>();
        Multiset_HashMapImpl<String> ms2 = new Multiset_HashMapImpl<>();

        ms2.add(null, 2);

        ms1.addAll(ms2);

        assertEquals(2, ms1.get(null));
    }
    // =========================================================
    // 4. addAll(Collection<T> values)
    // =========================================================

    @Test
    void addAll_collection_normal() {
        // ISP: normal collection with values
        // Coverage: loop executes

        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();

        ms.addAll(Arrays.asList("a", "b", "a"));

        // "a" appears twice, "b" once
        assertEquals(2, ms.get("a"));
        assertEquals(1, ms.get("b"));
    }

    @Test
    void addAll_collection_empty() {
        // ISP: empty collection
        // BVA: size = 0
        // Coverage: loop not entered

        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();

        ms.addAll(Collections.emptyList());

        assertTrue(ms.isEmpty());
    }

    @Test
    void addAll_collection_null() {
        // ISP: values = null (BAD case)
        // Coverage: if condition fails

        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();

        ms.addAll((Collection<String>) null);

        assertTrue(ms.isEmpty());
    }

    @Test
    void addAll_collection_duplicates() {
        // ISP: repeated values
        // Coverage: multiple loop iterations

        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();

        ms.addAll(Arrays.asList("x", "x", "x"));

        assertEquals(3, ms.get("x"));
    }

    @Test
    void addAll_collection_withNull() {
        // Edge: collection contains null

        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();

        ms.addAll(Arrays.asList(null, null));

        assertEquals(2, ms.get(null));
    }
    // =========================================================
    // 5. addAll(T[] values)
    // =========================================================
    @Test
    void addAll_array_normal() {
        // ISP: normal array
        // Coverage: loop executes

        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();

        String[] arr = {"a", "b", "a"};

        ms.addAll(arr);

        assertEquals(2, ms.get("a"));
        assertEquals(1, ms.get("b"));
    }

    @Test
    void addAll_array_empty() {
        // ISP: empty array
        // BVA: length = 0
        // Coverage: loop not entered

        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();

        String[] arr = {};

        ms.addAll(arr);

        assertTrue(ms.isEmpty());
    }

    @Test
    void addAll_array_null() {
        // ISP: array = null (BAD case)
        // Coverage: if condition fails

        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();

        ms.addAll((String[]) null);

        assertTrue(ms.isEmpty());
    }

    @Test
    void addAll_array_duplicates() {
        // ISP: repeated values

        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();

        String[] arr = {"x", "x", "x"};

        ms.addAll(arr);

        assertEquals(3, ms.get("x"));
    }

    @Test
    void addAll_array_withNull() {
        // Edge: null elements inside array

        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();

        String[] arr = {null, null};

        ms.addAll(arr);

        assertEquals(2, ms.get(null));
    }

    // =========================================================
    // 6. remove(Object key)
    // =========================================================

    // BVA --> remove one key count > 1, exist key <-- ISP
    @Test
    void remove_decreaseCount() {
        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();

        // Add twice
        ms.add("a", 5);

        // Remove once → count becomes 1
        ms.remove("a");

        assertEquals(4, ms.get("a"));
    }
    // BVA --> remove one key count = 1

    @Test
    void remove_lastElement() {
        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();

        // Add once
        ms.add("a");

        // Remove → should delete completely
        ms.remove("a");

        // "a" should not exist anymore
        assertNull(ms.get("a"));
    }
    // ISP --> remove non existing key
    @Test
    void remove_notExisting() {
        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();

        // Remove something not in set
        Integer result = ms.remove("x");

        // Should return null (nothing to remove)
        assertNull(result);
    }
    // ISP --> remove null key
    @Test
    void remove_nullKey() {
        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();

        ms.add(null);

        // Remove null
        ms.remove(null);

        assertNull(ms.get(null));
    }


    // =========================================================
    // 7. remove(T key, int mult)
    // =========================================================

    // ISP --> remove multiple keys
    @Test
    void remove_mult_normal() {
        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();

        ms.add("a", 5);

        // Remove 2 → left = 3
        ms.remove("a", 2);

        assertEquals(3, ms.get("a"));
    }

    // ISP --> remove exact key
    @Test
    void remove_mult_exact() {
        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();

        ms.add("a", 3);

        // Remove all exactly
        ms.remove("a", 3);

        assertNull(ms.get("a"));
    }
    // ISP --> remove more than existing key

    @Test
    void remove_mult_moreThanExists() {
        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();

        ms.add("a", 2);

        // Remove more than exists → should delete completely
        ms.remove("a", 10);

        assertNull(ms.get("a"));
    }
    // ISP --> remove zero key
    @Test
    void remove_mult_zero() {
        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();

        ms.add("a", 2);

        // Remove 0 → nothing changes
        ms.remove("a", 0);

        assertEquals(2, ms.get("a"));
    }
    // BVA --> remove non existing key
    @Test
    void remove_mult_notExist() {
        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();

        // Removing non-existing key
        int result = ms.remove("x", 3);

        assertEquals(0, result);
    }

    // =========================================================
    // 8. removeAll(T key)
    // =========================================================
// ISP --> exist BVA: count > 1
    @Test
    void removeAll_existing() {
        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();

        ms.add("a", 4);

        int removed = ms.removeAll("a");

        // Should return old count
        assertEquals(4, removed);

        // "a" should be gone
        assertNull(ms.get("a"));
    }
    // ISP --> Not exist , BVA: count = 0

    @Test
    void removeAll_notExisting() {
        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();

        assertEquals(0, ms.removeAll("x"));
    }
    @Test
    void removeAll_exactOne() {
        // ISP: Key exists once
        // BVA: count = 1 (boundary case)

        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();
        ms.add("a");

        int result = ms.removeAll("a");

        // Should return old count = 1
        assertEquals(1, result);

        // Key should be completely removed
        assertNull(ms.get("a"));
    }
    // Test exist different elements
    @Test
    void removeAll_afterMultipleOperations() {

        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();
        ms.add("a", 2);
        ms.add("b", 3);

        int result = ms.removeAll("a");

        // Should remove only "a"
        assertEquals(2, result);

        // "a" gone
        assertNull(ms.get("a"));

        // "b" still exists
        assertEquals(3, ms.get("b"));
    }

    // =========================================================
    // 9. intersect(Multiset<T> other)
    // =========================================================

    @Test
    void intersect_basicHappy() {
        // ISP: both sets have common element
        // BVA: counts > 1
        // Coverage: enters all conditions (other != null, key exists, jointMult > 0)

        Multiset_HashMapImpl<String> a = new Multiset_HashMapImpl<>();
        Multiset_HashMapImpl<String> b = new Multiset_HashMapImpl<>();

        a.add("x", 3);
        b.add("x", 2);

        Multiset_HashMapImpl<String> result =
                (Multiset_HashMapImpl<String>) a.intersect(b);

        // min(3,2) = 2
        assertEquals(2, result.get("x"));
    }

    @Test
    void intersect_noCommonElements() {
        // ISP: no shared keys
        // Coverage: otherMult == null branch

        Multiset_HashMapImpl<String> a = new Multiset_HashMapImpl<>();
        Multiset_HashMapImpl<String> b = new Multiset_HashMapImpl<>();

        a.add("a");
        b.add("b");

        Multiset_HashMapImpl<String> result =
                (Multiset_HashMapImpl<String>) a.intersect(b);

        // Expected empty result
        assertTrue(result.isEmpty());
    }

    @Test
    void intersect_otherIsNull() {
        // ISP: other = null (BAD case)
        // Coverage: skips whole loop

        Multiset_HashMapImpl<String> a = new Multiset_HashMapImpl<>();
        a.add("a", 2);

        Multiset_HashMapImpl<String> result =
                (Multiset_HashMapImpl<String>) a.intersect(null);

        // Should return empty set
        assertTrue(result.isEmpty());
    }

    @Test
    void intersect_boundaryCountOne() {
        // BVA: count = 1
        // Coverage: jointMult = 1

        Multiset_HashMapImpl<String> a = new Multiset_HashMapImpl<>();
        Multiset_HashMapImpl<String> b = new Multiset_HashMapImpl<>();

        a.add("a", 1);
        b.add("a", 5);

        Multiset_HashMapImpl<String> result =
                (Multiset_HashMapImpl<String>) a.intersect(b);

        assertEquals(1, result.get("a"));
    }

    // =========================================================
    // 10. totalCount()
    // =========================================================
    @Test
    void totalCount_normal() {
        // ISP: multiple elements
        // Coverage: loop executes

        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();
        ms.add("a", 2);
        ms.add("b", 3);

        // Expected: 2 + 3 = 5
        assertEquals(5, ms.totalCount());
    }

    @Test
    void totalCount_emptySet() {
        // ISP: empty set
        // BVA: sum = 0
        // Coverage: loop not entered

        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();

        assertEquals(0, ms.totalCount());
    }

    @Test
    void totalCount_boundaryOne() {
        // BVA: total = 1

        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();
        ms.add("a");

        assertEquals(1, ms.totalCount());
    }

    @Test
    void totalCount_largeValues() {
        // Edge: large numbers

        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();
        ms.add("a", 1000);

        assertEquals(1000, ms.totalCount());
    }

    @Test
    void totalCount_withNullKey() {
        // Edge: null key allowed

        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();
        ms.add(null, 3);

        assertEquals(3, ms.totalCount());
    }

    // =========================================================
    // 11. toList()
    // =========================================================

    @Test
    void toList_normal() {
        // ISP: normal case
        // Coverage: nested loops executed

        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();
        ms.add("a", 2);

        List<String> list = ms.toList();

        // List should contain "a" twice
        assertEquals(2, list.size());
        assertTrue(list.contains("a"));
    }

    @Test
    void toList_multipleElements() {
        // ISP: multiple keys

        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();
        ms.add("a", 2);
        ms.add("b", 1);

        List<String> list = ms.toList();

        // Total elements = 3
        assertEquals(3, list.size());
    }

    @Test
    void toList_empty() {
        // ISP: empty set
        // BVA: size = 0
        // Coverage: loops not executed

        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();

        assertTrue(ms.toList().isEmpty());
    }

    @Test
    void toList_boundaryOne() {
        // BVA: multiplicity = 1

        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();
        ms.add("x", 1);

        List<String> list = ms.toList();

        assertEquals(1, list.size());
    }

    @Test
    void toList_largeMultiplicity() {
        // Edge: large repetition

        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();
        ms.add("a", 100);

        List<String> list = ms.toList();

        assertEquals(100, list.size());
    }

    @Test
    void toList_withNullKey() {
        // Edge: null key

        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();
        ms.add(null, 2);

        List<String> list = ms.toList();

        // List should contain null twice
        assertEquals(2, list.size());
        assertTrue(list.contains(null));
    }

    // =========================================================
    // 12. toString()
    // ================================================
    @Test
    void toString_emptySet() {
        // ISP: empty multiset
        // BVA: size = 0 (boundary)
        // Coverage: goes to ELSE branch → return "{}"

        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();

        assertEquals("{}", ms.toString());
    }

    @Test
    void toString_singleElement() {
        // ISP: one element
        // BVA: multiplicity = 1
        // Coverage: loop runs once, no "^" added

        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();
        ms.add("a");

        // Expected: {a}
        assertEquals("{a}", ms.toString());
    }

    @Test
    void toString_multipleElements() {
        // ISP: multiple distinct elements
        // Coverage: loop runs multiple times

        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();
        ms.add("a");
        ms.add("b");

        String result = ms.toString();

        // Order is not guaranteed → check contains instead of exact match
        assertTrue(result.contains("a"));
        assertTrue(result.contains("b"));
    }

    @Test
    void toString_multiplicityGreaterThanOne() {
        // ISP: element appears multiple times
        // BVA: multiplicity > 1
        // Coverage: enters (multiplicity > 1) branch

        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();
        ms.add("a", 3);

        String result = ms.toString();

        // Expected format contains "^3"
        assertTrue(result.contains("a^3"));
    }

    @Test
    void toString_nullKey() {
        // Edge case: null key
        // Coverage: entry.getKey() = null

        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();
        ms.add(null, 2);

        String result = ms.toString();

        assertTrue(result.contains("null^2"));
    }

    @Test
    void toString_largeData() {
        // Edge: many elements (performance + correctness)

        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();
        for (int i = 0; i < 50; i++) {
            ms.add("x");
        }

        String result = ms.toString();

        assertTrue(result.contains("x^50"));
    }

    // =========================================================
    // 13. equals()
    // =========================================================

    @Test
    void equals_sameContentDifferentOrder() {
        // ISP: same elements but different order
        // Coverage: full loop + equality true

        Multiset_HashMapImpl<String> a = new Multiset_HashMapImpl<>();
        Multiset_HashMapImpl<String> b = new Multiset_HashMapImpl<>();

        a.add("a");
        a.add("b");

        b.add("b");
        b.add("a");

        assertTrue(a.equals(b));
    }

    @Test
    void equals_sameElementsSameMultiplicity() {
        // ISP: identical multisets
        // BVA: multiplicity > 1

        Multiset_HashMapImpl<String> a = new Multiset_HashMapImpl<>();
        Multiset_HashMapImpl<String> b = new Multiset_HashMapImpl<>();

        a.add("a", 3);
        b.add("a", 3);

        assertTrue(a.equals(b));
    }

    @Test
    void equals_differentMultiplicity() {
        // ISP: same key but different counts
        // Coverage: triggers (!otherMult.equals(myMult))

        Multiset_HashMapImpl<String> a = new Multiset_HashMapImpl<>();
        Multiset_HashMapImpl<String> b = new Multiset_HashMapImpl<>();

        a.add("a", 2);
        b.add("a", 1);

        assertFalse(a.equals(b));
    }

    @Test
    void equals_differentSize() {
        // ISP: different number of elements
        // Coverage: triggers (this.size != other.size)

        Multiset_HashMapImpl<String> a = new Multiset_HashMapImpl<>();
        Multiset_HashMapImpl<String> b = new Multiset_HashMapImpl<>();

        a.add("a");
        b.add("a");
        b.add("b");

        assertFalse(a.equals(b));
    }

    @Test
    void equals_nullObject() {
        // ISP: o = null (BAD case)
        // Coverage: fails first condition

        Multiset_HashMapImpl<String> a = new Multiset_HashMapImpl<>();

        assertFalse(a.equals(null));
    }

    @Test
    void equals_differentType() {
        // ISP: object not Multiset
        // Coverage: instanceof fails

        Multiset_HashMapImpl<String> a = new Multiset_HashMapImpl<>();

        assertFalse(a.equals("not a set"));
    }

    @Test
    void equals_withNullKey() {
        // Edge: null keys

        Multiset_HashMapImpl<String> a = new Multiset_HashMapImpl<>();
        Multiset_HashMapImpl<String> b = new Multiset_HashMapImpl<>();

        a.add(null, 2);
        b.add(null, 2);

        assertTrue(a.equals(b));
    }

    // =========================================================
    // 14. hashCode
    // =========================================================

    @Test
    void hashCode_shouldThrow() {
        Multiset_HashMapImpl<String> ms = new Multiset_HashMapImpl<>();

        // This method is designed to always throw exception
        assertThrows(IllegalStateException.class, ms::hashCode);
    }

}
