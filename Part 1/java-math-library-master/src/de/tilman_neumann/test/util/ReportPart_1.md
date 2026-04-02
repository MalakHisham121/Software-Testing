# Assignment: Java Math Library Testing
## Assignment 1 – Part 1: Test Design Report
### Student IDs:
    1. Shahd MOhamed Ahmed Sayed: 20220533
    2. Malak Hisham : 20221162
### Course: Software Testing

## Overview

**This report presents the design and implementation of test cases for:**

 - StringUtil
 - Multiset_HashMapImpl

### The goal is to apply:

    - Input Space Partitioning (ISP)
    - Boundary Value Analysis (BVA)
    - Unit Testing using JUnit

### The test suite ensures coverage of:

    - Normal behavior (Happy scenarios)
    - Invalid inputs (Bad scenarios)
    - Edge and boundary cases
# Repeat Method
1. Apply ISP (divide inputs)
    For s:

        null
        empty ""
        normal "a"
        unicode test
    For n:

        negative
        zero
        positive
        large number

2. Apply BVA (pick edges)
   From n:

       -1 (invalid edge)
       0 (boundary)
       1 (smallest valid)
    
   From s:
    
       "" (empty boundary)

3. formatLeft(String s, String mask) Same For formatRight(String s, String mask)
1. Apply ISP (divide inputs)
   s:

       null 
       empty 
       shorter than mask
       longer than mask

   mask:

          null 
          empty 
          longer
          shorter

2. Apply BVA (pick edges)
    For S, mask

       s.length() = 0
       mask.length() = 0
       s.length() = mask.length()

🔹 1. Test Design Methodology

We used:

Input Space Partitioning (ISP)
→ divide inputs into categories (valid, invalid, null, etc.)
Boundary Value Analysis (BVA)
→ test edge values like:
0
1

1

Scenario-based testing
Happy (correct behavior)
Bad (invalid inputs)
Edge (extreme/unusual)
🔹 2. Function-by-Function Analysis
✅ Function: add(T entry)

Description:
Adds one occurrence of element.

   ISP:
   
      new element
      existing element
      
      null ->T

   BVA:
   
      0 → 1
      1 → 2
      
      1

Test Cases: Multiset_HashMapImpl Class
✅ Function: add(T entry, int mult)

   ISP:
   
      mult positive
      mult zero
      mult negative

   BVA:
   
      mult = 0, 1

-----------------------------------
✅ Function: addAll(Multiset)
   ISP:

      null input
      empty multiset
      non-empty multiset
   BVA:

      size = 0, 1, >1
   Coverage:

      if(other != null)
      loop execution / skip
   Edge:

      null keys
      merging existing keys
-----------------------------------
✅ Function: addAll(Collection)
   ISP:

      null collection
      empty collection
      collection with values
   BVA:

      size = 0, 1, >1
   Coverage:

      loop executed / not executed
   Edge:

      duplicate values
      null elements
-----------------------------------

✅ Function: addAll(Array)
   ISP:

      null array
      empty array
      normal array
   BVA:

      length = 0, 1, >1
   Coverage:

      loop execution
   Edge:

      null elements
      duplicates
-----------------------------------


✅ Function: remove(Object key)

   ISP:
   
      exists
      not exists
      null

   BVA:

      count = 1
      count >1

   Scenarios:
   
      decrement (Happy)
      remove last (BVA)
      not exist (Bad)
      null (Edge)

   Coverage:

      all branches executed ✔
-----------------------------------

✅ Function: remove(T key, int mult)

   ISP:
      
      mult positive
      mult zero
      key exists / not exists
   
   BVA:
   
      exact removal
      over-removal
-----------------------------------

✅ Function: removeAll(T key)

   ISP:

      exists / not exists / null

   BVA:

      count = 0, 1, >1
-----------------------------------

intersect()
   ISP:

      other = null
      no common keys
      common keys
   BVA:

      multiplicity = 1
      multiplicity > 1
   Coverage:

      all conditions (if/else branches) executed
   Edge:

      empty sets
      zero multiplicity
-----------------------------------

✅ Function: totalCount()
   ISP:

      empty set
      multiple elements
   BVA:

      sum = 0, 1, large
   Coverage:

      loop entered / not entered
   Edge:

      null key
      large values
-----------------------------------
✅ Function: toList()
   ISP:

      empty / single / multiple elements
   BVA:

      multiplicity = 1, >1
   Coverage:

      nested loops
   Edge:

      null key
      large multiplicit
-----------------------------------

✅ Function: toString()
   ISP:

      empty set
      single element
      multiple elements
   BVA:

      size = 0, 1
      multiplicity = 1, >1
   Coverage:

      if(size>0) and else branch
      multiplicity condition
   Edge:

      null key
      large dataset
-----------------------------------
✅ Function: equals()
   ISP:

      equal sets
      different size
      different multiplicity
      null object
      different type
   BVA:

      multiplicity = 1 vs >1
   Coverage:

      all branches (if conditions)
   Edge:

      null keys
      order independence
-----------------------------------
✅ Function: hashCode()
   ISP:

      always throws
   Coverage:

      exception path
   Edge:

      verify correct exception typ