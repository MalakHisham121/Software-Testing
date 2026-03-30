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